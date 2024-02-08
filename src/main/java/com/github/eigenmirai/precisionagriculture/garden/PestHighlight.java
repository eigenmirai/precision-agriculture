package com.github.eigenmirai.precisionagriculture.garden;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PestHighlight {
    private boolean enabled = true;
    private final Minecraft mc = Minecraft.getMinecraft();
    private List<Entity> highlightedPests = new ArrayList<>();
    private Color color = new Color(1.0f, 0.0f, 1.0f, 0.5f); // default color

    List<String> pestNames = Arrays.asList("Fly", "Cricket", "Locust", "Rat", "Mosquito", "Earthworm", "Mite", "Moth", "Slug", "Beetle");

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {
        highlightedPests.clear();
    }

    @SubscribeEvent
    public void onWorldRender(RenderWorldLastEvent event) {
        if (!enabled) return;
        mc.theWorld.loadedEntityList.forEach(
                entity -> {
                    if (entity instanceof EntityBat || entity instanceof EntitySilverfish) {
                        highlightedPests.add(entity);
                        renderBoxAroundEntity(entity, event.partialTicks, this.color);
                        drawTracer(entity.getPositionVector(), this.color);
                    }
                }
        );
    }

    private void renderBoxAroundEntity(Entity entity, float partialTicks, Color color) {
        double interpX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks;
        double interpY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks;
        double interpZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks;

        double entityX = interpX - mc.getRenderManager().viewerPosX;
        double entityY = interpY - mc.getRenderManager().viewerPosY;
        double entityZ = interpZ - mc.getRenderManager().viewerPosZ;

        double width = entity.width;
        double height = entity.height;

        double outlineWidth = 0.1;

        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);

        GlStateManager.color(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, 0.5F);
        GL11.glLineWidth(2.0F); // line width

        GlStateManager.disableDepth();
        GlStateManager.depthMask(false);

        GlStateManager.pushMatrix();
        RenderHelper.disableStandardItemLighting();

        GL11.glBegin(GL11.GL_LINES);

        // bottom lines
        GL11.glVertex3d(entityX - width / 2 - outlineWidth, entityY, entityZ - width / 2 - outlineWidth);
        GL11.glVertex3d(entityX + width / 2 + outlineWidth, entityY, entityZ - width / 2 - outlineWidth);

        GL11.glVertex3d(entityX + width / 2 + outlineWidth, entityY, entityZ - width / 2 - outlineWidth);
        GL11.glVertex3d(entityX + width / 2 + outlineWidth, entityY, entityZ + width / 2 + outlineWidth);

        GL11.glVertex3d(entityX + width / 2 + outlineWidth, entityY, entityZ + width / 2 + outlineWidth);
        GL11.glVertex3d(entityX - width / 2 - outlineWidth, entityY, entityZ + width / 2 + outlineWidth);

        GL11.glVertex3d(entityX - width / 2 - outlineWidth, entityY, entityZ + width / 2 + outlineWidth);
        GL11.glVertex3d(entityX - width / 2 - outlineWidth, entityY, entityZ - width / 2 - outlineWidth);

        // top lines
        GL11.glVertex3d(entityX - width / 2 - outlineWidth, entityY + height + outlineWidth, entityZ - width / 2 - outlineWidth);
        GL11.glVertex3d(entityX + width / 2 + outlineWidth, entityY + height + outlineWidth, entityZ - width / 2 - outlineWidth);

        GL11.glVertex3d(entityX + width / 2 + outlineWidth, entityY + height + outlineWidth, entityZ - width / 2 - outlineWidth);
        GL11.glVertex3d(entityX + width / 2 + outlineWidth, entityY + height + outlineWidth, entityZ + width / 2 + outlineWidth);

        GL11.glVertex3d(entityX + width / 2 + outlineWidth, entityY + height + outlineWidth, entityZ + width / 2 + outlineWidth);
        GL11.glVertex3d(entityX - width / 2 - outlineWidth, entityY + height + outlineWidth, entityZ + width / 2 + outlineWidth);

        GL11.glVertex3d(entityX - width / 2 - outlineWidth, entityY + height + outlineWidth, entityZ + width / 2 + outlineWidth);
        GL11.glVertex3d(entityX - width / 2 - outlineWidth, entityY + height + outlineWidth, entityZ - width / 2 - outlineWidth);

        // vertical lines
        GL11.glVertex3d(entityX - width / 2 - outlineWidth, entityY, entityZ - width / 2 - outlineWidth);
        GL11.glVertex3d(entityX - width / 2 - outlineWidth, entityY + height + outlineWidth, entityZ - width / 2 - outlineWidth);

        GL11.glVertex3d(entityX + width / 2 + outlineWidth, entityY, entityZ - width / 2 - outlineWidth);
        GL11.glVertex3d(entityX + width / 2 + outlineWidth, entityY + height + outlineWidth, entityZ - width / 2 - outlineWidth);

        GL11.glVertex3d(entityX + width / 2 + outlineWidth, entityY, entityZ + width / 2 + outlineWidth);
        GL11.glVertex3d(entityX + width / 2 + outlineWidth, entityY + height + outlineWidth, entityZ + width / 2 + outlineWidth);

        GL11.glVertex3d(entityX - width / 2 - outlineWidth, entityY, entityZ + width / 2 + outlineWidth);
        GL11.glVertex3d(entityX - width / 2 - outlineWidth, entityY + height + outlineWidth, entityZ + width / 2 + outlineWidth);

        GL11.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.enableDepth();
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();

        GlStateManager.popMatrix();
    }

    public static void drawTracer(Vec3 from, Vec3 to, Color color) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.disableTexture2D();

        RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();

        double renderPosX = to.xCoord - renderManager.viewerPosX;
        double renderPosY = to.yCoord - renderManager.viewerPosY;
        double renderPosZ = to.zCoord - renderManager.viewerPosZ;

        GL11.glLineWidth(2.0f);
        GL11.glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3d(from.xCoord, from.yCoord, from.zCoord);
        GL11.glVertex3d(renderPosX, renderPosY, renderPosZ);
        GL11.glEnd();

        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.resetColor();
        GlStateManager.popMatrix();
    }

    public static void drawTracer(Vec3 to, Color color) {
        drawTracer(new Vec3(0, Minecraft.getMinecraft().thePlayer.getEyeHeight(), 0), to, color);
    }

    public void setColor(String color) {
        this.color = Color.decode(color);
    }

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
