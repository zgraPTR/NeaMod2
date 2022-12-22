package jp.neamod2.features;

import jp.neamod2.utils.RenderUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.RenderLivingEvent;

import java.awt.*;

public class DungeonFeature {

    public static final DungeonFeature INSTANCE = new DungeonFeature();

    public static DungeonFeature getInstance() {
        return INSTANCE;
    }

    public void onRenderLivingPre(RenderLivingEvent.Pre<EntityLivingBase> event) {
        String name = StringUtils.stripControlCodes(event.entity.getCustomNameTag());
        // fels
        if (event.entity instanceof EntityEnderman) {
            RenderUtils.drawOutlinedBoundingBox(event.entity.getEntityBoundingBox(), Color.CYAN, 1f);
        }
    }

}
