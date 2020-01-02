package muramasa.gtu.api;

import muramasa.gtu.api.cover.Cover;
import muramasa.gtu.api.machines.types.Machine;
import muramasa.gtu.api.pipe.PipeSize;
import muramasa.gtu.api.texture.TextureData;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.data.ModelProperty;

public class GregTechProperties {

    /** Block Dynamic Properties **/
    public static final ModelProperty<int[]> DYNAMIC_CONFIG = new ModelProperty<>();

    /** Block Machine Properties **/
    public static final ModelProperty<Machine> MACHINE_TYPE = new ModelProperty<>();
    public static final ModelProperty<Direction> MACHINE_FACING = new ModelProperty<>();
    public static final ModelProperty<TextureData> MACHINE_TEXTURE = new ModelProperty<>();
    public static final ModelProperty<Cover[]> MACHINE_COVER = new ModelProperty<>();

    /** Block Pipe Properties **/
    //public static PropertyBool PIPE_INSULATED = PropertyBool.create("insulated");
    //public static PropertyBool PIPE_RESTRICTIVE = PropertyBool.create("restrictive");
    public static final ModelProperty<PipeSize> PIPE_SIZE = new ModelProperty<>();
    public static final ModelProperty<Byte> PIPE_CONNECTIONS = new ModelProperty<>();

    /** Block Rock Properties **/
    //public static final ModelProperty<Integer> ROCK_MODEL = new ModelProperty<>();
    public static final IntegerProperty ROCK_MODEL = IntegerProperty.create("rock_model", 0, 6);
}
