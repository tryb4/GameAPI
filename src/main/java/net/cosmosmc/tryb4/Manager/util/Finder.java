package net.cosmosmc.tryb4.Manager.util;

import net.cosmosmc.tryb4.Manager.Manager;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * Created by TryB4
 * GameAPI
 */
public class Finder
{


    public static ItemStack getCompass(Player pl) throws Exception{
        Player closest = null;
        int close = Integer.MAX_VALUE / 2;
        for (Player p : Manager.getGame(pl).getPlayers()) {
            if ((int)p.getLocation().distance(pl.getLocation()) < close) {
                close = (int) p.getLocation().distance(pl.getLocation());
            }
        }
        for (Player p : Manager.getGame(pl).getPlayers()) {
            if (p.getLocation().distance(pl.getLocation()) <= close) {
                closest = p;
            }
        }
        DecimalFormat d = new DecimalFormat("#.##");
        ItemStack is = setName(Material.COMPASS, "§lNearest Player: " + (closest != null ? closest.getName() : "None") + "§r     §r§lDistance: " + (closest != null ? d.format(pl.getLocation().distance(closest.getLocation())) : "Relatively Close"), "", "§r§7Shows closest player!");
        return is;
    }

    private static ItemStack setName(Material m, String d, String... lore) {
        ItemStack is = new ItemStack(m);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§r" + d);
        im.setLore(Arrays.asList(lore));
        is.setItemMeta(im);
        return is;
    }

}
