package de.emeraldmc.chatsystem;

import com.github.cheesesoftware.PowerfulPermsAPI.PermissionManager;
import com.github.cheesesoftware.PowerfulPermsAPI.PowerfulPermsPlugin;
import de.emeraldmc.chatsystem.commands.ChatClear;
import de.emeraldmc.chatsystem.io.Config;
import de.emeraldmc.chatsystem.listener.ChatListener;
import de.emeraldmc.chatsystem.utils.Debug;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;

public class Main extends JavaPlugin {
    private static Main instance;

    private static PowerfulPermsPlugin PP;
    public static PermissionManager PM;

    public static Config configuration;

    public static HashMap<Player, PlayerCache> cacheHashMap = new HashMap<Player, PlayerCache>();

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
        configuration = new Config();
    }

    @Override
    public void onEnable() {
        File f = new File(getDataFolder()+ File.separator+ "config.yml");
        if(!f.exists())
        {
            this.saveDefaultConfig();
            Debug.print("Saved Default Config");
        }
        this.reloadConfig();

        if (Bukkit.getPluginManager().getPlugin("PowerfulPerms") == null) {
            Bukkit.getConsoleSender().sendMessage("§8[§4-§8] §cNo PowerfulPerms-Plugin found! §7Stopping Server ...");
            Bukkit.shutdown();
        }
        PP = (PowerfulPermsPlugin) Bukkit.getPluginManager().getPlugin("PowerfulPerms");
        PM = PP.getPermissionManager();

        registerEvents();
        registerCommands();
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Debug.print("Registered Listener: &6"+"\t"+"ChatListener");
    }

    private void registerCommands() {
        getCommand("chatclear").setExecutor(new ChatClear());
        Debug.print("Registered Command: &6"+"\t"+"ChatClear");
    }

}
