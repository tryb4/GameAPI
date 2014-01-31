package net.cosmosmc.tryb4.Manager.util.util;

import com.devro.thecosmoscore.TheCosmosCore;
import com.devro.thecosmoscore.Utils.ChatUtils;
import org.bukkit.entity.*;

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
