package com.wipdev.snegge.permissions;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public final class PermissionSystem {

	
	
	//Mapping the UUID of a Player as a String to Role on the Server
	private static Map<String,ServerRole> roles = new HashMap<String,ServerRole>();
	
	public static void init() {
		//loadDataFromFile or Database
		//fill roles map
	}
	
	public static void save() {
		//Save the list to load up later
	}
	
	public static void addPlayer(Player player,ServerRole role) {
		roles.put(player.getUniqueId().toString(), role);
	}
	
	public static int getPermissionsOfPlayer(Player player) {
		return roles.get(player.getUniqueId().toString()).getId();
	}
	public static ServerRole getServerRole(Player player) {
		return roles.get(player.getUniqueId().toString());
	}
	
	/*
	 * @return returns true if a entry in the roles list exists, returns false if player has no entry in the list.
	 */
	public static boolean playerExist(Player player) {
		return roles.containsKey(player.getUniqueId().toString());
	}
}
