package jp.neamod2.handlers;

import jp.neamod2.features.EnderCrystal;
import jp.neamod2.gui.NeaGui;
import jp.neamod2.utils.Utils;
import jp.neamod2.features.Dungeon;
import jp.neamod2.features.Message;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
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
            Dungeon.onRenderLivingPre(event);
        }
        if (Utils.isEnderCrystalEsp)
        {
            EnderCrystal.onRenderLivingPre();
        }
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String message = StringUtils.stripControlCodes(event.message.getUnformattedText());

        // Boop! 自動返信
        if (message.startsWith("From") && message.endsWith("Boop!") && Utils.isAutoBoop) {
            Message.boop(message);
        }

        if (Utils.isMsgTimer) {
            LocalDateTime nowDate = LocalDateTime.now();
            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH:mm:ss"); // 2020/12/20 13:32:48.293
            String time_str = dtf1.format(nowDate); // ②
            // メッセージの最初に時間を表示する
            event.message = new ChatComponentText(String.format("§6<%s> §f%s", time_str, message));
        }
    }

    /*
    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        ConfigHandler.onConfigurationChangedEvent(event);
    }
     */


}
