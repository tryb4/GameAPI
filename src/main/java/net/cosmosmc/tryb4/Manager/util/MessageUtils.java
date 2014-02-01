package net.cosmosmc.tryb4.Manager.util;

import com.devro.thecosmoscore.utils.ChatUtils;
import org.bukkit.entity.Player;

import com.devro.thecosmoscore.TheCosmosCore;

public class MessageUtils {
	public static void mesage(Player p, String module, String message)
	{
		p.sendMessage(com.devro.thecosmoscore.utils.ChatUtils.modulate(module, TheCosmosCore.MESSAGE_GENERAL_BODY + message));
	}
}
