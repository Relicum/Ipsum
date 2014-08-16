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

/**
 * Name: ArmorItems.java Created: 16 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public enum ArmorItems {

    GOLD_BOOTS, GOLD_LEGGINGS, GOLD_CHESTPLATE, GOLD_HELMET, IRON_BOOTS, IRON_LEGGINGS, IRON_CHESTPLATE, IRON_HELMET, DIAMOND_BOOTS, DIAMOND_LEGGINGS, DIAMOND_CHESTPLATE, DIAMOND_HELMET, CHAINMAIL_BOOTS, CHAINMAIL_LEGGINGS, CHAINMAIL_CHESTPLATE, CHAINMAIL_HELMET, LEATHER_BOOTS, LEATHER_LEGGINGS, LEATHER_CHESTPLATE, LEATHER_HELMET;

    /**
     * Search the enum to see if a given item is on the list
     * <p>
     * Return true if it is false if it's not
     *
     * @param item the item {@link org.bukkit.Material} in it's String format
     * @return the boolean
     */
    public static boolean find(String item) {
        for (ArmorItems v : values()) {
            if (v.name().equalsIgnoreCase(item)) {
                return true;
            }
        }
        return false;
    }
}
