package net.cosmosmc.tryb4.Manager.teams.core;

import com.devro.thecosmoscore.TheCosmosCore;
import com.devro.thecosmoscore.managers.UserManager;
import net.cosmosmc.tryb4.Manager.enums.FriendlyFireSetting;
import net.cosmosmc.tryb4.Manager.util.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Programmed by: DevRo_ (Erik Rosemberg)
 * Creation Date: 01, 02, 2014
 * Programmed for the GameAPI project.
 */
public class Team {
    private String name = "Unknown";
    private String description = "Unknown";

    private ChatColor color = ChatColor.GRAY;
    private FriendlyFireSetting ffSetting = FriendlyFireSetting.DISALLOW;

    public Team(String name, String description, ChatColor color, FriendlyFireSetting ffSetting) {
        this.name = name;
        this.description = description;
        this.color = color;
        this.ffSetting = ffSetting;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return (description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return (color.toString() + ChatColor.BOLD + name);
    }

    public ChatColor getColor() {
        return (color);
    }

    public void setColor(ChatColor color) {
        this.color = color;
    }

    public void setFriendlyFireSetting(FriendlyFireSetting setting) {
        this.ffSetting = setting;
    }

    public FriendlyFireSetting getFriendlyFireSetting() {
        return (ffSetting);
    }

    public void addPlayerToTeam(Player player, boolean announce) {
        //Set name prefix color

        if (announce) {
            player.sendMessage("Joined team message");
        }
    }

    public void removePlayerFromTeam(Player player, boolean announce) {
        UserManager.getUser(player).setNamePrefixColor(ChatColor.GRAY);

        if (announce) {
            MessageUtils.message(player, "Team", "Left team " + TheCosmosCore.MESSAGE_GENERAL_DATA + getName() + "!");
        }
    }

    public int getPlayerCount() {
        int playersOnTeam = 0;

        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            if (TeamManager.getTeamFromPlayer(player) == this) {
                playersOnTeam++;
            }
        }

        return (playersOnTeam);
    }
}
