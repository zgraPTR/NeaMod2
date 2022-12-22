package jp.neamod2.features;

import jp.neamod2.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;

import java.awt.*;

public class EnderCrystalFeature {

    public static final EnderCrystalFeature INSTANCE = new EnderCrystalFeature();

    public static EnderCrystalFeature getInstance() {
        return INSTANCE;
    }

    public void RenderLivingEvent() {

        for (Entity entity : Minecraft.getMinecraft().theWorld.loadedEntityList) {
            if (entity instanceof EntityEnderCrystal) {
                RenderUtils.drawOutlinedBoundingBox(entity.getEntityBoundingBox(), Color.YELLOW, 2f);
            }
        }
    }
}
