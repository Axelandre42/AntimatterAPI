package muramasa.gtu.api.registration;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.registry.IRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IModelOverride {

    @SideOnly(Side.CLIENT)
    default void onTextureStitch(TextureMap map) {
        //NOOP
    }

    @SideOnly(Side.CLIENT)
    default void onModelRegistration() {
        //NOOP
    }

    @SideOnly(Side.CLIENT)
    default void onModelBake(IRegistry<ModelResourceLocation, IBakedModel> registry) {
        //NOOP
    }
}
