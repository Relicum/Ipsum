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
import org.bukkit.inventory.ShapelessRecipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * RecipeBuilder is used to create custom recipes with a builder it also is serializable to YML.
 * <p>Use {@link com.relicum.ipsum.Items.SimpleItemFactory} to make it even quicker to build the recipe result.
 * The entire object can be saved and load from a yml file as it implements {@link org.bukkit.configuration.serialization.ConfigurationSerializable} .
 * <p>The builder make {@link org.bukkit.inventory.ShapedRecipe} and {@link org.bukkit.inventory.ShapelessRecipe}
 *
 * @author Relicum
 * @version 0.0.1
 */
@SerializableAs("RecipeBuilder")
public class RecipeBuilder implements ConfigurationSerializable {

    private ItemStack output;
    private String[] rows = null;
    private Map<String, String> ingredients = new HashMap<>();

    /**
     * Instantiates a new RecipeBuilder to create a shaped recipe.
     *
     * @param result the {@link org.bukkit.inventory.ItemStack} that will be produce after crafting.
     */
    public RecipeBuilder(ItemStack result) {
        this.rows = new String[3];
        this.output = new ItemStack(result);
    }

    /**
     * Instantiates a new RecipeBuilder to create either a shaped or a shapeless recipe.
     *
     * @param result    the result
     * @param shapeLess set to true if the recipe is shapeless or false for a shaped recipe.
     */
    public RecipeBuilder(ItemStack result, Boolean shapeLess) {

        if (!shapeLess) {
            this.rows = new String[3];
        }

        this.output = new ItemStack(result);
    }

    /**
     * Instantiates a new RecipeBuilder.
     */
    public RecipeBuilder() {

    }

    /**
     * Add the {@link org.bukkit.inventory.ItemStack} that will be produce after crafting.
     * <p>You only need to use this if you didn't pass it to the constructor.
     *
     * @param res the {@link org.bukkit.inventory.ItemStack} that will be produce after crafting.
     * @return an instance of {@link com.relicum.ipsum.Items.Recipes.RecipeBuilder} to allow the chaining of methods.
     */
    public RecipeBuilder addResult(ItemStack res) {
        output = new ItemStack(res);
        return this;
    }

    /**
     * Add top row of the crafting grid.
     * <p>This MUST be a string of length of 3, adding any Characters you want to use as part of the recipe.
     * See {@link org.bukkit.inventory.ShapedRecipe} for more details
     * <code>
     * String top = " T ";
     * </code>
     * The example above would make the top row, with an item in the middle with nothing either side.
     *
     * @param top the top row of the shape
     * @return an instance of {@link com.relicum.ipsum.Items.Recipes.RecipeBuilder} to allow the chaining of methods.
     */
    public RecipeBuilder addTopRow(String top) {
        this.rows[0] = top;
        return this;

    }

    /**
     * Add middle row of the crafting grid.
     * <p>This MUST be a string of length of 3,adding any Characters you want to use as part of the recipe.
     * See {@link org.bukkit.inventory.ShapedRecipe} for more details
     * <code>
     * String middle = "S S";
     * </code>
     * The example above would make the middle row, with an item either side and nothing in the middle.
     *
     * @param middle the middle row of the shape.
     * @return an instance of {@link com.relicum.ipsum.Items.Recipes.RecipeBuilder} to allow the chaining of methods.
     */
    public RecipeBuilder addMiddleRow(String middle) {
        this.rows[1] = middle;
        return this;

    }

    /**
     * Add bottom row of the crafting grid.
     * <p>This MUST be a string of length of 3,adding any Characters you want to use as part of the recipe.
     * See {@link org.bukkit.inventory.ShapedRecipe} for more details
     * <code>
     * String bottom = "STS";
     * </code>
     * The example above would make the bottom row, with an item either side and a different item in the middle.
     *
     * @param bottom the bottom row of the shape.
     * @return an instance of {@link com.relicum.ipsum.Items.Recipes.RecipeBuilder} to allow the chaining of methods.
     */
    public RecipeBuilder addBottomRow(String bottom) {
        this.rows[2] = bottom;
        return this;

    }

