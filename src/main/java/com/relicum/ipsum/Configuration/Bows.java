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
import net.cubespace.Yamler.Config.ConfigSection;
import net.cubespace.Yamler.Config.Path;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.HashMap;

/**
 * Name: Bows.java Created: 22 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class Bows extends Config {

    private ConfigSection root = new ConfigSection();

    @Path("root.bows")
    private HashMap<String, BowConfig> bows = new HashMap<>();

    public Bows(Plugin plugin, String name) {

        CONFIG_FILE = new File(plugin.getDataFolder(), File.separator + name + ".yml");
        CONFIG_HEADER = new String[]{"This stores the details for custom bows: " + name};
    }

    public void addBowConfig(String name, BowConfig config) {

        if (!bows.containsKey(name)) {
            bows.put(name, config);
        }

    }

    public BowConfig getBowConfig(String name) {

        return bows.get(name);
    }

    public void removeBowConfig(String name) {

        bows.remove(name);

    }
}
