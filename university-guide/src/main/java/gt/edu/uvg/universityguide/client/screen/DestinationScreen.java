package gt.edu.uvg.universityguide.client.screen;

import gt.edu.uvg.universityguide.network.TourPayloads;
import java.util.List;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.PacketDistributor;

public final class DestinationScreen extends Screen {
    private static final int PAGE_SIZE = 7;
    private final int guideEntityId;
    private final String guideName;
    private final List<TourPayloads.StopSummary> stops;
    private int page;

    public DestinationScreen(TourPayloads.OpenDestinations payload) {
        super(Component.translatable("screen.universityguide.destinations"));
        this.guideEntityId = payload.guideEntityId();
        this.guideName = payload.guideName();
        this.stops = payload.stops();
    }

    @Override
    protected void init() {
        int contentWidth = Math.min(320, width - 30);
        int left = (width - contentWidth) / 2;
        int firstY = 48;
        int start = page * PAGE_SIZE;
        int end = Math.min(start + PAGE_SIZE, stops.size());

        for (int index = start; index < end; index++) {
            TourPayloads.StopSummary stop = stops.get(index);
            String label = stop.name() + "  (" + stop.distance() + " m)";
            addRenderableWidget(Button.builder(Component.literal(label), button -> select(stop))
                    .bounds(left, firstY + (index - start) * 24, contentWidth, 20)
                    .build());
        }

        int footerY = Math.min(height - 28, firstY + PAGE_SIZE * 24 + 8);
        if (page > 0) {
            addRenderableWidget(Button.builder(Component.literal("<"), button -> changePage(-1))
                    .bounds(left, footerY, 40, 20).build());
        }
        if ((page + 1) * PAGE_SIZE < stops.size()) {
            addRenderableWidget(Button.builder(Component.literal(">"), button -> changePage(1))
                    .bounds(left + contentWidth - 40, footerY, 40, 20).build());
        }
        addRenderableWidget(Button.builder(Component.translatable("gui.cancel"), button -> onClose())
                .bounds(width / 2 - 60, footerY, 120, 20).build());
    }

    private void select(TourPayloads.StopSummary stop) {
        PacketDistributor.sendToServer(new TourPayloads.SelectDestination(guideEntityId, stop.id()));
        onClose();
    }

    private void changePage(int difference) {
        page += difference;
        rebuildWidgets();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        graphics.drawCenteredString(font, title, width / 2, 16, 0xFFFFFF);
        graphics.drawCenteredString(font, Component.literal(guideName), width / 2, 30, 0xA0E8A0);
        if (stops.isEmpty()) {
            graphics.drawCenteredString(font, Component.translatable("screen.universityguide.no_destinations"),
                    width / 2, height / 2, 0xB0B0B0);
        } else {
            int pages = (stops.size() + PAGE_SIZE - 1) / PAGE_SIZE;
            graphics.drawString(font, Component.translatable("screen.universityguide.page", page + 1, pages),
                    8, height - 12, 0x909090, false);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
