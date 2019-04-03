package com.wipdev.snegge.chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEventHandler implements Listener{

	@EventHandler
	public void onChatEvent(AsyncPlayerChatEvent ce){
		ce.setCancelled(true);
		Bukkit.broadcastMessage(assembleMessage(ce.getMessage(), ce.getPlayer()));
		
	}
	
	
	private String assembleMessage(String message,Player player) {
		return getPrefix(player)+getCustomNickname(player)+" : "+getChatColor(player)+ChatColor.stripColor(message);
	}
	
	
	private String getCustomNickname(Player player) {
		return player.getDisplayName();
	}
	
	
	private String getPrefix(Player player) {
		return "";
	}
	
	private ChatColor getChatColor(Player player) {
		
		return ChatColor.GRAY;
	}
}
