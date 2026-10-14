/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.CaseFormat
 *  ic2.api.item.ElectricItem
 *  ic2.api.item.HudMode
 *  ic2.api.item.IElectricItem
 *  ic2.api.item.IItemHudProvider
 *  ic2.api.item.IMetalArmor
 *  ic2.core.IC2
 *  ic2.core.IC2Potion
 *  ic2.core.init.BlocksItems
 *  ic2.core.init.Localization
 *  ic2.core.item.ElectricItemManager
 *  ic2.core.item.ItemTinCan
 *  ic2.core.ref.IItemModelProvider
 *  ic2.core.ref.ItemName
 *  ic2.core.util.StackUtil
 *  net.minecraft.client.renderer.block.model.ModelResourceLocation
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.MobEffects
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ActionResult
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumActionResult
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraftforge.client.model.ModelLoader
 *  net.minecraftforge.common.ISpecialArmor
 *  net.minecraftforge.common.ISpecialArmor$ArmorProperties
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.rioutouscraft.ssp.items.armor;

import com.rioutouscraft.ssp.SuperSolarPanels;

import com.rioutouscraft.ssp.Configs;
import com.rioutouscraft.ssp.tiles.TileEntitySolarPanel;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityAdvancedSolar;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityHybridSolar;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntitySingular;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntitySpectral;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityUltimateHybridSolar;
import com.google.common.base.CaseFormat;
import ic2.api.item.ElectricItem;
import ic2.api.item.HudMode;
import ic2.api.item.IElectricItem;
import ic2.api.item.IItemHudProvider;
import ic2.api.item.IMetalArmor;
import ic2.core.IC2;
import ic2.core.IC2Potion;
import ic2.core.init.BlocksItems;
import ic2.core.init.Localization;
import ic2.core.item.ElectricItemManager;
import ic2.core.item.ItemTinCan;
import ic2.core.ref.IItemModelProvider;
import ic2.core.ref.ItemName;
import ic2.core.util.StackUtil;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArmourSolarHelmet
extends ItemArmor
implements IItemModelProvider,
IElectricItem,
IMetalArmor,
ISpecialArmor,
IItemHudProvider {
    protected static final int DEFAULT_COLOUR = -1;
    protected final SolarHelmetTypes type;
    public static boolean chargeWholeInventory = false;
    protected TileEntitySolarPanel.GenerationState state = TileEntitySolarPanel.GenerationState.NONE;
    protected int ticker;
    protected static final Map<Potion, Integer> potionRemovalCost = new IdentityHashMap<Potion, Integer>();

    public ItemArmourSolarHelmet(SolarHelmetTypes type) {
        super(ItemArmor.ArmorMaterial.DIAMOND, -1, EntityEquipmentSlot.HEAD);
        ((ItemArmourSolarHelmet)BlocksItems.registerItem((Item)this, (ResourceLocation)new ResourceLocation("super_solar_panels_rioutouscraft", type.getName()))).setTranslationKey(type.getLocalisedName());
        this.setCreativeTab(SuperSolarPanels.SSPtab);
        this.setMaxDamage(27);
        this.type = type;
        if (this.armorType == EntityEquipmentSlot.HEAD) {
            MinecraftForge.EVENT_BUS.register((Object)this);
        }
        potionRemovalCost.put((Potion)IC2Potion.radiation, 5000);
    }

    public String getTranslationKey() {
        return "super_solar_panels_rioutouscraft." + super.getTranslationKey().substring(5);
    }

    public String getTranslationKey(ItemStack stack) {
        return this.getTranslationKey();
    }

    public String getItemStackDisplayName(ItemStack stack) {
        return Localization.translate((String)this.getTranslationKey(stack));
    }

    public int getMetadata(ItemStack stack) {
        return 0;
    }

    @SideOnly(value=Side.CLIENT)
    public void registerModels(ItemName name) {
        ModelLoader.setCustomModelResourceLocation((Item)this, (int)0, (ModelResourceLocation)new ModelResourceLocation("super_solar_panels_rioutouscraft:" + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.type.getName()), (String)null));
    }

    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return "super_solar_panels_rioutouscraft:textures/armour/" + (this.type.getName() + (type != null ? "Overlay" : "")).toLowerCase(Locale.ROOT) + ".png";
    }

    public boolean canBeDyed() {
        return this.type != SolarHelmetTypes.Spectral;
    }

    public void setColor(ItemStack stack, int colour) {
        this.getDisplayNbt(stack, true).setInteger("colour", colour);
    }

    public boolean hasColor(ItemStack stack) {
        return this.getColor(stack) != -1;
    }

    public int getColor(ItemStack stack) {
        NBTTagCompound nbt = this.getDisplayNbt(stack, false);
        if (nbt == null || !nbt.hasKey("colour", 3)) {
            return -1;
        }
        return nbt.getInteger("colour");
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
        if (!nbt.hasKey("display", 50)) {
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

    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        ItemTinCan can;
        if (this.HUDstuff(world.isRemote, player, stack)) {
            return;
        }
        if (this.type == SolarHelmetTypes.Spectral || this.type == SolarHelmetTypes.Singular
                || this.ticker++ % this.tickRate() == 0) {
            this.checkTheSky(world, player.getPosition());
        }
        if (this.type != SolarHelmetTypes.Spectral) {
            int airLevel = player.getAir();
            if (ElectricItem.manager.canUse(stack, 1000.0) && airLevel < 100) {
                player.setAir(airLevel + 200);
                ElectricItem.manager.use(stack, 1000.0, (EntityLivingBase)player);
            }
        }
        double output = 0.0;
        switch (this.state) {
            case DAY: {
                output = this.type.dayEU;
                break;
            }
            case NIGHT: {
                output = this.type.nightEU;
                break;
            }
            default: {
                return;
            }
        }
        if (this.type == SolarHelmetTypes.Spectral || this.type == SolarHelmetTypes.Singular) {
            output = this.chargeSelfThenInventory(player, stack, output);
        } else {
            for (ItemStack playerStack : player.inventory.armorInventory.subList(0, player.inventory.armorInventory.size() - 1)) {
                if (StackUtil.isEmpty((ItemStack)playerStack) || !(playerStack.getItem() instanceof IElectricItem) || (output -= ElectricItem.manager.charge(playerStack, output, this.type.tier, false, false)) > 0) continue;
                return;
            }
            if (chargeWholeInventory) {
                for (ItemStack playerStack : player.inventory.offHandInventory) {
                    if (StackUtil.isEmpty((ItemStack)playerStack) || !(playerStack.getItem() instanceof IElectricItem) || (output -= ElectricItem.manager.charge(playerStack, output, this.type.tier, false, false)) > 0) continue;
                    return;
                }
                for (ItemStack playerStack : player.inventory.mainInventory) {
                    if (StackUtil.isEmpty((ItemStack)playerStack) || !(playerStack.getItem() instanceof IElectricItem) || (output -= ElectricItem.manager.charge(playerStack, output, this.type.tier, false, false)) > 0) continue;
                    return;
                }
            }
        }
        NBTTagCompound nbtData = StackUtil.getOrCreateNbtData((ItemStack)stack);
        byte toggleTimer = nbtData.getByte("toggleTimer");
        boolean ret = false;
        ElectricItem.manager.charge(stack, (double)output, Integer.MAX_VALUE, true, false);
        int air = player.getAir();
        if (ElectricItem.manager.canUse(stack, 1000.0) && air < 100) {
            player.setAir(air + 200);
            ElectricItem.manager.use(stack, 1000.0, null);
            ret = true;
        } else if (air <= 0) {
            IC2.achievements.issueAchievement(player, "starveWithQHelmet");
        }
        if (ElectricItem.manager.canUse(stack, 1000.0) && player.getFoodStats().needFood()) {
            int slot = -1;
            for (int i = 0; i < player.inventory.mainInventory.size(); ++i) {
                ItemStack itemStack = (ItemStack)player.inventory.mainInventory.get(i);
            }
            if (slot > -1) {
                ItemStack playerStack2 = (ItemStack)player.inventory.mainInventory.get(slot);
                can = (ItemTinCan)playerStack2.getItem();
                ActionResult result = can.onEaten(player, playerStack2);
                if (StackUtil.isEmpty((ItemStack)(playerStack2 = (ItemStack)result.getResult()))) {
                    player.inventory.mainInventory.set(slot, StackUtil.emptyStack);
                }
                if (result.getType() == EnumActionResult.SUCCESS) {
                    ElectricItem.manager.use(stack, 1000.0, null);
                }
                ret = true;
            }
        } else if (player.getFoodStats().getFoodLevel() <= 0) {
            IC2.achievements.issueAchievement(player, "starveWithQHelmet");
        }
        boolean Nightvision = nbtData.getBoolean("Nightvision");
        short hubmode = nbtData.getShort("HudMode");
        if (IC2.keyboard.isAltKeyDown(player) && IC2.keyboard.isModeSwitchKeyDown(player) && toggleTimer == 0) {
            toggleTimer = 10;
            boolean bl = Nightvision = !Nightvision;
            if (IC2.platform.isSimulating()) {
                nbtData.setBoolean("Nightvision", Nightvision);
                if (Nightvision) {
                    IC2.platform.messagePlayer(player, "Nightvision enabled.", new Object[0]);
                } else {
                    IC2.platform.messagePlayer(player, "Nightvision disabled.", new Object[0]);
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
        if (Nightvision && IC2.platform.isSimulating() && ElectricItem.manager.use(stack, 1.0, (EntityLivingBase)player)) {
            BlockPos pos = new BlockPos((int)Math.floor(player.posX), (int)Math.floor(player.posY), (int)Math.floor(player.posZ));
            int skylight = player.getEntityWorld().getLightFromNeighbors(pos);
            if (skylight > 8) {
                IC2.platform.removePotion((EntityLivingBase)player, MobEffects.NIGHT_VISION);
            } else {
                player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 300, 0, true, true));
            }
            ret = true;
        }
        for (PotionEffect effect : new LinkedList<PotionEffect>(player.getActivePotionEffects())) {
            Potion potion = effect.getPotion();
            Integer cost = potionRemovalCost.get(potion);
            if (cost == null || !ElectricItem.manager.canUse(stack, (double)(cost = Integer.valueOf(cost * (effect.getAmplifier() + 1))).intValue())) continue;
            ElectricItem.manager.use(stack, (double)cost.intValue(), null);
            IC2.platform.removePotion((EntityLivingBase)player, potion);
        }
        player.removePotionEffect(MobEffects.POISON);
        player.removePotionEffect(MobEffects.UNLUCK);
        player.removePotionEffect(MobEffects.WITHER);
    }

    protected boolean HUDstuff(boolean isRemote, EntityPlayer player, ItemStack stack) {
        NBTTagCompound nbt = StackUtil.getOrCreateNbtData((ItemStack)stack);
        byte toggleTimer = nbt.getByte("toggleTimer");
        if (IC2.keyboard.isAltKeyDown(player) && IC2.keyboard.isHudModeKeyDown(player) && toggleTimer == 0) {
            byte hubmode = nbt.getByte("hudMode");
            toggleTimer = 10;
            hubmode = hubmode == HudMode.getMaxMode() ? (byte)0 : (byte)(hubmode + 1);
            if (!isRemote) {
                nbt.setByte("hudMode", hubmode);
                IC2.platform.messagePlayer(player, Localization.translate((String)HudMode.getFromID((int)hubmode).getTranslationKey()), new Object[0]);
            }
        }
        if (!isRemote && toggleTimer > 0) {
            NBTTagCompound nbtTagCompound = nbt;
            String s = "toggleTimer";
            toggleTimer = (byte)(toggleTimer - 1);
            nbtTagCompound.setByte("toggleTimer", toggleTimer);
        }
        return isRemote;
    }

    protected int tickRate() {
        return 128;
    }

    private double chargeSelfThenInventory(EntityPlayer player, ItemStack stack, double output) {
        if (output <= 0.0) return 0.0;
        output -= ElectricItem.manager.charge(stack, output, Integer.MAX_VALUE, true, false);
        if (ElectricItem.manager.getCharge(stack) < this.type.maxCharge || output <= 0.0) {
            return output;
        }
        for (ItemStack playerStack : player.inventory.armorInventory.subList(0, player.inventory.armorInventory.size() - 1)) {
            if (StackUtil.isEmpty((ItemStack)playerStack) || !(playerStack.getItem() instanceof IElectricItem)) continue;
            output -= ElectricItem.manager.charge(playerStack, output, this.type.tier, false, false);
            if (output <= 0.0) return 0.0;
        }
        for (ItemStack playerStack : player.inventory.offHandInventory) {
            if (StackUtil.isEmpty((ItemStack)playerStack) || !(playerStack.getItem() instanceof IElectricItem)) continue;
            output -= ElectricItem.manager.charge(playerStack, output, this.type.tier, false, false);
            if (output <= 0.0) return 0.0;
        }
        for (ItemStack playerStack : player.inventory.mainInventory) {
            if (StackUtil.isEmpty((ItemStack)playerStack) || !(playerStack.getItem() instanceof IElectricItem)) continue;
            output -= ElectricItem.manager.charge(playerStack, output, this.type.tier, false, false);
            if (output <= 0.0) return 0.0;
        }
        return output;
    }

    public void checkTheSky(World world, BlockPos pos) {
        if (!world.provider.hasSkyLight() || !world.canBlockSeeSky(pos)) {
            this.state = TileEntitySolarPanel.GenerationState.NONE;
        } else {
            this.state = world.isDaytime() ? TileEntitySolarPanel.GenerationState.DAY : TileEntitySolarPanel.GenerationState.NIGHT;
        }
    }

    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            ElectricItemManager.addChargeVariants((Item)this, items);
        }
    }

    public EnumRarity getRarity(ItemStack stack) {
        return this.type.rarity;
    }

    public boolean isMetalArmor(ItemStack stack, EntityPlayer player) {
        return true;
    }

    public int getItemEnchantability() {
        return 0;
    }

    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return false;
    }

    public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armour, DamageSource source, double damage, int slot) {
        if (source.isUnblockable()) {
            return new ISpecialArmor.ArmorProperties(0, 0.0, 0);
        }
        return new ISpecialArmor.ArmorProperties(0, 0.15 * this.type.damageAbsorptionRatio, (int)(25.0 * ElectricItem.manager.getCharge(armour) / (double)this.type.energyPerDamage));
    }

    public int getArmorDisplay(EntityPlayer player, ItemStack armour, int slot) {
        if (ElectricItem.manager.getCharge(armour) >= (double)this.type.energyPerDamage) {
            return (int)Math.round(3.0 * this.type.damageAbsorptionRatio);
        }
        return 0;
    }

    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
        ElectricItem.manager.discharge(stack, (double)(damage * this.type.energyPerDamage), Integer.MAX_VALUE, true, false, false);
    }

    public boolean canProvideEnergy(ItemStack stack) {
        return false;
    }

    public int getTier(ItemStack stack) {
        return this.type.tier;
    }

    public double getMaxCharge(ItemStack stack) {
        return this.type.maxCharge;
    }

    public double getTransferLimit(ItemStack stack) {
        return this.type.transferLimit;
    }

    public boolean doesProvideHUD(ItemStack stack) {
        return ElectricItem.manager.getCharge(stack) > 0.0;
    }

    public HudMode getHudMode(ItemStack stack) {
        return HudMode.getFromID((int)StackUtil.getOrCreateNbtData((ItemStack)stack).getByte("hudMode"));
    }

    public enum SolarHelmetTypes {
        Spectral(EnumRarity.EPIC, 5000, 0, 9, 10000, 10000, 2000, 9.0),
        Singular(EnumRarity.EPIC, 105000, 0, 9, 20000, 20000, 2000, 9.0),
        ADVANCED(EnumRarity.UNCOMMON, TileEntityAdvancedSolar.settings.dayPower, TileEntityAdvancedSolar.settings.nightPower, 3, Configs.maxCharge10, Configs.transferLimit10, 800, 0.9),
        HYBRID(EnumRarity.RARE, TileEntityHybridSolar.settings.dayPower, TileEntityHybridSolar.settings.nightPower, 4, Configs.maxCharge11, Configs.transferLimit11, 2000, 1.0),
        ULTIMATE(EnumRarity.EPIC, TileEntityUltimateHybridSolar.settings.dayPower, TileEntityUltimateHybridSolar.settings.nightPower, 4, Configs.maxCharge11, Configs.transferLimit11, 2000, 1.0);

        public final double maxCharge;
        public final double transferLimit;
        public final double damageAbsorptionRatio;
        public final double dayEU;
        public final double nightEU;
        public final int tier;
        public final int energyPerDamage;
        public final EnumRarity rarity;
        private final String name = name().toLowerCase(Locale.ENGLISH);

        SolarHelmetTypes(EnumRarity rarity, double dayEU, double nightEU, int tier, double maxCharge, double transferLimit, int energyPerDamage, double damageAbsorptionRatio) {
            this.rarity = rarity;
            this.dayEU = dayEU;
            this.nightEU = nightEU;
            this.tier = tier;
            this.maxCharge = maxCharge;
            this.transferLimit = transferLimit;
            this.energyPerDamage = energyPerDamage;
            this.damageAbsorptionRatio = damageAbsorptionRatio;
            assert damageAbsorptionRatio > 0.0;
        }

        public String getName() {
            return this.name + "SolarHelmet";
        }

        protected String getLocalisedName() {
            return "solar_helmets." + this.name;
        }
    }
}

