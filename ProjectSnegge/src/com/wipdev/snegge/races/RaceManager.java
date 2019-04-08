package com.wipdev.snegge.races;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.wipdev.snegge.SneggenPlugin;
import com.wipdev.snegge.permissions.PermissionSystem;
import com.wipdev.snegge.permissions.ServerRole;
import com.wipdev.snegge.utils.FileUtils;

public class RaceManager {
	
	private static Map<String,Race> loaded = new HashMap<String,Race>();
	
	private static final String filePath = SneggenPlugin.folerPath+"/races/save.txt";
	
	
	public static final List<Race> races = Arrays.asList(new Race[] {new TestRace(),new DwarfRace()});
	
	public static void init() {
		if(!FileUtils.exists(filePath)) {
			FileUtils.createNewFile(filePath);
			return;
		}
		
		List<String> lines = FileUtils.readLinesFormFile(FileUtils.loadFile(filePath));
		
		for(String line : lines) {
			String[] splited = line.split("\\s+");
			if(splited.length<=1)
				continue;
			loaded.put(splited[0], getById(Integer.parseInt(splited[1])));
		}
	}
	
	public static Race getById(int id) {
		for(Race race:races) {
			if(id == race.getId()) {
				return race;
			}
		}
		return null;
	}
	
	public static void save() {
		StringBuilder builder = new StringBuilder();
		
		for(Entry<String,Race> entry : loaded.entrySet()) {
			builder.append(entry.getKey()+" "+entry.getValue().getId()+"\n");
		}
		
		FileUtils.saveStringToFile(builder.toString(), FileUtils.loadFile(filePath));
	}
	
	
	public static Race getRace(Player player) {
		if(loaded.containsKey(player.getUniqueId().toString())) {
			return loaded.get(player.getUniqueId().toString());
		}else {
			return null;
		}
	}
	
	public static void setRace(Player player,Race race) {
		if(PermissionSystem.getPermissionsOfPlayer(player)>=ServerRole.ADMIN.getId()) {
				loaded.put(player.getUniqueId().toString(), race);
				player.sendMessage("You have choosen the Race : "+race.getChatPrefix());
				for(PotionEffect effect : player.getActivePotionEffects())
				{
				    player.removePotionEffect(effect.getType());
				}
				race.applyPermEffects(player);
		}else {
			if(!hasRace(player)) {
				loaded.put(player.getUniqueId().toString(), race);
				player.sendMessage("You have choosen the Race : "+race.getChatPrefix());
				race.applyPermEffects(player);
			}else {
				player.sendMessage("You have already choosen a class!");
			}
		}
	}
	
	
	
	public static boolean hasRace(Player player) {
		return loaded.containsKey(player.getUniqueId().toString());
	}

	public static void applyEffect(Player player) {
		if(hasRace(player)) {
			getRace(player).applyPermEffects(player);
		}
	}
}
