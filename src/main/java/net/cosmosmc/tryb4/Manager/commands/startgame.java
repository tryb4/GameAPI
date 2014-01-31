package net.cosmosmc.tryb4.Manager.commands;

import com.devro.thecosmoscore.commands.core.Command;
import com.devro.thecosmoscore.commands.core.CommandArgs;
import com.devro.thecosmoscore.enums.PermissionsRank;
import net.cosmosmc.tryb4.Manager.Manager;

/**
 * Created by TryB4
 * GameAPI
 */
public class startgame {

    @Command(name = "startgame,start", rank = PermissionsRank.ADMIN, usage = "/startgame|start")
    public boolean onCommand(CommandArgs a){
        if (Manager.getCurrentGame() != null) {
            if (!Manager.loading) {
                Manager.getCurrentGame().start();
                Manager.loading = true;
            }
            else {
                if (Manager.getCurrentGame().getStarting() > 5) {
                    Manager.getCurrentGame().setStart(5);
                }
            }
        }

        a.getSender().sendMessage("The game " + Manager.getCurrentGame().getName() + " will now start in " + Manager.getCurrentGame().getStarting() + " seconds");


        return true;
    }
}
