package me.louiletsplaypro.easycmds.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetGM implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Dieser CMD kann nur ein Spieler ausführen");
        }else{
            if(args.length != 0){
                if(args[0].equals("0") || args[0].equalsIgnoreCase("s")){
                    Player player = ((Player) sender).getPlayer();
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(ChatColor.GREEN + "Du bist nun im Survival");
                }else if(args[0].equals("1") || args[0].equalsIgnoreCase("c")){
                    Player player = ((Player) sender).getPlayer();
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage(ChatColor.GREEN + "Du bist nun im Creative");
                }else if(args[0].equals("3") || args[0].equalsIgnoreCase("z")){
                    Player player = ((Player) sender).getPlayer();
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(ChatColor.GREEN + "Du bist nun im Spectator");
                }else if(args[0].equals("2") || args[0].equalsIgnoreCase("a")){
                    Player player = ((Player) sender).getPlayer();
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage(ChatColor.GREEN + "Du bist nun im Adventure");
                }else{
                    sender.sendMessage(ChatColor.RED + "Nutze für diesen CMD /gm < 0 | 1 | 2 | 3 | s | c | a | z >");
                }
            }else{
                sender.sendMessage(ChatColor.RED + "Nutze für diesen CMD /gm < 0 | 1 | 2 | 3 | s | c | a | z >");
            }
        }
        return false;
    }
}
