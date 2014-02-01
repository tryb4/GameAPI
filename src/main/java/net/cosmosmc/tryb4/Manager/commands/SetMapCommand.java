package net.cosmosmc.tryb4.Manager.commands;

import com.devro.thecosmoscore.commands.core.Command;
import com.devro.thecosmoscore.commands.core.CommandArgs;
import com.devro.thecosmoscore.enums.PermissionsRank;
import net.cosmosmc.tryb4.Manager.Game;
import net.cosmosmc.tryb4.Manager.GameAPI;
import net.cosmosmc.tryb4.Manager.util.MapUtil;
import org.bukkit.command.*;

/**
 * Created by TryB4
 * GameAPI
 */
public class SetMapCommand {


    @Command(name = "SetMap", rank = PermissionsRank.ADMIN, usage = "/SetMap <number> <name> <author> [game]")
    public boolean onCommand(CommandArgs a){


        CommandSender p = a.getSender();

        if (a.getArgs().length >= 3) {
            return false;
        }


        int id = 0;
        try {
            id = Integer.parseInt(a.getArgs()[0]);
        }catch(NumberFormatException e){
            e.printStackTrace();
            p.sendMessage("Something went wrong! (check console!)");
            return true;
        }


        String name = a.getArgs()[1];
        String author = a.getArgs()[2];

        Game g = GameAPI.getCurrentGame();

        if (a.getArgs().length == 4){
            g = GameAPI.getGame(a.getArgs()[3]);
        }

        if (g == null) {
            g = GameAPI.getCurrentGame();
        }





        MapUtil.createMap(g, MapUtil.getMapCount(g) + 1, name, author);















        return true;
    }

}
