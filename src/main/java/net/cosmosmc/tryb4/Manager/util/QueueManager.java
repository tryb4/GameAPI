package net.cosmosmc.tryb4.Manager.util;

import net.cosmosmc.tryb4.Manager.Game;
import net.cosmosmc.tryb4.Manager.Manager;

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

    private Manager manager;
    public QueueManager(Manager m) {
        this.manager = m;
        this.current = 0;
    }
    public Manager getManager() {
        return this.manager;
    }
    public void setManager(Manager manager) {
        this.manager = manager;
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
