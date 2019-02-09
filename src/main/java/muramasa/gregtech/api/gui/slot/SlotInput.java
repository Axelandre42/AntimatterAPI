package muramasa.gregtech.api.gui.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotInput extends SlotItemHandler {

    private IItemHandler stackHandler;
    private int index;

    public SlotInput(IItemHandler stackHandler, int index, int x, int y) {
        super(stackHandler, index, x, y);
        this.stackHandler = stackHandler;
        this.index = index;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return true;
    }

    @Override
    public boolean canTakeStack(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public void onSlotChanged() {
        super.onSlotChanged();
//        stackHandler.onInputChanged(index);
    }
}
