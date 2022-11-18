package jp.neamod2.features;

import jp.neamod2.utils.Utils;
import net.minecraft.client.Minecraft;

public class Message {

    public static void boop(String message) {
        new Thread(() ->
        {
            try {
                Thread.sleep(200);
                String playerName = Utils.getPlayerName(message);
                Minecraft.getMinecraft().thePlayer.sendChatMessage(String.format("/boop %s", playerName));
            } catch (InterruptedException e) {

            }
        }).start();
    }

}
