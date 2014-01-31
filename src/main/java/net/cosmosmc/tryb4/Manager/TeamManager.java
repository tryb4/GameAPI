package net.cosmosmc.tryb4.Manager;

import java.util.ArrayList;
import java.util.List;

import net.cosmosmc.tryb4.Manager.util.l;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TeamManager 
{
	
	/**
	 * All currently registered teams
	 */
	private List<Team> teams = new ArrayList<Team>();
	/**
	 * Unregisters all teams
	 */
	public void clearTeams() 
	{
		for (Team t : teams) {
			t.unregister();
		}
	}

    /**
     * Returns all currently registered teams
     */
    public List<Team> getTeams() {
        return teams;
    }



	/**
	 * Registers team specified to scoreboard specified
	 */
	public Team register(String t, Scoreboard b) 
	{
		if (b.getTeam(t) != null) {
			b.getTeam(t).unregister();
		}
		return b.registerNewTeam(t);
	}
	
	
	/**
	 * Shorter method
	 * @param s - name of team to search for
	 * @return team if found
	 */
	public Team _(String s) 
	{
		return getTeam(s);
	}
	/**
	 * @param s - name of team to validate
	 * @return if 's' is a valid team
	 */
	public boolean isTeam(String s) 
	{
		for (Team t : teams) {
			if (t.getName().equalsIgnoreCase(s)) {
				return true;
			}
		}
		
		return false;
	}
	/**
	 * @param s - name of team to search for
	 * @return team if found
	 */
	public Team getTeam(String s) {
		if (isTeam(s)) {
			for (Team t : teams) {
				if (t.getName().equalsIgnoreCase(s)) {
					return t;
				}
			}
		}
		return null;
	}
	
	/**
	 * Adds player to team if found
	 */
	
	public void addToTeam(Player p, String t) 
	{
		if (isTeam(t)) 
		{
			if (!getTeam(t).hasPlayer(p)) {
			l._(p, "Joined " + t + " Team", "Team");
			getTeam(t).addPlayer(p);
			}
		}
		else {
			l._(p, "Unknown team: '"+t+"'", "Error");
		}
	}
	
	
	/**
	 * Removes player from team if found
	 */
	public void removeFromTeam(Player p, String t) 
	{
		if (isTeam(t)) 
		{
			if (getTeam(t).hasPlayer(p)) {
				l._(p, "Left " + t + " Team", "Team");
				getTeam(t).removePlayer(p);
				}
		}
		else {
			l._(p, "Unknown team: '"+t+"'", "Error");
		}
	}
	
	
	
	
	
}
