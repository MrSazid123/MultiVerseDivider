# MultiVerseDivider

MultiVerseDivider is a lightweight and professional Minecraft plugin designed for Spigot and Paper servers (1.21+). It automatically segments player visibility and the server's TAB list based on configurable world categories. This ensures that players can only see and interact with other players who are currently in the same category of worlds.

## Features

* **World Categorization:** Group different worlds into logical categories (e.g., survival, minigames, creative).
* **Visibility Isolation:** Players are strictly isolated; they cannot see players in other categories in-game or on the TAB list.
* **Seamless Transitions:** Automatically updates visibility when players switch worlds.
* **Performance Optimized:** Uses configurable ticks delay to prevent lag spikes during mass teleportations or world changes.
* **Multiverse-Core Integration:** Fully supports and depends on Multiverse-Core for world management.

## Installation

1. Download the latest version of the plugin.
2. Place the `MultiVerseDivider-1.0.0.jar` file into your server's `plugins` folder.
3. Ensure that `Multiverse-Core` is also installed in your `plugins` folder.
4. Restart your server to generate the default configuration files.
5. Modify the `config.yml` inside `plugins/MultiVerseDivider/` to suit your server's needs.
6. Run `/mvd reload` in-game or from the console to apply changes.

## Configuration

The `config.yml` allows you to define custom categories and control the plugin's behavior:

```yaml
categories:
  survival:
    - world
    - world_nether
    - world_the_end
  minigames:
    - bedwars
    - skywars
    - parkour
  creative:
    - creative
    - plotworld

settings:
  # Enable or disable the divider system entirely
  enabled: true
  
  # Enable debug logging for troubleshooting player visibility
  debug-logging: false
  
  # Whether to automatically update visibility when a player changes worlds
  update-on-world-change: true
  
  # Delay in ticks before calculating visibility after an event (20 ticks = 1 second)
  update-delay-ticks: 20
```

## Commands & Permissions

### Commands

* `/multiversedivider reload` - Reloads the plugin configuration.
* **Aliases:** `/mvd reload`, `/mvdv reload`

### Permissions

* `multiversedivider.admin` - Allows usage of the reload command. (Default: OP)

## Requirements

* **Server Software:** Spigot, Paper (or forks)
* **Minecraft Version:** 1.21 or higher
* **Java Version:** Java 21+
* **Dependencies:** Multiverse-Core
