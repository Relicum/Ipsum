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

package com.relicum.ipsum.Cooldowns;

import org.apache.commons.lang.Validate;

/**
 * Name: Cooldown.java Created: 18 October 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class Cooldown implements RestrictAble {


    private final long end;

    /**
     * Instantiates a new {@link Cooldown} internal use only.
     *
     * @param delay the delay
     */
    private Cooldown(long delay) {
        Validate.notNull(delay);
        this.end = System.currentTimeMillis() + delay;
    }

    /**
     * Create new Cooldown passing the length of cooldown in number of Milliseconds.
     *
     * @param delay the length of cooldown in milliseconds.
     * @return new instance of {@link Cooldown} object.
     */
    public static Cooldown createCooldown(long delay) {
        return new Cooldown(delay);
    }

    /**
     * Is the current task being attempted marked as restricted in any way.
     *
     * @return true and the task currently has a restriction, false and it currently does not. The restriction is not forced so you are free to override it whenever you choose.
     */
    @Override
    public boolean isRestricted() {
        return end > System.currentTimeMillis();
    }

    /**
     * Gets a human readable message containing the relevant restriction that is currently active.
     *
     * @return the message
     */
    @Override
    public String getMessage() {
        if (isRestricted()) {

            return "Still under cooldown time left is " + ((getDelay()) / 1000l) + " seconds";
        }

        return "Cooldown over";
    }

    /**
     * Gets the remaining time in milliseconds that the cooldown has until it expires.
     *
     * @return the time remaining until the cooldown expires
     */
    @Override
    public long getDelay() {
        return end - System.currentTimeMillis();
    }

    /**
     * Get the cooldown end time in milliseconds.
     *
     * @return cooldown end
     */
    public long getEnd() {
        return end;
    }
}
