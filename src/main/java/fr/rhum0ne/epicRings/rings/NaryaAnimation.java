package fr.rhum0ne.epicRings.rings;

import fr.rhum0ne.epicRings.utils.CoordSystem;
import org.bukkit.entity.Player;

import java.util.concurrent.ConcurrentLinkedQueue;

import static fr.rhum0ne.epicRings.utils.ParticlesUtils.showCircle;

public class NaryaAnimation extends RingAnimation {

    private static float RADIUS = 1.5f;


    public NaryaAnimation() {
        super(40);
    }

    @Override
    public void showState(ConcurrentLinkedQueue<Player> players) {
        //Pour faire l'aller-retour (20 est le plus haut, 0 le plus bas)
        int h = Math.abs(this.getCurrentState() - this.getStates()/2);

        //Donc 20 = 90°, 10 = 0°, 0 = -90°
        double phi = Math.toRadians(9*h - 90);

        showCircle(players, phi, RADIUS, CoordSystem.SPHERICAL, this.getRing().getParticle());
    }
}
