package fr.rhum0ne.epicRings.commands;

import fr.rhum0ne.epicRings.EpicRings;
import fr.rhum0ne.epicRings.rings.Ring;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RingsCommand implements CommandExecutor {

    private final EpicRings plugin;

    public RingsCommand() {
        this.plugin = EpicRings.getPlugin(EpicRings.class);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player player)){
            commandSender.sendMessage("Only players can use this command");
            return true;
        }

        if(strings.length == 1 && strings[0].equalsIgnoreCase("reload")){
            if(!player.hasPermission("epicrings.reload")){
                player.sendMessage("You don't have permission to use this command");
                return true;
            }

            player.sendMessage("Config reloaded");
            return true;
        }

        if(strings.length != 2){ return false; }

        String newState = strings[0];
        if(!newState.equalsIgnoreCase("enable") && !newState.equalsIgnoreCase("disable")){ return false; }

        String ringName = strings[1];
        Ring ring = plugin.getRing(ringName);
        if(ring == null){
            player.sendMessage("Ring not found");
            return true;
        }

        if(newState.equalsIgnoreCase("enable")){
            ring.enable(player);
            player.sendMessage("Ring enabled");
        } else {
            ring.disable(player);
            player.sendMessage("Ring disabled");
        }

        return true;
    }
}
