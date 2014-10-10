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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.relicum.ipsum.Game.GamerData;
import com.relicum.ipsum.io.GsonIO;
import org.apache.commons.lang.Validate;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Name: PlayerHandler.java Created: 14 September 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class PlayerHandler {

    private final Gson gson;
    private final Plugin plugin;
    private final String folder;
    private Map<UUID, GamerData> players = new ConcurrentHashMap<>();

    public PlayerHandler(Plugin plugin, String dir) {
        Validate.notNull(plugin);
        this.plugin = plugin;
        gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        this.folder = Paths.get(plugin.getDataFolder() + File.separator + dir).toString();
    }


    /**
     * Get plugin.
     *
     * @return Value of plugin.
     */
    public Plugin getPlugin() {
        return plugin;
    }

    /**
     * Gets gson.
     *
     * @return Value of gson.
     */
    public Gson getGson() {
        return gson;
    }

    /**
     * Gets players.
     *
     * @return Value of players.
     */
    public Map<UUID, GamerData> getPlayers() {
        return players;
    }

    public GamerData getPlayer(UUID uuid) {
        Validate.notNull(uuid);
        return players.get(uuid);

    }

    public void load(UUID uuid, String name) throws IOException {

        if (!players.containsKey(uuid)) {

            plugin.getLogger().info("Loading PlayerHandler for " + name + " with uuid " + uuid.toString());

            String folder = uuid.toString().substring(0, 2);

            File file = new File(plugin.getDataFolder(), "players" + File.separator + folder + File.separator + uuid.toString() + ".json");

            if (!file.getParentFile().exists()) {

                try {
                    if (file.getParentFile().mkdirs()) {
                        plugin.getLogger().info("New folder created for player " + name + " with uuid" + uuid.toString());
                    }
                } catch (Exception e) {
                    plugin.getLogger().severe("Unable to create new Folder for player " + name + " " + uuid.toString());
                    e.printStackTrace();
                }
            }

            file = null;

            GamerData gamerData = GsonIO.readFromFile(Paths.get(plugin.getDataFolder().toString() + File.separator + folder + File.separator, uuid.toString() + ".json"), GamerData.class, new GamerData());


        }


    }

    public void save(UUID uuid) throws IOException {

        String fold = uuid.toString().substring(0, 2);

        GsonIO.writeToFile(Paths.get(folder + File.separator + uuid.toString() + ".json"), getPlayer(uuid), GamerData.class);


    }

    public void addGamerData(GamerData data) {
        Validate.notNull(data);

        players.putIfAbsent(UUID.fromString(data.getUniqueId()), data);

    }

}
