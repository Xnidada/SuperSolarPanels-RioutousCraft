/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.api.event.TeBlockFinalCallEvent
 *  ic2.core.block.BlockTileEntity
 *  ic2.core.block.TeBlockRegistry
 *  ic2.core.util.ReflectionUtil
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.color.IItemColor
 *  net.minecraft.client.renderer.color.ItemColors
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.ForgeHooksClient
 *  net.minecraftforge.client.event.ColorHandlerEvent$Item
 *  net.minecraftforge.fluids.FluidRegistry
 *  net.minecraftforge.fml.client.registry.ClientRegistry
 *  net.minecraftforge.fml.common.IWorldGenerator
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.SidedProxy
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPostInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.registry.GameRegistry
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  net.minecraftforge.oredict.OreDictionary
 *  org.apache.logging.log4j.Logger
 */
package com.rioutouscraft.ssp;

import com.rioutouscraft.ssp.Configs;
import com.rioutouscraft.ssp.PrettyMolecularTransformerTESR;
import com.rioutouscraft.ssp.SPPRecipes;
import com.rioutouscraft.ssp.SSPSourceTab;
import com.rioutouscraft.ssp.SSPWorldDecorator;
import com.rioutouscraft.ssp.fluid.Neutron.BlockRegister;
import com.rioutouscraft.ssp.fluid.Neutron.FluidRegister;
import com.rioutouscraft.ssp.gui.ProgressBars;
import com.rioutouscraft.ssp.items.SSP_Items;
import com.rioutouscraft.ssp.items.resource.CraftingThings;
import com.rioutouscraft.ssp.keyboard.SSPKeys;
import com.rioutouscraft.ssp.proxy.CommonProxy;
import com.rioutouscraft.ssp.tiles.SSPBlock;
import com.rioutouscraft.ssp.tiles.TileEntityMolecularAssembler;
import ic2.api.event.TeBlockFinalCallEvent;
import ic2.core.block.BlockTileEntity;
import ic2.core.block.TeBlockRegistry;
import ic2.core.util.ReflectionUtil;
import java.lang.reflect.Field;
import java.util.Map;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber
@Mod(modid="super_solar_panels_rioutouscraft", name="SuperSolarPanels-RioutousCraft", dependencies="required-after:ic2@[2.8.222-ex112];", version="1.3.2-RioutousCraft", acceptedMinecraftVersions="[1.12.2]")
public final class SuperSolarPanels {
    public static boolean seasonal = false;
    public static final String MODID = "super_solar_panels_rioutouscraft";
    private static final String Urane = null;
    public static final CreativeTabs SSPtab = new SSPSourceTab("SSPtab");
    public static Logger log;
    public static BlockTileEntity machines;
    @SidedProxy(clientSide="com.rioutouscraft.ssp.proxy.ClientProxy", serverSide="com.rioutouscraft.ssp.proxy.CommonProxy")
    public static CommonProxy proxy;

    @SubscribeEvent
    public static void register(TeBlockFinalCallEvent event) {
        TeBlockRegistry.addAll(SSPBlock.class, (ResourceLocation)SSPBlock.IDENTITY);
        TeBlockRegistry.setDefaultMaterial((ResourceLocation)SSPBlock.IDENTITY, (Material)Material.ROCK);
    }

    @Mod.EventHandler
    public void load(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        log = event.getModLog();
        Configs.loadConfig(event.getSuggestedConfigurationFile(), event.getSide().isClient());
        machines = TeBlockRegistry.get((ResourceLocation)SSPBlock.IDENTITY);
        machines.setCreativeTab(SSPtab);
        if (event.getSide().isClient()) {
            SuperSolarPanels.setupRenderingGuf();
        }
        SSP_Items.buildItems(event.getSide());
        FluidRegister.register();
        BlockRegister.register();
        SSPKeys.addFlyKey();
        SPPRecipes.addMolecularTransformerRecipes();
        GameRegistry.registerWorldGenerator((IWorldGenerator)new SSPWorldDecorator(), (int)0);
        OreDictionary.registerOre((String)"enderquantumcomponent", (ItemStack)SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.enderquantumcomponent));
        OreDictionary.registerOre((String)"solarsplitter", (ItemStack)SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.solarsplitter));
        OreDictionary.registerOre((String)"bluecomponent", (ItemStack)SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.bluecomponent));
        OreDictionary.registerOre((String)"greencomponent", (ItemStack)SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.greencomponent));
        OreDictionary.registerOre((String)"redcomponent", (ItemStack)SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.redcomponent));
        OreDictionary.registerOre((String)"singularcore", (ItemStack)SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.singularcore));
        OreDictionary.registerOre((String)"photoniy", (ItemStack)SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.photoniy));
        OreDictionary.registerOre((String)"photoniy_ingot", (ItemStack)SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.photoniy_ingot));
        OreDictionary.registerOre((String)"spectralcore", (ItemStack)SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.spectralcore));
        OreDictionary.registerOre((String)"ingotUranium", (ItemStack)SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.URANIUM_INGOT));
        OreDictionary.registerOre((String)"ingotIridium", (ItemStack)SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.IRIDIUM_INGOT));
        OreDictionary.registerOre((String)"craftingSunnariumPart", (ItemStack)SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.SUNNARIUM_PART));
        OreDictionary.registerOre((String)"craftingSunnarium", (ItemStack)SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.SUNNARIUM));
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        SSPBlock.buildDummies();
        SPPRecipes.addCraftingRecipes();
        ProgressBars.addStyles();
        TileEntityMolecularAssembler.MolecularOutput.registerNetwork();
    }

    @SideOnly(value=Side.CLIENT)
    private static void setupRenderingGuf() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMolecularAssembler.class, (TileEntitySpecialRenderer)new PrettyMolecularTransformerTESR());
        ForgeHooksClient.registerTESRItemStack((Item)machines.getItem(), (int)SSPBlock.molecular_transformer.getId(), SSPBlock.molecular_transformer.getTeClass());
    }

    @SubscribeEvent
    @SideOnly(value=Side.CLIENT)
    public static void doColourThings(ColorHandlerEvent.Item event) {
        ItemColors colours = event.getItemColors();
        IItemColor armourColouring = (IItemColor)((Map)ReflectionUtil.getFieldValue((Field)ReflectionUtil.getField(ItemColors.class, Map.class), (Object)colours)).get(Items.LEATHER_BOOTS.delegate);
        colours.registerItemColorHandler(armourColouring, new Item[]{SSP_Items.Spectral_SOLAR_HELMET.getInstance()});
        colours.registerItemColorHandler(armourColouring, new Item[]{SSP_Items.Singular_SOLAR_HELMET.getInstance()});
        colours.registerItemColorHandler(armourColouring, new Item[]{SSP_Items.HYBRID_SOLAR_HELMET.getInstance(), SSP_Items.ULTIMATE_HYBRID_SOLAR_HELMET.getInstance()});
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    public static ResourceLocation getIdentifier(String name) {
        return new ResourceLocation(MODID, name);
    }

    static {
        FluidRegistry.enableUniversalBucket();
    }
}

