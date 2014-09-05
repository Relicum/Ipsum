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
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Name: Location.java Created: 30 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class Locations extends Config {

    private transient Random random = new Random();
    @Path("section")
    private ConfigSection section = new ConfigSection();
    @Path("section.spawns")
    private HashMap<String, Location> spawns = new HashMap<>();

    public Locations(Plugin plugin, String name) {

        CONFIG_FILE = new File(plugin.getDataFolder(), File.separator + name + ".yml");
        CONFIG_HEADER = new String[]{"This stores the details for custom locations: " + name};

    }

    public synchronized void addLocation(org.bukkit.Location loc) {
        this.spawns.put("my" + random.nextInt(1000), loc);
        System.out.println("Location added");
    }

    public synchronized void getListOfLocations() {

        for (Map.Entry<String, Location> entry : spawns.entrySet()) {

            System.out.println("Name is: " + entry.getKey() + "with a value of " + entry.getValue().toVector().toString());
        }

    }

    public synchronized void removeLocation(org.bukkit.Location loc) {
        if (spawns.containsValue(loc)) {
            spawns.remove("my");
            System.out.println("Location Deleted");
        }

    }
}
