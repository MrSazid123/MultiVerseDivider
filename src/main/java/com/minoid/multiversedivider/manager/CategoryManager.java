package com.minoid.multiversedivider.manager;

import com.minoid.multiversedivider.MultiVerseDivider;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryManager {
    private final MultiVerseDivider plugin;
    // Map of world name -> category name
    private final Map<String, String> worldCategories = new HashMap<>();
    
    public CategoryManager(MultiVerseDivider plugin) {
        this.plugin = plugin;
    }

    public void loadCategories(FileConfiguration config) {
        worldCategories.clear();
        ConfigurationSection section = config.getConfigurationSection("categories");
        if (section != null) {
            for (String category : section.getKeys(false)) {
                List<String> worlds = section.getStringList(category);
                for (String world : worlds) {
                    worldCategories.put(world.toLowerCase(), category.toLowerCase());
                }
            }
        }
        
        boolean debugLogging = config.getBoolean("settings.debug-logging", false);
        if (debugLogging) {
            plugin.getLogger().info("Loaded " + worldCategories.size() + " categorized worlds.");
        }
    }

    public String getCategory(String worldName) {
        return worldCategories.get(worldName.toLowerCase());
    }
}
