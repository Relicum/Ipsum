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

package com.relicum.ipsum.Game;

import com.relicum.ipsum.Configuration.GameRanks;
import com.relicum.ipsum.Configuration.RankMap;
import com.relicum.ipsum.Location.PointsGroup;
import com.relicum.ipsum.Location.SpawnPoint;
import net.minecraft.util.org.apache.commons.lang3.Validate;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;

/**
 * Name: GamerData.java Created: 13 September 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class GamerData {


    private String uniqueId = "";

    private String name = "";

    private String lastName = "";

    private String rank = "";

    private String rankColor = "";

    private String displayName = "";

    private String playerListName = "";

    private long lastSeen = 0l;

    private long firstSeen = 0l;

    private ArrayList<String> knowAliases = new ArrayList<>();

    private PointsGroup<String, SpawnPoint> gamePointGroup = new PointsGroup<>();

    public GamerData() {
        this.lastSeen = System.currentTimeMillis();
    }

    public GamerData(String uniqueId, String name, String lastName, String rank, String rankColor, String displayName, String playerListName, long lastSeen, long firstSeen, ArrayList<String> knowAliases) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.lastName = lastName;
        this.rank = rank;
        this.rankColor = rankColor;
        this.displayName = displayName;
        this.playerListName = playerListName;

        if (lastSeen == 0l) {
            this.lastSeen = lastSeen;
            this.firstSeen = firstSeen;
        }
        this.lastSeen = lastSeen;
        this.knowAliases = knowAliases;
    }

    public GamerData(String uniqueId, String name) {
        Validate.notNull(uniqueId);
        Validate.notNull(name);

        if (lastSeen == 0l) {
            this.lastSeen = System.currentTimeMillis();
            this.firstSeen = System.currentTimeMillis();
        }

        this.lastSeen = System.currentTimeMillis();
        rank = GameRanks.MVP.toString();
        rankColor = String.valueOf(RankMap.getFormat(GameRanks.MVP));
        this.uniqueId = uniqueId;
        this.name = name;
        this.lastName = name;
        this.playerListName = rankColor + name;
        this.displayName = rankColor + rank + name;
    }

    /**
     * Sets new displayName.
     *
     * @param displayName New value of displayName.
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets playerListName.
     *
     * @return Value of playerListName.
     */
    public String getPlayerListName() {
        return playerListName;
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
     * Sets new lastSeen.
     *
     * @param lastSeen New value of lastSeen.
     */
    public void setLastSeen(long lastSeen) {
        this.lastSeen = lastSeen;
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
     * Gets firstSeen.
     *
     * @return Value of firstSeen.
     */
    public long getFirstSeen() {
        return firstSeen;
    }

    /**
     * Gets knowAliases.
     *
     * @return Value of knowAliases.
     */
    public ArrayList<String> getKnowAliases() {
        return knowAliases;
    }

    /**
     * Sets new knowAliases.
     *
     * @param knowAliases New value of knowAliases.
     */
    public void setKnowAliases(ArrayList<String> knowAliases) {
        this.knowAliases = knowAliases;
    }

    /**
     * Sets new playerListName.
     *
     * @param playerListName New value of playerListName.
     */
    public void setPlayerListName(String playerListName) {
        this.playerListName = playerListName;
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
     * Gets lastSeen.
     *
     * @return Value of lastSeen.
     */
    public long getLastSeen() {
        return lastSeen;
    }

    /**
     * Gets displayName.
     *
     * @return Value of displayName.
     */
    public String getDisplayName() {
        return displayName;
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
     * Sets new rank.
     *
     * @param rank New value of rank.
     */
    public void setRank(String rank) {
        this.rank = rank;
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
     * Gets lastName.
     *
     * @return Value of lastName.
     */
    public String getLastName() {
        return lastName;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uniqueId", uniqueId)
                .append("name", name)
                .append("lastName", lastName)
                .append("rank", rank)
                .append("rankColor", rankColor)
                .append("displayName", displayName)
                .append("playerListName", playerListName)
                .append("lastSeen", lastSeen)
                .append("firstSeen", firstSeen)
                .append("knowAliases", knowAliases)
                .toString();
    }

    /**
     * Sets new gamePointGroup.
     *
     * @param gamePointGroup New value of gamePointGroup.
     */
    public void setGamePointGroup(PointsGroup<String, SpawnPoint> gamePointGroup) {
        this.gamePointGroup = gamePointGroup;
    }

    /**
     * Gets gamePointGroup.
     *
     * @return Value of gamePointGroup.
     */
    public PointsGroup<String, SpawnPoint> getGamePointGroup() {
        return gamePointGroup;
    }

    /**
     * Gets rankColor.
     *
     * @return Value of rankColor.
     */
    public String getRankColor() {
        return rankColor;
    }

    /**
     * Gets uniqueId.
     *
     * @return Value of uniqueId.
     */
    public String getUniqueId() {
        return uniqueId;
    }
}
