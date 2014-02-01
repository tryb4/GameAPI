package net.cosmosmc.tryb4.Manager.teams.core;

import org.bukkit.entity.Player;

import java.util.*;

public class TeamManager 
{

    private static TeamManager instance;

    private static Set<Team> teams = new HashSet<Team>();
    private static Team defaultTeam;
    private static HashMap<String, Team> playerTeams = new HashMap<String, Team>();

    public static TeamManager getInstance() {
        if (instance == null) {
            instance = new TeamManager();
        }

        return (instance);
    }

    public static void reset() {
        instance = null;
        teams.clear();
        playerTeams.clear();
    }

    public static Team registerTeam(Team team) {
        teams.add(team);

        return (team);
    }

    public static Team getTeamFromTeamName(String name) {
        for (Team t : getTeams()) {
            if (t.getName().equals(name)) {
                return (t);
            }
        }

        return (null);
    }

    public static Team getTeamFromPlayer(Player player) {
        return (playerTeams.get(player.getName()));
    }

    public static void setTeam(Player player, Team t) {
        Team oldTeam = getTeamFromPlayer(player);

        if (oldTeam != null) {
            oldTeam.removePlayerFromTeam(player, false);
        }

        playerTeams.put(player.getName(), t);
        t.addPlayerToTeam(player, true);
    }

    private static Team findSmallestTeam() {
        Team smallest = null;
        int smallestTeamSize = 99999;

        for (Team t : getTeams()) {
            int players = t.getPlayerCount();
            if (players < smallestTeamSize) {
                smallestTeamSize = players;
                smallest = t;
            }
        }

        return (smallest);
    }

    public static Set<Team> getTeams() {
        return (teams);
    }

    public static Team getDefaultTeam() {
        return (defaultTeam == null ? findSmallestTeam() : defaultTeam);
    }
	
	
	
	
	
}
