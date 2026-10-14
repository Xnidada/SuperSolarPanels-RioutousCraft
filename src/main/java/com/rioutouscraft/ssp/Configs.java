/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.config.Configuration
 *  org.apache.commons.io.IOUtils
 */
package com.rioutouscraft.ssp;

import com.rioutouscraft.ssp.MTRecipe;
import com.rioutouscraft.ssp.MolecularTransformerRecipeManager;
import com.rioutouscraft.ssp.SuperSolarPanels;
import com.rioutouscraft.ssp.items.battery.ItemBattery;
import com.rioutouscraft.ssp.tiles.Moonpanel.TileEntityAdminmoon;
import com.rioutouscraft.ssp.tiles.Moonpanel.TileEntityAdvancedSolarmoon;
import com.rioutouscraft.ssp.tiles.Moonpanel.TileEntityHybridSolarmoon;
import com.rioutouscraft.ssp.tiles.Moonpanel.TileEntityQuantumSolarmoon;
import com.rioutouscraft.ssp.tiles.Moonpanel.TileEntitySingularmoon;
import com.rioutouscraft.ssp.tiles.Moonpanel.TileEntitySpectralmoon;
import com.rioutouscraft.ssp.tiles.Moonpanel.TileEntityUltimateHybridSolarmoon;
import com.rioutouscraft.ssp.tiles.Moonpanel.TileEntityneutronmoon;
import com.rioutouscraft.ssp.tiles.Moonpanel.TileEntityphotonicmoon;
import com.rioutouscraft.ssp.tiles.Moonpanel.TileEntityprotonmoon;
import com.rioutouscraft.ssp.tiles.Sunpanel.TileEntityAdminsun;
import com.rioutouscraft.ssp.tiles.Sunpanel.TileEntityAdvancedSolarsun;
import com.rioutouscraft.ssp.tiles.Sunpanel.TileEntityHybridSolarsun;
import com.rioutouscraft.ssp.tiles.Sunpanel.TileEntityQuantumSolarsun;
import com.rioutouscraft.ssp.tiles.Sunpanel.TileEntitySingularsun;
import com.rioutouscraft.ssp.tiles.Sunpanel.TileEntitySpectralsun;
import com.rioutouscraft.ssp.tiles.Sunpanel.TileEntityUltimateHybridSolarsun;
import com.rioutouscraft.ssp.tiles.Sunpanel.TileEntityneutronsun;
import com.rioutouscraft.ssp.tiles.Sunpanel.TileEntityphotonicsun;
import com.rioutouscraft.ssp.tiles.Sunpanel.TileEntityprotonsun;
import com.rioutouscraft.ssp.tiles.TileEntityMoonPanel;
import com.rioutouscraft.ssp.tiles.TileEntityRainPanel;
import com.rioutouscraft.ssp.tiles.TileEntitySolarPanel;
import com.rioutouscraft.ssp.tiles.TileEntitySolarPanelsun;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityAdmin;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityAdvancedSolar;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityBarion;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityGraviton;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityHybridSolar;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityHadron;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityQuantumSolar;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityQuark;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntitySingular;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntitySpectral;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityUltimateHybridSolar;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityneutron;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityphotonic;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityproton;
import com.rioutouscraft.ssp.tiles.rainpanels.TileEntityAdminrain;
import com.rioutouscraft.ssp.tiles.rainpanels.TileEntityAdvancedSolarrain;
import com.rioutouscraft.ssp.tiles.rainpanels.TileEntityHybridSolarrain;
import com.rioutouscraft.ssp.tiles.rainpanels.TileEntityQuantumSolarrain;
import com.rioutouscraft.ssp.tiles.rainpanels.TileEntitySingularrain;
import com.rioutouscraft.ssp.tiles.rainpanels.TileEntitySpectralrain;
import com.rioutouscraft.ssp.tiles.rainpanels.TileEntityUltimateHybridSolarrain;
import com.rioutouscraft.ssp.tiles.rainpanels.TileEntityneutronrain;
import com.rioutouscraft.ssp.tiles.rainpanels.TileEntityphotonicrain;
import com.rioutouscraft.ssp.tiles.rainpanels.TileEntityprotonrain;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.text.ParseException;
import java.util.ArrayList;
import net.minecraftforge.common.config.Configuration;
import org.apache.commons.io.IOUtils;

