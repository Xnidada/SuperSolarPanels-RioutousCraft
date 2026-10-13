/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.material.Material
 */
package com.Denfop.ssp;

import com.Denfop.ssp.SuperSolarPanels;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class SSPB
extends Block {
    public SSPB(Material material, String name, float hardness, float resistance, String harvLevel, int level, SoundType soundType) {
        super(material);
        this.setRegistryName("super_solar_panels_rioutouscraft", name);
        this.func_149663_c(name);
        this.func_149711_c(hardness);
        this.func_149752_b(resistance);
        this.setHarvestLevel(harvLevel, level);
        this.func_149672_a(soundType);
        this.func_149647_a(SuperSolarPanels.SSPtab);
    }
}

