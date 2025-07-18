package fr.rhum0ne.epicRings;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Ring extends BukkitRunnable {

    private final ConcurrentLinkedQueue<Player> users = new ConcurrentLinkedQueue<>();

    private final Particle particle;

    public Ring(Particle particle, int period) {
        this.particle = particle;

        runTaskTimer(EpicRings.getPlugin(EpicRings.class), 0, period);
    }

    @Override
    public void run() {
        for(Player player : users){
            player.getWorld().spawnParticle(particle, player.getLocation(), 1, 0, 0, 0);
        }
    }

    public void enable(Player player) {
        users.add(player);
    }

    public void disable(Player player) {
        users.remove(player);
    }
}
