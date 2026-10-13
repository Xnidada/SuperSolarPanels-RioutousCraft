/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagInt
 */
package com.rioutouscraft.ssp.items.api;

import com.rioutouscraft.ssp.items.api.IRechargable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;

public class RechargeHelper {
    public static int getCharge(ItemStack is) {
        if (is == null || is.isEmpty() || !(is.getItem() instanceof IRechargable)) {
            return -1;
        }
        if (is.hasTagCompound()) {
            return is.getTagCompound().getInteger("tc.charge");
        }
        return 0;
    }

    public static boolean consumeCharge(ItemStack is, EntityLivingBase player, int amt) {
        int charge;
        if (is == null || is.isEmpty() || !(is.getItem() instanceof IRechargable)) {
            return false;
        }
        if (is.hasTagCompound() && (charge = is.getTagCompound().getInteger("tc.charge")) >= amt) {
            is.setTagInfo("tc.charge", (NBTBase)new NBTTagInt(charge -= amt));
            return true;
        }
        return false;
    }
}

