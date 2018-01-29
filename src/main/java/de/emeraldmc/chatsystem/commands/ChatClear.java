package de.emeraldmc.chatsystem.commands;

import com.sun.istack.internal.Nullable;
import de.emeraldmc.chatsystem.Main;
import de.emeraldmc.chatsystem.utils.ChatAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChatClear implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("chatclear")) {
            // --- /chatclear ---
            if (args.length == 0) {
                // --- check if sender has clearchat permission ---
                if (!sender.hasPermission("chat.cc")) ChatAPI.sendNoPermissionMessage(sender);

                clearChat(sender);
                return true;
            }
            // --- /chatclear anonymous
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("anonymous") || args[0].equalsIgnoreCase("anon")) {
                    // --- check if sender has clearchat permission ---
                    if (!sender.hasPermission("chat.cc")) ChatAPI.sendNoPermissionMessage(sender);

                    clearChat(null);
                    return true;
                }
            }

            // --- check if sender have permission, if so send the sender the help page
            if (sender.hasPermission("chat.cc")) sendHelp(sender);

            return true;
        }
        return false;
    }
    
    private void clearChat(@Nullable CommandSender sender) {
        if (sender == null ) {
            for (int i = 0; i < 60; i++) Bukkit.broadcastMessage("");
            return;
        }
        for (int i = 0; i < 60; i++) Bukkit.broadcastMessage("");
        ChatAPI.broadcastMessage(Main.configuration.CHAT_CLEARED.replaceAll("%player", sender.getName()));
    }

    private void sendHelp(CommandSender sender) {
        ChatAPI.sendMessage(sender, "&a&m-------------&6ChatSystem&a&m-------------");
        ChatAPI.sendMessage(sender, "&a/cc &8> &7Leert den Chat und gibt dies bekannt");
        ChatAPI.sendMessage(sender, "&a/cc anonymous &8 > &7Leert den Chat ohne dies bekannt zu geben");
    }
}
