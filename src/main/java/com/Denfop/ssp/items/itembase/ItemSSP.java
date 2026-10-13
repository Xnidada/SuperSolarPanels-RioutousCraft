/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.CaseFormat
 *  com.google.common.base.Function
 *  ic2.core.IC2
 *  ic2.core.init.Localization
 *  ic2.core.profile.Version
 *  ic2.core.ref.IItemModelProvider
 *  ic2.core.ref.ItemName
 *  ic2.core.util.StackUtil
 *  javax.annotation.Nullable
 *  net.minecraft.client.renderer.block.model.ModelResourceLocation
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.EnumFacing
 *  net.minecraftforge.client.model.ModelLoader
 *  net.minecraftforge.common.capabilities.Capability
 *  net.minecraftforge.common.capabilities.ICapabilityProvider
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.Denfop.ssp.items.itembase;

import com.google.common.base.CaseFormat;
import com.google.common.base.Function;
import ic2.core.IC2;
import ic2.core.init.Localization;
import ic2.core.profile.Version;
import ic2.core.ref.IItemModelProvider;
import ic2.core.ref.ItemName;
import ic2.core.util.StackUtil;
import java.util.IdentityHashMap;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSSP
extends Item
implements IItemModelProvider {
    private EnumRarity rarity = EnumRarity.COMMON;
    private Map<Capability<?>, Function<ItemStack, ?>> caps;
    protected String name;

    public ItemSSP(String string) {
        this.func_77637_a((CreativeTabs)IC2.tabIC2);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerModels(ItemName name) {
        ModelLoader.setCustomModelResourceLocation((Item)this, (int)0, (ModelResourceLocation)new ModelResourceLocation("super_solar_panels_rioutouscraft:" + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.name), null));
    }

    @SideOnly(value=Side.CLIENT)
    protected void registerModel(int meta, ItemName name) {
        ItemSSP.registerModel(this, meta, name, null);
    }

    @SideOnly(value=Side.CLIENT)
    protected void registerModel(int meta, ItemName name, String extraName) {
        ItemSSP.registerModel(this, meta, name, extraName);
    }

    @SideOnly(value=Side.CLIENT)
    public static void registerModel(Item item, int meta, ItemName name, String extraName) {
        ModelLoader.setCustomModelResourceLocation((Item)item, (int)meta, (ModelResourceLocation)ItemSSP.getModelLocation(name, extraName));
    }

    @SideOnly(value=Side.CLIENT)
    public static ModelResourceLocation getModelLocation(ItemName name, String string) {
        StringBuilder loc = new StringBuilder();
        loc.append("super_solar_panels_rioutouscraft");
        loc.append(':');
        return new ModelResourceLocation(loc.toString(), null);
    }

    @SideOnly(value=Side.CLIENT)
    public int getItemColor(ItemStack stack) {
        return 0xFFFFFF;
    }

    public String func_77658_a() {
        return "ic2." + super.func_77658_a().substring(5);
    }

    public String func_77667_c(ItemStack stack) {
        return this.func_77658_a();
    }

    public String func_77657_g(ItemStack stack) {
        return this.func_77667_c(stack);
    }

    public String func_77653_i(ItemStack stack) {
        return Localization.translate((String)this.func_77667_c(stack));
    }

    protected boolean func_194125_a(CreativeTabs tab) {
        return this.isEnabled() && super.func_194125_a(tab);
    }

    protected boolean isEnabled() {
        return Version.shouldEnable(((Object)((Object)this)).getClass());
    }

    public ItemSSP setRarity(EnumRarity rarity) {
        if (rarity == null) {
            throw new NullPointerException("null rarity");
        }
        this.rarity = rarity;
        return this;
    }

    public EnumRarity func_77613_e(ItemStack stack) {
        if (stack.func_77948_v() && this.rarity != EnumRarity.EPIC) {
            return EnumRarity.RARE;
        }
        return this.rarity;
    }

    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return ItemSSP.shouldReequip(oldStack, newStack, slotChanged);
    }

    public static boolean shouldReequip(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        if (!StackUtil.checkItemEquality((ItemStack)newStack, (ItemStack)oldStack)) {
            return true;
        }
        if (oldStack == null) {
            return false;
        }
        if (StackUtil.getSize((ItemStack)oldStack) != StackUtil.getSize((ItemStack)newStack)) {
            return true;
        }
        return slotChanged && StackUtil.checkItemEqualityStrict((ItemStack)oldStack, (ItemStack)newStack);
    }

    protected static int getRemainingUses(ItemStack stack) {
        return stack.func_77958_k() - stack.func_77952_i() + 1;
    }

    public <T> void addCapability(Capability<T> cap, Function<ItemStack, T> lookup) {
        if (this.caps == null) {
            this.caps = new IdentityHashMap();
        }
        assert (!this.caps.containsKey(cap));
        this.caps.put(cap, lookup);
    }

    public ICapabilityProvider initCapabilities(final ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new ICapabilityProvider(){

            public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
                return ItemSSP.this.caps != null && ItemSSP.this.caps.containsKey(capability);
            }

            public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
                return (T)(ItemSSP.this.caps == null || !ItemSSP.this.caps.containsKey(capability) ? null : ((Function)ItemSSP.this.caps.get(capability)).apply((Object)stack));
            }
        };
    }
}

