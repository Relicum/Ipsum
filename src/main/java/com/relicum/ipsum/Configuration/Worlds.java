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

import lombok.Getter;
import lombok.Setter;
import net.cubespace.Yamler.Config.Comment;
import net.cubespace.Yamler.Config.Config;
import net.cubespace.Yamler.Config.ConfigSection;
import net.cubespace.Yamler.Config.Path;
import net.minecraft.util.com.google.common.collect.Maps;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Worlds is used as a model that all worlds use to store their configs in.
 * <p>It is stored and reference through the {@link com.relicum.ipsum.Configuration.ConfigManager}
 * But most of the world managed ment is done through the {@link com.relicum.ipsum.Utils.WorldManager} object.
 * <p>You can make changes to this file but if the server is running at the time you will need to run the update command
 * which is part of this object itself. You also need to pass in the {@link net.cubespace.Yamler.Config.ConfigSection} that
 * you changed. There is only 2 <strong>world</strong> or <strong>general</strong>.
 * <p>If you made the changes offline you don't need to worry.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class Worlds extends Config {

    @Path("world")
    private ConfigSection world = new ConfigSection();

    @Getter
    @Comment("Defaults settings for world creation")
    @Path("world.defaults")
    private HashMap<String, Object> defaults = new HashMap<>();

    @Getter
    @Setter
    @Comment("Name of the world")
    @Path("world.defaults.name")
    private String name = "";

    @Comment("WorldType FLAT, NORMAL etc")
    @Path("world.defaults.type")
    private String type = WorldType.FLAT.getName();

    @Getter
    @Setter
    @Comment("World generator to use Ipsum has a build in void generator")
    @Path("world.defaults.generator")
    private String generator = "";

    @Getter
    @Setter
    @Comment("Should the world generate structures")
    @Path("world.defaults.generatestructures")
    private Boolean generateStructures = false;

    @Comment("World environment normal, nether,the_end")
    @Path("world.defaults.environment")
    private String environment = World.Environment.NORMAL.name();

    @Getter
    @Setter
    @Comment("Seed the world is created from")
    @Path("world.defaults.seed")
    private long seed = 0l;

    @Getter
    @Setter
    @Comment("Enable decides if the world should be loaded")
    @Path("world.enable")
    private Boolean enable = true;

    @Getter
    @Setter
    @Comment("If true the world will be loaded and have all default settings applied")
    @Path("world.firsttime")
    private Boolean firstTime = true;

    @Comment("General world settings")
    @Path("general")
    private ConfigSection general = new ConfigSection();

    @Path("general.settings")
    private HashMap<String, Object> settings = new HashMap<>();
    @Getter
    @Setter
    @Path("general.settings.spawninmemory")
    private Boolean spawninmemory = true;
    @Getter
    @Setter
    @Path("general.settings.monsters")
    private Boolean monsters = false;

    @Getter
    @Setter
    @Path("general.settings.animals")
    private Boolean animals = true;

    @Getter
    @Setter
    @Path("general.settings.autosave")
    private Boolean autosave = true;

    @Getter
    @Setter
    @Path("general.settings.difficulty")
    private String difficulty = "HARD";

    @Getter
    @Setter
    @Path("general.settings.pvp")
    private Boolean pvp = true;

    @Getter
    @Setter
    @Path("general.settings.setstorm")
    private Boolean setstorm = false;

    @Getter
    @Setter
    @Path("general.settings.setthunder")
    private Boolean setthunder = false;

    @Getter
    @Setter
    @Path("general.settings.clearweather")
    private long clearweather = 100000l;

    @Getter
    @Setter
    @Path("general.settings.settime")
    private long settime = 6000l;

    @Getter
    @Setter
    @Path("general.settings.spawnBlockLocation")
    private Loc spawnBlockLocation = new Loc(0, 31, 0, 0, 0, name);

    @Getter
    @Setter
    @Path("general.settings.spawnLocation")
    private Loc spawnLocation = new Loc(0, 32, 0, 0, 0, name);

    @Path("general.settings.spawnBlock")
    private String spawnBlock = Material.GOLD_BLOCK.name();

    @Path("general.gamerules")
    private ConfigSection gamerules = new ConfigSection();
    @Path("general.gamerules.rule")
    private HashMap<String, Boolean> rule = new HashMap<>();

    @Getter
    @Setter
    @Path("general.gamerules.rule.dofireticks")
    private Boolean doFireTick = false;

    @Getter
    @Setter
    @Path("general.gamerules.rule.mobgriefing")
    private Boolean mobGriefing = false;

    @Getter
    @Setter
    @Path("general.gamerules.rule.lightcyle")
    private Boolean doDaylightCycle = false;

    @Getter
    @Setter
    @Path("general.gamerules.rule.blockoutput")
    private Boolean commandBlockOutput = false;


    public Worlds() {

    }

    public Worlds(Plugin plugin, String name) {

        CONFIG_FILE = new File(plugin.getDataFolder(), File.separator + "worlds" + File.separator + name + ".yml");
        CONFIG_HEADER = new String[]{"This stores the world details for world: " + name};

    }

    public World.Environment getEnvironment() {
        return World.Environment.valueOf(environment);
    }

    public void setEnvironment(World.Environment env) {

        this.environment = env.name();
    }

    public WorldType getWorldType() {

        return WorldType.valueOf(type);
    }

    public void setWorldType(WorldType worldType) {

        this.type = worldType.name();
    }

    public Material getSpawnBlock() {

        return Material.valueOf(spawnBlock);
    }

    public void setSpawnBlock(Material material) {

        spawnBlock = material.name();
    }

    public Map<String, Object> getWorldDefaults() {
        Map<String, Object> map = Maps.newHashMap();

        map.putAll(defaults);

        return map;


    }
}
