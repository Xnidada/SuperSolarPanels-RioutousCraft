/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.CaseFormat
 *  ic2.api.reactor.IReactor
 *  ic2.core.init.BlocksItems
 *  ic2.core.init.Localization
 *  ic2.core.ref.ItemName
 *  net.minecraft.client.renderer.block.model.ModelResourceLocation
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.client.model.ModelLoader
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.rioutouscraft.ssp.items.reactors;

import com.rioutouscraft.ssp.items.itembase.AbstractDamageableReactorComponent;
import com.google.common.base.CaseFormat;
import ic2.api.reactor.IReactor;
import ic2.core.init.BlocksItems;
import ic2.core.init.Localization;
import ic2.core.ref.ItemName;
import java.util.List;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemReactorHeatStorage
extends AbstractDamageableReactorComponent {
    public ItemReactorHeatStorage(String name, int heatStorage) {
        super(name, heatStorage);
        this.name = name;
        ((ItemReactorHeatStorage)BlocksItems.registerItem((Item)this, (ResourceLocation)new ResourceLocation("super_solar_panels_rioutouscraft", this.name))).setTranslationKey(name);
    }

    @Override
    public String getTranslationKey() {
        return "super_solar_panels_rioutouscraft." + super.getTranslationKey().substring(4);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void registerModels(ItemName name) {
        ModelLoader.setCustomModelResourceLocation((Item)this, (int)0, (ModelResourceLocation)new ModelResourceLocation("super_solar_panels_rioutouscraft:" + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.name), null));
    }

    @Override
    public boolean canStoreHeat(ItemStack stack, IReactor reactor, int x, int y) {
        return true;
    }

    @Override
    public int getMaxHeat(ItemStack stack, IReactor reactor, int x, int y) {
        return this.getMaxCustomDamage(stack);
    }

    @Override
    public int getCurrentHeat(ItemStack stack, IReactor reactor, int x, int y) {
        return this.getCustomDamage(stack);
    }

    @Override
    public int alterHeat(ItemStack stack, IReactor reactor, int x, int y, int heat) {
        int myHeat = this.getCurrentHeat(stack, reactor, x, y);
        int max = this.getMaxHeat(stack, reactor, x, y);
        if ((myHeat += heat) > max) {
            reactor.setItemAt(x, y, null);
            heat = max - myHeat + 1;
        } else {
            if (myHeat < 0) {
                heat = myHeat;
                myHeat = 0;
            } else {
                heat = 0;
            }
            this.setCustomDamage(stack, myHeat);
        }
        return heat;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, world, tooltip, advanced);
        if (this.getCustomDamage(stack) > 0) {
            tooltip.add(Localization.translate((String)"ic2.reactoritem.heatwarning.line1"));
            tooltip.add(Localization.translate((String)"ic2.reactoritem.heatwarning.line2"));
        }
    }
}

