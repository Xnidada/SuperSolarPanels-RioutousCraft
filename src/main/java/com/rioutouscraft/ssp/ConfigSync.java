package com.rioutouscraft.ssp;

import com.rioutouscraft.ssp.tiles.TileEntitySolarPanel.SolarConfig;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityAdmin;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityAdvancedSolar;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityBarion;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityGraviton;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityHadron;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityHybridSolar;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityQuantumSolar;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityQuark;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntitySingular;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntitySpectral;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityUltimateHybridSolar;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityneutron;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityphotonic;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityproton;

/** Values received from the authoritative server for client-facing item information. */
public final class ConfigSync {
    private static final int LEVELS = 14;
    private static final double[] solarDay = new double[LEVELS];
    private static final double[] solarNight = new double[LEVELS];
    private static final double[] solarStorage = new double[LEVELS];
    private static final double[] solarOutput = new double[LEVELS];
    private static final double[] batteryCapacity = new double[LEVELS];
    private static final double[] moduleOutput = new double[LEVELS];
    private static final int[] moduleOutputTier = new int[LEVELS];
    private static volatile boolean serverValues;

    private ConfigSync() {
    }

    static void captureLocal() {
        SolarConfig[] configs = {
                TileEntityAdvancedSolar.settings, TileEntityHybridSolar.settings,
                TileEntityUltimateHybridSolar.settings, TileEntityQuantumSolar.settings,
                TileEntitySpectral.settings, TileEntityproton.settings,
                TileEntitySingular.settings, TileEntityAdmin.settings,
                TileEntityphotonic.settings, TileEntityneutron.settings,
                TileEntityBarion.settings, TileEntityHadron.settings,
                TileEntityGraviton.settings, TileEntityQuark.settings
        };
        for (int i = 0; i < LEVELS; i++) {
            SolarConfig config = configs[i];
            solarDay[i] = config.dayPower;
            solarNight[i] = config.nightPower;
            solarStorage[i] = config.storage;
            solarOutput[i] = config.output;
            batteryCapacity[i] = Configs.moduleBatteryCapacity[i];
            moduleOutput[i] = Configs.moduleOutput[i];
            moduleOutputTier[i] = Configs.moduleOutputTier[i];
        }
        serverValues = false;
    }

    static PacketServerConfig createPacket() {
        return new PacketServerConfig(solarDay, solarNight, solarStorage, solarOutput,
                batteryCapacity, moduleOutput, moduleOutputTier);
    }

    static void apply(PacketServerConfig packet) {
        System.arraycopy(packet.solarDay, 0, solarDay, 0, LEVELS);
        System.arraycopy(packet.solarNight, 0, solarNight, 0, LEVELS);
        System.arraycopy(packet.solarStorage, 0, solarStorage, 0, LEVELS);
        System.arraycopy(packet.solarOutput, 0, solarOutput, 0, LEVELS);
        System.arraycopy(packet.batteryCapacity, 0, batteryCapacity, 0, LEVELS);
        System.arraycopy(packet.moduleOutput, 0, moduleOutput, 0, LEVELS);
        System.arraycopy(packet.moduleOutputTier, 0, moduleOutputTier, 0, LEVELS);
        serverValues = true;
    }

    public static double solarDay(int tier, double fallback) {
        return value(solarDay, tier, fallback);
    }

    public static double solarNight(int tier, double fallback) {
        return value(solarNight, tier, fallback);
    }

    public static double solarStorage(int tier, double fallback) {
        return value(solarStorage, tier, fallback);
    }

    public static double solarOutput(int tier, double fallback) {
        return value(solarOutput, tier, fallback);
    }

    public static double batteryCapacity(int id, double fallback) {
        return value(batteryCapacity, id + 1, fallback);
    }

    public static double moduleOutput(int id, double fallback) {
        return value(moduleOutput, id + 1, fallback);
    }

    public static int moduleOutputTier(int id, int fallback) {
        if (!serverValues || id < 0 || id >= LEVELS) return fallback;
        return moduleOutputTier[id];
    }

    private static double value(double[] values, int level, double fallback) {
        if (!serverValues || level < 1 || level > LEVELS) return fallback;
        return values[level - 1];
    }
}
