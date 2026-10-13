/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.api.item.ICustomDamageItem
 *  ic2.core.IC2
 *  ic2.core.util.LogCategory
 *  ic2.core.util.StackUtil
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.NonNullList
 */
package com.Denfop.ssp.items.itembase;

import com.Denfop.ssp.items.itembase.BaseElectricItem;
import com.Denfop.ssp.items.itembase.ItemSSP;
import ic2.api.item.ICustomDamageItem;
import ic2.core.IC2;
import ic2.core.util.LogCategory;
import ic2.core.util.StackUtil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;

public class ItemGradualInt
extends ItemSSP
implements ICustomDamageItem {
    private static final boolean alwaysShowDurability = true;
    private static final String nbtKey = "advDmg";
    private final int maxDamage;

    public ItemGradualInt(String name, int maxDamage) {
        super(name);
        this.setNoRepair();
        this.maxDamage = maxDamage;
    }

    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    public double getDurabilityForDisplay(ItemStack stack) {
        return this.getCustomDamage(stack) / this.getMaxCustomDamage(stack);
    }

    public boolean func_77645_m() {
        return true;
    }

    public boolean isDamaged(ItemStack stack) {
        return this.getCustomDamage(stack) > 0;
    }

    public int getDamage(ItemStack stack) {
        return this.getCustomDamage(stack);
    }

    public int getCustomDamage(ItemStack stack) {
        if (!stack.func_77942_o()) {
            return 0;
        }
        return stack.func_77978_p().func_74762_e(nbtKey);
    }

    public int getMaxDamage(ItemStack stack) {
        return this.getMaxCustomDamage(stack);
    }

    public int getMaxCustomDamage(ItemStack stack) {
        return this.maxDamage;
    }

    public void setDamage(ItemStack stack, int damage) {
        int prev = this.getCustomDamage(stack);
        if (damage != prev && BaseElectricItem.logIncorrectItemDamaging) {
            IC2.log.warn(LogCategory.Armor, new Throwable(), "Detected invalid gradual item damage application (%d):", new Object[]{damage - prev});
        }
    }

    public void setCustomDamage(ItemStack stack, int damage) {
        NBTTagCompound nbt = StackUtil.getOrCreateNbtData((ItemStack)stack);
        nbt.func_74768_a(nbtKey, damage);
    }

    public boolean applyCustomDamage(ItemStack stack, int damage, EntityLivingBase src) {
        this.setCustomDamage(stack, this.getCustomDamage(stack) + damage);
        return true;
    }

    public void func_150895_a(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (!this.func_194125_a(tab)) {
            return;
        }
        ItemStack stack = new ItemStack((Item)this);
        this.setCustomDamage(stack, 0);
        subItems.add((Object)stack);
    }
}

