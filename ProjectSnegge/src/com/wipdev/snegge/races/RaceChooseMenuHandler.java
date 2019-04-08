package com.wipdev.snegge.races;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.wipdev.snegge.items.ItemUtils;


public class RaceChooseMenuHandler implements Listener{

	private static Inventory inventory;
	private static final String invName = "Choose your Race!";
	
	public RaceChooseMenuHandler() {
		inventory = Bukkit.createInventory(null, 27,invName);
		for(Race race:RaceManager.races) {
			inventory.setItem(race.getId()+8, race.getSelectorItem());
		}
	}
	
	@EventHandler
	public void handleRaceChooseMenuClick(InventoryClickEvent event) {
		
		if(event.getView().getTitle().equalsIgnoreCase(invName)) {
			event.setCancelled(true);
			if(event.getCurrentItem() != null) {
				if(event.getCurrentItem().getType() != Material.AIR) {
					if(event.getView().getTitle().equalsIgnoreCase(invName)) {
						ItemStack item = event.getCurrentItem();
						for(Race race:RaceManager.races) {
							if(item.getItemMeta().getDisplayName().equals(race.getSelectorItem().getItemMeta().getDisplayName())) {
								RaceManager.setRace((Player)event.getWhoClicked(), race);
								event.getWhoClicked().closeInventory();
								event.getWhoClicked().getInventory().addItem(ItemUtils.createItem(Material.ENDER_EYE, 1, AbilityHandler.abilityItemName));
							}
						}
						
					}
				}
			}
		}
	}
	
	
	public static void openInventory(Player player) {
		player.openInventory(inventory);
	}
}
