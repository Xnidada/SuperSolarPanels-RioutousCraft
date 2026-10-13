/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.CaseFormat
 *  ic2.api.item.ElectricItem
 *  ic2.api.item.IElectricItem
 *  ic2.api.item.IItemHudInfo
 *  ic2.core.IC2
 *  ic2.core.init.BlocksItems
 *  ic2.core.init.MainConfig
 *  ic2.core.item.ElectricItemManager
 *  ic2.core.item.IPseudoDamageItem
 *  ic2.core.item.ItemIC2
 *  ic2.core.ref.ItemName
 *  ic2.core.util.Config
 *  ic2.core.util.ConfigUtil
 *  ic2.core.util.LogCategory
 *  net.minecraft.client.renderer.block.model.ModelResourceLocation
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.model.ModelLoader
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.rioutouscraft.ssp.items.itembase;

import com.google.common.base.CaseFormat;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.api.item.IItemHudInfo;
import ic2.core.IC2;
import ic2.core.init.BlocksItems;
import ic2.core.init.MainConfig;
import ic2.core.item.ElectricItemManager;
import ic2.core.item.IPseudoDamageItem;
import ic2.core.item.ItemIC2;
import ic2.core.ref.ItemName;
import ic2.core.util.Config;
import ic2.core.util.ConfigUtil;
import ic2.core.util.LogCategory;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BaseElectricItem
extends ItemIC2
implements IPseudoDamageItem,
IElectricItem,
IItemHudInfo {
    protected static String name;
    public static final boolean logIncorrectItemDamaging;
    protected final double maxCharge;
    protected final double transferLimit;
    protected final int tier;

    public BaseElectricItem(String name, double maxCharge, double transferLimit, int tier) {
        super(null);
        this.maxCharge = maxCharge;
        this.transferLimit = transferLimit;
        this.tier = tier;
        this.setMaxDamage(27);
        this.setMaxStackSize(1);
        this.setNoRepair();
        BaseElectricItem.name = name;
        ((BaseElectricItem)BlocksItems.registerItem((Item)this, (ResourceLocation)new ResourceLocation("super_solar_panels_rioutouscraft", BaseElectricItem.name))).setTranslationKey(name);
    }

    public String getTranslationKey() {
        return "super_solar_panels_rioutouscraft." + super.getTranslationKey().substring(4);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerModels(ItemName name) {
        ModelLoader.setCustomModelResourceLocation((Item)this, (int)0, (ModelResourceLocation)new ModelResourceLocation("super_solar_panels_rioutouscraft:" + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, BaseElectricItem.name), null));
    }

    public boolean canProvideEnergy(ItemStack stack) {
        return false;
    }

    public double getMaxCharge(ItemStack stack) {
        return this.maxCharge;
    }

    public int getTier(ItemStack stack) {
        return this.tier;
    }

    public double getDamageAbsorptionRatio() {
        return 0.0;
    }

    public int getEnergyPerDamage() {
        return 0;
    }

    public double getTransferLimit(ItemStack stack) {
        return this.transferLimit;
    }

    public List<String> getHudInfo(ItemStack stack, boolean advanced) {
        LinkedList<String> info = new LinkedList<String>();
        info.add(ElectricItem.manager.getToolTip(stack));
        return info;
    }

    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (!this.isInCreativeTab(tab)) {
            return;
        }
        ElectricItemManager.addChargeVariants((Item)this, subItems);
    }

    public void setDamage(ItemStack stack, int damage) {
        int prev = this.getDamage(stack);
        if (damage != prev && logIncorrectItemDamaging) {
            IC2.log.warn(LogCategory.Armor, new Throwable(), "Detected invalid armor damage application (%d):", new Object[]{damage - prev});
        }
    }

    public void setStackDamage(ItemStack stack, int damage) {
        super.setDamage(stack, damage);
    }

    static {
        logIncorrectItemDamaging = ConfigUtil.getBool((Config)MainConfig.get(), (String)"debug/logIncorrectItemDamaging");
    }
}

