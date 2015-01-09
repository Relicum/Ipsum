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

package com.relicum.ipsum.io;

import org.bukkit.craftbukkit.libs.com.google.gson.Gson;
import org.bukkit.inventory.Inventory;

/**
 * JsonStringInv Temporary class to serialise and deserialize custom inventories using JSON.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class JsonStringInv {

    public final static Gson gson = GsonLoader.gsond;

    /**
     * Convert an Inventory to a JSON String
     *
     * @param inventory the inventory
     * @return the serialised JSON String
     */
    public static String convertToJson(Inventory inventory) {

        return gson.toJson(inventory, Inventory.class);
    }

    /**
     * Convert from JSON String to custom Inventory.
     *
     * @param json the json string to deserialize.
     * @return the inventory
     */
    public static Inventory convertFromString(String json) {

        return gson.fromJson(json, Inventory.class);
    }

}
