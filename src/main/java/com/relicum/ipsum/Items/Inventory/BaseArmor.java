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
 * BaseArmor describes the base of the armor contents that can be applied to a {@link org.bukkit.entity.Player} {@link org.bukkit.inventory.PlayerInventory}
 *
 * @author Relicum
 * @version 0.0.1
 */
public interface BaseArmor {

    /**
     * Get armor {@link org.bukkit.inventory.ItemStack[]} to apply to player.
     *
     * @return the {@link org.bukkit.inventory.ItemStack[]}
     */
    public ItemStack[] getArmor();


    /**
     * Sets player armor.
     *
     * @param armor the armor stack {@link org.bukkit.inventory.ItemStack[]}
     */
    public void setArmor(ItemStack[] armor);
}
