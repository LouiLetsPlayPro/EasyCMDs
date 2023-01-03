package me.louiletsplaypro.easycmds.commands;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

public class Invsee implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Diese Command kann nur von Spielern ausgeführt werden");
        }else{
            if(args.length != 1){
                sender.sendMessage(ChatColor.RED + "Nutze für diesen Command: /invsee <spieler>");
            }else{
                Player player = getPlayerName(args[0]);

                if(player != null){
                    ((Player)sender).openInventory(player.getInventory());
                }else{
                    sender.sendMessage(ChatColor.RED + "Der Spieler " + args[0] + " ist nicht Online");
                }
            }
        }
        return false;
    }

    private Player getPlayerName(String name){
        for (Player player : Bukkit.getOnlinePlayers()){
            if(player.getName().equals(name)){
                return player;
            }
        }
        return null;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> options = Lists.newArrayList();
        if(sender instanceof Player && args.length == 1){
            options.addAll(
                    Bukkit.getOnlinePlayers()
                            .stream()
                            .filter(
                                    p -> p
                                            .getName()
                                            .toLowerCase()
                                            .startsWith(args[0].toLowerCase()))
                            .map(p -> p.getName())
                            .collect(Collectors.toList()));
        }
        return options;
    }
}
