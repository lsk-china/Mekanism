package mekanism.client.gui.element.tab;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import mekanism.api.IIncrementalEnum;
import mekanism.client.gui.IGuiWrapper;
import mekanism.client.gui.element.GuiTexturedElement;
import mekanism.common.MekanismLang;
import mekanism.common.config.MekanismConfig;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.MekanismUtils.ResourceType;
import mekanism.common.util.UnitDisplayUtils.TemperatureUnit;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

public class GuiHeatTab extends GuiTexturedElement {

    private static final Map<TemperatureUnit, ResourceLocation> ICONS = new EnumMap<>(TemperatureUnit.class);
    private final IInfoHandler infoHandler;

    public GuiHeatTab(IGuiWrapper gui, IInfoHandler handler) {
        super(MekanismUtils.getResource(ResourceType.GUI_TAB, "heat_info.png"), gui, -26, 109, 26, 26);
        infoHandler = handler;
    }

    @Override
    public void drawBackground(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.drawBackground(guiGraphics, mouseX, mouseY, partialTicks);
        guiGraphics.blit(getResource(), relativeX, relativeY, 0, 0, width, height, width, height);
    }

    @Override
    public void renderToolTip(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        super.renderToolTip(guiGraphics, mouseX, mouseY);
        List<Component> info = new ArrayList<>(infoHandler.getInfo());
        info.add(MekanismLang.UNIT.translate(MekanismConfig.common.tempUnit.get()));
        displayTooltips(guiGraphics, mouseX, mouseY, info);
    }

    @Override
    protected ResourceLocation getResource() {
        return ICONS.computeIfAbsent(MekanismConfig.common.tempUnit.get(), type -> MekanismUtils.getResource(ResourceType.GUI_TAB,
              "heat_info_" + type.getTabName() + ".png"));
    }

    @Override
    public void onClick(double mouseX, double mouseY, int button) {
        if (button == GLFW.GLFW_MOUSE_BUTTON_1) {
            updateTemperatureUnit(IIncrementalEnum::getNext);
        } else if (button == GLFW.GLFW_MOUSE_BUTTON_2) {
            updateTemperatureUnit(IIncrementalEnum::getPrevious);
        }
    }

    @Override
    public boolean isValidClickButton(int button) {
        return button == GLFW.GLFW_MOUSE_BUTTON_1 || button == GLFW.GLFW_MOUSE_BUTTON_2;
    }

    private void updateTemperatureUnit(UnaryOperator<TemperatureUnit> converter) {
        TemperatureUnit current = MekanismConfig.common.tempUnit.get();
        TemperatureUnit updated = converter.apply(current);
        if (current != updated) {//Should always be true but validate it
            MekanismConfig.common.tempUnit.set(updated);
            MekanismConfig.common.save();
        }
    }
}