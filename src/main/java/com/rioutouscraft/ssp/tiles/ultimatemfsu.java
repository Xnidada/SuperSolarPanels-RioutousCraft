/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.api.tile.IEnergyStorage
 *  ic2.core.ContainerBase
 *  ic2.core.IHasGui
 *  ic2.core.block.TileEntityBlock
 *  ic2.core.block.TileEntityInventory
 *  ic2.core.block.comp.Energy
 *  ic2.core.block.comp.TileEntityComponent
 *  ic2.core.block.invslot.InvSlot
 *  ic2.core.block.invslot.InvSlot$Access
 *  ic2.core.block.invslot.InvSlot$InvSide
 *  ic2.core.block.invslot.InvSlotCharge
 *  ic2.core.block.invslot.InvSlotDischarge
 *  ic2.core.gui.dynamic.DynamicContainer
 *  ic2.core.gui.dynamic.DynamicGui
 *  ic2.core.gui.dynamic.GuiParser
 *  ic2.core.gui.dynamic.GuiParser$GuiNode
 *  ic2.core.init.Localization
 *  ic2.core.init.MainConfig
 *  ic2.core.ref.TeBlock$DefaultDrop
 *  ic2.core.util.Config
 *  ic2.core.util.ConfigUtil
 *  ic2.core.util.StackUtil
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.rioutouscraft.ssp.tiles;

import com.rioutouscraft.ssp.Configs;
import ic2.api.tile.IEnergyStorage;
import ic2.core.ContainerBase;
import ic2.core.IHasGui;
import ic2.core.block.TileEntityBlock;
import ic2.core.block.TileEntityInventory;
import ic2.core.block.comp.Energy;
import ic2.core.block.comp.TileEntityComponent;
import ic2.core.block.invslot.InvSlot;
import ic2.core.block.invslot.InvSlotCharge;
import ic2.core.block.invslot.InvSlotDischarge;
import ic2.core.gui.dynamic.DynamicContainer;
import ic2.core.gui.dynamic.DynamicGui;
import ic2.core.gui.dynamic.GuiParser;
import ic2.core.init.Localization;
import ic2.core.init.MainConfig;
import ic2.core.ref.TeBlock;
import ic2.core.util.Config;
import ic2.core.util.ConfigUtil;
import ic2.core.util.StackUtil;
import java.util.EnumSet;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ultimatemfsu
extends TileEntityInventory
implements IEnergyStorage,
IHasGui {
    private int output = Configs.ultimatemfsu;
    protected final InvSlotCharge chargeSlot = new InvSlotCharge((TileEntityInventory)this, 5);
    protected final InvSlotDischarge dischargeSlot = new InvSlotDischarge((TileEntityInventory)this, InvSlot.Access.IO, 5, InvSlot.InvSide.BOTTOM);
    private Energy energy = (Energy)this.addComponent((TileEntityComponent)new Energy((TileEntityBlock)this, (double)Configs.ultimatemfsu2, EnumSet.complementOf(EnumSet.of(EnumFacing.DOWN)), EnumSet.of(EnumFacing.DOWN), 5, Configs.ultimatemfsu1, false).addManagedSlot((InvSlot)this.chargeSlot).addManagedSlot((InvSlot)this.dischargeSlot));

    protected void updateEntityServer() {
        super.updateEntityServer();
        if (this.energy.getEnergy() > this.energy.getCapacity()) {
            this.energy.addEnergy(this.energy.getEnergy() - this.energy.getCapacity());
        }
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.energy.setDirections(EnumSet.complementOf(EnumSet.of(this.getFacing())), EnumSet.of(this.getFacing()));
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        return nbt;
    }

    public ContainerBase<?> getGuiContainer(EntityPlayer player) {
        try {
            return DynamicContainer.create((IInventory)this, (EntityPlayer)player, (GuiParser.GuiNode)GuiParser.parse((ResourceLocation)new ResourceLocation("super_solar_panels_rioutouscraft", "guidef/ultimatemfsu.xml"), (Class)this.teBlock.getTeClass()));
        }
        catch (Exception exception) {
            return null;
        }
    }

    @SideOnly(value=Side.CLIENT)
    public GuiScreen getGui(EntityPlayer player, boolean b) {
        try {
            return DynamicGui.create((IInventory)this, (EntityPlayer)player, (GuiParser.GuiNode)GuiParser.parse((ResourceLocation)new ResourceLocation("super_solar_panels_rioutouscraft", "guidef/ultimatemfsu.xml"), (Class)this.teBlock.getTeClass()));
        }
        catch (Exception exception) {
            return null;
        }
    }

    public int getStored() {
        return (int)this.energy.getEnergy();
    }

    public void setStored(int energy) {
    }

    public int addEnergy(int amount) {
        this.energy.addEnergy((double)amount);
        return amount;
    }

    public int getCapacity() {
        return (int)this.energy.getCapacity();
    }

    public int getOutput() {
        return this.output;
    }

    public double getOutputEnergyUnitsPerTick() {
        return this.output;
    }

    public boolean isTeleporterCompatible(EnumFacing side) {
        return true;
    }

    protected ItemStack adjustDrop(ItemStack drop, boolean wrench) {
        drop = super.adjustDrop(drop, wrench);
        if (wrench || this.teBlock.getDefaultDrop() == TeBlock.DefaultDrop.Self) {
            double retainedRatio = ConfigUtil.getDouble((Config)MainConfig.get(), (String)"balance/energyRetainedInStorageBlockDrops");
            double totalEnergy = this.energy.getEnergy();
            if (retainedRatio > 0.0 && totalEnergy > 0.0) {
                NBTTagCompound nbt = StackUtil.getOrCreateNbtData((ItemStack)drop);
                nbt.setDouble("energy", (double)Math.round(totalEnergy * retainedRatio));
            }
        }
        return drop;
    }

    public void onPlaced(ItemStack stack, EntityLivingBase placer, EnumFacing facing) {
        super.onPlaced(stack, placer, facing);
        if (!this.getWorld().isRemote) {
            NBTTagCompound nbt = StackUtil.getOrCreateNbtData((ItemStack)stack);
            this.energy.addEnergy(nbt.getDouble("energy"));
        }
    }

    public void setFacing(EnumFacing facing) {
        super.setFacing(facing);
        this.energy.setDirections(EnumSet.complementOf(EnumSet.of(this.getFacing())), EnumSet.of(this.getFacing()));
    }

    public void onGuiClosed(EntityPlayer player) {
    }

    public String getStorageText() {
        return Localization.translate((String)"gui.text.maxStorage", (Object[])new Object[]{(long)this.energy.getEnergy()});
    }

    public String getCapacityText() {
        return Localization.translate((String)"gui.text.currentStorage", (Object[])new Object[]{(long)this.energy.getCapacity()});
    }

    public String getOutputText() {
        return Localization.translate((String)"gui.text.output", (Object[])new Object[]{this.output});
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, tooltip, advanced);
        tooltip.add(String.format("%s %s %s %s %s %s", Localization.translate((String)"ic2.item.tooltip.Output"), (long)this.output, Localization.translate((String)"ic2.generic.text.EUt"), Localization.translate((String)"ic2.item.tooltip.Capacity"), (long)this.energy.getCapacity(), Localization.translate((String)"ic2.generic.text.EU")));
        tooltip.add(Localization.translate((String)"ic2.item.tooltip.Store") + " " + StackUtil.getOrCreateNbtData((ItemStack)stack).getDouble("energy") + " " + Localization.translate((String)"ic2.generic.text.EU"));
    }
}

