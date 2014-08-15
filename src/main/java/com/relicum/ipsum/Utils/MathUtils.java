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

/**
 * MathUtils contains various useful Math functions that get used regually.
 *
 * @author Relicum
 * @version 0.0.1
 */
public final class MathUtils {

    /**
     * Block coordinate for double, especially important for negative numbers.
     * (Adapted From Bukkit/NumberConversions.)
     * <p>
     * Thanks to asofold for this method.
     *
     * @param x {@link java.lang.Double}
     * @return the Block coordinate for double
     */
    public static int floor(final double x) {

        final int floor = (int) x;
        return (floor == x) ? floor : floor - (int) (Double.doubleToRawLongBits(x) >>> 63);
    }

    /**
     * Returns the direction you are looking
     *
     * @param yaw float
     * @return float
     */
    public static float getDirection(Float yaw) {

        yaw = yaw / 90;
        yaw = (float) Math.round(yaw);

        if (yaw == -4 || yaw == 0 || yaw == 4) {
            return (0.00F);
        }
        if (yaw == -1 || yaw == 3) {
            return -90.00F;
        }
        if (yaw == -2 || yaw == 2) {
            return -179.00F;
        }
        if (yaw == -3 || yaw == 1) {
            return 90.00F;
        }
        return 5.00F;
    }

    /**
     * Between range, checks if an int falls between to other int's.
     *
     * @param test the test
     * @param min  the min
     * @param max  the max
     * @return the boolean
     */
    public static boolean betweenRange(int test, int min, int max) {
        return test >= min && test <= max;
    }

}
