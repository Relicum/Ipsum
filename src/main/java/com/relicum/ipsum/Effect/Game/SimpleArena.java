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

package com.relicum.ipsum.Effect.Game;

import com.relicum.ipsum.Configuration.AbstractSerializable;
import com.relicum.ipsum.Location.LocationListMultimap;
import com.relicum.ipsum.Location.SpawnPoint;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.util.Vector;

import java.util.List;

/**
 * Name: SimpleArena.java Created: 29 October 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
@SerializableAs("SimpleArea")
public class SimpleArena extends AbstractSerializable implements ArenaRegion, ConfigurationSerializable {

    private LocationListMultimap locations = new LocationListMultimap();

    //private Map<String, Collection<SpawnPoint>> listMap = locations.asMap();

    private String uniqueId;
    private String arenaName;
    private ArenaState state;


    private SimpleArena(String uniqueId) {
        super();
        this.uniqueId = uniqueId;

    }


    private SimpleArena(String arenaName, String uniqueId) {
        super();
        this.arenaName = arenaName;
        this.uniqueId = uniqueId;
    }

    /**
     * Instantiates a new Simple arena.
     *
     * @param uniqueId the unique id
     * @return instance of {@link com.relicum.ipsum.Effect.Game.SimpleArena}
     */
    public static SimpleArena createSimpleArena(String uniqueId) {
        return new SimpleArena(uniqueId);
    }

    /**
     * Instantiates a new Simple arena.
     *
     * @param arenaName the arena name
     * @param uniqueId  the unique id
     * @return instance of {@link com.relicum.ipsum.Effect.Game.SimpleArena}
     */
    public static SimpleArena createSimpleArena(String arenaName, String uniqueId) {
        return new SimpleArena(arenaName, uniqueId);
    }


    /**
     * Get the Arenas unique ID
     *
     * @return the arena id
     */
    @Override
    public String getArenaId() {
        return this.uniqueId;
    }

    /**
     * Gets the Arenas Human readable name
     *
     * @return the arena name
     */
    @Override
    public String getArenaName() {
        return this.arenaName;
    }

    /**
     * Sets Arenas Human readable name.
     *
     * @param arenaName the arena name
     */
    @Override
    public void setArenaName(String arenaName) {
        this.arenaName = arenaName;
    }

    /**
     * Gets max point of the Arena Region
     *
     * @return the max point as {@link com.relicum.ipsum.Location.SpawnPoint}
     */
    @Override
    public SpawnPoint getMaxPoint() {
        return locations.get("region").get(1);
    }

    /**
     * Gets min point of the Arena Region
     *
     * @return the min point
     */
    @Override
    public SpawnPoint getMinPoint() {
        return locations.get("region").get(0);
    }

    /**
     * Gets max {@link org.bukkit.util.Vector} of the Arena Region
     *
     * @return the max {@link org.bukkit.util.Vector}
     */
    @Override
    public Vector getMaxVec() {
        return locations.get("region").get(1).toLocation().toVector();
    }

    /**
     * Gets min {@link org.bukkit.util.Vector} of the Arena Region
     *
     * @return the min {@link org.bukkit.util.Vector}
     */
    @Override
    public Vector getMinVec() {
        return locations.get("region").get(0).toLocation().toVector();
    }

    /**
     * Sets default spawn.
     *
     * @param defaultSpawn the default spawn
     */
    @Override
    public void setDefaultSpawn(SpawnPoint defaultSpawn) {
        locations.put("default-spawn", defaultSpawn);
    }

    /**
     * Gets default spawn.
     *
     * @return the default spawn
     */
    @Override
    public SpawnPoint getDefaultSpawn() {
        return locations.get("default-spawn").get(0);
    }

    /**
     * Get Arena state.
     *
     * @return the state
     */
    @Override
    public ArenaState getState() {
        return state;
    }

    /**
     * Update Arena state.
     *
     * @param state the state
     */
    @Override
    public void updateState(ArenaState state) {
        this.state = state;
    }


    /**
     * Get List of player {@link com.relicum.ipsum.Location.SpawnPoint}
     *
     * @return the list
     */
    public List<SpawnPoint> getPlayerSpawns() {

        return this.locations.get("player");
    }

    /**
     * Add a player {@link com.relicum.ipsum.Location.SpawnPoint}
     *
     * @param playerSpawn the player spawn
     */
    public void addPlayerSpawn(SpawnPoint playerSpawn) {

        this.locations.put("player", playerSpawn);
    }
}
