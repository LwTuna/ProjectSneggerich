package com.wipdev.snegge.races; 

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.wipdev.snegge.items.ItemUtils;

public class ElfRace extends Race{

	private final int stealthDuration=5;
	private JavaPlugin plugin;
	public static final double DAMGAGE_STEALTH_MULTIPLIER = 2;
	
	public ElfRace(JavaPlugin plugin) {
		super("Elf", ChatColor.LIGHT_PURPLE+"[Elf]",ItemUtils.createItem(Material.BOW, 1, "[Elf]"),10);
		this.plugin = plugin;
		RaceManager.ELF_RACE_ID = id;
	}

	@Override
	public void onAbility(final PlayerInteractEvent event) {
		event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(),Effect.SMOKE,2);
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, stealthDuration*20, 1));
		for(Player pl:event.getPlayer().getWorld().getPlayers()) {
			if(pl.equals(event.getPlayer())) {
				continue;
			}
			pl.hidePlayer(plugin,event.getPlayer());
		}
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			
			public void run() {
				for(Player pl:event.getPlayer().getWorld().getPlayers()) {
					pl.showPlayer(plugin,event.getPlayer());
				}
				
			}
		}, stealthDuration*20);
	}

	@Override
	public void applyPermEffects(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 0));
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
		
	}

}
