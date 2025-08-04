package fr.rhum0ne.epicRings;

import fr.rhum0ne.epicRings.commands.RingsCommand;
import fr.rhum0ne.epicRings.rings.Ring;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class EpicRings extends JavaPlugin {

    public HashMap<String, Ring> rings = new HashMap<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();

        this.getCommand("rings").setExecutor(new RingsCommand());
    }

    @Override
    public void onDisable() {
        for(Ring ring : rings.values()){
            ring.cancel();
        }
    }

    public Ring getRing(String ringName) {
        return rings.get(ringName);
    }

    public static void registerRing(String ringName, Ring ring){
        EpicRings plugin = getPlugin(EpicRings.class);
        plugin.rings.put(ringName, ring);
    }

}
