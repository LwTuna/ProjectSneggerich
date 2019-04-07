package com.wipdev.snegge.commands;

import org.bukkit.entity.Player;

public class CommandContainer {

	public Player executor;
	public String raw;
	public String[] args;
	public String command;
	
	public CommandContainer(String raw,Player player) {
		this.raw = raw;
		this.executor = player;
		
		String[] splited = raw.split("\\s+");
		if(splited[0].length()<=2) {
			player.sendMessage("Use ..[command] like ..help");
			return;
		}
		command = splited[0].substring(2);
		
		if(splited.length >=1) {
			args = new String[splited.length-1];
			for(int i=1;i<splited.length;i++) {
				args[i-1] = splited[i];
			}
		}else {
			args = new String[0];
		}
	}
	
}
