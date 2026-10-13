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

import com.rioutouscraft.ssp.tiles.Moonpanel.TileEntityAdminmoon;
import com.rioutouscraft.ssp.tiles.Moonpanel.TileEntityAdvancedSolarmoon;
import com.rioutouscraft.ssp.tiles.Moonpanel.TileEntityHybridSolarmoon;
import com.rioutouscraft.ssp.tiles.Moonpanel.TileEntityQuantumSolarmoon;
import com.rioutouscraft.ssp.tiles.Moonpanel.TileEntitySingularmoon;
import com.rioutouscraft.ssp.tiles.Moonpanel.TileEntitySpectralmoon;
import com.rioutouscraft.ssp.tiles.Moonpanel.TileEntityUltimateHybridSolarmoon;
import com.rioutouscraft.ssp.tiles.Moonpanel.TileEntityneutronmoon;
import com.rioutouscraft.ssp.tiles.Moonpanel.TileEntityphotonicmoon;
import com.rioutouscraft.ssp.tiles.Moonpanel.TileEntityprotonmoon;
import com.rioutouscraft.ssp.tiles.Sunpanel.TileEntityAdminsun;
import com.rioutouscraft.ssp.tiles.Sunpanel.TileEntityAdvancedSolarsun;
import com.rioutouscraft.ssp.tiles.Sunpanel.TileEntityHybridSolarsun;
import com.rioutouscraft.ssp.tiles.Sunpanel.TileEntityQuantumSolarsun;
import com.rioutouscraft.ssp.tiles.Sunpanel.TileEntitySingularsun;
import com.rioutouscraft.ssp.tiles.Sunpanel.TileEntitySpectralsun;
import com.rioutouscraft.ssp.tiles.Sunpanel.TileEntityUltimateHybridSolarsun;
import com.rioutouscraft.ssp.tiles.Sunpanel.TileEntityneutronsun;
import com.rioutouscraft.ssp.tiles.Sunpanel.TileEntityphotonicsun;
import com.rioutouscraft.ssp.tiles.Sunpanel.TileEntityprotonsun;
import com.rioutouscraft.ssp.tiles.TileEntityMolecularAssembler;
import com.rioutouscraft.ssp.tiles.Transformer.TileEntityTransformerEV;
import com.rioutouscraft.ssp.tiles.Transformer.TileEntityTransformerEV1;
import com.rioutouscraft.ssp.tiles.Transformer.TileEntityTransformerEV2;
import com.rioutouscraft.ssp.tiles.Transformer.TileEntityTransformerEV3;
import com.rioutouscraft.ssp.tiles.advancedmfsu;
import com.rioutouscraft.ssp.tiles.neutronfabricator.TileEntityMassFabricator;
import com.rioutouscraft.ssp.tiles.modular.TileEntityModularSolarPanel;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityAdmin;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityAdvancedSolar;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityHybridSolar;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityQuantumSolar;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntitySingular;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntitySpectral;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityUltimateHybridSolar;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityneutron;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityphotonic;
import com.rioutouscraft.ssp.tiles.overtimepanel.TileEntityproton;
import com.rioutouscraft.ssp.tiles.quantummfsu;
import com.rioutouscraft.ssp.tiles.rainpanels.TileEntityAdminrain;
import com.rioutouscraft.ssp.tiles.rainpanels.TileEntityAdvancedSolarrain;
import com.rioutouscraft.ssp.tiles.rainpanels.TileEntityHybridSolarrain;
import com.rioutouscraft.ssp.tiles.rainpanels.TileEntityQuantumSolarrain;
import com.rioutouscraft.ssp.tiles.rainpanels.TileEntitySingularrain;
import com.rioutouscraft.ssp.tiles.rainpanels.TileEntitySpectralrain;
import com.rioutouscraft.ssp.tiles.rainpanels.TileEntityUltimateHybridSolarrain;
import com.rioutouscraft.ssp.tiles.rainpanels.TileEntityneutronrain;
import com.rioutouscraft.ssp.tiles.rainpanels.TileEntityphotonicrain;
import com.rioutouscraft.ssp.tiles.rainpanels.TileEntityprotonrain;
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
    nutronfabricator(TileEntityMassFabricator.class, 10),
    proton_solar_panel(TileEntityproton.class, 11, EnumRarity.EPIC),
    transformator(TileEntityTransformerEV.class, 12),
    transformator1(TileEntityTransformerEV1.class, 13),
    transformator2(TileEntityTransformerEV2.class, 14),
    transformator3(TileEntityTransformerEV3.class, 15),
    advanced_solar_panelsun(TileEntityAdvancedSolarsun.class, 16),
    hybrid_solar_panelsun(TileEntityHybridSolarsun.class, 17, EnumRarity.RARE),
    ultimate_solar_panelsun(TileEntityUltimateHybridSolarsun.class, 18, EnumRarity.EPIC),
    quantum_solar_panelsun(TileEntityQuantumSolarsun.class, 19, EnumRarity.EPIC),
    spectral_solar_panelsun(TileEntitySpectralsun.class, 20),
    proton_solar_panelsun(TileEntityprotonsun.class, 50),
    singular_solar_panelsun(TileEntitySingularsun.class, 21, EnumRarity.RARE),
    admin_solar_panelsun(TileEntityAdminsun.class, 22, EnumRarity.EPIC),
    photonic_solar_panelsun(TileEntityphotonicsun.class, 23, EnumRarity.EPIC),
    neutronium_solar_panelsun(TileEntityneutronsun.class, 24, EnumRarity.EPIC),
    advanced_solar_panelmoon(TileEntityAdvancedSolarmoon.class, 26),
    hybrid_solar_panelmoon(TileEntityHybridSolarmoon.class, 27, EnumRarity.RARE),
    ultimate_solar_panelmoon(TileEntityUltimateHybridSolarmoon.class, 28, EnumRarity.EPIC),
    quantum_solar_panelmoon(TileEntityQuantumSolarmoon.class, 29, EnumRarity.EPIC),
    spectral_solar_panelmoon(TileEntitySpectralmoon.class, 30),
    singular_solar_panelmoon(TileEntitySingularmoon.class, 31, EnumRarity.RARE),
    admin_solar_panelmoon(TileEntityAdminmoon.class, 32, EnumRarity.EPIC),
    photonic_solar_panelmoon(TileEntityphotonicmoon.class, 33, EnumRarity.EPIC),
    neutronium_solar_panelmoon(TileEntityneutronmoon.class, 34, EnumRarity.EPIC),
    proton_solar_panelmoon(TileEntityprotonmoon.class, 48, EnumRarity.EPIC),
    proton_solar_panelrain(TileEntityprotonrain.class, 49, EnumRarity.EPIC),
    molecular_transformer(TileEntityMolecularAssembler.class, 25, EnumRarity.RARE),
    advanced_solar_panelrain(TileEntityAdvancedSolarrain.class, 36),
    hybrid_solar_panelrain(TileEntityHybridSolarrain.class, 37, EnumRarity.RARE),
    ultimate_solar_panelrain(TileEntityUltimateHybridSolarrain.class, 38, EnumRarity.EPIC),
    quantum_solar_panelrain(TileEntityQuantumSolarrain.class, 39, EnumRarity.EPIC),
    spectral_solar_panelrain(TileEntitySpectralrain.class, 40),
    singular_solar_panelrain(TileEntitySingularrain.class, 41, EnumRarity.RARE),
    admin_solar_panelrain(TileEntityAdminrain.class, 42, EnumRarity.EPIC),
    photonic_solar_panelrain(TileEntityphotonicrain.class, 43, EnumRarity.EPIC),
    neutronium_solar_panelrain(TileEntityneutronrain.class, 44, EnumRarity.EPIC),
    advancedmfsu(advancedmfsu.class, 45, EnumRarity.EPIC),
    ultimatemfsu(ultimatemfsu.class, 46, EnumRarity.EPIC),
    quantummfsu(quantummfsu.class, 47, EnumRarity.EPIC),
    modular_solar_panel(TileEntityModularSolarPanel.class, 51, EnumRarity.RARE);

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

