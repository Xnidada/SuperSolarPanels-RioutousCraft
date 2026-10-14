/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.CaseFormat
 *  ic2.api.item.ElectricItem
 *  ic2.api.item.HudMode
 *  ic2.core.IC2
 *  ic2.core.init.BlocksItems
 *  ic2.core.init.Localization
 *  ic2.core.item.armor.ItemArmorElectric
 *  ic2.core.item.armor.jetpack.IBoostingJetpack
 *  ic2.core.ref.ItemName
 *  ic2.core.util.StackUtil
 *  net.minecraft.client.renderer.block.model.ModelResourceLocation
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.MobEffects
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.World
 *  net.minecraftforge.client.model.ModelLoader
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.rioutouscraft.ssp.items.armorbase;

import com.rioutouscraft.ssp.SuperSolarPanels;

import com.rioutouscraft.ssp.Configs;
import com.rioutouscraft.ssp.keyboard.SSPKeys;
import com.google.common.base.CaseFormat;
import ic2.api.item.ElectricItem;
import ic2.api.item.HudMode;
import ic2.core.IC2;
import ic2.core.init.BlocksItems;
import ic2.core.init.Localization;
import ic2.core.item.armor.ItemArmorElectric;
import ic2.core.item.armor.jetpack.IBoostingJetpack;
import ic2.core.ref.ItemName;
import ic2.core.util.StackUtil;
import java.util.Locale;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAdvancedElectricJetpack
extends ItemArmorElectric
implements IBoostingJetpack {
    protected final String name;

    public ItemAdvancedElectricJetpack() {
        this("advancedJetpack");
    }

    protected ItemAdvancedElectricJetpack(String name) {
        this(name, 3000000.0, 30000.0, 4);
    }

    protected ItemAdvancedElectricJetpack(String name, double maxCharge, double transferLimit, int tier) {
        super(null, null, EntityEquipmentSlot.CHEST, maxCharge, transferLimit, tier);
        this.setCreativeTab(SuperSolarPanels.SSPtab);
        this.name = name;
        ((ItemAdvancedElectricJetpack)BlocksItems.registerItem((Item)this, (ResourceLocation)new ResourceLocation("super_solar_panels_rioutouscraft", this.name))).setTranslationKey(name);
        this.setMaxDamage(27);
        this.setMaxStackSize(1);
        this.setNoRepair();
    }

    @SideOnly(value=Side.CLIENT)
    public void registerModels(ItemName name) {
        ModelLoader.setCustomModelResourceLocation((Item)this, (int)0, (ModelResourceLocation)new ModelResourceLocation("super_solar_panels_rioutouscraft:" + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.name), null));
    }

    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return "super_solar_panels_rioutouscraft:textures/armour/" + this.name.toLowerCase(Locale.ROOT) + ".png";
    }

    public String getTranslationKey() {
        return "super_solar_panels_rioutouscraft." + super.getTranslationKey().substring(4);
    }

    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.UNCOMMON;
    }

    public static boolean isJetpackOn(ItemStack stack) {
        return StackUtil.getOrCreateNbtData((ItemStack)stack).getBoolean("isFlyActive");
    }

    public static boolean isHovering(ItemStack stack) {
        return StackUtil.getOrCreateNbtData((ItemStack)stack).getBoolean("hoverMode");
    }

    public static boolean switchJetpack(ItemStack stack) {
        NBTTagCompound nbt = StackUtil.getOrCreateNbtData((ItemStack)stack);
        boolean newMode = !nbt.getBoolean("isFlyActive");
        nbt.setBoolean("isFlyActive", newMode);
        return newMode;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        NBTTagCompound nbt = StackUtil.getOrCreateNbtData((ItemStack)stack);
        byte toggleTimer = nbt.getByte("toggleTimer");
        if (SSPKeys.isFlyKeyDown(player) && toggleTimer == 0) {
            toggleTimer = 10;
            nbt.setByte("toggleTimer", (byte)10);
            if (!world.isRemote) {
                String mode = ItemAdvancedElectricJetpack.switchJetpack(stack) ? TextFormatting.DARK_GREEN + Localization.translate((String)"gravisuite.message.on") : TextFormatting.DARK_RED + Localization.translate((String)"gravisuite.message.off");
            }
        }
        if (toggleTimer > 0 && !ItemAdvancedElectricJetpack.isJetpackOn(stack)) {
            toggleTimer = (byte)(toggleTimer - 1);
            nbt.setByte("toggleTimer", toggleTimer);
        }
        boolean ret = false;
        NBTTagCompound nbtData = StackUtil.getOrCreateNbtData((ItemStack)stack);
        boolean Nightvision = nbtData.getBoolean("Nightvision");
        short hubmode = nbtData.getShort("HudMode");
        if (SSPKeys.Isremovepoison2(player) && toggleTimer == 0) {
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
        BlockPos pos = new BlockPos((int)Math.floor(player.posX), (int)Math.floor(player.posY), (int)Math.floor(player.posZ));
        int skylight = player.getEntityWorld().getLightFromNeighbors(pos);
        if (skylight > 8) {
            if (!Configs.canCraftDoubleSlabs) return;
            player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 300, 0, true, true));
            return;
        } else {
            if (!Configs.canCraftDoubleSlabs) return;
            player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 300, 0, true, true));
        }
    }

    public boolean isJetpackActive(ItemStack stack) {
        return ItemAdvancedElectricJetpack.isJetpackOn(stack);
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
            if (!player.onGround) {
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
}

