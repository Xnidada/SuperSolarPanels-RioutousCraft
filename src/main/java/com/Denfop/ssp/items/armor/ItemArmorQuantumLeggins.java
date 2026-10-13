/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.api.item.ElectricItem
 *  ic2.api.item.HudMode
 *  ic2.core.IC2
 *  ic2.core.init.Localization
 *  ic2.core.init.MainConfig
 *  ic2.core.util.Config
 *  ic2.core.util.ConfigUtil
 *  ic2.core.util.StackUtil
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.MobEffects
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.Denfop.ssp.items.armor;

import com.Denfop.ssp.Configs;
import com.Denfop.ssp.items.armorbase.ItemLeggins;
import com.Denfop.ssp.keyboard.SSPKeys;
import ic2.api.item.ElectricItem;
import ic2.api.item.HudMode;
import ic2.core.IC2;
import ic2.core.init.Localization;
import ic2.core.init.MainConfig;
import ic2.core.util.Config;
import ic2.core.util.ConfigUtil;
import ic2.core.util.StackUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemArmorQuantumLeggins
extends ItemLeggins {
    protected static final int DEFAULT_COLOUR = -1;

    public ItemArmorQuantumLeggins() {
        super("graviLeggins", Configs.maxCharge5, Configs.transferLimit5, Configs.tier5);
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

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        boolean enableQuantumSpeedOnSprint;
        super.onArmorTick(world, player, stack);
        player.func_70066_B();
        NBTTagCompound nbtData = StackUtil.getOrCreateNbtData((ItemStack)stack);
        byte toggleTimer = nbtData.func_74771_c("toggleTimer");
        boolean ret = false;
        boolean bl = enableQuantumSpeedOnSprint = !IC2.platform.isRendering() || ConfigUtil.getBool((Config)MainConfig.get(), (String)"misc/quantumSpeedOnSprint");
        if (ElectricItem.manager.canUse(stack, 1000.0) && (player.field_70122_E || player.func_70090_H()) && IC2.keyboard.isForwardKeyDown(player) && (enableQuantumSpeedOnSprint && player.func_70051_ag() || !enableQuantumSpeedOnSprint && IC2.keyboard.isBoostKeyDown(player))) {
            byte speedTicker = nbtData.func_74771_c("speedTicker");
            if ((speedTicker = (byte)(speedTicker + 1)) >= 10) {
                speedTicker = 0;
                ElectricItem.manager.use(stack, 1000.0, null);
                ret = true;
            }
            nbtData.func_74774_a("speedTicker", speedTicker);
            float speed = 0.22f;
            if (player.func_70090_H()) {
                speed = 0.12f;
                if (IC2.keyboard.isJumpKeyDown(player)) {
                    player.field_70181_x += 0.12000000149011612;
                }
            }
            if (speed > 0.0f) {
                player.func_191958_b(0.0f, 0.0f, 1.0f, speed);
            }
        }
        IC2.platform.profilerEndSection();
        boolean Nightvision = nbtData.func_74767_n("Nightvision");
        short hubmode = nbtData.func_74765_d("HudMode");
        if (SSPKeys.Isremovepoison(player) && toggleTimer == 0) {
            toggleTimer = 10;
            boolean bl2 = Nightvision = !Nightvision;
            if (IC2.platform.isSimulating()) {
                nbtData.func_74757_a("Nightvision", Nightvision);
                if (Nightvision) {
                    IC2.platform.messagePlayer(player, "Effects enabled.", new Object[0]);
                } else {
                    IC2.platform.messagePlayer(player, "Effects disabled.", new Object[0]);
                }
            }
        }
        if (IC2.keyboard.isAltKeyDown(player) && IC2.keyboard.isHudModeKeyDown(player) && toggleTimer == 0) {
            toggleTimer = 10;
            hubmode = hubmode == HudMode.getMaxMode() ? (short)0 : (short)(hubmode + 1);
            if (IC2.platform.isSimulating()) {
                nbtData.func_74777_a("HudMode", hubmode);
                IC2.platform.messagePlayer(player, Localization.translate((String)HudMode.getFromID((int)hubmode).getTranslationKey()), new Object[0]);
            }
        }
        if (IC2.platform.isSimulating() && toggleTimer > 0) {
            NBTTagCompound nbtTagCompound = nbtData;
            String s = "toggleTimer";
            toggleTimer = (byte)(toggleTimer - 1);
            nbtTagCompound.func_74774_a("toggleTimer", toggleTimer);
        }
        if (!Nightvision) return;
        if (!IC2.platform.isSimulating()) return;
        if (!ElectricItem.manager.use(stack, 1.0, (EntityLivingBase)player)) return;
        BlockPos pos = new BlockPos((int)Math.floor(player.field_70165_t), (int)Math.floor(player.field_70163_u), (int)Math.floor(player.field_70161_v));
        int skylight = player.func_130014_f_().func_175671_l(pos);
        if (skylight > 8) {
            if (!Configs.canCraftHSP) return;
            player.func_70690_d(new PotionEffect(MobEffects.field_76424_c, 300, 2, true, true));
            if (!Configs.canCraftHSH) return;
            player.func_70690_d(new PotionEffect(MobEffects.field_188425_z, 300, 2, true, true));
            return;
        } else {
            if (!Configs.canCraftHSP) return;
            player.func_70690_d(new PotionEffect(MobEffects.field_76424_c, 300, 2, true, true));
            if (!Configs.canCraftHSH) return;
            player.func_70690_d(new PotionEffect(MobEffects.field_188425_z, 300, 2, true, true));
        }
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

