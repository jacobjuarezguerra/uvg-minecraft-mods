package gt.edu.uvg.universityguide.client.screen;

import gt.edu.uvg.universityguide.block.entity.TourStopBlockEntity;
import gt.edu.uvg.universityguide.network.TourPayloads;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.MultiLineEditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.PacketDistributor;

public final class StopEditorScreen extends Screen {
    private final TourPayloads.OpenStopEditor payload;
    private EditBox nameBox;
    private MultiLineEditBox descriptionBox;
    private Button saveButton;

    public StopEditorScreen(TourPayloads.OpenStopEditor payload) {
        super(Component.translatable("screen.universityguide.edit_stop"));
        this.payload = payload;
    }

    @Override
    protected void init() {
        String currentName = nameBox == null ? payload.name() : nameBox.getValue();
        String currentDescription = descriptionBox == null ? payload.description() : descriptionBox.getValue();
        int contentWidth = Math.min(420, width - 30);
        int left = (width - contentWidth) / 2;

        nameBox = new EditBox(font, left, 45, contentWidth, 20, Component.translatable("screen.universityguide.stop_name"));
        nameBox.setMaxLength(TourStopBlockEntity.MAX_NAME_LENGTH);
        nameBox.setValue(currentName);
        nameBox.setResponder(value -> updateSaveState());
        addRenderableWidget(nameBox);

        int descriptionHeight = Math.max(70, Math.min(180, height - 135));
        descriptionBox = new MultiLineEditBox(font, left, 85, contentWidth, descriptionHeight,
                Component.translatable("screen.universityguide.description_placeholder"),
                Component.translatable("screen.universityguide.stop_description"));
        descriptionBox.setCharacterLimit(TourStopBlockEntity.MAX_DESCRIPTION_LENGTH);
        descriptionBox.setValue(currentDescription);
        addRenderableWidget(descriptionBox);

        int buttonsY = Math.min(height - 28, 95 + descriptionHeight);
        saveButton = addRenderableWidget(Button.builder(Component.translatable("gui.done"), button -> save())
                .bounds(width / 2 - 125, buttonsY, 120, 20).build());
        addRenderableWidget(Button.builder(Component.translatable("gui.cancel"), button -> onClose())
                .bounds(width / 2 + 5, buttonsY, 120, 20).build());
        setInitialFocus(nameBox);
        updateSaveState();
    }

    private void updateSaveState() {
        if (saveButton != null && nameBox != null) {
            saveButton.active = !nameBox.getValue().trim().isBlank();
        }
    }

    private void save() {
        PacketDistributor.sendToServer(new TourPayloads.UpdateStop(
                payload.pos(), nameBox.getValue(), descriptionBox.getValue()));
        onClose();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        graphics.drawCenteredString(font, title, width / 2, 16, 0xFFFFFF);
        graphics.drawString(font, Component.translatable("screen.universityguide.stop_name"),
                nameBox.getX(), 32, 0xD0D0D0, false);
        graphics.drawString(font, Component.translatable("screen.universityguide.stop_description"),
                descriptionBox.getX(), 72, 0xD0D0D0, false);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
