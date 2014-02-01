package net.cosmosmc.tryb4.Manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.devro.thecosmoscore.managers.UserManager;
import net.cosmosmc.tryb4.Manager.util.Finder;
import net.cosmosmc.tryb4.Manager.util.SoundsAndEffects;
import net.cosmosmc.tryb4.Manager.util.Winner;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

public class Game 
{
	private String name;
	private boolean inUse = false;
	private List<Player> playing = new ArrayList<Player>();
    private List<Player> spectators = new ArrayList<Player>();
	private int minPlayers;
	private int maxPlayers;
	private int starting = 20;
	private int ending = -1;


    /**
     * The map that will be played
     */
    private Map map;


    private HashMap<Player, Scoreboard> boards = new HashMap<Player, Scoreboard>();

	private List<Integer> task = new ArrayList<Integer>();
	
	private boolean useEndTimer = false;

    private GameAPI manager;

    private boolean canStart = false;

    private boolean started = false;

	/**
	 * Main purpose of this project.
	 */
	public Game(String name, GameAPI m)
	{
        this.manager = m;
		this.name = name;
		minPlayers = 4;
		maxPlayers = 6;
	}
	
	/**
	 * Sets minimum amount of players required to play
	 */
	public void setMin(int min) {this.minPlayers = min;}
	/**
	 * Sets maximum amount of allowed players to be on the game-server - players with "join.full" permission can join until there is (max * 2) players online.
	 */
	public void setMax(int max) {this.maxPlayers = max;}
	/**
	 * Set time until starting
	 */
	public void setStart(int starting) {this.starting = starting;}
	
	/**
	 * Set time until forced game ending - optional
	 */
	public void setEnding(int ending) {this.ending = ending; if (!useEndTimer)useEndTimer=true;}
	
	public int getStarting() {
		return starting;
	}
	public int getEnding() {
		return ending;
	}
	public int getMax() {
		return maxPlayers;
	}
	public int getMin() {
		return minPlayers;
	}

    public Map getMap() {
        return map;
    }
    public void setMap(Map m) {
        this.map = m;
    }

    public GameAPI getManager() {
        return manager;
    }

