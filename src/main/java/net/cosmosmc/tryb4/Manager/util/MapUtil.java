package net.cosmosmc.tryb4.Manager.util;

import net.cosmosmc.tryb4.Manager.Game;
import net.cosmosmc.tryb4.Manager.Saving;

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
            Saving.get().set(g.getName() + ".map.numberof", getMapCount(g) + 1);
            Saving.save();
        }
        Saving.get().set(g.getName() + ".map." + id + ".name", name);
        Saving.get().set(g.getName() + ".map." + id + ".author", author);
        Saving.save();
    }

}
