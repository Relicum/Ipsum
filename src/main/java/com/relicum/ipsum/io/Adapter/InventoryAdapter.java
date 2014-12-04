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

import com.relicum.ipsum.io.GsonLoader;
import org.bukkit.craftbukkit.libs.com.google.gson.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.lang.reflect.Type;

/**
 * This is my Gson adapter for Inventories.
 * It handles all inventories as CraftInventoryCustom "Chest"s with size of your choice
 * except for PlayerInventory which it handles pretty darn well!
 * <p>Class designed by the MassiveCraft development team, more details on them can be found at www.MassiveCraft.com.
 * <p>The copyright belongs to MassiveCraft and is licensed for public use under GPLv3.
 */
public class InventoryAdapter implements JsonDeserializer<Inventory>, JsonSerializer<Inventory> {

    // FIELD NAME CONSTANTS
    // -------------------------------------------- //

    public static final String SIZE = "size";
    public static final String TITLE = "title";

    public static final String PLAYER = "player";

    public static final String HELMET = "helmet";
    public static final String CHESTPLATE = "chestplate";
    public static final String LEGGINGS = "leggings";
    public static final String BOOTS = "boots";

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static InventoryAdapter i = new InventoryAdapter();

    public static InventoryAdapter get() {
        return i;
    }

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public JsonElement serialize(Inventory src, Type typeOfSrc, JsonSerializationContext context) {
        return toJson(src);
    }

    @Override
    public Inventory deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return fromJson(json);
    }

    // -------------------------------------------- //
    // IMPLEMENTATION
    // -------------------------------------------- //

    public static JsonElement toJson(Inventory src) {
        // The return value is this object:
        JsonObject jsonInventory = new JsonObject();

        // These variables are used in loops and repetitive logic.
        ItemStack itemStack = null;
        JsonElement jsonItemStack = null;

        // Every inventory has a content part.
        ItemStack[] itemStacks = src.getContents();

        if (src instanceof PlayerInventory) {
            // Add the size "player"
            jsonInventory.addProperty(SIZE, PLAYER);

            // Cast to PlayerInventory
            PlayerInventory psrc = (PlayerInventory) src;

            // helmet
            itemStack = psrc.getHelmet();
            if (itemStack != null) {
                jsonItemStack = GsonLoader.gson.toJsonTree(itemStack, ItemStack.class);
                jsonInventory.add(HELMET, jsonItemStack);
            }

            // chestplate
            itemStack = psrc.getChestplate();
            if (itemStack != null) {
                jsonItemStack = GsonLoader.gson.toJsonTree(itemStack, ItemStack.class);
                jsonInventory.add(CHESTPLATE, jsonItemStack);
            }

            // leggings
            itemStack = psrc.getLeggings();
            if (itemStack != null) {
                jsonItemStack = GsonLoader.gson.toJsonTree(itemStack, ItemStack.class);
                jsonInventory.add(LEGGINGS, jsonItemStack);
            }

            // boots
            itemStack = psrc.getBoots();
            if (itemStack != null) {
                jsonItemStack = GsonLoader.gson.toJsonTree(itemStack, ItemStack.class);
                jsonInventory.add(BOOTS, jsonItemStack);
            }
        } else {
            // Add the size *length*
            jsonInventory.addProperty(SIZE, itemStacks.length);
            jsonInventory.addProperty(TITLE, src.getTitle());
        }

        // Add the content at the end since we like to have it at the bottom of return json.
        for (int i = 0; i < itemStacks.length; i++) {
            itemStack = itemStacks[i];
            jsonItemStack = GsonLoader.gson.toJsonTree(itemStack, ItemStack.class);
            if (jsonItemStack == null) continue;
            jsonInventory.add(String.valueOf(i), jsonItemStack);
        }

        return jsonInventory;
    }

    public static Inventory fromJson(JsonElement json) {
        // If must be an object!
        if (!json.isJsonObject()) return null;
        JsonObject jsonInventory = json.getAsJsonObject();

        // The return value
        Inventory ret = null;

        // These variables are used in loops and repetitive logic.
        ItemStack itemStack = null;
        JsonElement jsonItemStack = null;

        // There must be a size entry!
        if (!jsonInventory.has(SIZE)) return null;

        JsonPrimitive jsonSize = jsonInventory.get(SIZE).getAsJsonPrimitive();
        int size = 0;

        // What size/type is it?
        if (jsonSize.isString() && jsonSize.getAsString().equals(PLAYER)) {
            // We use 36 here since it's the size of the player inventory (without armor)
            size = 36;

            // This is a PlayerInventory
            ret = createPlayerInventory();
            PlayerInventory pret = (PlayerInventory) ret;

            // helmet
            if (jsonInventory.has(HELMET)) {
                jsonItemStack = jsonInventory.get(HELMET);
                itemStack = GsonLoader.gson.fromJson(jsonItemStack, ItemStack.class);
                pret.setHelmet(itemStack);
            }

            // chestplate
            if (jsonInventory.has(CHESTPLATE)) {
                jsonItemStack = jsonInventory.get(CHESTPLATE);
                itemStack = GsonLoader.gson.fromJson(jsonItemStack, ItemStack.class);
                pret.setChestplate(itemStack);
            }

            // leggings
            if (jsonInventory.has(LEGGINGS)) {
                jsonItemStack = jsonInventory.get(LEGGINGS);
                itemStack = GsonLoader.gson.fromJson(jsonItemStack, ItemStack.class);
                pret.setLeggings(itemStack);
            }

            // boots
            if (jsonInventory.has(BOOTS)) {
                jsonItemStack = jsonInventory.get(BOOTS);
                itemStack = GsonLoader.gson.fromJson(jsonItemStack, ItemStack.class);
                pret.setBoots(itemStack);
            }
        } else {
            // A custom size were specified
            size = jsonSize.getAsInt();

            // This is a "Custom" Inventory (content only).
            ret = createInventory(null, size, jsonInventory.getAsJsonPrimitive(TITLE).getAsString());
        }

        // Now process content
        ItemStack[] itemStacks = new ItemStack[size];
        for (int i = 0; i < size; i++) {
            // Fetch the jsonItemStack or mark it as empty and continue
            String stackIdx = String.valueOf(i);
            jsonItemStack = jsonInventory.get(stackIdx);
            itemStack = GsonLoader.gson.fromJson(jsonItemStack, ItemStack.class);
            itemStacks[i] = itemStack;
        }
        ret.setContents(itemStacks);

        return ret;
    }

    public static PlayerInventory createPlayerInventory() {
        return getInventoryMixin().createPlayerInventory();
    }

    public static Inventory createInventory(InventoryHolder holder, int size, String title) {
        return getInventoryMixin().createInventory(holder, size, title);
    }

    public static InventoryMixin getInventoryMixin() {
        return inventoryMixin;
    }

    private static InventoryMixin inventoryMixin = InventoryMixinDefault.get();
}
