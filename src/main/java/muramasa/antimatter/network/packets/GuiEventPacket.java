package muramasa.antimatter.network.packets;

import muramasa.antimatter.machine.event.GuiEvent;
import muramasa.antimatter.tile.TileEntityMachine;
import muramasa.antimatter.util.Utils;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.util.function.Supplier;

public class GuiEventPacket {

    private final GuiEvent event;
    private final BlockPos pos;
    private final int dimension;

    public GuiEventPacket(GuiEvent event, BlockPos pos, int dimension) {
        this.event = event;
        this.pos = pos;
        this.dimension = dimension;
    }

    public static void encode(GuiEventPacket msg, PacketBuffer buf) {
        buf.writeEnumValue(msg.event);
        buf.writeBlockPos(msg.pos);
        buf.writeVarInt(msg.dimension);
    }

    public static GuiEventPacket decode(PacketBuffer buf) {
        return new GuiEventPacket(buf.readEnumValue(GuiEvent.class), buf.readBlockPos(), buf.readVarInt());
    }

    public static void handle(final GuiEventPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            DimensionType dimensionType = DimensionType.getById(msg.dimension);
            if (dimensionType != null) {
                World world = ServerLifecycleHooks.getCurrentServer().getWorld(dimensionType);
                TileEntity tile = Utils.getTile(world, msg.pos);
                if (tile instanceof TileEntityMachine) {
                    ((TileEntityMachine) tile).onMachineEvent(msg.event);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
