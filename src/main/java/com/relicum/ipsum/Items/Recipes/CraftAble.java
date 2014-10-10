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

package com.relicum.ipsum.Items.Recipes;

import org.bukkit.inventory.Recipe;

/**
 * Name: Craftable.java Created: 27 September 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public interface CraftAble {

    /**
     * Gets the type Recipe used to craft the required item.
     * <p>This will either return an instance of ShapedRecipe or ShapelessRecipe.
     *
     * @return the recipe for the item required see#{@link org.bukkit.inventory.ShapedRecipe} or see#{@link org.bukkit.inventory.ShapelessRecipe} .
     */
    <T extends Recipe> T getRecipe();

    /**
     * Returns if the recipe is a shaped or unshaped recipe.
     *
     * @return the {@link java.lang.Boolean} true and the recipe is shaped and false if it's shapeless
     */
    Boolean isShaped();
}
