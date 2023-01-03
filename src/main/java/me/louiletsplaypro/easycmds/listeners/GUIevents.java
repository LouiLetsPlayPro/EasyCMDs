package me.louiletsplaypro.easycmds.listeners;

import me.louiletsplaypro.easycmds.Easycmds;
import me.louiletsplaypro.easycmds.utils.ItemBuilder;
import me.louiletsplaypro.easycmds.utils.ScoreboardUtilitys;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.List;
import java.util.stream.Collectors;

public class GUIevents implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent event) {

        if(event.getCurrentItem() == null) return;

        Player player = (Player) event.getWhoClicked();

        if(event.getView().getTitle().equals("         " + ChatColor.BOLD + ChatColor.RED + "Easy" + ChatColor.DARK_BLUE + "CMD's" + ChatColor.GOLD + " Utilitys")){
            event.setCancelled(true);

            if(event.getCurrentItem().getItemMeta().hasLocalizedName()){

                switch (event.getCurrentItem().getItemMeta().getLocalizedName()){
                    case "gamemodeswitcher":
                        Inventory inventar = Bukkit.createInventory(null, 9, "  " + ChatColor.BOLD + ChatColor.RED + "Easy" + ChatColor.DARK_BLUE + "CMD's" + ChatColor.GOLD + " Gamemode Switcher");

                        inventar.setItem(1, new ItemBuilder(Material.IRON_SWORD).setDisplayname(ChatColor.BLUE + "Survival").setLore(ChatColor.GRAY + "Setzte dein Spielmodus auf Survival", " ", ChatColor.DARK_GREEN + "Alternativen:", ChatColor.DARK_GREEN + " - /gm0", ChatColor.DARK_GREEN + " - /gm 0", ChatColor.DARK_GREEN + " - /gm s", ChatColor.DARK_GREEN + " - /gamemode survival").setLocalizedName("survival").build());
                        inventar.setItem(3, new ItemBuilder(Material.GRASS_BLOCK).setDisplayname(ChatColor.BLUE + "Creative").setLore(ChatColor.GRAY + "Setzte dein Spielmodus auf Creative", " ", ChatColor.DARK_GREEN + "Alternativen:", ChatColor.DARK_GREEN + " - /gm1", ChatColor.DARK_GREEN + " - /gm 1", ChatColor.DARK_GREEN + " - /gm c", ChatColor.DARK_GREEN + " - /gamemode creative").setLocalizedName("creative").build());
                        inventar.setItem(5, new ItemBuilder(Material.PAPER).setDisplayname(ChatColor.BLUE + "Adventure").setLore(ChatColor.GRAY + "Setzte dein Spielmodus auf Adventure", " ", ChatColor.DARK_GREEN + "Alternativen:", ChatColor.DARK_GREEN + " - /gm2", ChatColor.DARK_GREEN + " - /gm 2", ChatColor.DARK_GREEN + " - /gm a", ChatColor.DARK_GREEN + " - /gamemode adventure").setLocalizedName("adventure").build());
                        inventar.setItem(7, new ItemBuilder(Material.ENDER_EYE).setDisplayname(ChatColor.BLUE + "Spectator").setLore(ChatColor.GRAY + "Setzte dein Spielmodus auf Zuschauer", " ", ChatColor.DARK_GREEN + "Alternativen:", ChatColor.DARK_GREEN + " - /gm3", ChatColor.DARK_GREEN + " - /gm 3", ChatColor.DARK_GREEN + " - /gm z", ChatColor.DARK_GREEN + " - /gamemode spectator").setLocalizedName("spectator").build());

                        player.openInventory(inventar);
                        break;
                    case "deaktivateSnowball":
                        FileConfiguration config = Easycmds.getPlugin().getConfig();
                        Easycmds.getPlugin().saveDefaultConfig();
                        player.getWorld().setStorm(false);
                        player.sendMessage(ChatColor.RED + "Du hast das Snowball System deaktiviert");
                        player.closeInventory();
                        break;
                    case "aktivateSnowball":
                        FileConfiguration config1 = Easycmds.getPlugin().getConfig();
                        config1.set("snowball.enabled", true);
                        config1.set("snowball.score", config1.get("snowball.score"));
                        Easycmds.getPlugin().saveConfig();
                        player.getWorld().setStorm(true);
                        for (Player onlinePlayer : Bukkit.getOnlinePlayers()){
                            onlinePlayer.setScoreboard(ScoreboardUtilitys.getBaseScoreboard(onlinePlayer.getName()));
                            onlinePlayer.sendMessage(ChatColor.RED + " ");
                            onlinePlayer.sendMessage(ChatColor.RED + " ");
                            onlinePlayer.sendMessage(ChatColor.BOLD  + "" + ChatColor.UNDERLINE + ChatColor.GOLD + "Event - Schneeballschalcht wurde gestertet");
                            onlinePlayer.sendMessage(ChatColor.RED + " ");
                            onlinePlayer.sendMessage(ChatColor.WHITE + "Sammel jetzt mittels eines " + ChatColor.UNDERLINE + "Rechtsklicks" + ChatColor.RESET + ChatColor.WHITE + " Schneebälle vom Boden auf und beschieße damit andere mitspieler.");
                            onlinePlayer.sendMessage(ChatColor.RED + "Der Spielspaß aller spieler geht vor alles! Freundschaften oder Familie dürfen ignoriert werden jedoch zählt es nicht für den Ingame Chat!");
                            onlinePlayer.sendMessage(ChatColor.RED + " ");
                            onlinePlayer.sendMessage(ChatColor.DARK_AQUA + "Viel Spaß!");
                        }
                        player.sendMessage(ChatColor.GREEN + "Du hast das Snowball System aktiviert");
                        player.closeInventory();
                        break;
                    case "invsee?Player":
                        int size = Bukkit.getOnlinePlayers().size();
                        int sizecalc = 54;
                        if(size == 0){
                            player.sendMessage(ChatColor.RED + "Keine Spieler Online!");
                            player.closeInventory();
                            return;
                        }
                        Inventory inventory = Bukkit.createInventory(null, sizecalc, "  " + ChatColor.BOLD + ChatColor.RED + "Easy" + ChatColor.DARK_BLUE + "CMD's" + ChatColor.GOLD + " invsee");
                        int itemplace = 0;

                        for (Player onlinePlayer : Bukkit.getOnlinePlayers()){
                            if(itemplace == 54){
                                    itemplace = 54;
                            }else{
                                inventory.setItem(itemplace, new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(onlinePlayer.getName()).setDisplayname(ChatColor.BLUE + "Show " + onlinePlayer.getName() + "'s Inventar").setLore(ChatColor.GRAY + "Lass dir das Inventar von", ChatColor.GRAY + onlinePlayer.getName(), " ", ChatColor.DARK_GREEN + "Alternativen:",ChatColor.DARK_GREEN + " - /invsee " + onlinePlayer.getName()).setLocalizedName("invsee?" + onlinePlayer.getName()).build());
                                itemplace += 1;
                            }

                        }
                        player.openInventory(inventory);
                }
            }
        }

