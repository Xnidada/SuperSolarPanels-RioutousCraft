/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 */
package com.Denfop.ssp.items.api;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public interface IRechargable {
    public int getMaxCharge(ItemStack var1, EntityLivingBase var2);

    public EnumChargeDisplay showInHud(ItemStack var1, EntityLivingBase var2);

    public static enum EnumChargeDisplay {
        NEVER,
        NORMAL,
        PERIODIC;

    }
}

