package me.louiletsplaypro.easycmds.commands;

import com.google.common.collect.Lists;
import me.louiletsplaypro.easycmds.Easycmds;
import me.louiletsplaypro.easycmds.utils.ScoreboardUtilitys;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.ChatPaginator;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class Snowball implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            Player player = (Player)sender;
            if(!player.hasPermission("easycmds.snowball")){
                sender.sendMessage(ChatColor.RED + "Du hast keine Berechtigung für diesen Command");
            }else{
                if(args.length == 0){
                    FileConfiguration config = Easycmds.getPlugin().getConfig();
                    boolean enabled = config.getBoolean("snowball.enabled");
                    if(enabled){
                        config.set("snowball.enabled", false);
                        List<String> scores = Lists.newArrayList();
                        for (Player onlinePlayer : Bukkit.getOnlinePlayers()){
                            onlinePlayer.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
                            scores.add(config.getInt("snowball.score." + onlinePlayer.getName()) + "_" + onlinePlayer.getName());
                            config.set("snowball.score." + onlinePlayer.getName(), 0);
                        }

                        Arrays.sort(new List[]{scores});

                        player.sendMessage(Arrays.toString(new List[]{scores}));

                        Easycmds.getPlugin().saveConfig();
                        player.getWorld().setStorm(false);
                        sender.sendMessage(ChatColor.RED + "Du hast das Snowball System deaktiviert");
                    }else{
                        config.set("snowball.enabled", true);
                        Easycmds.getPlugin().saveConfig();

                        player.getWorld().setStorm(true);
                        player.getWorld().setGameRule(GameRule.RANDOM_TICK_SPEED, 10);

                        for (Player onlinePlayer : Bukkit.getOnlinePlayers()){
                            onlinePlayer.setScoreboard(ScoreboardUtilitys.getBaseScoreboard(onlinePlayer.getName()));
                            onlinePlayer.sendMessage(ChatColor.RED + " ");
                            onlinePlayer.sendMessage(ChatColor.RED + " ");
                            onlinePlayer.sendMessage(ChatColor.BOLD + "" + ChatColor.UNDERLINE + ChatColor.GOLD + "Event - Schneeballschalcht wurde gestertet");
                            onlinePlayer.sendMessage(ChatColor.RED + " ");
                            onlinePlayer.sendMessage(ChatColor.WHITE + "Sammel jetzt mittels eines " + ChatColor.UNDERLINE + "Rechtsklicks" + ChatColor.RESET + ChatColor.WHITE + " Schneebälle vom Boden auf und beschieße damit andere mitspieler.");
                            onlinePlayer.sendMessage(ChatColor.RED + "Der Spielspaß aller spieler geht vor alles! Freundschaften oder Familie dürfen ignoriert werden jedoch zählt es nicht für den Ingame Chat!");
                            onlinePlayer.sendMessage(ChatColor.RED + " ");
                            onlinePlayer.sendMessage(ChatColor.DARK_AQUA + "Viel Spaß!");
                        }

                        sender.sendMessage(ChatColor.GREEN + "Du hast das Snowball System aktiviert");
                    }

                }else{
                    sender.sendMessage(ChatColor.RED + "Nutze für diesen Command nur /snowball");
                    player.getWorld().setGameRule(GameRule.RANDOM_TICK_SPEED, 3);
                }
            }
        }

        return false;
    }
}
