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


import net.cubespace.Yamler.Config.InvalidConfigurationException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * PlayerManager is used to load and create new Player data files as well as provide a central place for the plugin to access player data.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class PlayerManager<T extends JavaPlugin> {

    private final T plugin;
    public final HashMap<String, PlayerData> loadedPlayers = new HashMap<>();


    public PlayerManager(T plugin) {

        this.plugin = plugin;
    }

    /**
     * Is the players {@link PlayerData} loaded
     *
     * @param uuid the uuid of the player to check if they are already loaded.
     * @return true if they have they have been loaded false if not.
     */
    public boolean isLoaded(UUID uuid) {

        return loadedPlayers.containsKey(uuid.toString());
    }

    /**
     * Save a players data file to disk.
     *
     * @param uuid the uuid in string format of the player.
     */
    public void save(UUID uuid) throws RuntimeException {

        if (isLoaded(uuid)) {

            try {
                loadedPlayers.get(uuid.toString()).save();
                plugin.getLogger().info("Successfully saved PlayerDatabase with uuid " + uuid.toString());
            } catch (InvalidConfigurationException e) {
                plugin.getLogger().severe("Failed saving PlayerDatabase with uuid " + uuid.toString());
                throw new RuntimeException(e);
            }
        }

    }


    /**
     * Remove void.
     *
     * @param uuid the uuid of the player to remove
     * @throws RuntimeException if the player was unable to be removed
     */
    public boolean remove(UUID uuid) throws RuntimeException {

        if (isLoaded(uuid)) {

            try {
                loadedPlayers.get(uuid.toString()).save();
                loadedPlayers.remove(uuid.toString());
                plugin.getLogger().info("Successfully removed from loaded players PlayerDatabase with uuid " + uuid.toString());
                return true;
            } catch (InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }
        }

        return false;
    }

    /**
     * Remove all players from the Player Store, saving each player before hand, best used in plugins on disable method.
     */
    public void removeAll() {


        for (Map.Entry<String, PlayerData> player : loadedPlayers.entrySet()) {
            try {
                player.getValue().save();
                loadedPlayers.remove(player.getKey());
                plugin.getLogger().info("Successfully removed from loaded players PlayerDatabase with name " + player.getValue().getName());
            } catch (InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }

        plugin.getLogger().info("All player successfully saved and removed from PlayerStore");
    }

    /**
     * Get {@link org.bukkit.entity.Player} from Player Store using players UUID to search by.
     *
     * @param uuid the uuid
     * @return the player
     * @throws Exception if were unable to return find a valid player with that UUID
     */
    public Player getPlayer(UUID uuid) throws Exception {

        if (isLoaded(uuid)) {
            return Bukkit.getPlayer(UUID.fromString(loadedPlayers.get(uuid.toString()).getUniqueId()));
        } else throw new Exception("Unable to get player from loaded players");
    }

    /**
     * Get player data object from the player store.
     *
     * @param player the uuid in string format of the player.
     * @return the {@link PlayerData}
     */
    public PlayerData get(Player player) {

        return loadedPlayers.get(player.getUniqueId().toString());

    }

    /**
     * Load a new {@link PlayerData} and add it to the Player Store
     *
     * @param uuid the uuid of the player
     * @param name the name of the player {@link org.bukkit.entity.Player#getName()}
     */
    public void load(UUID uuid, String name) {

        if (!loadedPlayers.containsKey(uuid.toString())) {

            plugin.getLogger().info("Loading PlayerDatabase for " + name + " with uuid " + uuid.toString());

            String folder = uuid.toString().substring(0, 2);

            File file = new File(plugin.getDataFolder(), "players" + File.separator + folder + File.separator + uuid.toString() + ".yml");

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

            PlayerData playerData = new PlayerData(plugin, uuid, name);

            try {
                playerData.init();
                loadedPlayers.put(uuid.toString(), playerData);
                plugin.getLogger().info("Successfully initialized PlayerDatabase for " + name);
            } catch (InvalidConfigurationException e) {
                plugin.getLogger().severe("Failed to init PlayerDatabase for " + name);
                e.printStackTrace();
            }
            playerData.setLastSeen(System.currentTimeMillis());
        }

    }

    /**
     * Delete player data.
     *
     * @param uuid the uuid of the player
     * @return the true if the player file was deleted false if the file didn't exist
     */
    public boolean deletePlayerData(UUID uuid) {

        if (isLoaded(uuid)) {
            loadedPlayers.remove(uuid.toString());
            System.out.println("Remove from loaded players");
        }

        String folder = uuid.toString().substring(0, 2);
        File file = new File(plugin.getDataFolder(), "players" + File.separator + folder + File.separator + uuid.toString() + ".yml");

        return !file.exists() || file.delete();

    }
}
