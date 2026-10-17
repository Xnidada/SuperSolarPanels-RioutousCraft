package com.rioutouscraft.ssp.items.modular;

import com.rioutouscraft.ssp.SuperSolarPanels;
import com.rioutouscraft.ssp.ConfigSync;
import ic2.core.block.state.IIdProvider;
import ic2.core.init.BlocksItems;
import ic2.core.init.Localization;
import ic2.core.item.ItemMulti;
import ic2.core.ref.ItemName;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public final class ModularPanelBattery extends ItemMulti<ModularPanelBattery.Type> {
    public static final String NAME = "solar_panel_battery";

    public ModularPanelBattery() {
        super((ItemName) null, Type.class);
        setTranslationKey(NAME);
        setCreativeTab(SuperSolarPanels.SSPtab);
        BlocksItems.registerItem(this, new ResourceLocation(SuperSolarPanels.MODID, NAME));
    }

    public static Type getPanelType(ItemStack stack) {
        return stack == null || stack.isEmpty() || !(stack.getItem() instanceof ModularPanelBattery)
                ? Type.BASIC : Type.getFromID(stack.getMetadata());
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return Localization.translate(SuperSolarPanels.MODID + "." + NAME + "." + getPanelType(stack).getName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected void registerModel(int meta, ItemName ignored, String extraName) {
        ModelLoader.setCustomModelResourceLocation(this, meta,
                new ModelResourceLocation(SuperSolarPanels.MODID + ":" + NAME + "/" + Type.getFromID(meta).getName(), null));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels(ItemName ignored) {
        for (Type type : Type.values()) registerModel(type.getId(), ignored, null);
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        tooltip.add(Localization.translate(SuperSolarPanels.MODID + ".module.capacity", format(getPanelType(stack).getCapacity())));
    }

    private static String format(double value) {
        if (value == Math.rint(value)) return String.format(java.util.Locale.ROOT, "%.0f", value);
        return String.format(java.util.Locale.ROOT, "%.2f", value).replaceAll("0+$", "").replaceAll("\\.$", "");
    }

    public enum Type implements IIdProvider {
        BASIC(0, "basic", 320D),
        ADVANCED(1, "advanced", 2000D),
        QUANTUM(2, "quantum", 20000D),
        LEVEL_4(3, "level_4", 100000D),
        LEVEL_5(4, "level_5", 500000D),
        LEVEL_6(5, "level_6", 5000000D),
        LEVEL_7(6, "level_7", 100000000D),
        LEVEL_8(7, "level_8", 150000000D),
        LEVEL_9(8, "level_9", 500000000D),
        LEVEL_10(9, "level_10", 650000000D),
        LEVEL_11(10, "level_11", 1000000000D),
        LEVEL_12(11, "level_12", 2500000000D),
        LEVEL_13(12, "level_13", 25000000000D),
        LEVEL_14(13, "level_14", 250000000000D);

        private final int id;
        private final String name;
        private final double defaultCapacity;

        Type(int id, String name, double defaultCapacity) {
            this.id = id;
            this.name = name;
            this.defaultCapacity = defaultCapacity;
        }

        public double getCapacity() { return ConfigSync.batteryCapacity(id, defaultCapacity); }

        public static Type getFromID(int id) {
            Type[] values = values();
            return values[Math.floorMod(id, values.length)];
        }

        @Override public String getName() { return name; }
        @Override public int getId() { return id; }
    }
}
