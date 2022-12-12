package jp.neamod2.features;

import jp.neamod2.utils.ChatUtils;
import jp.neamod2.utils.Utils;
import net.minecraft.client.Minecraft;

public class MessageFeature {

    public static void boop(String message) {
        new Thread(() ->
        {
            try {
                Thread.sleep(100);
                String playerName = ChatUtils.getPlayerName(message);
                Minecraft.getMinecraft().thePlayer.sendChatMessage(String.format("/boop %s", playerName));
            } catch (InterruptedException e) {
                ChatUtils.addChatMessage("Boop返信エラー!");
            }
        }).start();
    }

}
