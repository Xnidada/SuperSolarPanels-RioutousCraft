/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.api.reactor.IReactor
 *  ic2.core.profile.NotClassic
 *  net.minecraft.item.ItemStack
 */
package com.rioutouscraft.ssp.items.reactors;

import com.rioutouscraft.ssp.Configs;
import com.rioutouscraft.ssp.items.SSP_Items;
import com.rioutouscraft.ssp.items.reactors.ItemReactorUranium;
import com.rioutouscraft.ssp.items.resource.CraftingThings;
import ic2.api.reactor.IReactor;
import ic2.core.profile.NotClassic;
import net.minecraft.item.ItemStack;

@NotClassic
public class ItemReactorProton
extends ItemReactorUranium {
    public ItemReactorProton(String name, int cells) {
        super(name, cells, Configs.proton_fuel_rod);
    }

    @Override
    protected int getFinalHeat(ItemStack stack, IReactor reactor, int x, int y, int heat) {
        float breedereffectiveness;
        if (reactor.isFluidCooled() && (double)(breedereffectiveness = (float)(reactor.getHeat() / reactor.getMaxHeat())) > 0.5) {
            heat *= 1;
        }
        return heat;
    }

    @Override
    protected ItemStack getDepletedStack(ItemStack stack, IReactor reactor) {
        switch (this.numberOfCells) {
            case 1: {
                ItemStack ret = SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.depleted_proton_fuel_rod);
                return ret.copy();
            }
            case 2: {
                ItemStack ret = SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.depleted_dual_proton_fuel_rod);
                return ret.copy();
            }
            case 4: {
                ItemStack ret = SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.depleted_quad_proton_fuel_rod);
                return ret.copy();
            }
            case 8: {
                ItemStack ret = SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.depleted_eit_proton_fuel_rod);
                return ret.copy();
            }
        }
        throw new RuntimeException("invalid cell count: " + this.numberOfCells);
    }

    @Override
    public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY, boolean heatrun) {
        if (!heatrun) {
            float breedereffectiveness = (float)(reactor.getHeat() / reactor.getMaxHeat()) * 32.0f;
            float ReaktorOutput = 32.0f * breedereffectiveness + 3.0f;
            reactor.addOutput(ReaktorOutput);
        }
        return true;
    }
}

