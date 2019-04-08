package com.wipdev.snegge.races;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

public class AbilityHandler implements Listener{

	private JavaPlugin plugin;
	
	public static final String abilityItemName = "Class Ability!";
	
	private Map<String,Long> lastAbilityUse = new HashMap<String,Long>();
	
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
		
		if(canUseAbility(event.getPlayer())) {
			race.onAbility(event);
			setCooldown(event.getPlayer());
			event.getPlayer().sendMessage(ChatColor.GREEN+"You have used your ability!");
		}else {
			event.getPlayer().sendMessage(ChatColor.RED+"You can't use your ability yet. Remaining cooldown :"+getRemainingCooldown(event.getPlayer()));
		}
		
	}
	
	private void setCooldown(Player player) {
		lastAbilityUse.put(player.getUniqueId().toString(), System.currentTimeMillis());
	}
	private int getRemainingCooldown(Player player) {
		if(!lastAbilityUse.containsKey(player.getUniqueId().toString())){
			return 0;
		}else {
			long last = lastAbilityUse.get(player.getUniqueId().toString());
			long diff = System.currentTimeMillis() -last;
			int secondsPassed =  (int) (diff /1000);
			Race race = RaceManager.getRace(player);
			return race.getCooldown() - secondsPassed;
			
		}
	}
	private boolean canUseAbility(Player player) {
		if(!lastAbilityUse.containsKey(player.getUniqueId().toString())) {
			return true;
		}else {
			return getRemainingCooldown(player)<=0;
		}
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
					damager.sendMessage(ChatColor.GREEN+"You are now Visible!");
				}
			}
		}
	}
	
}
