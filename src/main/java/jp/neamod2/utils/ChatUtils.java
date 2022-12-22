package jp.neamod2.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class ChatUtils {

    public static void addChatMessage(String message) {
        if (Minecraft.getMinecraft().thePlayer != null) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(String.format("%s %s", Utils.prefix, message)));
        } else {
            Utils.println(message);
        }
    }

    public static String getPlayerName(String content) {
        String[] messageList = content.split(" ");
        String playerName = messageList[1];
        if (messageList[1].contains("[")) {
            playerName = messageList[2];
        }
        return playerName.replace("§r§7", "").replace(":", "");
    }
}
