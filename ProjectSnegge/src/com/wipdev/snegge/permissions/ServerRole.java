package com.wipdev.snegge.permissions;

import org.bukkit.ChatColor;
/**
 * The Information about the diffrent Server roles
 * 
 * @author Jonas
 *
 */
public enum ServerRole {

	DEFAULT_USER(0,ChatColor.GRAY+""),VIP(1,ChatColor.GREEN+"[VIP]"),MODERATOR(2,ChatColor.BLUE+"[MODERATOR]"),
		ADMIN(3,ChatColor.RED+"[ADMIN]"),SUPER_ADMIN(4,ChatColor.DARK_RED+"[SUPER ADMIN]");
	
	
	int id;
	String prefix;
	/**
	 * The constructor of enum Server Role
	 * @param id the id of the role == permissions of the role
	 * @param prefix the chat prefix with color
	 */
	ServerRole(int id,String prefix){
		this.id = id;
		this.prefix = prefix;
		
	}
	/**
	 * Gets the id of the Server Role
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * Gets the prefix of the Server Role
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}
	
	
}
