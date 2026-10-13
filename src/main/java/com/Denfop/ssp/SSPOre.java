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
package com.Denfop.ssp;

import com.Denfop.ssp.SuperSolarPanels;
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
        this.setRegistryName("super_solar_panels", name);
        this.func_149663_c(name);
        this.func_149711_c(hardness);
        this.func_149752_b(resistance);
        this.setHarvestLevel(harvLevel, level);
        this.func_149672_a(soundType);
        this.func_149647_a(SuperSolarPanels.SSPtab);
        this.func_149715_a(0.2f);
    }

    public boolean func_149716_u() {
        return false;
    }

    public int func_149745_a(Random random) {
        return 1;
    }

    public int func_149679_a(int fortune, Random random) {
        if (fortune > 0) {
            return this.func_149745_a(random) + random.nextInt(fortune);
        }
        return this.func_149745_a(random);
    }

    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
        Random rand = world instanceof World ? ((World)world).field_73012_v : new Random();
        return MathHelper.func_76136_a((Random)rand, (int)4, (int)7);
    }
}

