package fr.rhum0ne.epicRings.rings;

import fr.rhum0ne.epicRings.utils.CoordSystem;
import org.bukkit.entity.Player;

import java.util.concurrent.ConcurrentLinkedQueue;

import static fr.rhum0ne.epicRings.utils.ParticlesUtils.showParticle;

public class NenyaAnimation extends RingAnimation {

    private static float RADIUS = 1.5f;

    private double angle = 0;
    private double step;

    public NenyaAnimation() {
        super(60);

        this.step = 2*Math.PI/this.getStates();
    }

    @Override
    public void showState(ConcurrentLinkedQueue<Player> players) {

        showParticle(players, angle, 2*angle, RADIUS, CoordSystem.SPHERICAL, this.getRing().getParticle());

        angle += step;
        if(angle >= 2*Math.PI){
            angle = angle - 2*Math.PI;
        }
    }
}
