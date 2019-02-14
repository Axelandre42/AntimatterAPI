package muramasa.gregtech.api.enums;

import muramasa.gregtech.api.data.Materials;
import muramasa.gregtech.api.interfaces.IMaterialFlag;
import muramasa.gregtech.api.materials.Material;

import java.util.ArrayList;
import java.util.Locale;

public enum GenerationFlag implements IMaterialFlag {

    //TODO rename to generation flag?

    ORE(),
    DUST(), //Add Dusts
    CRUSHED(),
    CRUSHEDC(),
    CRUSHEDP(),
    INGOT(), //Add Ingots and Nuggets (Can't have Nuggets without Ingots)
    BGEM(), //Add Basic Gem
    GEM(), //Add Gem quality variants
    //    Cell(), //Add Fluid Cells //TODO pointless with fluidStack flag?
    LIQUID(), //Add Standard Fluid
    GAS(), //Add Gas Fluid
    PLASMA(), //Add Plasma Fluid
    TOOLS(), //Add Tool Parts
    PLATE(), //Add Plates
    ROD(), //Add Rods
    RING(), //Add Rings
    BOLT(), //Add Bolts
    FOIL(), //Add Foils
    SCREW(), //Add Screws
    GEAR(), //Add Gears
    SGEAR(), //Add Small Gear
    WIREF(), //Add Fine Wire
    ROTOR(), //Add Rotors //TODO deprecate? only 1 rotor
    DPLATE(), //Add Dense Plates
    SPRING(), //Add Springs
    HINGOT(), //Hot Ingots
    BLOCK(), //Add Blocks
    FRAME(); //Add Frame Blocks

    private long bit;
    private ArrayList<String> materialNames = new ArrayList<>();

    GenerationFlag() {
        bit = 1 << ordinal();
    }

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ENGLISH);
    }

    @Override
    public void add(Material... mats) {
        for (Material material : mats) {
            if (!materialNames.contains(material.getName())) {
                materialNames.add(material.getName());
            }
        }
    }

    @Override
    public long getBit() {
        return this.bit;
    }

    @Override
    public Material[] getMats() {
        Material[] materials = new Material[materialNames.size()];
        int size = materials.length;
        for (int i = 0; i < size; i++) {
            materials[i] = Materials.get(materialNames.get(i));
        }
        return materials;
    }

    @Override
    public ArrayList<String> getMatNames() {
        return materialNames;
    }
}