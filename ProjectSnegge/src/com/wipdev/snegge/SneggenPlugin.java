package com.wipdev.snegge;

import org.bukkit.plugin.java.JavaPlugin;

import com.wipdev.snegge.chat.ChatEventHandler;
import com.wipdev.snegge.permissions.PermissionSystem;
import com.wipdev.snegge.playerStart.JoinEventHandler;
/**
 * Entry point for the Plugin
 * Initalizing and Saving everything here
 * btw kms for all comments
 * @author Jonas
 * @version 0.0.1
 */
public class SneggenPlugin extends JavaPlugin{
	/**
	 * initalize the permission system
	 * add EventHandlers to the plugin
	 * 
	 * (non-Javadoc)
	 * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
	 */
	@Override
	public void onEnable() {
		getLogger().info("Snegge am Starten...");
		PermissionSystem.init();
		 
		
		
		
		getServer().getPluginManager().registerEvents(new ChatEventHandler(), this);
		getServer().getPluginManager().registerEvents(new JoinEventHandler(), this);
	}
	/**
	 * Called when exiting the Plugin/Server
	 * saves the Permission from runtime
	 * 
	 * (non-Javadoc)
	 * @see org.bukkit.plugin.java.JavaPlugin#onDisable()
	 */ 
	@Override
	public void onDisable() {
		PermissionSystem.save();
	}
	
}
