package com.wipdev.snegge.races;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class AbilityHandler implements Listener{

	private JavaPlugin plugin;
	
	public static final String abilityItemName = "Class Ability!";
	
	public AbilityHandler(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onItemUse(PlayerInteractEvent event) {
		if(event.getPlayer().getInventory().getItemInMainHand() != null) {
			if(event.getPlayer().getInventory().getItemInMainHand().getType() == Material.ENDER_EYE) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(abilityItemName)) {
						event.setCancelled(true);
						handleAbility(event);
					}
				}
			}
		}
	}

	private void handleAbility(PlayerInteractEvent event) {
		
		Race race = RaceManager.getRace(event.getPlayer());
		if(race == null) {
			event.getPlayer().sendMessage("You have to choose a race first!");
			return;
		}
		race.onAbility(event);
	}
	
	@EventHandler
	public void onPlayerRespawn(final PlayerRespawnEvent event) {
		if(RaceManager.hasRace(event.getPlayer())) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				
				public void run() {
					RaceManager.applyEffect(event.getPlayer());
					
				}
			}, 100);
		}
	}
	
}
