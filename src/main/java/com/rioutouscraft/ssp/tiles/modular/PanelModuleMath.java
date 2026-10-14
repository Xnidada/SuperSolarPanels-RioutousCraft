package com.rioutouscraft.ssp.tiles.modular;

/** Arithmetic shared by the four existing panel families. */
public final class PanelModuleMath {
    private PanelModuleMath() {}

    public static int generation(int base, double bonus) {
        return (int) Math.min(Integer.MAX_VALUE, Math.max(0L, Math.round(base * (1.0 + bonus))));
    }

    public static double generation(double base, double bonus) {
        return Math.max(0.0, base * (1.0 + bonus));
    }

    public static long generation(long base, double bonus) {
        return Math.max(0L, Math.round(base * (1.0 + bonus)));
    }

    public static int capacity(int base, int extra) {
        return (int) Math.min(Integer.MAX_VALUE, Math.max(0L, (long) base + extra));
    }

    public static double capacity(double base, int extra) {
        return Math.max(0.0, base + extra);
    }

    public static long capacity(long base, int extra) {
        return Math.max(0L, base + extra);
    }

    public static int store(int stored, int generated, int capacity) {
        return (int) Math.min(Math.max(0, capacity), (long) Math.max(0, stored) + Math.max(0, generated));
    }

    public static double store(double stored, double generated, double capacity) {
        return Math.min(Math.max(0.0, capacity), Math.max(0.0, stored) + Math.max(0.0, generated));
    }

    public static long store(long stored, long generated, long capacity) {
        return Math.min(Math.max(0L, capacity), Math.max(0L, stored) + Math.max(0L, generated));
    }
}
