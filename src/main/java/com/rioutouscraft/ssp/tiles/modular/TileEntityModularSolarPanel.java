package com.rioutouscraft.ssp.tiles.modular;

import com.rioutouscraft.ssp.SuperSolarPanels;
import com.rioutouscraft.ssp.items.modular.ModularPanelBattery;
import com.rioutouscraft.ssp.items.modular.ModularPanelGlass;
import com.rioutouscraft.ssp.items.modular.ModularPanelOutput;
import com.rioutouscraft.ssp.gui.BackgroundlessDynamicGUI;
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
import ic2.core.gui.dynamic.DynamicContainer;
import ic2.core.gui.dynamic.GuiParser;
import ic2.core.gui.dynamic.IGuiValueProvider;
import ic2.core.network.GuiSynced;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public final class TileEntityModularSolarPanel extends TileEntityInventory
        implements IEnergySource, IHasGui, IGuiValueProvider {
    public static final int BASE_DAY_POWER = 32;
    public static final int BASE_NIGHT_POWER = 8;
    public static final int BASE_STORAGE = 50000;
    public static final int BASE_OUTPUT = 32;

    public final InvSlotPanelComponent glassSlot;
    public final InvSlotPanelComponent batterySlot;
    public final InvSlotPanelComponent outputSlot;
    @GuiSynced public int storage;
    @GuiSynced private GenerationState active = GenerationState.NONE;
    private int ticker;
    private boolean addedToEnergyNet;

    public TileEntityModularSolarPanel() {
        this.glassSlot = new InvSlotPanelComponent(this, "glass", InvSlotPanelComponent.Kind.GLASS);
        this.batterySlot = new InvSlotPanelComponent(this, "battery", InvSlotPanelComponent.Kind.BATTERY);
        this.outputSlot = new InvSlotPanelComponent(this, "output", InvSlotPanelComponent.Kind.OUTPUT);
    }

    @Override
    protected void onLoaded() {
        super.onLoaded();
        if (!world.isRemote) {
            addedToEnergyNet = !MinecraftForge.EVENT_BUS.post((Event) new EnergyTileLoadEvent((IEnergyTile) this));
            checkSky();
        }
    }

    @Override
    protected void onUnloaded() {
        super.onUnloaded();
        if (addedToEnergyNet) {
            MinecraftForge.EVENT_BUS.post((Event) new EnergyTileUnloadEvent((IEnergyTile) this));
            addedToEnergyNet = false;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        storage = nbt.getInteger("modularStorage");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("modularStorage", storage);
        return nbt;
    }

    @Override
    protected void updateEntityServer() {
        super.updateEntityServer();
        if (ticker++ % 32 == 0) checkSky();
        int generated = getGeneratedEU();
        if (generated > 0) storage = Math.min(getMaxStorage(), storage + generated);
    }

    private void checkSky() {
        if (world.provider.isNether() || !world.canBlockSeeSky(pos.up())) {
            active = GenerationState.NONE;
        } else if (world.isDaytime()) {
            active = GenerationState.DAY;
        } else {
            active = GenerationState.NIGHT;
        }
    }

    private int getGeneratedEU() {
        if (glassSlot.isEmpty()) return 0;
        ModularPanelGlass.Type glass = ModularPanelGlass.getPanelType(glassSlot.get());
        switch (active) {
            case DAY: return (int) Math.round(BASE_DAY_POWER * glass.dayMultiplier);
            case NIGHT: return (int) Math.round(BASE_NIGHT_POWER * glass.nightMultiplier);
            default: return 0;
        }
    }

    public int getMaxStorage() {
        return BASE_STORAGE + (batterySlot.isEmpty() ? 0 : ModularPanelBattery.getPanelType(batterySlot.get()).capacity);
    }

    public int getOutputLimit() {
        return BASE_OUTPUT + (outputSlot.isEmpty() ? 0 : ModularPanelOutput.getPanelType(outputSlot.get()).output);
    }

    public int getGeneratedDisplay() {
        return getGeneratedEU();
    }

    public String getGlassName() {
        return glassSlot.isEmpty() ? "none" : ModularPanelGlass.getPanelType(glassSlot.get()).getName();
    }

    public String getBatteryName() {
        return batterySlot.isEmpty() ? "none" : ModularPanelBattery.getPanelType(batterySlot.get()).getName();
    }

    public String getOutputName() {
        return outputSlot.isEmpty() ? "none" : ModularPanelOutput.getPanelType(outputSlot.get()).getName();
    }

    public String getModeName() {
        return active.name().toLowerCase(java.util.Locale.ROOT);
    }

    @Override
    public boolean emitsEnergyTo(IEnergyAcceptor receiver, EnumFacing side) {
        return true;
    }

    @Override
    public int getSourceTier() {
        return outputSlot.isEmpty() ? 2 : ModularPanelOutput.getPanelType(outputSlot.get()).tier;
    }

    @Override
    public double getOfferedEnergy() {
        return Math.min(storage, getOutputLimit());
    }

    @Override
    public void drawEnergy(double amount) {
        storage = Math.max(0, (int) (storage - amount));
    }

    @Override
    public ContainerBase<? extends TileEntityModularSolarPanel> getGuiContainer(EntityPlayer player) {
        return DynamicContainer.create(this, player, GuiParser.parse((ITeBlock) teBlock));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiScreen getGui(EntityPlayer player, boolean isAdmin) {
        return BackgroundlessDynamicGUI.create(this, player, GuiParser.parse((ITeBlock) teBlock));
    }

    @Override
    public void onGuiClosed(EntityPlayer player) {
    }

    @Override
    public double getGuiValue(String name) {
        if ("progress".equals(name)) return getMaxStorage() == 0 ? 0 : (double) storage / getMaxStorage();
        if ("generated".equals(name)) return getGeneratedDisplay();
        if ("output".equals(name)) return getOutputLimit();
        throw new IllegalArgumentException("Unexpected GUI value requested: " + name);
    }

    @Override
    public boolean getGuiState(String name) {
        if ("daylight".equals(name)) return active == GenerationState.DAY;
        if ("night".equals(name)) return active == GenerationState.NIGHT;
        return super.getGuiState(name);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, List<String> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, tooltip, flag);
        tooltip.add("Modular solar panel");
    }

    public enum GenerationState { NONE, DAY, NIGHT }
}
