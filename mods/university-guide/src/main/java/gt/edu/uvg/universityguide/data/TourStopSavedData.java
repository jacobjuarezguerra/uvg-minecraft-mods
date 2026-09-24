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

public final class TourStopSavedData extends SavedData {
    private static final String FILE_NAME = "universityguide_stops";
    private static final Factory<TourStopSavedData> FACTORY =
            new Factory<>(TourStopSavedData::new, TourStopSavedData::load);

    private final Map<UUID, Entry> entries = new LinkedHashMap<>();

    public static TourStopSavedData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(FACTORY, FILE_NAME);
    }

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
                data.entries.put(id, new Entry(id, blockPos, stopTag.getString("Name")));
            });
        }
        return data;
    }

    public void upsert(UUID id, BlockPos pos, String name) {
        Entry replacement = new Entry(id, pos.immutable(), name);
        if (!replacement.equals(entries.put(id, replacement))) {
            setDirty();
        }
    }

    public void remove(UUID id) {
        if (entries.remove(id) != null) {
            setDirty();
        }
    }

    public Optional<Entry> find(UUID id) {
        return Optional.ofNullable(entries.get(id));
    }

    public List<Entry> sortedEntries() {
        return entries.values().stream()
                .sorted(Comparator.comparing(Entry::name, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    public Collection<Entry> entries() {
        return List.copyOf(entries.values());
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        ListTag list = new ListTag();
        for (Entry entry : entries.values()) {
            CompoundTag stopTag = new CompoundTag();
            stopTag.putUUID("Id", entry.id());
            stopTag.put("Pos", NbtUtils.writeBlockPos(entry.pos()));
            stopTag.putString("Name", entry.name());
            list.add(stopTag);
        }
        tag.put("Stops", list);
        return tag;
    }

    public record Entry(UUID id, BlockPos pos, String name) {
    }
}
