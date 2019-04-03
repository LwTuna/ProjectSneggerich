package com.wipdev.snegge.permissions;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
/**
 * Contains every necessary information about every players permissions/roles on the server
 * @author Jonas
 *
 */
public final class PermissionSystem {

	
	
	//Mapping the UUID of a Player as a String to Role on the Server
	private static Map<String,ServerRole> roles = new HashMap<String,ServerRole>();
	
	/**
	 * initialize the roles map from a file or db
	 * TODO load the file/db to put in values^^
	 */
	public static void init() {}
	
	/**
	 * Save the roles map to a file or db
	 *	TODO Save the roles map...
	 */
	public static void save() {}
	
	/**
	 * Adds a Player to the Roles list
	 *	@param player the player to add to the list
	 *  @param role the role the player should be asigned
	 */
	public static void addPlayer(Player player,ServerRole role) {
		roles.put(player.getUniqueId().toString(), role);
	}
	
	/**
	 * Gets the int id of the player role
	 * @param player the player which should be checked
	 * @return the id of the players role
	 */
	public static int getPermissionsOfPlayer(Player player) {
		return roles.get(player.getUniqueId().toString()).getId();
	}
	/**
	 * Gets the ServerRole of the Player
	 * @param player the player to return it's role
	 * @return the Server Role of the player
	 */
	public static ServerRole getServerRole(Player player) {
		return roles.get(player.getUniqueId().toString());
	}
	
	/**
	 * Checks if the player exists in the roles map
	 * @param player The player who should be checked
	 * @return wheter the player exits or not
	 */
	public static boolean playerExist(Player player) {
		return roles.containsKey(player.getUniqueId().toString());
	}
}