    public void start()
	{
		task.add(
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (canStart){
                                if (starting < 12)
                                {
                                    SoundsAndEffects.playSound(Arrays.asList(Bukkit.getOnlinePlayers()), Sound.NOTE_PLING, starting, 1);
                                }
                        if (starting > 0) {
                            starting--;
                        }
                        else {
                            starting = 20;
                            this.cancel();
                            task.remove(getTaskId());
                        }
                        }else{
                            this.cancel();
                        }
                    }
                }.runTaskTimer(getManager(), 30, 30).getTaskId()
        );
	}

    private String format(int a, boolean c) {
        return (a % 60 + (c ? "s" : " Seconds"));
    }


    public List<Player> getSpectators() {
        return spectators;
    }


    public void addSpectator(Player p)
    {
        if (getPlayers().contains(p)) {
            playing.remove(p);
        }

        if (!spectators.contains(p)) {
            spectators.add(p);
        }
    }

    public void addPlayer(Player p){
        if (!playing.contains(p) && !spectators.contains(p)) {
            playing.add(p);
        }
    }

    public void updateBoard(Player p)
    {
        if (boards.containsKey(p)) {
            Scoreboard b = boards.get(p);

            b.clearSlot(DisplaySlot.SIDEBAR);
            if (b.getObjective("t") != null) b.getObjective("t").unregister();

            Objective o = b.registerNewObjective("t", "dummy");

            String s = (canStart() ? "§lStarting in §a§l" + format(starting, false) : "§a§lWaiting for Players");

            if (s.length() > 32)
            {
                s = s.substring(0, 31);
            }

            o.setDisplayName(s);

            o.setDisplaySlot(DisplaySlot.SIDEBAR);


            Score c = null;

            c = o.getScore(Bukkit.getOfflinePlayer("§ePlayers:"));
            c.setScore(15);
            c = o.getScore(Bukkit.getOfflinePlayer(getPlayers().size() + "§r§r"));
            c.setScore(14);
            c = o.getScore(Bukkit.getOfflinePlayer("§r§r§r "));
            c.setScore(13);
            c = o.getScore(Bukkit.getOfflinePlayer("§eMax Players:"));
            c.setScore(12);
            c = o.getScore(Bukkit.getOfflinePlayer(getMax() + "§r§r§r"));
            c.setScore(11);
            c = o.getScore(Bukkit.getOfflinePlayer("§r§r§r§r "));
            c.setScore(10);
            c = o.getScore(Bukkit.getOfflinePlayer("§eMin Players:"));
            c.setScore(9);
            c = o.getScore(Bukkit.getOfflinePlayer(getMin() + "§r§r§r§r"));
            c.setScore(8);



            p.setScoreboard(b);

        }
        else {
            boards.put(p, Bukkit.getScoreboardManager().getNewScoreboard());
            updateBoard(p);
        }


        if (spectators.contains(p))
        {
            PlayerInventory pi = p.getInventory();
            pi.clear();
            pi.setArmorContents(null);
            try {
                pi.setItem(0, Finder.getCompass(p));
            }catch (Exception e) {
            }

            if (!UserManager.getUser(p).isSpectator())
            {
                UserManager.getUser(p).setAsSpectator();
            }
        }

    }

    public void setCanStart(boolean canStart) {
        this.canStart = canStart;
    }

    public boolean canStart() {
        return canStart;
    }

    /**
     * When the start() timer is done.
     *
     * Here you can use useEndTimer and see if it should start a countdown.
     *
     * oh, and you should use: setEnding to set it to how long until it should stop.
     */
    public void callStart() {}


    /**
     * Ends game
     */
    public void callEnd() {
        for (Player p : playing){
            clear(p);
            p.teleport(getMap().loadLocation("lobby"));
            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 16);
        }

        for (Player p : spectators)
        {
            clear(p);
            p.teleport(getMap().loadLocation("lobby"));
            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 16);
            if (UserManager.getUser(p).isSpectator()) {
                UserManager.getUser(p).setAsNonSpectator();
            }
        }
        spectators.clear();
        setCanStart(false);
        setStart(20);
        GameAPI.gameEnded(this, new Winner("Default Winner"));
    }


    public void clear(Player p) {
        PlayerInventory pi = p.getInventory();
        pi.clear();
        pi.setArmorContents(null);
        p.setMaxHealth(20);
        p.setHealth(20);
        p.setFoodLevel(20);
    }


    @Deprecated
    public void end() {}


    public boolean isStarted() {
        return started;
    }
    public void setStarted(boolean c){
        this.started = c;
    }










    public void playerLeaves(PlayerQuitEvent e){
        if (playing.contains(e.getPlayer())){
            playing.remove(e.getPlayer());
        }
    }
    public void playerJoins(PlayerJoinEvent e){
        if (!playing.contains(e.getPlayer())) {
            playing.add(e.getPlayer());
        }
    }
    public void playerBreaksBlock(BlockBreakEvent e){e.setCancelled(true);}
    public void playerKicked(PlayerKickEvent e){}
    public void playerPlacesBlock(BlockPlaceEvent e){e.setCancelled(true);}
    public void blockPhysics(BlockPhysicsEvent e){e.setCancelled(true);}
    public void blockFromTo(BlockFromToEvent e){e.setCancelled(true);}
    public void playerDies(PlayerDeathEvent e){}
    public void playerMoves(PlayerMoveEvent e){}
    public void playerTeleports(PlayerTeleportEvent e){}
    public void entityDies(EntityDeathEvent e){}
    public void entityTeleport(EntityTeleportEvent e){}
    public void entityDamaged(EntityDamageEvent e){}
    public void entityDamageEntity(EntityDamageByEntityEvent e){}
    public void entityChangesBlock(EntityChangeBlockEvent e){}
    public void entityExplodes(EntityExplodeEvent e){e.blockList().clear();}








	
	
	
	
	
	public void addTask(BukkitRunnable task) 
	{
		this.task.add(task.getTaskId());
	}
	public List<Integer> getTasks()
	{
		return this.task;
	}
	/**
	 * Currently no way of getting this
	 * 
	 * WARNING: DOES NOT WORK!
	 */
	public BukkitRunnable getTask(int task) 
	{return null;}
	
	
	
	
	
	
	public List<Player> getPlayers() {
		return this.playing;
	}
	public boolean isInUse() {
		return this.inUse;
	}
	public String getName() {
		return this.name;
	}
	
	

}
