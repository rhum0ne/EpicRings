package fr.rhum0ne.epicRings.rings;

import fr.rhum0ne.epicRings.EpicRings;
import lombok.Getter;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.ConcurrentLinkedQueue;

@Getter
public class Ring extends BukkitRunnable {

    private final ConcurrentLinkedQueue<Player> users = new ConcurrentLinkedQueue<>();

    private final Particle particle;
    private final RingAnimation animation;

    public Ring(Particle particle, int period, RingAnimation animation) {
        this.particle = particle;
        this.animation = animation;

        animation.setRing(this);

        runTaskTimer(EpicRings.getPlugin(EpicRings.class), 0, period);
    }

    @Override
    public void run() {
        animation.showState(users);

        animation.nextState();
    }

    public void enable(Player player) {
        users.add(player);
    }

    public void disable(Player player) {
        users.remove(player);
    }
}
