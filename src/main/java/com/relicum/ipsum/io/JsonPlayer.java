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

import com.relicum.ipsum.Configuration.PersistentPlayer;
import org.bukkit.craftbukkit.libs.com.google.gson.Gson;
import org.bukkit.craftbukkit.libs.com.google.gson.GsonBuilder;
import org.bukkit.craftbukkit.libs.com.google.gson.reflect.TypeToken;
import org.bukkit.craftbukkit.libs.com.google.gson.stream.JsonReader;
import org.bukkit.craftbukkit.libs.com.google.gson.stream.JsonWriter;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Paths;

/**
 * JsonPlayer used to serialize and deserialize {@link com.relicum.ipsum.Configuration.PersistentPlayer} objects to and from disk.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class JsonPlayer {

    private final Gson gson;
    private final Type type;
    private final String stringPath;

    /**
     * Instantiates a new JsonPlayer object, current usage is more of a data abstraction layer.
     *
     * @param plugin the {@link org.bukkit.plugin.Plugin}
     */
    public JsonPlayer(Plugin plugin) {

        type = new TypeToken<PersistentPlayer>() {
        }.getType();

        gson = new GsonBuilder().serializeNulls().setPrettyPrinting().enableComplexMapKeySerialization().create();

        stringPath = plugin.getDataFolder().toString() + File.separator + "players" + File.separator;
    }

    /**
     * Save to the {@link com.relicum.ipsum.Configuration.PersistentPlayer} object to disk in JSON format.
     *
     * @param persistentPlayer the {@link com.relicum.ipsum.Configuration.PersistentPlayer} that will be serialized to JSON and saved to disk
     */
    public void saveToDisk(PersistentPlayer persistentPlayer) {

        try {
            JsonWriter jsonWriter = GsonIO.writer(Paths.get(stringPath, persistentPlayer.getUuid() + ".json"));
            jsonWriter.setIndent("    ");
            gson.toJson(persistentPlayer, type, jsonWriter);
            jsonWriter.flush();
            jsonWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load PersistentPlayer from disk, passing the players {@link java.util.UUID} as reference.
     *
     * @param uuid the {@link java.util.UUID} of the player.
     * @return the {@link com.relicum.ipsum.Configuration.PersistentPlayer} object.
     */
    public PersistentPlayer readFromDisk(String uuid) {
        PersistentPlayer player = null;
        try {
            JsonReader jsonReader = GsonIO.reader(Paths.get(stringPath, uuid + ".json"));
            player = gson.fromJson(jsonReader, type);
            jsonReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return player;
    }

    public Type getType() {
        return this.type;
    }

}
