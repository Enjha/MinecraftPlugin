package fr.enjha.events;

import fr.enjha.Main;
import fr.enjha.events.items.EventFreezeBall;
import fr.enjha.events.items.EventGrapple;

import static org.bukkit.Bukkit.getServer;

public class EventsManager {

    private final Main main;

    public EventsManager(Main main){
        this.main = main;
    }

    public void init(){
        getServer().getPluginManager().registerEvents(new EventFallDamage(), this.main);
        getServer().getPluginManager().registerEvents(new EventDoubleJump(), this.main);
        getServer().getPluginManager().registerEvents(new EventUtils(), this.main);
        getServer().getPluginManager().registerEvents(new EventGrapple(this.main), this.main);
        getServer().getPluginManager().registerEvents(new EventFreezeBall(this.main), this.main);
    }

}
