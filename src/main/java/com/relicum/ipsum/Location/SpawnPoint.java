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
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.HashMap;
import java.util.Map;

/**
 * SpawnPoint is a fully working class that represents a single Minecraft {@link org.bukkit.Location} .
 * <p>You can require a different implementation look at {@link com.relicum.ipsum.Location.ExtendedPoint} or
 * {@link PointInstance} .
 *
 * @author Relicum
 * @version 0.0.1
 */
@SerializableAs("SpawnPoint")
public class SpawnPoint extends PointInstance implements ConfigurationSerializable {


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
        super();
        this.setWorld(world);
        this.setX(Math.round(x) + 0.5d);
        this.setY(Math.round(y) + 0.5d);
        this.setZ(Math.round(z) + 0.5d);
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
        super();
        this.setWorld(world);
        this.setX(Math.round(x) + 0.5d);
        this.setY(Math.round(y) + 0.5d);
        this.setZ(Math.round(z) + 0.5d);
        this.setYaw(90.0f);
        this.setPitch(1.4f);
    }

    /**
     * Instantiates a new Spawn point.
     *
     * @param location the {@link org.bukkit.Location}
     */
    public SpawnPoint(Location location) {
        super();
        this.setWorld(location.getWorld().getName());
        this.setX(Math.round(location.getX()) + 0.5d);
        this.setY(Math.round(location.getBlockY()) + 0.5d);
        this.setZ(Math.round(location.getZ()) + 0.5d);
        this.setYaw(MathUtils.getDirection(location.getYaw()));
        this.setPitch(location.getPitch());

    }


    public static SpawnPoint deserialize(Map<String, Object> map) {
        Object xObject = map.get("xpos"), yObject = map.get("ypos"), zObject = map.get("zpos"), worldObject = map.get("world"), yawObject = map.get("yawpos"), pitchObject = map.get("pitchpos"), pType = map.get("type");
        if (xObject == null || yObject == null || zObject == null || worldObject == null || !(xObject instanceof Double) || !(yObject instanceof Double)
                || !(zObject instanceof Double)) {
            return null;
        }

        Double x = (double) xObject, y = (double) yObject, z = (double) zObject;
        Double yaw = (Double) yawObject, pitch = (Double) pitchObject;
        String worldString = worldObject.toString();

        return new SpawnPoint(worldString, x, y, z, yaw.floatValue(), pitch.floatValue());

    }


    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>(6);
        map.put("world", world);
        map.put("xpos", X);
        map.put("ypos", Y);
        map.put("zpos", Z);
        map.put("yawpos", yaw);
        map.put("pitchpos", pitch);

        return map;
    }


}
