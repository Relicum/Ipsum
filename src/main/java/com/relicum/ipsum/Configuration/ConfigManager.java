package com.relicum.ipsum.Configuration;

import com.relicum.ipsum.Ipsum;
import net.cubespace.Yamler.Config.Config;
import net.cubespace.Yamler.Config.InvalidConfigurationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Name: ConfigManager.java Created: 05 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class ConfigManager {

    private HashMap<String, Config> configHashMap = new HashMap<>();
    private Ipsum plugin;

    public ConfigManager(Ipsum plugin) {
        this.plugin = plugin;

    }

    /**
     * Init a new Config and store it inside the Manager
     *
     * @param name   The name which should be used to store the Config to
     * @param config The config which should be inited and stored
     */
    public void initConfig(String name, Config config) {
        System.out.println("Attempting to load new Config '" + name + "'");

        try {
            config.init();
            config.save();
            configHashMap.put(name, config);
        } catch (InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Gets the Config stored under the name
     *
     * @param <T>  the type parameter
     * @param name the name
     * @return the config
     */
    public <T> T getConfig(String name) {
        plugin.getLogger().info("Getting Config for " + name + ": " + configHashMap.get(name).toString());

        return (T) configHashMap.get(name);
    }

    /**
     * Reload all config files
     */
    public void reloadAll() {

        for (Map.Entry<String, Config> entry : configHashMap.entrySet()) {

            try {
                entry.getValue().reload();
            } catch (InvalidConfigurationException e) {
                plugin.getLogger().severe("Error reloading config files");
                throw new RuntimeException(e);
            }
        }

        plugin.getLogger().info("Configs successfully reloaded");
    }


    /**
     * Save all config files
     */
    public void saveAll() {

        for (Map.Entry<String, Config> entry : configHashMap.entrySet()) {

            try {
                entry.getValue().save();
                plugin.getLogger().info("Successfully saved " + entry.getKey());
            } catch (InvalidConfigurationException e) {
                plugin.getLogger().severe("Error saving config files");
                throw new RuntimeException(e);

            }
        }

    }

}
