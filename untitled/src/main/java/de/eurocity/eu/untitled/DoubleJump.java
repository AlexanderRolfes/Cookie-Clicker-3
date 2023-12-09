package de.eurocity.eu.untitled;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

public final class DoubleJump extends JavaPlugin implements Listener{

        private Inventory gui;
        private ItemStack cookie;
    private Map<Player, Integer> cookieCounts;
    private FileConfiguration config;
        @Override
        public void onEnable() {
            getCommand("cookie").setExecutor(new CookieCMD());
            getCommand("cookies").setExecutor(new CookiesCMD());

            Bukkit.getPluginManager().registerEvents(this, this);


            gui = Bukkit.createInventory(null, 4*9, "Cookie Clicker");
            cookieCounts = new HashMap<>();
            config = getConfig();

            cookie = new ItemStack(Material.COOKIE);

            placeCookieInRandomSlot();

            new BukkitRunnable() {
                @Override
                public void run() {
                    placeCookieInRandomSlot();
                }
            }.runTaskTimer(this, 0, 100);
        }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("cookie")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.openInventory(gui);
            }
            return true;
        } else if (command.getName().equalsIgnoreCase("cookies")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                int cookieCount = cookieCounts.getOrDefault(player, 0);
                player.sendMessage("Du hast " + cookieCount + " Cookies gesammelt!");
            } else {
                sender.sendMessage("Dieser Befehl kann nur von Spielern ausgeführt werden!");
            }
            return true;
        }
        return false;
    }

@EventHandler
        public void onInventoryClick(InventoryClickEvent event) {
            Player player = (Player) event.getWhoClicked();

            if (event.getInventory().equals(gui)) {
                if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.COOKIE) {
                    player.sendMessage("Du hast einen Cookie geklickt!");

                    event.getInventory().removeItem(cookie);

                    placeCookieInRandomSlot();

                    int currentCount = cookieCounts.getOrDefault(player, 0);
                    cookieCounts.put(player, currentCount + 1);

                    config.set("cookies." + player.getUniqueId(), currentCount + 1);
                    saveConfig();
                }

                event.setCancelled(true);
            }
        }

        private void placeCookieInRandomSlot() {
            Random random = new Random();
            int slot = random.nextInt(4*9);

            gui.setItem(slot, cookie);
        }
    }

