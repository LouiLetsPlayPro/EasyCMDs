package me.louiletsplaypro.easycmds;

import me.louiletsplaypro.easycmds.commands.*;
import me.louiletsplaypro.easycmds.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Easycmds extends JavaPlugin {

    private static  Easycmds plugin;

    @Override
    public void onEnable() {

        // Plugin startup logic
        getLogger().info("Loaded Easy CMD'S");

        saveDefaultConfig();
        plugin = this;

        //register Events
        getServer().getPluginManager().registerEvents(new PermCheck(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinLeftEvent(), this);
        getServer().getPluginManager().registerEvents(new SnowballEvents(), this);
        getServer().getPluginManager().registerEvents(new GUIevents(), this);

        //register commands
        getCommand("gm0").setExecutor(new SetGM0());
        getCommand("gm1").setExecutor(new SetGM1());
        getCommand("gm2").setExecutor(new SetGM2());
        getCommand("gm3").setExecutor(new SetGM3());
        getCommand("gm").setExecutor(new SetGM());
        getCommand("invsee").setExecutor(new Invsee());
        getCommand("snowball").setExecutor(new Snowball());
        getCommand("gui").setExecutor(new GUI());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Unloaded Easy CMD'S");
    }

    public static Easycmds getPlugin() {
        return plugin;
    }
}
