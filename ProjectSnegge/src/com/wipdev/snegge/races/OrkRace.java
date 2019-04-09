package com.wipdev.snegge.races;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.wipdev.snegge.items.ItemUtils;

public class OrkRace extends Race{

	private final int duration = 8;
	
	public OrkRace() {
		super("Ork", ChatColor.DARK_GREEN+"[ORK]", ItemUtils.createItem(Material.IRON_AXE, 1, "[ORK]"), 30);
	}

	@Override
	public void onAbility(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(),Effect.ENDERDRAGON_GROWL,2);
		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, duration, 1));
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, duration, 1));
	}

	@Override
	public void applyPermEffects(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
		
	}

	
	
}
