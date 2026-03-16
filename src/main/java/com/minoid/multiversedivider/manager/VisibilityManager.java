package com.minoid.multiversedivider.manager;

import com.minoid.multiversedivider.MultiVerseDivider;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class VisibilityManager {
    private final MultiVerseDivider plugin;

    public VisibilityManager(MultiVerseDivider plugin) {
        this.plugin = plugin;
    }

    public void updateVisibilityWithDelay(Player player) {
        int delay = plugin.getConfigManager().getUpdateDelayTicks();
        if (delay > 0) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    updateVisibility(player);
                }
            }.runTaskLater(plugin, delay);
        } else {
            updateVisibility(player);
        }
    }

    public void updateAllVisibilityWithDelay() {
        int delay = plugin.getConfigManager().getUpdateDelayTicks();
        if (delay > 0) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    updateAllVisibility();
                }
            }.runTaskLater(plugin, delay);
        } else {
            updateAllVisibility();
        }
    }

    public void updateVisibility(Player p) {
        if (!p.isOnline()) return;

        if (!plugin.getConfigManager().isEnabled()) {
            // If disabled, ensure everyone can see everyone
            for (Player target : Bukkit.getOnlinePlayers()) {
                if (!p.equals(target)) {
                    p.showPlayer(plugin, target);
                    target.showPlayer(plugin, p);
                }
            }
            return;
        }

        String world = p.getWorld().getName();
        String category = plugin.getCategoryManager().getCategory(world);

        if (plugin.getConfigManager().isDebugLogging()) {
            plugin.getLogger().info("Updating visibility for " + p.getName() + " in world: " + world + " (Category: " + category + ")");
        }

        for (Player target : Bukkit.getOnlinePlayers()) {
            if (target.equals(p)) continue;

            String targetWorld = target.getWorld().getName();
            String targetCategory = plugin.getCategoryManager().getCategory(targetWorld);

            boolean shouldSee = false;
            // Case 1: Both worlds are in the exact same category
            if (category != null && category.equals(targetCategory)) {
                shouldSee = true;
            } 
            // Case 2: Uncategorized worlds - only see players in exact same world
            else if (category == null && world.equals(targetWorld)) {
                shouldSee = true;
            }

            if (shouldSee) {
                p.showPlayer(plugin, target);
                target.showPlayer(plugin, p);
                if (plugin.getConfigManager().isDebugLogging()) {
                    plugin.getLogger().info(p.getName() + " can now see " + target.getName());
                }
            } else {
                p.hidePlayer(plugin, target);
                target.hidePlayer(plugin, p);
                if (plugin.getConfigManager().isDebugLogging()) {
                    plugin.getLogger().info(p.getName() + " is hidden from " + target.getName());
                }
            }
        }
    }

    public void updateAllVisibility() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            updateVisibility(p);
        }
    }
}
