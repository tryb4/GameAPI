package net.cosmosmc.tryb4.Manager;

import net.cosmosmc.tryb4.Manager.util.MessageUtils;
import org.bukkit.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by TryB4
 * GameAPI
 */
public class KitManager
{
    private static List<Kit> kits = new ArrayList<Kit>();
    private static HashMap<String, Kit> playerKits = new HashMap<String, Kit>();
    private static Kit defaultKit = null;


    public static void applyKit(Player p, Kit k) {
        if (k.getAccessCriteria().canAccess(p, k)) {
            removeKit(p, k);
            playerKits.put(p.getName(), k);
            MessageUtils.message(p, "Kit", "You equipped the " + k.getName() + " kit");
        }
        else {
            MessageUtils.message(p, "Error",  "You do not have access to this kit.");
        }
    }
    public static Kit getDefaultKit() {
        return defaultKit;
    }

    public static void setDefaultKit(Kit defaultKit) {
        KitManager.defaultKit = defaultKit;
    }

    public static void removeKit(Player p, Kit k) {
        if (playerKits.containsKey(p.getName())) {
            playerKits.remove(p.getName());
            MessageUtils.message(p, "Kit", "You unequipped the " + k.getName() + " kit");
        }
    }


    public static void clearKits() {
        playerKits.clear();
    }

    public static Kit getKit(Player p)
    {
        if (playerKits.containsKey(p.getName())) {
            return playerKits.get(p.getName());
        }
        return null;
    }
    public static HashMap<String, Kit> getPlayerKits() {
        return playerKits;
    }
    public static List<Kit> getKits() {
        return kits;
    }


















}
