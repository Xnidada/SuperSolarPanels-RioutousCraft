/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.api.recipe.IRecipeInput
 *  ic2.api.recipe.MachineRecipeResult
 *  ic2.api.recipe.Recipes
 *  ic2.api.upgrade.IUpgradableBlock
 *  ic2.api.upgrade.UpgradableProperty
 *  ic2.core.ContainerBase
 *  ic2.core.IC2
 *  ic2.core.IHasGui
 *  ic2.core.audio.AudioSource
 *  ic2.core.audio.PositionSpec
 *  ic2.core.block.TileEntityBlock
 *  ic2.core.block.TileEntityInventory
 *  ic2.core.block.comp.Fluids
 *  ic2.core.block.comp.Redstone
 *  ic2.core.block.comp.Redstone$IRedstoneChangeHandler
 *  ic2.core.block.comp.TileEntityComponent
 *  ic2.core.block.invslot.InvSlot
 *  ic2.core.block.invslot.InvSlot$Access
 *  ic2.core.block.invslot.InvSlot$InvSide
 *  ic2.core.block.invslot.InvSlotConsumableLiquid
 *  ic2.core.block.invslot.InvSlotConsumableLiquid$OpType
 *  ic2.core.block.invslot.InvSlotConsumableLiquidByList
 *  ic2.core.block.invslot.InvSlotOutput
 *  ic2.core.block.invslot.InvSlotProcessable
 *  ic2.core.block.invslot.InvSlotUpgrade
 *  ic2.core.block.machine.tileentity.TileEntityElectricMachine
 *  ic2.core.network.GuiSynced
 *  ic2.core.network.NetworkManager
 *  ic2.core.profile.NotClassic
 *  ic2.core.recipe.MatterAmplifierRecipeManager
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.fluids.Fluid
 *  net.minecraftforge.fluids.FluidStack
 *  net.minecraftforge.fluids.FluidTank
 *  net.minecraftforge.fluids.IFluidTank
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.Denfop.ssp.tiles.neutronfabricator;

