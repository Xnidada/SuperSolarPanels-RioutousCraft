/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.config.ConfigCategory
 *  net.minecraftforge.common.config.Configuration
 */
package com.rioutouscraft.ssp;

import java.io.File;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {
    public static int iridiumOnChunk = 4;

    public static void loadConfig(File configFile) {
        Configuration config = new Configuration(configFile);
        try {
            config.load();
            config.removeCategory(new ConfigCategory("general"));
            String diff = config.get("base", "Difficulty recipes", "NORMAL", "Determines the difficulty of recipes. Valid values: EASY, NORMAL, HARD").getString();
            iridiumOnChunk = config.getInt("Iridium/platium on chunk", "BASE", 3, 1, 10, "Attempts to generate platium/iridium ore at the chunk. The more the more common ore blocks.");
            config.save();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

