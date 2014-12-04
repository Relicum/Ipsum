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

import com.relicum.ipsum.Location.BlockPoint;
import net.minecraft.util.com.google.gson.Gson;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Region holds the 2 {@link org.bukkit.util.BlockVector} that define the min and max points of a region.
 *
 * @author Relicum
 * @version 0.0.1
 */
@SerializableAs("Region")
public class Region implements ConfigurationSerializable {

    private transient BlockVector minBlock;
    private transient BlockVector maxBlock;
    private BlockPoint minPoint;
    private BlockPoint maxPoint;
    private String world;

    public Region() {
    }


    /**
     * Instantiates a new Region data.
     *
     * @param min   the vector to use for the min point.
     * @param max   the vector to use for the max point.
     * @param world the world name as a string the 2 points are in.
     */
    public Region(BlockVector min, BlockVector max, String world) {
        this.minPoint = new BlockPoint(world, min.getBlockX(), min.getBlockY(), min.getBlockZ());
        this.maxPoint = new BlockPoint(world, max.getBlockX(), max.getBlockY(), max.getBlockZ());
        this.world = world;
    }

    /**
     * Returns the object as a Json String.
     *
     * @param gson the gson object to use to convert it to a Json string.
     * @return the Json String
     */
    public String toJSON(Gson gson) {

        return gson.toJson(this, Region.class);
    }


    /**
     * Check to see if a given location is in this region.
     *
     * @param vector the location to check
     * @return true if the location is in the region, false if not.
     */
    public boolean isRegion(Vector vector) {
        if (minBlock == null)
            minBlock = new BlockVector(minPoint.toBlockVector());
        if (maxBlock == null)
            maxBlock = new BlockVector(maxPoint.toBlockVector());

        return vector.isInAABB(minBlock, maxBlock);
    }

    /**
     * Get min point as a {@link org.bukkit.util.BlockVector}
     *
     * @return the block vector
     */
    public BlockVector getMin() {
        if (minBlock == null)
            minBlock = minPoint.toBlockVector();
        return minBlock;
    }

    /**
     * Get max point as a {@link org.bukkit.util.BlockVector}
     *
     * @return the block vector of the max point
     */
    public BlockVector getMax() {
        if (maxBlock == null)
            maxBlock = maxPoint.toBlockVector();
        return maxBlock;
    }

    /**
     * Sets max point.
     *
     * @param maxBlock {@link org.bukkit.util.BlockVector} of max point.
     */
    public void setMaxBlock(BlockVector maxBlock) {
        this.maxBlock = maxBlock;
    }

    /**
     * Sets min point.
     *
     * @param minBlock {@link org.bukkit.util.BlockVector} of min point.
     */
    public void setMinBlock(BlockVector minBlock) {
        this.minBlock = minBlock;
    }

    /**
     * Gets world.
     *
     * @return Value of world.
     */
    public String getWorld() {
        return world;
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
     * Instantiates a new Region data used to deserialize the object.
     *
     * @param map the map
     * @return new instance of {@link com.relicum.ipsum.Effect.Game.Region}
     */
    public static Region deserialize(Map<String, Object> map) {

        return new Region((BlockVector) map.get("minBlock"), (BlockVector) map.get("maxBlock"), (String) map.get("world"));

    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new LinkedHashMap<>(3);
        map.put("minBlock", minPoint.toBlockVector());
        map.put("maxBlock", maxPoint.toBlockVector());
        map.put("world", world);

        return map;
    }

}
