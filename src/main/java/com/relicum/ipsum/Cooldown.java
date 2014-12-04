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

package com.relicum.ipsum;

/**
 * The type Cooldown.
 *
 * @param <T> the type parameter
 */
public abstract class Cooldown<T extends Cooldown<T>> {

    /**
     * The Cooldown type.
     */
    public Class<T> cooldownType;

    /**
     * Gets cool status.
     *
     * @param delay the delay
     * @return the cool status
     */
    abstract boolean getCoolStatus(Long delay);

    /**
     * Add delay.
     *
     * @param delay the delay
     */
    abstract void addDelay(Long delay);

    /**
     * Gets cooldown type.
     *
     * @return the cooldown type
     */
    public Class<T> getCooldownType() {
        return cooldownType;
    }


    /**
     * Sets cooldown type.
     *
     * @param type the type
     */
    public void setCooldownType(Class<T> type) {
        cooldownType = type;
    }
}
