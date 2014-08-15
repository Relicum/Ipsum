/*
 * Ipsum is a rapid development API for Minecraft, developer by Relicum
 * Copyright (C) 2014.  Chris Lutte
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.relicum.ipsum.Configuration;

import net.cubespace.Yamler.Config.Config;
import net.cubespace.Yamler.Config.InvalidConfigurationException;
import org.bukkit.plugin.Plugin;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Name: ConfigManager.java Created: 05 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class ConfigManager {

    private HashMap<String, Config> configHashMap = new HashMap<>();
    private Map<String, Worlds> worldsHashMap = Collections.synchronizedMap(new HashMap<>());
    private Plugin plugin;


    public ConfigManager(Plugin plugin) {
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
     * Get plugin.
     *
     * @return the plugin
     */
    public Plugin getPlugin() {
        return this.plugin;
    }

    /**
     * Init a new Config and store it inside the Manager
     *
     * @param name   The name which should be used to store the Config to
     * @param config The config which should be inited and stored
     */
    public void initWorlds(String name, Worlds config) {
        System.out.println("Attempting to load new World Config '" + name + "'");

        try {
            config.init();
            config.save();
            worldsHashMap.put(name, config);
        } catch (InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Gets the Config stored under the name
     *
     * @param name the name
     * @return the config
     */
    public Config getConfig(String name) {
        plugin.getLogger().info("Getting Config for " + name + ": " + configHashMap.get(name).toString());

        return configHashMap.get(name);
    }

    /**
     * Get world.
     *
     * @param name the name
     * @return the worlds
     */
    public Worlds getWorld(String name) {
        //plugin.getLogger().info("Getting Worlds Config for " + name + ": " + worldsHashMap.get(name).toString());

        return worldsHashMap.get(name);
    }


    public List<String> getEnabled() {
        return worldsHashMap.values().stream().filter(Worlds::getEnable).map(Worlds::getName).collect(Collectors.toCollection(ArrayList<String>::new));
    }

    public void putWorld(String s) {
        initWorlds(s, new Worlds(plugin, s));
    }


    public void putWorlds(List<String> list) {
        if (list.size() == 0) {
            plugin.getLogger().warning("No World files were found");
            return;
        }
        list.forEach(this::putWorld);
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

        for (Map.Entry<String, Worlds> entry : worldsHashMap.entrySet()) {

            try {
                entry.getValue().reload();
            } catch (InvalidConfigurationException e) {
                plugin.getLogger().severe("Error reloading config files");
                throw new RuntimeException(e);
            }
        }

        plugin.getLogger().info("Worlds Configs successfully reloaded");
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
        for (Map.Entry<String, Worlds> entry : worldsHashMap.entrySet()) {

            try {
                entry.getValue().save();
                plugin.getLogger().info("Successfully saved " + entry.getKey());
            } catch (InvalidConfigurationException e) {
                plugin.getLogger().severe("Error saving world config files");
                throw new RuntimeException(e);

            }
        }

    }

    public Map<String, Worlds> getWorldsHashMap() {
        return worldsHashMap;
    }
}
