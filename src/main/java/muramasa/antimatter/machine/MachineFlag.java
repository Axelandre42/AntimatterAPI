package muramasa.antimatter.machine;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import muramasa.antimatter.machine.types.Machine;

import java.util.*;

public enum MachineFlag {

    BASIC(), //
    STEAM(),
    MULTI(), //Has structure
    HATCH(),
    ITEM(), //Can store items
    FLUID(),
    ENERGY(), //Needs power
    RECIPE(), //Has a recipe map
    GUI(),
    COVERABLE(),
    CONFIGURABLE();

    public static MachineFlag[] VALUES;

    static {
        VALUES = values();
    }

    private Set<Machine<?>> types = new ObjectOpenHashSet<>();

    public void add(Machine<?>... machines) {
        types.addAll(Arrays.asList(machines));
    }

    public Set<Machine<?>> getTypes() {
        return types;
    }

    public static Collection<Machine<?>> getTypes(MachineFlag... flags) {
        List<Machine<?>> types = new ObjectArrayList<>();
        for (MachineFlag flag : flags) {
            types.addAll(flag.getTypes());
        }
        return types;
    }
}
