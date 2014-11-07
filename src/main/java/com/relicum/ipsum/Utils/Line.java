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

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.util.Iterator;

/**
 * This class allows you to iterate over the blocks that are between two locations.
 *
 * @author Relicum
 * @version 0.0.1
 */
public final class Line implements Iterable<Block> {
    private final String worldName;
    private final Vector start;
    private final Vector end;
    private final Vector direction;
    private final int distance;
    private final double offsetY;

    public Line(Location start, Location end, double offsetY) {
        if (!start.getWorld().getName().equals(end.getWorld().getName()))
            throw new IllegalArgumentException("Cannot create a block line between two different worlds");
        this.worldName = start.getWorld().getName();
        this.start = start.toVector();
        this.end = end.toVector();
        this.direction = this.end.subtract(this.start);
        this.distance = (int) start.distance(end);
        this.offsetY = offsetY;
    }

    public Line(Location start, Location end) {
        this(start, end, 0);
    }

    public Line(LivingEntity start, Location end) {
        this(start.getLocation(), end, start.getEyeHeight());
    }

    public String getWorldName() {
        return this.worldName;
    }

    public World getWorld() throws IllegalStateException {
        World w = Bukkit.getWorld(worldName);
        if (w == null)
            throw new IllegalStateException("World '" + worldName + "' is not loaded");
        return w;
    }

    public Vector getStart() {
        return this.start;
    }

    public Vector getEnd() {
        return this.end;
    }

    public Vector getDirection() {
        return this.direction;
    }

    public int getDistance() {
        return this.distance;
    }

    public double getOffsetY() {
        return this.offsetY;
    }

    @Override
    public Iterator<Block> iterator() {
        return new BlockIterator(getWorld(), start, direction, offsetY, distance);
    }
}