public final class Configs {
    private static final String GENERAL = "general";
    private static final String SOLARS = "solars";
    private static final String QUANTUM_GENERATOR = "quantum generator";
    private static final String CRAFTING = "recipes settings";
    static boolean hardRecipes;
    static boolean easyASPRecipe;
    public static boolean canCraftDoubleSlabs;
    public static boolean canCraftMT;
    public static boolean canCraftASP;
    public static boolean canCraftHSP;
    public static boolean canCraftUHSP;
    public static boolean canCraftQSP;
    public static boolean canCraftASH;
    public static boolean canCraftHSH;
    public static boolean canCraftUHSH;
    public static int damage1;
    public static int damage2;
    public static int damage3;
    public static int damage4;
    public static int maxCharge1;
    public static int maxCharge2;
    public static int transferLimit1;
    public static int transferLimit2;
    public static int tier1;
    public static int tier2;
    private static final String NEW_LINE;
    private static final String CONFIG_VERSION = "2.0";
    public static ItemBattery settings;
    public static ItemBattery settings1;
    public static int maxCharge3;
    public static int maxCharge4;
    public static int maxCharge5;
    public static int maxCharge6;
    public static int transferLimit3;
    public static int transferLimit4;
    public static int transferLimit5;
    public static int transferLimit6;
    public static int tier3;
    public static int tier4;
    public static int tier5;
    public static int tier6;
    public static int maxCharge7;
    public static int transferLimit8;
    public static int tier8;
    public static int maxCharge8;
    public static int twelve_heat_storage;
    public static int max_heat_storage;
    public static int maxCharge9;
    public static int maxCharge10;
    public static int maxCharge11;
    public static int transferLimit9;
    public static int transferLimit10;
    public static int transferLimit11;
    public static int proton_fuel_rod;
    public static int rotor_carbon1;
    public static int rotor_carbon2;
    public static int rotor_carbon3;
    public static int coefficient;
    public static int coefficient1;
    public static int coefficient2;
    public static int operationEnergyCost;
    public static int maxChargedrill;
    public static int transferLimitdrill;
    public static int tierdrill;
    public static int drillSpeed;
    public static int drillSpeed1;
    public static int drillSpeed2;
    public static int energyCost;
    public static int energyCost1;
    public static int energyCost2;
    public static int maxWindStrength;
    public static int maxWindStrength1;
    public static int maxWindStrength2;
    public static int minWindStrength;
    public static int minWindStrength1;
    public static int minWindStrength2;
    public static int Radius;
    public static int Radius1;
    public static int Radius2;
    public static int Neutronfabricator;
    public static int Neutronfabricator1;
    public static int advancedmfsu;
    public static int advancedmfsu1;
    public static int advancedmfsu2;
    public static int ultimatemfsu;
    public static int ultimatemfsu1;
    public static int ultimatemfsu2;
    public static int quantummfsu;
    public static int quantummfsu1;
    public static int quantummfsu2;
    static MTRecipe[] MTRecipes;

    static void loadConfig(File config, boolean client) {
        SuperSolarPanels.log.info("Loading SSP Config from " + config.getAbsolutePath());
        Configs.loadNormalConfig(config, client);
        try {
            Configs.loadMolecularTransformerConfig(config.getParentFile(), config.getName());
        }
        catch (ParseException e) {
            MolecularTransformerRecipeManager.showError("Error reading Molecular Transformer recipes file:" + NEW_LINE + e.toString());
        }
    }

