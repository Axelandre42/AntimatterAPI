package muramasa.gtu.api.gui.client;

import muramasa.gtu.api.gui.server.ContainerMachine;
import muramasa.gtu.api.machines.MachineFlag;
import muramasa.gtu.api.tileentities.TileEntityMachine;
import net.minecraft.client.gui.GuiButton;

import java.io.IOException;

public class GuiBasicMachine extends GuiMachine {

    public GuiBasicMachine(TileEntityMachine tile, ContainerMachine container) {
        super(tile, container);
    }

    @Override
    public void initGui() {
        super.initGui();
//        this.buttonList.get(new GuiButton(1, guiLeft, guiTop, 16, 16, "X"));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        drawTooltipInArea(tile.getMachineState().getDisplayName(), mouseX, mouseY, (xSize / 2) - 5, 45, 10, 8);
        if (tile.getType().hasFlag(MachineFlag.FLUID)) {
            drawContainedFluids(mouseX, mouseY);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);

        int progressTime = (int)(20 * tile.getClientProgress());
        drawTexturedModalRect(guiLeft + (xSize / 2) - 10, guiTop + 24, xSize, 0, progressTime, 18);

        if (tile.getMachineState().getOverlayId() == 2) {
            drawTexturedModalRect(guiLeft + (xSize / 2) - 4, guiTop + 44, xSize, 54, 8, 9);
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1) {
            System.out.println("Clicked X");
        }
    }
}