/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ic2.core.ContainerBase
 *  ic2.core.gui.GuiElement
 *  ic2.core.gui.Image
 *  ic2.core.gui.dynamic.DynamicContainer
 *  ic2.core.gui.dynamic.DynamicGui
 *  ic2.core.gui.dynamic.GuiParser$GuiNode
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 */
package com.rioutouscraft.ssp;

import com.rioutouscraft.ssp.gui.BackgroundlessDynamicGUI;
import ic2.core.ContainerBase;
import ic2.core.gui.GuiElement;
import ic2.core.gui.Image;
import ic2.core.gui.dynamic.DynamicContainer;
import ic2.core.gui.dynamic.DynamicGui;
import ic2.core.gui.dynamic.GuiParser;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public class TransparentDynamicGUI<T extends ContainerBase<? extends IInventory>>
extends BackgroundlessDynamicGUI<T> {
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T extends IInventory> DynamicGui<ContainerBase<T>> create(T base, EntityPlayer player, GuiParser.GuiNode guiNode) {
        return (DynamicGui<ContainerBase<T>>)(DynamicGui) new TransparentDynamicGUI<DynamicContainer<T>>(player, DynamicContainer.create(base, player, guiNode), guiNode);
    }

    protected TransparentDynamicGUI(EntityPlayer player, T container, GuiParser.GuiNode guiNode) {
        super(player, container, guiNode);
    }

    @Override
    protected void drawElement(GuiElement<?> element, int mouseX, int mouseY) {
        boolean image = element instanceof Image;
        if (image) {
            GlStateManager.enableBlend();
            GlStateManager.blendFunc((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        }
        super.drawElement(element, mouseX, mouseY);
        if (image) {
            GlStateManager.disableBlend();
        }
    }
}

