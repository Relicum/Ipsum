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

package com.relicum.ipsum.Utils;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.plugin.Plugin;

import java.util.Random;

/**
 * WorldMaker used to created new worlds.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class WorldMaker {
    private Plugin plugin;
    private WorldType worldType = WorldType.FLAT;
    private World.Environment environment = World.Environment.NORMAL;
    private boolean generateStructures = false;


    public WorldMaker(Plugin plugin) {

        this.plugin = plugin;


    }

    public WorldCreator getWorldCreator(String name) {

        WorldCreator worldCreator = new WorldCreator(name);
        worldCreator.environment(environment);
        worldCreator.generateStructures(generateStructures);
        worldCreator.type(worldType);
        // worldCreator.seed(randomSeed());

        return worldCreator;
    }

    public WorldCreator getBlankCreator(String name) {

        return new WorldCreator(name);

    }


    /**
     * Fet a new Random seed for the world
     *
     * @return Random Long as a world seed
     */
    public long randomSeed() {

        Random random = new Random();
        return random.nextLong();
    }

    /**
     * Get plugin.
     *
     * @return the t
     */
    public Plugin getPlugin() {
        return plugin;
    }
}
