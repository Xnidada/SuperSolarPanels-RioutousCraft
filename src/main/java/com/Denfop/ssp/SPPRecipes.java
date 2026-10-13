/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.api.item.IC2Items
 *  ic2.api.recipe.IRecipeInput
 *  ic2.api.recipe.IRecipeInputFactory
 *  ic2.api.recipe.Recipes
 *  ic2.core.block.ITeBlock
 *  ic2.core.recipe.ColourCarryingRecipe
 *  ic2.core.util.ConfigUtil
 *  ic2.core.util.StackUtil
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.NonNullList
 *  net.minecraftforge.fluids.Fluid
 *  net.minecraftforge.fluids.FluidStack
 *  net.minecraftforge.fluids.FluidUtil
 *  net.minecraftforge.oredict.OreDictionary
 */
package com.Denfop.ssp;

import com.Denfop.ssp.Configs;
import com.Denfop.ssp.IMolecularTransformerRecipeManager;
import com.Denfop.ssp.MTRecipe;
import com.Denfop.ssp.SuperSolarPanels;
import com.Denfop.ssp.fluid.Neutron.FluidRegister;
import com.Denfop.ssp.items.SSP_Items;
import com.Denfop.ssp.items.resource.CraftingThings;
import com.Denfop.ssp.tiles.SSPBlock;
import ic2.api.item.IC2Items;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.IRecipeInputFactory;
import ic2.api.recipe.Recipes;
import ic2.core.block.ITeBlock;
import ic2.core.recipe.ColourCarryingRecipe;
import ic2.core.util.ConfigUtil;
import ic2.core.util.StackUtil;
import java.text.ParseException;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.oredict.OreDictionary;

final class SPPRecipes {
    SPPRecipes() {
    }

