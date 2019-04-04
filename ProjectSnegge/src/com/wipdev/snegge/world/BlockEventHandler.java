package com.wipdev.snegge.world;

import java.util.Collection;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import com.wipdev.snegge.permissions.PermissionSystem;
import com.wipdev.snegge.permissions.ServerRole;

public class BlockEventHandler implements Listener{

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		if(PermissionSystem.getPermissionsOfPlayer(e.getPlayer()) >= ServerRole.ADMIN.getId() && e.getPlayer().getGameMode() == GameMode.CREATIVE) {
			return;
		}
		Block block = e.getBlock();
		Material mat = block.getType();
		if(mat.equals(Material.COAL_ORE) ||mat.equals(Material.IRON_ORE) || mat.equals(Material.GOLD_ORE) ||mat.equals(Material.REDSTONE_ORE) ||
				mat.equals(Material.LAPIS_ORE) ||mat.equals(Material.EMERALD_ORE) || mat.equals(Material.DIAMOND_ORE)) {
			Collection<ItemStack> drops = e.getBlock().getDrops(e.getPlayer().getItemInHand());
		     
	        for (ItemStack drop : drops) {
	            e.getPlayer().getInventory().addItem(drop);
	        }
	        e.getBlock().setType(Material.STONE);
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
