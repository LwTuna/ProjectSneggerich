package com.wipdev.snegge.races;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.entity.Player;

import com.wipdev.snegge.SneggenPlugin;
import com.wipdev.snegge.permissions.ServerRole;
import com.wipdev.snegge.utils.FileUtils;

public class RaceManager {
	
	private static Map<String,Race> loaded = new HashMap<String,Race>();
	
	private static final String filePath = SneggenPlugin.folerPath+"/races/save.txt";
	
	
	public static final List<Race> races = Arrays.asList(new Race[] {new TestRace()});
	
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
		if(!hasRole(player)) {
			loaded.put(player.getUniqueId().toString(), race);
		}
	}
	public static boolean hasRole(Player player) {
		return loaded.containsKey(player.getUniqueId().toString());
	}
}
