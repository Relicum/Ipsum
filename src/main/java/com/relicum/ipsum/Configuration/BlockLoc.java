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

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.cubespace.Yamler.Config.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * Name: BlockLoc.java Created: 30 April 2014
 * Simple BlockVector Object. Is world aware.
 * Useful for storing locations where pitch and yaw don't matter
 *
 * @author Relicum
 * @version 0.0.1
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BlockLoc extends Config {

    private int X;
    private int Y;
    private int Z;
    private String world;

    @Override
    public String toString() {


        return world + "," + X + "," + Y + "," + Z;
    }

    /**
     * Sets new X.
     *
     * @param X New value of X.
     */
    public void setX(int X) {

        this.X = X;
    }

    /**
     * Gets X.
     *
     * @return Value of X.
     */
    public int getX() {

        return X;
    }

    /**
     * Gets Z.
     *
     * @return Value of Z.
     */
    public int getZ() {

        return Z;
    }

    /**
     * Gets Y.
     *
     * @return Value of Y.
     */
    public int getY() {

        return Y;
    }

    /**
     * Sets new Y.
     *
     * @param Y New value of Y.
     */
    public void setY(int Y) {

        this.Y = Y;
    }

    /**
     * Sets new world.
     *
     * @param world New value of world.
     */
    public void setWorld(String world) {

        this.world = world;
    }

    /**
     * Sets new Z.
     *
     * @param Z New value of Z.
     */
    public void setZ(int Z) {

        this.Z = Z;
    }

    /**
     * Gets world.
     *
     * @return Value of world.
     */
    public String getWorld() {

        return world;
    }

    public Location getLocation() {
        return new Location(Bukkit.getWorld(world), X, Y, Z);
    }
}
