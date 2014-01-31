package net.cosmosmc.tryb4.Manager.util;

import org.bukkit.entity.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TryB4
 * GameAPI
 */
public class Winner
{
    private String name;
    private List<Player> players = new ArrayList<Player>();
    private Player winner = null;
    private boolean isListOfPlayers = false;
    private boolean noWinner = false;
    public Winner(String name)
    {
        this.name = name;
    }


    public void setNoWinner(boolean noWinner) {
        this.noWinner = noWinner;
    }

    public boolean isNoWinner() {
        return noWinner;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setListOfPlayers(boolean isListOfPlayers) {
        this.isListOfPlayers = isListOfPlayers;
    }

    public boolean isListOfPlayers() {
        return isListOfPlayers;
    }



    public List<Player> getPlayers() {
        if (isListOfPlayers) {
            return players;
        }
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
