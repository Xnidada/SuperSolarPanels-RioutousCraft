/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraftforge.fluids.BlockFluidClassic
 *  net.minecraftforge.fluids.Fluid
 */
package com.rioutouscraft.ssp.fluid.Neutron;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockNeutronFluid
extends BlockFluidClassic {
    public BlockNeutronFluid(Fluid fluid) {
        super(fluid, Material.WATER);
        this.setRegistryName("neutronfluid");
    }
}

