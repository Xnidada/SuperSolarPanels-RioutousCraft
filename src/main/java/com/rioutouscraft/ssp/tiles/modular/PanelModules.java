package com.rioutouscraft.ssp.tiles.modular;

import com.rioutouscraft.ssp.items.modular.ModularPanelBattery;
import com.rioutouscraft.ssp.items.modular.ModularPanelGlass;
import com.rioutouscraft.ssp.items.modular.ModularPanelOutput;
import ic2.api.energy.EnergyNet;

/** Registers persistent IC2 inventory slots on an existing panel, rather than a new block. */
public final class PanelModules {
    public final InvSlotPanelComponent glassSlot;
    public final InvSlotPanelComponent batterySlot;
    public final InvSlotPanelComponent outputSlot;

    public PanelModules(InvSlotPanelComponent glassSlot, InvSlotPanelComponent batterySlot, InvSlotPanelComponent outputSlot) {
        this.glassSlot = glassSlot;
        this.batterySlot = batterySlot;
        this.outputSlot = outputSlot;
    }

    public int generation(int base, boolean daylight) {
        double bonus = 0.0;
        if (!daylight && !glassSlot.isEmpty()) {
            bonus = ModularPanelGlass.getPanelType(glassSlot.get()).nightMultiplier;
        }
        return PanelModuleMath.generation(base, bonus);
    }

    public double generation(double base, boolean daylight) {
        double bonus = 0.0;
        if (!daylight && !glassSlot.isEmpty()) {
            bonus = ModularPanelGlass.getPanelType(glassSlot.get()).nightMultiplier;
        }
        return PanelModuleMath.generation(base, bonus);
    }

    /** IndustrialUpgrade-style weather coefficient: normal rain is 65% output. */
    public double generation(double base, boolean daylight, boolean raining) {
        if (!raining) return generation(base, daylight);
        double weatherMultiplier = 0.65;
        if (!glassSlot.isEmpty()) {
            ModularPanelGlass.Type glass = ModularPanelGlass.getPanelType(glassSlot.get());
            weatherMultiplier += glass.rainMultiplier;
            // Night generation remains a separate modifier when it rains at night.
            weatherMultiplier *= daylight ? 1.0 : 1.0 + glass.nightMultiplier;
        }
        return PanelModuleMath.generation(base, weatherMultiplier - 1.0);
    }

    public int capacity(int base) {
        double extra = batterySlot.isEmpty() ? 0 : ModularPanelBattery.getPanelType(batterySlot.get()).getCapacity();
        return (int)Math.min(Integer.MAX_VALUE, PanelModuleMath.capacity(base, extra));
    }

    public double capacity(double base) {
        double extra = batterySlot.isEmpty() ? 0 : ModularPanelBattery.getPanelType(batterySlot.get()).getCapacity();
        return PanelModuleMath.capacity(base, extra);
    }

    public double outputLimit(int baseTier) {
        return EnergyNet.instance.getPowerFromTier(baseTier)
                + (outputSlot.isEmpty() ? 0 : ModularPanelOutput.getPanelType(outputSlot.get()).getOutput());
    }

    public double outputLimit(double baseOutput, int baseTier) {
        return baseOutput + (outputSlot.isEmpty() ? 0 : ModularPanelOutput.getPanelType(outputSlot.get()).getOutput());
    }

    public int sourceTier(int baseTier) {
        if (outputSlot.isEmpty()) return baseTier;
        return Math.max(baseTier, ModularPanelOutput.getPanelType(outputSlot.get()).getTier());
    }
}
