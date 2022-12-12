package jp.neamod2.handlers;

import jp.neamod2.features.DungeonFeature;
import jp.neamod2.features.EnderCrystalFeature;
import jp.neamod2.features.MessageFeature;
import jp.neamod2.gui.NeaGui;
import jp.neamod2.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventHandler {

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
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
    public void onRenderLivingPre(RenderLivingEvent.Pre<EntityLivingBase> event) {
        if (Utils.isDungeonEsp) {
            DungeonFeature.onRenderLivingPre(event);
        }
        if (Utils.isEnderCrystalEsp) {
            EnderCrystalFeature.onRenderLivingPre();
        }
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String message = event.message.getFormattedText();

        // Boop! 自動返信
        if (message.startsWith("§dFrom") && message.endsWith("§r§d§lBoop!§r") && Utils.isAutoBoop) {
            MessageFeature.boop(message);
        }

        if (Utils.isMsgTimer) {
            LocalDateTime nowDate = LocalDateTime.now();
            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH:mm"); //時間:分
            String time_str = dtf1.format(nowDate);
            event.message = new ChatComponentText(String.format(" §a<%s> ", time_str)).appendSibling(event.message);
        }
    }
}
