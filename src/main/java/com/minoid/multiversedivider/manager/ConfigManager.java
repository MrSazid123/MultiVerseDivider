package com.minoid.multiversedivider.manager;

import com.minoid.multiversedivider.MultiVerseDivider;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
    private final MultiVerseDivider plugin;
    private boolean enabled;
    private boolean debugLogging;
    private boolean updateOnWorldChange;
    private int updateDelayTicks;

    public ConfigManager(MultiVerseDivider plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();

        this.enabled = config.getBoolean("settings.enabled", true);
        this.debugLogging = config.getBoolean("settings.debug-logging", false);
        this.updateOnWorldChange = config.getBoolean("settings.update-on-world-change", true);
        this.updateDelayTicks = config.getInt("settings.update-delay-ticks", 20);
        
        plugin.getCategoryManager().loadCategories(config);
    }

    public boolean isEnabled() { return enabled; }
    public boolean isDebugLogging() { return debugLogging; }
    public boolean isUpdateOnWorldChange() { return updateOnWorldChange; }
    public int getUpdateDelayTicks() { return updateDelayTicks; }
}
