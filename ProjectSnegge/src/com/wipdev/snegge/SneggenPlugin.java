package com.wipdev.snegge;

import org.bukkit.plugin.java.JavaPlugin;

import com.wipdev.snegge.chat.ChatEventHandler;
import com.wipdev.snegge.permissions.PermissionSystem;
import com.wipdev.snegge.playerStart.JoinEventHandler;

public class SneggenPlugin extends JavaPlugin{
 
	public void onEnable() {
		getLogger().info("Snegge am Starten...");
		PermissionSystem.init();
		
		
		
		
		getServer().getPluginManager().registerEvents(new ChatEventHandler(), this);
		getServer().getPluginManager().registerEvents(new JoinEventHandler(), this);
	}
	
	public void onDisable() {
		PermissionSystem.save();
	}
	
}
