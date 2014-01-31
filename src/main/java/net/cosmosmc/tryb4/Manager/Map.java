package net.cosmosmc.tryb4.Manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class Map 
{
	
	private String name;
	private String creator;
	private Game g;
    private int id;
	
	/**
	 * Creates a map for a game 'g'.
	 */
	public Map(String name, String creator, Game g) 
	{
		this.name = name;
		this.creator = creator;
		this.g = g;
	}


    public int getMapCount()
    {
        if (Saving.get().get(g.getName() + ".map.numberof") == null) {
            Saving.get().set(g.getName() + ".map.numberof", 0);
        Saving.save();
        }
        return Saving.get().getInt(g.getName() + ".map.numberof");
    }


	public String getName() {
		return this.name;
	}
	public String getAuthor() {
		return this.creator;
	}
	public void setAuthor(String c) {
		this.creator = c;
	}
	public void setName(String c) {
		this.name = c;
	}
	
	public int getSpawnCount() 
	{
		if (Saving.get().get(g.getName() + "." + getName() + ".numberof") == null) 
		{
			Saving.get().set(g.getName() + "." + getName() + ".numberof", 0);
			Saving.save();
		}
		return Saving.get().getInt(g.getName() + "." + getName() + ".numberof");
	}
	
	public void addSpawnPoint(Location l) 
	{
		Saving.get().set(g.getName() + "." + getName() + ".numberof", getSpawnCount() + 1);
		saveLocation("spawn." + getSpawnCount(), l);
	}
	
	
	public void unloadWorld() 
	{
		World w = loadLocation("spawn.1").getWorld();
		Bukkit.unloadWorld(w, false);
	}
	public void loadWorld() 
	{
		World w = loadLocation("spawn.1").getWorld();
		Bukkit.createWorld(new WorldCreator(w.getName()));
	}
	
	public void saveLocation(String s, Location l) 
	{
		Saving.get().set(g.getName() + "." + s + ".x", l.getX());
		Saving.get().set(g.getName() + "." + s + ".y", l.getY());
		Saving.get().set(g.getName() + "." + s + ".z", l.getZ());
		Saving.get().set(g.getName() + "." + s + ".w", l.getWorld().getName());
		Saving.save();
	}
	public Location loadLocation(String s) 
	{
		if (Saving.get().get(s + ".x") != null) 
		{
			return new Location(
					Bukkit.getWorld(Saving.get().getString(g.getName() + "." + s + ".w")),
					Saving.get().getDouble(g.getName() + "." + s + ".x"),
					Saving.get().getDouble(g.getName() + "." + s + ".y"),
					Saving.get().getDouble(g.getName() + "." + s + ".z")
					);
		}
		else {
			return null;
		}
	}
	

}
