/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  gnu.trove.map.TObjectIntMap
 *  gnu.trove.map.hash.TObjectIntHashMap
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.EnumSkyBlock
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.opengl.GL11
 */
package com.rioutouscraft.ssp;

import com.rioutouscraft.ssp.PrettyMolecularTransformerModel;
import com.rioutouscraft.ssp.SuperSolarPanels;
import com.rioutouscraft.ssp.tiles.TileEntityMolecularAssembler;
import gnu.trove.map.TObjectIntMap;
import gnu.trove.map.hash.TObjectIntHashMap;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class PrettyMolecularTransformerTESR
extends TileEntitySpecialRenderer<TileEntityMolecularAssembler> {
    private static final ResourceLocation transfTextloc = new ResourceLocation("super_solar_panels_rioutouscraft", "textures/models/textureMolecularTransformer.png");
    private static final ResourceLocation plazmaTextloc = new ResourceLocation("super_solar_panels_rioutouscraft", "textures/models/plazma.png");
    private static final ResourceLocation particlesTextloc = new ResourceLocation("super_solar_panels_rioutouscraft", "textures/models/particles.png");
    public static final PrettyMolecularTransformerModel model = new PrettyMolecularTransformerModel();
    private static final TObjectIntMap<List<Serializable>> textureSizeCache = new TObjectIntHashMap();
    private static final IResourceManager resources = Minecraft.getMinecraft().getResourceManager();
    public static boolean drawActiveCore = false;
    public int ticker;

    public static int getTextureSize(String s, int dv) {
        if (textureSizeCache.containsKey(Arrays.asList(s, dv))) {
            return textureSizeCache.get(Arrays.asList(s, dv));
        }
        try {
            InputStream inputstream = resources.getResource(new ResourceLocation("advanced_solar_panels", s)).getInputStream();
            if (inputstream == null) {
                throw new FileNotFoundException("Image not found: " + s);
            }
            int size = ImageIO.read(inputstream).getWidth() / dv;
            textureSizeCache.put(Arrays.asList(s, dv), size);
            return size;
        }
        catch (Exception e) {
            SuperSolarPanels.log.error("Error getting size of texture " + s + " (" + dv + ')', (Throwable)e);
            return 16;
        }
    }

    public void renderCore(TileEntity te, double x, double y, double z) {
        ++this.ticker;
        if (this.ticker > 160) {
            this.ticker = 0;
        }
        int plazmaSize = PrettyMolecularTransformerTESR.getTextureSize(plazmaTextloc.getPath(), 64);
        int particleSize = PrettyMolecularTransformerTESR.getTextureSize(particlesTextloc.getPath(), 32);
        float rotationX = ActiveRenderInfo.getRotationX();
        float rotationXZ = ActiveRenderInfo.getRotationXZ();
        float rotationZ = ActiveRenderInfo.getRotationZ();
        float rotationYZ = ActiveRenderInfo.getRotationYZ();
        float rotationXY = ActiveRenderInfo.getRotationXY();
        float scaleCore = 0.35f;
        float posX = (float)x + 0.5f;
        float posY = (float)y + 0.5f;
        float posZ = (float)z + 0.5f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        Color colour = new Color(0xC0FFFF);
        GL11.glPushMatrix();
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        this.bindTexture(plazmaTextloc);
        int phase = this.ticker % 16;
        float quadPlazmaSize = plazmaSize * 4;
        float plasmaEdge = (float)plazmaSize - 0.01f;
        float xBottom = ((float)(phase % 4 * plazmaSize) + 0.0f) / quadPlazmaSize;
        float xTop = ((float)(phase % 4 * plazmaSize) + plasmaEdge) / quadPlazmaSize;
        float yBottom = ((float)(phase / 4 * plazmaSize) + 0.0f) / quadPlazmaSize;
        float yTop = ((float)(phase / 4 * plazmaSize) + plasmaEdge) / quadPlazmaSize;
        buffer.begin(7, DefaultVertexFormats.BLOCK);
        GL11.glColor4f((float)((float)colour.getRed() / 255.0f), (float)((float)colour.getGreen() / 255.0f), (float)((float)colour.getBlue() / 255.0f), (float)1.0f);
        buffer.pos((double)(posX - rotationX * scaleCore - rotationYZ * scaleCore), (double)(posY - rotationXZ * scaleCore), (double)(posZ - rotationZ * scaleCore - rotationXY * scaleCore)).tex((double)xTop, (double)yTop).endVertex();
        buffer.pos((double)(posX - rotationX * scaleCore + rotationYZ * scaleCore), (double)(posY + rotationXZ * scaleCore), (double)(posZ - rotationZ * scaleCore + rotationXY * scaleCore)).tex((double)xTop, (double)yBottom).endVertex();
        buffer.pos((double)(posX + rotationX * scaleCore + rotationYZ * scaleCore), (double)(posY + rotationXZ * scaleCore), (double)(posZ + rotationZ * scaleCore + rotationXY * scaleCore)).tex((double)xBottom, (double)yBottom).endVertex();
        buffer.pos((double)(posX + rotationX * scaleCore - rotationYZ * scaleCore), (double)(posY - rotationXZ * scaleCore), (double)(posZ + rotationZ * scaleCore - rotationXY * scaleCore)).tex((double)xBottom, (double)yTop).endVertex();
        tessellator.draw();
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        this.bindTexture(particlesTextloc);
        float octParticleSize = particleSize * 8;
        plasmaEdge = (float)particleSize - 0.01f;
        xBottom = ((float)((phase += 24) % 8 * particleSize) + 0.0f) / octParticleSize;
        xTop = ((float)(phase % 8 * particleSize) + plasmaEdge) / octParticleSize;
        yBottom = ((float)(phase / 8 * particleSize) + 0.0f) / octParticleSize;
        yTop = ((float)(phase / 8 * particleSize) + plasmaEdge) / octParticleSize;
        scaleCore = 0.4f + MathHelper.sin((float)((float)this.ticker / 10.0f)) * 0.1f;
        buffer.begin(7, DefaultVertexFormats.BLOCK);
        GlStateManager.disableLighting();
        buffer.pos((double)(posX - rotationX * scaleCore - rotationYZ * scaleCore), (double)(posY - rotationXZ * scaleCore), (double)(posZ - rotationZ * scaleCore - rotationXY * scaleCore)).tex((double)xTop, (double)yTop).color(255, 255, 255, 255).endVertex();
        buffer.pos((double)(posX - rotationX * scaleCore + rotationYZ * scaleCore), (double)(posY + rotationXZ * scaleCore), (double)(posZ - rotationZ * scaleCore + rotationXY * scaleCore)).tex((double)xTop, (double)yBottom).color(255, 255, 255, 255).endVertex();
        buffer.pos((double)(posX + rotationX * scaleCore + rotationYZ * scaleCore), (double)(posY + rotationXZ * scaleCore), (double)(posZ + rotationZ * scaleCore + rotationXY * scaleCore)).tex((double)xBottom, (double)yBottom).color(255, 255, 255, 255).endVertex();
        buffer.pos((double)(posX + rotationX * scaleCore - rotationYZ * scaleCore), (double)(posY - rotationXZ * scaleCore), (double)(posZ + rotationZ * scaleCore - rotationXY * scaleCore)).tex((double)xBottom, (double)yTop).color(255, 255, 255, 255).endVertex();
        GlStateManager.enableLighting();
        tessellator.draw();
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
    }

    protected int getTileLighting(TileEntity tile, int lightValue) {
        if (tile == null || !tile.hasWorld()) {
            int blockLight = EnumSkyBlock.BLOCK.defaultLightValue;
            if (blockLight < lightValue) {
                blockLight = lightValue;
            }
            return EnumSkyBlock.SKY.defaultLightValue << 20 | blockLight << 4;
        }
        return tile.getWorld().getCombinedLight(tile.getPos(), lightValue);
    }

    public void render(TileEntityMolecularAssembler tileTransformer, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)((float)x + 0.5f), (float)((float)y + 1.5f), (float)((float)z + 0.5f));
        if (destroyStage >= 0) {
            this.bindTexture(DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode((int)5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale((float)4.0f, (float)4.0f, (float)1.0f);
            GlStateManager.translate((float)0.0625f, (float)0.0625f, (float)0.0625f);
            GlStateManager.matrixMode((int)5888);
        }
        GlStateManager.pushMatrix();
        GlStateManager.rotate((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        this.bindTexture(transfTextloc);
        model.render(null, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);
        GlStateManager.popMatrix();
        if (destroyStage >= 0) {
            GlStateManager.matrixMode((int)5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode((int)5888);
        }
        GlStateManager.popMatrix();
        if (tileTransformer != null && drawActiveCore && tileTransformer.getActive()) {
            GL11.glPushMatrix();
            GlStateManager.pushAttrib();
            this.renderCore((TileEntity)tileTransformer, x, y, z);
            GlStateManager.popAttrib();
            GL11.glPopMatrix();
        }
    }
}

