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

import net.minecraft.util.com.google.gson.JsonElement;
import net.minecraft.util.com.google.gson.JsonObject;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * PotionEffectAdapter json serializer.
 * <p>Class designed by the MassiveCraft development team, more details on them can be found at www.MassiveCraft.com.
 * <p>The copyright belongs to MassiveCraft and is licensed for public use under GPLv3.
 *
 * @version 0.0.1
 */
@SuppressWarnings("deprecation")
public class PotionEffectAdapter {

    // -------------------------------------------- //
    // FIELD CONSTANTS
    // -------------------------------------------- //

    public static final String POTION_EFFECT_ID = "id";
    public static final String POTION_DURATION = "duration";
    public static final String POTION_AMPLIFIER = "amplifier";
    public static final String POTION_AMBIENT = "ambient";

    public static final int POTION_DURATION_DEFAULT = 20 * 3 * 60;
    public static final int POTION_AMPLIFIER_DEFAULT = 0;
    public static final boolean POTION_AMBIENT_DEFAULT = false;

    // -------------------------------------------- //
    // TO JSON
    // -------------------------------------------- //

    public static JsonObject toJson(PotionEffect potionEffect) {
        if (potionEffect == null) return null;

        JsonObject ret = new JsonObject();

        ret.addProperty(POTION_EFFECT_ID, potionEffect.getType().getId());
        ret.addProperty(POTION_DURATION, potionEffect.getDuration());
        ret.addProperty(POTION_AMPLIFIER, potionEffect.getAmplifier());
        ret.addProperty(POTION_AMBIENT, potionEffect.isAmbient());

        return ret;
    }

    // -------------------------------------------- //
    // FROM JSON
    // -------------------------------------------- //

    public static PotionEffect fromJson(JsonElement jsonElement) {
        if (jsonElement == null) return null;
        if (!jsonElement.isJsonObject()) return null;

        JsonObject json = jsonElement.getAsJsonObject();

        PotionEffectType pet = PotionEffectType.getById(json.get(POTION_EFFECT_ID).getAsInt());

        int duration = POTION_DURATION_DEFAULT;
        JsonElement durationElement = json.get(POTION_DURATION);
        if (durationElement != null) {
            duration = durationElement.getAsInt();
        }

        int amplifier = POTION_AMPLIFIER_DEFAULT;
        JsonElement amplifierElement = json.get(POTION_AMPLIFIER);
        if (amplifierElement != null) {
            amplifier = amplifierElement.getAsInt();
        }

        boolean ambient = POTION_AMBIENT_DEFAULT;
        JsonElement ambientElement = json.get(POTION_AMBIENT);
        if (ambientElement != null) {
            ambient = ambientElement.getAsBoolean();
        }

        return new PotionEffect(pet, duration, amplifier, ambient);
    }
}
