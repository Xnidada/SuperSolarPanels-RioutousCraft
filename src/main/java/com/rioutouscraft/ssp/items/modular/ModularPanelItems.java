package com.rioutouscraft.ssp.items.modular;

import ic2.core.ref.ItemName;
import net.minecraftforge.fml.relauncher.Side;

public final class ModularPanelItems {
    public static ModularPanelGlass GLASS;
    public static ModularPanelBattery BATTERY;
    public static ModularPanelOutput OUTPUT;

    private ModularPanelItems() {}

    public static void register(Side side) {
        GLASS = new ModularPanelGlass();
        BATTERY = new ModularPanelBattery();
        OUTPUT = new ModularPanelOutput();
        if (side == Side.CLIENT) registerModels();
    }

    private static void registerModels() {
        GLASS.registerModels((ItemName) null);
        BATTERY.registerModels((ItemName) null);
        OUTPUT.registerModels((ItemName) null);
    }
}
