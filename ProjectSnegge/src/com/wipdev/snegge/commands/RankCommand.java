package com.wipdev.snegge.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.wipdev.snegge.permissions.PermissionSystem;
import com.wipdev.snegge.permissions.ServerRole;

public class RankCommand extends CommandExecutor{

	public RankCommand(JavaPlugin plugin) {
		super(plugin, "..rank [id] [nameofplayer]", ServerRole.ADMIN.getId());
	}

	@Override
	public void onExecute(CommandContainer container) {
		if(container.args.length<=1 || container.args.length>=3) {
			container.executor.sendMessage("Correct Usage : "+HELP);
		}else {
			String[] args = container.args;
			try {
				int id = Integer.parseInt(args[0]);
				if(PermissionSystem.getPermissionsOfPlayer(container.executor) < id) {
					container.executor.sendMessage("You cant assign a role that's higher that yours!");
				}else {
					if(Bukkit.getPlayer(args[1]) == null) {
						container.executor.sendMessage("Player "+args[1]+" not online or existing.");
						return;
					}else {
						PermissionSystem.addPlayer(Bukkit.getPlayer(args[1]), ServerRole.getById(id));
						Bukkit.getPlayer(args[1]).sendMessage("Your rank has changed : "+ServerRole.getById(id).getPrefix());
						container.executor.sendMessage("Succesfull changed rank!");
					}
				}
			}catch(Exception e) {
				container.executor.sendMessage("Error : "+e.getMessage());
			}
			
			
		}
	}

}
