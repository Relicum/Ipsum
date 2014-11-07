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

import com.relicum.ipsum.Location.SpawnPoint;
import org.bukkit.util.Vector;


/**
 * ArenaRegion is a base interface for arenas that have a fixed region or arena.
 *
 * @author Relicum
 * @version 0.0.1
 */
public interface ArenaRegion {


    /**
     * Get the Arenas unique ID
     *
     * @return the arena UUID
     */
    public String getArenaId();

    /**
     * Gets the Arenas Human readable name
     *
     * @return the arena name
     */
    public String getArenaName();

    /**
     * Sets Arenas Human readable name.
     *
     * @param arenaName the arena name
     */
    public void setArenaName(String arenaName);

    /**
     * Gets max point of the Arena Region
     *
     * @return the max point as {@link com.relicum.ipsum.Location.SpawnPoint}
     */
    public SpawnPoint getMaxPoint();

    /**
     * Gets min point of the Arena Region
     *
     * @return the min point
     */
    public SpawnPoint getMinPoint();

    /**
     * Gets max {@link org.bukkit.util.Vector} of the Arena Region
     *
     * @return the max {@link org.bukkit.util.Vector}
     */
    default Vector getMaxVec() {

        return getMaxPoint().toLocation().toVector();


    }

    /**
     * Gets min {@link org.bukkit.util.Vector} of the Arena Region
     *
     * @return the min {@link org.bukkit.util.Vector}
     */
    default Vector getMinVec() {

        return getMinPoint().toLocation().toVector();
    }


}
