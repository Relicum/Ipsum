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

import com.relicum.ipsum.Items.Inventory.MenuClickAction;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * The interface Generic menu item representsents the properties and attributes all menu items have in common.
 */
public interface GenericMenuItem {


    /**
     * Gets on click action.
     *
     * @return the on click action
     */
    public MenuClickAction getOnClickAction();

    /**
     * Gets item material.
     *
     * @return the item material
     */
    public Material getItemMaterial();

    /**
     * Gets item byte/durability, used for thing like wool or clay.
     *
     * @return the item byte
     */
    public byte getItemByte();

    /**
     * Gets item display name.
     *
     * @return the item display name
     */
    public String getItemDisplayName();

    /**
     * Gets item lore.
     *
     * @return the item lore
     */
    public List<String> getItemLore();

    /**
     * Gets item amount.
     *
     * @return the item amount
     */
    public int getItemAmount();

    /**
     * Gets permission.
     *
     * @return the permission
     */
    public String getPermission() throws UnsupportedOperationException;

    /**
     * Gets cost per use.
     *
     * @return the cost per use
     */
    public float getCostPerUse();


    /**
     * Gets item.
     *
     * @return the item
     */
    public ItemStack getItem();

}
