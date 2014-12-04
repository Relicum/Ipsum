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

package com.relicum.ipsum.Items.Inventory;

import org.bukkit.inventory.ItemStack;

/**
 * BaseContents describes the base of the inventory contents that can be applied to a {@link org.bukkit.entity.Player} {@link org.bukkit.inventory.PlayerInventory}
 *
 * @author Relicum
 * @version 0.0.1
 */
public interface BaseContents {


    /**
     * Sets new inventory.
     *
     * @param contents New value of inventory.
     */
    public void setContents(ItemStack[] contents);

    /**
     * Gets inventory.
     *
     * @return Value of inventory.
     */
    public ItemStack[] getContents();

}
