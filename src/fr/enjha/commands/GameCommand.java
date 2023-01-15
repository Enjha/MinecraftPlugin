package fr.enjha.commands;

import fr.enjha.Main;
import fr.enjha.items.FreezeBall;
import fr.enjha.items.GrapplingHook;
import fr.enjha.items.ItemsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GameCommand implements CommandExecutor {

    private ItemsManager itemsManager;

    public GameCommand(Main main){
        this.itemsManager = main.getItemsManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
        if(sender instanceof Player){
            if(command.equalsIgnoreCase("game")){
                Player player = (Player) sender;
                if(args.length == 1){
                    if(args[0].equalsIgnoreCase("enter")){
                        player.sendMessage("§6Vous êtes maintenant dans le jeu !");
                        ItemStack grapplingHook = this.itemsManager.getItemCustom(GrapplingHook.class).getItem();
                        ItemStack freezeBall = this.itemsManager.getItemCustom(FreezeBall.class).getItem();
                        player.getInventory().clear();
                        player.getInventory().setItem(0, grapplingHook);
                        player.getInventory().setItem(1, freezeBall);
                        if(!player.getAllowFlight())
                            player.setAllowFlight(true);
                    }
                }else{
                    player.sendMessage("§cUtilisation: /game enter");
                }
            }
        }
        return false;
    }

}
