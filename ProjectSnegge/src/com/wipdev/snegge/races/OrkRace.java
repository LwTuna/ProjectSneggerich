package com.wipdev.snegge.races;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.wipdev.snegge.SneggenPlugin;
import com.wipdev.snegge.items.ItemUtils;

public class OrkRace extends Race{

	private final int duration = 8;
	
	public OrkRace() {
		super("Ork", ChatColor.DARK_GREEN+"[ORK]", ItemUtils.createItem(Material.IRON_AXE, 1, "[ORK]"), 30);
	}

	@Override
	public void onAbility(final PlayerInteractEvent event) {
		Player player = event.getPlayer();
		event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(),Effect.ENDERDRAGON_GROWL,2);
		player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, duration*20, 1));
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, duration*20, 1));
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(SneggenPlugin.instance, new Runnable() {
			
			public void run() {
				applyPermEffects(event.getPlayer());
				
			}
		}, duration*20 +5);
	}

	@Override
	public void applyPermEffects(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
		
	}

	
	
}
