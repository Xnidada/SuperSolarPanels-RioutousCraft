/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.core.ContainerBase
 *  ic2.core.gui.GuiElement
 *  ic2.core.gui.dynamic.DynamicContainer
 *  ic2.core.gui.dynamic.DynamicGui
 *  ic2.core.gui.dynamic.GuiParser$GuiNode
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 */
package com.rioutouscraft.ssp.gui;

import ic2.core.ContainerBase;
import ic2.core.gui.GuiElement;
import ic2.core.gui.dynamic.DynamicContainer;
import ic2.core.gui.dynamic.DynamicGui;
import ic2.core.gui.dynamic.GuiParser;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public class BackgroundlessDynamicGUI<T extends ContainerBase<? extends IInventory>>
extends DynamicGui<T> {
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T extends IInventory> DynamicGui<ContainerBase<T>> create(T base, EntityPlayer player, GuiParser.GuiNode guiNode) {
        return (DynamicGui<ContainerBase<T>>)(DynamicGui) new BackgroundlessDynamicGUI<DynamicContainer<T>>(player, DynamicContainer.create(base, player, guiNode), guiNode);
    }

    protected BackgroundlessDynamicGUI(EntityPlayer player, T container, GuiParser.GuiNode guiNode) {
        super(player, container, guiNode);
    }

    protected final void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mouseX -= this.guiLeft;
        mouseY -= this.guiTop;
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        for (GuiElement element : this.elements) {
            if (!element.isEnabled()) continue;
            this.drawElement(element, mouseX, mouseY);
        }
    }

    protected void drawElement(GuiElement<?> element, int mouseX, int mouseY) {
        element.drawBackground(mouseX, mouseY);
    }

    public int getLeft() {
        return this.guiLeft;
    }

    public int getTop() {
        return this.guiTop;
    }
}

