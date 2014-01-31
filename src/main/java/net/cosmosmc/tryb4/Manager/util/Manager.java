package net.cosmosmc.tryb4.Manager.util;

import net.cosmosmc.tryb4.Manager.util.util.Game;
import org.bukkit.event.*;
import org.bukkit.plugin.java.*;

import java.util.ArrayList;
import java.util.List;

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
	public static void removeFromQueue(net.cosmosmc.tryb4.Manager.util.util.Game g)
	{
		if (queue.contains(g)) {
			queue.remove(g);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
