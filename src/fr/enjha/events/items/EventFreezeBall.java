package fr.enjha.events.items;

import fr.enjha.Main;
import fr.enjha.items.FreezeBall;
import fr.enjha.utils.Cooldown;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class EventFreezeBall implements Listener {

    private final Cooldown cooldownFreezeBall;
    private final Cooldown cooldownFreeze = new Cooldown(5);

    public EventFreezeBall(Main main) {
        this.cooldownFreezeBall = main.getItemsManager().getItemCustom(FreezeBall.class).getCooldown();
    }

    @EventHandler
    public void onClickFreezeBall(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (!player.getItemInHand().hasItemMeta())
                return;
            if (player.getItemInHand().getItemMeta().getDisplayName().equals("§3FreezeBall")) {
                if (!cooldownFreezeBall.isPlayerOnCooldown(player)) {
                    cooldownFreezeBall.addPlayerInCooldown(player);
                    player.launchProjectile(Snowball.class).setCustomName("§3FreezeBall");
                } else {
                    player.sendMessage(cooldownFreezeBall.getCooldownMessage(player));
                }
            }
        }
    }

    @EventHandler
    public void onThrowFreezeBall(EntityDamageByEntityEvent event) {
        Entity freezeBall = event.getDamager();
        Entity target = event.getDamager();

        if (event.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
            if (freezeBall instanceof Snowball) {
                if (((Snowball) freezeBall).getShooter() instanceof Player shooter) {
                    if (target instanceof Player player) {
                        if (!cooldownFreeze.isPlayerOnCooldown(player)) {

                            shooter.sendMessage(freezeBall.getCustomName());

                            cooldownFreeze.addPlayerInCooldown(player);
                            shooter.sendMessage("§bVous avez gelé §e" + player.getName() + " §bpour §6" + cooldownFreeze.getCooldownTime() + " §bsecondes !");
                            player.sendMessage("§cVous avez été gelé par une §4FreezeBall, vous ne pouvez plus vous déplacer pendant §e" + cooldownFreeze.getCooldownTime() + "§c secondes");
                        } else {
                            shooter.sendMessage("§cCe joueur est déja gelé, vous devez attendre qu'il ne le soit plus.");
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onFrozenPlayerMove(PlayerMoveEvent event) throws InterruptedException {
        Player player = event.getPlayer();
        if (cooldownFreeze.isPlayerOnCooldown(player)) {
            if (event.getTo().getBlockX() != event.getFrom().getBlockX() || event.getTo().getBlockZ() != event.getFrom().getBlockZ()) {
                player.teleport(event.getFrom());
                player.sendMessage("§cVous encore gelé, il vous reste §e" + this.cooldownFreeze.getCooldownLeft(player) + "  §csecondes avant de pouvoir a nouveau vous déplacé !");
            }
        }
    }
}
