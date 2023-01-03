package me.louiletsplaypro.easycmds.listeners;

import me.louiletsplaypro.easycmds.utils.ScoreboardUtilitys;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinLeftEvent implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {

        event.setJoinMessage(ChatColor.AQUA + event.getPlayer().getName() + ChatColor.GREEN + " hat das Spiel betreten");
        event.getPlayer().setScoreboard(ScoreboardUtilitys.getBaseScoreboard(event.getPlayer().getName()));

    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerQuit(PlayerQuitEvent event) {

        event.setQuitMessage(ChatColor.AQUA + event.getPlayer().getName() + ChatColor.GREEN + " hat das Spiel verlassen");

    }
}
