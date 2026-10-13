/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.core.ref.ItemName
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.Denfop.ssp.items.api;

import ic2.core.ref.ItemName;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IItemModelProvider {
    @SideOnly(value=Side.CLIENT)
    public void registerModels(ItemName var1);
}

