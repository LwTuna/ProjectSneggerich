package com.wipdev.snegge.teleport;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import com.wipdev.snegge.SneggenPlugin;
import com.wipdev.snegge.items.ItemUtils;

public class WorldTeleportUIManager implements Listener{

	private JavaPlugin plugin;
	
	
	
	public WorldTeleportUIManager(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	public void onCompassUse(PlayerInteractEvent event) {
		if(event.getPlayer().getInventory().getItemInMainHand() != null) {
			if(event.getPlayer().getInventory().getItemInMainHand().getType() == Material.COMPASS) {
				Inventory inv = Bukkit.createInventory(null, 27, "Teleport");
				for(int i=1;i<=SneggenPlugin.worldamt;i++) {
					inv.setItem(i-1, ItemUtils.createItem(Material.DIRT,1,"Floor "+i));
				}
				event.getPlayer().openInventory(inv);
			}
		}
	}
	@EventHandler
	public void onInventoryClick(final InventoryClickEvent event) {
		
		if(event.getView().getTitle().equalsIgnoreCase("Teleport")) {
			event.setCancelled(true);
			if(event.getCurrentItem() != null) {
				if(event.getCurrentItem().getType() != Material.AIR) {
					
					if(event.getView().getTitle().equalsIgnoreCase("Teleport")) {
						String itemname = event.getCurrentItem().getItemMeta().getDisplayName();
						String floor = itemname.split("\\s+")[1];
						
						final Location loc = event.getWhoClicked().getLocation();
						loc.setWorld(Bukkit.getServer().getWorld("floor"+floor));
						loc.setX(0);
						loc.setY(65);
						loc.setZ(0);
						event.getWhoClicked().teleport(loc);
						
					}
				}	
			}
		}
		
	}
	
}
