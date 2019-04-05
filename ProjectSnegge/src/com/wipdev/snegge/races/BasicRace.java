package com.wipdev.snegge.races;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;

public abstract class BasicRace {

	private int id;
	private String raceName;
	private String chatPrefix;
	private List<PotionEffect> permEffects = new ArrayList<PotionEffect>();
	
	public BasicRace(int id, String raceName, String chatPrefix, List<PotionEffect> permEffects) {
		this.id = id;
		this.raceName = raceName;
		this.chatPrefix = chatPrefix;
		this.permEffects = permEffects;
	}
	
	public abstract void onAbility(PlayerInteractEvent event);
	
	
	public void applyEffects(Player player) {
		player.addPotionEffects(permEffects);
	}

	public int getId() {
		return id;
	}

	public String getRaceName() {
		return raceName;
	}

	public String getChatPrefix() {
		return chatPrefix;
	}
	
	
	
}
