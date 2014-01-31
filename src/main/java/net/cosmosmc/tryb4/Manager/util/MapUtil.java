package net.cosmosmc.tryb4.Manager.util;

import net.cosmosmc.tryb4.Manager.Game;
import net.cosmosmc.tryb4.Manager.Saving;
import org.bukkit.*;

/**
 * Created by TryB4
 * GameAPI
 */
public class MapUtil
{
    public static int getMapCount(Game g) {
        return g.getMap().getSpawnCount();
    }

    public static void createMap(Game g, int id, String name, String author)
    {
        if (id > getMapCount(g)) {
            Saving.get().set(".map.numberof", getMapCount(g) + 1);
            Saving.save();
        }
        Saving.get().set(".map." + id + ".name", name);
        Saving.get().set(".map." + id + ".author", author);
        Saving.save();
    }
    public static void saveLocation(String s, Location l)
    {
        Saving.get().set("." + s + ".x", l.getX());
        Saving.get().set("." + s + ".y", l.getY());
        Saving.get().set("." + s + ".z", l.getZ());
        Saving.get().set("." + s + ".w", l.getWorld().getName());
        Saving.save();
    }
    public static Location loadLocation(String s)
    {
        if (Saving.get().get(s + ".x") != null)
        {
            return new Location(
                    Bukkit.getWorld(Saving.get().getString("." + s + ".w")),
                    Saving.get().getDouble("." + s + ".x"),
                    Saving.get().getDouble("." + s + ".y"),
                    Saving.get().getDouble("." + s + ".z")
            );
        }
        else {
            return null;
        }
    }

}
