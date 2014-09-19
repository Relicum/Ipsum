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

package com.relicum.ipsum.Minecraft;

import com.relicum.ipsum.Location.Locateable;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * BasePlayer defines some basic functionality to get details of a player.
 *
 * @author Relicum
 * @version 0.0.1
 */
public interface BasePlayer extends Locateable {

    /**
     * Get players Unique Id {@link java.util.UUID}
     *
     * @return the unique id of the player
     */
    public UUID getUniqueId();

    /**
     * Get the players current registered name
     *
     * @return the players name as a {@link java.lang.String}
     */
    public String getName();

    /**
     * Get player by unique id {@link java.util.UUID}
     *
     * @param uuid the uuid
     * @return the {@link org.bukkit.entity.Player}
     */
    public Player getPlayer(UUID uuid);

    /**
     * Get player by {@link String} this should not be used if doing a full lookup {@link org.bukkit.entity.Player} are indexed by {@link java.util.UUID} now
     *
     * @param name the {@link String} name of the player
     * @return the player
     */
    public Player getPlayer(String name);

    /**
     * Get the current {@link org.bukkit.Location} of the player
     *
     * @return the {@link org.bukkit.Location}
     */
    public Location getLocation();

    /**
     * Is the {@link org.bukkit.entity.Player} a server operator.
     *
     * @return true if they are an server operator false if not.
     */
    public boolean isOp();

}
