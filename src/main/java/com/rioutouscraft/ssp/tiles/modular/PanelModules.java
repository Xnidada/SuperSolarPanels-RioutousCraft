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
        if (!glassSlot.isEmpty()) {
            ModularPanelGlass.Type glass = ModularPanelGlass.getPanelType(glassSlot.get());
            bonus = daylight ? glass.dayMultiplier : glass.nightMultiplier;
        }
        return PanelModuleMath.generation(base, bonus);
    }

    public double generation(double base, boolean daylight) {
        double bonus = 0.0;
        if (!glassSlot.isEmpty()) {
            ModularPanelGlass.Type glass = ModularPanelGlass.getPanelType(glassSlot.get());
            bonus = daylight ? glass.dayMultiplier : glass.nightMultiplier;
        }
        return PanelModuleMath.generation(base, bonus);
    }

    public int capacity(int base) {
        int extra = batterySlot.isEmpty() ? 0 : ModularPanelBattery.getPanelType(batterySlot.get()).capacity;
        return PanelModuleMath.capacity(base, extra);
    }

    public double capacity(double base) {
        int extra = batterySlot.isEmpty() ? 0 : ModularPanelBattery.getPanelType(batterySlot.get()).capacity;
        return PanelModuleMath.capacity(base, extra);
    }

    public double outputLimit(int baseTier) {
        return EnergyNet.instance.getPowerFromTier(baseTier)
                + (outputSlot.isEmpty() ? 0 : ModularPanelOutput.getPanelType(outputSlot.get()).output);
    }

    public double outputLimit(double baseOutput, int baseTier) {
        return baseOutput + (outputSlot.isEmpty() ? 0 : ModularPanelOutput.getPanelType(outputSlot.get()).output);
    }

    public int sourceTier(int baseTier) {
        if (outputSlot.isEmpty()) return baseTier;
        return Math.max(baseTier, ModularPanelOutput.getPanelType(outputSlot.get()).tier);
    }
}
