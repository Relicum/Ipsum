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
import lombok.ToString;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * WorldConfig used to store data for world management.
 *
 * @author Relicum
 * @version 0.0.1
 */
@Getter
@ToString
@SerializableAs("WorldConfig")
public class WorldConfig extends AbstractSerializable implements ConfigurationSerializable {


    private Boolean enable;
    private Boolean standAlone;
    private Boolean handleFirstSpawn;
    private Boolean handleAllSpawns;
    private Boolean handleNoSpawns;
    private Boolean removeEnd;
    private Boolean removeNether;
    private Boolean handleWorldChat;
    private Boolean isVoidWorld;
    private Boolean realTimeWorldLoading;
    private Boolean restartAfterGame;
    private List<String> worldsEnabled;


    public WorldConfig() {
        if (worldsEnabled == null) {
            worldsEnabled = new ArrayList<>();
        }
    }

    public WorldConfig(Map<String, Object> args) {
        super(args);
        if (worldsEnabled == null) {
            worldsEnabled = new ArrayList<>();
        }
    }

    public WorldConfig addWorld(String world) {
        worldsEnabled.add(world);
        return this;
    }


    public WorldConfig setEnable(Boolean enable) {
        this.enable = enable;
        return this;
    }

    public WorldConfig setStandAlone(Boolean standAlone) {
        this.standAlone = standAlone;
        return this;
    }

    public WorldConfig setHandleFirstSpawn(Boolean handleFirstSpawn) {
        this.handleFirstSpawn = handleFirstSpawn;
        return this;
    }

    public WorldConfig setHandleAllSpawns(Boolean handleAllSpawns) {
        this.handleAllSpawns = handleAllSpawns;
        return this;
    }

    public WorldConfig setHandleNoSpawns(Boolean handleNoSpawns) {
        this.handleNoSpawns = handleNoSpawns;
        return this;
    }

    public WorldConfig setRemoveEnd(Boolean removeEnd) {
        this.removeEnd = removeEnd;
        return this;
    }

    public WorldConfig setRemoveNether(Boolean removeNether) {
        this.removeNether = removeNether;
        return this;
    }

    public WorldConfig setHandleWorldChat(Boolean handleWorldChat) {
        this.handleWorldChat = handleWorldChat;
        return this;
    }

    public WorldConfig setIsVoidWorld(Boolean isVoidWorld) {
        this.isVoidWorld = isVoidWorld;
        return this;
    }

    public WorldConfig setRealTimeWorldLoading(Boolean realTimeWorldLoading) {
        this.realTimeWorldLoading = realTimeWorldLoading;
        return this;
    }

    public WorldConfig setRestartAfterGame(Boolean restartAfterGame) {
        this.restartAfterGame = restartAfterGame;
        return this;
    }


}
