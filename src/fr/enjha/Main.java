package fr.enjha;

import fr.enjha.commands.CommandsManager;
import fr.enjha.events.EventsManager;
import fr.enjha.items.ItemsManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private ItemsManager itemsManager;

    @Override
    public void onEnable(){
        this.itemsManager = new ItemsManager();
        this.itemsManager.init();
        new CommandsManager(this).init();
        new EventsManager(this).init();
        // Pour le config.yml
       // this.saveDefaultConfig();
    }

    public ItemsManager getItemsManager(){
        return this.itemsManager;
    }
}