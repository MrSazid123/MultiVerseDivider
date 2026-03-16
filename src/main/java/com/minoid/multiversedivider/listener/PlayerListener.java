package com.minoid.multiversedivider.listener;

import com.minoid.multiversedivider.MultiVerseDivider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
    private final MultiVerseDivider plugin;

    public PlayerListener(MultiVerseDivider plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        plugin.getVisibilityManager().updateVisibilityWithDelay(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Player is removed from server's online list, visibility will update automatically
        // No explicit hiding needed, but we could add cleanup if we cached per-player state
    }

    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        if (plugin.getConfigManager().isUpdateOnWorldChange()) {
            plugin.getVisibilityManager().updateVisibilityWithDelay(event.getPlayer());
        }
    }
}
