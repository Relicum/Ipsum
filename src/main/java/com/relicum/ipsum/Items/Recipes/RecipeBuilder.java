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

import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Name: RecipeBuilder.java Created: 25 September 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
@SerializableAs("RecipeBuilder")
public class RecipeBuilder implements ConfigurationSerializable {

    private ItemStack output;
    private String[] rows = new String[3];
    private Map<String, ItemStack> ingredients = new HashMap<>();

    public RecipeBuilder(ItemStack result) {

        this.output = new ItemStack(result);
    }

    public RecipeBuilder() {

    }

    public RecipeBuilder addResult(ItemStack res) {
        output = new ItemStack(res);
        return this;
    }

    public RecipeBuilder addTopRow(String top) {
        this.rows[0] = top;
        return this;

    }

    public RecipeBuilder addMiddleRow(String middle) {
        this.rows[1] = middle;
        return this;

    }

    public RecipeBuilder addBottomRow(String bottom) {
        this.rows[2] = bottom;
        return this;

    }

    public RecipeBuilder addIngredient(Character c, Material m) {
        ingredients.put(String.valueOf(c), new ItemStack(m));
        return this;

    }

    public RecipeBuilder addIngredient(Character c, Material m, int a) {
        ingredients.put(String.valueOf(c), new ItemStack(m, a));
        return this;

    }

    public ShapedRecipe getRecipe() {

        ShapedRecipe recipe = new ShapedRecipe(output);
        recipe.shape(rows);
        for (Map.Entry<String, ItemStack> entry : ingredients.entrySet()) {
            recipe.setIngredient(entry.getKey().charAt(0), entry.getValue().getData());
        }

        return recipe;

    }

    public void clear() {
        rows = new String[3];
        ingredients.clear();
        ingredients = new HashMap<>();
        output = null;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>(3);
        map.put("output", output);
        map.put("shape", rows);
        map.put("ingredients", ingredients);

        return map;
    }

    @SuppressWarnings("unchecked")
    public static RecipeBuilder deserialize(Map<String, Object> map) {

        Object objOut = map.get("output"),
                objRows = map.get("shape"),
                objIng = map.get("ingredients");

        String[] r = ((ArrayList<String>) objRows).toArray(new String[3]);

        return new RecipeBuilder((ItemStack) objOut, r, (Map<String, ItemStack>) objIng);

    }

    protected RecipeBuilder(ItemStack output, String[] rows, Map<String, ItemStack> ingredients) {
        this.output = new ItemStack(output);
        this.rows = rows;
        this.ingredients = ingredients;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RecipeBuilder{");
        sb.append("output=").append(output);
        sb.append(", rows=").append(rows == null ? "null" : Arrays.asList(rows).toString());
        sb.append(", ingredients=").append(ingredients);
        sb.append(", recipe=").append(getRecipe());
        sb.append(", rialize=").append(serialize());
        sb.append('}');
        return sb.toString();
    }
}
