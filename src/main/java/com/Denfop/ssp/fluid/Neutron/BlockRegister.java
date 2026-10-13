/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.block.model.ModelResourceLocation
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraftforge.fml.common.registry.ForgeRegistries
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  net.minecraftforge.registries.IForgeRegistryEntry
 */
package com.Denfop.ssp.fluid.Neutron;

import com.Denfop.ssp.fluid.Neutron.BlockNeutronFluid;
import com.Denfop.ssp.fluid.Neutron.FluidRegister;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class BlockRegister {
    public static BlockNeutronFluid Neutron_BLOCK = new BlockNeutronFluid(FluidRegister.Neutron);

    public static void register() {
        BlockRegister.setRegister((Block)Neutron_BLOCK);
    }

    @SideOnly(value=Side.CLIENT)
    public static void registerRender() {
        BlockRegister.setRender((Block)Neutron_BLOCK);
    }

    private static void setRegister(Block block) {
        ForgeRegistries.BLOCKS.register((IForgeRegistryEntry)block);
        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }

    @SideOnly(value=Side.CLIENT)
    private static void setRender(Block block) {
        Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a(Item.func_150898_a((Block)block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}

