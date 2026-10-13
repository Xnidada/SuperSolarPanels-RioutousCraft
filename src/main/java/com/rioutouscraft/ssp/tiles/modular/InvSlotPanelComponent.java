package com.rioutouscraft.ssp.tiles.modular;

import com.rioutouscraft.ssp.items.modular.ModularPanelBattery;
import com.rioutouscraft.ssp.items.modular.ModularPanelGlass;
import com.rioutouscraft.ssp.items.modular.ModularPanelOutput;
import ic2.core.block.IInventorySlotHolder;
import ic2.core.block.invslot.InvSlot;
import net.minecraft.item.ItemStack;

public final class InvSlotPanelComponent extends InvSlot {
    public enum Kind { GLASS, BATTERY, OUTPUT }

    private final Kind kind;

    public InvSlotPanelComponent(IInventorySlotHolder<?> base, String name, Kind kind) {
        super(base, name, Access.IO, 1, InvSide.TOP);
        this.kind = kind;
    }

    @Override
    public boolean accepts(ItemStack stack) {
        if (stack == null || stack.isEmpty()) return false;
        switch (kind) {
            case GLASS: return stack.getItem() instanceof ModularPanelGlass;
            case BATTERY: return stack.getItem() instanceof ModularPanelBattery;
            case OUTPUT: return stack.getItem() instanceof ModularPanelOutput;
            default: return false;
        }
    }
}
