package net.cosmosmc.tryb4.Manager.listeners;

import net.cosmosmc.tryb4.Manager.enums.FriendlyFireSetting;
import net.cosmosmc.tryb4.Manager.teams.core.TeamManager;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Programmed by: DevRo_ (Erik Rosemberg)
 * Creation Date: 01, 02, 2014
 * Programmed for the GameAPI project.
 */
public class NoTeamPVPListener implements Listener {
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player damaged = (Player) event.getEntity();
        Player damager = null;

        if (event.getDamager() instanceof Player) {
            damager = (Player) event.getDamager();
        } else if (event.getDamager() instanceof Projectile) {
            Projectile projectile = (Projectile) event.getDamager();

            if (projectile.getShooter() instanceof Player) {
                damager = (Player) projectile.getShooter();
            }
        }

        if (damager == null) {
            return;
        }

        if (checkDamage(damager, damaged)) {
            event.setCancelled(true);
        }
    }

    public boolean checkDamage(Player player1, Player player2) {
        if (!TeamManager.getInstance().getTeamFromPlayer(player1).getName().equals(TeamManager.getInstance().getTeamFromPlayer(player2).getName())) {
            return (false);
        }

        return (TeamManager.getInstance().getTeamFromPlayer(player1).getFriendlyFireSetting() == FriendlyFireSetting.DISALLOW);
    }
}
