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

import com.relicum.ipsum.Location.Locateable;
import com.relicum.ipsum.Location.SpawnPoint;
import com.relicum.ipsum.io.SaveAble;

import java.util.ArrayList;
import java.util.List;

/**
 * PersistentPlayer holds the basic player data that will be persisted to disk.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class PersistentPlayer implements SaveAble, Locateable {

    private String uuid;

    private String name;

    private String lastName = "";

    private String rank;

    private boolean Op;

    private long lastSeen = 0l;

    private long firstSeen = 0l;

    private SpawnPoint location;

    private List<String> knownNames = new ArrayList<>();

    /**
     * Instantiates a new Persistent player.
     */
    public PersistentPlayer() {

        this.lastSeen = System.currentTimeMillis();
    }

    /**
     * Instantiates a new Persistent player, this is mainly the start of a data abstraction layer.
     *
     * @param uuid     the {@link java.util.UUID} of the player in a string format.
     * @param name     the IGN of the player
     * @param op       set true if the player is an op
     * @param location the {@link com.relicum.ipsum.Location.SpawnPoint} last know player location.
     * @param rank     the {@link com.relicum.ipsum.Configuration.GameRanks} the player belongs to.
     */
    public PersistentPlayer(String uuid, String name, boolean op, SpawnPoint location, GameRanks rank) {
        this.uuid = uuid;
        this.name = name;
        if (!knownNames.contains(name)) {
            knownNames.add(name);
        }
        if (!(name.equals(lastName))) {
            lastName = name;
        }
        this.rank = rank.name();
        Op = op;
        if (firstSeen == 0) {
            this.firstSeen = System.currentTimeMillis();
            this.lastSeen = System.currentTimeMillis();
        }

        this.lastSeen = System.currentTimeMillis();
        this.location = location;
    }


    /**
     * Gets firstSeen.
     *
     * @return Value of firstSeen.
     */
    public long getFirstSeen() {
        return firstSeen;
    }

    /**
     * Gets lastSeen.
     *
     * @return Value of lastSeen.
     */
    public long getLastSeen() {
        return lastSeen;
    }

    /**
     * Gets Op.
     *
     * @return Value of Op.
     */
    public boolean isOp() {
        return Op;
    }

    /**
     * Gets location.
     *
     * @return Value of location.
     */
    public SpawnPoint getLocation() {
        return location;
    }

    /**
     * Gets uuid.
     *
     * @return Value of uuid.
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets new location.
     *
     * @param location New value of location.
     */
    public void setLocation(SpawnPoint location) {
        this.location = location;
    }

    /**
     * Gets lastName.
     *
     * @return Value of lastName.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets new lastSeen.
     *
     * @param lastSeen New value of lastSeen.
     */
    public void setLastSeen(long lastSeen) {
        this.lastSeen = lastSeen;
    }

    /**
     * Sets new Op.
     *
     * @param Op New value of Op.
     */
    public void setOp(boolean Op) {
        this.Op = Op;
    }

    /**
     * Sets new uuid.
     *
     * @param uuid New value of uuid.
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Sets new firstSeen.
     *
     * @param firstSeen New value of firstSeen.
     */
    public void setFirstSeen(long firstSeen) {
        this.firstSeen = firstSeen;
    }

    /**
     * Sets new rank.
     *
     * @param rank New value of rank.
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * Gets rank.
     *
     * @return Value of rank.
     */
    public String getRank() {
        return rank;
    }

    /**
     * Sets new lastName.
     *
     * @param lastName New value of lastName.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets name.
     *
     * @return Value of name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets new name.
     *
     * @param name New value of name.
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets known player names.
     *
     * @return list of know players this player has used.
     */
    public List<String> getKnownNames() {
        return knownNames;
    }

    /**
     * Sets new knownNames.
     *
     * @param name New value of knownNames.
     */
    public void setKnownName(String name) {
        if (!knownNames.contains(name)) {
            knownNames.add(name);
        }
    }

}
