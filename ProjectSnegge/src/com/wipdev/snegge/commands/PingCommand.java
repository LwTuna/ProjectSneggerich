package com.wipdev.snegge.commands;

import org.bukkit.plugin.java.JavaPlugin;

import com.wipdev.snegge.permissions.ServerRole;

public class PingCommand extends CommandExecutor{

	public PingCommand(JavaPlugin plugin) {
		super(plugin, "for Pong", ServerRole.DEFAULT_USER.getId());
	}

	@Override
	public void onExecute(CommandContainer container) {
		container.executor.sendMessage("Pong!");
		
	}

}
