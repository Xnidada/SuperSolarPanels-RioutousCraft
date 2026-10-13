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
package com.Denfop.ssp.items.armorbase;

import com.Denfop.ssp.Configs;
import com.Denfop.ssp.keyboard.SSPKeys;
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
        this.name = name;
        ((ItemAdvancedElectricJetpack)BlocksItems.registerItem((Item)this, (ResourceLocation)new ResourceLocation("super_solar_panels_rioutouscraft", this.name))).func_77655_b(name);
        this.func_77656_e(27);
        this.func_77625_d(1);
        this.setNoRepair();
    }

    @SideOnly(value=Side.CLIENT)
    public void registerModels(ItemName name) {
        ModelLoader.setCustomModelResourceLocation((Item)this, (int)0, (ModelResourceLocation)new ModelResourceLocation("super_solar_panels_rioutouscraft:" + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.name), null));
    }

    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return "super_solar_panels_rioutouscraft:textures/armour/" + this.name + ".png";
    }

    public String getTranslationKey() {
        return "ssp." + super.func_77658_a().substring(4);
    }

    public EnumRarity func_77613_e(ItemStack stack) {
        return EnumRarity.UNCOMMON;
    }

    public static boolean isJetpackOn(ItemStack stack) {
        return StackUtil.getOrCreateNbtData((ItemStack)stack).func_74767_n("isFlyActive");
    }

    public static boolean isHovering(ItemStack stack) {
        return StackUtil.getOrCreateNbtData((ItemStack)stack).func_74767_n("hoverMode");
    }

    public static boolean switchJetpack(ItemStack stack) {
        NBTTagCompound nbt = StackUtil.getOrCreateNbtData((ItemStack)stack);
        boolean newMode = !nbt.func_74767_n("isFlyActive");
        nbt.func_74757_a("isFlyActive", newMode);
        return newMode;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        NBTTagCompound nbt = StackUtil.getOrCreateNbtData((ItemStack)stack);
        byte toggleTimer = nbt.func_74771_c("toggleTimer");
        if (SSPKeys.isFlyKeyDown(player) && toggleTimer == 0) {
            toggleTimer = 10;
            nbt.func_74774_a("toggleTimer", (byte)10);
            if (!world.field_72995_K) {
                String mode = ItemAdvancedElectricJetpack.switchJetpack(stack) ? TextFormatting.DARK_GREEN + Localization.translate((String)"gravisuite.message.on") : TextFormatting.DARK_RED + Localization.translate((String)"gravisuite.message.off");
            }
        }
        if (toggleTimer > 0 && !ItemAdvancedElectricJetpack.isJetpackOn(stack)) {
            toggleTimer = (byte)(toggleTimer - 1);
            nbt.func_74774_a("toggleTimer", toggleTimer);
        }
        boolean ret = false;
        NBTTagCompound nbtData = StackUtil.getOrCreateNbtData((ItemStack)stack);
        boolean Nightvision = nbtData.func_74767_n("Nightvision");
        short hubmode = nbtData.func_74765_d("HudMode");
        if (SSPKeys.Isremovepoison2(player) && toggleTimer == 0) {
            toggleTimer = 10;
            boolean bl = Nightvision = !Nightvision;
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
            if (!Configs.canCraftDoubleSlabs) return;
            player.func_70690_d(new PotionEffect(MobEffects.field_76426_n, 300, 0, true, true));
            return;
        } else {
            if (!Configs.canCraftDoubleSlabs) return;
            player.func_70690_d(new PotionEffect(MobEffects.field_76426_n, 300, 0, true, true));
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
}

