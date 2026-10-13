/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagInt
 */
package com.Denfop.ssp.items.api;

import com.Denfop.ssp.items.api.IRechargable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;

public class RechargeHelper {
    public static int getCharge(ItemStack is) {
        if (is == null || is.func_190926_b() || !(is.func_77973_b() instanceof IRechargable)) {
            return -1;
        }
        if (is.func_77942_o()) {
            return is.func_77978_p().func_74762_e("tc.charge");
        }
        return 0;
    }

    public static boolean consumeCharge(ItemStack is, EntityLivingBase player, int amt) {
        int charge;
        if (is == null || is.func_190926_b() || !(is.func_77973_b() instanceof IRechargable)) {
            return false;
        }
        if (is.func_77942_o() && (charge = is.func_77978_p().func_74762_e("tc.charge")) >= amt) {
            is.func_77983_a("tc.charge", (NBTBase)new NBTTagInt(charge -= amt));
            return true;
        }
        return false;
    }
}

