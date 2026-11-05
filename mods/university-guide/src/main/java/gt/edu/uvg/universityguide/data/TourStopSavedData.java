package gt.edu.uvg.universityguide.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

/** Per-dimension persistent UUID index; menus can list stops without loading every destination chunk. */
public final class TourStopSavedData extends SavedData {
    public static final int SUMMARY_LENGTH=160;
    private static final String FILE_NAME = "universityguide_stops";
    private static final Factory<TourStopSavedData> FACTORY =
            new Factory<>(TourStopSavedData::new, TourStopSavedData::load);

    private final Map<UUID, Entry> entries = new LinkedHashMap<>();

    /** Retrieve the index from this dimension's data storage; dimensions do not share a stop map. */
    public static TourStopSavedData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(FACTORY, FILE_NAME);
    }

    /** Skip records lacking a UUID or valid position rather than constructing unusable destinations. */
    private static TourStopSavedData load(CompoundTag tag, HolderLookup.Provider registries) {
        TourStopSavedData data = new TourStopSavedData();
        ListTag list = tag.getList("Stops", Tag.TAG_COMPOUND);
        for (int index = 0; index < list.size(); index++) {
            CompoundTag stopTag = list.getCompound(index);
            if (!stopTag.hasUUID("Id")) {
                continue;
            }
            Optional<BlockPos> pos = NbtUtils.readBlockPos(stopTag, "Pos");
            pos.ifPresent(blockPos -> {
                UUID id = stopTag.getUUID("Id");
                data.entries.put(id, new Entry(id, blockPos, stopTag.getString("Name"),brief(stopTag.getString("Summary"))));
            });
        }
        return data;
    }

    /** Copy mutable positions and mark data dirty only when the stored entry actually changes. */
    public void upsert(UUID id, BlockPos pos, String name) {
        upsert(id,pos,name,entries.containsKey(id)?entries.get(id).summary():"");
    }

    // Guarda un resumen sin cargar cada chunk al abrir el menú.
    public void upsert(UUID id,BlockPos pos,String name,String description){
        Entry replacement = new Entry(id, pos.immutable(), name,brief(description));
        if (!replacement.equals(entries.put(id, replacement))) {
            setDirty();
        }
    }
    private static String brief(String text){
        String clean=text==null?"":text.replace('\u0000',' ').replaceAll("\\s+"," ").trim();
        if(clean.length()<=SUMMARY_LENGTH)return clean;
        int end=Character.isHighSurrogate(clean.charAt(SUMMARY_LENGTH-1))?SUMMARY_LENGTH-1:SUMMARY_LENGTH;
        return clean.substring(0,end);
    }

    /** Only request a disk save when a record was present and has been removed. */
    public void remove(UUID id) {
        if (entries.remove(id) != null) {
            setDirty();
        }
    }

    /** UUID lookup avoids ambiguity when different stops share a display name. */
    public Optional<Entry> find(UUID id) {
        return Optional.ofNullable(entries.get(id));
    }

    /** Sort names without case sensitivity so destination pagination remains predictable. */
    public List<Entry> sortedEntries() {
        return entries.values().stream()
                .sorted(Comparator.comparing(Entry::name, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    /** Return a snapshot so callers cannot mutate the backing index. */
    public Collection<Entry> entries() {
        return List.copyOf(entries.values());
    }

    /** Serialize the compact index; detailed descriptions remain in each stop's block entity. */
    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        ListTag list = new ListTag();
        for (Entry entry : entries.values()) {
            CompoundTag stopTag = new CompoundTag();
            stopTag.putUUID("Id", entry.id());
            stopTag.put("Pos", NbtUtils.writeBlockPos(entry.pos()));
            stopTag.putString("Name", entry.name());
            stopTag.putString("Summary",entry.summary());
            list.add(stopTag);
        }
        tag.put("Stops", list);
        return tag;
    }

    // Resume una parada persistente por identificador y posición.
    public record Entry(UUID id, BlockPos pos, String name,String summary) {
    }
}
