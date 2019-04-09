package com.wipdev.snegge.races;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.wipdev.snegge.items.ItemUtils;

public class DwarfRace extends Race{

	public DwarfRace() {
		super("Dwarf", ChatColor.LIGHT_PURPLE+"[DWARF]",ItemUtils.createItem(Material.IRON_PICKAXE, 1, "[Dwarf]"),30);
	}

	@Override
	public void onAbility(PlayerInteractEvent event) {
		event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(),Effect.ZOMBIE_DESTROY_DOOR,2);
		ThrownPotion potion = event.getPlayer().launchProjectile(ThrownPotion.class);
		potion.setItem(getPotion());
	}

	@Override
	public void applyPermEffects(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1));
		
	}

	
	private ItemStack getPotion() {
		 ItemStack potion = new ItemStack(Material.SPLASH_POTION, 1);
	     PotionMeta meta = (PotionMeta) potion.getItemMeta();
	     //TODO ADD Confusion Potion effect
	     potion.setItemMeta(meta);
	     return potion;
	}
}
