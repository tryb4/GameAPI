package net.cosmosmc.tryb4.Manager.util;

import org.bukkit.entity.Player;

import com.devro.thecosmoscore.TheCosmosCore;

public class MessageUtils {
	public static void message(Player p, String module, String message)
	{
		p.sendMessage(com.devro.thecosmoscore.utils.ChatUtils.modulate(module, TheCosmosCore.MESSAGE_GENERAL_BODY + message));
	}
}
