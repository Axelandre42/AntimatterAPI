package muramasa.gregtech.api.cover;

import muramasa.gregtech.Ref;
import muramasa.gregtech.api.GregTechAPI;
import muramasa.gregtech.api.enums.ToolType;
import muramasa.gregtech.api.texture.Texture;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import java.util.List;

public abstract class Cover {

    private static int lastInternalId = 0;

    private int internalId;

    public abstract String getName();

    public int getInternalId() {
        return internalId;
    }

    public final void onRegister() {
        internalId = lastInternalId++;
    }

    public final Cover getNewInstance(ItemStack stack) {
        int id = internalId;
        Cover cover = onPlace(stack);
        cover.internalId = id;
        return cover;
    }

    public Cover onPlace(ItemStack stack) {
        return this;
    }

    public boolean onInteract(TileEntity tile, ToolType type) {
        return true;
    }

    public void onUpdate(TileEntity tile) {
        //NOOP
    }

    public List<BakedQuad> onRender(List<BakedQuad> quads, int side) {
        return quads;
    }

    public boolean isEqual(Cover cover) {
        return internalId == cover.getInternalId();
    }

    public boolean isEmpty() {
        return internalId == GregTechAPI.CoverNone.internalId;
    }

    public Texture[] getTextures() {
        return new Texture[] {
            new Texture("blocks/machine/cover/" + getName())
        };
    }

    public ModelResourceLocation getModel() {
        return new ModelResourceLocation(Ref.MODID + ":machine/cover/" + getName());
    }

    public static ModelResourceLocation getBasicModel() {
        return new ModelResourceLocation(Ref.MODID + ":machine/cover/basic");
    }

    public static int getLastInternalId() {
        return lastInternalId;
    }
}