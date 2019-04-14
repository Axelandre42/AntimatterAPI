package muramasa.gtu.api.tileentities;

import muramasa.gtu.Ref;
import muramasa.gtu.api.GregTechAPI;
import muramasa.gtu.api.capability.GTCapabilities;
import muramasa.gtu.api.capability.impl.*;
import muramasa.gtu.api.machines.ContentUpdateType;
import muramasa.gtu.api.machines.MachineState;
import muramasa.gtu.api.recipe.Recipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;

import java.util.List;

import static muramasa.gtu.api.machines.MachineFlag.*;
import static muramasa.gtu.api.machines.MachineState.*;

public abstract class TileEntityBasicMachine extends TileEntityMachine {

    /** Capabilities **/
    //TODO move to TileEntityMachine?
    protected MachineItemHandler itemHandler;
    protected MachineFluidHandler fluidHandler;
    protected MachineEnergyHandler energyHandler;
    protected MachineCoverHandler coverHandler;
    protected MachineConfigHandler configHandler;

    /** Logic **/
    protected Recipe activeRecipe;
    protected int curProgress, maxProgress;
    protected float clientProgress;

    @Override
    public void onFirstTick() {
        super.onFirstTick();
        if (getType().hasFlag(ITEM)) itemHandler = new MachineItemHandler(this, itemData);
        if (getType().hasFlag(FLUID)) fluidHandler = new MachineFluidHandler(this, fluidData);
        if (getType().hasFlag(ENERGY)) energyHandler = new MachineEnergyHandler(this);
        if (getType().hasFlag(COVERABLE)) coverHandler = new MachineCoverHandler(this);
        if (getType().hasFlag(CONFIGURABLE)) configHandler = new MachineConfigHandler(this);
        markDirty();
    }

    @Override
    public void onServerUpdate() {
        if (getMachineState() == ACTIVE) tickMachineLoop();
        if (coverHandler != null) coverHandler.tick();
    }

    /** Recipe Methods **/
    public Recipe findRecipe() {
        return null;
    }

    public void checkRecipe() {
        if (getMachineState().allowRecipeCheck()) { //No active recipes, see of contents match one
            System.out.println("check recipe");
            if (!hadFirstTick) return; //TODO fixme
            if ((activeRecipe = findRecipe()) != null) {
                curProgress = 0;
                maxProgress = activeRecipe.getDuration();
                setMachineState(ACTIVE);
                onRecipeFound();
            }
        }
    }

    public MachineState tickRecipe() {
        onRecipeTick();
        if (activeRecipe == null) return IDLE; //TODO this null check added if saved state triggers tickMachineLoop, but there is no recipe to process
        if (curProgress == maxProgress) { //End of current recipe cycle, deposit items
            if (!canOutput()) return OUTPUT_FULL; //Return and loop until outputs can be added
            curProgress = 0;
            addOutputs(); //Add outputs and reset to process next recipe cycle
            return !canRecipeContinue() ? IDLE : ACTIVE; //Check if has enough stack count for next recipe cycle
        } else {
            //Calculate per recipe tick so user has risk of losing items
            if (!consumeResourceForRecipe()) return curProgress == 0 ? NO_POWER : POWER_LOSS;
            if (curProgress == 0) consumeInputs(); //Consume recipe inputs on first recipe tick
            curProgress++;
            return ACTIVE;
        }
    }

    public void tickMachineLoop() {
        switch (getMachineState()) {
            case ACTIVE:
            case OUTPUT_FULL:
                setMachineState(tickRecipe());
                break;
        }
    }

    public boolean consumeResourceForRecipe() {
        if (energyHandler.extract(activeRecipe.getPower(), true) == activeRecipe.getPower()) {
            energyHandler.extract(activeRecipe.getPower(), false);
            return true;
        }
        return false;
    }

    public void consumeInputs() {
        //NOOP
    }

    public boolean canOutput() {
        return true; //NOOP
    }

