package com.minoid.multiversedivider.command;

import com.minoid.multiversedivider.MultiVerseDivider;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {
    private final MultiVerseDivider plugin;

    public ReloadCommand(MultiVerseDivider plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("multiversedivider.admin")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to perform this command.");
                return true;
            }
            
            plugin.getConfigManager().loadConfig();
            plugin.getVisibilityManager().updateAllVisibilityWithDelay();
            sender.sendMessage(ChatColor.GREEN + "MultiVerseDivider configuration reloaded successfully!");
            return true;
        }
        
        sender.sendMessage(ChatColor.YELLOW + "Usage: /multiversedivider reload");
        return true;
    }
}
