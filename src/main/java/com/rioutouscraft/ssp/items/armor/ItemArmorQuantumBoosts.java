/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.api.item.ElectricItem
 *  ic2.api.item.HudMode
 *  ic2.core.IC2
 *  ic2.core.init.Localization
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
package com.rioutouscraft.ssp.items.armor;

import com.rioutouscraft.ssp.SuperSolarPanels;

import com.rioutouscraft.ssp.Configs;
import com.rioutouscraft.ssp.items.armorbase.ItemBoosts;
import com.rioutouscraft.ssp.keyboard.SSPKeys;
import ic2.api.item.ElectricItem;
import ic2.api.item.HudMode;
import ic2.core.IC2;
import ic2.core.init.Localization;
import ic2.core.util.StackUtil;
import java.util.List;
import java.util.Locale;
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
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.world.World;

public class ItemArmorQuantumBoosts
extends ItemBoosts {
    protected static final int DEFAULT_COLOUR = -1;
    private static final String JUMP_BOOST_ENABLED = "jumpBoostEnabled";
    private static final String JUMP_BOOST_KEY_DOWN = "jumpBoostKeyDown";
    private float jumpCharge;

    public ItemArmorQuantumBoosts() {
        super("graviBoosts", Configs.maxCharge3, Configs.transferLimit3, Configs.tier3);
    }

    @Override
    public String getTranslationKey() {
        return SuperSolarPanels.MODID + ".graviBoosts";
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return "super_solar_panels_rioutouscraft:textures/armour/" + (this.name + (type != null ? "Overlay" : "")).toLowerCase(Locale.ROOT) + ".png";
    }

    public void setColor(ItemStack stack, int colour) {
        this.getDisplayNbt(stack, true).setInteger("colour", colour);
    }

    public boolean hasColor(ItemStack stack) {
        return this.getColor(stack) != -1;
    }

    public int getColor(ItemStack stack) {
        NBTTagCompound nbt = this.getDisplayNbt(stack, false);
        return nbt == null || !nbt.hasKey("colour", 3) ? -1 : nbt.getInteger("colour");
    }

    public void removeColor(ItemStack stack) {
        NBTTagCompound nbt = this.getDisplayNbt(stack, false);
        if (nbt == null || !nbt.hasKey("colour", 3)) {
            return;
        }
        nbt.removeTag("colour");
        if (nbt.isEmpty()) {
            stack.getTagCompound().removeTag("display");
        }
    }

    protected NBTTagCompound getDisplayNbt(ItemStack stack, boolean create) {
        NBTTagCompound out;
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt == null) {
            if (!create) {
                return null;
            }
            nbt = new NBTTagCompound();
            stack.setTagCompound(nbt);
        }
        if (!nbt.hasKey("display", 10)) {
            if (!create) {
                return null;
            }
            out = new NBTTagCompound();
            nbt.setTag("display", (NBTBase)out);
        } else {
            out = nbt.getCompoundTag("display");
        }
        return out;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.EPIC;
    }

    private static boolean isJumpBoostEnabled(ItemStack stack) {
        NBTTagCompound nbt = stack.getTagCompound();
        return nbt == null || !nbt.hasKey(JUMP_BOOST_ENABLED) || nbt.getBoolean(JUMP_BOOST_ENABLED);
    }

    private static void setJumpBoostEnabled(ItemStack stack, boolean enabled) {
        StackUtil.getOrCreateNbtData(stack).setBoolean(JUMP_BOOST_ENABLED, enabled);
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        boolean enabled = isJumpBoostEnabled(stack);
        String state = Localization.translate(SuperSolarPanels.MODID + ".quantum_boost." + (enabled ? "enabled" : "disabled"));
        tooltip.add(Localization.translate(SuperSolarPanels.MODID + ".quantum_boost.jump_boost", state));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        super.onArmorTick(world, player, stack);
        player.fallDistance = 0.0f;
        NBTTagCompound nbtData = StackUtil.getOrCreateNbtData((ItemStack)stack);
        byte toggleTimer = nbtData.getByte("toggleTimer");
        boolean ret = false;
        if (IC2.platform.isSimulating()) {
            boolean jumpKeyDown = SSPKeys.isQuantumBoostJumpKeyDown(player);
            boolean previousJumpKeyDown = nbtData.getBoolean(JUMP_BOOST_KEY_DOWN);
            if (jumpKeyDown && !previousJumpKeyDown && toggleTimer == 0) {
                boolean enabled = !isJumpBoostEnabled(stack);
                setJumpBoostEnabled(stack, enabled);
                toggleTimer = 10;
                nbtData.setByte("toggleTimer", toggleTimer);
                IC2.platform.messagePlayer(player, Localization.translate(SuperSolarPanels.MODID + ".quantum_boost.status." + (enabled ? "enabled" : "disabled")), new Object[0]);
            }
            nbtData.setBoolean(JUMP_BOOST_KEY_DOWN, jumpKeyDown);
        }
        if (IC2.platform.isSimulating()) {
            boolean wasOnGround;
            boolean bl = wasOnGround = !nbtData.hasKey("wasOnGround") || nbtData.getBoolean("wasOnGround");
            if (wasOnGround && !player.onGround && IC2.keyboard.isJumpKeyDown(player) && IC2.keyboard.isBoostKeyDown(player)) {
                ElectricItem.manager.use(stack, 4000.0, null);
                ret = true;
            }
            if (player.onGround != wasOnGround) {
                nbtData.setBoolean("wasOnGround", player.onGround);
            }
        } else {
            if (ElectricItem.manager.canUse(stack, 4000.0) && player.onGround) {
                this.jumpCharge = 1.0f;
            }
            if (player.motionY >= 0.0 && this.jumpCharge > 0.0f && !player.isInWater()) {
                if (this.jumpCharge == 0.9f) {
                    player.motionX *= 1.0;
                    player.motionZ *= 1.0;
                }
                player.motionY += (double)(this.jumpCharge * 0.3f);
                this.jumpCharge = (float)((double)this.jumpCharge * 0.75);
            } else if (this.jumpCharge < 1.0f) {
                this.jumpCharge = 0.0f;
            }
        }
        boolean Nightvision = nbtData.getBoolean("Nightvision");
        short hubmode = nbtData.getShort("HudMode");
        if (SSPKeys.Isremovepoison1(player) && toggleTimer == 0) {
            toggleTimer = 10;
            boolean bl = Nightvision = !Nightvision;
            if (IC2.platform.isSimulating()) {
                nbtData.setBoolean("Nightvision", Nightvision);
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
                nbtData.setShort("HudMode", hubmode);
                IC2.platform.messagePlayer(player, Localization.translate((String)HudMode.getFromID((int)hubmode).getTranslationKey()), new Object[0]);
            }
        }
        if (IC2.platform.isSimulating() && toggleTimer > 0) {
            NBTTagCompound nbtTagCompound = nbtData;
            String s = "toggleTimer";
            toggleTimer = (byte)(toggleTimer - 1);
            nbtTagCompound.setByte("toggleTimer", toggleTimer);
        }
        if (!Nightvision) return;
        if (!IC2.platform.isSimulating()) return;
        if (!ElectricItem.manager.use(stack, 1.0, (EntityLivingBase)player)) return;
        if (Configs.canCraftMT) {
            player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 300, 0, true, true));
        }
        if (isJumpBoostEnabled(stack)) {
            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 300, 0, true, true));
        }
        if (Configs.canCraftASH) {
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 300, 4, true, true));
        }
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
            if (!player.onGround) {
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

