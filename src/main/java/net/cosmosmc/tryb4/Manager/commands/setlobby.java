package net.cosmosmc.tryb4.Manager.commands;

import com.devro.thecosmoscore.commands.core.Command;
import com.devro.thecosmoscore.commands.core.CommandArgs;
import com.devro.thecosmoscore.enums.PermissionsRank;
import net.cosmosmc.tryb4.Manager.Game;
import net.cosmosmc.tryb4.Manager.Manager;
import net.cosmosmc.tryb4.Manager.Map;
import net.cosmosmc.tryb4.Manager.util.l;
import org.bukkit.entity.*;

/**
 * Created by TryB4
 * GameAPI
 */
public class setlobby {

    @Command(name = "setlobby, sl", rank = PermissionsRank.ADMIN, usage = "/setlobby")
    public boolean onCommand(CommandArgs a)
    {
        if (!(a.getSender() instanceof Player)) {
            return false;
        }

        Game g = Manager.getCurrentGame();

        if (g.getMap() != null) {
            Map m = g.getMap();
            m.saveLocation("lobby", a.getPlayer().getLocation());
            l._(a.getPlayer(), "Successfully set the lobby location for " + g.getName() + ".", "Map");
        }


        return true;
    }
}
