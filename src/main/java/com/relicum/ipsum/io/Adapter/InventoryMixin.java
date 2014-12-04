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

package com.relicum.ipsum.io.Adapter;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.PlayerInventory;

/**
 * <p>Class designed by the MassiveCraft development team, more details on them can be found at www.MassiveCraft.com.
 * <p>The copyright belongs to MassiveCraft and is licensed for public use under GPLv3.
 *
 * @author MassiveCraft
 * @version 0.0.1
 */
public interface InventoryMixin {

    // Create a Player Inventory without a Player
    public PlayerInventory createPlayerInventory();

    // Create an arbitrary size standard chest-like inventory
    public Inventory createInventory(InventoryHolder holder, int size, String title);
}
