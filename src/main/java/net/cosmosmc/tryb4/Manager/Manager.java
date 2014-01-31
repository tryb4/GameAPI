package net.cosmosmc.tryb4.Manager;

import java.util.ArrayList;
import java.util.List;

import net.cosmosmc.tryb4.Manager.util.Game;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Manager extends JavaPlugin implements Listener {

	
	private static List<Game> queue = new ArrayList<Game>();
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		queue.clear();
		Saving.init(this);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void addToQueue(Game g) 
	{
		if (!queue.contains(g)) {
			queue.add(g);
		}
	}
	public static void removeFromQueue(Game g) 
	{
		if (queue.contains(g)) {
			queue.remove(g);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
