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

package com.relicum.ipsum.Minecraft;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

import java.util.UUID;

/**
 * WorldBuilder used to create a new world using the builder pattern return the new {@link org.bukkit.World} object.
 *
 * @author Relicum
 */
public class WorldBuilder {

    private WorldCreator world;

    private WorldType worldType;

    private World.Environment environment;

    private String generator;

    private boolean generateStructures;

    private String seed;

    /**
     * Sets World Type.
     *
     * @param worldType the {@link org.bukkit.WorldType} type of the new world!
     * @return instance of it self for chaining the build methods.
     */
    public WorldBuilder setWorldType(WorldType worldType) {
        this.worldType = worldType;
        return this;
    }

    /**
     * Sets World Environment.
     *
     * @param environment the {@link org.bukkit.World.Environment} of the new world
     * @return instance of it self for chaining the build methods.
     */
    public WorldBuilder setEnvironment(World.Environment environment) {
        this.environment = environment;
        return this;
    }

    /**
     * Sets the name of the World Generator. (Optional)
     *
     * @param generator the name of the world generator.
     * @return instance of it self for chaining the build methods.
     * @see com.relicum.ipsum.Utils.WorldGenerator for details on creating a void world.
     */
    public WorldBuilder setGenerator(String generator) {
        this.generator = generator;
        return this;
    }

    /**
     * Sets the option to have structures built in the world.
     *
     * @param generateStructures set to true if you want structures to be made in the world, false if not.
     * @return instance of it self for chaining the build methods.
     */
    public WorldBuilder setGenerateStructures(Boolean generateStructures) {
        this.generateStructures = generateStructures;
        return this;
    }

    /**
     * Sets the new seed of the world, or set to 0 to have a random seed generated
     *
     * @param seed the seed
     * @return instance of it self for chaining the build methods.
     */
    public WorldBuilder setSeed(String seed) {
        if (seed.equalsIgnoreCase("0")) {
            seed = UUID.randomUUID().toString();
        }
        this.seed = seed;
        return this;
    }

    /**
     * Instantiates a new World builder.
     *
     * @param name the name
     */
    private WorldBuilder(String name) {

        world = new WorldCreator(name);

    }

    /**
     * Instantiates a new World builder passing in the name of the new world.
     *
     * @param name the name of the new world. Max 16 chars, no spaces or non alpha numeric chars apart from _
     * @return instance of it self for chaining the build methods.
     */
    public static WorldBuilder getBuilder(String name) {
        return new WorldBuilder(name);
    }

    /**
     * Build the new world and returns the new {@link org.bukkit.World} object.
     *
     * @return the new {@link org.bukkit.World} object.
     */
    public World build() {
        world.type(worldType);
        world.environment(World.Environment.valueOf(environment.name()));
        world.generator(generator);
        world.generateStructures(generateStructures);
        world.seed(Long.valueOf(seed));

        return world.createWorld();
    }
}
