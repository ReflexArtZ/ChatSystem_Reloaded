package de.emeraldmc.chatsystem;


import com.github.cheesesoftware.PowerfulPermsAPI.PermissionPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatMessage {
    public final static String CHAT_FORMAT = Main.configuration.CHAT_FORMAT;

    private Player player;
    private PermissionPlayer permissionPlayer;

    private String message;

    /**
     * Constructor
     * @param player
     * @param message
     */
    public ChatMessage(Player player, String message) {
        this.player = player;
        this.message = message;
        permissionPlayer = Main.PM.getPermissionPlayer(player.getUniqueId());
    }

    /**
     * send formatted message
     */
    public void send() {
        String s = CHAT_FORMAT
                .replaceAll("%prefix", permissionPlayer.getPrefix())
                .replaceAll("%player", player.getDisplayName())
                .replaceAll("%suffix", permissionPlayer.getSuffix())
                .replaceAll("%message", message);
        s = ChatColor.translateAlternateColorCodes('&', s);
        Bukkit.broadcastMessage(s);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
