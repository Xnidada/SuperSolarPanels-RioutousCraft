/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.CaseFormat
 *  ic2.api.item.IKineticRotor
 *  ic2.api.item.IKineticRotor$GearboxType
 *  ic2.core.block.kineticgenerator.gui.GuiWaterKineticGenerator
 *  ic2.core.block.kineticgenerator.gui.GuiWindKineticGenerator
 *  ic2.core.init.BlocksItems
 *  ic2.core.init.Localization
 *  ic2.core.profile.NotClassic
 *  ic2.core.ref.ItemName
 *  net.minecraft.client.Minecraft
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
package com.Denfop.ssp.items;

import com.Denfop.ssp.items.itembase.ItemGradualInt;
import com.google.common.base.CaseFormat;
import ic2.api.item.IKineticRotor;
import ic2.core.block.kineticgenerator.gui.GuiWaterKineticGenerator;
import ic2.core.block.kineticgenerator.gui.GuiWindKineticGenerator;
import ic2.core.init.BlocksItems;
import ic2.core.init.Localization;
import ic2.core.profile.NotClassic;
import ic2.core.ref.ItemName;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@NotClassic
public class ItemWindRotor
extends ItemGradualInt
implements IKineticRotor {
    private final int maxWindStrength;
    private final int minWindStrength;
    private final int radius;
    private final ResourceLocation renderTexture;
    private final float efficiency;

    public ItemWindRotor(String name, int Radius, int durability, float efficiency, int minWindStrength, int maxWindStrength, ResourceLocation RenderTexture) {
        super(name, durability);
        this.func_77625_d(1);
        this.radius = Radius;
        this.efficiency = efficiency;
        this.renderTexture = RenderTexture;
        this.minWindStrength = minWindStrength;
        this.maxWindStrength = maxWindStrength;
        this.name = name;
        ((ItemWindRotor)BlocksItems.registerItem((Item)this, (ResourceLocation)new ResourceLocation("super_solar_panels", this.name))).func_77655_b(name);
    }

    @Override
    public String func_77658_a() {
        return "super_solar_panels." + super.func_77658_a().substring(4);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void registerModels(ItemName name) {
        ModelLoader.setCustomModelResourceLocation((Item)this, (int)0, (ModelResourceLocation)new ModelResourceLocation("super_solar_panels:" + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.name), null));
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
        tooltip.add(Localization.translate((String)"ic2.itemrotor.wind.info", (Object[])new Object[]{this.minWindStrength, this.maxWindStrength}));
        IKineticRotor.GearboxType type = null;
        if (Minecraft.func_71410_x().field_71462_r instanceof GuiWaterKineticGenerator) {
            type = IKineticRotor.GearboxType.WATER;
        } else if (Minecraft.func_71410_x().field_71462_r instanceof GuiWindKineticGenerator) {
            type = IKineticRotor.GearboxType.WIND;
        }
        if (type != null) {
            tooltip.add(Localization.translate((String)("ic2.itemrotor.fitsin." + this.isAcceptedType(stack, type))));
        }
    }

    public int getDiameter(ItemStack stack) {
        return this.radius;
    }

    public float getEfficiency(ItemStack stack) {
        return this.efficiency;
    }

    public int getMinWindStrength(ItemStack stack) {
        return this.minWindStrength;
    }

    public int getMaxWindStrength(ItemStack stack) {
        return this.maxWindStrength;
    }

    public boolean isAcceptedType(ItemStack stack, IKineticRotor.GearboxType type) {
        return type == IKineticRotor.GearboxType.WIND;
    }

    public ResourceLocation getRotorRenderTexture(ItemStack stack) {
        return this.renderTexture;
    }
}

