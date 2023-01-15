package fr.enjha.items;

import fr.enjha.utils.Cooldown;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class FreezeBall implements ItemCustom {

    private ItemStack freezeBall;
    private final Cooldown cooldown;

    public FreezeBall(Cooldown cooldown) {
        this.cooldown = cooldown;
    }

    @Override
    public void init() {
        List<String> lore = new ArrayList<>();
        lore.add("§bJeter cette boule sur un ennemi pour le gelé !");
        lore.add("§bCet item a 15 secondes de cooldown.");
        this.freezeBall = this.createItem(Material.SNOW_BALL, 12, "§3FreezeBall", lore);
    }

    @Override
    public ItemStack getItem() {
        return this.freezeBall;
    }

    public Cooldown getCooldown() {
        return this.cooldown;
    }
}
