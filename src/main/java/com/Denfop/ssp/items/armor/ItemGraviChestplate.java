/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.api.item.ElectricItem
 *  ic2.core.IC2
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package com.Denfop.ssp.items.armor;

import com.Denfop.ssp.Configs;
import com.Denfop.ssp.items.armorbase.ItemAdvancedElectricJetpack;
import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemGraviChestplate
extends ItemAdvancedElectricJetpack {
    protected static final int DEFAULT_COLOUR = -1;

    public ItemGraviChestplate() {
        super("graviChestplate", Configs.maxCharge6, Configs.transferLimit6, Configs.tier6);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return "super_solar_panels:textures/armour/" + this.name + (type != null ? "Overlay" : "") + ".png";
    }

    public void func_82813_b(ItemStack stack, int colour) {
        this.getDisplayNbt(stack, true).func_74768_a("colour", colour);
    }

    public boolean func_82816_b_(ItemStack stack) {
        return this.func_82814_b(stack) != -1;
    }

    public int func_82814_b(ItemStack stack) {
        NBTTagCompound nbt = this.getDisplayNbt(stack, false);
        return nbt == null || !nbt.func_150297_b("colour", 3) ? -1 : nbt.func_74762_e("colour");
    }

    public void func_82815_c(ItemStack stack) {
        NBTTagCompound nbt = this.getDisplayNbt(stack, false);
        if (nbt == null || !nbt.func_150297_b("colour", 3)) {
            return;
        }
        nbt.func_82580_o("colour");
        if (nbt.func_82582_d()) {
            stack.func_77978_p().func_82580_o("display");
        }
    }

    protected NBTTagCompound getDisplayNbt(ItemStack stack, boolean create) {
        NBTTagCompound out;
        NBTTagCompound nbt = stack.func_77978_p();
        if (nbt == null) {
            if (!create) {
                return null;
            }
            nbt = new NBTTagCompound();
            stack.func_77982_d(nbt);
        }
        if (!nbt.func_150297_b("display", 10)) {
            if (!create) {
                return null;
            }
            out = new NBTTagCompound();
            nbt.func_74782_a("display", (NBTBase)out);
        } else {
            out = nbt.func_74775_l("display");
        }
        return out;
    }

    @Override
    public EnumRarity func_77613_e(ItemStack stack) {
        return EnumRarity.EPIC;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        super.onArmorTick(world, player, stack);
        player.func_70066_B();
    }

    @Override
    public boolean isJetpackActive(ItemStack stack) {
        return super.isJetpackActive(stack) && ElectricItem.manager.getCharge(stack) >= 10000.0;
    }

    @Override
    public float getPower(ItemStack stack) {
        return 1.5f;
    }

    @Override
    public float getDropPercentage(ItemStack stack) {
        return 0.01f;
    }

    @Override
    public float getBaseThrust(ItemStack stack, boolean hover) {
        return hover ? 1.0f : 0.5f;
    }

    @Override
    public float getBoostThrust(EntityPlayer player, ItemStack stack, boolean hover) {
        return IC2.keyboard.isBoostKeyDown(player) && ElectricItem.manager.getCharge(stack) >= 834.0 ? (hover ? 0.1f : 0.3f) : 0.0f;
    }

    @Override
    public boolean useBoostPower(ItemStack stack, float boostAmount) {
        return ElectricItem.manager.discharge(stack, 834.0, Integer.MAX_VALUE, true, false, false) > 0.0;
    }

    @Override
    public float getWorldHeightDivisor(ItemStack stack) {
        return 0.91071427f;
    }

    @Override
    public float getHoverMultiplier(ItemStack stack, boolean upwards) {
        return 0.25f;
    }

    @Override
    public float getHoverBoost(EntityPlayer player, ItemStack stack, boolean up) {
        if (IC2.keyboard.isBoostKeyDown(player) && ElectricItem.manager.getCharge(stack) >= 834.0) {
            if (!player.field_70122_E) {
                ElectricItem.manager.discharge(stack, 834.0, Integer.MAX_VALUE, true, false, false);
            }
            return 3.0f;
        }
        return 1.0f;
    }

    @Override
    public boolean drainEnergy(ItemStack pack, int amount) {
        return ElectricItem.manager.discharge(pack, 278.0, Integer.MAX_VALUE, true, false, false) > 0.0;
    }

    @Override
    public int getEnergyPerDamage() {
        return 20000;
    }

    @Override
    public double getDamageAbsorptionRatio() {
        return 1.1;
    }

    @Override
    public boolean canProvideEnergy(ItemStack stack) {
        return true;
    }
}

