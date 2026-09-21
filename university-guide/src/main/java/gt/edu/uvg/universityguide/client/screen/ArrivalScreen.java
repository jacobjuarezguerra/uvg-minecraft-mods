package gt.edu.uvg.universityguide.client.screen;

import java.util.List;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;

public final class ArrivalScreen extends Screen {
    private final String description;
    private List<FormattedCharSequence> lines = List.of();
    private int scrollLine;
    private int visibleLines;

    public ArrivalScreen(String title, String description) {
        super(Component.literal(title));
        this.description = description;
    }

    @Override
    protected void init() {
        lines = font.split(Component.literal(description), Math.min(500, width - 50));
        visibleLines = Math.max(3, (height - 100) / 11);
        addRenderableWidget(Button.builder(Component.translatable("gui.done"), button -> onClose())
                .bounds(width / 2 - 75, height - 28, 150, 20).build());
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        graphics.drawCenteredString(font, title, width / 2, 18, 0xFFE08A);
        int left = (width - Math.min(500, width - 50)) / 2;
        int end = Math.min(lines.size(), scrollLine + visibleLines);
        int y = 46;
        for (int index = scrollLine; index < end; index++) {
            graphics.drawString(font, lines.get(index), left, y, 0xF0F0F0, false);
            y += 11;
        }
        if (lines.size() > visibleLines) {
            graphics.drawCenteredString(font,
                    Component.translatable("screen.universityguide.scroll_hint", scrollLine + 1, lines.size()),
                    width / 2, height - 42, 0x909090);
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        int maximum = Math.max(0, lines.size() - visibleLines);
        scrollLine = Mth.clamp(scrollLine - (int)Math.signum(scrollY), 0, maximum);
        return true;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
