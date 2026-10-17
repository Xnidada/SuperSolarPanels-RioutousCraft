/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.core.block.state.IIdProvider
 *  ic2.core.init.BlocksItems
 *  ic2.core.item.ItemMulti
 *  ic2.core.ref.ItemName
 *  net.minecraft.client.renderer.block.model.ModelResourceLocation
 *  net.minecraft.item.Item
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.model.ModelLoader
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.rioutouscraft.ssp.items.resource;

import com.rioutouscraft.ssp.SuperSolarPanels;

import ic2.core.block.state.IIdProvider;
import ic2.core.init.BlocksItems;
import ic2.core.item.ItemMulti;
import ic2.core.ref.ItemName;
import java.util.Locale;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CraftingThings
extends ItemMulti {
    protected static final String NAME = "crafting";

    public CraftingThings() {
        super((ItemName)null, CraftingTypes.class);
        setCreativeTab(SuperSolarPanels.SSPtab);
        ((CraftingThings)BlocksItems.registerItem((Item)this, (ResourceLocation)new ResourceLocation("super_solar_panels_rioutouscraft", NAME))).setTranslationKey(NAME);
    }

    @SideOnly(value=Side.CLIENT)
    protected void registerModel(int meta, ItemName name, String extraName) {
        ModelLoader.setCustomModelResourceLocation((Item)this, (int)meta, (ModelResourceLocation)new ModelResourceLocation("super_solar_panels_rioutouscraft:crafting/" + CraftingTypes.getFromID(meta).getName(), (String)null));
    }

    public String getTranslationKey() {
        return "super_solar_panels_rioutouscraft." + super.getTranslationKey().substring(4);
    }

    public static enum CraftingTypes implements IIdProvider
    {
        enderquantumcomponent(0),
        SUNNARIUM(1),
        SUNNARIUM_PART(2),
        SUNNARIUM_ALLOY(3),
        IRRADIANT_URANIUM(4),
        ENRICHED_SUNNARIUM(5),
        ENRICHED_SUNNARIUM_ALLOY(6),
        IRRADIANT_GLASS_PANE(7),
        IRIDIUM_IRON_PLATE(8),
        REINFORCED_IRIDIUM_IRON_PLATE(9),
        IRRADIANT_REINFORCED_PLATE(10),
        IRIDIUM_INGOT(11),
        URANIUM_INGOT(12),
        QUANTUM_CORE(13),
        advanced_core(14),
        hybrid_core(15),
        ultimate_core(16),
        solarsplitter(17),
        bluecomponent(18),
        greencomponent(19),
        redcomponent(20),
        singularcore(21),
        spectralcore(22),
        quantumitems6(23),
        dust(24),
        protoncore(25),
        protonshard(26),
        proton(27),
        neutronshard(28),
        neutron(29),
        neutroncore(30),
        EnrichedSunnariumAlloy2(31),
        EnrichedSunnariumAlloy3(32),
        EnrichedSunnariumAlloy4(33),
        quantcore1(34),
        quantcore2(35),
        nanobox(36),
        depleted_proton_fuel_rod(37),
        depleted_dual_proton_fuel_rod(38),
        depleted_quad_proton_fuel_rod(39),
        depleted_eit_proton_fuel_rod(40),
        QuantumItems2(41),
        QuantumItems3(42),
        QuantumItems4(43),
        QuantumItems5(44),
        compresscarbon(45),
        coal_chunk(46),
        compresscarbonultra(47),
        sunlinse(48),
        nightlinse(49),
        rainlinse(50),
        photoniy_ingot(51),
        photoniy(52),
        barion_core(53),
        hadron_core(54),
        graviton_core(55),
        quark_core(56);

        private final String name = this.name().toLowerCase(Locale.ENGLISH);
        private final int ID;
        private static final CraftingTypes[] VALUES;

        private CraftingTypes(int ID) {
            this.ID = ID;
        }

        public String getName() {
            return this.name;
        }

        public int getId() {
            return this.ID;
        }

        public static CraftingTypes getFromID(int ID) {
            return VALUES[ID % VALUES.length];
        }

        static {
            VALUES = CraftingTypes.values();
        }
    }
}

