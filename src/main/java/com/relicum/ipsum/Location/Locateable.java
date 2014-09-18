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

package com.relicum.ipsum.Location;

import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * Interface Locateable indicates that the class represents or contains a Bukkit Object that has a {@link org.bukkit.Location}
 * associated with it.
 * <p>The single method {@link #toLocation()} which deserializes the string return by the toString() method and returns a new {@link org.bukkit.Location}
 * instance.
 *
 * @author Relicum
 * @version 0.0.1
 */
public interface Locateable {


    /**
     * Returns a new instance of a {@link org.bukkit.Location} object.
     * <p>Takes a string representing the properties need to create a new {@link org.bukkit.Location} and
     * creates and returns a new instance.
     * <p>
     * This will throw a {@link java.lang.RuntimeException }if the the toString method has not been overwritten correctly or String[] argument does not contain either 4 or 6 elements
     *
     * @return the location
     */
    default Location toLocation() {

        String[] loc = toString().split(",");

        if (loc.length == 4) {

            return new Location(Bukkit.getWorld(loc[0]), Double.valueOf(loc[1]), Double.valueOf(loc[2]), Double.valueOf(loc[3]));
        }

        if (loc.length == 6) {

            return new Location(Bukkit.getWorld(loc[0]), Double.valueOf(loc[1]), Double.valueOf(loc[2]), Double.valueOf(loc[3]), Float.valueOf(loc[4]), Float.valueOf(loc[5]));
        } else {
            throw new RuntimeException("Invaild arguments see the cause in Throwable", new Throwable("The String[] must contain either 4 or 6 elements"));
        }
    }


}
