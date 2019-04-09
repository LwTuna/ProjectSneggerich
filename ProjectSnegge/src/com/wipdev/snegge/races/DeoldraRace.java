package com.wipdev.snegge.races;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.wipdev.snegge.SneggenPlugin;
import com.wipdev.snegge.items.ItemUtils;

public class DeoldraRace extends Race{

	private final int duration = 8;
	
	public DeoldraRace() {
		super("Deoldra", "[Deoldra]", ItemUtils.createItem(Material.SHIELD, 1, "[Deoldra]"), 30);
	}

	@Override
	public void onAbility(final PlayerInteractEvent event) {
		Player player = event.getPlayer();
		event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(),Effect.ANVIL_LAND,2);
		player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
		player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, duration*20, 2));
		player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, duration*20, 2));
		player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, duration*20, 0));
		Bukkit.getScheduler().scheduleSyncDelayedTask(SneggenPlugin.instance, new Runnable() {
			
			public void run() {
				applyPermEffects(event.getPlayer());
				
			}
		}, duration*20 +5);
	}

	@Override
	public void applyPermEffects(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
		
	}

}