    /**
     * Add an ingredient to match the characters used in the rows, Each character used in the row must be included here.
     * <p>If you have used a character more than once you DO NOT need to add it more than once here. r the blanks in the rows
     * you do not need to include anything, do not add air blocks for blanks or the player will need air blocks to craft it.
     *
     * @param c the {@link java.lang.Character} that matches a character used in the rows.
     * @param m the {@link org.bukkit.Material} that it represents, which the player will use for crafting.
     * @return an instance of {@link com.relicum.ipsum.Items.Recipes.RecipeBuilder} to allow the chaining of methods.
     */
    public RecipeBuilder addIngredient(Character c, Material m) {
        ingredients.put(String.valueOf(c), m.name());
        return this;

    }


    /**
     * Gets the {@link org.bukkit.inventory.ShapedRecipe} itself.
     * <p>Only call this when you are sure you have added everything. The result of this method can be used to add the custom recipe
     * to the server. {@link org.bukkit.Server#addRecipe(org.bukkit.inventory.Recipe)} for details.
     *
     * @return the instance of {@link org.bukkit.inventory.ShapedRecipe}
     */
    public ShapedRecipe getRecipe() {


        ShapedRecipe recipe = new ShapedRecipe(output);
        recipe.shape(rows);

        for (Map.Entry<String, String> entry : ingredients.entrySet()) {
            recipe.setIngredient(entry.getKey().charAt(0), Material.valueOf(entry.getValue()));
        }

        return recipe;

    }

    public ShapelessRecipe getShapeLessRecipe() {

        ShapelessRecipe recipe = new ShapelessRecipe(output);

        for (Map.Entry<String, String> entry : ingredients.entrySet()) {
            recipe.addIngredient(Integer.valueOf(entry.getKey()), Material.valueOf(entry.getValue()));
        }

        return recipe;

    }

    public CustomRecipe build() {
        if (rows != null)
            return new CustomRecipe(output, ingredients, rows);

        return new CustomRecipe(output, ingredients);
    }

    public CustomRecipe buildShapeLess() {
        return null;
    }

    /**
     * Clears all values from the object.
     */
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
        if (rows != null) {
            map.put("shape", rows);
        }
        map.put("ingredients", ingredients);

        return map;
    }

    @SuppressWarnings("unchecked")
    public static RecipeBuilder deserialize(Map<String, Object> map) {
        Object objRows = null;
        Object objOut = map.get("output");
        if (map.containsKey("shape")) {
            objRows = map.get("shape");
        }
        Object objIng = map.get("ingredients");
        if (objOut == null || objIng == null) {
            throw new NullPointerException("Unable to deserialize RecipeBuilder due to some of the values being null");

        }

        if (objRows != null) {

            String[] r = ((ArrayList<String>) objRows).toArray(new String[3]);

            return new RecipeBuilder((ItemStack) objOut, r, (Map<String, String>) objIng);
        } else
            return new RecipeBuilder((ItemStack) objOut, (Map<String, String>) objIng);
    }


    /**
     * Instantiates a new RecipeBuilder used internally to deserialize the object.
     *
     * @param output      the output
     * @param rows        the rows
     * @param ingredients the ingredients
     */
    protected RecipeBuilder(ItemStack output, String[] rows, Map<String, String> ingredients) {
        this.output = new ItemStack(output);
        this.rows = rows;
        this.ingredients = ingredients;
    }


    /**
     * Instantiates a new RecipeBuilder used internally to deserialize the object.
     *
     * @param output      the output
     * @param ingredients the ingredients
     */
    protected RecipeBuilder(ItemStack output, Map<String, String> ingredients) {
        this.output = new ItemStack(output);
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
