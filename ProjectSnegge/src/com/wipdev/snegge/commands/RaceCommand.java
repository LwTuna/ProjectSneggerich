package com.wipdev.snegge.commands;

import org.bukkit.plugin.java.JavaPlugin;

import com.wipdev.snegge.permissions.ServerRole;
import com.wipdev.snegge.races.RaceChooseMenuHandler;

public class RaceCommand extends CommandExecutor{

	public RaceCommand(JavaPlugin plugin) {
		super(plugin, "opens a race selector menu",ServerRole.DEFAULT_USER.getId());
	}

	@Override
	public void onExecute(final CommandContainer container) {
		runSyncTask(new Runnable() {
			
			public void run() {
				RaceChooseMenuHandler.openInventory(container.executor);
			}
		});
		
	}

}
