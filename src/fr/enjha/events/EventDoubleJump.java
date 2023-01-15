package fr.enjha.events;

import fr.enjha.utils.Cooldown;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EventDoubleJump implements Listener {

    private final Cooldown cooldown = new Cooldown(3);

    @EventHandler
    public void setFlyOnJump(PlayerToggleFlightEvent event) {
        if (event.isFlying() && event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            Player player = event.getPlayer();
            if (this.cooldown.isPlayerOnCooldown(player)) {
                event.setCancelled(true);
                player.playSound(player.getLocation(), Sound.NOTE_BASS, 10.0F, 10.0F);
                player.sendMessage(this.cooldown.getCooldownMessage(player));
                return;
            }
            player.setFlying(false);
            Vector jump = player.getLocation().getDirection().multiply(3).setY(1.1);
            player.setVelocity(player.getVelocity().add(jump));
            player.playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 10.0F, 100.0F);
            this.cooldown.addPlayerInCooldown(player);
            event.setCancelled(true);
        }
    }
}
