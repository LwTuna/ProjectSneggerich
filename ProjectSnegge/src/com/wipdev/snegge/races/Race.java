package com.wipdev.snegge.races;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;

public abstract class Race {
	
	protected static int nextID = 1;
	
	private int id;
	private String raceName;
	private String chatPrefix;
	
	public Race(String raceName, String chatPrefix) {
		this.id = nextID;
		nextID++;
		this.raceName = raceName;
		this.chatPrefix = chatPrefix;
	}
	
	public abstract void onAbility(PlayerInteractEvent event);
	
	public abstract void applyPermEffects(Player player);
	

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
