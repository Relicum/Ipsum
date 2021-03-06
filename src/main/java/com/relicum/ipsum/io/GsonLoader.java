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

package com.relicum.ipsum.io;


import com.relicum.ipsum.Items.Inventory.Slot;
import com.relicum.ipsum.Menus.BungeeMenuItem;
import com.relicum.ipsum.Menus.MenuItem;
import com.relicum.ipsum.io.Adapter.*;
import org.bukkit.craftbukkit.libs.com.google.gson.*;
import org.bukkit.craftbukkit.libs.com.google.gson.reflect.TypeToken;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.lang.reflect.Modifier;
import java.util.EnumMap;
import java.util.UUID;

/**
 * GsonLoader returns a pre configured instance of {@link com.google.gson.Gson} with common types already been registered with the object.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class GsonLoader {


    public static Gson gson = getGsonBuilder().create();
    public static Gson gsond = getGsonBuilder2().create();

    public static GsonBuilder getGsonBuilder() {
        return new GsonBuilder()
                .setPrettyPrinting()

                .serializeNulls()
                        //.disableHtmlEscaping()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .registerTypeAdapter(new TypeToken<EnumMap<Slot, BungeeMenuItem>>() {
                }.getType(), new EnumMapInstanceCreator<Slot, BungeeMenuItem>(Slot.class))
                .registerTypeAdapter(new TypeToken<EnumMap<Slot, MenuItem>>() {
                }.getType(), new EnumMapInstanceCreator<Slot, MenuItem>(Slot.class))
                .registerTypeAdapter(JsonNull.class, JsonElementAdapter.get())
                .registerTypeAdapter(JsonPrimitive.class, JsonElementAdapter.get())
                .registerTypeAdapter(JsonArray.class, JsonElementAdapter.get())
                .registerTypeAdapter(JsonObject.class, JsonElementAdapter.get())
                .registerTypeAdapter(UUID.class, UUIDAdapter.get())
                .registerTypeAdapter(ItemStack.class, ItemStackAdapter.get())
                .registerTypeAdapter(Inventory.class, InventoryAdapter.get())
                .registerTypeAdapter(PlayerInventory.class, PlayerInventoryAdapter.get());


    }

    public static GsonBuilder getGsonBuilder2() {
        return new GsonBuilder()
                //.setPrettyPrinting()

                //.serializeNulls()
                //.disableHtmlEscaping()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .registerTypeAdapter(new TypeToken<EnumMap<Slot, BungeeMenuItem>>() {
                }.getType(), new EnumMapInstanceCreator<Slot, BungeeMenuItem>(Slot.class))
                .registerTypeAdapter(new TypeToken<EnumMap<Slot, MenuItem>>() {
                }.getType(), new EnumMapInstanceCreator<Slot, MenuItem>(Slot.class))
                .registerTypeAdapter(JsonNull.class, JsonElementAdapter.get())
                .registerTypeAdapter(JsonPrimitive.class, JsonElementAdapter.get())
                .registerTypeAdapter(JsonArray.class, JsonElementAdapter.get())
                .registerTypeAdapter(JsonObject.class, JsonElementAdapter.get())
                .registerTypeAdapter(UUID.class, UUIDAdapter.get())
                .registerTypeAdapter(ItemStack.class, ItemStackAdapter.get())
                .registerTypeAdapter(Inventory.class, InventoryAdapter.get())
                .registerTypeAdapter(PlayerInventory.class, PlayerInventoryAdapter.get());


    }
}
