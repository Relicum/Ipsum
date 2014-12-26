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

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonArray;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonElement;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonObject;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonPrimitive;

import java.util.*;

public class FireworkEffectAdapter {

    // -------------------------------------------- //
    // FIELD CONSTANTS
    // -------------------------------------------- //

    public static final String FLICKER = "flicker";
    public static final String TRAIL = "trail";
    public static final String COLORS = "colors";
    public static final String FADE_COLORS = "fade-colors";
    public static final String TYPE = "type";

    public static final boolean FLICKER_DEFAULT = false;
    public static final boolean TRAIL_DEFAULT = false;
    public static final List<Color> COLORS_DEFAULT = Collections.unmodifiableList(Arrays.asList(Color.GREEN));
    public static final List<Color> FADE_COLORS_DEFAULT = Collections.unmodifiableList(new ArrayList<Color>());
    public static final Type TYPE_DEFAULT = Type.BALL_LARGE;

    // -------------------------------------------- //
    // TO JSON
    // -------------------------------------------- //

    public static JsonObject toJson(FireworkEffect fireworkEffect) {
        if (fireworkEffect == null) return null;

        JsonObject ret = new JsonObject();

        ret.addProperty(FLICKER, fireworkEffect.hasFlicker());
        ret.addProperty(TRAIL, fireworkEffect.hasTrail());
        ret.add(COLORS, fromColorCollection(fireworkEffect.getColors()));
        ret.add(FADE_COLORS, fromColorCollection(fireworkEffect.getFadeColors()));
        ret.addProperty(TYPE, fireworkEffect.getType().name());

        return ret;
    }

    // -------------------------------------------- //
    // FROM JSON
    // -------------------------------------------- //

    public static FireworkEffect fromJson(JsonElement jsonElement) {
        if (jsonElement == null) return null;
        if (!jsonElement.isJsonObject()) return null;

        JsonObject json = jsonElement.getAsJsonObject();

        boolean flicker = FLICKER_DEFAULT;
        boolean trail = TRAIL_DEFAULT;
        List<Color> colors = COLORS_DEFAULT;
        List<Color> fadeColors = FADE_COLORS_DEFAULT;
        Type type = TYPE_DEFAULT;

        JsonElement element;

        element = json.get(FLICKER);
        if (element != null) {
            flicker = element.getAsBoolean();
        }

        element = json.get(TRAIL);
        if (element != null) {
            trail = element.getAsBoolean();
        }

        element = json.get(COLORS);
        if (element != null) {
            colors = toColorCollection(element);
        }

        element = json.get(FADE_COLORS);
        if (element != null) {
            fadeColors = toColorCollection(element);
        }

        element = json.get(TYPE);
        if (element != null) {
            type = Type.valueOf(element.getAsString());
        }

        FireworkEffect ret = FireworkEffect.builder()
                .flicker(flicker)
                .trail(trail)
                .withColor(colors)
                .withFade(fadeColors)
                .with(type)
                .build();

        return ret;
    }

    // -------------------------------------------- //
    // MINI UTILS
    // -------------------------------------------- //

    public static JsonArray fromColorCollection(Collection<Color> colors) {
        JsonArray ret = new JsonArray();
        for (Color color : colors) {
            ret.add(new JsonPrimitive(color.asRGB()));
        }
        return ret;
    }

    public static List<Color> toColorCollection(JsonElement json) {
        JsonArray array = json.getAsJsonArray();
        List<Color> ret = new ArrayList<Color>();

        Iterator<JsonElement> iter = array.iterator();
        while (iter.hasNext()) {
            JsonElement element = iter.next();
            ret.add(Color.fromRGB(element.getAsInt()));
        }

        return ret;
    }

}
