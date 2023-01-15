package fr.enjha.events;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EventFallDamage implements Listener {

    @EventHandler
    public void explodeOnFallDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player player))
            return;

        // TMP
        e.setCancelled(true);

        if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            e.setCancelled(true);
            Location location = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() - 1, player.getLocation().getZ());
            World world = location.getWorld();
            int radius = 3;
            int bX = location.getBlockX();
            int bY = location.getBlockY();
            int bZ = location.getBlockZ();
            for (int x = bX - radius; x <= bX + radius; x++) {
                for (int y = bY - radius; y <= bY + radius; y++) {
                    for (int z = bZ - radius; z <= bZ + radius; z++) {
                        double distance = ((bX - x) * (bX - x) + ((bZ - z) * (bZ - z)) + ((bY - y) * (bY - y)));
                        if (distance < radius * radius && y <= location.getBlockY()) {
                            Location l = new Location(world, x, y, z);
                            l.getBlock().breakNaturally();
                            l.getWorld().playEffect(l, Effect.EXPLOSION, 5);
                            player.teleport(location);
                        }
                    }
                }
            }
        }
    }
}