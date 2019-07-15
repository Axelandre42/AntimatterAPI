package muramasa.gtu.api.recipe;

import it.unimi.dsi.fastutil.ints.Int2IntArrayMap;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

//Reminder: A simplified version of a HashMap get
//if (e.hash == hash && ((k = e.key) == key || key.equals(k))) return e.value;
public class RecipeInput {

    private ItemWrapper[] items;
    private Int2IntArrayMap itemMap = new Int2IntArrayMap();

    private FluidWrapper[] fluids;
    private Int2IntArrayMap fluidMap = new Int2IntArrayMap();

    private int computedHash;

    public RecipeInput(ItemStack[] items, FluidStack[] fluids) {
        long tempHash = 1; //A long hash used to handle many inputs with nbt hashes
        if (items != null && items.length > 0) {
            this.items = new ItemWrapper[items.length];
            for (int i = 0; i < items.length; i++) {
                this.items[i] = new ItemWrapper(items[i]);
                itemMap.put(this.items[i].getHash(), i);
                tempHash += this.items[i].getHash();
            }
        }
        if (fluids != null && fluids.length > 0) {
            this.fluids = new FluidWrapper[fluids.length];
            for (int i = 0; i < fluids.length; i++) {
                this.fluids[i] = new FluidWrapper(fluids[i]);
                fluidMap.put(this.fluids[i].getHash(), i);
                tempHash += this.fluids[i].getHash();
            }
        }
        computedHash = (int) (tempHash ^ (tempHash >>> 32)); //A int version of the hash for the actual comparision
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RecipeInput)) return false;
        RecipeInput other = (RecipeInput) obj;
        if (items != null) {
            for (int i = 0; i < items.length; i++) {
                if (!other.items[other.itemMap.get(items[i].getHash())].equals(items[i])) return false;
            }
        }
        if (fluids != null) {
            for (int i = 0; i < fluids.length; i++) {
                if (!other.fluids[other.fluidMap.get(fluids[i].getHash())].equals(fluids[i])) return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return computedHash;
    }
}