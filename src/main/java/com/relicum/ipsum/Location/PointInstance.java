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

import com.relicum.ipsum.Configuration.AbstractSerializable;
import org.apache.commons.lang.Validate;

/**
 * PointInstance a low memory object for holding Minecraft {@link org.bukkit.Location} .
 * <p>Just extend this class. This is used to create an actual location object when needed as
 * defined in {@link com.relicum.ipsum.Location.Locateable} to alter the String[] that is pasted to the method
 * override the method {@link #toString()}
 *
 * @author Relicum
 * @version 0.0.1
 */
public abstract class PointInstance extends AbstractSerializable implements ExtendedPoint, Locateable {

    protected Double X;
    protected Double Y;
    protected Double Z;
    protected Float yaw;
    protected Float pitch;
    protected String world;

    /**
     * Gets X point
     *
     * @return the X point
     */
    @Override
    public double getX() {
        return this.X;
    }

    /**
     * Gets Y point
     *
     * @return the Y point
     */
    @Override
    public double getY() {
        return this.Y;
    }

    /**
     * Gets Z point
     *
     * @return the Z point
     */
    @Override
    public double getZ() {
        return this.Z;
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
     * Gets yaw of the point
     *
     * @return the yaw
     */
    @Override
    public float getYaw() {
        return this.yaw;
    }

    /**
     * Get pitch of the point
     *
     * @return the pitch
     */
    @Override
    public float getPitch() {
        return this.pitch;
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


    @Override
    public String toString() {

        return getWorld() + "," + getX() + "," + getY() + "," + getZ() + "," + getYaw() + "," + getPitch();
    }
}
