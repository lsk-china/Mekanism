package mekanism.common.inventory.container.robit;

import javax.annotation.Nonnull;
import mekanism.common.entity.EntityRobit;
import mekanism.common.inventory.slot.SlotEnergy.SlotDischarge;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;

public class ContainerRobitMain extends ContainerRobit {

    public ContainerRobitMain(PlayerInventory inventory, EntityRobit entity) {
        super(entity, inventory);
    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(PlayerEntity player, int slotID) {
        return ItemStack.EMPTY;
    }

    @Override
    protected void addSlots() {
        addSlot(new SlotDischarge(robit, 27, 153, 17));
    }
}