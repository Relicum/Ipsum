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

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;

/**
 * Name: ConfigAccesor.java Created: 15 November 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class ConfigAccessor {

    private String fileName;

    private JavaPlugin plugin;

    private File configFile;

    private FileConfiguration fileConfiguration;

    private YamlConfiguration yml;

    /**
     * Default Constructor
     *
     * @param plugin   JavaPlugin
     * @param fileName String
     */
    public ConfigAccessor(JavaPlugin plugin, String fileName) {

        if (plugin == null) throw new IllegalArgumentException("plugin cannot be null");

        this.plugin = plugin;
        this.fileName = fileName;

        if (!plugin.getDataFolder().exists()) {

            try {
                Files.createDirectory(Paths.get(plugin.getDataFolder().toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.configFile = new File(plugin.getDataFolder(), fileName);
    }

    private void loadFile() {

        fileConfiguration = YamlConfiguration.loadConfiguration(configFile);

    }

    /**
     * Get config as instance of FileConfiguration
     *
     * @return FileConfiguration
     */
    public FileConfiguration getConfig() {

        if (fileConfiguration == null) {
            this.reloadConfig();
        }
        return fileConfiguration;
    }


    /**
     * Reloads the config fiule
     */
    public void reloadConfig() {

        fileConfiguration = YamlConfiguration.loadConfiguration(configFile);

        // Look for defaults in the jar
        InputStream defConfigStream = plugin.getResource(fileName);
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            fileConfiguration.setDefaults(defConfig);
        }
    }


    /**
     * Saves the config file
     */
    public void saveConfig() {

        if (fileConfiguration == null || configFile == null) {
            return;
        } else {
            try {
                getConfig().save(configFile);
            } catch (IOException ex) {
                plugin.getLogger().log(Level.SEVERE, "Could not save config to " + configFile, ex);
            }
        }
    }


    /**
     * Saves the default config
     */
    public void saveDefaultConfig() {

        if (!configFile.exists()) {
            this.plugin.saveResource(fileName, false);
        }
    }
}
