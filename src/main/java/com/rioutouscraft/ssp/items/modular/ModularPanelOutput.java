package com.rioutouscraft.ssp.items.modular;

import com.rioutouscraft.ssp.SuperSolarPanels;
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
        tooltip.add(Localization.translate(SuperSolarPanels.MODID + ".module.output", getPanelType(stack).output));
    }

    public enum Type implements IIdProvider {
        BASIC(0, "basic", 32, 2),
        ADVANCED(1, "advanced", 128, 3),
        QUANTUM(2, "quantum", 512, 4);

        private final int id;
        private final String name;
        public final int output;
        public final int tier;

        Type(int id, String name, int output, int tier) {
            this.id = id;
            this.name = name;
            this.output = output;
            this.tier = tier;
        }

        public static Type getFromID(int id) {
            Type[] values = values();
            return values[Math.floorMod(id, values.length)];
        }

        @Override public String getName() { return name; }
        @Override public int getId() { return id; }
    }
}
