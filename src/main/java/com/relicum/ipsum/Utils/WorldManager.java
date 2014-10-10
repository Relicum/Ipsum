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

import com.relicum.ipsum.Configuration.ConfigManager;
import com.relicum.ipsum.Configuration.Loc;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.*;
import org.bukkit.block.BlockState;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * WorldManager used to Create, Modify and Administer new Worlds.
 *
 * @author Relicum
 * @version 0.0.1
 */
@Getter
@Setter
public class WorldManager {

    private Plugin plugin;

    private String name = "";

    private String type = WorldType.FLAT.getName();

    private String generator = "";

    private Boolean generateStructures = false;

    private String environment = World.Environment.NORMAL.name();

    private long seed = 0l;

    private Boolean enable = true;

    private Boolean spawninmemory = true;

    private Boolean monsters = false;

    private Boolean animals = true;

    private Boolean autosave = true;

    private String difficulty = "HARD";

    private Boolean pvp = true;

    private Boolean setstorm = false;

    private Boolean setthunder = false;

    private long clearweather = 10000l;

    private long settime = 6000l;

    private Loc spawnBlockLocation = new Loc(0, 31, 0, 0.0f, 0.0f, name);

    private Loc spawnLocation = new Loc(0, 32, 0, 0.0f, 0.0f, name);

    private String spawnBlock = Material.GOLD_BLOCK.name();

    private Boolean doFireTick = false;

    private Boolean mobGriefing = false;

    private Boolean doDaylightCycle = false;

    private Boolean commandBlockOutput = false;

    private ConfigManager configManager;

    /**
     * Instantiates a new World manager.
     * <p>This will create any required directories for you.
     * It will also load all the relevant world config files and store
     * them in the config manager.
     * <p>Any world that is marked as enabled will be loaded with a delayed
     * start to make sure everything else has load first.
     *
     * @param plug          the instance of your plugins main class
     * @param configManager the configManager
     */
    public WorldManager(Plugin plug, ConfigManager configManager) {

        this.plugin = plug;

        createDirectory();
        this.configManager = configManager;
        configManager.putWorlds(getWorldConfigs());

        delayedWorldLoad();

    }

    /**
     * Create the world directory for storing world config in.
     * <p>You should not need to call this as its called in the
     * constructor.
     */
    public void createDirectory() {

        FileUtils.createDirectory(getPlugin().getDataFolder().toString(), "worlds");

    }

    /**
     * Get plugin.
     *
     * @return an instance of YOUR plugins main class
     */
    public Plugin getPlugin() {

        return plugin;
    }

    /**
     * Searches the world config folder and returns a list of all yml lines.
     *
     * @return the list of world config files. Each one represents its only world.
     */
    public List<String> getWorldConfigs() {

        try {
            return FileUtils.getAllFilesInDirectory(getPlugin().getDataFolder().toString() + File.separator + "worlds" + File.separator, "yml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public void getWorldLoadConfig(String name) {


    }

    /**
     * Delayed world load, loads all enabled worlds on server start.
     * <p>The delay is to allow all other plugins to load first.
     */
    public void delayedWorldLoad() {

        List<String> list = configManager.getEnabled();
        if (list == null) {
            System.out.println("There are no worlds to load");
            getPlugin().getLogger().warning("There were no worlds to load");
            return;
        }
        new BukkitRunnable() {

            int c = 0;

            int t = list.size() - 1;

            /**
             * When an object implementing interface <code>Runnable</code> is used
             * to create a thread, starting the thread causes the object's
             * <code>run</code> method to be called in that separately executing
             * thread.
             * <p>
             * The general contract of the method <code>run</code> is that it may
             * take any action whatsoever.
             *
             * @see Thread#run()
             */
            @Override
            public void run() {

                if (c > t) {
                    System.out.println("Load load repeating task ended num " + c);
                    cancel();
                    return;
                }

                if (configManager.getWorld(list.get(c)).getFirstTime()) {
                    System.out.println("rst time load loading num " + c);
                    configManager.getWorld(list.get(c)).setFirstTime(false);
                    applyNewWorldSettings(list.get(c));
                } else {
                    System.out.println("Normal world loading num " + c);
                    loadWorld(list.get(c));
                }
                c++;

            }
        }.runTaskTimer(getPlugin(), 60l, 40l);


    }

    /**
     * Load a world by name, the configs for the world should of been loaded before calling this.
     *
     * @param name the name of the world you wish to load.
     */
    public void loadWorld(String name) {

        try {
            WorldCreator.name(name).createWorld();
        } catch (Exception e) {
            e.printStackTrace();
        }

        getPlugin().getLogger().info("Successfully load the world: " + name);
    }

    /**
     * Loads a new world for the first time applying some default settings.
     * <p>You can change any of the settings once the world has been loaded once.
     * You can do that through the config file or by using the in game WorldModify command.
     *
     * @param name the name of the world you wish to load.
     */
    public void applyNewWorldSettings(String name) {

        World world = WorldCreator.name(name).createWorld();

        world.loadChunk(0, 0, true);
        world.setSpawnLocation(spawnLocation.getX(), spawnLocation.getY(), spawnLocation.getZ());
        world.setKeepSpawnInMemory(spawninmemory);

        world.getSpawnLocation().getWorld().getChunkAt(world.getSpawnLocation()).load();
        BlockState block = world.getBlockAt(spawnBlockLocation.getX(), spawnBlockLocation.getY(), spawnBlockLocation.getZ()).getState();
        block.setType(Material.valueOf(spawnBlock));
        block.update(true);
        world.setAutoSave(getAutosave());
        world.setDifficulty(Difficulty.valueOf(getDifficulty()));
        world.setStorm(setstorm);
        world.setThundering(setthunder);
        world.setWeatherDuration(1000000);
        world.setTime(settime);

        world.setGameRuleValue("doDaylightCycle", getDoDaylightCycle().toString());
        world.setGameRuleValue("doFireTick", getDoFireTick().toString());
        world.setGameRuleValue("mobGriefing", getMobGriefing().toString());
        world.setGameRuleValue("commandBlockOutput", getCommandBlockOutput().toString());


    }

    public boolean checkWorldHasConfig(String name) {

        for (String s : getWorldConfigs()) {
            if (s.equalsIgnoreCase(name))
                return true;

        }
        return false;
    }
}
