/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 */
package com.Denfop.ssp;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class SSPBlocks
extends Block {
    public SSPBlocks(String name) {
        super(Material.field_151576_e);
        this.setRegistryName(name);
        this.func_149663_c(name);
    }

    public boolean func_149662_c(IBlockState state) {
        return false;
    }

    public boolean func_149686_d(IBlockState state) {
        return false;
    }
}

