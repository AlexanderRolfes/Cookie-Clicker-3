package de.eurocity.eu.untitled;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CookiesCMD implements CommandExecutor {

    private Map<Player, Integer> cookieCounts;

    public void setCookieCounts(Map<Player, Integer> cookieCounts) {
        this.cookieCounts = cookieCounts = new HashMap<>();
    } ;
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
     if (cmd.getName().equalsIgnoreCase("cookies")) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            int cookieCount = cookieCounts.getOrDefault(player, 0);
            player.sendMessage("Du hast " + cookieCount + " Cookies gesammelt!");
        } else {
            sender.sendMessage("Dieser Befehl kann nur von Spielern ausgeführt werden!");
        }
        return true;
    } return false;
}
}
