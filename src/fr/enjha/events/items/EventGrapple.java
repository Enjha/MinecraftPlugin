package fr.enjha.events.items;

import fr.enjha.Main;
import fr.enjha.items.GrapplingHook;
import fr.enjha.utils.Cooldown;
import org.bukkit.Location;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class EventGrapple implements Listener {

    private final Cooldown cooldown;

    public EventGrapple(Main main) {
        this.cooldown = main.getItemsManager().getItemCustom(GrapplingHook.class).getCooldown();
    }

    @EventHandler
    public void onUseGrapple(PlayerFishEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();
        ItemMeta meta = item.getItemMeta();
        String itemName = meta.getDisplayName();

        if (itemName.equals("§2Grappling Hook")) {
            if (event.getState() != PlayerFishEvent.State.FISHING) {
                if (this.cooldown.isPlayerOnCooldown(player)) {
                    player.sendMessage(this.cooldown.getCooldownMessage(player));
                } else {
                    grapplingHook(event.getHook(), player);
                    this.cooldown.addPlayerInCooldown(player);
                }
            }
        }
    }

    public void grapplingHook(FishHook fishhook, Player p) {
        Location location = fishhook.getLocation();
        double dx = location.getX() - p.getLocation().getX();
        double dy = location.getY() - p.getLocation().getY();
        double dz = location.getZ() - p.getLocation().getZ();
        double distance = Math.sqrt((dx * dx) + (dy * dy) + (dz * dz));
        double multiplier = 0.500;
        Vector velocity = new Vector(dx * multiplier, (dy * multiplier) + Math.sqrt(distance) * 0.10, dz * multiplier);
        p.setVelocity(velocity);
    }
}
