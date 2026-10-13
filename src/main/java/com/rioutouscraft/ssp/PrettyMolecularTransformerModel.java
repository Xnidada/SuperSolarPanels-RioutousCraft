/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.entity.Entity
 */
package com.rioutouscraft.ssp;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class PrettyMolecularTransformerModel
extends ModelBase {
    ModelRenderer coreBottom;
    ModelRenderer coreWorkZone;
    ModelRenderer coreTopElectr;
    ModelRenderer coreTopPlate;
    ModelRenderer firstElTop;
    ModelRenderer firstElBottom;
    ModelRenderer secondElTop;
    ModelRenderer secondElBottom;
    ModelRenderer thirdElTop;
    ModelRenderer thirdElBottom;

    public PrettyMolecularTransformerModel() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.coreBottom = new ModelRenderer((ModelBase)this, 0, 0);
        this.coreBottom.addBox(-5.0f, 4.0f, -5.0f, 10, 3, 10);
        this.coreBottom.setRotationPoint(0.0f, 16.0f, 0.0f);
        this.coreBottom.setTextureSize(128, 64);
        this.coreBottom.mirror = true;
        this.setRotation(this.coreBottom, 0.0f, 0.0f, 0.0f);
        this.coreWorkZone = new ModelRenderer((ModelBase)this, 0, 44);
        this.coreWorkZone.addBox(-3.0f, -4.0f, -3.0f, 6, 9, 6);
        this.coreWorkZone.setRotationPoint(0.0f, 16.0f, 0.0f);
        this.coreWorkZone.setTextureSize(128, 64);
        this.coreWorkZone.mirror = true;
        this.setRotation(this.coreWorkZone, 0.0f, 0.0f, 0.0f);
        this.coreTopElectr = new ModelRenderer((ModelBase)this, 25, 44);
        this.coreTopElectr.addBox(-2.0f, -8.0f, -1.466667f, 3, 2, 3);
        this.coreTopElectr.setRotationPoint(0.0f, 16.0f, 0.0f);
        this.coreTopElectr.setTextureSize(128, 64);
        this.coreTopElectr.mirror = true;
        this.setRotation(this.coreTopElectr, 0.0f, 0.0f, 0.0f);
        this.coreTopPlate = new ModelRenderer((ModelBase)this, 0, 30);
        this.coreTopPlate.addBox(-5.0f, -7.0f, -4.5f, 9, 3, 9);
        this.coreTopPlate.setRotationPoint(0.0f, 16.0f, 0.0f);
        this.coreTopPlate.setTextureSize(128, 64);
        this.coreTopPlate.mirror = true;
        this.setRotation(this.coreTopPlate, 0.0f, 0.0f, 0.0f);
        this.firstElTop = new ModelRenderer((ModelBase)this, 20, 16);
        this.firstElTop.addBox(3.0f, -8.0f, -5.0f, 4, 3, 10);
        this.firstElTop.setRotationPoint(0.0f, 16.0f, 0.0f);
        this.firstElTop.setTextureSize(128, 64);
        this.firstElTop.mirror = true;
        this.setRotation(this.firstElTop, 0.0f, 0.0f, 0.0f);
        this.firstElBottom = new ModelRenderer((ModelBase)this, 49, 16);
        this.firstElBottom.addBox(4.0f, 3.0f, -3.0f, 3, 5, 6);
        this.firstElBottom.setRotationPoint(0.0f, 16.0f, 0.0f);
        this.firstElBottom.setTextureSize(128, 64);
        this.firstElBottom.mirror = true;
        this.setRotation(this.firstElBottom, 0.0f, 0.0f, 0.0f);
        this.secondElTop = new ModelRenderer((ModelBase)this, 20, 16);
        this.secondElTop.addBox(3.0f, -8.0f, -5.0f, 4, 3, 10);
        this.secondElTop.setRotationPoint(0.0f, 16.0f, 0.0f);
        this.secondElTop.setTextureSize(128, 64);
        this.secondElTop.mirror = true;
        this.setRotation(this.secondElTop, 0.0f, -2.094395f, 0.0f);
        this.secondElBottom = new ModelRenderer((ModelBase)this, 49, 16);
        this.secondElBottom.addBox(4.0f, 3.0f, -3.0f, 3, 5, 6);
        this.secondElBottom.setRotationPoint(0.0f, 16.0f, 0.0f);
        this.secondElBottom.setTextureSize(128, 64);
        this.secondElBottom.mirror = true;
        this.setRotation(this.secondElBottom, 0.0f, -2.094395f, 0.0f);
        this.thirdElTop = new ModelRenderer((ModelBase)this, 20, 16);
        this.thirdElTop.addBox(3.0f, -8.0f, -5.0f, 4, 3, 10);
        this.thirdElTop.setRotationPoint(0.0f, 16.0f, 0.0f);
        this.thirdElTop.setTextureSize(128, 64);
        this.thirdElTop.mirror = true;
        this.setRotation(this.thirdElTop, 0.0f, 2.094395f, 0.0f);
        this.thirdElBottom = new ModelRenderer((ModelBase)this, 49, 16);
        this.thirdElBottom.addBox(4.0f, 3.0f, -3.0f, 3, 5, 6);
        this.thirdElBottom.setRotationPoint(0.0f, 16.0f, 0.0f);
        this.thirdElBottom.setTextureSize(128, 64);
        this.thirdElBottom.mirror = true;
        this.setRotation(this.thirdElBottom, 0.0f, 2.094395f, 0.0f);
    }

    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        this.coreBottom.render(scale);
        this.firstElTop.render(scale);
        this.firstElBottom.render(scale);
        this.secondElTop.render(scale);
        this.secondElBottom.render(scale);
        this.thirdElTop.render(scale);
        this.thirdElBottom.render(scale);
        this.coreTopElectr.render(scale);
        this.coreTopPlate.render(scale);
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        this.coreWorkZone.render(scale);
        GlStateManager.popMatrix();
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}

