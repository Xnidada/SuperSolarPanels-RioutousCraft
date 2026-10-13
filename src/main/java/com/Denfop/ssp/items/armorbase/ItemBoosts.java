/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.CaseFormat
 *  ic2.api.item.ElectricItem
 *  ic2.core.IC2
 *  ic2.core.init.BlocksItems
 *  ic2.core.item.armor.ItemArmorElectric
 *  ic2.core.item.armor.jetpack.IBoostingJetpack
 *  ic2.core.ref.ItemName
 *  ic2.core.util.StackUtil
 *  net.minecraft.client.renderer.block.model.ModelResourceLocation
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagInt
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.client.model.ModelLoader
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.Denfop.ssp.items.armorbase;

import com.Denfop.ssp.items.api.PlayerEvents;
import com.Denfop.ssp.items.api.RechargeHelper;
import com.google.common.base.CaseFormat;
import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import ic2.core.init.BlocksItems;
import ic2.core.item.armor.ItemArmorElectric;
import ic2.core.item.armor.jetpack.IBoostingJetpack;
import ic2.core.ref.ItemName;
import ic2.core.util.StackUtil;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBoosts
extends ItemArmorElectric
implements IBoostingJetpack {
    protected final String name;

    public ItemBoosts() {
        this("advancedJetpack");
    }

    protected ItemBoosts(String name) {
        this(name, 3000000.0, 30000.0, 3);
    }

    protected ItemBoosts(String name, double maxCharge, double transferLimit, int tier) {
        super((ItemName)null, (String)null, EntityEquipmentSlot.FEET, maxCharge, transferLimit, tier);
        this.name = name;
        ((ItemBoosts)BlocksItems.registerItem((Item)this, (ResourceLocation)new ResourceLocation("super_solar_panels_rioutouscraft", this.name))).func_77655_b(name);
        this.func_77656_e(27);
        this.func_77625_d(1);
        this.setNoRepair();
    }

    @SideOnly(value=Side.CLIENT)
    public void registerModels(ItemName name) {
        ModelLoader.setCustomModelResourceLocation((Item)this, (int)0, (ModelResourceLocation)new ModelResourceLocation("super_solar_panels_rioutouscraft:" + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.name), (String)null));
    }

    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return "super_solar_panels_rioutouscraft:textures/armour/" + this.name + ".png";
    }

    public String func_77658_a() {
        return "super_solar_panels_rioutouscraft." + super.func_77658_a().substring(4);
    }

    public EnumRarity func_77613_e(ItemStack stack) {
        return EnumRarity.UNCOMMON;
    }

    public static boolean isHovering(ItemStack stack) {
        return StackUtil.getOrCreateNbtData((ItemStack)stack).func_74767_n("hoverMode");
    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        boolean hasCharge;
        NBTTagCompound nbt = StackUtil.getOrCreateNbtData((ItemStack)stack);
        byte toggleTimer = nbt.func_74771_c("toggleTimer");
        if (!world.field_72995_K && player.field_70173_aa % 20 == 0) {
            int e = 0;
            if (stack.func_77942_o()) {
                e = stack.func_77978_p().func_74762_e("energy");
            }
            if (e > 0) {
                --e;
            } else if (e <= 0 && RechargeHelper.consumeCharge(stack, (EntityLivingBase)player, 1)) {
                e = 60;
            }
            stack.func_77983_a("energy", (NBTBase)new NBTTagInt(e));
        }
        boolean bl = hasCharge = RechargeHelper.getCharge(stack) > 0;
        if (hasCharge && !player.field_71075_bZ.field_75100_b && player.field_191988_bg > 0.0f) {
            if (player.field_70170_p.field_72995_K && !player.func_70093_af()) {
                if (!PlayerEvents.prevStep.containsKey(player.func_145782_y())) {
                    PlayerEvents.prevStep.put(player.func_145782_y(), Float.valueOf(player.field_70138_W));
                }
                player.field_70138_W = 1.0f;
            }
            if (player.field_70122_E) {
                float bonus = 0.05f;
                if (player.func_70090_H()) {
                    bonus /= 4.0f;
                }
                player.func_191958_b(0.0f, 0.0f, bonus, 1.0f);
            } else {
                if (player.func_70090_H()) {
                    player.func_191958_b(0.0f, 0.0f, 0.025f, 1.0f);
                }
                player.field_70747_aH = 0.05f;
            }
        }
    }

    public double getChargeLevel(ItemStack stack) {
        return ElectricItem.manager.getCharge(stack) / this.getMaxCharge(stack);
    }

    public float getPower(ItemStack stack) {
        return 1.0f;
    }

    public float getDropPercentage(ItemStack stack) {
        return 0.05f;
    }

    public float getBaseThrust(ItemStack stack, boolean hover) {
        return hover ? 0.65f : 0.3f;
    }

    public float getBoostThrust(EntityPlayer player, ItemStack stack, boolean hover) {
        return IC2.keyboard.isBoostKeyDown(player) && ElectricItem.manager.getCharge(stack) >= 60.0 ? (hover ? 0.07f : 0.09f) : 0.0f;
    }

    public boolean useBoostPower(ItemStack stack, float boostAmount) {
        return ElectricItem.manager.discharge(stack, 60.0, Integer.MAX_VALUE, true, false, false) > 0.0;
    }

    public float getWorldHeightDivisor(ItemStack stack) {
        return 1.0f;
    }

    public float getHoverMultiplier(ItemStack stack, boolean upwards) {
        return 0.2f;
    }

    public float getHoverBoost(EntityPlayer player, ItemStack stack, boolean up) {
        if (IC2.keyboard.isBoostKeyDown(player) && ElectricItem.manager.getCharge(stack) >= 60.0) {
            if (!player.field_70122_E) {
                ElectricItem.manager.discharge(stack, 60.0, Integer.MAX_VALUE, true, false, false);
            }
            return 2.0f;
        }
        return 1.0f;
    }

    public boolean drainEnergy(ItemStack pack, int amount) {
        return ElectricItem.manager.discharge(pack, (double)(amount * 6), Integer.MAX_VALUE, true, false, false) > 0.0;
    }

    public boolean canProvideEnergy(ItemStack stack) {
        return true;
    }

    public int getEnergyPerDamage() {
        return 0;
    }

    public double getDamageAbsorptionRatio() {
        return 0.0;
    }

    public boolean isJetpackActive(ItemStack arg0) {
        return false;
    }
}

