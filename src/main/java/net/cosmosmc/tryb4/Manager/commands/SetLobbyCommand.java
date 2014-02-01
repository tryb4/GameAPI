package net.cosmosmc.tryb4.Manager.commands;

import com.devro.thecosmoscore.commands.core.Command;
import com.devro.thecosmoscore.commands.core.CommandArgs;
import com.devro.thecosmoscore.enums.PermissionsRank;
import net.cosmosmc.tryb4.Manager.Game;
import net.cosmosmc.tryb4.Manager.GameAPI;
import net.cosmosmc.tryb4.Manager.Map;
import net.cosmosmc.tryb4.Manager.util.MessageUtils;
import org.bukkit.entity.*;

/**
 * Created by TryB4
 * GameAPI
 */
public class SetLobbyCommand {

    @Command(name = "SetLobby, sl", rank = PermissionsRank.ADMIN, usage = "/SetLobby")
    public boolean onCommand(CommandArgs a)
    {
        if (!(a.getSender() instanceof Player)) {
            return false;
        }

        Game g = GameAPI.getCurrentGame();

        if (g.getMap() != null) {
            Map m = g.getMap();
            m.saveLocation("lobby", a.getPlayer().getLocation());
            MessageUtils.message(a.getPlayer(), "Map","Successfully set the lobby location for " + g.getName() + ".");
        }


        return true;
    }
}
