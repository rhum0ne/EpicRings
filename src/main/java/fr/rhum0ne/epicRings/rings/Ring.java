package fr.rhum0ne.epicRings.rings;

import fr.rhum0ne.epicRings.EpicRings;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Ring extends BukkitRunnable {

    private final ConcurrentLinkedQueue<Player> users = new ConcurrentLinkedQueue<>();

    private final Particle particle;
    private final int maxFrame;
    private final RingAnimation animation;

    public Ring(Particle particle, int period, int maxFrame, RingAnimation animation) {
        this.particle = particle;
        this.maxFrame = maxFrame;
        this.animation = animation;

        runTaskTimer(EpicRings.getPlugin(EpicRings.class), 0, period);
    }

    @Override
    public void run() {
        for(Player player : users){
            animation.showState(player);
        }

        animation.nextState();
    }

    public void enable(Player player) {
        users.add(player);
    }

    public void disable(Player player) {
        users.remove(player);
    }
}
