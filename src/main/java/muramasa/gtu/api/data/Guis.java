package muramasa.gtu.api.data;

import muramasa.gtu.api.gui.GuiData;

import static muramasa.gtu.api.data.Machines.*;
import static muramasa.gtu.api.gui.SlotType.*;
import static muramasa.gtu.api.machines.Tier.*;

public class Guis {

    public static GuiData MULTI_DISPLAY = new GuiData("multi_display").add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 17, 34).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 143, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34).add(FL_IN, 17, 63).add(FL_IN, 35, 63).add(FL_IN, 53, 63).add(FL_OUT, 107, 63).add(FL_OUT, 125, 63).add(FL_OUT, 143, 63);
    public static GuiData MULTI_DISPLAY_COMPACT = new GuiData("multi_display").add(MULTI_DISPLAY).setPadding(0, 0, 0, 0);

    public static void init() {

        //TODO changing slots of a machine in world, will crash from GTItemHandler.validateSlot()

        ALLOY_SMELTER.getGui().add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 25);
        ASSEMBLER.getGui().add(IT_IN, 17, 16).add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 17, 34).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 25);
        BENDER.getGui().add(ALLOY_SMELTER);
        CANNER.getGui().add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 25);
        COMPRESSOR.getGui().add(IT_IN, 53, 25).add(IT_OUT, 107, 25);
        CUTTER.getGui().add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25);
        FURNACE.getGui().add(ALLOY_SMELTER); //TODO
        EXTRACTOR.getGui().add(COMPRESSOR);
        EXTRUDER.getGui().add(ALLOY_SMELTER);
        LATHE.getGui().add(CUTTER);
        PULVERIZER.getGui().add(COMPRESSOR);
        PULVERIZER.getGui().add(HV, COMPRESSOR).add(HV, IT_OUT, 125, 25);
        PULVERIZER.getGui().add(EV, COMPRESSOR).add(EV, IT_OUT, 125, 25).add(EV, IT_OUT, 143, 25);
        PULVERIZER.getGui().add(IV, IT_IN, 53, 25).add(IV, IT_OUT, 107, 16).add(IV, IT_OUT, 125, 16).add(IV, IT_OUT, 107, 34).add(IV, IT_OUT, 125, 34);
        RECYCLER.getGui().add(COMPRESSOR);
        SCANNER.getGui().add(COMPRESSOR);
        WIRE_MILL.getGui().add(COMPRESSOR);
        CENTRIFUGE.getGui().add(IT_IN, 35, 25).add(IT_IN, 53, 25).add(IT_OUT, 107, 16).add(IT_OUT, 125, 16).add(IT_OUT, 142, 16).add(IT_OUT, 107, 34).add(IT_OUT, 125, 34).add(IT_OUT, 143, 34);
        ELECTROLYZER.getGui().add(CENTRIFUGE);
        THERMAL_CENTRIFUGE.getGui().add(IT_IN, 53, 25).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25);
        ORE_WASHER.getGui().add(THERMAL_CENTRIFUGE);
        CHEMICAL_REACTOR.getGui().add(CANNER);
        FLUID_CANNER.getGui().add(COMPRESSOR);
        DISASSEMBLER.getGui().add(ALLOY_SMELTER); //TODO
        MASS_FABRICATOR.getGui().add(COMPRESSOR);
        AMP_FABRICATOR.getGui().add(COMPRESSOR);
        REPLICATOR.getGui().add(COMPRESSOR);
        FERMENTER.getGui().add(COMPRESSOR);
        FLUID_EXTRACTOR.getGui().add(COMPRESSOR);
        FLUID_SOLIDIFIER.getGui().add(COMPRESSOR).add(FL_IN, 53, 63);
        DISTILLERY.getGui().add(COMPRESSOR);
        CHEMICAL_BATH.getGui().add(THERMAL_CENTRIFUGE);
        AUTOCLAVE.getGui().add(COMPRESSOR);
        MIXER.getGui().add(IT_IN, 35, 16).add(IT_IN, 53, 16).add(IT_IN, 35, 34).add(IT_IN, 53, 34).add(IT_OUT, 107, 25);
        LASER_ENGRAVER.getGui().add(ALLOY_SMELTER);
        FORMING_PRESS.getGui().add(ALLOY_SMELTER);
        FORGE_HAMMER.getGui().add(ALLOY_SMELTER); //TODO
        SIFTER.getGui().add(DISASSEMBLER);
        ARC_FURNACE.getGui().add(ALLOY_SMELTER); //TODO
        PLASMA_ARC_FURNACE.getGui().add(ARC_FURNACE);

        COAL_BOILER.getGui().add(ALLOY_SMELTER); //TODO
        LAVA_BOILER.getGui().add(ALLOY_SMELTER);
        SOLAR_BOILER.getGui().add(ALLOY_SMELTER);
        STEAM_FURNACE.getGui().add(FURNACE);
        STEAM_PULVERIZER.getGui().add(PULVERIZER);
        STEAM_EXTRACTOR.getGui().add(EXTRACTOR);
        STEAM_FORGE_HAMMER.getGui().add(FORGE_HAMMER);
        STEAM_COMPRESSOR.getGui().add(COMPRESSOR);
        STEAM_ALLOY_SMELTER.getGui().add(ALLOY_SMELTER);

        PRIMITIVE_BLAST_FURNACE.getGui().add(IT_IN, 53, 16).add(IT_IN, 53, 34).add(IT_IN, 53, 52).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25).add(IT_OUT, 143, 25).setPadding(0, 0, 0, 0);
        BRONZE_BLAST_FURNACE.getGui().add(IT_IN, 53, 16).add(IT_IN, 53, 34).add(IT_OUT, 107, 25).add(IT_OUT, 125, 25);

        HATCH_MUFFLER.getGui().add(IT_IN, 79, 34);

        HATCH_ITEM_INPUT.getGui().add(ULV, IT_IN, 79, 34);
        HATCH_ITEM_INPUT.getGui().add(LV, IT_IN, 70, 25).add(LV, IT_IN, 88, 25).add(LV, IT_IN, 70, 43).add(LV, IT_IN, 88, 43);
        HATCH_ITEM_INPUT.getGui().add(MV, IT_IN, 61, 16).add(MV, IT_IN, 79, 16).add(MV, IT_IN, 97, 16).add(MV, IT_IN, 61, 34).add(MV, IT_IN, 79, 34).add(MV, IT_IN, 97, 34).add(MV, IT_IN, 61, 52).add(MV, IT_IN, 79, 52).add(MV, IT_IN, 97, 52);
        HATCH_ITEM_INPUT.getGui().add(HV, IT_IN, 52, 7).add(HV, IT_IN, 70, 7).add(HV, IT_IN, 88, 7).add(HV, IT_IN, 106, 7).add(HV, IT_IN, 52, 25).add(HV, IT_IN, 70, 25).add(HV, IT_IN, 88, 25).add(HV, IT_IN, 106, 25).add(HV, IT_IN, 52, 43).add(HV, IT_IN, 70, 43).add(HV, IT_IN, 88, 43).add(HV, IT_IN, 106, 43).add(HV, IT_IN, 52, 61).add(HV, IT_IN, 70, 61).add(HV, IT_IN, 88, 61).add(HV, IT_IN, 106, 61);
        HATCH_ITEM_INPUT.getGui().add(EV, HATCH_ITEM_INPUT, HV);
        HATCH_ITEM_INPUT.getGui().add(IV, HATCH_ITEM_INPUT, HV);
        HATCH_ITEM_INPUT.getGui().add(LUV, HATCH_ITEM_INPUT, HV);
        HATCH_ITEM_INPUT.getGui().add(ZPM, HATCH_ITEM_INPUT, HV);
        HATCH_ITEM_INPUT.getGui().add(UV, HATCH_ITEM_INPUT, HV);
        HATCH_ITEM_INPUT.getGui().add(MAX, HATCH_ITEM_INPUT, HV);

        HATCH_ITEM_OUTPUT.getGui().add(ULV, IT_OUT, 79, 34);
        HATCH_ITEM_OUTPUT.getGui().add(LV, IT_OUT, 70, 25).add(LV, IT_OUT, 88, 25).add(LV, IT_OUT, 70, 43).add(LV, IT_OUT, 88, 43);
        HATCH_ITEM_OUTPUT.getGui().add(MV, IT_OUT, 61, 16).add(MV, IT_OUT, 79, 16).add(MV, IT_OUT, 97, 16).add(MV, IT_OUT, 61, 34).add(MV, IT_OUT, 79, 34).add(MV, IT_OUT, 97, 34).add(MV, IT_OUT, 61, 52).add(MV, IT_OUT, 79, 52).add(MV, IT_OUT, 97, 52);
        HATCH_ITEM_OUTPUT.getGui().add(HV, IT_OUT, 52, 7).add(HV, IT_OUT, 70, 7).add(HV, IT_OUT, 88, 7).add(HV, IT_OUT, 106, 7).add(HV, IT_OUT, 52, 25).add(HV, IT_OUT, 70, 25).add(HV, IT_OUT, 88, 25).add(HV, IT_OUT, 106, 25).add(HV, IT_OUT, 52, 43).add(HV, IT_OUT, 70, 43).add(HV, IT_OUT, 88, 43).add(HV, IT_OUT, 106, 43).add(HV, IT_OUT, 52, 61).add(HV, IT_OUT, 70, 61).add(HV, IT_OUT, 88, 61).add(HV, IT_OUT, 106, 61);
        HATCH_ITEM_OUTPUT.getGui().add(EV, HATCH_ITEM_OUTPUT, HV);
        HATCH_ITEM_OUTPUT.getGui().add(IV, HATCH_ITEM_OUTPUT, HV);
        HATCH_ITEM_OUTPUT.getGui().add(LUV, HATCH_ITEM_OUTPUT, HV);
        HATCH_ITEM_OUTPUT.getGui().add(ZPM, HATCH_ITEM_OUTPUT, HV);
        HATCH_ITEM_OUTPUT.getGui().add(UV, HATCH_ITEM_OUTPUT, HV);
        HATCH_ITEM_OUTPUT.getGui().add(MAX, HATCH_ITEM_OUTPUT, HV);

        HATCH_FLUID_INPUT.getGui().add(FL_IN, 79, 34);
        HATCH_FLUID_INPUT.getGui().add(LV, HATCH_FLUID_INPUT, ULV);
        HATCH_FLUID_INPUT.getGui().add(MV, HATCH_FLUID_INPUT, ULV);
        HATCH_FLUID_INPUT.getGui().add(HV, HATCH_FLUID_INPUT, ULV);
        HATCH_FLUID_INPUT.getGui().add(EV, FL_IN, 61, 16).add(EV, FL_IN, 79, 16).add(EV, FL_IN, 97, 16).add(EV, FL_IN, 61, 34).add(EV, FL_IN, 79, 34).add(EV, FL_IN, 97, 34).add(EV, FL_IN, 61, 52).add(EV, FL_IN, 79, 52).add(EV, FL_IN, 97, 52);
        HATCH_FLUID_INPUT.getGui().add(IV, HATCH_FLUID_INPUT, ULV);
        HATCH_FLUID_INPUT.getGui().add(LUV, HATCH_FLUID_INPUT, ULV);
        HATCH_FLUID_INPUT.getGui().add(ZPM, HATCH_FLUID_INPUT, ULV);
        HATCH_FLUID_INPUT.getGui().add(UV, HATCH_FLUID_INPUT, ULV);
        HATCH_FLUID_INPUT.getGui().add(MAX, HATCH_FLUID_INPUT, ULV);

        HATCH_FLUID_OUTPUT.getGui().add(FL_OUT, 79, 34);
        HATCH_FLUID_OUTPUT.getGui().add(LV, HATCH_FLUID_OUTPUT, ULV);
        HATCH_FLUID_OUTPUT.getGui().add(MV, HATCH_FLUID_OUTPUT, ULV);
        HATCH_FLUID_OUTPUT.getGui().add(HV, HATCH_FLUID_OUTPUT, ULV);
        HATCH_FLUID_OUTPUT.getGui().add(EV, HATCH_FLUID_OUTPUT, ULV);
        HATCH_FLUID_OUTPUT.getGui().add(IV, HATCH_FLUID_OUTPUT, ULV);
        HATCH_FLUID_OUTPUT.getGui().add(LUV, HATCH_FLUID_OUTPUT, ULV);
        HATCH_FLUID_OUTPUT.getGui().add(ZPM, HATCH_FLUID_OUTPUT, ULV);
        HATCH_FLUID_OUTPUT.getGui().add(UV, HATCH_FLUID_OUTPUT, ULV);
        HATCH_FLUID_OUTPUT.getGui().add(MAX, HATCH_FLUID_OUTPUT, ULV);
    }
}