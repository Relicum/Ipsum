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

package com.relicum.ipsum.Items;

import org.bukkit.util.Vector;

/**
 * Name: RandomLocation.java Created: 01 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class RandomLocation {

    //todo random needs to be defined by a set of rules
    //to start only have to inside a radius and not landing in water
    //find a block location 500 radius from spawn thats not water or lava
    ;
    Vector min;
    Vector max;

    public RandomLocation(int X, int Y, int Z, int X1, int Z1) {
        this.min = new Vector(X, Y, Z);
        this.max = new Vector(X1, Y, Z1);


    }


}
