package net.cosmosmc.tryb4.Manager;

import java.util.ArrayList;
import java.util.List;

import com.devro.thecosmoscore.enums.PermissionsRank;
import com.devro.thecosmoscore.managers.UserManager;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.*;

public class Manager extends JavaPlugin implements Listener {

	
	private static List<Game> queue = new ArrayList<Game>();

    private static Game currentGame;

    public static boolean loading = false;


	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		queue.clear();
		Saving.init(this);





        new BukkitRunnable(){
            public void run() {
                if (getCurrentGame().getPlayers().size() >= getCurrentGame().getMin()) {
                    if (!getCurrentGame().isStarted()) {
                        if (getCurrentGame().canStart()) {
                            loading = true;
                            getCurrentGame().start();
                        }
                    }
                }else{
                    if (loading || getCurrentGame().canStart()) {
                        loading = false;
                        getCurrentGame().setCanStart(false);
                    }
                }
            }
        }.runTaskTimer(this, 20 * 3, 20 * 3);



        new BukkitRunnable(){
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    getCurrentGame().updateBoard(p);
                }
            }
        }.runTaskTimer(this, 20 * 3, 20 * 3);
	}



    public static void setCurrentGame(Game g) {
       currentGame = g;
    }
    public static Game getCurrentGame() {
        return currentGame;
    }
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void addToQueue(Game g) 
	{
		if (!queue.contains(g)) {
			queue.add(g);
		}
	}
	public static void removeFromQueue(Game g) 
	{
		if (queue.contains(g)) {
			queue.remove(g);
		}
	}


    /**
     * Events
     */



    @EventHandler
    public void login(PlayerLoginEvent e){
        Player p = e.getPlayer();
        if (getCurrentGame().getPlayers().size() >= getCurrentGame().getMax()) {
            if (UserManager.getUser(p).getRank() == PermissionsRank.NORMAL) {
                e.setResult(PlayerLoginEvent.Result.KICK_OTHER);
                e.setKickMessage("§cJoining denied > Server is full!\nPurchase a rank to be able to join full games!");
            }
        }
    }

    @EventHandler
    public void join(PlayerJoinEvent e)
    {
        e.setJoinMessage(ChatColor.DARK_GRAY + "+ " + ChatColor.GRAY + e.getPlayer().getDisplayName());
        getCurrentGame().playerJoins(e);
    }
    @EventHandler
    public void quit(PlayerQuitEvent e)
    {
        e.setQuitMessage(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + e.getPlayer().getDisplayName());
        getCurrentGame().playerLeaves(e);
    }
    @EventHandler
    public void kicked(PlayerKickEvent e){
        e.setLeaveMessage(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + e.getPlayer().getDisplayName());
        getCurrentGame().playerKicked(e);
    }
    @EventHandler
    public void blockBreak(BlockBreakEvent e){
        getCurrentGame().playerBreaksBlock(e);
    }
    @EventHandler
    public void blockPlace(BlockPlaceEvent e){
        getCurrentGame().playerPlacesBlock(e);
    }
    @EventHandler
    public void blockPhysics(BlockPhysicsEvent e){
        getCurrentGame().blockPhysics(e);
    }
    @EventHandler
    public void blockFromTo(BlockFromToEvent e){
        getCurrentGame().blockFromTo(e);
    }
    @EventHandler
    public void playerDies(PlayerDeathEvent e){
        getCurrentGame().playerDies(e);
    }
    @EventHandler
    public void playerMoves(PlayerMoveEvent e){
        getCurrentGame().playerMoves(e);
    }
    @EventHandler
    public void playerTeleports(PlayerTeleportEvent e){
        getCurrentGame().playerTeleports(e);
    }
    @EventHandler
    public void entityDies(EntityDeathEvent e){
        getCurrentGame().entityDies(e);
    }
    @EventHandler
    public void entityTeleport(EntityTeleportEvent e){
        getCurrentGame().entityTeleport(e);
    }
    @EventHandler
    public void entityDamaged(EntityDamageEvent e){
        getCurrentGame().entityDamaged(e);
    }
    @EventHandler
    public void entityDamageEntity(EntityDamageByEntityEvent e){
        getCurrentGame().entityDamageEntity(e);
    }
    @EventHandler
    public void entityChangesBlock(EntityChangeBlockEvent e){
        getCurrentGame().entityChangesBlock(e);
    }
    @EventHandler
    public void entityExplodes(EntityExplodeEvent e){
        getCurrentGame().entityExplodes(e);
    }











	
	
	
	
	
	
	
	
	
}
