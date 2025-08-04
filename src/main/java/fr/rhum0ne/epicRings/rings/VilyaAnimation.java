package fr.rhum0ne.epicRings.rings;

import fr.rhum0ne.epicRings.utils.CoordSystem;
import org.bukkit.entity.Player;

import java.util.concurrent.ConcurrentLinkedQueue;

import static fr.rhum0ne.epicRings.utils.ParticlesUtils.showCircle;

public class VilyaAnimation extends RingAnimation {

    private static float RADIUS = 1.5f;

    private static double defaultTop = 0.25;
    private static double defaultBottom = -0.25;
    private double hTop = defaultTop;
    private double hBottom = defaultBottom;

    private double step = 0.25;

    public VilyaAnimation() {
        super(6);
    }

    @Override
    public void showState(ConcurrentLinkedQueue<Player> players) {
        double nextHTop = hTop + step;
        double nextHBottom = hBottom - step;

        if(this.getCurrentState() == 0){
            nextHTop = defaultTop;
            nextHBottom = defaultBottom;
        }

        showCircle(players, hTop, RADIUS, CoordSystem.CYLINDRICAL, this.getRing().getParticle());
        showCircle(players, hBottom, RADIUS, CoordSystem.CYLINDRICAL, this.getRing().getParticle());
        showCircle(players, nextHTop, RADIUS, CoordSystem.CYLINDRICAL, this.getRing().getParticle());
        showCircle(players, nextHBottom, RADIUS, CoordSystem.CYLINDRICAL, this.getRing().getParticle());

        hTop = nextHTop;
        hBottom = nextHBottom;
    }
}
