package com.minoid.multiversedivider;

import org.bukkit.plugin.java.JavaPlugin;

import com.minoid.multiversedivider.command.ReloadCommand;
import com.minoid.multiversedivider.listener.PlayerListener;
import com.minoid.multiversedivider.manager.CategoryManager;
import com.minoid.multiversedivider.manager.ConfigManager;
import com.minoid.multiversedivider.manager.VisibilityManager;

public class MultiVerseDivider extends JavaPlugin {

    private CategoryManager categoryManager;
    private ConfigManager configManager;
    private VisibilityManager visibilityManager;

    @Override
    public void onEnable() {
        // Check for Multiverse-Core dependency
        if (getServer().getPluginManager().getPlugin("Multiverse-Core") == null) {
            getLogger().warning("Multiverse-Core not found! Categories will still use Bukkit world names, but MV API functions won't be available if added later.");
        }

        this.categoryManager = new CategoryManager(this);
        this.configManager = new ConfigManager(this);
        this.visibilityManager = new VisibilityManager(this);

        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getCommand("multiversedivider").setExecutor(new ReloadCommand(this));

        // Initial setup for any players already online (e.g., during reload)
        visibilityManager.updateAllVisibilityWithDelay();

        getLogger().info("-----------------------------------");
        getLogger().info("MultiVerseDivider has been enabled!");
        getLogger().info("-----------------------------------");
    }

    @Override
    public void onDisable() {
        getLogger().info("------------------------------------");
        getLogger().info("MultiVerseDivider has been disabled!Hope you Enjoyed the Plugin");
        getLogger().info("------------------------------------");
    }

    public CategoryManager getCategoryManager() {
        return categoryManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public VisibilityManager getVisibilityManager() {
        return visibilityManager;
    }
}
