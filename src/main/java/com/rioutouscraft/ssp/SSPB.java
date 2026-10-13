/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.material.Material
 */
package com.rioutouscraft.ssp;

import com.rioutouscraft.ssp.SuperSolarPanels;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class SSPB
extends Block {
    public SSPB(Material material, String name, float hardness, float resistance, String harvLevel, int level, SoundType soundType) {
        super(material);
        this.setRegistryName("super_solar_panels_rioutouscraft", name);
        this.setTranslationKey(name);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setHarvestLevel(harvLevel, level);
        this.setSoundType(soundType);
        this.setCreativeTab(SuperSolarPanels.SSPtab);
    }
}

