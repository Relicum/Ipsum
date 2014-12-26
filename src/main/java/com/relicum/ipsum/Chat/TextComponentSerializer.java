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
package com.relicum.ipsum.Chat;

import org.bukkit.craftbukkit.libs.com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author md_5
 */

public class TextComponentSerializer extends BaseComponentSerializer implements JsonSerializer<TextComponent>,
        JsonDeserializer<TextComponent> {

    @Override
    public TextComponent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        TextComponent component = new TextComponent();
        JsonObject object = json.getAsJsonObject();
        deserialize(object, component, context);
        component.setText(object.get("text").getAsString());
        return component;
    }

    @Override
    public JsonElement serialize(TextComponent src, Type typeOfSrc, JsonSerializationContext context) {
        List<BaseComponent> extra = src.getExtra();
        if (!src.hasFormatting() && (extra == null || extra.size() == 0))
            return new JsonPrimitive(src.getText());

        JsonObject object = new JsonObject();
        serialize(object, src, context);
        object.addProperty("text", src.getText());
        return object;
    }
}
