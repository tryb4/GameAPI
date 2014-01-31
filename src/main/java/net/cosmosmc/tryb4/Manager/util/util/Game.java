package net.cosmosmc.tryb4.Manager.util.util;

import org.bukkit.entity.*;
import org.bukkit.scheduler.*;

import java.util.ArrayList;
import java.util.List;

public class Game 
{
	private String name;
	private boolean inUse = false;
	private List<Player> playing;
	private int minPlayers;
	private int maxPlayers;
	private int starting = 20;
	private int ending = -1;
	
	private List<Integer> task = new ArrayList<Integer>();
	
	private boolean useEndTimer = false;
	
	/**
	 * Main purpose of this project.
	 */
	public Game(String name) 
	{
		this.name = name;
		minPlayers = 4;
		maxPlayers = 6;
	}
	
	/**
	 * Sets minimum amount of players required to play
	 */
	public void setMin(int min) {this.minPlayers = min;}
	/**
	 * Sets maximum amount of allowed players to be on the game-server - players with "join.full" permission can join until there is (max * 2) players online.
	 */
	public void setMax(int max) {this.maxPlayers = max;}
	/**
	 * Set time until starting
	 */
	public void setStart(int starting) {this.starting = starting;}
	
	/**
	 * Set time until forced game ending - optional
	 */
	public void setEnding(int ending) {this.ending = ending; if (!useEndTimer)useEndTimer=true;}
	
	public int getStarting() {
		return starting;
	}
	public int getEnding() {
		return ending;
	}
	public int getMax() {
		return maxPlayers;
	}
	public int getMin() {
		return minPlayers;
	}
	
	
	
	
	
	
	public void start() 
	{
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void addTask(BukkitRunnable task) 
	{
		this.task.add(task.getTaskId());
	}
	public List<Integer> getTasks()
	{
		return this.task;
	}
	/**
	 * Currently no way of getting this
	 * 
	 * WARNING: DOES NOT WORK!
	 */
	public BukkitRunnable getTask(int task) 
	{return null;}
	
	
	
	
	
	
	public List<Player> getPlayers() {
		return this.playing;
	}
	public boolean isInUse() {
		return this.inUse;
	}
	public String getName() {
		return this.name;
	}
	
	

}
