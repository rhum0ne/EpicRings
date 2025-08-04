package fr.rhum0ne.epicRings.utils;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import static fr.rhum0ne.epicRings.utils.CoordSystem.getPlayerCenter;

public class CirclesUtils {

    public static void showCircle(ConcurrentLinkedQueue<Player> players, double theta, double radius, CoordSystem coordSystem, Particle particle){
        for(double phi = 0; phi < 2*Math.PI; phi += 0.1 ){
            Vector particleXYZ = coordSystem.toXYZ(radius, theta, phi); //C'est radius, h, theta si CYLINDRICAL mais on se comprend

            for(Player player : players){
                double[] playerXYZ = getPlayerCenter(player);
                player.getWorld().spawnParticle(particle,
                        playerXYZ[0] + particleXYZ.getX(),
                        playerXYZ[1] + particleXYZ.getY(),
                        playerXYZ[2] + particleXYZ.getZ(),
                        1, 0, 0, 0, 0
                );
            }
        }
    }
}
