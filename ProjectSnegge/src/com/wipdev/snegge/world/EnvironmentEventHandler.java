package com.wipdev.snegge.world;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class EnvironmentEventHandler implements Listener{

	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event) {
		boolean rain = event.toWeatherState();
        if(rain)
            event.setCancelled(true);
	}
		
}
