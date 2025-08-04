package fr.rhum0ne.epicRings.utils;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public enum CoordSystem {

    SPHERICAL {// (r, θ, φ)
        @Override public Vector toXYZ(double r, double theta, double phi) {
            double sinPhi = Math.sin(phi);
            return new Vector(
                    r * Math.cos(theta) * Math.cos(phi),
                    r * Math.sin(theta),
                    r * Math.cos(theta) * sinPhi
            );
        }
    },

    CYLINDRICAL {// (ρ, θ, h)
        @Override public Vector toXYZ(double ρ, double h, double theta) {
            return new Vector(
                    ρ * Math.cos(theta),
                    h,
                    ρ * Math.sin(theta)
            );
        }
    };

    public abstract Vector toXYZ(double r,double theta,double phi);

    public static double[] getPlayerCenter(Player player){
        return new double[] {
                player.getLocation().getX(),
                player.getLocation().getY()+1,
                player.getLocation().getZ()
        };
    }
}
