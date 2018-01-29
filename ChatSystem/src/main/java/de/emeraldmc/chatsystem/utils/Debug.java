package de.emeraldmc.chatsystem.utils;

import de.emeraldmc.chatsystem.Main;
import org.bukkit.Bukkit;

public class Debug {
    public static void print(String s) {
        if (Main.configuration.DEBUG) {
            Bukkit.getConsoleSender().sendMessage(ChatAPI.translateColor("&aChatSystem &8 > "+s));
        }
    }
}