        if(event.getView().getTitle().equals("  " + ChatColor.BOLD + ChatColor.RED + "Easy" + ChatColor.DARK_BLUE + "CMD's" + ChatColor.GOLD + " Gamemode Switcher")){
            event.setCancelled(true);

            if(event.getCurrentItem().getItemMeta().hasLocalizedName()){
                switch (event.getCurrentItem().getItemMeta().getLocalizedName()){
                    case "survival":
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(ChatColor.GREEN + "Du bist nun im Survival");
                        player.closeInventory();
                        break;
                    case "creative":
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(ChatColor.GREEN + "Du bist nun im Creative");
                        player.closeInventory();
                        break;
                    case "adventure":
                        player.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage(ChatColor.GREEN + "Du bist nun im Adventure");
                        player.closeInventory();
                        break;
                    case "spectator":
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(ChatColor.GREEN + "Du bist nun im Spectator");
                        player.closeInventory();
                        break;
                }
            }
        }

        if(event.getView().getTitle().equals("  " + ChatColor.BOLD + ChatColor.RED + "Easy" + ChatColor.DARK_BLUE + "CMD's" + ChatColor.GOLD + " invsee")){
            event.setCancelled(true);
            if(event.getCurrentItem().getItemMeta().hasLocalizedName()) {
                String invName = event.getCurrentItem().getItemMeta().getLocalizedName().replace("invsee?","");
                Player invPlayer = getPlayerName(invName);

                if(invPlayer != null){
                    player.openInventory(invPlayer.getInventory());
                }else{
                    player.sendMessage(ChatColor.RED + "Der Spieler " + invName + " ist nicht Online");
                }
            }
        }
    }

    private Player getPlayerName(String name){
        for (Player player : Bukkit.getOnlinePlayers()){
            if(player.getName().equals(name)){
                return player;
            }
        }
        return null;
    }
}