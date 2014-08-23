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

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

/**
 * Name: LocationChecker.java Created: 23 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class LocationChecker implements Listener {


    private final Vector min;
    private final Vector max;


    /**
     * Instantiates a new Location checker using Vectors already set to the min and max
     * points of the cube.
     *
     * @param min the min point
     * @param max the max point
     */
    public LocationChecker(Vector min, Vector max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Instantiates a new Location checker.But gets passed the min and max points as Locations.
     * Internal they are converted to Vectors.
     *
     * @param min the min point
     * @param max the max point
     */
    public LocationChecker(Location min, Location max) {
        this.min = min.toVector();
        this.max = max.toVector();
    }


    @EventHandler
    public void onMove(PlayerMoveEvent e) {

        if (isAABB(e.getFrom().toVector()))
            e.getPlayer().sendMessage("You are in the cube");
        else
            e.getPlayer().sendMessage("You are NOT in the cube");

    }

    /**
     * This Checks to see if the give vector is in the bounded Box.
     *
     * @param vector the vector that is been checked if its in the bounded box.
     * @return the boolean true if the vector is in the box. False if its not.
     */
    public boolean isAABB(Vector vector) {
        return vector.isInAABB(min, max);
    }

}
