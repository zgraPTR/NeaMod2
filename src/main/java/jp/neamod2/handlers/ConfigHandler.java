package jp.neamod2.handlers;

import jp.neamod2.utils.Utils;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {

    public static Configuration configuration;
    private final static String configFile = "config/NeaMod2.cfg";


    public static boolean getBoolean(String category, String key) {
        configuration.load();
        if (configuration.getCategory(category).containsKey(key)) {
            return configuration.get(category, key, false).getBoolean();
        }
        return true;
    }

    public static boolean hasKey(String category, String key) {
        configuration.load();
        try {
            if (!configuration.hasCategory(category)) return false;
            return configuration.getCategory(category).containsKey(key);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            configuration.save();
        }
        return false;
    }

    public static void init() {
        configuration = new Configuration(new File(configFile));
        try {
            configuration.load();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            configuration.save();
        }
    }

    public static boolean initBoolean(String category, String key, boolean defaultValue) {
        if (!hasKey(category, key)) {
            writeBool(category, key, defaultValue);
            return defaultValue;
        } else {
            return getBoolean(category, key);
        }
    }

    public static void loadConfig() {
        init();
        Utils.isAutoBoop = initBoolean("message", "AutoBoop", true);
        Utils.isDungeonEsp = initBoolean("dungeon", "DnEsp", false);
        Utils.isEnderCrystalEsp = initBoolean("block", "EnderCrystalEsp", false);
        Utils.isMsgTimer = initBoolean("message", "MsgTimer", false);
    }


    public static void writeBool(String category, String key, Boolean value) {
        configuration.load();
        try {
            configuration.getCategory(category).get(key).set(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            configuration.save();
        }
    }
}
