package com.wipdev.snegge.races;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public abstract class Race {
	
	protected static int nextID = 1;
	
	protected int id;
	private String raceName;
	private String chatPrefix;
	private ItemStack selectorItem;
	
	public Race(String raceName, String chatPrefix,ItemStack selectorItem) {
		this.id = nextID;
		nextID++;
		this.raceName = raceName;
		this.chatPrefix = chatPrefix;
		this.selectorItem = selectorItem;
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

	public ItemStack getSelectorItem() {
		return selectorItem;
	}
	
	
	
}
