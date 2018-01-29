package de.emeraldmc.chatsystem.utils;

import de.emeraldmc.chatsystem.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ChatAPI {
    public final static String prefix = translateColor(Main.configuration.PREFIX);

    public static String translateColor(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    public static void sendNoPermissionMessage(CommandSender sender) {
        sender.sendMessage(prefix+translateColor(Main.configuration.NO_PERMISSION));
    }
    public static void sendMessage(CommandSender sender, String s) {
        sender.sendMessage(prefix+translateColor(s));
    }
    public static void broadcastMessage(String s) {
        Bukkit.broadcastMessage(prefix+translateColor(s));
    }
}
