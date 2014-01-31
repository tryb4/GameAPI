package net.cosmosmc.tryb4.Manager.commands;

import com.devro.thecosmoscore.commands.core.Command;
import com.devro.thecosmoscore.commands.core.CommandArgs;
import com.devro.thecosmoscore.enums.PermissionsRank;
import net.cosmosmc.tryb4.Manager.GameAPI;

/**
 * Created by TryB4
 * GameAPI
 */
public class startgame {

    @Command(name = "startgame,start", rank = PermissionsRank.ADMIN, usage = "/startgame|start")
    public boolean onCommand(CommandArgs a){
        if (GameAPI.getCurrentGame() != null) {
            if (!GameAPI.loading) {
                GameAPI.getCurrentGame().start();
                GameAPI.loading = true;
            }
            else {
                if (GameAPI.getCurrentGame().getStarting() > 5) {
                    GameAPI.getCurrentGame().setStart(5);
                }
            }
        }

        a.getSender().sendMessage("The game " + GameAPI.getCurrentGame().getName() + " will now start in " + GameAPI.getCurrentGame().getStarting() + " seconds");


        return true;
    }
}
