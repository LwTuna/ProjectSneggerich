package com.wipdev.snegge.races;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class AbilityHandler implements Listener{

	@EventHandler
	public void onItemUse(PlayerInteractEvent event) {
		if(event.getPlayer().getInventory().getItemInMainHand() != null) {
			if(event.getPlayer().getInventory().getItemInMainHand().getType() == Material.ENDER_EYE) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					handleAbility(event);
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
	
	
}