    public void addOutputs() {
        //NOOP
    }

    public boolean canRecipeContinue() {
        return true; //NOOP
    }

    /** Events **/
    public void onRecipeFound() {
        //NOOP
    }

    public void onRecipeTick() {
        //NOOP
    }

    @Override
    public void onContentsChanged(ContentUpdateType type, int slot, boolean empty) {
        if (empty) return;
        switch (type) {
            case ITEM_INPUT:
                if (getMachineState().allowLoopTick() || getMachineState() == NO_POWER) tickMachineLoop();
                if (getType().hasFlag(RECIPE)) checkRecipe();
                break;
            case ITEM_OUTPUT:
                if (getMachineState().allowLoopTick() || getMachineState() == NO_POWER) tickMachineLoop();
                break;
        }
        markDirty(); //TODO determine if needed
    }

    /** Getters **/
    @Override
    public float getClientProgress() {
        return clientProgress;
    }

    @Override
    public int getCurProgress() {
        return curProgress;
    }

    @Override
    public int getMaxProgress() {
        return maxProgress;
    }

    @Override
    public MachineItemHandler getItemHandler() {
        return itemHandler;
    }

    @Override
    public MachineFluidHandler getFluidHandler() {
        return fluidHandler;
    }

    @Override
    public MachineCoverHandler getCoverHandler() {
        return coverHandler;
    }

    /** Setters **/
    @Override
    public void setMachineState(MachineState newState) {
        if (getMachineState().getOverlayId() != newState.getOverlayId() && (newState.getOverlayId() == 0 || newState.getOverlayId() == 1)) {
            markForRenderUpdate();
            System.out.println("RENDER UPDATE");
        }
        super.setMachineState(newState);
    }

    @Override
    public void setClientProgress(float newProgress) {
        clientProgress = newProgress;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey(Ref.KEY_MACHINE_TILE_STATE)) {
            //TODO saving state needed? if recipe is saved, serverUpdate should handle it.
            super.setMachineState(VALUES[compound.getInteger(Ref.KEY_MACHINE_TILE_STATE)]);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if (getMachineState() != null) {
            compound.setInteger(Ref.KEY_MACHINE_TILE_STATE, getMachineState().getId());
        }
        return compound;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing side) {
        if (getType().hasFlag(ITEM) && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (coverHandler == null) return false;
            return side == null || coverHandler.hasCover(side, GregTechAPI.CoverItem);
        } else if (getType().hasFlag(FLUID) && capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return side == null || coverHandler.hasCover(side, GregTechAPI.CoverFluid);
        } else if (getType().hasFlag(ENERGY) && capability == GTCapabilities.ENERGY) {
            if (coverHandler == null) return false;
            return side == null || coverHandler.hasCover(side, GregTechAPI.CoverEnergy);
        } else if (getType().hasFlag(COVERABLE) && capability == GTCapabilities.COVERABLE) {
            if (coverHandler == null) return false;
            return side == null || !coverHandler.get(side).isEmpty();
        } else if (getType().hasFlag(CONFIGURABLE) && capability == GTCapabilities.CONFIGURABLE) {
            return true;
        }
        return super.hasCapability(capability, side);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing side) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemHandler.getInputHandler());
        } else if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(fluidHandler.getInputWrapper());
        } else if (capability == GTCapabilities.ENERGY) {
            return GTCapabilities.ENERGY.cast(energyHandler);
        } else if (capability == GTCapabilities.COVERABLE) {
            return GTCapabilities.COVERABLE.cast(coverHandler);
        } else if (capability == GTCapabilities.CONFIGURABLE) {
            return GTCapabilities.CONFIGURABLE.cast(configHandler);
        }
        return super.getCapability(capability, side);
    }

    @Override
    public List<String> getInfo() {
        List<String> info = super.getInfo();
        if (clientProgress > 0) {
            info.add("Recipe Progress: " + clientProgress);
        }
        return info;
    }
}
