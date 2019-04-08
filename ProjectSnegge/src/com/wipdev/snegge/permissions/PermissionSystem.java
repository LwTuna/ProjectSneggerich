package com.wipdev.snegge.permissions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.entity.Player;

import com.wipdev.snegge.SneggenPlugin;
import com.wipdev.snegge.utils.FileUtils;
/**
 * Contains every necessary information about every players permissions/roles on the server
 * @author Jonas
 *
 */
public final class PermissionSystem {

	private static final String filepath=SneggenPlugin.folerPath+"/permissions/save.txt";
	
	//Mapping the UUID of a Player as a String to Role on the Server
	private static Map<String,ServerRole> roles = new HashMap<String,ServerRole>();
	
	/**
	 * initialize the roles map from a file or db
	 * TODO load the file/db to put in values^^
	 */
	public static void init() {
		if(!FileUtils.exists(filepath)) {
			FileUtils.createNewFile(filepath);
			return;
		}
		
		List<String> lines = FileUtils.readLinesFormFile(FileUtils.loadFile(filepath));
		
		for(String line : lines) {
			String[] splited = line.split("\\s+");
			if(splited.length<=1)
				continue;
			roles.put(splited[0], ServerRole.getById(Integer.parseInt(splited[1])));
		}
		
	}
	
	/**
	 * Save the roles map to a file or db
	 *
	 */
	public static void save() {
		StringBuilder builder = new StringBuilder();
		
		for(Entry<String,ServerRole> entry : roles.entrySet()) {
			builder.append(entry.getKey()+" "+entry.getValue().id+"\n");
		}
		
		FileUtils.saveStringToFile(builder.toString(), FileUtils.loadFile(filepath));
	}
	
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
