package com.wipdev.snegge.commands;

import org.bukkit.plugin.java.JavaPlugin;

import com.wipdev.snegge.permissions.ServerRole;
import com.wipdev.snegge.teleport.WorldTeleportUIManager;

public class TeleportCommand extends CommandExecutor{

	public TeleportCommand(JavaPlugin plugin) {
		super(plugin, "opens the teleport ui", ServerRole.DEFAULT_USER.getId());
	}

	@Override
	public void onExecute(final CommandContainer container) {
		super.runSyncTask(new Runnable() {
			
			public void run() {
				WorldTeleportUIManager.createTeleportUI(container.executor);
			}
		});
	}
	
	

}
