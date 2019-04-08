package com.wipdev.snegge.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.wipdev.snegge.permissions.ServerRole;

public class CommandSystem {

	private Map<String,CommandExecutor> commands = new HashMap<String, CommandExecutor>();
	private JavaPlugin plugin;
	

	
	public CommandSystem(JavaPlugin plugin) {
		this.plugin = plugin;
		commands.put("rank",new RankCommand(plugin));
		commands.put("ping", new PingCommand(plugin));
		commands.put("tp", new TeleportCommand(plugin));
		commands.put("teleport", new TeleportCommand(plugin));
		commands.put("help", new CommandExecutor(plugin," a list of all commands",ServerRole.DEFAULT_USER.getId()) {
		
			@Override
			public void onExecute(CommandContainer container) {
				container.executor.sendMessage(assempleHelpCommand());
				
			}
		});
		
		
	}

	public void handleCommand(String message,Player player) {
		CommandContainer cont = new CommandContainer(message, player);
		if(commands.containsKey(cont.command)) {
			if(commands.get(cont.command).hasPermissions(player)){
				commands.get(cont.command).onExecute(cont);
			}else {
				player.sendMessage(ChatColor.YELLOW+"You do have enough Permissions to NOT do that.");
			}
			
		}else {
			player.sendMessage("Try ..help for a List of Commands");
		}
	}
	
	private String assempleHelpCommand() {
		StringBuilder builder = new StringBuilder();
		builder.append(ChatColor.BLUE+"*******HELP*******\n");
		for(Entry<String,CommandExecutor> command : commands.entrySet()) {
			String role = ServerRole.getById(command.getValue().permissionsNeeded).getPrefix();
			builder.append(ChatColor.WHITE+command.getKey()+": "+command.getValue().getHELP()+" "+role+"\n");
			
		}
		return builder.toString();
	}
}
