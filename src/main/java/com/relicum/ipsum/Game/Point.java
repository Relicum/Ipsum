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

/**
 * Point simple interface to define an object that has 3 points to it and the world that it is located in.
 */
public interface Point {

    /**
     * Gets X point
     *
     * @return the X point
     */
    public double getX();

    /**
     * Gets Y point
     *
     * @return the Y point
     */
    public double getY();

    /**
     * Gets Z point
     *
     * @return the Z point
     */
    public double getZ();

    /**
     * Sets X point
     *
     * @param pointX the point to set X to
     */
    public void setX(double pointX);

    /**
     * Sets Y point
     *
     * @param pointY the point to set Y to
     */
    public void setY(double pointY);

    /**
     * Sets Z point
     *
     * @param pointZ the point to set Z to
     */
    public void setZ(double pointZ);

    /**
     * Set world string name
     *
     * @param world the world the points are in
     */
    public void setWorld(String world);


}
