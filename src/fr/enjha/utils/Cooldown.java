package fr.enjha.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Cooldown {

    private final Map<UUID, Double> cooldownPlayerList;
    private final double cooldownTime;
    private final String TEMPLATE_MESSAGE = "§2Cooldown left: §a";

    public Cooldown(double cooldownTime) {
        this.cooldownTime = cooldownTime;
        this.cooldownPlayerList = new HashMap<>();
    }

    public Map<UUID, Double> getCooldownPlayerList() {
        return cooldownPlayerList;
    }

    public double getCooldownTime() {
        return cooldownTime;
    }

    public double getCooldownLeft(Player p) {
        double timeleft = (this.cooldownPlayerList.get(p.getUniqueId()) - System.currentTimeMillis()) / 1000;
        return Math.round(timeleft * 100.0) / 100.0;
    }

    public String getCooldownMessage(Player p) {
        return TEMPLATE_MESSAGE + this.getCooldownLeft(p);
    }

    public void addPlayerInCooldown(Player p) {
        this.cooldownPlayerList.put(p.getUniqueId(), (double) (System.currentTimeMillis() + (this.cooldownTime * 1000)));
    }

    public boolean isPlayerOnCooldown(Player p) {
        if (cooldownPlayerList.containsKey(p.getUniqueId())) {
            return cooldownPlayerList.get(p.getUniqueId()) > System.currentTimeMillis();
        }
        return false;
    }
}
