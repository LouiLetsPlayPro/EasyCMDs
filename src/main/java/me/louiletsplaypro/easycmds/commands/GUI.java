package me.louiletsplaypro.easycmds.commands;

import jdk.internal.icu.impl.CharTrie;
import me.louiletsplaypro.easycmds.Easycmds;
import me.louiletsplaypro.easycmds.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import me.louiletsplaypro.easycmds.utils.ItemBuilder.*;

public class GUI implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Dieser CMD ist Playeronly!");
        }else{

            Player player = (Player) sender;
            Inventory inventar = Bukkit.createInventory(null, 9, "         " + ChatColor.BOLD + ChatColor.RED + "Easy" + ChatColor.DARK_BLUE + "CMD's" + ChatColor.GOLD + " Utilitys");

            inventar.setItem(1, new ItemBuilder(Material.PAPER).setDisplayname(ChatColor.BLUE + "Spieler Inventar").setLore(ChatColor.GRAY + "Schau in das Inventar von anderen Spielern", ChatColor.GRAY + "welche Online sind", ChatColor.BLACK + "Nur bis zu 54 Spieler möglich").setLocalizedName("invsee?Player").build());
            inventar.setItem(3, new ItemBuilder(Material.BOOK).setDisplayname(ChatColor.BLUE + "Spieler Information").setLore(ChatColor.GRAY + "Schau dir Spielerinformationen an,", ChatColor.GRAY + "von Spielern welche Online sind").setLocalizedName("playerinfo?Player").build());
            inventar.setItem(5, new ItemBuilder(Material.COMMAND_BLOCK).setDisplayname(ChatColor.BLUE + "Gamemode Switcher").setLore(ChatColor.GRAY + "Ändere deinen Spielmodus"," ", ChatColor.GRAY + "Dein Aktueller Spielmodus:", ChatColor.DARK_AQUA + "         " + player.getGameMode().name()).setLocalizedName("gamemodeswitcher").build());

            if(Easycmds.getPlugin().getConfig().getBoolean("snowball.enabled")){
                inventar.setItem(7, new ItemBuilder(Material.SNOWBALL).setDisplayname(ChatColor.BLUE + "Schneeballschlacht switcher").setLore(ChatColor.GRAY + "Aktiviere / " + ChatColor.GREEN + " Deaktiviere " + ChatColor.GRAY + " die Schneeballschlacht", ChatColor.RED + "Noch nicht fertig!").setLocalizedName("deaktivateSnowball").build());
            }else{
                inventar.setItem(7, new ItemBuilder(Material.SNOWBALL).setDisplayname(ChatColor.BLUE + "Schneeballschlacht switcher").setLore(ChatColor.GREEN + "Aktiviere " + ChatColor.GRAY + "/ Deaktiviere die Schneeballschlacht", ChatColor.RED + "Noch nicht fertig!").setLocalizedName("aktivateSnowball").build());
            }
            player.openInventory(inventar);
        }
        return false;
    }
}
