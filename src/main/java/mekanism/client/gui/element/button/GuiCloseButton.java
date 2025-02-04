package mekanism.client.gui.element.button;

import mekanism.client.gui.IGuiWrapper;
import mekanism.client.gui.element.window.GuiWindow;
import mekanism.common.MekanismLang;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.MekanismUtils.ResourceType;

public class GuiCloseButton extends MekanismImageButton {

    public GuiCloseButton(IGuiWrapper gui, int x, int y, GuiWindow window) {
        super(gui, x, y, 8, MekanismUtils.getResource(ResourceType.GUI_BUTTON, "close.png"), window::close,
              (onHover, guiGraphics, mouseX, mouseY) -> gui.displayTooltips(guiGraphics, mouseX, mouseY, MekanismLang.CLOSE.translate()));
    }

    @Override
    public boolean resetColorBeforeRender() {
        return false;
    }
}
