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

package com.relicum.ipsum.Configuration;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * AbstractSerializable is a generic class you can impliment that wil Serialize and deserialize to yml files by default.
 * <p>The author of the original version of this class was dmulloy2 all credits do to him. The License for the file is GPL3
 * the orginal source can be found at <a href="https://github.com/dmulloy2/PlayerData/blob/master/src/main/java/net/dmulloy2/playerdata/types/AbstractPlayerData.java">GITHUB</a>
 *
 * @author dmulloy2
 * @version 0.0.1
 */
public abstract class AbstractSerializable implements ConfigurationSerializable {


    public AbstractSerializable() {
    }


    public AbstractSerializable(Map<String, Object> args) {
        for (Map.Entry<String, Object> entry : args.entrySet()) {
            try {
                for (Field field : getClass().getDeclaredFields()) {
                    if (field.getName().equals(entry.getKey())) {
                        boolean accessible = field.isAccessible();
                        field.setAccessible(true);
                        field.set(this, entry.getValue());
                        field.setAccessible(accessible);
                    }
                }
            } catch (Throwable ex) {
            }
        }
    }


    @Override
    public final Map<String, Object> serialize() {
        Map<String, Object> data = new LinkedHashMap<>();

        for (Field field : getClass().getDeclaredFields()) {
            if (Modifier.isTransient(field.getModifiers()))
                continue;

            try {
                boolean accessible = field.isAccessible();

                field.setAccessible(true);

                if (field.getType().equals(Integer.TYPE)) {
                    if (field.getInt(this) != 0)
                        data.put(field.getName(), field.getInt(this));
                } else if (field.getType().equals(Long.TYPE)) {
                    if (field.getLong(this) != 0)
                        data.put(field.getName(), field.getLong(this));
                } else if (field.getType().equals(Boolean.TYPE)) {
                    if (field.getBoolean(this))
                        data.put(field.getName(), field.getBoolean(this));
                } else if (field.getType().isAssignableFrom(Collection.class)) {
                    if (!((Collection<?>) field.get(this)).isEmpty())
                        data.put(field.getName(), field.get(this));
                } else if (field.getType().isAssignableFrom(String.class)) {
                    if (((String) field.get(this)) != null)
                        data.put(field.getName(), field.get(this));
                } else if (field.getType().isAssignableFrom(Map.class)) {
                    if (!((Map<?, ?>) field.get(this)).isEmpty())
                        data.put(field.getName(), field.get(this));
                } else {
                    if (field.get(this) != null)
                        data.put(field.getName(), field.get(this));
                }

                field.setAccessible(accessible);
            } catch (Throwable ex) {
            }
        }

        return data;
    }
}
