package jp.neamod2.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class RenderUtils {

    public static void drawOutlinedBoundingBox(AxisAlignedBB aabb, Color color, float width, float partialTicks) {
        Entity render = Minecraft.getMinecraft().getRenderViewEntity();

        double realX = render.lastTickPosX + (render.posX - render.lastTickPosX) * partialTicks;
        double realY = render.lastTickPosY + (render.posY - render.lastTickPosY) * partialTicks;
        double realZ = render.lastTickPosZ + (render.posZ - render.lastTickPosZ) * partialTicks;

        GlStateManager.pushMatrix(); //行列スタック?
        GlStateManager.translate(-realX, -realY, -realZ);

        GlStateManager.depthMask(false); //奥の海面が正しく表示されるようになる
        GlStateManager.disableLighting(); //不自然なライティングがなくなる
        GlStateManager.disableTexture2D(); //espを自然に見せる
        GlStateManager.enableBlend(); //奥にあるブロックが真っ白になるのを防ぐ
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glLineWidth(width);

        RenderGlobal.drawOutlinedBoundingBox(aabb, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        GlStateManager.translate(realX, realY, realZ);

        //設定を元に戻す
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }
}
