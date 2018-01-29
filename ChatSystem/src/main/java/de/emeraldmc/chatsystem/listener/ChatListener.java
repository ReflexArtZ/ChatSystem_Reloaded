package de.emeraldmc.chatsystem.listener;

import de.emeraldmc.chatsystem.*;
import de.emeraldmc.chatsystem.utils.ChatAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String message = e.getMessage();

        // --- cancel to send the message (will be broadcasted later) ---
        e.setCancelled(true);

        // --- converting and broadcasting message ---
        ChatMessage chatMessage = new ChatMessage(player, message);
        if (!Main.cacheHashMap.containsKey(player)) {
            Main.cacheHashMap.put(player, new PlayerCache());
            Main.cacheHashMap.get(player).getMessageQueue().put(new ChatMessageLight(message));
            chatMessage.send();
            return;
        }
        ChatIssue issue = Main.cacheHashMap.get(player).checkMessage(new ChatMessageLight(message));
        Main.cacheHashMap.get(player).getMessageQueue().put(new ChatMessageLight(message));
        if (issue == ChatIssue.SPAM) {
            ChatAPI.sendMessage(player, Main.configuration.CHAT_SPAM);
            return;
        } else if (issue == ChatIssue.AD) {
            ChatAPI.sendMessage(player, Main.configuration.CHAT_AD);
            return;
        } else if (issue == ChatIssue.TIME) {
            ChatAPI.sendMessage(player, Main.configuration.CHAT_TIME);
            return;
        } else if (issue == ChatIssue.CAPS) {
            ChatAPI.sendMessage(player, Main.configuration.CHAT_CAPS);
            chatMessage.setMessage(chatMessage.getMessage().toLowerCase());
            chatMessage.send();
        } else {
            chatMessage.send();
        }
    }
}
