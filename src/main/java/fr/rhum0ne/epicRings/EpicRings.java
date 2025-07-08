package fr.rhum0ne.epicRings;

import fr.rhum0ne.epicRings.commands.RingsCommand;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.module.Configuration;
import java.util.HashMap;

public final class EpicRings extends JavaPlugin {

    public HashMap<String, Ring> rings = new HashMap<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();

        this.getCommand("rings").setExecutor(new RingsCommand());

        loadConfig();
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

    private void loadConfig(){
        FileConfiguration config = this.getConfig();

        ConfigurationSection ringsSection = config.getConfigurationSection("rings");
        if(ringsSection == null){
            Bukkit.getLogger().warning("No rings found in config");
            return;
        }

        for(String ringName : ringsSection.getKeys(false)){
            ConfigurationSection ringSection = ringsSection.getConfigurationSection(ringName);
            if(ringSection == null){
                Bukkit.getLogger().warning("No ring found in config for " + ringName);
                continue;
            }

            String particleName = ringSection.getString("particle");
            if(particleName == null){ particleName = "WAX_OFF"; }
            Particle particle = Particle.valueOf(particleName);

            int period = ringSection.getInt("period");
            if(period <= 0){ period = 1; }

            Ring ring = new Ring(particle, period);
            rings.put(ringName, ring);

            Bukkit.getLogger().info("Ring " + ringName + " loaded with particle " + particleName + " and period " + period);
        }
    }

    public void reload() {
        for(Ring ring : rings.values()){
            ring.cancel();
        }
        this.rings.clear();
        this.loadConfig();
    }

}
