package jp.neamod2.utils;


import net.minecraft.util.EnumChatFormatting;

public class Utils {
    public static String prefix = "§bNeaMod2 > §f"; // chat送信時のprefix
    public static String guiToOpen = null;

    public static String getColouredBoolean(boolean bool) {
        return bool ? EnumChatFormatting.GREEN + "On" : EnumChatFormatting.RED + "Off";
    }

    public static void println(String content) {
        System.out.println("NeaMod2<println> ->" + content);
    }

}
