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

/**
 * Extended point extends {@link com.relicum.ipsum.Location.Point} adding Yaw and Pitch for the point.
 */
public interface ExtendedPoint extends Point {

    /**
     * Gets yaw of the point
     *
     * @return the yaw
     */
    public float getYaw();

    /**
     * Get pitch of the point
     *
     * @return the pitch
     */
    public float getPitch();

    /**
     * Set yaw for the point
     *
     * @param yaw the yaw the point will be set to
     */
    public void setYaw(float yaw);

    /**
     * Set pitch for the point
     *
     * @param pitch the pitch the point will be set to
     */
    public void setPitch(float pitch);
}
