package de.emeraldmc.chatsystem.io;

import de.emeraldmc.chatsystem.Main;

public class Config {
    public final boolean DEBUG;

    public final String CHAT_FORMAT;

    public final String PREFIX;
    public final String CHAT_CLEARED;
    public final String NO_PERMISSION;
    public final String CHAT_SPAM;
    public final String CHAT_CAPS;
    public final String CHAT_AD;
    public final String CHAT_TIME;

    public final int MIN_LEVENSTHEIN_DISTANCE;
    public final int FREQ_MAX_MIN_LEVENSTHEIN_DISTANCE;
    public final int MAX_CAPS;
    public final int MIN_TIME_DIFF;


    /**
     *
     */
    public Config() {
        DEBUG = configBoolean("Config.debug");

        CHAT_FORMAT = configString("Config.chatFormat");

        PREFIX = configString("Config.prefix");
        CHAT_CLEARED = configString("Config.chatCleared");
        NO_PERMISSION = configString("Config.noPermission");
        CHAT_SPAM = configString("Config.chatSpam");
        CHAT_CAPS = configString("Config.chatCaps");
        CHAT_AD = configString("Config.chatAd");
        CHAT_TIME = configString("Config.chatTime");

        MIN_LEVENSTHEIN_DISTANCE = configInt("Config.minLevenshteinDistance");
        FREQ_MAX_MIN_LEVENSTHEIN_DISTANCE = configInt("Config.maxMinLevenshteinDistance");
        MAX_CAPS = configInt("Config.maxCaps");
        MIN_TIME_DIFF = configInt("Config.minTimeDiff");
    }

    private boolean configBoolean(String path) {
        return Main.getInstance().getConfig().getBoolean(path);
    }

    private String configString(String path) {
        return Main.getInstance().getConfig().getString(path);
    }

    private int configInt(String path) {
        return Main.getInstance().getConfig().getInt(path);
    }
}
