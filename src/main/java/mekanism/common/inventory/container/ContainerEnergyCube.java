package mekanism.common.inventory.container;

import mekanism.common.inventory.slot.SlotEnergy.SlotCharge;
import mekanism.common.inventory.slot.SlotEnergy.SlotDischarge;
import mekanism.common.tile.energy_cube.TileEntityEnergyCube;
import net.minecraft.entity.player.PlayerInventory;

public class ContainerEnergyCube extends ContainerEnergyStorage<TileEntityEnergyCube> {

    public ContainerEnergyCube(PlayerInventory inventory, TileEntityEnergyCube tile) {
        super(tile, inventory);
    }

    @Override
    protected void addSlots() {
        addSlot(new SlotCharge(tileEntity, 0, 143, 35));
        addSlot(new SlotDischarge(tileEntity, 1, 17, 35));
    }
}