import com.Denfop.ssp.Configs;
import com.Denfop.ssp.fluid.Neutron.FluidRegister;
import com.Denfop.ssp.tiles.neutronfabricator.ContainerMatter;
import com.Denfop.ssp.tiles.neutronfabricator.GuiMatter;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.MachineRecipeResult;
import ic2.api.recipe.Recipes;
import ic2.api.upgrade.IUpgradableBlock;
import ic2.api.upgrade.UpgradableProperty;
import ic2.core.ContainerBase;
import ic2.core.IC2;
import ic2.core.IHasGui;
import ic2.core.audio.AudioSource;
import ic2.core.audio.PositionSpec;
import ic2.core.block.TileEntityBlock;
import ic2.core.block.TileEntityInventory;
import ic2.core.block.comp.Fluids;
import ic2.core.block.comp.Redstone;
import ic2.core.block.comp.TileEntityComponent;
import ic2.core.block.invslot.InvSlot;
import ic2.core.block.invslot.InvSlotConsumableLiquid;
import ic2.core.block.invslot.InvSlotConsumableLiquidByList;
import ic2.core.block.invslot.InvSlotOutput;
import ic2.core.block.invslot.InvSlotProcessable;
import ic2.core.block.invslot.InvSlotUpgrade;
import ic2.core.block.machine.tileentity.TileEntityElectricMachine;
import ic2.core.network.GuiSynced;
import ic2.core.network.NetworkManager;
import ic2.core.profile.NotClassic;
import ic2.core.recipe.MatterAmplifierRecipeManager;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@NotClassic
public class TileEntityMassFabricator
extends TileEntityElectricMachine
implements IHasGui,
IUpgradableBlock {
    public int scrap = 0;
    private double lastEnergy;
    private static final int StateIdle = 0;
    private static final int StateRunning = 1;
    private static final int StateRunningScrap = 2;
    private int state = 0;
    private int prevState = 0;
    public boolean redstonePowered = false;
    private AudioSource audioSource;
    private AudioSource audioSourceScrap;
    public final InvSlotUpgrade upgradeSlot;
    public final InvSlotProcessable<IRecipeInput, Integer, ItemStack> amplifierSlot = new InvSlotProcessable<IRecipeInput, Integer, ItemStack>((TileEntityInventory)this, "scrap", 1, Recipes.matterAmplifier){

        protected ItemStack getInput(ItemStack stack) {
            return stack;
        }

        protected void setInput(ItemStack input) {
            this.put(input);
        }
    };
    public final InvSlotOutput outputSlot = new InvSlotOutput((TileEntityInventory)this, "output", 1);
    public final InvSlotConsumableLiquid containerslot = new InvSlotConsumableLiquidByList((TileEntityInventory)this, "container", InvSlot.Access.I, 1, InvSlot.InvSide.TOP, InvSlotConsumableLiquid.OpType.Fill, new Fluid[]{FluidRegister.Neutron});
    @GuiSynced
    public final FluidTank fluidTank;
    protected final Redstone redstone;
    protected final Fluids fluids;

    public TileEntityMassFabricator() {
        super(Math.round(Configs.Neutronfabricator), Configs.Neutronfabricator1);
        this.upgradeSlot = new InvSlotUpgrade((TileEntityInventory)this, "upgrade", 4);
        this.redstone = (Redstone)this.addComponent((TileEntityComponent)new Redstone((TileEntityBlock)this));
        this.redstone.subscribe(new Redstone.IRedstoneChangeHandler(){

            public void onRedstoneChange(int newLevel) {
                TileEntityMassFabricator.this.energy.setEnabled(newLevel == 0);
            }
        });
        this.fluids = (Fluids)this.addComponent((TileEntityComponent)new Fluids((TileEntityBlock)this));
        this.fluidTank = this.fluids.addTank("fluidTank", 10000, Fluids.fluidPredicate((Fluid[])new Fluid[]{FluidRegister.Neutron}));
        this.comparator.setUpdate(() -> {
            int count = TileEntityMassFabricator.calcRedstoneFromInvSlots((InvSlot[])new InvSlot[]{this.amplifierSlot});
            return count > 0 ? count : (this.scrap > 0 ? 1 : 0);
        });
    }

    public static void init() {
        Recipes.matterAmplifier = new MatterAmplifierRecipeManager();
    }

    public static void addAmplifier(ItemStack input, int amount, int amplification) {
        TileEntityMassFabricator.addAmplifier(Recipes.inputFactory.forStack(input, amount), amplification);
    }

    public static void addAmplifier(String input, int amount, int amplification) {
        TileEntityMassFabricator.addAmplifier(Recipes.inputFactory.forOreDict(input, amount), amplification);
    }

    public static void addAmplifier(IRecipeInput input, int amplification) {
        Recipes.matterAmplifier.addRecipe((Object)input, (Object)amplification, null, false);
    }

    public void func_145839_a(NBTTagCompound nbt) {
        super.func_145839_a(nbt);
        this.scrap = nbt.func_74762_e("scrap");
        this.lastEnergy = nbt.func_74769_h("lastEnergy");
    }

    public NBTTagCompound func_189515_b(NBTTagCompound nbt) {
        super.func_189515_b(nbt);
        nbt.func_74768_a("scrap", this.scrap);
        nbt.func_74780_a("lastEnergy", this.lastEnergy);
        return nbt;
    }

    protected void onLoaded() {
        super.onLoaded();
        if (!this.func_145831_w().field_72995_K) {
            this.setUpgradestat();
        }
    }

    protected void onUnloaded() {
        if (IC2.platform.isRendering() && this.audioSource != null) {
            IC2.audioManager.removeSources((Object)this);
            this.audioSource = null;
            this.audioSourceScrap = null;
        }
        super.onUnloaded();
    }

    protected void updateEntityServer() {
        super.updateEntityServer();
        this.redstonePowered = false;
        boolean needsInvUpdate = false;
        needsInvUpdate |= this.upgradeSlot.tickNoMark();
        if (this.redstone.hasRedstoneInput() || this.energy.getEnergy() <= 0.0) {
            this.setState(0);
            this.setActive(false);
        } else {
            MachineRecipeResult recipe;
            if (this.scrap > 0) {
                double bonus = Math.min((double)this.scrap, this.energy.getEnergy() - this.lastEnergy);
                if (bonus > 0.0) {
                    this.energy.forceAddEnergy(5.0 * bonus);
                    this.scrap = (int)((double)this.scrap - bonus);
                }
                this.setState(2);
            } else {
                this.setState(1);
            }
            this.setActive(true);
            if (this.scrap < 10000 && (recipe = this.amplifierSlot.process()) != null) {
                this.amplifierSlot.consume(recipe);
                this.scrap += ((Integer)recipe.getOutput()).intValue();
            }
            if (this.energy.getEnergy() >= this.energy.getCapacity()) {
                needsInvUpdate = this.attemptGeneration();
            }
            this.lastEnergy = this.energy.getEnergy();
            if (needsInvUpdate |= this.containerslot.processFromTank((IFluidTank)this.fluidTank, this.outputSlot)) {
                this.func_70296_d();
            }
        }
    }

    public boolean amplificationIsAvailable() {
        if (this.scrap > 0) {
            return true;
        }
        MachineRecipeResult recipe = this.amplifierSlot.process();
        return recipe != null && (Integer)recipe.getOutput() > 0;
    }

    public boolean attemptGeneration() {
        if (this.fluidTank.getFluidAmount() + 1 > this.fluidTank.getCapacity()) {
            return false;
        }
        this.fluidTank.fillInternal(new FluidStack((Fluid)FluidRegister.Neutron, 1), true);
        this.energy.useEnergy(this.energy.getCapacity());
        return true;
    }

    public String getProgressAsString() {
        int p = (int)Math.min(100.0 * this.energy.getFillRatio(), 100.0);
        return "" + p + "%";
    }

    public ContainerBase<TileEntityMassFabricator> getGuiContainer(EntityPlayer player) {
        return new ContainerMatter(player, this);
    }

    @SideOnly(value=Side.CLIENT)
    public GuiScreen getGui(EntityPlayer player, boolean isAdmin) {
        return new GuiMatter(new ContainerMatter(player, this));
    }

    public void onGuiClosed(EntityPlayer player) {
    }

    private void setState(int aState) {
        this.state = aState;
        if (this.prevState != this.state) {
            ((NetworkManager)IC2.network.get(true)).updateTileEntityField((TileEntity)this, "state");
        }
        this.prevState = this.state;
    }

    public List<String> getNetworkedFields() {
        ArrayList<String> ret = new ArrayList<String>();
        ret.add("state");
        ret.addAll(super.getNetworkedFields());
        return ret;
    }

    public void onNetworkUpdate(String field) {
        if (field.equals("state") && this.prevState != this.state) {
            switch (this.state) {
                case 0: {
                    if (this.audioSource != null) {
                        this.audioSource.stop();
                    }
                    if (this.audioSourceScrap == null) break;
                    this.audioSourceScrap.stop();
                    break;
                }
                case 1: {
                    if (this.audioSource == null) {
                        this.audioSource = IC2.audioManager.createSource((Object)this, PositionSpec.Center, "Generators/MassFabricator/MassFabLoop.ogg", true, false, IC2.audioManager.getDefaultVolume());
                    }
                    if (this.audioSource != null) {
                        this.audioSource.play();
                    }
                    if (this.audioSourceScrap == null) break;
                    this.audioSourceScrap.stop();
                    break;
                }
                case 2: {
                    if (this.audioSource == null) {
                        this.audioSource = IC2.audioManager.createSource((Object)this, PositionSpec.Center, "Generators/MassFabricator/MassFabLoop.ogg", true, false, IC2.audioManager.getDefaultVolume());
                    }
                    if (this.audioSourceScrap == null) {
                        this.audioSourceScrap = IC2.audioManager.createSource((Object)this, PositionSpec.Center, "Generators/MassFabricator/MassFabScrapSolo.ogg", true, false, IC2.audioManager.getDefaultVolume());
                    }
                    if (this.audioSource != null) {
                        this.audioSource.play();
                    }
                    if (this.audioSourceScrap == null) break;
                    this.audioSourceScrap.play();
                }
            }
            this.prevState = this.state;
        }
        super.onNetworkUpdate(field);
    }

    public void func_70296_d() {
        super.func_70296_d();
        if (IC2.platform.isSimulating()) {
            this.setUpgradestat();
        }
    }

    public void setUpgradestat() {
        this.upgradeSlot.onChanged();
        this.energy.setSinkTier(TileEntityMassFabricator.applyModifier(14, this.upgradeSlot.extraTier, 1.0));
    }

    private static int applyModifier(int base, int extra, double multiplier) {
        double ret = Math.round((double)(base + extra) * multiplier);
        return ret > 2.147483647E9 ? Integer.MAX_VALUE : (int)ret;
    }

    public double getEnergy() {
        return this.energy.getEnergy();
    }

    public boolean useEnergy(double amount) {
        return this.energy.useEnergy(amount);
    }

    public Set<UpgradableProperty> getUpgradableProperties() {
        return EnumSet.of(UpgradableProperty.RedstoneSensitive, new UpgradableProperty[]{UpgradableProperty.Transformer, UpgradableProperty.ItemConsuming, UpgradableProperty.ItemProducing, UpgradableProperty.FluidProducing, UpgradableProperty.Processing});
    }
}

