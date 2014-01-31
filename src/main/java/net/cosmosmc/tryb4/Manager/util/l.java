package net.cosmosmc.tryb4.Manager.util;

import com.devro.thecosmoscore.utils.ChatUtils;
import org.bukkit.entity.Player;

import com.devro.thecosmoscore.TheCosmosCore;

public class l {
	public static void _(Player p, String s) 
	{
		p.sendMessage(ChatUtils.modulate("Game", TheCosmosCore.MESSAGE_GENERAL_BODY) + s);
	}
	public static void _(Player p, String s, String f) 
	{
		p.sendMessage(ChatUtils.modulate(f, TheCosmosCore.MESSAGE_GENERAL_BODY) + s);
	}
}
