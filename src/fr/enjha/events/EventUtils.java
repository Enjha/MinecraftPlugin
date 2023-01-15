package fr.enjha.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventUtils implements Listener {

    @EventHandler
    public void join(PlayerJoinEvent event) {
        event.getPlayer().getWorld().setTime(1000L);
    }

    // TMP
    @EventHandler
    public void food(PlayerInteractEvent event){
        event.getPlayer().setFoodLevel(20);
        event.getPlayer().setHealth(20);
    }
}
