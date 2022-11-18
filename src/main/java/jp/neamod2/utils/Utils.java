package jp.neamod2.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Utils {
    public static String prefix = "§bNeaMod2 > §f"; // chat送信時のprefix
    public static String guiToOpen = null;
    public static boolean isAutoBoop=true;
    public static boolean isDungeonEsp =false;
    public static boolean isEnderCrystalEsp=false;
    public static boolean isMsgTimer =false;

    public static void addChatMessage(String message)
    {
        Minecraft.getMinecraft().thePlayer.addChatMessage((new ChatComponentText(String.format("%s %s",prefix, message))));
    }

    public static String getPlayerName(String content)
    {
        String[] messageList = content.split(" ");
        String playerName = messageList[1];
        if (messageList[1].contains("["))
        {
            playerName = messageList[2];
        }
        return playerName.replace(":", "");
    }

    public static String getColouredBoolean(boolean bool) {
        return bool ? EnumChatFormatting.GREEN + "On" : EnumChatFormatting.RED + "Off";
    }

}
