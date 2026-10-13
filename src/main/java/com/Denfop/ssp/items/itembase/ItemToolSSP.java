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
package com.Denfop.ssp.items.itembase;

import com.Denfop.ssp.items.api.IToolClass;
import com.Denfop.ssp.items.itembase.ItemSSP;
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
        this.func_77625_d(1);
        this.func_77637_a((CreativeTabs)IC2.tabIC2);
        for (IToolClass iToolClass : toolClasses) {
            if (iToolClass.getName() == null) continue;
            this.setHarvestLevel(iToolClass.getName(), harvestLevel.level);
        }
        if (toolClasses.contains(ToolClass.Pickaxe) && harvestLevel.toolMaterial == Item.ToolMaterial.DIAMOND) {
            mineableBlocks.add(Blocks.field_150343_Z);
            mineableBlocks.add(Blocks.field_150450_ax);
            mineableBlocks.add(Blocks.field_150439_ay);
        }
    }

    public String func_77658_a() {
        return "ic2." + super.func_77658_a().substring(5);
    }

    public String func_77667_c(ItemStack itemStack) {
        return this.func_77658_a();
    }

    public String func_77657_g(ItemStack itemStack) {
        return this.func_77667_c(itemStack);
    }

    public String func_77653_i(ItemStack itemStack) {
        return Localization.translate((String)this.func_77667_c(itemStack));
    }

    protected boolean func_194125_a(CreativeTabs tab) {
        return this.isEnabled() && super.func_194125_a(tab);
    }

    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return ItemSSP.shouldReequip(oldStack, newStack, slotChanged);
    }

    public boolean canHarvestBlock(IBlockState state, ItemStack itemStack) {
        Material material = state.func_185904_a();
        for (IToolClass iToolClass : this.toolClasses) {
            if (iToolClass.getBlacklist().contains(state.func_177230_c())) {
                return false;
            }
            if (iToolClass.getBlacklist().contains(material)) {
                return false;
            }
            if (iToolClass.getWhitelist().contains(state.func_177230_c())) {
                return true;
            }
            if (!iToolClass.getWhitelist().contains(material)) continue;
            return true;
        }
        return super.canHarvestBlock(state, itemStack);
    }

    public float func_150893_a(ItemStack itemStack, IBlockState state) {
        return this.canHarvestBlock(state, itemStack) ? this.field_77864_a : super.func_150893_a(itemStack, state);
    }

    public EnumRarity func_77613_e(ItemStack stack) {
        return stack.func_77948_v() && this.rarity != EnumRarity.EPIC ? EnumRarity.RARE : this.rarity;
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

