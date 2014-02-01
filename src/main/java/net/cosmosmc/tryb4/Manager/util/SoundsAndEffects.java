package net.cosmosmc.tryb4.Manager.util;

import org.bukkit.*;
import org.bukkit.entity.*;

import java.util.List;

/**
 * Created by TryB4
 * GameAPI
 */
public class SoundsAndEffects {


    public static void playSound(List<Player> players, Sound s, float volume, float pitch)
    {
        for (Player p : players) {
            p.playSound(p.getLocation(), s, volume, pitch);
        }
    }
    public static void playEffect(List<Player> players, Effect e, int data)
    {
        for (Player p : players) {
            p.playEffect(p.getLocation(), e, data);
        }
    }

}
