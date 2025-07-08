package fr.rhum0ne.epicRings;

import fr.rhum0ne.epicRings.commands.RingsCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class EpicRings extends JavaPlugin {

    public HashMap<String, Ring> rings = new HashMap<>();

    @Override
    public void onEnable() {

        this.getCommand("rings").setExecutor(new RingsCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Ring getRing(String ringName) {
        return rings.get(ringName);
    }
}