    static void addCraftingRecipes() {
        IRecipeInputFactory input = Recipes.inputFactory;
        IRecipeInputFactory input1 = Recipes.inputFactory;
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.redcomponent), "AAA", "BBB", "AAA", Character.valueOf('A'), IC2Items.getItem((String)"glass", (String)"reinforced"), Character.valueOf('B'), Items.field_151137_ax);
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.bluecomponent), "AAA", "BBB", "AAA", Character.valueOf('A'), IC2Items.getItem((String)"glass", (String)"reinforced"), Character.valueOf('B'), new ItemStack(Items.field_151100_aR, 1, 4));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.solarsplitter), "ABC", "ABC", "ABC", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.redcomponent), Character.valueOf('B'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.greencomponent), Character.valueOf('C'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.bluecomponent));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.greencomponent), "A  ", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.IRRADIANT_GLASS_PANE));
        SPPRecipes.addShapelessRecipe(new ItemStack(SSP_Items.Spectral_SOLAR_HELMET.getInstance()), SSP_Items.ULTIMATE_HYBRID_SOLAR_HELMET.getInstance(), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.spectral_solar_panel), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.spectralcore));
        SPPRecipes.addShapelessRecipe(new ItemStack(SSP_Items.Singular_SOLAR_HELMET.getInstance()), SSP_Items.Spectral_SOLAR_HELMET.getInstance(), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.singularcore));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.IRIDIUM_IRON_PLATE), "III", "IPI", "III", Character.valueOf('I'), "plateIron", Character.valueOf('P'), "ingotIridium");
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.ultimate_core), "IPI", Character.valueOf('I'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.ENRICHED_SUNNARIUM_ALLOY), Character.valueOf('P'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.SUNNARIUM_ALLOY));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.advanced_core), "IPI", Character.valueOf('I'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.SUNNARIUM), Character.valueOf('P'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.URANIUM_INGOT));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.hybrid_core), "IPI", Character.valueOf('I'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.SUNNARIUM), Character.valueOf('P'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.SUNNARIUM_ALLOY));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.REINFORCED_IRIDIUM_IRON_PLATE), "ACA", "CIC", "ACA", Character.valueOf('A'), IC2Items.getItem((String)"crafting", (String)"alloy"), Character.valueOf('C'), IC2Items.getItem((String)"crafting", (String)"carbon_plate"), Character.valueOf('I'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.IRIDIUM_IRON_PLATE));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.IRRADIANT_REINFORCED_PLATE), "RSR", "LIL", "RDR", Character.valueOf('R'), Items.field_151137_ax, Character.valueOf('S'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.SUNNARIUM_PART), Character.valueOf('L'), new ItemStack(Items.field_151100_aR, 1, 4), Character.valueOf('I'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.REINFORCED_IRIDIUM_IRON_PLATE), Character.valueOf('D'), Items.field_151045_i);
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.advanced_solar_panel), "PDP", "ASA", "CIC", Character.valueOf('P'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.IRRADIANT_GLASS_PANE), Character.valueOf('A'), IC2Items.getItem((String)"crafting", (String)"alloy"), Character.valueOf('S'), IC2Items.getItem((String)"te", (String)"solar_generator"), Character.valueOf('C'), IC2Items.getItem((String)"crafting", (String)"advanced_circuit"), Character.valueOf('D'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.advanced_core), Character.valueOf('I'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.IRRADIANT_REINFORCED_PLATE));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.hybrid_solar_panel), "PLP", "IAI", "CSC", Character.valueOf('P'), IC2Items.getItem((String)"crafting", (String)"carbon_plate"), Character.valueOf('L'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.hybrid_core), Character.valueOf('I'), IC2Items.getItem((String)"crafting", (String)"iridium"), Character.valueOf('A'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.advanced_solar_panel), Character.valueOf('C'), IC2Items.getItem((String)"crafting", (String)"advanced_circuit"), Character.valueOf('S'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.ENRICHED_SUNNARIUM));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.ultimate_solar_panel), " S ", "SCS", " S ", Character.valueOf('S'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.hybrid_solar_panel), Character.valueOf('C'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.ultimate_core));
        SPPRecipes.addShapelessRecipe(StackUtil.setSize((ItemStack)SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.hybrid_solar_panel), (int)4), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.ultimate_solar_panel));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QUANTUM_CORE), "ANA", "NEN", "ANA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.ENRICHED_SUNNARIUM_ALLOY), Character.valueOf('N'), Items.field_151156_bN, Character.valueOf('E'), Items.field_151061_bv);
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.quantum_solar_panel), " S ", "SQS", " S ", Character.valueOf('S'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.ultimate_solar_panel), Character.valueOf('Q'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QUANTUM_CORE));
        SPPRecipes.addShapedRecipe(new ItemStack(SSP_Items.ADVANCED_SOLAR_HELMET.getInstance()), " S ", "CNC", "GTG", Character.valueOf('S'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.advanced_solar_panel), Character.valueOf('C'), IC2Items.getItem((String)"crafting", (String)"advanced_circuit"), Character.valueOf('N'), IC2Items.getItem((String)"nano_helmet"), Character.valueOf('G'), IC2Items.getItem((String)"cable", (String)"type:gold,insulation:2"), Character.valueOf('T'), IC2Items.getItem((String)"te", (String)"lv_transformer"));
        SPPRecipes.addShapedColourRecipe(new ItemStack(SSP_Items.HYBRID_SOLAR_HELMET.getInstance()), " S ", "CQC", "GTG", Character.valueOf('S'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.hybrid_solar_panel), Character.valueOf('C'), IC2Items.getItem((String)"crafting", (String)"advanced_circuit"), Character.valueOf('Q'), IC2Items.getItem((String)"quantum_helmet"), Character.valueOf('G'), IC2Items.getItem((String)"cable", (String)"type:glass,insulation:0"), Character.valueOf('T'), IC2Items.getItem((String)"te", (String)"hv_transformer"));
        SPPRecipes.addShapedColourRecipe(new ItemStack(SSP_Items.ULTIMATE_HYBRID_SOLAR_HELMET.getInstance()), " S ", "CQC", "GTG", Character.valueOf('S'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.ultimate_solar_panel), Character.valueOf('C'), IC2Items.getItem((String)"crafting", (String)"advanced_circuit"), Character.valueOf('Q'), IC2Items.getItem((String)"quantum_helmet"), Character.valueOf('G'), IC2Items.getItem((String)"cable", (String)"type:glass,insulation:0"), Character.valueOf('T'), IC2Items.getItem((String)"te", (String)"hv_transformer"));
        SPPRecipes.addShapelessRecipe(new ItemStack(SSP_Items.ULTIMATE_HYBRID_SOLAR_HELMET.getInstance()), SSP_Items.HYBRID_SOLAR_HELMET.getInstance(), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.ultimate_solar_panel));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.IRRADIANT_URANIUM), " G ", "GUG", " G ", Character.valueOf('G'), Items.field_151114_aO, Character.valueOf('U'), "ingotUranium");
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.IRRADIANT_GLASS_PANE), "GGG", "UDU", "GGG", Character.valueOf('G'), IC2Items.getItem((String)"glass", (String)"reinforced"), Character.valueOf('U'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.IRRADIANT_URANIUM), Character.valueOf('D'), Items.field_151114_aO);
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.ENRICHED_SUNNARIUM), "UUU", "USU", "UUU", Character.valueOf('U'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.IRRADIANT_URANIUM), Character.valueOf('S'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.SUNNARIUM));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.ENRICHED_SUNNARIUM_ALLOY), " S ", "SAS", " S ", Character.valueOf('S'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.ENRICHED_SUNNARIUM), Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.SUNNARIUM_ALLOY));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.SUNNARIUM_ALLOY), "III", "ISI", "III", Character.valueOf('I'), IC2Items.getItem((String)"crafting", (String)"iridium"), Character.valueOf('S'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.SUNNARIUM));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.SUNNARIUM), "SSS", "SSS", "SSS", Character.valueOf('S'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.SUNNARIUM_PART));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.enderquantumcomponent), "ABA", "BCB", "ABA", Character.valueOf('A'), IC2Items.getItem((String)"crafting", (String)"iridium"), Character.valueOf('B'), Items.field_151061_bv, Character.valueOf('C'), Items.field_151156_bN);
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.spectral_solar_panel), " B ", "BAB", " B ", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.spectralcore), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.quantum_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.singular_solar_panel), " B ", "BAB", " B ", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.singularcore), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.proton_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.admin_solar_panel), " B ", "BAB", " B ", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.quantcore2), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.singular_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.photonic_solar_panel), " B ", "BAB", " B ", Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.admin_solar_panel), Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.quantcore1));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.singularcore), "ABA", "DCD", "ABA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.enderquantumcomponent), Character.valueOf('C'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.photoniy_ingot), Character.valueOf('B'), IC2Items.getItem((String)"crafting", (String)"iridium"), Character.valueOf('D'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.ENRICHED_SUNNARIUM_ALLOY));
        SPPRecipes.addCompressorRecipe(input.forStack(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.photoniy), 9), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.photoniy_ingot));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.spectralcore), "CBC", "BAB", "CBC", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QUANTUM_CORE), Character.valueOf('B'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.photoniy), Character.valueOf('C'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.solarsplitter));
        SPPRecipes.addShapedRecipe(new ItemStack(SSP_Items.GRAVI_CHESTPLATE.getInstance()), "CBC", "CAC", " H ", Character.valueOf('A'), IC2Items.getItem((String)"quantum_chestplate"), Character.valueOf('B'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.quantumitems6), Character.valueOf('C'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QuantumItems3), Character.valueOf('H'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QuantumItems5));
        SPPRecipes.addShapedRecipe(new ItemStack(SSP_Items.Quantum_leggins.getInstance()), "CBC", "CAC", " H ", Character.valueOf('A'), IC2Items.getItem((String)"quantum_leggings"), Character.valueOf('B'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.quantumitems6), Character.valueOf('C'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QuantumItems3), Character.valueOf('H'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QuantumItems5));
        SPPRecipes.addShapedRecipe(new ItemStack(SSP_Items.Quantum_boosts.getInstance()), "CBC", "CAC", " H ", Character.valueOf('A'), IC2Items.getItem((String)"quantum_boots"), Character.valueOf('B'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.quantumitems6), Character.valueOf('C'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QuantumItems3), Character.valueOf('H'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QuantumItems5));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QuantumItems3), "ACA", "CBC", "ACA", Character.valueOf('A'), IC2Items.getItem((String)"crafting", (String)"carbon_plate"), Character.valueOf('B'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.nanobox), Character.valueOf('C'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QuantumItems4));
        SPPRecipes.addShapedRecipe(new ItemStack(SSP_Items.Quantum_HELMET.getInstance()), "CBC", "CAC", " H ", Character.valueOf('A'), IC2Items.getItem((String)"quantum_helmet"), Character.valueOf('B'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.quantumitems6), Character.valueOf('C'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QuantumItems3), Character.valueOf('H'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QuantumItems5));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.EnrichedSunnariumAlloy4), "ABA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.ENRICHED_SUNNARIUM_ALLOY), Character.valueOf('B'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.spectralcore));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.EnrichedSunnariumAlloy3), "ABA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.EnrichedSunnariumAlloy4), Character.valueOf('B'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.photoniy_ingot));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.EnrichedSunnariumAlloy2), "ABA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.EnrichedSunnariumAlloy3), Character.valueOf('B'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.singularcore));
        SPPRecipes.addShapedRecipe(new ItemStack(SSP_Items.drill.getInstance()), "ODO", "COC", Character.valueOf('O'), IC2Items.getItem((String)"upgrade", (String)"overclocker"), Character.valueOf('D'), StackUtil.copyWithWildCard((ItemStack)IC2Items.getItem((String)"diamond_drill")), Character.valueOf('C'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.quantumitems6));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.quantcore2), "ABA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.EnrichedSunnariumAlloy3), Character.valueOf('B'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.singularcore));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.quantcore1), "ABA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.EnrichedSunnariumAlloy2), Character.valueOf('B'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.quantcore2));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.nanobox), "ACA", "CBC", "ACA", Character.valueOf('A'), IC2Items.getItem((String)"crafting", (String)"carbon_plate"), Character.valueOf('B'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.IRIDIUM_IRON_PLATE), Character.valueOf('C'), IC2Items.getItem((String)"crafting", (String)"alloy"));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.quantcore2), "ABA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.EnrichedSunnariumAlloy3), Character.valueOf('B'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.singularcore));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.quantcore1), "ABA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.EnrichedSunnariumAlloy2), Character.valueOf('B'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.quantcore2));
        SPPRecipes.addCompressorRecipe(input.forStack(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.IRIDIUM_INGOT), 9), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QuantumItems2));
        SPPRecipes.addCompressorRecipe(input.forStack(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QuantumItems2), 9), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QuantumItems4));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.quantumitems6), " A ", "ABA", " A ", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.nanobox), Character.valueOf('B'), IC2Items.getItem((String)"crafting", (String)"advanced_circuit"));
        SPPRecipes.addCompressorRecipe(input.forStack(IC2Items.getItem((String)"dust", (String)"energium"), 9), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.dust));
        SPPRecipes.addShapedRecipe(new ItemStack(SSP_Items.quantumsaber.getInstance()), "O  ", "OD ", "OBC", Character.valueOf('O'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.nanobox), Character.valueOf('B'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.dust), Character.valueOf('D'), StackUtil.copyWithWildCard((ItemStack)IC2Items.getItem((String)"nano_saber")), Character.valueOf('C'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.quantumitems6));
        SPPRecipes.addShapedRecipe(new ItemStack(SSP_Items.spectralsaber.getInstance()), "O  ", "OD ", "OBC", Character.valueOf('O'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QuantumItems3), Character.valueOf('B'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.dust), Character.valueOf('D'), new ItemStack(SSP_Items.quantumsaber.getInstance()), Character.valueOf('C'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QuantumItems5));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QuantumItems5), "ACA", "CBC", "ACA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QuantumItems3), Character.valueOf('B'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.quantumitems6), Character.valueOf('C'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QuantumItems2));
        SPPRecipes.addCompressorRecipe(input.forStack(IC2Items.getItem((String)"misc_resource", (String)"iridium_ore")), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.IRIDIUM_INGOT));
        SPPRecipes.addExtrudingRecipe(input.forStack(IC2Items.getItem((String)"crafting", (String)"iridium")), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.IRIDIUM_INGOT));
        SPPRecipes.addCompressorRecipe(input.forStack(IC2Items.getItem((String)"resource", (String)"uranium_ore")), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.URANIUM_INGOT));
        SPPRecipes.addCompressorRecipe(input.forStack(IC2Items.getItem((String)"crushed", (String)"uranium")), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.URANIUM_INGOT));
        SPPRecipes.addCompressorRecipe(input.forStack(IC2Items.getItem((String)"purified", (String)"uranium")), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.URANIUM_INGOT));
        SPPRecipes.addCompressorRecipe(input.forStack(IC2Items.getItem((String)"nuclear", (String)"uranium")), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.URANIUM_INGOT));
        SPPRecipes.addCompressorRecipe(input.forStack(IC2Items.getItem((String)"crafting", (String)"carbon_plate"), 9), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.compresscarbon));
        SPPRecipes.addCompressorRecipe(input.forStack(IC2Items.getItem((String)"crafting", (String)"alloy"), 9), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.compresscarbonultra));
        SPPRecipes.addExtrudingRecipe(input.forStack(IC2Items.getItem((String)"crafting", (String)"coal_chunk"), 9), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.coal_chunk));
        SPPRecipes.addCompressorRecipe(input.forStack(IC2Items.getItem((String)"nuclear", (String)"plutonium")), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.protonshard));
        SPPRecipes.addCompressorRecipe(input.forStack(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.protonshard), 18), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.proton));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.protoncore), " B ", "ACA", " B ", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.proton), Character.valueOf('B'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.EnrichedSunnariumAlloy4), Character.valueOf('C'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.spectralcore));
        SPPRecipes.addCompressorRecipe(input.forStack(FluidUtil.getFilledBucket((FluidStack)new FluidStack((Fluid)FluidRegister.Neutron, 1))), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.neutronshard));
        SPPRecipes.addCompressorRecipe(input.forStack(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.neutronshard), 9), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.neutron));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.neutroncore), " A ", "ABA", " A ", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.neutron), Character.valueOf('B'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.quantcore2));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.neutronium_solar_panel), " B ", "BAB", " B ", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.neutroncore), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.photonic_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.proton_solar_panel), " B ", "BAB", " B ", Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.spectral_solar_panel), Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.protoncore));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.nutronfabricator), " A ", "BCB", " A ", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.enderquantumcomponent), Character.valueOf('C'), IC2Items.getItem((String)"te", (String)"matter_generator"), Character.valueOf('B'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QuantumItems5));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.transformator), "BCB", Character.valueOf('C'), IC2Items.getItem((String)"te", (String)"ev_transformer"), Character.valueOf('B'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.quantumitems6));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.transformator1), "BCB", Character.valueOf('C'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.transformator), Character.valueOf('B'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.quantumitems6));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.transformator2), "BCB", Character.valueOf('C'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.transformator1), Character.valueOf('B'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QuantumItems5));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.transformator3), "BCB", Character.valueOf('C'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.transformator2), Character.valueOf('B'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QuantumItems5));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.ultimatemfsu), " B ", "BCB", " B ", Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.advancedmfsu), Character.valueOf('C'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.quantumitems6));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.quantummfsu), " B ", "BCB", " B ", Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.ultimatemfsu), Character.valueOf('C'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QuantumItems5));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.advancedmfsu), " B ", "BCB", " B ", Character.valueOf('B'), new ItemStack(SSP_Items.ADVANCED_crystal.getInstance()), Character.valueOf('C'), IC2Items.getItem((String)"te", (String)"mfsu"));
        SPPRecipes.addShapedRecipe(new ItemStack(SSP_Items.ADVANCED_crystal.getInstance()), " A ", "DCD", " A ", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.nanobox), Character.valueOf('D'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QuantumItems3), Character.valueOf('C'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.dust));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.molecular_transformer), "MTM", "CcC", "MTM", Character.valueOf('M'), IC2Items.getItem((String)"resource", (String)"advanced_machine"), Character.valueOf('T'), IC2Items.getItem((String)"te", (String)"ev_transformer"), Character.valueOf('C'), IC2Items.getItem((String)"crafting", (String)"advanced_circuit"), Character.valueOf('c'), IC2Items.getItem((String)"nuclear", (String)"plutonium"));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.rainlinse), "USU", Character.valueOf('U'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.sunlinse), Character.valueOf('S'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.nightlinse));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.dust), "UUU", "UUU", "UUU", Character.valueOf('U'), IC2Items.getItem((String)"dust", (String)"energium"));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.ENRICHED_SUNNARIUM), "UUU", "USU", "UUU", Character.valueOf('U'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.IRRADIANT_URANIUM), Character.valueOf('S'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.SUNNARIUM));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.ENRICHED_SUNNARIUM_ALLOY), " S ", "SAS", " S ", Character.valueOf('S'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.ENRICHED_SUNNARIUM), Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.SUNNARIUM_ALLOY));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.SUNNARIUM_ALLOY), "III", "ISI", "III", Character.valueOf('I'), IC2Items.getItem((String)"crafting", (String)"iridium"), Character.valueOf('S'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.SUNNARIUM));
        SPPRecipes.addShapedRecipe(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.SUNNARIUM), "SSS", "SSS", "SSS", Character.valueOf('S'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.SUNNARIUM_PART));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.spectral_solar_panelsun), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.sunlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.spectral_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.singular_solar_panelsun), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.sunlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.singular_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.admin_solar_panelsun), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.sunlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.admin_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.photonic_solar_panelsun), "BA", Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.photonic_solar_panel), Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.sunlinse));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.spectral_solar_panelmoon), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.nightlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.spectral_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.singular_solar_panelmoon), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.nightlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.singular_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.admin_solar_panelmoon), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.nightlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.admin_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.photonic_solar_panelmoon), "BA", Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.photonic_solar_panel), Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.nightlinse));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.spectral_solar_panelrain), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.rainlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.spectral_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.singular_solar_panelrain), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.rainlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.singular_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.admin_solar_panelrain), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.rainlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.admin_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.photonic_solar_panelrain), "BA", Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.photonic_solar_panel), Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.rainlinse));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.proton_solar_panelsun), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.sunlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.proton_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.neutronium_solar_panelsun), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.sunlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.neutronium_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.proton_solar_panelmoon), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.nightlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.proton_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.neutronium_solar_panelmoon), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.nightlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.neutronium_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.proton_solar_panelrain), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.rainlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.proton_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.neutronium_solar_panelrain), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.rainlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.neutronium_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.advanced_solar_panelsun), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.sunlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.advanced_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.hybrid_solar_panelsun), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.sunlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.hybrid_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.advanced_solar_panelmoon), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.nightlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.advanced_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.hybrid_solar_panelmoon), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.nightlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.hybrid_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.advanced_solar_panelrain), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.rainlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.advanced_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.hybrid_solar_panelrain), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.rainlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.hybrid_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.ultimate_solar_panelsun), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.sunlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.ultimate_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.quantum_solar_panelsun), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.sunlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.quantum_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.ultimate_solar_panelmoon), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.nightlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.ultimate_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.quantum_solar_panelmoon), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.nightlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.quantum_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.ultimate_solar_panelrain), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.rainlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.ultimate_solar_panel));
        SPPRecipes.addShapedRecipe(SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.quantum_solar_panelrain), "BA", Character.valueOf('A'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.rainlinse), Character.valueOf('B'), SuperSolarPanels.machines.getItemStack((ITeBlock)SSPBlock.quantum_solar_panel));
        SPPRecipes.addcanerRecipe(input.forStack(IC2Items.getItem((String)"crafting", (String)"fuel_rod"), 1), input1.forStack(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.proton)), new ItemStack(SSP_Items.proton_fuel_rod.getInstance()));
        SPPRecipes.addcentrifugeRecipe(input.forStack(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.depleted_proton_fuel_rod)), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.proton), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.protonshard));
        SPPRecipes.addcentrifugeRecipe(input.forStack(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.depleted_dual_proton_fuel_rod)), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.depleted_proton_fuel_rod), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.depleted_proton_fuel_rod));
        SPPRecipes.addcentrifugeRecipe(input.forStack(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.depleted_quad_proton_fuel_rod)), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.depleted_dual_proton_fuel_rod), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.depleted_dual_proton_fuel_rod));
        SPPRecipes.addcentrifugeRecipe(input.forStack(SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.depleted_eit_proton_fuel_rod)), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.depleted_quad_proton_fuel_rod), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.depleted_quad_proton_fuel_rod));
        SPPRecipes.addShapedColourRecipe(new ItemStack(SSP_Items.dual_proton_fuel_rod.getInstance()), "SQS", Character.valueOf('S'), new ItemStack(SSP_Items.proton_fuel_rod.getInstance()), Character.valueOf('Q'), IC2Items.getItem((String)"plate", (String)"iron"));
        SPPRecipes.addShapedColourRecipe(new ItemStack(SSP_Items.quad_proton_fuel_rod.getInstance()), " S ", "CQC", " S ", Character.valueOf('S'), new ItemStack(SSP_Items.dual_proton_fuel_rod.getInstance()), Character.valueOf('C'), IC2Items.getItem((String)"plate", (String)"copper"), Character.valueOf('Q'), IC2Items.getItem((String)"plate", (String)"iron"));
        SPPRecipes.addShapedColourRecipe(new ItemStack(SSP_Items.quad_proton_fuel_rod.getInstance()), "SQS", "CQC", "SQS", Character.valueOf('S'), new ItemStack(SSP_Items.proton_fuel_rod.getInstance()), Character.valueOf('C'), IC2Items.getItem((String)"plate", (String)"copper"), Character.valueOf('Q'), IC2Items.getItem((String)"plate", (String)"iron"));
        SPPRecipes.addShapedColourRecipe(new ItemStack(SSP_Items.eit_proton_fuel_rod.getInstance()), "SQS", "CQC", "SQS", Character.valueOf('S'), new ItemStack(SSP_Items.dual_proton_fuel_rod.getInstance()), Character.valueOf('C'), IC2Items.getItem((String)"plate", (String)"copper"), Character.valueOf('Q'), IC2Items.getItem((String)"plate", (String)"iron"));
        SPPRecipes.addShapedColourRecipe(new ItemStack(SSP_Items.eit_proton_fuel_rod.getInstance()), " S ", "CQC", " S ", Character.valueOf('S'), new ItemStack(SSP_Items.quad_proton_fuel_rod.getInstance()), Character.valueOf('C'), IC2Items.getItem((String)"plate", (String)"copper"), Character.valueOf('Q'), IC2Items.getItem((String)"plate", (String)"iron"));
        SPPRecipes.addShapedColourRecipe(new ItemStack(SSP_Items.iridium.getInstance()), "QSQ", Character.valueOf('S'), IC2Items.getItem((String)"rotor_carbon"), Character.valueOf('Q'), IC2Items.getItem((String)"misc_resource", (String)"iridium_ore"));
        SPPRecipes.addShapedColourRecipe(new ItemStack(SSP_Items.compressiridium.getInstance()), " S ", "CQC", " S ", Character.valueOf('S'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.compresscarbon), Character.valueOf('C'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.quantumitems6), Character.valueOf('Q'), new ItemStack(SSP_Items.iridium.getInstance()));
        SPPRecipes.addShapedColourRecipe(new ItemStack(SSP_Items.spectral.getInstance()), " S ", "CQC", " S ", Character.valueOf('S'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.QuantumItems2), Character.valueOf('C'), SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.compresscarbonultra), Character.valueOf('Q'), new ItemStack(SSP_Items.compressiridium.getInstance()));
        SPPRecipes.addShapedColourRecipe(new ItemStack(SSP_Items.twelve_heat_storage.getInstance()), "TCT", "TGT", "TCT", Character.valueOf('C'), IC2Items.getItem((String)"hex_heat_storage"), Character.valueOf('G'), IC2Items.getItem((String)"plate", (String)"iron"), Character.valueOf('T'), IC2Items.getItem((String)"plate", (String)"tin"));
        SPPRecipes.addShapedColourRecipe(new ItemStack(SSP_Items.max_heat_storage.getInstance()), "TCT", "TGT", "TCT", Character.valueOf('C'), new ItemStack(SSP_Items.twelve_heat_storage.getInstance()), Character.valueOf('G'), IC2Items.getItem((String)"plate", (String)"iron"), Character.valueOf('T'), IC2Items.getItem((String)"plate", (String)"tin"));
    }

    private static void addcanerRecipe(IRecipeInput input, IRecipeInput input1, ItemStack output) {
        Recipes.cannerBottle.addRecipe(input, input1, output, false);
    }

    private static void addcentrifugeRecipe(IRecipeInput input, ItemStack output, ItemStack itemStack) {
        Recipes.centrifuge.addRecipe(input, null, false, new ItemStack[]{output, itemStack});
    }

    private static void addExtrudingRecipe(IRecipeInput input, ItemStack output) {
        Recipes.metalformerExtruding.addRecipe(input, null, false, new ItemStack[]{output});
    }

    private static void addShapedColourRecipe(ItemStack output, Object ... inputs) {
        ColourCarryingRecipe.addAndRegister((ItemStack)output, (Object[])inputs);
    }

    private static void addCompressorRecipe(IRecipeInput input, ItemStack output) {
        Recipes.compressor.addRecipe(input, (NBTTagCompound)null, false, new ItemStack[]{output});
    }

    public static void addShapedRecipe(ItemStack output, Object ... inputs) {
        Recipes.advRecipes.addRecipe(output, inputs);
    }

    public static void addShapelessRecipe(ItemStack output, Object ... inputs) {
        Recipes.advRecipes.addShapelessRecipe(output, inputs);
    }

    static void addMolecularTransformerRecipes() {
        SuperSolarPanels.log.info("Loading Molecular Transformer recipes from file");
        int successes = 0;
        for (MTRecipe recipe : Configs.MTRecipes) {
            try {
                if (!SPPRecipes.decodeLine(recipe.lineNumber, recipe.parts)) continue;
                ++successes;
            }
            catch (ParseException e) {
                SuperSolarPanels.log.warn("Skipping line " + recipe.lineNumber + " due to an error parsing", (Throwable)e);
            }
        }
        SuperSolarPanels.log.info("Load complete, successfully loaded {} out of {}.", (Object)successes, (Object)Configs.MTRecipes.length);
    }

    private static boolean decodeLine(int number, String[] parts) throws ParseException {
        int energy;
        IRecipeInput input = ConfigUtil.asRecipeInputWithAmount((String)parts[0].trim());
        if (input == null) {
            SuperSolarPanels.log.warn("Skipping line {} as the input ({}) cannot be resolved", (Object)number, (Object)parts[0].trim());
            return false;
        }
        ItemStack output = ConfigUtil.asStackWithAmount((String)parts[1].trim());
        if (output == null) {
            NonNullList nonNullList;
            String attempt = parts[1].trim();
            if (attempt.startsWith("OreDict:") && !(nonNullList = OreDictionary.getOres((String)attempt.substring(attempt.indexOf(58) + 1).trim())).isEmpty()) {
                output = (ItemStack)nonNullList.get(0);
                SuperSolarPanels.log.debug("Continued on line {} as the output ({}) could be resolved to {}", (Object)number, (Object)attempt, (Object)output);
            }
            if (output == null) {
                SuperSolarPanels.log.warn("Skipping line {} as the output ({}) cannot be resolved", (Object)number, (Object)attempt);
                return false;
            }
        }
        try {
            energy = Integer.parseInt(parts[2].trim());
        }
        catch (NumberFormatException e) {
            SuperSolarPanels.log.warn("Skipping line {} as the energy ({}) cannot be resolved to a number", (Object)number, (Object)parts[2].trim());
            return false;
        }
        if (!IMolecularTransformerRecipeManager.RECIPES.addRecipe(input, energy, output, false)) {
            SuperSolarPanels.log.warn("Skipping line {} as the recipe is a duplicate", (Object)number);
            return false;
        }
        return true;
    }
}

