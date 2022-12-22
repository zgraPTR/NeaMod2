package jp.neamod2.handlers;

import jp.neamod2.utils.ChatUtils;
import jp.neamod2.utils.ConfigUtils;
import jp.neamod2.utils.Utils;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {
    static final String configFile = "config/NeaMod2.cfg";
    static Configuration config = new Configuration(new File(configFile));


    public static boolean getBool(String category, String key, boolean defaultValue) {
        config.load();
        try {
            return config.get(category, key, defaultValue).getBoolean();
        } catch (Exception e) {
            e.printStackTrace();
            ChatUtils.addChatMessage("ConfigHandler -> initエラー");
        } finally {
            config.save();
        }
        return false;
    }

    public static int getInt(String category, String key, int defaultValue) {
        config.load();
        try {
            return config.get(category, key, 0).getInt();
        } catch (Exception e) {
            e.printStackTrace();
            ChatUtils.addChatMessage("ConfigHandler -> initエラー");
        } finally {
            config.save();
        }
        return 0;
    }

    public static String getStr(String category, String key, String defaultValue) {
        config.load();
        try {
            return config.get(category, key, "None").getString();
        } catch (Exception e) {
            e.printStackTrace();
            ChatUtils.addChatMessage("ConfigHandler -> initエラー");
        } finally {
            config.save();
        }
        return "None";
    }

    public static void writeBool(String category, String key, boolean value) {
        try {
            boolean set = config.get(category, key, value).getBoolean();
            config.getCategory(category).get(key).set(value);
        } catch (Exception e) {
            e.printStackTrace();
            ChatUtils.addChatMessage("ConfigHandler -> initエラー");
        } finally {
            config.save();
        }
    }

    public static void writeInt(String category, String key, int value) {
        try {
            int set = config.get(category, key, value).getInt();
            config.getCategory(category).get(key).set(value);
        } catch (Exception e) {
            e.printStackTrace();
            ChatUtils.addChatMessage("ConfigHandler -> initエラー");
        } finally {
            config.save();
        }
    }

    public static void writeStr(String category, String key, String value) {
        try {
            String set = config.get(category, key, value).getString();
            config.getCategory(category).get(key).set(value);
        } catch (Exception e) {
            e.printStackTrace();
            ChatUtils.addChatMessage("ConfigHandler -> initエラー");
        } finally {
            config.save();
        }
    }

    static boolean hasKey(String category, String key) {
        config.load();
        try {
            if (!config.hasCategory(category)) return false;
            return config.getCategory(category).containsKey(key);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            config.save();
        }
        return false;
    }

    static void init() {
        try {
            config.load();
        } catch (Exception e) {
            Utils.println("ConfigHandler -> initエラー");
            e.printStackTrace();
        } finally {
            config.save();
        }
    }

    static boolean initBoolean(String category, String key, boolean defaultValue) {
        if (!hasKey(category, key)) {
            writeBool(category, key, defaultValue);
            return defaultValue;
        } else {
            return getBool(category, key, defaultValue);
        }
    }

    public static void load() {
        init();
        ConfigUtils.msgTimer = initBoolean("message", "MsgTimer", false);
        ConfigUtils.replyBoop = initBoolean("message", "ReplyBoop", true);
        ConfigUtils.fishTimer = initBoolean("message", "FishTimer", false);
        ConfigUtils.dungeonEsp = initBoolean("esp", "Dungeon", false);
        ConfigUtils.enderCrystalEsp = initBoolean("esp", "EnderCrystal", false);
    }

}
