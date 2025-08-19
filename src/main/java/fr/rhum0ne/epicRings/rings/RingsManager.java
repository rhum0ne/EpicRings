package fr.rhum0ne.epicRings.rings;

import fr.rhum0ne.epicRings.EpicRings;

import java.util.HashMap;

public class RingsManager {

    public HashMap<String, Ring> rings = new HashMap<>();

    public Ring getRing(String ringName) {
        return rings.get(ringName);
    }

    public void registerRing(String ringName, Ring ring){
        rings.put(ringName, ring);
    }

    public void stopAnimations() {
        for(Ring ring : rings.values()){
            ring.cancel();
        }
    }
}
