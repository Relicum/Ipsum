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

package com.relicum.ipsum.Utils;

import com.relicum.ipsum.Configuration.WorldConfig;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Handles and Manages world management from a single location.
 * <p>If you use this you need to add in the plugins main class in the on enable method
 * <code>ConfigurationSerialization.registerClass(WorldConfig.class)</code>
 *
 * @author Relicum
 * @version 0.0.1
 */
@SuppressWarnings("ConstantConditions")
public class WorldHandler {

    @Getter
    private final Plugin plugin;
    private WeakReference<Map<String, World>> worlds;
    private WorldConfig config;

    private WorldHandler(Plugin plugin) {
        this.plugin = plugin;
        setupDefaults();
        worlds = new WeakReference<>(new HashMap<>());
    }

    /**
     * Gets new world handler.
     *
     * @param plugin the plugin
     * @return the handler
     */
    public static WorldHandler getHandler(Plugin plugin) {
        return new WorldHandler(plugin);
    }

    private void setupDefaults() {

        if (!plugin.getConfig().contains("mw.settings")) {
            config = new WorldConfig();
            config.setEnable(true);
            config.setStandAlone(false);
            config.setHandleFirstSpawn(false);
            config.setHandleAllSpawns(false);
            config.setHandleNoSpawns(false);
            config.setRemoveEnd(false);
            config.setRemoveNether(false);
            config.setHandleWorldChat(false);
            config.setIsVoidWorld(false);
            config.setRealTimeWorldLoading(false);
            config.setRestartAfterGame(false);

            plugin.getConfig().set("mw.settings", config);
            plugin.saveConfig();

        } else
            config = (WorldConfig) plugin.getConfig().get("mw.settings");

    }


    /**
     * Init worlds on start up
     */
    public void initWorlds() {

    }

    /**
     * New world setup use the {@link com.relicum.ipsum.Minecraft.WorldBuilder} to create the world and pass it to this method.
     *
     * @param world the new {@link org.bukkit.World} to setup and initialize its settings.
     */
    public void newWorldSetup(World world) {


    }

    /**
     * Load world.
     *
     * @param name the name
     * @return the boolean
     */
    public boolean loadWorld(String name) {

        return WorldCreator.name(name).createWorld() != null;
    }

    /**
     * Unload world.
     *
     * @param name   of the {@link org.bukkit.World} to unload
     * @param unload set to true to save the chunks before unloading the world
     */
    public void unloadWorld(String name, boolean unload) {
        if (plugin.getServer().getWorld(name) != null) {

            plugin.getServer().unloadWorld(name, unload);

        }
    }

    /**
     * Save all worlds that the plugin manages
     *
     * @return true if all worlds were saved, false if there was an issue
     */
    public boolean saveAll() {
        try {
            if (worlds.get() != null)
                worlds.get().values().forEach(World::save);
            return true;
        } catch (NullPointerException ignored) {
            plugin.getLogger().warning("NPE when saving worlds to be expected some time");
        }
        return false;
    }

    /**
     * Save world.
     *
     * @param name the {@link org.bukkit.World} name
     * @return true if it saved, false and there was a problem
     */
    public boolean saveWorld(String name) {

        try {
            getWorld(name).save();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Get world by name
     *
     * @param name the world name
     * @return the {@link org.bukkit.World}
     */
    public World getWorld(String name) {

        try {
            return worlds.get().get(name);

        } catch (NullPointerException ne) {
            plugin.getLogger().warning("NPE when saving worlds to be expected some time");
            ne.printStackTrace();
        }

        return worlds.get().put(name, getWorld(name));

    }

    /**
     * Get world by {@link java.util.UUID}
     *
     * @param uuid of the world
     * @return the {@link org.bukkit.World}
     */
    public World getWorld(UUID uuid) {

        return plugin.getServer().getWorld(uuid);
    }

    /**
     * Send message to players in specified world.
     *
     * @param name    the {@link org.bukkit.World} name
     * @param message the message to send to all players
     */
    public void sendMessageToPlayers(String name, String message) {

        getWorld(name).getPlayers().forEach(p -> p.sendMessage(message));

    }

    /**
     * Kick all players from a world.
     *
     * @param name the {@link org.bukkit.World} the world name to kick the players from
     */
    public void kickAllPlayers(String name) {

        getWorld(name).getPlayers().forEach(p -> p.kickPlayer("World is shutting down"));
    }

    public void messageAndKick(String name) {

        sendMessageToPlayers(name, ChatColor.LIGHT_PURPLE + "Please switch worlds this world is shutting down in 10 seconds");

        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> kickAllPlayers(name), 200l);
    }

    /**
     * Remove all non living entities from the world, likely to cause lag if there are lots of entities.
     * <p>I plan to recode this later on to avoid causing any lag.
     *
     * @param name the {@link org.bukkit.World} name
     */
    public void removeAllNonLivingEntities(String name) {

        getWorld(name).getEntities().stream().filter(p -> (!(p instanceof LivingEntity))).forEach(Entity::remove);

    }

    /**
     * Sets new config.
     *
     * @param config New value of config.
     */
    public void setConfig(WorldConfig config) {
        this.config = config;
    }

    /**
     * Gets config.
     *
     * @return Value of config.
     */
    public WorldConfig getConfig() {
        return config;
    }
}
