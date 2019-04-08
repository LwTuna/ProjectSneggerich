package com.wipdev.snegge.races;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.wipdev.snegge.items.ItemUtils;

public class DwarfRace extends Race{

	public DwarfRace() {
		super("Dwarf", ChatColor.LIGHT_PURPLE+"[Dwarf]",ItemUtils.createItem(Material.IRON_PICKAXE, 1, "[Dwarf]"),30);
	}

	@Override
	public void onAbility(PlayerInteractEvent event) {
		event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(),Effect.ZOMBIE_DESTROY_DOOR,2);
		
	}

	@Override
	public void applyPermEffects(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1));
		
	}

}
