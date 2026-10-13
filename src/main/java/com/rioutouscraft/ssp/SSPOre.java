/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.rioutouscraft.ssp;

import com.rioutouscraft.ssp.SuperSolarPanels;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SSPOre
extends Block {
    public SSPOre(Material material, String name, float hardness, float resistance, String harvLevel, int level, SoundType soundType) {
        super(material);
        this.setRegistryName("super_solar_panels_rioutouscraft", name);
        this.setTranslationKey(name);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setHarvestLevel(harvLevel, level);
        this.setSoundType(soundType);
        this.setCreativeTab(SuperSolarPanels.SSPtab);
        this.setLightLevel(0.2f);
    }

    public boolean hasTileEntity() {
        return false;
    }

    public int quantityDropped(Random random) {
        return 1;
    }

    public int quantityDroppedWithBonus(int fortune, Random random) {
        if (fortune > 0) {
            return this.quantityDropped(random) + random.nextInt(fortune);
        }
        return this.quantityDropped(random);
    }

    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        return MathHelper.getInt((Random)rand, (int)4, (int)7);
    }
}

