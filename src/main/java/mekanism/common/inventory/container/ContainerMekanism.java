package mekanism.common.inventory.container;

import javax.annotation.Nonnull;
import mekanism.common.tile.base.TileEntityMekanism;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;

public abstract class ContainerMekanism<TILE extends TileEntityMekanism> extends Container {

    protected TILE tileEntity;

    protected ContainerMekanism(TILE tile, PlayerInventory inventory) {
        this.tileEntity = tile;
        if (shouldAddSlots()) {
            addSlots();
            if (inventory != null) {
                addInventorySlots(inventory);
                openInventory(inventory);
            }
        }
    }

    protected int getInventoryOffset() {
        return 84;
    }

    protected void addInventorySlots(PlayerInventory inventory) {
        int offset = getInventoryOffset();
        for (int slotY = 0; slotY < 3; slotY++) {
            for (int slotX = 0; slotX < 9; slotX++) {
                addSlot(new Slot(inventory, slotX + slotY * 9 + 9, 8 + slotX * 18, offset + slotY * 18));
            }
        }
        offset += 58;
        for (int slotY = 0; slotY < 9; slotY++) {
            addSlot(new Slot(inventory, slotY, 8 + slotY * 18, offset));
        }
    }

    protected boolean shouldAddSlots() {
        return true;
    }

    protected abstract void addSlots();

    protected void openInventory(PlayerInventory inventory) {
        if (tileEntity != null) {
            tileEntity.open(inventory.player);
            tileEntity.openInventory(inventory.player);
        }
    }

    @Override
    public void onContainerClosed(PlayerEntity entityplayer) {
        super.onContainerClosed(entityplayer);
        closeInventory(entityplayer);
    }

    protected void closeInventory(PlayerEntity entityplayer) {
        if (tileEntity != null) {
            tileEntity.close(entityplayer);
            tileEntity.closeInventory(entityplayer);
        }
    }

    @Override
    public boolean canInteractWith(@Nonnull PlayerEntity entityplayer) {
        return tileEntity == null || tileEntity.isUsableByPlayer(entityplayer);
    }
}