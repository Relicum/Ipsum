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

package com.relicum.ipsum.Items;

import lombok.experimental.Accessors;
import lombok.experimental.Builder;
import org.bukkit.enchantments.Enchantment;

/**
 * Used to build a simple enchant using a built in builder.
 */
@Accessors(fluent = true)
@Builder
public class Enchant {
    /**
     * The Enchantment.
     */
    private Enchantment enchantment;
    /**
     * The Level.
     */
    private int level;
    /**
     * The Force.
     */
    private boolean force;

    public static Enchant get(Enchantment enchantment, int level, boolean force) {
        return new Enchant(enchantment, level, force);
    }

    /**
     * Enchantment enchantment.
     *
     * @return the enchantment
     */
    public Enchantment enchantment() {
        return this.enchantment;
    }

    /**
     * The level for the enchant
     *
     * @return the int
     */
    public int level() {
        return this.level;
    }

    /**
     * Check if unsafe enchants is active
     *
     * @return the if this enchant is set to force unsafe enchants
     */
    public boolean force() {
        return this.force;
    }

    private Enchant(Enchantment enchantment, int level, boolean force) {
        this.enchantment = enchantment;
        this.level = level;
        this.force = force;
    }

    /**
     * Enchantment enchant.
     *
     * @param enchantment the {@link org.bukkit.enchantments.Enchantment} for list of enchants
     * @return the instance of itself so methods can be chained
     */
    public Enchant enchantment(Enchantment enchantment) {
        this.enchantment = enchantment;
        return this;
    }

    /**
     * Level to apply to enchant.
     *
     * @param level the level
     * @return the instance of itself so methods can be chained
     */
    public Enchant level(int level) {
        this.level = level;
        return this;
    }

    /**
     * Force unsafe enchants.
     *
     * @param force the force
     * @return the instance of itself so methods can be chained
     */
    public Enchant force(boolean force) {
        this.force = force;
        return this;
    }
}
