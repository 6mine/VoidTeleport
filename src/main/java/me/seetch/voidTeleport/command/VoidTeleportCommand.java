package me.seetch.voidTeleport.command;

import me.seetch.voidTeleport.VoidTeleport;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class VoidTeleportCommand implements CommandExecutor {
    private final Plugin plugin;

    public VoidTeleportCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            ((VoidTeleport) plugin).reloadConfig();
            sender.sendMessage("§aКонфигурация VoidTeleport перезагружена.");
            return true;
        }
        return false;
    }
}
