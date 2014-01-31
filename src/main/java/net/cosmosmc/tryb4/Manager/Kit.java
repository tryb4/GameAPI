package net.cosmosmc.tryb4.Manager;

import com.devro.thecosmoscore.shop.Marketable;
import com.devro.thecosmoscore.shop.core.AccessCriteria;
import com.devro.thecosmoscore.shop.core.FreeAccessCriteria;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;


public class Kit implements Marketable {

	
	private AccessCriteria access = new FreeAccessCriteria();
	private String description;
	private MaterialData displayData;
	private String name;
	private int uuid;
	/**
	 * Kit that is free
	 */
	public Kit(String description, MaterialData displayData, String name, int uuid) 
	{
		this.uuid = uuid;
		this.name = name;
		this.displayData = displayData;
		this.description = description;
	}
	/**
	 * Kit that is NOT free
	 */
	public Kit(String description, MaterialData displayData, String name, int uuid, AccessCriteria access) 
	{
		this.uuid = uuid;
		this.name = name;
		this.displayData = displayData;
		this.description = description;
		setAccessCriteria(access);
	}
	
	
	
	/**
	 * What happens when game starts and player gets kit?
	 * 
	 * @param p - player who recieves the kit
	 */
	public void apply(Player p) {}

	
	
	
	public void setAccessCriteria(AccessCriteria access) 
	{
		this.access = access;
	}
	
	
	
	
	
	@Override
	public AccessCriteria getAccessCriteria() {
		return access;
	}
	@Override
	public String getDescription() {
		return description;
	}
	@Override
	public MaterialData getDisplayData() {
		return displayData;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public int getUUID() {
		return uuid;
	}
	
	
	
	
	
	
//	@Override
//	public AccessCriteria getAccessCriteria() {
//		return FreeAccessCriteria;
//	}
//
//	@Override
//	public String getDescription() {
//		return null;
//	}
//
//	@Override
//	public MaterialData getDisplayData() {
//		return null;
//	}
//
//	@Override
//	public String getName() {
//		return null;
//	}
//
//	@Override
//	public int getUUID() {
//		return 0;
//	}
	

}
