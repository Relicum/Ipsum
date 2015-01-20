/*
 * Ipsum is a rapid development API for Minecraft, developer by Relicum
 * Copyright (C) 2015.  Chris Lutte
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

package com.relicum.ipsum.Menus;

import org.bukkit.inventory.Inventory;

/**
 * The interface Generic menu is the base for all custom Menus.
 */
public interface GenericMenu {

    /**
     * Gets size, the total number of slots the Inventory can have.
     *
     * @return number of slots.
     */
    public int getSize();

    /**
     * Sets number of slots in inventory. Multiples of 9 only.
     *
     * @param size the number of Slots
     */
    public void setSize(int size);

    /**
     * Gets menu title.
     *
     * @return the menu title
     */
    public String getMenuTitle();

    /**
     * Sets menu title. Max 16 Characters.
     *
     * @param menuTitle the menu title
     */
    public void setMenuTitle(String menuTitle);

    /**
     * Gets a new instance of the inventory.
     *
     * @return the inventory, depending on implementation this may or may not of
     *         had the items added to it
     */
    public Inventory getInventory();

    /**
     * Gets unique name, human readable identifying name for the menu.
     *
     * @return the unique name
     */
    public String getUniqueName();

    /**
     * Sets unique name, human readable identifying name for the menu.
     *
     * @param name the unique name max chars 16 NO color
     */
    public void setUniqueName(String name);

}
