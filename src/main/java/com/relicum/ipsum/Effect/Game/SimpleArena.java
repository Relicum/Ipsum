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


    /**
     * Instantiates a new Simple arena.
     *
     * @param uniqueId the unique id
     */
    public SimpleArena(String uniqueId) {
        super();
        this.uniqueId = uniqueId;

    }

    /**
     * Instantiates a new Simple arena.
     *
     * @param arenaName the arena name
     * @param uniqueId  the unique id
     */
    public SimpleArena(String arenaName, String uniqueId) {
        super();
        this.arenaName = arenaName;
        this.uniqueId = uniqueId;
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
     * Sets new minPoint.
     *
     * @param minPoint New value of minPoint.
     */
    public void setMinPoint(SpawnPoint minPoint) {
        this.locations.put("region", minPoint);
    }


    /**
     * Sets new maxPoint.
     *
     * @param maxPoint New value of maxPoint.
     */
    public void setMaxPoint(SpawnPoint maxPoint) {
        this.locations.put("region", maxPoint);
    }

    /**
     * Set lobby spawn {@link com.relicum.ipsum.Location.SpawnPoint}
     *
     * @param lobbySpawn the lobby spawn
     */
    public void setLobbySpawn(SpawnPoint lobbySpawn) {
        this.locations.put("lobby", lobbySpawn);
    }

    /**
     * Get lobby spawn.
     *
     * @return the {@link com.relicum.ipsum.Location.SpawnPoint}
     */
    public SpawnPoint getLobbySpawn() {

        return this.locations.get("lobby").get(0);
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
