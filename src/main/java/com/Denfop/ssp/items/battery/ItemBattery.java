/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.api.item.ElectricItem
 *  ic2.core.util.StackUtil
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ActionResult
 *  net.minecraft.util.EnumActionResult
 *  net.minecraft.util.EnumHand
 *  net.minecraft.world.World
 */
package com.Denfop.ssp.items.battery;

import com.Denfop.ssp.items.itembase.BaseElectricItem;
import ic2.api.item.ElectricItem;
import ic2.core.util.StackUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemBattery
extends BaseElectricItem {
    private static int maxLevel = 4;
    public static ItemBattery settings;
    public static ItemBattery settings1;

    public ItemBattery(String name, double maxCharge, double transferLimit, int tier) {
        super(name, maxCharge, transferLimit, tier);
        this.func_77625_d(16);
    }

    @Override
    public boolean canProvideEnergy(ItemStack stack) {
        return true;
    }

    public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = StackUtil.get((EntityPlayer)player, (EnumHand)hand);
        if (world.field_72995_K || StackUtil.getSize((ItemStack)stack) != 1) {
            return new ActionResult(EnumActionResult.PASS, (Object)stack);
        }
        if (ElectricItem.manager.getCharge(stack) > 0.0) {
            boolean transferred = false;
            for (int i = 0; i < 9; ++i) {
                double transfer;
                ItemStack target = (ItemStack)player.field_71071_by.field_70462_a.get(i);
                if (target == null || target == stack || !(ElectricItem.manager.discharge(target, Double.POSITIVE_INFINITY, Integer.MAX_VALUE, true, true, true) <= 0.0) || !((transfer = ElectricItem.manager.discharge(stack, 2.0 * this.transferLimit, Integer.MAX_VALUE, true, true, true)) > 0.0) || !((transfer = ElectricItem.manager.charge(target, transfer, this.tier, true, false)) > 0.0)) continue;
                ElectricItem.manager.discharge(stack, transfer, Integer.MAX_VALUE, true, true, false);
                transferred = true;
            }
            if (transferred && !world.field_72995_K) {
                player.field_71070_bA.func_75142_b();
            }
        }
        return new ActionResult(EnumActionResult.SUCCESS, (Object)stack);
    }
}