    private static void loadNormalConfig(File configFile, boolean client) {
        Configuration config = new Configuration(configFile);
        try {
            config.load();
            TileEntityAdvancedSolar.settings = new TileEntitySolarPanel.SolarConfig(getDouble(config, "Advanced solar panels", "AdvancedSPGenDay", 5D), getDouble(config, "Advanced solar panels", "AdvancedSPGenNight", 2.5D), getDouble(config, "Advanced solar panels", "AdvancedSPStorage", 3200D), 1, getDouble(config, "Advanced solar panels", "AdvancedSPOutput", 10D));
            TileEntityHybridSolar.settings = new TileEntitySolarPanel.SolarConfig(getDouble(config, "Hybrid solar panels", "HybrydSPGenDay", 20D), getDouble(config, "Hybrid solar panels", "HybrydSPGenNight", 10D), getDouble(config, "Hybrid solar panels", "HybrydSPStorage", 20000D), 2, getDouble(config, "Hybrid solar panels", "HybrydSPOutput", 40D));
            TileEntityUltimateHybridSolar.settings = new TileEntitySolarPanel.SolarConfig(getDouble(config, "Ultimate solar panels", "UltimateHSPGenDay", 80D), getDouble(config, "Ultimate solar panels", "UltimateHSPGenNight", 40D), getDouble(config, "Ultimate solar panels", "UltimateHSPStorage", 200000D), 3, getDouble(config, "Ultimate solar panels", "UltimateHSPOutput", 160D));
            TileEntityQuantumSolar.settings = new TileEntitySolarPanel.SolarConfig(getDouble(config, "Quantum solar panels", "QuantumSPGenDay", 320D), getDouble(config, "Quantum solar panels", "QuantumSPGenNight", 160D), getDouble(config, "Quantum solar panels", "QuantumSPStorage", 1000000D), 4, getDouble(config, "Quantum solar panels", "QuantumSPOutput", 640D));
            TileEntitySpectral.settings = new TileEntitySolarPanel.SolarConfig(getDouble(config, "Spectral solar panels", "SpecrtalGenDay", 1280D), getDouble(config, "Spectral solar panels", "SpecrtalGenNight", 640D), getDouble(config, "Spectral solar panels", "SpecrtalStorage", 5000000D), 5, getDouble(config, "Spectral solar panels", "SpecrtalOutput", 2560D));
            TileEntityproton.settings = new TileEntitySolarPanel.SolarConfig(getDouble(config, "Proton solar panels", "ProtonGenDay", 5120D), getDouble(config, "Proton solar panels", "ProtonGenNight", 2560D), getDouble(config, "Proton solar panels", "ProtonStorage", 50000000D), 6, getDouble(config, "Proton solar panels", "ProtonOutput", 10240D));
            TileEntitySingular.settings = new TileEntitySolarPanel.SolarConfig(getDouble(config, "Singular solar panels", "SingularGenDay", 20480D), getDouble(config, "Singular solar panels", "SingularGenNight", 10240D), getDouble(config, "Singular solar panels", "SingularStorage", 1000000000D), 7, getDouble(config, "Singular solar panels", "SingularOutput", 40960D));
            TileEntityAdmin.settings = new TileEntitySolarPanel.SolarConfig(getDouble(config, "Diffraction solar panels", "DiffractionGenDay", 81920D), getDouble(config, "Diffraction solar panels", "DiffractionGenNight", 40960D), getDouble(config, "Diffraction solar panels", "DiffractionStorage", 1500000000D), 8, getDouble(config, "Diffraction solar panels", "DiffractionOutput", 163840D));
            TileEntityphotonic.settings = new TileEntitySolarPanel.SolarConfig(getDouble(config, "Photonic solar panels", "PhotonicGenDay", 327680D), getDouble(config, "Photonic solar panels", "PhotonicGenNight", 163840D), getDouble(config, "Photonic solar panels", "PhotonicStorage", 5000000000D), 9, getDouble(config, "Photonic solar panels", "PhotonicOutput", 655360D));
            TileEntityneutron.settings = new TileEntitySolarPanel.SolarConfig(getDouble(config, "Neutron solar panels", "NeutronGenDay", 1310720D), getDouble(config, "Neutron solar panels", "NeutronGenNight", 655360D), getDouble(config, "Neutron solar panels", "NeutronStorage", 6500000000D), 10, getDouble(config, "Neutron solar panels", "NeutronOutput", 2621440D));
            TileEntityBarion.settings = new TileEntitySolarPanel.SolarConfig(getDouble(config, "Barion solar panels", "BarionGenDay", 5242880D), getDouble(config, "Barion solar panels", "BarionGenNight", 2621440D), getDouble(config, "Barion solar panels", "BarionStorage", 10000000000D), 11, getDouble(config, "Barion solar panels", "BarionOutput", 10485760D));
            TileEntityHadron.settings = new TileEntitySolarPanel.SolarConfig(getDouble(config, "Hadron solar panels", "HadronGenDay", 20971520D), getDouble(config, "Hadron solar panels", "HadronGenNight", 10485760D), getDouble(config, "Hadron solar panels", "HadronStorage", 25000000000D), 12, getDouble(config, "Hadron solar panels", "HadronOutput", 41943040D));
            TileEntityGraviton.settings = new TileEntitySolarPanel.SolarConfig(getDouble(config, "Graviton solar panels", "GravitonGenDay", 83886080D), getDouble(config, "Graviton solar panels", "GravitonGenNight", 41943040D), getDouble(config, "Graviton solar panels", "GravitonStorage", 250000000000D), 13, getDouble(config, "Graviton solar panels", "GravitonOutput", 167772160D));
            TileEntityQuark.settings = new TileEntitySolarPanel.SolarConfig(getDouble(config, "Quark solar panels", "QuarkGenDay", 335544320D), getDouble(config, "Quark solar panels", "QuarkGenNight", 167772160D), getDouble(config, "Quark solar panels", "QuarkStorage", 2500000000000D), 14, getDouble(config, "Quark solar panels", "QuarkOutput", 671088640D));
            damage1 = config.get("Settings quantum saber", "Damage quantum saber(not right click)+1 ", 11).getInt(11);
            damage2 = config.get("Settings quantum saber", "Damage quantum saber(right click)+1", 29).getInt(29);
            damage3 = config.get("Settings spectral saber", "Damage quantum saber(not right click)+1 ", 14).getInt(14);
            damage4 = config.get("Settings spectral saber", "Damage quantum saber(right click)+1 ", 39).getInt(39);
            maxCharge1 = config.get("Settings quantum saber", "maxCharge", 300000).getInt(300000);
            maxCharge2 = config.get("Settings spectral saber", "maxCharge", 600000).getInt(600000);
            transferLimit1 = config.get("Settings quantum saber", "transferLimit", 2000).getInt(2000);
            transferLimit2 = config.get("Settings spectral saber", "transferLimit ", 2000).getInt(2000);
            tier1 = config.get("Settings quantum saber", "tier ", 4).getInt(4);
            tier2 = config.get("Settings spectral saber", "tier", 5).getInt(5);
            maxCharge3 = config.get("Settings Quantum Boosts", "maxCharge", 100000000).getInt(100000000);
            maxCharge4 = config.get("Settings Quantum Helmet", "maxCharge", 100000000).getInt(100000000);
            maxCharge5 = config.get("Settings Quantum Leggings", "maxCharge", 100000000).getInt(100000000);
            maxCharge6 = config.get("Settings Quantum Chestplate", "maxCharge ", 100000000).getInt(100000000);
            maxCharge1 = config.get("Settings quantum saber", "maxCharge", 300000).getInt(300000);
            maxCharge2 = config.get("Settings spectral saber", "maxCharge", 600000).getInt(600000);
            transferLimit3 = config.get("Settings Quantum Boosts", "transferLimit", 100000).getInt(100000);
            transferLimit4 = config.get("Settings Quantum Helmet", "transferLimit ", 100000).getInt(100000);
            transferLimit5 = config.get("Settings Quantum Leggings", "transferLimit ", 100000).getInt(100000);
            transferLimit6 = config.get("Settings Quantum Chestplater", "transferLimit", 100000).getInt(100000);
            canCraftDoubleSlabs = !config.get("settings Quantum chestplate", "Disable Effect FIRE RESISTANCE ", false).getBoolean(false);
            canCraftMT = !config.get("settings Quantum Boosts", "Disable Effect WATER BREATHING ", false).getBoolean(false);
            canCraftASP = !config.get("settings Quantum Boosts", "Disable Effect JUMP BOOST", false).getBoolean(false);
            canCraftASH = !config.get("settings Quantum Boosts", "Disable Effect REGENERATION ", false).getBoolean(false);
            canCraftHSP = !config.get("settings Quantum Leggins", "Disable Effect SPEED ", false).getBoolean(false);
            canCraftHSH = !config.get("settings Quantum Leggins", "Disable Effect LUCK", false).getBoolean(false);
            tier3 = config.get("Settings Quantum Boosts", "tier", 8).getInt(8);
            tier4 = config.get("Settings Quantum Helmet", "tier ", 8).getInt(8);
            tier5 = config.get("Settings Quantum Leggings", "tier ", 8).getInt(8);
            tier6 = config.get("Settings Quantum Chestplater", "tier", 8).getInt(8);
            maxCharge8 = config.get("Settings Spectral Battery", "maxCharge", 100000000).getInt(100000000);
            twelve_heat_storage = config.get("Settings twelve heat storage", "heatStorage", 120000).getInt(120000);
            max_heat_storage = config.get("Settings max heat storage", "heatStorage", 240000).getInt(240000);
            proton_fuel_rod = config.get("Settings proton fuel rods", "maxDamage(times, 1=1 seconds) ", 30000).getInt(30000);
            maxCharge10 = config.get("Settings Advanced Solar Nano Helmet", "maxCharge", 300000).getInt(300000);
            maxCharge11 = config.get("Settings Ultimate and Hybrid Solar Quantum Helmet", "maxCharge", 600000).getInt(600000);
            transferLimit8 = config.get("Settings Spectral Battery", "transferLimit", 30000).getInt(30000);
            coefficient = config.get("Settings Iridium rotor", "efficiency ", 2.0).getInt(2);
            coefficient1 = config.get("Settings Compress iridium rotor", "efficiency ", 3.0).getInt(3);
            coefficient2 = config.get("Settings Spectral rotor", "efficiency", 5.0).getInt(5);
            tier8 = config.get("Settings Spectral Battery", "tier", 4).getInt(4);
            rotor_carbon1 = config.get("Settings Iridium rotor", "durability(times, 1=1 seconds) ", 648000).getInt(648000);
            rotor_carbon2 = config.get("Settings Compress iridium rotor", " durability(times, 1=1 seconds)", 720000).getInt(720000);
            rotor_carbon3 = config.get("Settings Spectral rotor", "durability(times, 1=1 seconds)", 720000).getInt(720000);
            maxWindStrength = config.get("Settings Iridium rotor", "maxWindStrength", 110).getInt(110);
            maxWindStrength1 = config.get("Settings Compress iridium rotor", "maxWindStrength", 110).getInt(110);
            maxWindStrength2 = config.get("Settings Spectral rotor", "maxWindStrength", 110).getInt(110);
            minWindStrength = config.get("Settings Iridium rotor", "minWindStrength", 25).getInt(25);
            minWindStrength1 = config.get("Settings Compress iridium rotor", "minWindStrength", 25).getInt(25);
            minWindStrength2 = config.get("Settings Spectral rotor", "minWindStrength", 25).getInt(25);
            Radius = config.get("Settings Iridium rotor", "Radius", 11).getInt(11);
            Radius1 = config.get("Settings Compress iridium rotor", "Radius", 11).getInt(11);
            Radius2 = config.get("Settings Spectral rotor", "Radius", 11).getInt(11);
            advancedmfsu = config.get("Settings Advanced mfsu", "output", 16384).getInt(16384);
            advancedmfsu1 = config.get("Settings Advanced mfsu", "tier ", 5).getInt(5);
            advancedmfsu2 = config.get("Settings Advanced mfsu", "maxCharge ", 100000000).getInt(100000000);
            ultimatemfsu = config.get("Settings Ultimate mfsu", "output", 65536).getInt(65536);
            ultimatemfsu1 = config.get("Settings Ultimate mfsu", "tier", 6).getInt(6);
            ultimatemfsu2 = config.get("Settings Ultimate mfsu", "maxCharge ", 400000000).getInt(400000000);
            quantummfsu = config.get("Settings Quantum mfsu", "output ", 262144).getInt(262144);
            quantummfsu1 = config.get("Settings Quantum mfsu", "tier", 7).getInt(7);
            quantummfsu2 = config.get("Settings Quantum mfsu", "maxCharge", 1600000000).getInt(1600000000);
            operationEnergyCost = config.get("Settings Spectral Drill", "tier", 8).getInt(8);
            maxChargedrill = config.get("Settings Spectral Drill", "tier ", 8).getInt(8);
            transferLimitdrill = config.get("Settings Spectral Drill", "tier ", 8).getInt(8);
            tierdrill = config.get("Settings Spectral Drill", "tier", 8).getInt(8);
            Neutronfabricator = config.get("Settings Neutron fabricator", "1 mb = 15625000.0F (default)", 1.5625E7).getInt(15625000);
            Neutronfabricator1 = config.get("Settings Neutron fabricator", "tier", 14).getInt(14);
            drillSpeed = config.get("Settings Spectral Drill(Normal Mode)", "drillSpeed", 35.0).getInt(35);
            drillSpeed1 = config.get("Settings Spectral Drill(BIG_HOLES(3x3) Mode)", "drillSpeed", 12.0).getInt(12);
            drillSpeed2 = config.get("Settings Spectral Drill(BIG_HOLES(5x5) Mode)", "drillSpeed ", 16.0).getInt(16);
            energyCost = config.get("Settings Spectral Drill(Normal Mode)", "energyCost", 160.0).getInt(160);
            energyCost1 = config.get("Settings Spectral Drill(BIG_HOLES(3x3) Mode)", "energyCost", 160.0).getInt(160);
            energyCost2 = config.get("Settings Spectral Drill(BIG_HOLES(5x5) Mode)", "energyCost", 160.0).getInt(160);
            TileEntityneutronsun.settings = new TileEntitySolarPanelsun.SolarConfig(config.get("Neutron solar panels sun", "NeutronGenNight", 0xF00000).getInt(0xF00000), config.get("Neutron solar panels sun", "NeutronStorage", 999999999).getInt(999999999), config.get("Neutron solar panels sun", "NeutronTier", 10).getInt(10));
            TileEntityprotonsun.settings = new TileEntitySolarPanelsun.SolarConfig(config.get("Proton solar panels sun", "ProtonGenNight", 49152).getInt(49152), config.get("Proton solar panels sun", "ProtonStorage", 999999999).getInt(999999999), config.get("Proton solar panels sun", "ProtonTier", 6).getInt(6));
            TileEntityAdvancedSolarsun.settings = new TileEntitySolarPanelsun.SolarConfig(config.get("Advanced solar panels sun", "AdvancedSPGenNight", 6).getInt(6), config.get("Advanced solar panels sun", "AdvancedSPStorage", 32000).getInt(32000), config.get("Advanced solar panels sun", "AdvancedSPTier", 1).getInt(1));
            TileEntityHybridSolarsun.settings = new TileEntitySolarPanelsun.SolarConfig(config.get("Hybrid solar panels sun", "HybrydSPGenNight", 48).getInt(48), config.get("Hybrid solar panels sun", "HybrydSPStorage", 100000).getInt(100000), config.get("Hybrid solar panels sun", "HybrydSPTier", 2).getInt(2));
            TileEntityUltimateHybridSolarsun.settings = new TileEntitySolarPanelsun.SolarConfig(config.get("Ultimate solar panels sun", "UltimateHSPGenNight", 384).getInt(384), config.get("Ultimate solar panels sun", "UltimateHSPStorage", 1000000).getInt(1000000), config.get("Ultimate solar panels sun", "UltimateHSPTier", 3).getInt(3));
            TileEntityQuantumSolarsun.settings = new TileEntitySolarPanelsun.SolarConfig(config.get("Quantum solar panels sun", "QuantumSPGenNight", 3072).getInt(3072), config.get("Quantum solar panels sun", "QuantumSPStorage", 10000000).getInt(10000000), config.get("Quantum solar panels sun", "QuantumSPTier", 4).getInt(4));
            TileEntitySpectralsun.settings = new TileEntitySolarPanelsun.SolarConfig(config.get("Spectral solar panels sun", "SpecrtalGenNight", 12288).getInt(12288), config.get("Spectral solar panels sun", "SpecrtalStorage", 100000000).getInt(100000000), config.get("Spectral solar panels sun", "SpecrtalTier", 5).getInt(5));
            TileEntitySingularsun.settings = new TileEntitySolarPanelsun.SolarConfig(config.get("Singular solar panels sun", "SingularGenNight", 196608).getInt(196608), config.get("Singular solar panels sun", "SingularStorage", 1000000000).getInt(100000), config.get("Singular solar panels sun", "SingularTier", 7).getInt(7));
            TileEntityAdminsun.settings = new TileEntitySolarPanelsun.SolarConfig(config.get("Light-Absord solar panels sun", "AdminGenNight", 831432).getInt(831432), config.get("Light-Absord solar panels sun", "AdminStorage", 1000000000).getInt(1000000), config.get("Light-Absord solar panels sun", "AdminPTier", 8).getInt(8));
            TileEntityphotonicsun.settings = new TileEntitySolarPanelsun.SolarConfig(config.get("Photonic solar panels sun", "PhotonicGenNight", 0x3C0000).getInt(0x3C0000), config.get("Photonic solar panels sun", "PhotonicStorage", 999999999).getInt(999999999), config.get("Photonic solar panels sun", "PhotonicTier", 9).getInt(9));
            TileEntityneutronmoon.settings = new TileEntityMoonPanel.SolarConfig(config.get("solarsmoon", "NeutronGenNight", 0xF00000).getInt(0xF00000), config.get(SOLARS, "NeutronStorage", 999999999).getInt(999999999), config.get(SOLARS, "NeutronTier", 9).getInt(9));
            TileEntityprotonmoon.settings = new TileEntityMoonPanel.SolarConfig(config.get("solarsmoon", "ProtonGenNight", 49152).getInt(49152), config.get(SOLARS, "ProtonStorage", 999999999).getInt(999999999), config.get(SOLARS, "ProtonTier", 6).getInt(6));
            TileEntityAdvancedSolarmoon.settings = new TileEntityMoonPanel.SolarConfig(config.get("solarsmoon", "AdvancedSPGenNight", 6).getInt(6), config.get(SOLARS, "AdvancedSPStorage", 32000).getInt(32000), config.get(SOLARS, "AdvancedSPTier", 1).getInt(1));
            TileEntityHybridSolarmoon.settings = new TileEntityMoonPanel.SolarConfig(config.get("solarsmoon", "HybrydSPGenNight", 48).getInt(48), config.get(SOLARS, "HybrydSPStorage", 100000).getInt(100000), config.get(SOLARS, "HybrydSPTier", 2).getInt(2));
            TileEntityUltimateHybridSolarmoon.settings = new TileEntityMoonPanel.SolarConfig(config.get("solarsmoon", "UltimateHSPGenNight", 384).getInt(384), config.get(SOLARS, "UltimateHSPStorage", 1000000).getInt(1000000), config.get(SOLARS, "UltimateHSPTier", 3).getInt(3));
            TileEntityQuantumSolarmoon.settings = new TileEntityMoonPanel.SolarConfig(config.get("solarsmoon", "QuantumSPGenNight", 3072).getInt(3072), config.get(SOLARS, "QuantumSPStorage", 10000000).getInt(10000000), config.get(SOLARS, "QuantumSPTier", 4).getInt(4));
            TileEntitySpectralmoon.settings = new TileEntityMoonPanel.SolarConfig(config.get("solarsmoon", "SpecrtalGenNight", 12288).getInt(12288), config.get(SOLARS, "SpecrtalStorage", 100000000).getInt(100000000), config.get(SOLARS, "SpecrtalTier", 5).getInt(5));
            TileEntitySingularmoon.settings = new TileEntityMoonPanel.SolarConfig(config.get("solarsmoon", "SingularGenNight", 196608).getInt(196608), config.get(SOLARS, "SingularStorage", 1000000000).getInt(100000), config.get(SOLARS, "SingularTier", 7).getInt(7));
            TileEntityAdminmoon.settings = new TileEntityMoonPanel.SolarConfig(config.get("solarsmoon", "AdminGenNight", 831432).getInt(831432), config.get(SOLARS, "AdminStorage", 1000000000).getInt(1000000), config.get(SOLARS, "AdminPTier", 8).getInt(8));
            TileEntityphotonicmoon.settings = new TileEntityMoonPanel.SolarConfig(config.get("solarsmoon", "PhotonicGenNight", 0x3C0000).getInt(0x3C0000), config.get(SOLARS, "PhotonicStorage", 999999999).getInt(999999999), config.get(SOLARS, "PhotonicTier", 9).getInt(9));
            TileEntityneutronrain.settings = new TileEntityRainPanel.SolarConfig(config.get("solarsrain", "NeutronGenNight", 0xF00000).getInt(0xF00000), config.get(SOLARS, "NeutronStorage", 999999999).getInt(999999999), config.get(SOLARS, "NeutronTier", 9).getInt(9));
            TileEntityprotonrain.settings = new TileEntityRainPanel.SolarConfig(config.get("solarsrain", "ProtonGenNight", 49152).getInt(49152), config.get(SOLARS, "ProtonStorage", 999999999).getInt(999999999), config.get(SOLARS, "ProtonTier", 6).getInt(6));
            TileEntityAdvancedSolarrain.settings = new TileEntityRainPanel.SolarConfig(config.get("solarsrain", "AdvancedSPGenNight", 6).getInt(6), config.get(SOLARS, "AdvancedSPStorage", 32000).getInt(32000), config.get(SOLARS, "AdvancedSPTier", 1).getInt(1));
            TileEntityHybridSolarrain.settings = new TileEntityRainPanel.SolarConfig(config.get("solarsrain", "HybrydSPGenNight", 48).getInt(48), config.get(SOLARS, "HybrydSPStorage", 100000).getInt(100000), config.get(SOLARS, "HybrydSPTier", 2).getInt(2));
            TileEntityUltimateHybridSolarrain.settings = new TileEntityRainPanel.SolarConfig(config.get("solarsrain", "UltimateHSPGenNight", 384).getInt(384), config.get(SOLARS, "UltimateHSPStorage", 1000000).getInt(1000000), config.get(SOLARS, "UltimateHSPTier", 3).getInt(3));
            TileEntityQuantumSolarrain.settings = new TileEntityRainPanel.SolarConfig(config.get("solarsrain", "QuantumSPGenNight", 3072).getInt(3072), config.get(SOLARS, "QuantumSPStorage", 10000000).getInt(10000000), config.get(SOLARS, "QuantumSPTier", 4).getInt(4));
            TileEntitySpectralrain.settings = new TileEntityRainPanel.SolarConfig(config.get("solarsrain", "SpecrtalGenNight", 12288).getInt(12288), config.get(SOLARS, "SpecrtalStorage", 100000000).getInt(100000000), config.get(SOLARS, "SpecrtalTier", 5).getInt(5));
            TileEntitySingularrain.settings = new TileEntityRainPanel.SolarConfig(config.get("solarsrain", "SingularGenNight", 196608).getInt(196608), config.get(SOLARS, "SingularStorage", 1000000000).getInt(100000), config.get(SOLARS, "SingularTier", 7).getInt(7));
            TileEntityAdminrain.settings = new TileEntityRainPanel.SolarConfig(config.get("solarsrain", "AdminGenNight", 831432).getInt(831432), config.get(SOLARS, "AdminStorage", 1000000000).getInt(1000000), config.get(SOLARS, "AdminPTier", 8).getInt(8));
            TileEntityphotonicrain.settings = new TileEntityRainPanel.SolarConfig(config.get("solarsrain", "PhotonicGenNight", 0x3C0000).getInt(0x3C0000), config.get(SOLARS, "PhotonicStorage", 999999999).getInt(999999999), config.get(SOLARS, "PhotonicTier", 9).getInt(9));
        }
        catch (Exception e) {
            SuperSolarPanels.log.fatal("Fatal error reading config file.", (Throwable)e);
            throw new RuntimeException(e);
        }
        finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }

