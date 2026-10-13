/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.block.model.ModelResourceLocation
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraftforge.fml.common.registry.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistryEntry
 */
package com.Denfop.ssp;

import com.Denfop.ssp.SSPB;
import com.Denfop.ssp.SSPOre;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class BlocksRegister {
    public static Block iridiumOre = new SSPOre(Material.field_151576_e, "iridium_ore", 5.0f, 8.0f, "pickaxe", 3, SoundType.field_185851_d);
    public static Block iridium_Block = new SSPB(Material.field_151576_e, "iridium_block", 8.0f, 15.0f, "pickaxe", 2, SoundType.field_185851_d);
    public static Block platiumOre = new SSPOre(Material.field_151576_e, "platium_ore", 5.0f, 8.0f, "pickaxe", 3, SoundType.field_185851_d);
    public static Block platium_Block = new SSPB(Material.field_151576_e, "platium_block", 8.0f, 15.0f, "pickaxe", 2, SoundType.field_185851_d);

    public static void register() {
        BlocksRegister.setRegister(iridiumOre);
        BlocksRegister.setRegister(iridium_Block);
        BlocksRegister.setRegister(platiumOre);
        BlocksRegister.setRegister(platium_Block);
    }

    public static void registerRender() {
        BlocksRegister.setRender(iridiumOre);
        BlocksRegister.setRender(iridium_Block);
        BlocksRegister.setRender(platiumOre);
        BlocksRegister.setRender(platium_Block);
    }

    private static void setRegister(Block block) {
        ForgeRegistries.BLOCKS.register((IForgeRegistryEntry)block);
        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }

    private static void setRender(Block block) {
        Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a(Item.func_150898_a((Block)block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}

