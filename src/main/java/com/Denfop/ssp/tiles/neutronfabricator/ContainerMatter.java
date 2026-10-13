/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.core.ContainerFullInv
 *  ic2.core.block.invslot.InvSlot
 *  ic2.core.slot.SlotInvSlot
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 */
package com.Denfop.ssp.tiles.neutronfabricator;

import com.Denfop.ssp.tiles.neutronfabricator.TileEntityMassFabricator;
import ic2.core.ContainerFullInv;
import ic2.core.block.invslot.InvSlot;
import ic2.core.slot.SlotInvSlot;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerMatter
extends ContainerFullInv<TileEntityMassFabricator> {
    public ContainerMatter(EntityPlayer player, TileEntityMassFabricator tileEntity) {
        super(player, (IInventory)tileEntity, 166);
        this.func_75146_a((Slot)new SlotInvSlot(tileEntity.amplifierSlot, 0, 72, 40));
        this.func_75146_a((Slot)new SlotInvSlot((InvSlot)tileEntity.outputSlot, 0, 125, 59));
        this.func_75146_a((Slot)new SlotInvSlot((InvSlot)tileEntity.containerslot, 0, 125, 23));
        for (int i = 0; i < 4; ++i) {
            this.func_75146_a((Slot)new SlotInvSlot((InvSlot)tileEntity.upgradeSlot, i, 152, 8 + i * 18));
        }
    }

    public List<String> getNetworkedFields() {
        List ret = super.getNetworkedFields();
        ret.add("energy");
        ret.add("scrap");
        ret.add("fluidTank");
        return ret;
    }
}

