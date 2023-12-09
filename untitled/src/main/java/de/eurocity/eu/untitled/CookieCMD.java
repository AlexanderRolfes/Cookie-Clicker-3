package de.eurocity.eu.untitled;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class CookieCMD implements CommandExecutor {
    private Inventory gui =Bukkit.createInventory(null,4*9,"Cookie Clicker");

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("cookie")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.openInventory(gui);
            }
            return true;
        }
        return false;
    }
}