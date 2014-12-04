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
import java.util.UUID;

/**
 * UUIDAdapter automatically detail with UUID in any objects.
 * <p>Class designed by the MassiveCraft development team, more details on them can be found at www.MassiveCraft.com.
 * <p>The copyright belongs to MassiveCraft and is licensed for public use under GPLv3.
 *
 * @author MassiveCraft
 * @version 0.0.1
 */
public class UUIDAdapter implements JsonSerializer<UUID>, JsonDeserializer<UUID> {


    private static UUIDAdapter i = new UUIDAdapter();

    public static UUIDAdapter get() {
        return i;
    }

    /**
     * Gson invokes this call-back method during deserialization when it encounters a field of the
     * specified type.
     * <p>In the implementation of this call-back method, you should consider invoking
     * {@link com.google.gson.JsonDeserializationContext#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type)} method to create objects
     * for any non-trivial field of the returned object. However, you should never invoke it on the
     * the same type passing {@code json} since that will cause an infinite loop (Gson will call your
     * call-back method again).
     *
     * @param json    The Json data being deserialized
     * @param typeOfT The type of the Object to deserialize to
     * @param context the context
     * @return a deserialized object of the specified type typeOfT which is a subclass of {@code T}
     * @throws com.google.gson.JsonParseException if json is not in the expected format of {@code typeofT}
     */
    @Override
    public UUID deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        return convertJsonElementToUUID(json);
    }

    /**
     * Gson invokes this call-back method during serialization when it encounters a field of the
     * specified type.
     * <p>In the implementation of this call-back method, you should consider invoking
     * {@link com.google.gson.JsonSerializationContext#serialize(Object, java.lang.reflect.Type)} method to create JsonElements for any
     * non-trivial field of the {@code src} object. However, you should never invoke it on the
     * {@code src} object itself since that will cause an infinite loop (Gson will call your
     * call-back method again).
     *
     * @param src       the object that needs to be converted to Json.
     * @param typeOfSrc the actual type (fully generalized version) of the source object.
     * @param context   the context
     * @return a JsonElement corresponding to the specified object.
     */
    @Override
    public JsonElement serialize(UUID src, Type typeOfSrc, JsonSerializationContext context) {

        return convertUUIDToJsonPrimitive(src);
    }

    private static String convertUUIDToString(UUID objID) {

        return objID.toString();
    }

    private static UUID convertStringToUUID(String s) {

        return UUID.fromString(s);
    }

    private static UUID convertJsonElementToUUID(JsonElement jsonElement) {

        return convertStringToUUID(jsonElement.getAsString());
    }

    private static JsonPrimitive convertUUIDToJsonPrimitive(UUID objID) {

        return new JsonPrimitive(convertUUIDToString(objID));
    }
}
