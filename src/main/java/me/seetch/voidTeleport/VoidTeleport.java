package me.seetch.voidTeleport;

import me.seetch.voidTeleport.command.VoidTeleportCommand;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class VoidTeleport extends JavaPlugin implements Listener {

    private FileConfiguration config;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("voidteleport").setExecutor(new VoidTeleportCommand(this));
    }

    @EventHandler
    public void onPlayerFallIntoVoid(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        String worldName = player.getLocation().getWorld().getName();

        if (config.contains("worlds." + worldName)) {
            boolean enabled = config.getBoolean("worlds." + worldName + ".enabled");
            double voidY = config.getDouble("worlds." + worldName + ".void_y");

            if (enabled && event.getFrom().getY() > event.getTo().getY() && player.getLocation().getY() < voidY) {
                List<String> commands = config.getStringList("worlds." + worldName + ".commands");

                for (String command : commands) {
                    String playerCommand = command.replace("%player%", player.getName());
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), playerCommand);
                }
            }
        }
    }
}