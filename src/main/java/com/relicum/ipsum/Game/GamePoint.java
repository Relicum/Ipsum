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

package com.relicum.ipsum.Game;

import lombok.EqualsAndHashCode;

/**
 * GamePoint is a fully working class that represents a single Minecraft {@link org.bukkit.Location} .
 * <p>You can require a different implementation look at {@link com.relicum.ipsum.Game.ExtendedPoint} or
 * {@link com.relicum.ipsum.Game.AbstractPoint} .
 *
 * @author Relicum
 * @version 0.0.1
 */
@EqualsAndHashCode
public class GamePoint extends AbstractPoint {


    /**
     * Instantiates a new Game point.
     */
    public GamePoint() {
    }


    /**
     * Instantiates a new Game point.
     *
     * @param world the world
     * @param x     the x
     * @param y     the y
     * @param z     the z
     * @param yaw   the yaw
     * @param pitch the pitch
     */
    public GamePoint(String world, double x, double y, double z, float yaw, float pitch) {
        super(world, x, y, z, yaw, pitch);
    }

    /**
     * Instantiates a new Game point.
     *
     * @param world the world
     * @param x     the x
     * @param y     the y
     * @param z     the z
     */
    public GamePoint(String world, double x, double y, double z) {
        super(world, x, y, z);
    }

    /**
     * A String representation of a Minecraft Location.
     * <p>This should return the suitable data that can be pasted to a Object to be converted to a {@link org.bukkit.Location}
     *
     * @return the string representation of a minecraft location.
     */
    @Override
    public String toLocationStr() {
        return getWorld() + "," + getX() + "," + getY() + "," + getZ() + "," + getYaw() + "," + getPitch();
    }
}
