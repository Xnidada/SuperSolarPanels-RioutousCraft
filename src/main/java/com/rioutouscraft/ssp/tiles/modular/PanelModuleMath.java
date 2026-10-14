package com.rioutouscraft.ssp.tiles.modular;

/** Arithmetic shared by the four existing panel families. */
public final class PanelModuleMath {
    private PanelModuleMath() {}

    public static int generation(int base, double bonus) {
        return (int) Math.min(Integer.MAX_VALUE, Math.max(0L, Math.round(base * (1.0 + bonus))));
    }

    public static int capacity(int base, int extra) {
        return (int) Math.min(Integer.MAX_VALUE, Math.max(0L, (long) base + extra));
    }

    public static int store(int stored, int generated, int capacity) {
        // Removing a battery stops generation until the existing buffer drains; it does not delete EU.
        if (stored >= capacity) return stored;
        return (int) Math.min(capacity, (long) Math.max(0, stored) + Math.max(0, generated));
    }
}
