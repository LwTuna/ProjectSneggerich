package com.wipdev.snegge.playerStart;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.wipdev.snegge.permissions.PermissionSystem;
import com.wipdev.snegge.permissions.ServerRole;
/**
 * A event Handler to ahdnle Player Join Events
 * @author Jonas
 *
 */
public class JoinEventHandler implements Listener{

	private static List<String> sneggenFacts = new ArrayList<String>();
	public JoinEventHandler() {
		sneggenFacts.add("Die Größte Snegge der Welt ist 200m groß");
	}
	
	/**
	 * Handles the PlayerJoinEvent
	 * prints custom join messege wheter the player is new to the server ot not
	 * if not the join message cotains the role prefix
	 * @param e the data about the join event
	 */
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if(PermissionSystem.playerExist(e.getPlayer())) {
			e.setJoinMessage(PermissionSystem.getServerRole(e.getPlayer()).getPrefix()+" "+e.getPlayer().getDisplayName()+" joined the Server.");
		}else {
			PermissionSystem.addPlayer(e.getPlayer(), ServerRole.DEFAULT_USER);
			e.setJoinMessage(ChatColor.LIGHT_PURPLE+e.getPlayer().getName()+" has joined for the first Time on the Server.");
		}
		e.getPlayer().sendMessage("Did you know : "+sneggenFacts.get((int) Math.random()*sneggenFacts.size()));
		
	}
	
	
}
