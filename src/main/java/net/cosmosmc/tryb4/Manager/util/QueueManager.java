package net.cosmosmc.tryb4.Manager.util;

import net.cosmosmc.tryb4.Manager.Game;
import net.cosmosmc.tryb4.Manager.GameAPI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TryB4
 * GameAPI
 */
public class QueueManager
{
    private List<Game> queue = new ArrayList<Game>();
    private int current = -1;

    private GameAPI gameAPI;
    public QueueManager(GameAPI m) {
        this.gameAPI = m;
        this.current = 0;
    }
    public GameAPI getGameAPI() {
        return this.gameAPI;
    }
    public void setGameAPI(GameAPI GameAPI) {
        this.gameAPI = GameAPI;
    }
    public void setQueue(List<Game> queue) {
        this.queue = queue;
    }
    public Game nextInQueue() {
        if (current < queue.size()-1) {
            current++;
        }
        else {
            current = 0;
        }
        return queue.get(current);
    }
}