    private static int getInt(Configuration config, String category, String key, int defaultValue) {
        return config.get(category, key, defaultValue).getInt(defaultValue);
    }

    private static double getDouble(Configuration config, String category, String key, double defaultValue) {
        return config.get(category, key, defaultValue).getDouble(defaultValue);
    }

    private static void loadMolecularTransformerConfig(File configFolder, String configFile) throws ParseException {
        int fileExtensionMarker = configFile.lastIndexOf(46);
        File config = new File(configFolder, configFile.substring(0, fileExtensionMarker) + "_MTRecipes" + configFile.substring(fileExtensionMarker));
        SuperSolarPanels.log.info("Loading MT Recipes from " + config.getAbsolutePath());
        if (!config.exists()) {
            Configs.fillDefault(config);
        }
        FileInputStream stream = null;
        BufferedReader reader = null;
        ArrayList<MTRecipe> recipes = new ArrayList<MTRecipe>(20);
        try {
            String line;
            stream = new FileInputStream(config);
            reader = new BufferedReader(new InputStreamReader(stream));
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                ++lineNumber;
                if ((line = line.trim()).startsWith("#") || line.isEmpty()) continue;
                if (line.startsWith("version=")) {
                    String version = line.substring(line.indexOf(61) + 1);
                    if (CONFIG_VERSION.equals(version)) continue;
                    throw new ParseException("Advanced Solars expected a file version of 2.0, but the config is " + version, line.indexOf(61) + 1);
                }
                MTRecipe recipe = new MTRecipe(lineNumber, line);
                if (recipe.isValid()) {
                    recipes.add(recipe);
                    continue;
                }
                SuperSolarPanels.log.warn("Skipping line {} as it is has the wrong format (expected length 3, found {})", (Object)lineNumber, (Object)recipe.parts.length);
            }
        }
        catch (IOException e) {
            try {
                SuperSolarPanels.log.fatal("RIP MT Config!", (Throwable)e);
                throw new RuntimeException("Fatal error reading Molecular Transformer recipe file", e);
            }
            catch (Throwable throwable) {
                IOUtils.closeQuietly(reader);
                IOUtils.closeQuietly((InputStream)stream);
                throw throwable;
            }
        }
        IOUtils.closeQuietly((Reader)reader);
        IOUtils.closeQuietly((InputStream)stream);
        MTRecipes = recipes.toArray(new MTRecipe[recipes.size()]);
    }

    private static void fillDefault(File config) {
        FileOutputStream stream = null;
        BufferedWriter writer = null;
        try {
            config.createNewFile();
            stream = new FileOutputStream(config);
            writer = new BufferedWriter(new OutputStreamWriter(stream));
            Configs.write(writer, "##################################################################################################");
            Configs.write(writer, "#                        AdvancedSolarPanels Molecular Transformer Recipes                       #");
            Configs.write(writer, "##################################################################################################");
            Configs.write(writer, "# Format of recipe: \"inputItem*stackSize;outputItem*outputStackSize;energy\"                      #");
            Configs.write(writer, "# InputItem (and outputItem) format:                                                             #");
            Configs.write(writer, "# \"OreDict:forgeOreDictName\" or \"minecraft:item_name@meta\" or \"modID:item_name@meta\"             #");
            Configs.write(writer, "# New line = new recipe.                                                                         #");
            Configs.write(writer, "# Add \"#\" before line to skip parsing line/recipe                                                #");
            Configs.write(writer, "##################################################################################################");
            writer.write("version=2.0" + NEW_LINE);
            Configs.write(writer, "##################################################################################################");
            Configs.write(writer, "minecraft:skull@1; minecraft:nether_star; 280000000");
            Configs.write(writer, "minecraft:iron_ingot@*; ic2:misc_resource#iridium_ore; 18000000");
            Configs.write(writer, "minecraft:netherrack@*; minecraft:gunpowder*2; 80000");
            Configs.write(writer, "minecraft:sand@*; minecraft:gravel; 30000");
            Configs.write(writer, "minecraft:dirt@*; minecraft:clay; 30000");
            Configs.write(writer, "minecraft:coal@1; minecraft:coal@0; 40000");
            Configs.write(writer, "minecraft:glowstone_dust@*; super_solar_panels_rioutouscraft:crafting@2; 800000");
            Configs.write(writer, "minecraft:glowstone@*; super_solar_panels_rioutouscraft:crafting@1; 4500000");
            Configs.write(writer, "minecraft:wool@4; minecraft:glowstone; 350000");
            Configs.write(writer, "minecraft:wool@11; minecraft:lapis_block; 350000");
            Configs.write(writer, "minecraft:wool@14; minecraft:redstone_block; 350000");
            Configs.write(writer, "minecraft:dye@4; OreDict:gemSapphire; 4000000");
            Configs.write(writer, "minecraft:redstone@*; OreDict:gemRuby; 4000000");
            Configs.write(writer, "minecraft:coal@0; ic2:crafting#industrial_diamond; 7000000");
            Configs.write(writer, "ic2:crafting#industrial_diamond; minecraft:diamond; 800000");
            Configs.write(writer, "OreDict:dustTitanium; OreDict:dustChrome; 300000");
            Configs.write(writer, "OreDict:ingotTitanium; OreDict:ingotChrome; 300000");
            Configs.write(writer, "OreDict:gemNetherQuartz; OreDict:gemCertusQuartz; 300000");
            Configs.write(writer, "OreDict:ingotCopper; OreDict:ingotNickel; 150000");
            Configs.write(writer, "OreDict:ingotTin; OreDict:ingotSilver; 350000");
            Configs.write(writer, "OreDict:ingotSilver; OreDict:ingotGold; 350000");
            Configs.write(writer, "OreDict:ingotGold; OreDict:ingotPlatinum; 4000000");
            Configs.write(writer, "super_solar_panels_rioutouscraft:crafting@5; super_solar_panels_rioutouscraft:crafting@48; 15000000");
            Configs.write(writer, "super_solar_panels_rioutouscraft:crafting@6; super_solar_panels_rioutouscraft:crafting@49; 15000000");
            Configs.write(writer, "ic2:misc_resource#iridium_ore; super_solar_panels_rioutouscraft:crafting@52; 25000000");
        }
        catch (IOException e) {
            try {
                SuperSolarPanels.log.fatal("RIP MT Config!", (Throwable)e);
                throw new RuntimeException("Fatal error writing Molecular Transformer recipe file", e);
            }
            catch (Throwable throwable) {
                IOUtils.closeQuietly(writer);
                IOUtils.closeQuietly(stream);
                throw throwable;
            }
        }
        IOUtils.closeQuietly((Writer)writer);
        IOUtils.closeQuietly((OutputStream)stream);
    }

    private static void write(BufferedWriter writer, String line) throws IOException {
        writer.write(line);
        writer.newLine();
    }

    static {
        NEW_LINE = System.getProperty("line.separator");
    }
}

