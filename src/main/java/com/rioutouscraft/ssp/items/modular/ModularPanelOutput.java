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

public final class ModularPanelOutput extends ItemMulti<ModularPanelOutput.Type> {
    public static final String NAME = "solar_panel_output";

    public ModularPanelOutput() {
        super((ItemName) null, Type.class);
        setTranslationKey(NAME);
        setCreativeTab(SuperSolarPanels.SSPtab);
        BlocksItems.registerItem(this, new ResourceLocation(SuperSolarPanels.MODID, NAME));
    }

    public static Type getPanelType(ItemStack stack) {
        return stack == null || stack.isEmpty() || !(stack.getItem() instanceof ModularPanelOutput)
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
        tooltip.add(Localization.translate(SuperSolarPanels.MODID + ".module.output", format(getPanelType(stack).getOutput())));
    }

    private static String format(double value) {
        if (value == Math.rint(value)) return String.format(java.util.Locale.ROOT, "%.0f", value);
        return String.format(java.util.Locale.ROOT, "%.2f", value).replaceAll("0+$", "").replaceAll("\\.$", "");
    }

    public enum Type implements IIdProvider {
        BASIC(0, "basic", 32D, 2),
        ADVANCED(1, "advanced", 128D, 3),
        QUANTUM(2, "quantum", 512D, 4),
        LEVEL_4(3, "level_4", 2048D, 5),
        LEVEL_5(4, "level_5", 8192D, 6),
        LEVEL_6(5, "level_6", 32768D, 7),
        LEVEL_7(6, "level_7", 131072D, 8),
        LEVEL_8(7, "level_8", 524288D, 9),
        LEVEL_9(8, "level_9", 2097152D, 10),
        LEVEL_10(9, "level_10", 8388608D, 11),
        LEVEL_11(10, "level_11", 33554432D, 12),
        LEVEL_12(11, "level_12", 134217728D, 13),
        LEVEL_13(12, "level_13", 536870912D, 14),
        LEVEL_14(13, "level_14", 2147483648D, 15);

        private final int id;
        private final String name;
        private final double defaultOutput;
        private final int defaultTier;

        Type(int id, String name, double defaultOutput, int defaultTier) {
            this.id = id;
            this.name = name;
            this.defaultOutput = defaultOutput;
            this.defaultTier = defaultTier;
        }

        public double getOutput() { return ConfigSync.moduleOutput(id, defaultOutput); }
        public int getTier() { return ConfigSync.moduleOutputTier(id, defaultTier); }

        public static Type getFromID(int id) {
            Type[] values = values();
            return values[Math.floorMod(id, values.length)];
        }

        @Override public String getName() { return name; }
        @Override public int getId() { return id; }
    }
}
