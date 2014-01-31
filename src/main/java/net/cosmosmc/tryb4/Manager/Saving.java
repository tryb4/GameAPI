package net.cosmosmc.tryb4.Manager;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Saving 
{
	private static YamlConfiguration yaml;
	private static File f;
	
	public static void init(Plugin p) 
	{
		f = new File(p.getDataFolder(), "storage.yml");
		
		if (!f.exists()) 
		{
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("=======================");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("=======================");
				System.out.println("File creation of 'storage.yml' failed!");
				System.out.println("=======================");
				
				return;
			}
		}
		yaml = YamlConfiguration.loadConfiguration(f);
	}
	
	public static void save() 
	{
		try {
			yaml.save(f);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("=======================");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("=======================");
			System.out.println("File save of 'storage.yml' failed!");
			System.out.println("=======================");
			
			return;
		}
	}
	public static YamlConfiguration reload() 
	{
		yaml = YamlConfiguration.loadConfiguration(f);
		return yaml;
	}
	
	public static YamlConfiguration get() 
	{
		return yaml;
	}
	public static File getFile() 
	{
		return f;
	}
	
	
	

}
