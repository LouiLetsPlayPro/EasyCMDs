package me.louiletsplaypro.easycmds.listeners;

import me.louiletsplaypro.easycmds.Easycmds;
import me.louiletsplaypro.easycmds.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


public class SnowballEvents implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onProjectileHit(ProjectileHitEvent event) {
        if(Easycmds.getPlugin().getConfig().getBoolean("snowball.enabled")){
            if(event.getHitEntity() instanceof Player) {
                Player hitplayer = (Player)event.getHitEntity();
                if(event.getEntity() instanceof Snowball){
                    hitplayer.setFreezeTicks(hitplayer.getFreezeTicks() + 30);
                    if(event.getEntity().getShooter() instanceof Player){
                        Player shooter = (Player) event.getEntity().getShooter();
                        shooter.playSound(shooter.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100, 0);
                        onPlayUpdate(shooter.getName());
                    }
                }
            }

        }

    }
    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(Easycmds.getPlugin().getConfig().getBoolean("snowball.enabled")) {
            Player player = event.getPlayer();
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (event.getClickedBlock().getType().equals(Material.SNOW)) {
                    event.getClickedBlock().breakNaturally();
                    player.getInventory().addItem(new ItemStack(Material.SNOWBALL));
                }
            }
        }
    }

    public void onPlayUpdate(String playerName){
        FileConfiguration config = Easycmds.getPlugin().getConfig();
        int playerScore = config.getInt("snowball.score." + playerName);
        playerScore += 1;
        config.set("snowball.enabled", config.getBoolean("snowball.enabled"));
        config.set("snowball.score", config.get("snowball.score"));
        config.set("snowball.score." + playerName, playerScore);
        Easycmds.getPlugin().saveConfig();

        //ScoreboardUtilitys.setScore(ChatColor.GRAY + "Deine Treffer: " + ChatColor.RED + playerScore, 14);

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()){
            if(onlinePlayer.getName().equals(playerName)) {
                onlinePlayer.setScoreboard(ScoreboardUtilitys.getBaseScoreboard(onlinePlayer.getName()));
            }
        }
    }
}

