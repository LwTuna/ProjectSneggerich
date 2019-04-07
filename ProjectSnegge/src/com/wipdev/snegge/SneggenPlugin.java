package com.wipdev.snegge;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import com.wipdev.snegge.chat.ChatEventHandler;
import com.wipdev.snegge.mobs.DisableMobSpawns;
import com.wipdev.snegge.permissions.PermissionSystem;
import com.wipdev.snegge.playerStart.JoinEventHandler;
import com.wipdev.snegge.teleport.WorldTeleportUIManager;
import com.wipdev.snegge.world.BlockEventHandler;
import com.wipdev.snegge.world.SAOChunkGenerator;
/**
 * Entry point for the Plugin
 * Initalizing and Saving everything here
 * btw kms for all comments
 * @author Jonas
 * @version 0.0.2
 */
public class SneggenPlugin extends JavaPlugin{
	
	public static final String folerPath = "plugins/SneggenPlugin";
	public static int worldamt = 5;
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
		getLogger().info("Finished loading permissions");
		
		getServer().getPluginManager().registerEvents(new WorldTeleportUIManager(this), this);
		getServer().getPluginManager().registerEvents(new BlockEventHandler(this), this);
		getServer().getPluginManager().registerEvents(new ChatEventHandler(this), this);
		getServer().getPluginManager().registerEvents(new JoinEventHandler(), this);
		getServer().getPluginManager().registerEvents(new DisableMobSpawns(), this);
		for(int i=1;i<=worldamt;i++) {
			WorldCreator wc = new WorldCreator("floor"+i);
			wc.generator(new SAOChunkGenerator());
			getServer().createWorld(wc);
		}
	
	}
	
	
	
	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
	    return new SAOChunkGenerator();
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
		save();
	}
	
	private void save() {
		PermissionSystem.save();
	}
}
