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

import org.apache.commons.lang.Validate;

/**
 * AbstractPoint a low memory object for holding Minecraft {@link org.bukkit.Location} .
 * <p>Just extend this class and impliment the {@link #toLocationStr()} method. This is used to create a actual
 * location object when needed.
 *
 * @author Relicum
 * @version 0.0.1
 */
public abstract class AbstractPoint implements ExtendedPoint {

    private double X;
    private double Y;
    private double Z;
    private float yaw;
    private float pitch;
    private String world;

    public AbstractPoint() {

    }

    /**
     * Instantiates a new Abstract point.
     *
     * @param world the world
     * @param x     the x
     * @param y     the y
     * @param z     the z
     * @param yaw   the yaw
     * @param pitch the pitch
     */
    protected AbstractPoint(String world, double x, double y, double z, float yaw, float pitch) {
        X = x;
        Y = y;
        Z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.world = world;
    }

    protected AbstractPoint(String world, double x, double y, double z) {
        this.world = world;
        X = x;
        Y = y;
        Z = z;
        this.pitch = 0.0f;
        this.yaw = 0.0f;
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

    /**
     * Sets X point
     *
     * @param pointX the point to set X to
     */
    @Override
    public void setX(double pointX) {
        Validate.notNull(pointX);
        this.X = pointX;
    }

    /**
     * Sets Y point
     *
     * @param pointY the point to set Y to
     */
    @Override
    public void setY(double pointY) {
        Validate.notNull(pointY);
        this.Y = pointY;
    }

    /**
     * Sets Z point
     *
     * @param pointZ the point to set Z to
     */
    @Override
    public void setZ(double pointZ) {
        Validate.notNull(pointZ);
        this.Z = pointZ;
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

    /**
     * Get world.
     *
     * @return the string name of the world
     */
    public String getWorld() {

        return this.world;
    }

    /**
     * A String representation of a Minecraft Location.
     * <p>This should return the suitable data that can be pasted to a Object to be converted to a {@link org.bukkit.Location}
     *
     * @return the string representation of a minecraft location.
     */
    public abstract String toLocationStr();

    /**
     * Gets yaw of the point
     *
     * @return the yaw
     */
    @Override
    public float getYaw() {
        return yaw;
    }

    /**
     * Get pitch of the point
     *
     * @return the pitch
     */
    @Override
    public float getPitch() {
        return pitch;
    }

    /**
     * Set yaw for the point
     *
     * @param yaw the yaw the point will be set to
     */
    @Override
    public void setYaw(float yaw) {
        Validate.notNull(yaw);
        this.yaw = yaw;
    }

    /**
     * Set pitch for the point
     *
     * @param pitch the pitch the point will be set to
     */
    @Override
    public void setPitch(float pitch) {
        Validate.notNull(pitch);
        this.pitch = pitch;
    }
}
