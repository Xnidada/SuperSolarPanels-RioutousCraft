/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.core.ContainerBase
 *  ic2.core.GuiIC2
 *  ic2.core.gui.GuiElement
 *  ic2.core.gui.TankGauge
 *  ic2.core.init.Localization
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fluids.IFluidTank
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.rioutouscraft.ssp.tiles.neutronfabricator;

import com.rioutouscraft.ssp.tiles.neutronfabricator.ContainerMatter;
import com.rioutouscraft.ssp.tiles.neutronfabricator.TileEntityMassFabricator;
import ic2.core.ContainerBase;
import ic2.core.GuiIC2;
import ic2.core.gui.GuiElement;
import ic2.core.gui.TankGauge;
import ic2.core.init.Localization;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class GuiMatter
extends GuiIC2<ContainerMatter> {
    public String progressLabel;
    public String amplifierLabel;

    public GuiMatter(ContainerMatter container) {
        super(container);
        this.addElement((GuiElement)TankGauge.createNormal((GuiIC2)this, (int)96, (int)22, (IFluidTank)((TileEntityMassFabricator)container.base).fluidTank));
        this.progressLabel = Localization.translate((String)"ic2.Matter.gui.info.progress");
        this.amplifierLabel = Localization.translate((String)"ic2.Matter.gui.info.amplifier");
    }

    protected void drawForegroundLayer(int mouseX, int mouseY) {
        super.drawForegroundLayer(mouseX, mouseY);
        this.fontRenderer.drawString(this.progressLabel, 8, 22, 0x404040);
        this.fontRenderer.drawString(((TileEntityMassFabricator)((ContainerMatter)this.container).base).getProgressAsString(), 18, 31, 0x404040);
        if (((TileEntityMassFabricator)((ContainerMatter)this.container).base).scrap > 0) {
            this.fontRenderer.drawString(this.amplifierLabel, 8, 46, 0x404040);
            this.fontRenderer.drawString("" + ((TileEntityMassFabricator)((ContainerMatter)this.container).base).scrap, 8, 58, 0x404040);
        }
    }

    public ResourceLocation getTexture() {
        return new ResourceLocation("ic2", "textures/gui/GUIMatter.png");
    }
}

