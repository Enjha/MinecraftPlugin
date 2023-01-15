package fr.enjha.items;

import fr.enjha.utils.Cooldown;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GrapplingHook implements ItemCustom{

    private ItemStack grapplingHook;
    private final Cooldown cooldown;

    public GrapplingHook(Cooldown cooldown){
        this.cooldown = cooldown;
    }
    @Override
    public void init(){
        List<String> lore = new ArrayList<>();
        lore.add("§aPermet de vous projeter n'importe ou !");
        lore.add("§aCet item a 7 secondes de cooldown.");
        this.grapplingHook = this.createItem(Material.FISHING_ROD, 1, "§2Grappling Hook", lore);
    }

    @Override
    public ItemStack getItem(){
        return this.grapplingHook;
    }

    public Cooldown getCooldown(){
        return this.cooldown;
    }

}
