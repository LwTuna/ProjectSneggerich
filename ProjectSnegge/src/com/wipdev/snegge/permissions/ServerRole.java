package com.wipdev.snegge.permissions;

import org.bukkit.ChatColor;

public enum ServerRole {

	DEFAULT_USER(0,ChatColor.GRAY+""),VIP(1,ChatColor.GREEN+"[VIP]"),MODERATOR(2,ChatColor.BLUE+"[MODERATOR]"),
		ADMIN(3,ChatColor.RED+"[ADMIN]"),SUPER_ADMIN(4,ChatColor.DARK_RED+"[SUPER ADMIN]");
	
	
	int id;
	String prefix;
	ServerRole(int id,String prefix){
		this.id = id;
		this.prefix = prefix;
		
	}
	public int getId() {
		return id;
	}
	public String getPrefix() {
		return prefix;
	}
	
	
}
