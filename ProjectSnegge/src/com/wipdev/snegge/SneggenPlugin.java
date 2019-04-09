package com.wipdev.snegge;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import com.wipdev.snegge.chat.ChatEventHandler;
import com.wipdev.snegge.mobs.DisableMobSpawns;
import com.wipdev.snegge.permissions.PermissionSystem;
import com.wipdev.snegge.playerStart.JoinEventHandler;
import com.wipdev.snegge.races.AbilityHandler;
import com.wipdev.snegge.races.RaceChooseMenuHandler;
import com.wipdev.snegge.races.RaceManager;
import com.wipdev.snegge.teleport.WorldTeleportUIManager;
import com.wipdev.snegge.world.BlockEventHandler;
import com.wipdev.snegge.world.EnvironmentEventHandler;
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
	public static int worldamt = 6;
	public static JavaPlugin instance;
	
	/**
	 * initalize the permission system
	 * add EventHandlers to the plugin
	 * 
	 * (non-Javadoc)
	 * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
	 */
	@Override
	public void onEnable() {
		instance = this;
		getLogger().info("Snegge am Starten...");
		PermissionSystem.init();
		RaceManager.init();
		getLogger().info("Finished loading permissions");
		
		getServer().getPluginManager().registerEvents(new WorldTeleportUIManager(this), this);
		getServer().getPluginManager().registerEvents(new BlockEventHandler(this), this);
		getServer().getPluginManager().registerEvents(new ChatEventHandler(this), this);
		getServer().getPluginManager().registerEvents(new JoinEventHandler(this), this);
		getServer().getPluginManager().registerEvents(new DisableMobSpawns(), this);
		getServer().getPluginManager().registerEvents(new EnvironmentEventHandler(), this);
		getServer().getPluginManager().registerEvents(new AbilityHandler(this), this);
		getServer().getPluginManager().registerEvents(new RaceChooseMenuHandler(), this);
		for(int i=1;i<=worldamt;i++) {
			WorldCreator wc = new WorldCreator("floor"+i);
			wc.generator(new SAOChunkGenerator());
			getServer().createWorld(wc);
		}
	 	
//		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
//
//			public void run() {
//				Player player = Bukkit.getPlayer("PoisonBoy99");
//				if(player == null)
//					return;
//				player.getWorld().spawnEntity(player.getLocation(), EntityType.PARROT);
//				
//			}
//			
//		}, 30L, 30L);
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
		RaceManager.save();
	}
}
