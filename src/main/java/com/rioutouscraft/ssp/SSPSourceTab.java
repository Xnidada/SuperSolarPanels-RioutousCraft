/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.core.block.ITeBlock
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.ItemStack
 */
package com.rioutouscraft.ssp;

import com.rioutouscraft.ssp.SuperSolarPanels;
import com.rioutouscraft.ssp.tiles.SSPBlock;
import ic2.core.block.ITeBlock;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class SSPSourceTab
extends CreativeTabs {
    public SSPSourceTab(String label) {
        super("SSPSourceTab");
    }

    public ItemStack createIcon() {
        return SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.admin_solar_panel);
    }
}

