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

import com.relicum.ipsum.Location.PointsGroup;
import com.relicum.ipsum.Location.SpawnCollection;
import com.relicum.ipsum.Location.SpawnPoint;
import com.relicum.ipsum.Scoreboards.SimpleScoreBoardHandler;
import com.relicum.ipsum.Utils.CustomSound;
import net.minecraft.util.org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * AbstractGame Class to define a game using different states to automate the management of the game.
 *
 * @author Relicum
 * @version 0.0.1
 */
public abstract class AbstractGame {

    private GameState state;

    private SpawnCollection<String, String, SpawnPoint> spawnLocations = new SpawnCollection<>();


    private Map<UUID, Gamer> gamers = new HashMap<>();

    //private SimpleScoreBoardHandler scoreBoardHandler;

    private String countDownMessage;
    private String startGameMessage;

    private Plugin plugin;

    private BukkitTask countDown;

    public AbstractGame(Plugin plugin, String name, SimpleScoreBoardHandler boardHandler) {
        this.plugin = plugin;
        this.state = GameState.DISABLED;
        //this.scoreBoardHandler = boardHandler;
    }

    /**
     * Update the game state this will automatically trigger the relevant method to change the game state itself.
     *
     * @param state the {@link com.relicum.ipsum.Game.GameState} that the game is to be updated to.
     */
    public void updateState(GameState state) {
        this.state = state;
        update();
    }

    private void update() {

        if (state.equals(GameState.LOADING)) {
            doLoading();
        }
        if (state.equals(GameState.INGAME)) {
            doStart();
        }

        if (state.equals(GameState.WAITING)) {
            doWaiting();
        }

        if (state.equals(GameState.DISABLED)) {
            doDisable();
        }

        if (state.equals(GameState.RESTART)) {
            doRestart();
        }

        if (state.equals(GameState.ENDED)) {
            doEnded();
        }
    }


    /**
     * This state is used to setup and load a game, when complete players should be able to join the game.
     * <p>You should update the {@link com.relicum.ipsum.Game.GameState} to <strong>WAITING</strong> once everything is
     * loaded using the {@link #updateState(com.relicum.ipsum.Game.GameState)} method.
     */
    public abstract void doLoading();

    /**
     * While in the waiting state the game should accept players and update any sign displays.
     * <p>When the start condition has been met a countdown should be displayed to the players.
     * On completion of the countdown the {@link com.relicum.ipsum.Game.GameState} should be updated the
     * <strong>INGAME</strong> using the {@link #updateState(com.relicum.ipsum.Game.GameState)} method.
     */
    public abstract void doWaiting();

    /**
     * Do start.
     */
    public abstract void doStart();

    /**
     * Do restart.
     */
    public abstract void doRestart();

    /**
     * Do disable.
     */
    public abstract void doDisable();

    /**
     * Do ended.
     */
    public abstract void doEnded();

    /**
     * Gets the current game state the game is in.
     *
     * @return the state {@link com.relicum.ipsum.Game.GameState}
     */
    public GameState getState() {
        return this.state;
    }

    public void setupGame() {
        plugin.getLogger().info("Starting game setup");


    }

    public void addPlayerToGame(Gamer playerData) {
        Validate.notNull(playerData);
        //playerData.setScore(scoreBoardHandler.getObjective().getScore(ChatColor.GOLD + playerData.getName()));

        gamers.put(playerData.getPlayer().getUniqueId(), playerData);


    }


    public void removePlayer(UUID uuid) {
        gamers.get(uuid).deleteScore();

        gamers.remove(uuid);
    }

    public boolean isInGame(UUID uuid) {

        return gamers.containsKey(uuid);
    }


    /**
     * Gets gamers.
     *
     * @return the gamers
     */
    public Map<UUID, Gamer> getGamers() {
        return this.gamers;
    }

    /**
     * Gets score board handler.
     *
     * @return the score board handler
     */
    public SimpleScoreBoardHandler getScoreBoardHandler() {
        return null;
    }

    /**
     * Gets plugin.
     *
     * @return the plugin
     */
    public Plugin getPlugin() {
        return this.plugin;
    }

    public void runCountdown(String message, String startMessage) {

        countDown = new BukkitRunnable() {

            long total = 220;

            public String updateMessage() {

                return message.replace("%time%", String.valueOf(total / 20));
            }

            @Override
            public void run() {
                total -= 20;
                plugin.getServer().broadcastMessage(updateMessage());
                // plugin.getServer().broadcastMessage(I18N.altFormat(I18N.STRING("game.broadcast.countdown", (total / 20))));
                if (total < 1) {

                    Bukkit.getOnlinePlayers().forEach(p -> p.playSound(p.getLocation(), CustomSound.PREPARE_TO_FIGHT.getSound(), 3.0f, 1.0f));
                    Bukkit.broadcastMessage(startMessage);
                    updateState(GameState.INGAME);
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 200l, 20l);
    }

    /**
     * Sets the SpawnCollection {@link com.relicum.ipsum.Location.SpawnCollection} object.
     * <p>This is most like only set if you deserialize the entire object, this method allow you to add all the games spawns
     * at once.
     *
     * @param spawnLocations new instance of {@link com.relicum.ipsum.Location.SpawnCollection} to set.
     */
    public void setSpawnLocations(SpawnCollection<String, String, SpawnPoint> spawnLocations) {
        this.spawnLocations = spawnLocations;
    }

    /**
     * Get the SpawnCollection object {@link com.relicum.ipsum.Location.SpawnCollection}
     *
     * @return the SpawnLocation Collection
     */
    public SpawnCollection<String, String, SpawnPoint> getSpawnLocations() {
        return spawnLocations;
    }

    /**
     * Add PointsGroup {@link com.relicum.ipsum.Location.PointsGroup} to the Collection of spawn groups {@link com.relicum.ipsum.Location.SpawnCollection}
     *
     * @param group      the name used to identify the PointsGroup
     * @param spawnGroup the {@link com.relicum.ipsum.Location.PointsGroup} that is to be added
     */
    public void addSpawnGroup(String group, PointsGroup<String, SpawnPoint> spawnGroup) {
        this.spawnLocations.addPointsGroup(group, spawnGroup);
    }

    /**
     * Get a Points group from the collection
     *
     * @param group the name used to identify the PointsGroup
     * @return the points group {@link com.relicum.ipsum.Location.PointsGroup}
     */
    public PointsGroup<String, SpawnPoint> getSpawnGroup(String group) {
        return spawnLocations.getGroup(group);
    }

}
