package com.wipdev.snegge.chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.wipdev.snegge.permissions.PermissionSystem;
/**
 * A Listener for the PlayerChatEvent 
 * Redoning the whole message system adding the prefixes for roles and classes in chat
 * 
 * TODO add pms
 * @author Jonas
 *
 */
public class ChatEventHandler implements Listener{

	
	/**
	 * Redo the Chat messages(Add prefix chatcolor nickname etc.)
	 * @param ce Information about the chat event
	 */
	@EventHandler
	public void onChatEvent(AsyncPlayerChatEvent ce){
		ce.setCancelled(true);
		Bukkit.broadcastMessage(assembleMessage(ce.getMessage(), ce.getPlayer()));
		
	}
	
	/**
	 * Assembles the message with their role prefix,nickname,their chatcolor and the actual message
	 * 
	 * @param message the message the player has written
	 * @param player The player sending the message
	 * @return The final assembled message to broadcast
	 */
	private String assembleMessage(String message,Player player) {
		return getPrefix(player)+" "+getChatColor(player)+getCustomNickname(player)+" :"+ChatColor.stripColor(message);
	}
	
	/**
	 * Gets the Nickname of the Player
	 * TODO get a saved nickname
	 * @param the player sending the message
	 * @return the nickname of the player
	 * 
	 */
	private String getCustomNickname(Player player) {
		return player.getDisplayName();
	}
	
	/**
	 * 	Gets the Prefix from ServerRole and played Class as Chat prefix
	 * TODO add the Player Class to prefix
	 * @return the player prefix
	 * @param player the player sending the message
	 */
	private String getPrefix(Player player) {
		return PermissionSystem.getServerRole(player).getPrefix();
	}
	
	/**
	 * Gets the ChatColor of the Player
	 * TODO Change Chatcolor depending on rank
	 * 
	 * @return the chat color DEFAULT= Gray of the player
	 * @param player the player sending the message
	 */
	private ChatColor getChatColor(Player player) {
		
		return ChatColor.GRAY;
	}
}
