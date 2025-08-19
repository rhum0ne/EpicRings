package fr.rhum0ne.epicRings.commands;

import fr.rhum0ne.epicRings.EpicRings;
import fr.rhum0ne.epicRings.rings.Ring;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RingsCommand implements CommandExecutor {

    private final EpicRings plugin;
    private static final String PREFIX = ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "EpicRings"
            + ChatColor.GRAY + " » " + ChatColor.RESET;

    public RingsCommand(EpicRings plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Console ?
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Seuls les joueurs peuvent utiliser cette commande.");
            return true;
        }
        Player player = (Player) sender;

        // Mauvais arguments → message d'usage clair
        if (args.length != 2) {
            sendUsage(player, label);
            return true;
        }

        final String actionRaw = args[0];
        final String ringName  = args[1];

        // Action attendue : enable/disable (avec quelques synonymes FR/EN)
        final boolean enable;
        if (actionRaw.equalsIgnoreCase("enable")) {
            enable = true;
        } else if (actionRaw.equalsIgnoreCase("disable")) {
            enable = false;
        } else {
            sendUsage(player, label);
            return true;
        }

        // Récupération du ring
        Ring ring = plugin.getRingsManager().getRing(ringName);
        if (ring == null) {
            player.sendMessage(PREFIX + ChatColor.RED + "Anneau introuvable : " + ChatColor.YELLOW + ringName + ChatColor.GRAY + ".");
            return true;
        }

        // État courant pour ce joueur
        boolean alreadyEnabled = ring.getUsers().contains(player); // <-- nécessite Ring#isEnabled(Player)

        // Empêcher les actions redondantes
        if (enable && alreadyEnabled) {
            player.sendMessage(PREFIX + ChatColor.YELLOW + "L’anneau " + ChatColor.GOLD + ringName
                    + ChatColor.YELLOW + " est déjà activé.");
            return true;
        }
        if (!enable && !alreadyEnabled) {
            player.sendMessage(PREFIX + ChatColor.YELLOW + "L’anneau " + ChatColor.GOLD + ringName
                    + ChatColor.YELLOW + " n’est pas activé.");
            return true;
        }

        // Exécuter et informer
        if (enable) {
            ring.enable(player);
            player.sendMessage(PREFIX + ChatColor.GREEN + "Vous venez d'activer : " + ChatColor.GOLD + ringName);
        } else {
            ring.disable(player);
            player.sendMessage(PREFIX + ChatColor.RED + "Vous venez de désactiver : " + ChatColor.GOLD + ringName);
        }

        return true;
    }

    private void sendUsage(Player player, String label) {
        player.sendMessage(PREFIX + ChatColor.GRAY + "Utilisation : "
                + ChatColor.AQUA + "/" + label + " <enable|disable> <nomAnneau>");
        player.sendMessage(ChatColor.GRAY + "Exemples : "
                + ChatColor.DARK_AQUA + "/" + label + " enable speed "
                + ChatColor.GRAY + "• "
                + ChatColor.DARK_AQUA + "/" + label + " disable speed");
    }
}
