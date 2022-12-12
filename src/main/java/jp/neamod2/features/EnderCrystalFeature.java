package jp.neamod2.features;

import jp.neamod2.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;

import java.awt.*;

public class EnderCrystalFeature {

    public static void onRenderLivingPre() {
        for (final Entity e : Minecraft.getMinecraft().theWorld.loadedEntityList) {
            if (e instanceof EntityEnderCrystal) {
                RenderUtils.drawOutlinedBoundingBox(e.getEntityBoundingBox(), Color.yellow, 3, 1f);
            }
        }

    }
}
