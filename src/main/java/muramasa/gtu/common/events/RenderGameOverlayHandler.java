package muramasa.gtu.common.events;

import muramasa.gtu.Ref;
import muramasa.gtu.api.data.ItemType;
import muramasa.gtu.api.tools.MaterialTool;
import muramasa.gtu.api.util.Utils;
import muramasa.gtu.api.tileentities.TileEntityBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

@Mod.EventBusSubscriber
public class RenderGameOverlayHandler extends Gui {

    private static ResourceLocation energyBar = new ResourceLocation(Ref.MODID, "textures/gui/energy_bar.png");

    @SubscribeEvent
    public static void onRenderDebugInfo(RenderGameOverlayEvent.Text e) {
        if (Minecraft.getMinecraft().gameSettings.showDebugInfo && Minecraft.getMinecraft().objectMouseOver != null) {
            TileEntity tile = Utils.getTile(Minecraft.getMinecraft().world, Minecraft.getMinecraft().objectMouseOver.getBlockPos());
            if (tile instanceof TileEntityBase) {
                e.getLeft().addAll(((TileEntityBase) tile).getInfo());
            }
        }
    }

    @SubscribeEvent(receiveCanceled = true)
    public static void onRenderGameOverlay(RenderGameOverlayEvent.Pre e) {
        EntityPlayerSP entityPlayerSP = Ref.mc.player;
        ItemStack stack = entityPlayerSP.getHeldItemMainhand();
        if (!(stack.getItem() instanceof MaterialTool)) return;
        MaterialTool tool = (MaterialTool) stack.getItem();
        if (tool.getType().isPowered() && e.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE && !entityPlayerSP.isCreative()) {
            e.setCanceled(true);
        }
    }

    @SubscribeEvent(receiveCanceled = true)
    public static void onRenderGameOverlay(RenderGameOverlayEvent.Post e) {
        if (e.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            EntityPlayerSP entityPlayerSP = Ref.mc.player;
            ItemStack stack = entityPlayerSP.getHeldItemMainhand();
            if (!(stack.getItem() instanceof MaterialTool)) return;
            MaterialTool tool = (MaterialTool) stack.getItem();
            if (tool.getType().isPowered() && !entityPlayerSP.isCreative()) {
                GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
                GL11.glPushMatrix();
                int x = (e.getResolution().getScaledWidth() / 2) - 91;
                int y = e.getResolution().getScaledHeight() - 29;
                Ref.mc.renderEngine.bindTexture(energyBar);
                int energySize = (int)(180 * ((float) tool.getEnergy(stack) / (float) tool.getMaxEnergy(stack)));
                drawModalRectWithCustomSizedTexture(x, y, 0, 0, 182, 5, 182, 15);
                drawModalRectWithCustomSizedTexture(x + 1, y + 1, 0, 6, energySize, 3, 182, 15);
                int iconU = energySize == 0 ? 4 : 0;
                drawModalRectWithCustomSizedTexture(x + 89, y, iconU, 10, 4, 5, 182, 15);
                GL11.glPopAttrib();
                GL11.glPopMatrix();
            } else if (ItemType.DebugScanner.isEqual(stack)) {

            }
        }
    }
}