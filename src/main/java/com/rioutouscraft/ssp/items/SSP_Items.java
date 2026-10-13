/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.core.block.state.IIdProvider
 *  ic2.core.item.tool.HarvestLevel
 *  ic2.core.item.tool.ToolClass
 *  ic2.core.ref.IItemModelProvider
 *  ic2.core.ref.IMultiItem
 *  ic2.core.ref.ItemName
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.rioutouscraft.ssp.items;

import com.rioutouscraft.ssp.Configs;
import com.rioutouscraft.ssp.items.ItemWindRotor;
import com.rioutouscraft.ssp.items.armor.ItemArmorQuantumBoosts;
import com.rioutouscraft.ssp.items.armor.ItemArmorQuantumHelmet;
import com.rioutouscraft.ssp.items.armor.ItemArmorQuantumLeggins;
import com.rioutouscraft.ssp.items.armor.ItemArmourSolarHelmet;
import com.rioutouscraft.ssp.items.armor.ItemGraviChestplate;
import com.rioutouscraft.ssp.items.battery.ItemBattery;
import com.rioutouscraft.ssp.items.reactors.ItemReactorHeatStorage;
import com.rioutouscraft.ssp.items.reactors.ItemReactorProton;
import com.rioutouscraft.ssp.items.resource.CraftingThings;
import com.rioutouscraft.ssp.items.tools.ItemNanoSaber;
import com.rioutouscraft.ssp.items.tools.ItemUltDrill;
import ic2.core.block.state.IIdProvider;
import ic2.core.item.tool.HarvestLevel;
import ic2.core.item.tool.ToolClass;
import ic2.core.ref.IItemModelProvider;
import ic2.core.ref.IMultiItem;
import ic2.core.ref.ItemName;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public enum SSP_Items {
    Spectral_SOLAR_HELMET,
    Singular_SOLAR_HELMET,
    Quantum_leggins,
    Quantum_boosts,
    CRAFTING,
    GRAVI_CHESTPLATE,
    ADVANCED_crystal,
    quantumsaber,
    spectralsaber,
    Quantum_HELMET,
    twelve_heat_storage,
    max_heat_storage,
    proton_fuel_rod,
    quad_proton_fuel_rod,
    eit_proton_fuel_rod,
    dual_proton_fuel_rod,
    HYBRID_SOLAR_HELMET,
    ULTIMATE_HYBRID_SOLAR_HELMET,
    ADVANCED_SOLAR_HELMET,
    drill,
    iridium,
    compressiridium,
    spectral;

    public Item instance;

    public <T extends Item> T getInstance() {
        return (T)this.instance;
    }

    public <T extends Enum> ItemStack getItemStack(T variant) {
        if (this.instance == null) {
            return null;
        }
        if (this.instance instanceof IMultiItem) {
            IMultiItem multiItem = (IMultiItem)this.instance;
            return multiItem.getItemStack((IIdProvider)variant);
        }
        if (variant == null) {
            return new ItemStack(this.instance);
        }
        throw new IllegalArgumentException("Not applicable");
    }

    public <T extends Item> void setInstance(T instance) {
        if (this.instance != null) {
            throw new IllegalStateException("Duplicate instances!");
        }
        this.instance = instance;
    }

    public static void buildItems(Side side) {
        Singular_SOLAR_HELMET.setInstance(new ItemArmourSolarHelmet(ItemArmourSolarHelmet.SolarHelmetTypes.Singular));
        Spectral_SOLAR_HELMET.setInstance(new ItemArmourSolarHelmet(ItemArmourSolarHelmet.SolarHelmetTypes.Spectral));
        CRAFTING.setInstance(new CraftingThings());
        GRAVI_CHESTPLATE.setInstance(new ItemGraviChestplate());
        Quantum_leggins.setInstance(new ItemArmorQuantumLeggins());
        Quantum_boosts.setInstance(new ItemArmorQuantumBoosts());
        ADVANCED_crystal.setInstance(new ItemBattery("spectral_battery", Configs.maxCharge8, Configs.transferLimit8, Configs.tier8));
        quantumsaber.setInstance(new ItemNanoSaber("quantumsaber", 10, HarvestLevel.Diamond, ToolClass.Sword, Configs.maxCharge1, Configs.transferLimit1, Configs.tier1, Configs.damage1, Configs.damage2));
        spectralsaber.setInstance(new ItemNanoSaber("spectralsaber", 10, HarvestLevel.Diamond, ToolClass.Sword, Configs.maxCharge2, Configs.transferLimit2, Configs.tier2, Configs.damage3, Configs.damage4));
        Quantum_HELMET.setInstance(new ItemArmorQuantumHelmet(ItemArmorQuantumHelmet.SolarHelmetTypes.Helmet));
        twelve_heat_storage.setInstance(new ItemReactorHeatStorage("twelve_heat_storage", Configs.twelve_heat_storage));
        max_heat_storage.setInstance(new ItemReactorHeatStorage("max_heat_storage", Configs.max_heat_storage));
        proton_fuel_rod.setInstance(new ItemReactorProton("proton_fuel_rod", 1));
        dual_proton_fuel_rod.setInstance(new ItemReactorProton("dual_proton_fuel_rod", 2));
        quad_proton_fuel_rod.setInstance(new ItemReactorProton("quad_proton_fuel_rod", 4));
        eit_proton_fuel_rod.setInstance(new ItemReactorProton("eit_proton_fuel_rod", 8));
        ADVANCED_SOLAR_HELMET.setInstance(new ItemArmourSolarHelmet(ItemArmourSolarHelmet.SolarHelmetTypes.ADVANCED));
        HYBRID_SOLAR_HELMET.setInstance(new ItemArmourSolarHelmet(ItemArmourSolarHelmet.SolarHelmetTypes.HYBRID));
        ULTIMATE_HYBRID_SOLAR_HELMET.setInstance(new ItemArmourSolarHelmet(ItemArmourSolarHelmet.SolarHelmetTypes.ULTIMATE));
        drill.setInstance(new ItemUltDrill());
        iridium.setInstance(new ItemWindRotor("rotor_carbon1", Configs.Radius, Configs.rotor_carbon1, Configs.coefficient, Configs.minWindStrength, Configs.maxWindStrength, new ResourceLocation("super_solar_panels_rioutouscraft", "textures/items/carbon_rotor_model1.png")));
        compressiridium.setInstance(new ItemWindRotor("rotor_carbon2", Configs.Radius1, Configs.rotor_carbon2, Configs.coefficient1, Configs.minWindStrength1, Configs.maxWindStrength1, new ResourceLocation("super_solar_panels_rioutouscraft", "textures/items/carbon_rotor_model2.png")));
        spectral.setInstance(new ItemWindRotor("rotor_carbon3", Configs.Radius2, Configs.rotor_carbon3, Configs.coefficient2, Configs.minWindStrength2, Configs.maxWindStrength2, new ResourceLocation("super_solar_panels_rioutouscraft", "textures/items/carbon_rotor_model3.png")));
        if (side == Side.CLIENT) {
            SSP_Items.doModelGuf();
        }
    }

    @SideOnly(value=Side.CLIENT)
    public static void doModelGuf() {
        for (SSP_Items item : SSP_Items.values()) {
            ((IItemModelProvider)item.getInstance()).registerModels((ItemName)null);
        }
    }

    public ItemStack getItemStack(CraftingThings.CraftingTypes proton, int i) {
        return null;
    }
}

