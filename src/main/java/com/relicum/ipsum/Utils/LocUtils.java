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

import com.relicum.ipsum.Configuration.BlockLoc;
import com.relicum.ipsum.Configuration.Loc;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * LocUtils contains a selection of static methods for converting {@link com.relicum.ipsum.Configuration.Loc} and {@link com.relicum.ipsum.Configuration.BlockLoc} to various types.
 * <p>This class also contains various other methods for calculating collections of locations. Or collections of {@link org.bukkit.entity.Player} or {@link org.bukkit.entity.Entity}
 * from a given location.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class LocUtils {


    /**
     * Used to stop the class from being instantiated
     */
    private LocUtils() {

    }

    /**
     * Convert a Bukkit Location to a Loc object.
     *
     * @param l the {@link org.bukkit.Location}
     * @return the {@link com.relicum.ipsum.Configuration.Loc} object serialised to a Yamler format
     */
    public static Loc locationToLoc(Location l) {

        return new Loc(l.getBlockX(), l.getBlockY(), l.getBlockZ(), MathUtils.getDirection(l.getYaw()), MathUtils.floor(l.getPitch()), l.getWorld().getName());
    }

    public static Location blockLocToLocation(BlockLoc blockLoc) {

        Validate.notNull(blockLoc);
        return new Location(Bukkit.getWorld(blockLoc.getWorld()), blockLoc.getX() + 0.5, blockLoc.getY() + 0.5, blockLoc.getZ() + 0.5);
    }

    /**
     * Convert a Bukkit Location to blockLoc object
     *
     * @param l the {@link org.bukkit.Location}
     * @return the {@link com.relicum.ipsum.Configuration.BlockLoc} object serialised to a yamler format
     */
    public static BlockLoc locationToBlockLoc(Location l) {

        return new BlockLoc(l.getBlockX(), l.getBlockY(), l.getBlockZ(), l.getWorld().getName());
    }

    /**
     * Vector to block loc.
     *
     * @param v     the {@link org.bukkit.util.Vector} to convert
     * @param world the bukkit world in a {@link java.lang.String} format
     * @return the {@link com.relicum.ipsum.Configuration.BlockLoc} object serialised to a yamler format
     */
    public static BlockLoc vectorToBlockLoc(Vector v, String world) {

        return new BlockLoc(v.getBlockX(), v.getBlockY(), v.getBlockZ(), world);
    }

    /**
     * Will return the blocks that are in the "radius" position from centerLoc
     *
     * @param centerLoc Central Location
     * @param radius    Distance in blocks from the "centerLoc"
     * @return Circle
     */
    public static List<Location> getCircle(Location centerLoc, int radius) {

        List<Location> circle = new ArrayList<>();
        World world = centerLoc.getWorld();
        int x = 0;
        int z = radius;
        int error = 0;
        int d = 2 - 2 * radius;
        while (z >= 0) {
            circle.add(new Location(world, centerLoc.getBlockX() + x, centerLoc.getY(), centerLoc.getBlockZ() + z));
            circle.add(new Location(world, centerLoc.getBlockX() - x, centerLoc.getY(), centerLoc.getBlockZ() + z));
            circle.add(new Location(world, centerLoc.getBlockX() - x, centerLoc.getY(), centerLoc.getBlockZ() - z));
            circle.add(new Location(world, centerLoc.getBlockX() + x, centerLoc.getY(), centerLoc.getBlockZ() - z));
            error = 2 * (d + z) - 1;
            if ((d < 0) && (error <= 0)) {
                x++;
                d += 2 * x + 1;
            } else {
                error = 2 * (d - x) - 1;
                if ((d > 0) && (error > 0)) {
                    z--;
                    d += 1 - 2 * z;
                } else {
                    x++;
                    d += 2 * (x - z);
                    z--;
                }
            }
        }
        return circle;
    }

    /**
     * Will get all blocks between position1 and position2
     * credits CaptainBern
     *
     * @param position1 First position
     * @param position2 Second position
     * @return Cuboid
     */
    public static List<Location> getCuboid(Location position1, Location position2) {

        if (position1.getWorld().getName() != position2.getWorld().getName()) {
            throw new UnsupportedOperationException("'Position1' and 'Position2' location need to be in the same world!");
        }

        List<Location> cube = new ArrayList<Location>();

        int minX = (int) Math.min(position1.getX(), position2.getX());
        int maxX = (int) Math.max(position1.getX(), position2.getX());

        int minY = (int) Math.min(position1.getY(), position2.getY());
        int maxY = (int) Math.max(position1.getY(), position2.getY());

        int minZ = (int) Math.min(position1.getZ(), position2.getZ());
        int maxZ = (int) Math.max(position1.getZ(), position2.getZ());

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    cube.add(new Location(position1.getWorld(), x, y, z));
                }
            }
        }
        return cube;
    }

    /**
     * It will return the blocks that are in the same level as the "position1" and till the "position2".
     *
     * @param position1 First position
     * @param position2 Second position
     * @return Plain Cuboid
     */
    public static List<Location> getPlain(Location position1, Location position2) {

        List<Location> plain = new ArrayList<Location>();
        if (position1 == null) return plain;
        if (position2 == null) return plain;
        for (int x = Math.min(position1.getBlockX(), position2.getBlockX()); x <= Math.max(position1.getBlockX(), position2.getBlockX()); x++) {
            for (int z = Math.min(position1.getBlockZ(), position2.getBlockZ()); z <= Math.max(position1.getBlockZ(), position2.getBlockZ()); z++) {
                plain.add(new Location(position1.getWorld(), x, position1.getBlockY(), z));
            }
        }
        return plain;
    }

    /**
     * If "land" is activated, it will return air blocks only one block above the ground;
     * If "land" is deactivated, it will return only the air blocks in the cuboid.
     *
     * @param position1          First position
     * @param position2          Second position
     * @param getOnlyAboveGround boolean (see the notes);
     * @return Cuboid
     */
    public static List<Location> getBlocks(Location position1, Location position2, boolean getOnlyAboveGround) {

        List<Location> blocks = new ArrayList<Location>();
        if (position1 == null) return blocks;
        if (position2 == null) return blocks;

        for (int x = Math.min(position1.getBlockX(), position2.getBlockX()); x <= Math.max(position1.getBlockX(), position2.getBlockX()); x++) {
            for (int z = Math.min(position1.getBlockZ(), position2.getBlockZ()); z <= Math.max(position1.getBlockZ(), position2.getBlockZ()); z++) {
                for (int y = Math.min(position1.getBlockY(), position2.getBlockY()); y <= Math.max(position1.getBlockY(), position2.getBlockY()); y++) {
                    Block b = position1.getWorld().getBlockAt(x, y, z);
                    if ((b.getType() == Material.AIR) && (
                            (!getOnlyAboveGround) || (b.getRelative(BlockFace.DOWN).getType() != Material.AIR))) {
                        blocks.add(b.getLocation());
                    }
                }
            }
        }
        return blocks;
    }

    /**
     * Will return  the blocks that are in diagonal from position1 till position2.
     * (Between in a line)
     *
     * @param position1 First position
     * @param position2 Second position
     * @return Line
     */
    public static List<Location> getLine(Location position1, Location position2) {

        List<Location> line = new ArrayList<Location>();
        int dx = Math.max(position1.getBlockX(), position2.getBlockX()) - Math.min(position1.getBlockX(), position2.getBlockX());
        int dy = Math.max(position1.getBlockY(), position2.getBlockY()) - Math.min(position1.getBlockY(), position2.getBlockY());
        int dz = Math.max(position1.getBlockZ(), position2.getBlockZ()) - Math.min(position1.getBlockZ(), position2.getBlockZ());
        int x1 = position1.getBlockX();
        int x2 = position2.getBlockX();
        int y1 = position1.getBlockY();
        int y2 = position2.getBlockY();
        int z1 = position1.getBlockZ();
        int z2 = position2.getBlockZ();
        int x = 0;
        int y = 0;
        int z = 0;
        int i = 0;
        int d = 1;
        switch (getHighest(dx, dy, dz)) {
            case 1:
                i = 0;
                d = 1;
                if (x1 > x2) d = -1;
                x = position1.getBlockX();
                do {
                    i++;
                    y = y1 + (x - x1) * (y2 - y1) / (x2 - x1);
                    z = z1 + (x - x1) * (z2 - z1) / (x2 - x1);
                    line.add(new Location(position1.getWorld(), x, y, z));
                    x += d;
                }
                while (
                        i <= Math.max(x1, x2) - Math.min(x1, x2));
                break;
            case 2:
                i = 0;
                d = 1;
                if (y1 > y2) d = -1;
                y = position1.getBlockY();
                do {
                    i++;
                    x = x1 + (y - y1) * (x2 - x1) / (y2 - y1);
                    z = z1 + (y - y1) * (z2 - z1) / (y2 - y1);
                    line.add(new Location(position1.getWorld(), x, y, z));
                    y += d;
                }
                while (
                        i <= Math.max(y1, y2) - Math.min(y1, y2));
                break;
            case 3:
                i = 0;
                d = 1;
                if (z1 > z2) d = -1;
                z = position1.getBlockZ();
                do {
                    i++;
                    y = y1 + (z - z1) * (y2 - y1) / (z2 - z1);
                    x = x1 + (z - z1) * (x2 - x1) / (z2 - z1);
                    line.add(new Location(position1.getWorld(), x, y, z));
                    z += d;
                }
                while (
                        i <= Math.max(z1, z2) - Math.min(z1, z2));
        }

        return line;

    }

    /**
     * Support for getLine
     *
     * @param x x
     * @param y y
     * @param z z
     * @return highest
     */
    private static int getHighest(int x, int y, int z) {

        if ((x >= y) && (x >= z)) return 1;
        if ((y >= x) && (y >= z)) return 2;
        return 3;
    }

    /**
     * It will return players in a radius, from location.
     *
     * @param location Initial location
     * @param radius   distance from the "location" that will return all the entities from each block;
     * @return HashSet(Player)
     * credits skore87
     */
    public static HashSet<Player> getNearbyEntities(Location location, int radius) {

        int chunkRadius = radius < 16 ? 1 : (radius - (radius % 16)) / 16;
        HashSet<Player> radiusEntities = new HashSet<>();

        for (int chX = 0 - chunkRadius; chX <= chunkRadius; chX++) {
            for (int chZ = 0 - chunkRadius; chZ <= chunkRadius; chZ++) {
                int x = (int) location.getX(), y = (int) location.getY(), z = (int) location.getZ();
                for (Entity e : new Location(location.getWorld(), x + (chX * 16), y, z + (chZ * 16)).getChunk().getEntities()) {
                    if (e.getLocation().distance(location) <= radius && e.getLocation().getBlock() != location.getBlock()) {
                        if (e instanceof LivingEntity) {
                            if (e instanceof Player) {
                                radiusEntities.add((Player) e);
                            }

                        }
                    }
                }
            }
        }
        return radiusEntities;
    }
}
