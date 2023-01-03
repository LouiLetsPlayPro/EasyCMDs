package me.louiletsplaypro.easycmds.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;

public class PermCheck implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent event) {
        Player cmdopener = (Player) event.getWhoClicked();

        if(!cmdopener.hasPermission("easycmds.invsee.edit") || !cmdopener.isOp()){
            Inventory inv = event.getInventory();
            if(inv instanceof PlayerInventory){
                PlayerInventory playerinv = (PlayerInventory)inv;
                if(!playerinv.getHolder().equals(cmdopener)){
                    event.setCancelled(true);
                    cmdopener.sendMessage(ChatColor.RED + "Du hast keine Berechtigung dafür");
                }
            }
        }
    }
}
