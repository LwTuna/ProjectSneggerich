package com.wipdev.snegge.world;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.wipdev.snegge.permissions.PermissionSystem;
import com.wipdev.snegge.permissions.ServerRole;
/**
 * 
 * @author Jonas
 *
 */
public class BlockEventHandler implements Listener{

	private JavaPlugin javaPlugin;
	
	private static final Map<Material,Integer> oreRespawnCooldown = new HashMap<Material,Integer>();
	
	public BlockEventHandler(JavaPlugin javaPlugin) {
		this.javaPlugin = javaPlugin;
		oreRespawnCooldown.put(Material.COAL_ORE, 1200);
		oreRespawnCooldown.put(Material.IRON_ORE, 1800);
		oreRespawnCooldown.put(Material.GOLD_ORE, 6000);
		oreRespawnCooldown.put(Material.REDSTONE_ORE, 2400);
		oreRespawnCooldown.put(Material.LAPIS_ORE, 3000);
		oreRespawnCooldown.put(Material.EMERALD_ORE, 18000);
		oreRespawnCooldown.put(Material.DIAMOND_ORE, 18000);
	}

	@EventHandler
	public void onBlockBreak(final BlockBreakEvent e){
		if(PermissionSystem.getPermissionsOfPlayer(e.getPlayer()) >= ServerRole.ADMIN.getId() && e.getPlayer().getGameMode() == GameMode.CREATIVE) {
			return;
		}
		Block block = e.getBlock();
		final Material mat = block.getType();
		if(mat.equals(Material.COAL_ORE) ||mat.equals(Material.IRON_ORE) || mat.equals(Material.GOLD_ORE) ||mat.equals(Material.REDSTONE_ORE) ||
				mat.equals(Material.LAPIS_ORE) ||mat.equals(Material.EMERALD_ORE) || mat.equals(Material.DIAMOND_ORE)) {
			Collection<ItemStack> drops = e.getBlock().getDrops(e.getPlayer().getInventory().getItemInMainHand());
		    
	        for (ItemStack drop : drops) {
	            e.getPlayer().getInventory().addItem(drop);
	        }
	        e.getBlock().setType(Material.STONE);
	        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(javaPlugin, new Runnable() {
				Location loc = e.getBlock().getLocation();
				Material m = mat;
				public void run() {
					loc.getBlock().setType(m);
				}
			},oreRespawnCooldown.get(mat)/100);
	        
		}
		e.setCancelled(true);
		
	}
	
	@EventHandler
	public void onBlockBreak(BlockPlaceEvent e){
		if(PermissionSystem.getPermissionsOfPlayer(e.getPlayer()) >= ServerRole.ADMIN.getId() && e.getPlayer().getGameMode() == GameMode.CREATIVE) {
			return;
		}
		e.setCancelled(true);
	}
	 
}
