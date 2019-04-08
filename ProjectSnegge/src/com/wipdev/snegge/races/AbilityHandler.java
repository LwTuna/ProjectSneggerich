package com.wipdev.snegge.races;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

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
	
	@EventHandler
	public void removeStealth(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Player) {
			Player damager = (Player) event.getDamager();
			if(RaceManager.getRace(damager).getId() ==RaceManager.ELF_RACE_ID){
				if(damager.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
					damager.removePotionEffect(PotionEffectType.INVISIBILITY);
					for(Player pl:damager.getWorld().getPlayers()) {
						pl.showPlayer(plugin,damager);
					}
					event.setDamage(event.getDamage() * ElfRace.DAMGAGE_STEALTH_MULTIPLIER);
				}
			}
		}
	}
	
}
