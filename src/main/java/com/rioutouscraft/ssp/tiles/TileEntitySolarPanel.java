/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.api.energy.EnergyNet
 *  ic2.api.energy.event.EnergyTileLoadEvent
 *  ic2.api.energy.event.EnergyTileUnloadEvent
 *  ic2.api.energy.tile.IEnergyAcceptor
 *  ic2.api.energy.tile.IEnergySource
 *  ic2.api.energy.tile.IEnergyTile
 *  ic2.core.ContainerBase
 *  ic2.core.IHasGui
 *  ic2.core.block.ITeBlock
 *  ic2.core.block.TileEntityInventory
 *  ic2.core.block.invslot.InvSlot$Access
 *  ic2.core.gui.dynamic.DynamicContainer
 *  ic2.core.gui.dynamic.GuiParser
 *  ic2.core.gui.dynamic.GuiParser$GuiNode
 *  ic2.core.gui.dynamic.IGuiValueProvider
 *  ic2.core.init.Localization
 *  ic2.core.network.GuiSynced
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.EnumFacing
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.Event
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.rioutouscraft.ssp.tiles;

import com.rioutouscraft.ssp.gui.BackgroundlessDynamicGUI;
import com.rioutouscraft.ssp.tiles.modular.PanelModules;
import com.rioutouscraft.ssp.tiles.modular.PanelModuleMath;
import com.rioutouscraft.ssp.tiles.modular.InvSlotPanelComponent;
import com.rioutouscraft.ssp.tiles.InvSlotMultiCharge;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.energy.tile.IEnergyTile;
import ic2.core.ContainerBase;
import ic2.core.IHasGui;
import ic2.core.block.ITeBlock;
import ic2.core.block.TileEntityInventory;
import ic2.core.block.invslot.InvSlot;
import ic2.core.gui.dynamic.DynamicContainer;
import ic2.core.gui.dynamic.GuiParser;
import ic2.core.gui.dynamic.IGuiValueProvider;
import ic2.core.init.Localization;
import ic2.core.network.GuiSynced;
import java.util.List;
import java.util.Locale;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class TileEntitySolarPanel
extends TileEntityInventory
implements IEnergySource,
IHasGui,
IGuiValueProvider {
    public final double maxStorage;
    public final InvSlotPanelComponent glassSlot = new InvSlotPanelComponent(this, "glass", InvSlotPanelComponent.Kind.GLASS);
    public final InvSlotPanelComponent batterySlot = new InvSlotPanelComponent(this, "battery", InvSlotPanelComponent.Kind.BATTERY);
    public final InvSlotPanelComponent outputSlot = new InvSlotPanelComponent(this, "output", InvSlotPanelComponent.Kind.OUTPUT);
    public final PanelModules modules = new PanelModules(glassSlot, batterySlot, outputSlot);
    protected final double dayPower;
    protected final double nightPower;
    protected final int tier;
    protected final double output;
    protected final InvSlotMultiCharge chargeSlots;
    @GuiSynced
    public double storage;
    @GuiSynced
    protected GenerationState active = GenerationState.NONE;
    protected int ticker;
    protected boolean canRain;
    protected boolean hasSky;
    private boolean addedToEnet;

    public TileEntitySolarPanel(SolarConfig config) {
        this(config.dayPower, config.nightPower, config.storage, config.tier, config.output);
    }

    public TileEntitySolarPanel(double dayPower, double nightPower, double storage, int tier, double output) {
        this.dayPower = dayPower;
        this.nightPower = nightPower;
        this.maxStorage = storage;
        this.tier = tier;
        this.output = output;
        this.chargeSlots = new InvSlotMultiCharge(this, tier, 4, InvSlot.Access.IO);
    }

    protected void onLoaded() {
        super.onLoaded();
        if (!this.world.isRemote) {
            this.addedToEnet = !MinecraftForge.EVENT_BUS.post((Event)new EnergyTileLoadEvent((IEnergyTile)this));
            this.canRain = this.world.getBiome(this.pos).canRain() || this.world.getBiome(this.pos).getRainfall() > 0.0f;
            this.hasSky = !this.world.provider.isNether();
        }
    }

    protected void onUnloaded() {
        super.onUnloaded();
        if (this.addedToEnet) {
            this.addedToEnet = MinecraftForge.EVENT_BUS.post((Event)new EnergyTileUnloadEvent((IEnergyTile)this));
        }
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("storage", 6)) {
            this.storage = Math.max(0.0, nbt.getDouble("storage"));
        } else if (nbt.hasKey("storage", 4)) {
            this.storage = Math.max(0.0, nbt.getLong("storage"));
        } else {
            this.storage = Math.max(0.0, nbt.getInteger("storage"));
        }
        this.clampStorageToCapacity();
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        this.clampStorageToCapacity();
        super.writeToNBT(nbt);
        nbt.setDouble("storage", this.storage);
        return nbt;
    }

    protected void updateEntityServer() {
        super.updateEntityServer();
        this.clampStorageToCapacity();
        if (this.ticker++ % this.tickRate() == 0) {
            this.checkTheSky();
        }
        switch (this.active) {
            case DAY: {
                this.tryGenerateEnergy(this.dayPower, false);
                break;
            }
            case NIGHT: {
                this.tryGenerateEnergy(this.nightPower, false);
                break;
            }
            case RAIN: {
                this.tryGenerateEnergy(this.world.isDaytime() ? this.dayPower : this.nightPower, true);
            }
        }
        if (this.storage > 0) {
            this.storage = Math.max(0.0, this.storage - this.chargeSlots.charge(this.storage));
        }
    }

    protected int tickRate() {
        return 128;
    }

    public void checkTheSky() {
        if (!this.hasSky || !this.world.canBlockSeeSky(this.pos.up())) {
            this.active = GenerationState.NONE;
        } else if (this.canRain && (this.world.isRaining() || this.world.isThundering())) {
            this.active = GenerationState.RAIN;
        } else {
            this.active = this.world.isDaytime() ? GenerationState.DAY : GenerationState.NIGHT;
        }
    }

    public void tryGenerateEnergy(double amount, boolean raining) {
        boolean daylight = this.active == GenerationState.DAY
                || this.active == GenerationState.RAIN && this.world.isDaytime();
        this.storage = PanelModuleMath.store(this.storage, this.modules.generation(amount, daylight, raining), this.getMaxStorage());
    }

    public boolean emitsEnergyTo(IEnergyAcceptor receiver, EnumFacing side) {
        return true;
    }

    public int getSourceTier() {
        return this.modules.sourceTier(this.tier);
    }

    public double getOfferedEnergy() {
        this.clampStorageToCapacity();
        return Math.min(this.storage, this.getOutputLimit());
    }

    public void drawEnergy(double amount) {
        this.storage = Math.max(0.0, this.storage - amount);
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, tooltip, advanced);
        tooltip.add(Localization.translate((String)"ic2.item.tooltip.PowerTier", (Object[])new Object[]{this.tier}));
        tooltip.add(Localization.translate((String)"super_solar_panels_rioutouscraft.item.dayGeneration", (Object[])new Object[]{formatEnergy(this.dayPower)}));
        tooltip.add(Localization.translate((String)"super_solar_panels_rioutouscraft.item.nightGeneration", (Object[])new Object[]{formatEnergy(this.nightPower)}));
        tooltip.add(Localization.translate((String)"super_solar_panels_rioutouscraft.item.maxOutput", (Object[])new Object[]{formatEnergy(this.output)}));
        tooltip.add(Localization.translate((String)"super_solar_panels_rioutouscraft.item.storage", (Object[])new Object[]{formatEnergy(this.maxStorage)}));
    }

    public ContainerBase<? extends TileEntitySolarPanel> getGuiContainer(EntityPlayer player) {
        return DynamicContainer.create(this, (EntityPlayer)player, (GuiParser.GuiNode)GuiParser.parse((ITeBlock)this.teBlock));
    }

    @SideOnly(value=Side.CLIENT)
    public GuiScreen getGui(EntityPlayer player, boolean isAdmin) {
        return BackgroundlessDynamicGUI.create(this, player, GuiParser.parse((ITeBlock)this.teBlock));
    }

    public void onGuiClosed(EntityPlayer player) {
    }

    public double getGuiValue(String name) {
        if ("progress".equals(name)) {
            this.clampStorageToCapacity();
            return this.getMaxStorage() == 0 ? 0.0 : Math.min(1.0, (double) this.storage / this.getMaxStorage());
        }
        throw new IllegalArgumentException("Unexpected GUI value requested: " + name);
    }

    public boolean getGuiState(String name) {
        if ("sunlight".equals(name)) {
            return this.active == GenerationState.DAY;
        }
        if ("moonlight".equals(name)) {
            return this.active == GenerationState.NIGHT;
        }
        if ("rainlight".equals(name)) {
            return this.active == GenerationState.RAIN;
        }
        return super.getGuiState(name);
    }

    private void clampStorageToCapacity() {
        double capacity = this.getMaxStorage();
        if (Double.isNaN(this.storage) || this.storage < 0.0) {
            this.storage = 0.0;
        } else if (this.storage > capacity) {
            this.storage = capacity;
        }
    }

    public double getMaxStorage() {
        return this.modules.capacity(this.maxStorage);
    }

    public double getOutputLimit() {
        return this.modules.outputLimit(this.output, this.tier);
    }

    public String getMaxOutput() {
        return String.format(Locale.ROOT, "%s %s %s", Localization.translate((String)"super_solar_panels_rioutouscraft.gui.maxOutput"), formatEnergy(this.getOutputLimit()), Localization.translate((String)"ic2.generic.text.EUt"));
    }

    public String getOutput() {
        boolean daylight = this.active == GenerationState.DAY
                || this.active == GenerationState.RAIN && this.world.isDaytime();
        switch (this.active) {
            case DAY: {
                return String.format(Locale.ROOT, "%s %s %s", Localization.translate((String)"super_solar_panels_rioutouscraft.gui.generating"), formatEnergy(this.modules.generation(this.dayPower, daylight, false)), Localization.translate((String)"ic2.generic.text.EUt"));
            }
            case NIGHT: {
                return String.format(Locale.ROOT, "%s %s %s", Localization.translate((String)"super_solar_panels_rioutouscraft.gui.generating"), formatEnergy(this.modules.generation(this.nightPower, daylight, false)), Localization.translate((String)"ic2.generic.text.EUt"));
            }
            case RAIN: {
                return String.format(Locale.ROOT, "%s %s %s", Localization.translate((String)"super_solar_panels_rioutouscraft.gui.generating"), formatEnergy(this.modules.generation(this.world.isDaytime() ? this.dayPower : this.nightPower, daylight, true)), Localization.translate((String)"ic2.generic.text.EUt"));
            }
        }
        return String.format(Locale.ROOT, "%s 0 %s", Localization.translate((String)"super_solar_panels_rioutouscraft.gui.generating"), Localization.translate((String)"ic2.generic.text.EUt"));
    }

    private static String formatEnergy(double value) {
        if (value == Math.rint(value)) {
            return String.format(Locale.ROOT, "%.0f", value);
        }
        return String.format(Locale.ROOT, "%.2f", value).replaceAll("0+$", "").replaceAll("\\.$", "");
    }

    public static final class SolarConfig {
        public final double dayPower;
        public final double nightPower;
        final double storage;
        final int tier;
        final double output;

        public SolarConfig(double dayPower, double nightPower, double storage, int tier, double output) {
            this.dayPower = dayPower;
            this.nightPower = nightPower;
            this.storage = storage;
            this.tier = tier;
            this.output = output;
        }
    }

    public static enum GenerationState {
        NONE,
        NIGHT,
        DAY,
        RAIN;

    }
}

