package me.Xaidinatorx2000X.KillerBlood;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;


public class Main extends JavaPlugin implements Listener {
    private static Plugin instance;

    public void onEnable() {
        instance = this;
        getConfig().options().copyDefaults(true);
        saveConfig();
        Server server = getServer();
        ConsoleCommandSender console = server.getConsoleSender();

        if(getConfig().getString( "Send Stats" ).equals("enabled" ) ) {
            try {
                Metrics metrics = new Metrics(this);
                metrics.start();
                console.sendMessage("MC Stats (Metrics) is enabled!");
            } catch (IOException e) {
                // Failed to submit the stats :-( <-- Dat face doe
            }
        }
        Bukkit.getPluginManager().registerEvents(new OnDamage(), this);

        console.sendMessage(ChatColor.BLUE + "========== KillerBlood! ============");
        console.sendMessage(ChatColor.BLUE + "=========== VERSION: 1.0 ===========");
        console.sendMessage(ChatColor.BLUE + "======== BY Xaidinatorx2000X! ========");
    }

    public void onDisable() {
        getLogger().info(getDescription().getName() + " has been disabled!");
    }

    public static Plugin getInstance() {
        return instance;
    }
}
