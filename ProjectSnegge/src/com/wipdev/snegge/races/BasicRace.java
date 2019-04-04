package com.wipdev.snegge.races;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Effect;

public abstract class BasicRace {

	private int id;
	private String raceName;
	private String chatPrefix;
	private Map<Effect,Integer> permEffects = new HashMap<Effect,Integer>();
	
	public BasicRace(int id, String raceName, String chatPrefix, Map<Effect, Integer> permEffects) {
		this.id = id;
		this.raceName = raceName;
		this.chatPrefix = chatPrefix;
		this.permEffects = permEffects;
	}
	
	public abstract void onAbility();
	
}
