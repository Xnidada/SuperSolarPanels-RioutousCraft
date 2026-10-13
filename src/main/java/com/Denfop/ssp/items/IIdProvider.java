/*
 * Decompiled with CFR 0.152.
 */
package com.Denfop.ssp.items;

public interface IIdProvider {
    public String getName();

    public int getId();

    default public int getColor() {
        return 0xFFFFFF;
    }

    default public String getModelName() {
        return this.getName();
    }
}

