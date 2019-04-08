package com.wipdev.snegge.playerStart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.wipdev.snegge.permissions.PermissionSystem;
import com.wipdev.snegge.permissions.ServerRole;
import com.wipdev.snegge.races.RaceChooseMenuHandler;
import com.wipdev.snegge.races.RaceManager;
/**
 * A event Handler to ahdnle Player Join Events
 * @author Jonas
 *
 */
public class JoinEventHandler implements Listener{

	private static List<String> sneggenFacts = new ArrayList<String>();
	
	private JavaPlugin plugin;
	

	public JoinEventHandler(JavaPlugin plugin) {
		this.plugin = plugin;
		sneggenFacts.add("Die Größte Snegge der Welt ist 200m groß");
		sneggenFacts.add("Nacktsneggen legen wie Fögel Eierund bauen Nester. Nur net so weit oben!");
		sneggenFacts.add("Es gibt viele Arten von Nacktsneggen. Zum Beispiel die Tiegersnegge und halt noch übel viele.");
		sneggenFacts.add("Nacktsneggen bilden kleine Tiergemeinschaften. Der bekannteste ist UniCredirt Banking.");
		sneggenFacts.add("Nacknseggen sind im Durschnitte zwischen 10 und 20 cm groß");
		sneggenFacts.add("Nacktsneggen können nicht lesen. Die scheiß Wixxer");
		sneggenFacts.add("In der westlichen Welt gilt du Snegge als Beleidigung wegen ihrer Langsamigkeit. Aber die schnellste Snegge der Welt, also die ist schon ziemlich krass.");
	}
	
	/**
	 * Handles the PlayerJoinEvent
	 * prints custom join messege wheter the player is new to the server ot not
	 * if not the join message cotains the role prefix
	 * @param e the data about the join event
	 */
	@EventHandler
	public void onJoin(final PlayerJoinEvent e) {
		if(PermissionSystem.playerExist(e.getPlayer())) {
			e.setJoinMessage(PermissionSystem.getServerRole(e.getPlayer()).getPrefix()+" "+e.getPlayer().getDisplayName()+" joined the Server.");
		}else {
			PermissionSystem.addPlayer(e.getPlayer(), ServerRole.DEFAULT_USER);
			
			e.setJoinMessage(ChatColor.LIGHT_PURPLE+e.getPlayer().getName()+" has joined for the first Time on the Server.");
		}
		Random random = new Random();
		int factId = random.nextInt(sneggenFacts.size());
		e.getPlayer().sendMessage(sneggenFacts.get(factId));
		
		if(!RaceManager.hasRace(e.getPlayer())) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				
				public void run() {
					RaceChooseMenuHandler.openInventory(e.getPlayer());
					
				}
			}, 60);
		}
		
		
	}
	
	
}
