package com.wipdev.snegge.races;

import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TestRace extends Race{

	public TestRace() {
		super("Test Race", "Test Race");
	}

	@Override
	public void onAbility(PlayerInteractEvent event) {
		event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(),Effect.GHAST_SHRIEK,2);
		
	}

	@Override
	public void applyPermEffects(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
		
	}

}
