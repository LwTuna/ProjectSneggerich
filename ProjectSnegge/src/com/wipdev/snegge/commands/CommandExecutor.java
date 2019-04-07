package com.wipdev.snegge.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.wipdev.snegge.permissions.PermissionSystem;

public abstract class CommandExecutor {

	protected JavaPlugin plugin;
	
	protected final String HELP;
	
	protected final int permissionsNeeded;
	
	public CommandExecutor(JavaPlugin plugin,String help,int permissionsNeeded) {
		this.plugin = plugin;
		this.HELP = help;
		this.permissionsNeeded = permissionsNeeded;
	}
	
	public abstract void onExecute(CommandContainer container);
	
	
	public void runSyncTask(Runnable runnable) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, runnable);
	}

	public boolean hasPermissions(Player player) {
		return PermissionSystem.getPermissionsOfPlayer(player)>=permissionsNeeded;
	}
	
	public JavaPlugin getPlugin() {
		return plugin;
	}

	public String getHELP() {
		return HELP;
	}

	public int getPermissionsNeeded() {
		return permissionsNeeded;
	}
	
	
}
