package fr.rhum0ne.epicRings;

import fr.rhum0ne.epicRings.commands.RingsCommand;
import fr.rhum0ne.epicRings.rings.*;
import lombok.Getter;
import org.bukkit.Particle;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

@Getter
public final class EpicRings extends JavaPlugin {

    public static EpicRings plugin;
    private RingsManager ringsManager;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();

        this.getCommand("rings").setExecutor(new RingsCommand(this));

        ringsManager = new RingsManager();

        ringsManager.registerRing("narya", new Ring(Particle.FLAME, 2, new NaryaAnimation()));
        ringsManager.registerRing("nenya", new Ring(Particle.SNOWFLAKE, 1, new NenyaAnimation()));
        ringsManager.registerRing("vilya", new Ring(Particle.WAX_OFF, 20, new VilyaAnimation()));
    }

    @Override
    public void onDisable() {
        ringsManager.stopAnimations();
    }

}
