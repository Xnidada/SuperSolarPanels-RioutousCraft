/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.api.item.IBoxable
 *  ic2.core.IC2
 *  ic2.core.init.Localization
 *  ic2.core.item.tool.HarvestLevel
 *  ic2.core.item.tool.ToolClass
 *  ic2.core.profile.Version
 *  ic2.core.ref.IItemModelProvider
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemTool
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.rioutouscraft.ssp.items.itembase;

import com.rioutouscraft.ssp.items.api.IToolClass;
import com.rioutouscraft.ssp.items.itembase.ItemSSP;
import ic2.api.item.IBoxable;
import ic2.core.IC2;
import ic2.core.init.Localization;
import ic2.core.item.tool.HarvestLevel;
import ic2.core.item.tool.ToolClass;
import ic2.core.profile.Version;
import ic2.core.ref.IItemModelProvider;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ItemToolSSP
extends ItemTool
implements IItemModelProvider,
IBoxable {
    protected EnumRarity rarity = EnumRarity.COMMON;
    protected final Set<? extends IToolClass> toolClasses;
    protected String name;

    protected ItemToolSSP(String name, HarvestLevel harvestLevel, Set<? extends IToolClass> toolClasses) {
        this(name, harvestLevel, toolClasses, new HashSet<Block>());
    }

    protected ItemToolSSP(String name, HarvestLevel harvestLevel, Set<? extends IToolClass> toolClasses, Set<Block> mineableBlocks) {
        this(name, 0.0f, 0.0f, harvestLevel, toolClasses, mineableBlocks);
    }

    protected ItemToolSSP(String name, float damage, float speed, HarvestLevel harvestLevel, Set<? extends IToolClass> toolClasses, Set<Block> mineableBlocks) {
        super(damage, speed, harvestLevel.toolMaterial, mineableBlocks);
        this.toolClasses = toolClasses;
        this.setMaxStackSize(1);
        this.setCreativeTab((CreativeTabs)IC2.tabIC2);
        for (IToolClass iToolClass : toolClasses) {
            if (iToolClass.getName() == null) continue;
            this.setHarvestLevel(iToolClass.getName(), harvestLevel.level);
        }
        if (toolClasses.contains(ToolClass.Pickaxe) && harvestLevel.toolMaterial == Item.ToolMaterial.DIAMOND) {
            mineableBlocks.add(Blocks.OBSIDIAN);
            mineableBlocks.add(Blocks.REDSTONE_ORE);
            mineableBlocks.add(Blocks.LIT_REDSTONE_ORE);
        }
    }

    public String getTranslationKey() {
        return "ic2." + super.getTranslationKey().substring(5);
    }

    public String getTranslationKey(ItemStack itemStack) {
        return this.getTranslationKey();
    }

    public String getUnlocalizedNameInefficiently(ItemStack itemStack) {
        return this.getTranslationKey(itemStack);
    }

    public String getItemStackDisplayName(ItemStack itemStack) {
        return Localization.translate((String)this.getTranslationKey(itemStack));
    }

    protected boolean isInCreativeTab(CreativeTabs tab) {
        return this.isEnabled() && super.isInCreativeTab(tab);
    }

    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return ItemSSP.shouldReequip(oldStack, newStack, slotChanged);
    }

    public boolean canHarvestBlock(IBlockState state, ItemStack itemStack) {
        Material material = state.getMaterial();
        for (IToolClass iToolClass : this.toolClasses) {
            if (iToolClass.getBlacklist().contains(state.getBlock())) {
                return false;
            }
            if (iToolClass.getBlacklist().contains(material)) {
                return false;
            }
            if (iToolClass.getWhitelist().contains(state.getBlock())) {
                return true;
            }
            if (!iToolClass.getWhitelist().contains(material)) continue;
            return true;
        }
        return super.canHarvestBlock(state, itemStack);
    }

    public float getDestroySpeed(ItemStack itemStack, IBlockState state) {
        return this.canHarvestBlock(state, itemStack) ? this.efficiency : super.getDestroySpeed(itemStack, state);
    }

    public EnumRarity getRarity(ItemStack stack) {
        return stack.isItemEnchanted() && this.rarity != EnumRarity.EPIC ? EnumRarity.RARE : this.rarity;
    }

    public boolean canBeStoredInToolbox(ItemStack itemStack) {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public int getItemColor(ItemStack stack, int tintIndex) {
        return 0xFFFFFF;
    }

    public ItemToolSSP setRarity(EnumRarity rarity) {
        if (rarity == null) {
            throw new NullPointerException("Rarity cannot be null");
        }
        this.rarity = rarity;
        return this;
    }

    protected boolean isEnabled() {
        return Version.shouldEnable(((Object)((Object)this)).getClass());
    }
}

