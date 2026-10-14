/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.core.block.ITeBlock
 *  ic2.core.block.TileEntityBlock
 *  ic2.core.ref.TeBlock$DefaultDrop
 *  ic2.core.ref.TeBlock$HarvestTool
 *  ic2.core.util.Util
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.common.Loader
 *  net.minecraftforge.fml.common.ModContainer
 *  net.minecraftforge.fml.common.registry.GameRegistry
 */
package com.rioutouscraft.ssp.tiles;

import com.rioutouscraft.ssp.tiles.TileEntityMolecularAssembler;
import com.rioutouscraft.ssp.tiles.Transformer.TileEntityTransformerEV;
import com.rioutouscraft.ssp.tiles.Transformer.TileEntityTransformerEV1;
import com.rioutouscraft.ssp.tiles.Transformer.TileEntityTransformerEV2;
import com.rioutouscraft.ssp.tiles.Transformer.TileEntityTransformerEV3;
import com.rioutouscraft.ssp.tiles.advancedmfsu;
import com.rioutouscraft.ssp.tiles.neutronfabricator.TileEntityMassFabricator;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityAdmin;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityAdvancedSolar;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityBarion;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityGraviton;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityHybridSolar;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityHadron;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityQuantumSolar;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityQuark;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntitySingular;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntitySpectral;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityUltimateHybridSolar;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityneutron;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityphotonic;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityproton;
import com.rioutouscraft.ssp.tiles.quantummfsu;
import com.rioutouscraft.ssp.tiles.ultimatemfsu;
import ic2.core.block.ITeBlock;
import ic2.core.block.TileEntityBlock;
import ic2.core.ref.TeBlock;
import ic2.core.util.Util;
import java.util.Set;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.registry.GameRegistry;

public enum SSPBlock implements ITeBlock
{
    advanced_solar_panel(TileEntityAdvancedSolar.class, 1),
    hybrid_solar_panel(TileEntityHybridSolar.class, 2, EnumRarity.RARE),
    ultimate_solar_panel(TileEntityUltimateHybridSolar.class, 3, EnumRarity.EPIC),
    quantum_solar_panel(TileEntityQuantumSolar.class, 4, EnumRarity.EPIC),
    spectral_solar_panel(TileEntitySpectral.class, 5),
    singular_solar_panel(TileEntitySingular.class, 6, EnumRarity.RARE),
    admin_solar_panel(TileEntityAdmin.class, 7, EnumRarity.EPIC),
    photonic_solar_panel(TileEntityphotonic.class, 8, EnumRarity.EPIC),
    neutronium_solar_panel(TileEntityneutron.class, 9, EnumRarity.EPIC),
    barion_solar_panel(TileEntityBarion.class, 52, EnumRarity.EPIC),
    hadron_solar_panel(TileEntityHadron.class, 53, EnumRarity.EPIC),
    graviton_solar_panel(TileEntityGraviton.class, 54, EnumRarity.EPIC),
    quark_solar_panel(TileEntityQuark.class, 55, EnumRarity.EPIC),
    nutronfabricator(TileEntityMassFabricator.class, 10),
    proton_solar_panel(TileEntityproton.class, 11, EnumRarity.EPIC),
    transformator(TileEntityTransformerEV.class, 12),
    transformator1(TileEntityTransformerEV1.class, 13),
    transformator2(TileEntityTransformerEV2.class, 14),
    transformator3(TileEntityTransformerEV3.class, 15),
    molecular_transformer(TileEntityMolecularAssembler.class, 25, EnumRarity.RARE),
    advancedmfsu(advancedmfsu.class, 45, EnumRarity.EPIC),
    ultimatemfsu(ultimatemfsu.class, 46, EnumRarity.EPIC),
    quantummfsu(quantummfsu.class, 47, EnumRarity.EPIC);

    private final Class<? extends TileEntityBlock> teClass;
    private final int itemMeta;
    private final EnumRarity rarity;
    private TileEntityBlock dummyTe;
    private static final SSPBlock[] VALUES;
    public static final ResourceLocation IDENTITY;

    private SSPBlock(Class<? extends TileEntityBlock> teClass, int itemMeta) {
        this(teClass, itemMeta, EnumRarity.UNCOMMON);
    }

    private SSPBlock(Class<? extends TileEntityBlock> teClass, int itemMeta, EnumRarity rarity) {
        this.teClass = teClass;
        this.itemMeta = itemMeta;
        this.rarity = rarity;
        GameRegistry.registerTileEntity(teClass, (String)("super_solar_panels_rioutouscraft:" + this.getName()));
    }

    public boolean hasItem() {
        return true;
    }

    public String getName() {
        return this.name();
    }

    public int getId() {
        return this.itemMeta;
    }

    public ResourceLocation getIdentifier() {
        return IDENTITY;
    }

    public Class<? extends TileEntityBlock> getTeClass() {
        return this.teClass;
    }

    public float getHardness() {
        return 3.0f;
    }

    public float getExplosionResistance() {
        return 0.0f;
    }

    public TeBlock.HarvestTool getHarvestTool() {
        return TeBlock.HarvestTool.Pickaxe;
    }

    public TeBlock.DefaultDrop getDefaultDrop() {
        return TeBlock.DefaultDrop.Self;
    }

    public boolean allowWrenchRotating() {
        return false;
    }

    public Set<EnumFacing> getSupportedFacings() {
        return Util.horizontalFacings;
    }

    public EnumRarity getRarity() {
        return this.rarity;
    }

    public static void buildDummies() {
        ModContainer mc = Loader.instance().activeModContainer();
        if (mc == null || !"super_solar_panels_rioutouscraft".equals(mc.getModId())) {
            throw new IllegalAccessError("Don't mess with this please.");
        }
        for (SSPBlock block : VALUES) {
            if (block.teClass == null) continue;
            try {
                block.dummyTe = block.teClass.newInstance();
            }
            catch (Exception e) {
                if (!Util.inDev()) continue;
                e.printStackTrace();
            }
        }
    }

    public TileEntityBlock getDummyTe() {
        return this.dummyTe;
    }

    public boolean hasActive() {
        return false;
    }

    static {
        VALUES = SSPBlock.values();
        IDENTITY = new ResourceLocation("super_solar_panels_rioutouscraft", "machines");
    }
}

