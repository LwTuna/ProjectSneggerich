package com.wipdev.snegge.playerStart;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.wipdev.snegge.permissions.PermissionSystem;
import com.wipdev.snegge.permissions.ServerRole;

public class JoinEventHandler implements Listener{

	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if(PermissionSystem.playerExist(e.getPlayer())) {
			e.setJoinMessage(PermissionSystem.getServerRole(e.getPlayer()).getPrefix()+" "+e.getPlayer().getDisplayName()+" joined the Server.");
		}else {
			PermissionSystem.addPlayer(e.getPlayer(), ServerRole.DEFAULT_USER);
			e.setJoinMessage(ChatColor.LIGHT_PURPLE+e.getPlayer().getName()+" has joined for the first Time on the Server.");
		}
	}
	
	
}
