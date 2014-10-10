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

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.HashMap;
import java.util.Map;

/**
 * Name: CustomRecipe.java Created: 28 September 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class CustomRecipe implements CraftAble {

    @Getter
    private ItemStack output;
    @Getter
    private String[] rows = new String[3];
    @Getter
    private Map<String, String> ingredients = null;
    @Getter
    private Boolean shaped = false;

    public CustomRecipe(ItemStack output, Map<String, String> ingredients, String[] rows) {
        this.output = output;
        this.ingredients = new HashMap<>(ingredients.size());
        this.ingredients = ingredients;
        this.rows = rows;
        this.shaped = true;
    }

    public CustomRecipe(ItemStack output, Map<String, String> ingredient) {
        this.output = output;
        this.ingredients = new HashMap<>(ingredient.size());
        this.ingredients = ingredient;


    }

    /**
     * Gets the type Recipe used to craft the required item.
     * <p>This will either return an instance of ShapedRecipe or ShapelessRecipe.
     *
     * @return the recipe for the item required see#{@link org.bukkit.inventory.ShapedRecipe} or see#{@link org.bukkit.inventory.ShapelessRecipe} .
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Recipe> T getRecipe() {

        if (shaped) {
            ShapedRecipe recipe = new ShapedRecipe(this.getOutput());
            recipe.shape(this.rows);
            for (Map.Entry<String, String> entry : ingredients.entrySet()) {
                recipe.setIngredient(entry.getKey().charAt(0), Material.valueOf(entry.getValue()));
            }

            return (T) recipe;
        }

        ShapelessRecipe recipe = new ShapelessRecipe(this.getOutput());
        for (Map.Entry<String, String> entry : ingredients.entrySet()) {
            recipe.addIngredient(Integer.valueOf(entry.getKey()), Material.valueOf(entry.getValue()));
        }


        return (T) recipe;
    }

    /**
     * Returns if the recipe is a shaped or unshaped recipe.
     *
     * @return the {@link Boolean} true and the recipe is shaped and false if it's shapeless
     */
    @Override
    public Boolean isShaped() {
        return shaped;
    }
}
