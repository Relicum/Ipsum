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

import com.relicum.ipsum.Utils.MathUtils;

import java.lang.reflect.Type;

/**
 * SpawnPoint is a fully working class that represents a single Minecraft {@link org.bukkit.Location} .
 * <p>You can require a different implementation look at {@link com.relicum.ipsum.Location.ExtendedPoint} or
 * {@link PointInstance} .
 *
 * @author Relicum
 * @version 0.0.1
 */
public class SpawnPoint extends PointInstance {


    /**
     * Instantiates a new SpawnPoint.
     */
    public SpawnPoint() {


    }


    /**
     * Instantiates a new SpawnPoint
     *
     * @param world the world
     * @param x     the x
     * @param y     the y
     * @param z     the z
     * @param yaw   the yaw
     * @param pitch the pitch
     */
    public SpawnPoint(String world, double x, double y, double z, float yaw, float pitch) {
        this.setWorld(world);
        this.setX(MathUtils.round(x) + 0.5d);
        this.setY(y);
        this.setZ(MathUtils.round(z) + 0.5d);
        this.setYaw(MathUtils.getDirection(yaw));
        this.setPitch(pitch);
    }

    /**
     * Instantiates a new BlockLocation
     *
     * @param world the world
     * @param x     the x
     * @param y     the y
     * @param z     the z
     */
    public SpawnPoint(String world, double x, double y, double z) {
        this.setWorld(world);
        this.setX(MathUtils.round(x));
        this.setY(MathUtils.round(y));
        this.setZ(MathUtils.round(z));
        this.setYaw(0.0f);
        this.setPitch(0.0f);
    }

    /**
     * Get type.
     *
     * @return the type
     */
    public Type getType() {

        return this.getClass();
    }
}
