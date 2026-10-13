/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.CaseFormat
 *  ic2.api.reactor.IReactor
 *  ic2.api.reactor.IReactorComponent
 *  ic2.core.IC2Potion
 *  ic2.core.init.BlocksItems
 *  ic2.core.item.armor.ItemArmorHazmat
 *  ic2.core.ref.ItemName
 *  net.minecraft.client.renderer.block.model.ModelResourceLocation
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.client.model.ModelLoader
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.Denfop.ssp.items.reactors;

import com.Denfop.ssp.items.SSP_Items;
import com.Denfop.ssp.items.itembase.AbstractDamageableReactorComponent;
import com.Denfop.ssp.items.resource.CraftingThings;
import com.google.common.base.CaseFormat;
import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorComponent;
import ic2.core.IC2Potion;
import ic2.core.init.BlocksItems;
import ic2.core.item.armor.ItemArmorHazmat;
import ic2.core.ref.ItemName;
import java.util.ArrayDeque;
import java.util.Collection;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemReactorUranium
extends AbstractDamageableReactorComponent {
    public int numberOfCells;

    public ItemReactorUranium(String name, int cells) {
        this(name, cells, 20000);
    }

    @Override
    public String func_77658_a() {
        return "super_solar_panels_rioutouscraft." + super.func_77658_a().substring(4);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void registerModels(ItemName name) {
        ModelLoader.setCustomModelResourceLocation((Item)this, (int)0, (ModelResourceLocation)new ModelResourceLocation("super_solar_panels_rioutouscraft:" + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.name), null));
    }

    public String setUnlocalizedName() {
        return "super_solar_panels_rioutouscraft." + super.func_77658_a().substring(4);
    }

    protected ItemReactorUranium(String name, int cells, int duration) {
        super(name, duration);
        this.func_77625_d(64);
        this.numberOfCells = cells;
        this.name = name;
        ((ItemReactorUranium)BlocksItems.registerItem((Item)this, (ResourceLocation)new ResourceLocation("super_solar_panels_rioutouscraft", this.name))).func_77655_b(name);
    }

    public int getMetadata(ItemStack stack) {
        return this.getCustomDamage(stack) > 0 ? 1 : 0;
    }

    @Override
    public void processChamber(ItemStack stack, IReactor reactor, int x, int y, boolean heatRun) {
        if (!reactor.produceEnergy()) {
            return;
        }
        int basePulses = 1 + this.numberOfCells / 2;
        for (int iteration = 0; iteration < this.numberOfCells; ++iteration) {
            int dheat;
            int pulses = basePulses;
            if (!heatRun) {
                for (int i = 0; i < pulses; ++i) {
                    this.acceptUraniumPulse(stack, reactor, stack, x, y, x, y, heatRun);
                }
                pulses += ItemReactorUranium.checkPulseable(reactor, x - 1, y, stack, x, y, heatRun) + ItemReactorUranium.checkPulseable(reactor, x + 1, y, stack, x, y, heatRun) + ItemReactorUranium.checkPulseable(reactor, x, y - 1, stack, x, y, heatRun) + ItemReactorUranium.checkPulseable(reactor, x, y + 1, stack, x, y, heatRun);
                continue;
            }
            int heat = ItemReactorUranium.triangularNumber(pulses += ItemReactorUranium.checkPulseable(reactor, x - 1, y, stack, x, y, heatRun) + ItemReactorUranium.checkPulseable(reactor, x + 1, y, stack, x, y, heatRun) + ItemReactorUranium.checkPulseable(reactor, x, y - 1, stack, x, y, heatRun) + ItemReactorUranium.checkPulseable(reactor, x, y + 1, stack, x, y, heatRun)) * 4;
            ArrayDeque<ItemStackCoord> heatAcceptors = new ArrayDeque<ItemStackCoord>();
            this.checkHeatAcceptor(reactor, x - 1, y, heatAcceptors);
            this.checkHeatAcceptor(reactor, x + 1, y, heatAcceptors);
            this.checkHeatAcceptor(reactor, x, y - 1, heatAcceptors);
            this.checkHeatAcceptor(reactor, x, y + 1, heatAcceptors);
            for (heat = this.getFinalHeat(stack, reactor, x, y, heat); !heatAcceptors.isEmpty() && heat > 0; heat += dheat) {
                dheat = heat / heatAcceptors.size();
                heat -= dheat;
                ItemStackCoord acceptor = (ItemStackCoord)heatAcceptors.remove();
                IReactorComponent acceptorComp = (IReactorComponent)acceptor.stack.func_77973_b();
                dheat = acceptorComp.alterHeat(acceptor.stack, reactor, acceptor.x, acceptor.y, dheat);
            }
            if (heat <= 0) continue;
            reactor.addHeat(heat);
        }
        if (!heatRun && this.getCustomDamage(stack) >= this.getMaxCustomDamage(stack) - 1) {
            reactor.setItemAt(x, y, this.getDepletedStack(stack, reactor));
        } else if (!heatRun) {
            this.applyCustomDamage(stack, 1, null);
        }
    }

    protected int getFinalHeat(ItemStack stack, IReactor reactor, int x, int y, int heat) {
        return heat;
    }

    protected ItemStack getDepletedStack(ItemStack stack, IReactor reactor) {
        switch (this.numberOfCells) {
            case 1: {
                ItemStack ret = SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.depleted_proton_fuel_rod);
                return ret.func_77946_l();
            }
            case 2: {
                ItemStack ret = SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.depleted_dual_proton_fuel_rod);
                return ret.func_77946_l();
            }
            case 4: {
                ItemStack ret = SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.depleted_quad_proton_fuel_rod);
                return ret.func_77946_l();
            }
            case 8: {
                ItemStack ret = SSP_Items.CRAFTING.getItemStack(CraftingThings.CraftingTypes.depleted_eit_proton_fuel_rod);
                return ret.func_77946_l();
            }
        }
        throw new RuntimeException("invalid cell count: " + this.numberOfCells);
    }

    protected static int checkPulseable(IReactor reactor, int x, int y, ItemStack stack, int mex, int mey, boolean heatrun) {
        ItemStack other = reactor.getItemAt(x, y);
        if (other != null && other.func_77973_b() instanceof IReactorComponent && ((IReactorComponent)other.func_77973_b()).acceptUraniumPulse(other, reactor, stack, x, y, mex, mey, heatrun)) {
            return 1;
        }
        return 0;
    }

    protected static int triangularNumber(int x) {
        return (x * x + x) / 2;
    }

    protected void checkHeatAcceptor(IReactor reactor, int x, int y, Collection<ItemStackCoord> heatAcceptors) {
        ItemStack stack = reactor.getItemAt(x, y);
        if (stack != null && stack.func_77973_b() instanceof IReactorComponent && ((IReactorComponent)stack.func_77973_b()).canStoreHeat(stack, reactor, x, y)) {
            heatAcceptors.add(new ItemStackCoord(stack, x, y));
        }
    }

    @Override
    public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY, boolean heatrun) {
        if (!heatrun) {
            reactor.addOutput(2.0f);
        }
        return true;
    }

    @Override
    public float influenceExplosion(ItemStack stack, IReactor reactor) {
        return 2 * this.numberOfCells;
    }

    public void func_77663_a(ItemStack stack, World world, Entity entity, int slotIndex, boolean isCurrentItem) {
        EntityLivingBase entityLiving;
        if (entity instanceof EntityLivingBase && !ItemArmorHazmat.hasCompleteHazmat((EntityLivingBase)(entityLiving = (EntityLivingBase)entity))) {
            IC2Potion.radiation.applyTo(entityLiving, 200, 100);
        }
    }

    private static class ItemStackCoord {
        public final ItemStack stack;
        public final int x;
        public final int y;

        public ItemStackCoord(ItemStack stack, int x, int y) {
            this.stack = stack;
            this.x = x;
            this.y = y;
        }
    }
}

