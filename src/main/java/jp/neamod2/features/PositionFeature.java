package jp.neamod2.features;

import jp.neamod2.utils.RenderUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;

public class PositionFeature {

    public static final PositionFeature INSTANCE = new PositionFeature();

    public static PositionFeature getInstance() {
        return INSTANCE;
    }

    public static Position target = new Position();

    public void RenderWorldLastEvent(RenderWorldLastEvent event) {
        RenderUtils.draw3DWaypointString(event.partialTicks);
    }

    public static class Position {

        public String name;
        public BlockPos pos;

        public String getDistance(EntityPlayer player) {
            return Math.round(player.getDistance(pos.getX(), pos.getY(), pos.getZ())) + "m";
        }

    }

}

