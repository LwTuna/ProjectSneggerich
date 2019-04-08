package com.wipdev.snegge.races;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.wipdev.snegge.items.ItemUtils;

public class TestRace extends Race{

	public TestRace() {
		super("Dwarf Race", ChatColor.LIGHT_PURPLE+"[Dwarf Race]",ItemUtils.createItem(Material.WHITE_WOOL, 1, "[Dwarf Race]"));
	}

	@Override
	public void onAbility(PlayerInteractEvent event) {
		event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(),Effect.VILLAGER_HAGGLE,2);
		
	}

	@Override
	public void applyPermEffects(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, Integer.MAX_VALUE, 1));
		
	}

}