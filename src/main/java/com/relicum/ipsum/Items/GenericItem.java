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

import org.bukkit.Material;
import org.bukkit.material.MaterialData;

import java.util.List;

/**
 * The interface that has the generic methods for setting properties that most items use.
 */
public interface GenericItem {


    /**
     * Sets material.
     *
     * @param material the material
     * @return the instance of itself so methods can be chained
     */
    public GenericItem setMaterial(Material material);

    /**
     * Sets amount. This is the number of Items the {@link org.bukkit.inventory.ItemStack} contains.
     *
     * @param i the number of items
     * @return the instance of itself so methods can be chained
     */
    public GenericItem setAmount(int i);

    /**
     * Sets durability and also data value for things like Wool.
     *
     * @param durability or data value.
     * @return the instance of itself so methods can be chained
     */
    public GenericItem setDurability(Short durability);


    /**
     * Sets ItemMetas displayName, use the <strong>&amp;</strong> to add color as per minecrafts color formatting.
     *
     * @param name the name
     * @return the instance of itself so methods can be chained
     */
    public GenericItem setDisplayName(String name);

    /**
     * Sets item lores as a list of string.
     *
     * @param list the list making up the lore.
     * @return the instance of itself so methods can be chained
     */
    public GenericItem setItemLores(List<String> list);

    /**
     * Sets a single line to item lore.
     *
     * @param line the line
     * @return the instance of itself so methods can be chained
     */
    public GenericItem setLore(String line);

    /**
     * Sets material data.
     *
     * @param materialData the material data
     * @return the instance of itself so methods can be chained
     */
    public GenericItem setMaterialData(MaterialData materialData);


    /**
     * Add enchantment.
     *
     * @param enchant the enchant
     * @return the instance of itself so methods can be chained
     */
    public GenericItem addEnchantment(Enchant enchant);


    /**
     * Add unsafe enchantment.
     *
     * @param enchant the enchant
     * @return the instance of itself so methods can be chained
     */
    public GenericItem addUnsafeEnchantment(Enchant enchant);


}
