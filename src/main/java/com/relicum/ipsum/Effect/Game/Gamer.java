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

package com.relicum.ipsum.Effect.Game;

import com.relicum.ipsum.Configuration.GameRanks;
import com.relicum.ipsum.Configuration.PlayerData;
import net.minecraft.util.org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;

import java.util.UUID;

/**
 * Name: Gamer.java Created: 13 September 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class Gamer {

    private final PlayerData playerData;

    private Score score;

    private int scoreCount = 0;

    private Location nextSpawn;


    /**
     * Instantiates a new Gamer.
     *
     * @param data players instance of {@link com.relicum.ipsum.Configuration.PlayerData}
     */
    protected Gamer(final PlayerData data) {
        this.playerData = data;


    }


    /**
     * Gets display name as stored in {@link com.relicum.ipsum.Configuration.PlayerData}
     *
     * @return the display name
     */
    public String getDisplayName() {

        return this.playerData.getDisplayName();
    }

    /**
     * Gets player.
     *
     * @return the {@link org.bukkit.entity.Player}
     */
    public Player getPlayer() {
        return this.playerData.getPlayer();
    }

    /**
     * Gets uuid of the player.
     *
     * @return the uuid of the player
     */
    public UUID getUuid() {
        return this.playerData.getPlayer().getUniqueId();
    }

    /**
     * Gets player name as stored in {@link com.relicum.ipsum.Configuration.PlayerData}
     *
     * @return the name
     */
    public String getName() {
        return this.playerData.getName();
    }

    /**
     * Returns if the player is an op
     *
     * @return the boolean
     */
    public boolean isOP() {
        return this.playerData.getPlayer().isOp();
    }

    /**
     * Gets {@link com.relicum.ipsum.Configuration.GameRanks} of the player.
     *
     * @return the {@link com.relicum.ipsum.Configuration.GameRanks} of the player
     */
    public GameRanks getRank() {
        return GameRanks.valueOf(this.playerData.getRank());
    }

    /**
     * Gets the current {@link org.bukkit.scoreboard.Score} belonging to the player
     *
     * @return the {@link org.bukkit.scoreboard.Score}
     */
    public Score getScore() {
        return this.score;
    }

    /**
     * Sets the players {@link org.bukkit.scoreboard.Score}.
     *
     * @param score the {@link org.bukkit.scoreboard.Score} object representing the player.
     */
    public void setScore(Score score) {
        Validate.notNull(score, "Set score can not be null on Gamer");
        this.score = score;
    }

    /**
     * Increment the players {@link org.bukkit.scoreboard.Score} object by 1.
     */
    public void addToScore() {
        scoreCount++;
        this.score.setScore(scoreCount);
    }

    /**
     * Set {@link org.bukkit.scoreboard.Score} to -1 and set players scoreboard to main scoreboard
     */
    public void deleteScore() {
        this.score.setScore(-1);
        getPlayer().setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    }

    /**
     * Teleport player to next spawn point set.
     */
    public void teleportNextSpawn() {
        Validate.notNull(this.nextSpawn, "Unable to send player to next spawn as its not been set");
        getPlayer().teleport(this.nextSpawn);
    }

    public void teleportPlayer(Location location) {
        Validate.notNull(location, "Can not teleport player as location is null");
        getPlayer().teleport(location);
    }

    /**
     * Gets player data.
     *
     * @return the player data
     */
    public PlayerData getPlayerData() {
        return this.playerData;
    }

    /**
     * Gets score count.
     *
     * @return the score count
     */
    public int getScoreCount() {
        return this.scoreCount;
    }

    /**
     * Gets next spawn point the player will go to.
     *
     * @return the next spawn {@link org.bukkit.Location}
     */
    public Location getNextSpawn() {
        return this.nextSpawn;
    }

    public void setScoreCount(int scoreCount) {
        this.scoreCount = scoreCount;
    }

    /**
     * Sets next spawn point the player will go to.
     *
     * @param nextSpawn the next spawn {@link org.bukkit.Location}
     */
    public void setNextSpawn(Location nextSpawn) {
        this.nextSpawn = nextSpawn;
    }
}
