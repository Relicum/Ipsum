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


import com.relicum.ipsum.io.Adapter.ItemStackAdapter;
import com.relicum.ipsum.io.Adapter.JsonElementAdapter;
import com.relicum.ipsum.io.Adapter.UUIDAdapter;
import net.minecraft.util.com.google.gson.*;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Modifier;
import java.util.UUID;

/**
 * Name: GsonLoader.java Created: 25 September 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class GsonLoader {


    public static Gson gson = getGsonBuilder().create();

    public static GsonBuilder getGsonBuilder() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .disableHtmlEscaping()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .registerTypeAdapter(JsonNull.class, JsonElementAdapter.get())
                .registerTypeAdapter(JsonPrimitive.class, JsonElementAdapter.get())
                .registerTypeAdapter(JsonArray.class, JsonElementAdapter.get())
                .registerTypeAdapter(JsonObject.class, JsonElementAdapter.get())
                .registerTypeAdapter(UUID.class, UUIDAdapter.get())
                .registerTypeAdapter(ItemStack.class, ItemStackAdapter.get());
        // .registerTypeAdapter(Inventory.class, InventoryAdapter.get())
        //  .registerTypeAdapter(PlayerInventory.class, PlayerInventoryAdapter.get())

    }
}
