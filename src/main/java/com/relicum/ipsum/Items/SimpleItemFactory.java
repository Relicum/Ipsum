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
import org.bukkit.SkullType;

/**
 * SimpleItemFactory Single entry point to build any item or collections of items.
 * <p>Its only function is to build the require item/items returning the item stacks.
 * Other modules will be integrated to provide further function abilities.</p>
 *
 * @author Relicum
 * @version 0.0.1
 */
public class SimpleItemFactory {


    public SimpleItemFactory() {


    }


    /**
     * Gets item builder.
     *
     * @param material the material that the item is made from
     * @param amount   the amount of items in the stack
     * @param type     the {@link com.relicum.ipsum.Items.MetaType} of the item
     * @return the {@link com.relicum.ipsum.Items.ItemBuilder} used to build standard items.
     */
    public ItemBuilder getItemBuilder(Material material, int amount, MetaType type) {

        return new ItemBuilder(material, amount, type);
    }

    /**
     * Gets SkullBuilder.
     *
     * @param amount the amount of items in the stack.
     * @param type   the {@link org.bukkit.SkullType} used to determine the skull to build.
     * @return the {@link com.relicum.ipsum.Items.SkullBuilder} to build the skull.
     */
    public SkullBuilder getSkullBuilder(int amount, SkullType type) {


        return new SkullBuilder(Material.SKULL_ITEM, amount, type);

    }

    /**
     * Gets ColorArmorBuilder.
     *
     * @param material must be a instance of a leather armor type
     * @param amount   the amount of items in the stack
     * @return the {@link com.relicum.ipsum.Items.ColorArmorBuilder} to build colored armor.
     */
    public ColorArmorBuilder getColorArmorBuilder(Material material, int amount) {

        return new ColorArmorBuilder(material, amount, MetaType.COLORED_ARMOR);
    }

}
