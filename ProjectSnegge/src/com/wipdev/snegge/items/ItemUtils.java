package com.wipdev.snegge.items;

import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {

	public static ItemStack createItem(Material m) {
		return new ItemStack(m, 1);
	}
	
	public static ItemStack createItem(Material m,int amt) {
		return new ItemStack(m, amt);
	}
	
	public static ItemStack createItem(Material m,int amt,Map<Enchantment,Integer> enchants) {
		ItemStack is = createItem(m, amt);
		is.addEnchantments(enchants);
		return is;
	}
	
	public static ItemStack createItem(Material m,int amt,String itemname) {
		ItemStack is = createItem(m, amt);
		
		return setName(is, itemname);
	}
	
	public static ItemStack createItem(Material m,int amt,String itemname,List<String> lore) {
		ItemStack is = createItem(m, amt);
		
		return setName(setLore(is, lore), itemname);
	}
	
	public static ItemStack createItem(Material m,int amt,String itemname,List<String> lore,Map<Enchantment,Integer> enchants) {
		ItemStack is = setName(setLore(createItem(m, amt), lore), itemname);
		is.addEnchantments(enchants);
		return is;
	}
	public static ItemStack createItem(Material m,int amt,String itemname,List<String> lore,Map<Enchantment,Integer> enchants,ItemFlag... flags) {
		ItemStack is = addFlags(setName(setLore(createItem(m, amt), lore), itemname),flags);
		is.addEnchantments(enchants);
		return is;
	}
	
	
	public static ItemStack addFlags(ItemStack is,ItemFlag... flags) {
		ItemMeta im =is.getItemMeta();
		im.addItemFlags(flags);
		
		is.setItemMeta(im);
		return is;
	}
	public static ItemStack setLore(ItemStack is,List<String> lore) {
		ItemMeta im =is.getItemMeta();
		im.setLore(lore);
		
		is.setItemMeta(im);
		return is;
	}
	
	public static ItemStack setName(ItemStack is,String name) {
		ItemMeta im =is.getItemMeta();
		im.setDisplayName(name);
	
		is.setItemMeta(im);
		return is;
	}
	
	public static ItemStack copy(ItemStack item) {
		return new ItemStack(item);
	}
}
