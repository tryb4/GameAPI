package net.cosmosmc.tryb4.Manager.test;

import net.cosmosmc.tryb4.Manager.util.WinBase;
import org.bukkit.entity.*;

/**
 * Created by TryB4
 * GameAPI
 */
public class Tracker extends WinBase implements WinCondition {

    /**
     * Get 20 kills without dieing to win -
     */

    private int kills = 0;
    private boolean died = false;

    private Player p;
    public Tracker(Player p) {this.p = p;}


    public void callDied() {
        if (!died) {
            died = true;
            kills = 0;
        }
    }
    public void callKill() {
        if (!died) {
        if (kills < 20) {
            kills++;
        }
        else {
            callWin();
        }
        }
        else {
            callLost();
        }
    }


}
