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
package com.rioutouscraft.ssp.fluid.Neutron;

import com.rioutouscraft.ssp.SuperSolarPanels;

import com.rioutouscraft.ssp.fluid.Neutron.BlockNeutronFluid;
import com.rioutouscraft.ssp.fluid.Neutron.FluidRegister;
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
        ForgeRegistries.BLOCKS.register(block);
        ItemBlock itemBlock = new ItemBlock(block);
        itemBlock.setCreativeTab(SuperSolarPanels.SSPtab);
        itemBlock.setRegistryName(block.getRegistryName());
        ForgeRegistries.ITEMS.register(itemBlock);
    }

    @SideOnly(value=Side.CLIENT)
    private static void setRender(Block block) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock((Block)block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}

