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
import org.apache.commons.lang.Validate;
import org.bukkit.block.Block;
import org.bukkit.util.BlockVector;

/**
 * BlockPoint stores an instance of a minecraft block location.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class BlockPoint implements Point, Locateable {

    private double X;
    private double Y;
    private double Z;
    private String world;
    private transient BlockVector blockVector;


    /**
     * Instantiates a new Block location.
     *
     * @param world the world
     * @param x     the x
     * @param y     the y
     * @param z     the z
     */
    public BlockPoint(String world, double x, double y, double z) {
        setX(x);
        setY(y);
        setZ(z);
        this.world = world;
    }

    /**
     * Instantiates a new Block location.
     */
    public BlockPoint() {


    }

    /**
     * Instantiates a new Block point.
     *
     * @param block the block
     */
    public BlockPoint(Block block) {

        this.world = block.getWorld().getName();
        X = block.getX();
        Y = block.getY();
        Z = block.getZ();

    }

    public BlockVector toBlockVector() {
        if (blockVector == null)
            blockVector = new BlockVector(X, Y, Z);
        return blockVector;
    }

    /**
     * Gets X point
     *
     * @return the X point
     */
    @Override
    public double getX() {
        return X;
    }

    /**
     * Gets Y point
     *
     * @return the Y point
     */
    @Override
    public double getY() {
        return Y;
    }

    /**
     * Gets Z point
     *
     * @return the Z point
     */
    @Override
    public double getZ() {
        return Z;
    }

    public String getWorld() {
        return world;
    }

    /**
     * Sets X point
     *
     * @param pointX the point to set X to
     */
    @Override
    public void setX(double pointX) {
        Validate.notNull(pointX);
        X = MathUtils.ceil(pointX);
    }

    /**
     * Sets Y point
     *
     * @param pointY the point to set Y to
     */
    @Override
    public void setY(double pointY) {
        Validate.notNull(pointY);
        Y = MathUtils.ceil(pointY);
    }

    /**
     * Sets Z point
     *
     * @param pointZ the point to set Z to
     */
    @Override
    public void setZ(double pointZ) {

        Validate.notNull(pointZ);
        Z = MathUtils.ceil(pointZ);
    }

    /**
     * Set world string name
     *
     * @param world the world the points are in
     */
    @Override
    public void setWorld(String world) {
        Validate.notNull(world);
        this.world = world;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockPoint)) return false;

        BlockPoint that = (BlockPoint) o;

        if (Double.compare(that.X, X) != 0) return false;
        if (Double.compare(that.Y, Y) != 0) return false;
        if (Double.compare(that.Z, Z) != 0) return false;
        if (!world.equals(that.world)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(X);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(Y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(Z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + world.hashCode();
        return result;
    }

    @Override
    public String toString() {

        return getWorld() + "," + getX() + "," + getY() + "," + getZ();
    }
}
