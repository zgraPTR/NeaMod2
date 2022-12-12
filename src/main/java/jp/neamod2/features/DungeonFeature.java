package jp.neamod2.features;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderLivingEvent;

public class DungeonFeature {

    public static void onRenderLivingPre(RenderLivingEvent.Pre<EntityLivingBase> event) {
        //String name = StringUtils.stripControlCodes(event.entity.getCustomNameTag());
        // fels
        /*
        if (event.entity instanceof EntityEnderman) {
            RenderUtils.drawOutlinedBoundingBox(event.entity.getEntityBoundingBox(), Color.CYAN, 1, 1f);
        }
         */
    }

}
