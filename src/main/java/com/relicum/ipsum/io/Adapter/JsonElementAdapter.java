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

import org.bukkit.craftbukkit.libs.com.google.gson.*;

import java.lang.reflect.Type;

/**
 * JsonElementAdapter
 * <p>Class designed by the MassiveCraft development team, more details on them can be found at www.MassiveCraft.com.
 * <p>The copyright belongs to MassiveCraft and is licensed for public use under GPLv3.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class JsonElementAdapter implements JsonDeserializer<JsonElement>, JsonSerializer<JsonElement> {
    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static JsonElementAdapter i = new JsonElementAdapter();

    public static JsonElementAdapter get() {
        return i;
    }

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public JsonElement serialize(JsonElement src, Type typeOfSrc, JsonSerializationContext context) {
        return src;
    }

    @Override
    public JsonElement deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return json;
    }

}
