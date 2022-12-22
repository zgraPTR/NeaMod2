package jp.neamod2.handlers;

import jp.neamod2.features.EnderCrystalFeature;
import jp.neamod2.features.FishFeature;
import jp.neamod2.features.MessageFeature;
import jp.neamod2.features.PositionFeature;
import jp.neamod2.gui.NeaGui;
import jp.neamod2.utils.ChatUtils;
import jp.neamod2.utils.ConfigUtils;
import jp.neamod2.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.time.LocalDateTime;

public class EventHandler {

    @SubscribeEvent
    public void RenderTickEvent(TickEvent.RenderTickEvent event) {
        if (Utils.guiToOpen != null) {
            Minecraft mc = Minecraft.getMinecraft();
            if (Utils.guiToOpen.startsWith("Main")) {
                int page = Character.getNumericValue(Utils.guiToOpen.charAt(Utils.guiToOpen.length() - 1));
                mc.displayGuiScreen(new NeaGui(page, ""));
            }
            Utils.guiToOpen = null;
        }
    }

    @SubscribeEvent
    public void RenderWorldLastEvent(RenderWorldLastEvent event) {
        if (PositionFeature.target.name != null) {
            PositionFeature.getInstance().RenderWorldLastEvent(event);
        }
    }


    @SubscribeEvent
    public void RenderLivingEvent(RenderLivingEvent.Pre<EntityLivingBase> event) {
        /*if (ConfigUtils.dungeonEsp) {
            DungeonFeature.getInstance().onRenderLivingPre(event);
        }*/
        if (ConfigUtils.enderCrystalEsp) {
            EnderCrystalFeature.getInstance().RenderLivingEvent();
        }
    }

    public String tempSound = "";

    @SubscribeEvent
    public void PlaySoundEvent(PlaySoundEvent event) {
        if (Minecraft.getMinecraft().theWorld == null) return;
        String soundName = event.name;
        try {
            if (Minecraft.getMinecraft().thePlayer.fishEntity == null) {

            } else if (soundName.equals("random.splash") || soundName.equals("game.player.swim.splash") && ConfigUtils.fishTimer && Minecraft.getMinecraft().thePlayer.fishEntity.angler.getHeldItem() != null) {
                FishFeature.getInstance().PlaySoundEvent();
            }
        } catch (Exception e) {
            ChatUtils.addChatMessage("釣り検知エラー");
            e.printStackTrace();
        }

    }

    @SubscribeEvent
    public void ClientChatReceivedEvent(ClientChatReceivedEvent event) {
        String message = event.message.getFormattedText();

        if (message.startsWith("§dFrom") && message.endsWith("§r§d§lBoop!§r") && ConfigUtils.replyBoop) {
            MessageFeature.getInstance().boop(message);
        }

        if (ConfigUtils.msgTimer) {
            LocalDateTime nowDate = LocalDateTime.now();
            event.message = new ChatComponentText(String.format(" §a<%tH:%tm> ", nowDate, nowDate)).appendSibling(event.message);
        }
    }
}
