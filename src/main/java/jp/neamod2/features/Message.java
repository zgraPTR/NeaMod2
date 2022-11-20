package jp.neamod2.features;

import jp.neamod2.utils.ChatUtils;
import net.minecraft.client.Minecraft;

public class Message {

    public static void boop(String message) {
        new Thread(() ->
        {
            try {
                Thread.sleep(100);
                String playerName = ChatUtils.getPlayerName(message);
                Minecraft.getMinecraft().thePlayer.sendChatMessage(String.format("/boop %s", playerName));
            } catch (InterruptedException e) {

            }
        }).start();
    }

}
