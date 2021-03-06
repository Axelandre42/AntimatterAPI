package muramasa.antimatter.datagen.builder;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelBuilder;

import java.util.List;
import java.util.function.Consumer;

public class AntimatterItemModelBuilder extends ItemModelBuilder {

    protected ResourceLocation loader;
    protected final List<Consumer<JsonObject>> properties = new ObjectArrayList<>();

    public AntimatterItemModelBuilder(ResourceLocation outputLocation, ExistingFileHelper exFileHelper) {
        super(outputLocation, exFileHelper);
    }

    public AntimatterItemModelBuilder property(String property, JsonElement element) {
        properties.add(o -> o.add(property, element));
        return this;
    }

    public AntimatterItemModelBuilder property(String property, String value) {
        properties.add(o -> o.addProperty(property, value));
        return this;
    }

    public AntimatterItemModelBuilder property(String property, boolean value) {
        properties.add(o -> o.addProperty(property, value));
        return this;
    }

    public AntimatterItemModelBuilder bucketLoader() {
        this.loader = new ResourceLocation("forge", "bucket");
        return this;
    }

    public AntimatterItemModelBuilder bucketProperties(Fluid fluid) {
        property("fluid", fluid.getRegistryName().toString());
        property("flipGas", fluid.getAttributes().isLighterThanAir());
        property("applyTint", true);
        // property("coverIsMask", false);
        return bucketLoader();
    }

    @Override
    public JsonObject toJson() {
        JsonObject root = super.toJson();
        if (loader != null) root.addProperty("loader", loader.toString());
        if (!properties.isEmpty()) properties.forEach(c -> c.accept(root));
        return root;
    }

}
