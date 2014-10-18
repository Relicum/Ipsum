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

package com.relicum.ipsum.Cooldowns;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * CoolDownManager manages player cooldowns in an efficient way, it is designed for dealing with a high volume of player cooldowns concurrently.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class CoolDownManager {


    private ConcurrentMap<UUID, PlayerCooldowns> cooldowns;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private Random random = new Random();

    private CoolDownManager() {

        cooldowns = new ConcurrentHashMap<>();

        startScheduler();

    }

    /**
     * Create a new {@link com.relicum.ipsum.Cooldowns.CoolDownManager}
     *
     * @return the new {@link com.relicum.ipsum.Cooldowns.CoolDownManager}
     */
    public static CoolDownManager createCoolDownManager() {
        return new CoolDownManager();
    }


    public void startScheduler() {

        scheduler.scheduleWithFixedDelay(new Expire(), 4, 2, TimeUnit.SECONDS);
    }

    /**
     * Add a new cooldown for a player, this method checks to see if they need to have a new {@link com.relicum.ipsum.Cooldowns.PlayerCooldowns} created first.
     * <p>If no {@link com.relicum.ipsum.Cooldowns.PlayerCooldowns} is found for the player it is created first before adding the cooldown. You should use this
     * method if you are unsure if the player already has a {@link com.relicum.ipsum.Cooldowns.PlayerCooldowns} created.
     *
     * @param uuid  the uuid of the player
     * @param delay the delay in milliseconds the cooldown should last for
     * @return the integer ID that needs to be used for any future reference to this cooldown.
     */
    public Integer addNewCooldown(UUID uuid, long delay) {

        Integer tid = getRandomInt();

        if (!contains(uuid)) {

            createNewPlayerStore(uuid);

        }
        cooldowns.get(uuid).addCoolDown(tid, delay);

        return tid;
    }

    /**
     * Add a new cooldown for a player, this method makes the assumption the player already has a {@link com.relicum.ipsum.Cooldowns.PlayerCooldowns} created.
     * <p>If no {@link com.relicum.ipsum.Cooldowns.PlayerCooldowns} is found for the player, any exception is thrown and caught internally.You should use this
     * method if you are SURE the player already has a {@link com.relicum.ipsum.Cooldowns.PlayerCooldowns} created.
     *
     * @param uuid  the uuid of the player
     * @param delay the delay in milliseconds the cooldown should last for
     * @return the integer ID that needs to be used for any future reference to this cooldown.
     */
    public Integer addCooldown(UUID uuid, long delay) {
        Integer tid = getRandomInt();

        try {
            cooldowns.get(uuid).addCoolDown(tid, delay);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tid;
    }

    /**
     * Create new {@link com.relicum.ipsum.Cooldowns.PlayerCooldowns} for a player.
     * <p>This is useful if you know the player will be having cooldowns applied to pre create this object.
     *
     * @param uuid the uuid of the player
     * @return true if the {@link com.relicum.ipsum.Cooldowns.PlayerCooldowns} was created successfully. Possible chance of this returning null.
     */
    public boolean createNewPlayerStore(UUID uuid) {

        return cooldowns.putIfAbsent(uuid, PlayerCooldowns.createNew()) != null;
    }

    /**
     * Checks if there is a record already for the players UUID which the cooldown is being registered against.
     * <p>You DO NOT need to use this when creating or checking cooldown status as internal the check is made for you.
     * The method is public in case you have any other reason you might want to check.
     *
     * @param uuid the uuid of the player
     * @return true if the player already has a record, false if they don't currently have a record.
     */
    public boolean contains(UUID uuid) {

        return cooldowns.containsKey(uuid);
    }


    public boolean isRestricted(UUID uuid, Integer id) {

        return cooldowns.get(uuid).isRestricted(id);
    }

    public String getMessage(UUID uuid, Integer id) {

        return cooldowns.get(uuid).getMessage(id);
    }

    public long getDelay(UUID uuid, Integer id) {

        return cooldowns.get(uuid).getDelay(id);
    }

    public long getEnd(UUID uuid, Integer id) {

        return cooldowns.get(uuid).getEnd(id);
    }

    /**
     * Remove player from the {@link com.relicum.ipsum.Cooldowns.CoolDownManager} should be called in onQuit.
     *
     * @param uuid the uuid of the player
     */
    public void removePlayer(UUID uuid) {
        if (contains(uuid))
            cooldowns.remove(uuid);
    }


    /**
     * Get the {@link com.relicum.ipsum.Cooldowns.PlayerCooldowns} for the specified {@link java.util.UUID}
     *
     * @param uuid the uuid of the player
     * @return the {@link com.relicum.ipsum.Cooldowns.PlayerCooldowns} if it a match is found or null if not.
     */
    public PlayerCooldowns getPlayerStore(UUID uuid) {

        if (contains(uuid))
            return cooldowns.get(uuid);
        else {
            return null;
        }

    }

    /**
     * Get random ID which is used for the key of the cool down.
     *
     * @return the integer
     */
    public Integer getRandomInt() {

        return random.nextInt();
    }

    /**
     * Shutdown the scheduler, this should be called in the plugins {@link org.bukkit.plugin.Plugin#onDisable()} method.
     *
     * @return true if there were no errors shutting down.
     */
    public boolean shutdown() {
        System.out.println("Shutting down Cooldowns executor");
        scheduler.shutdown();
        try {
            scheduler.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cooldowns.clear();

        return true;
    }

    public int getSize() {

        return cooldowns.size();
    }

    private class Expire implements Runnable {


        @Override
        public void run() {
            System.out.println("Attempting to remove expired cooldowns map size is " + getSize());
            cooldowns.values().forEach(PlayerCooldowns::removeAllExpired);
        }
    }

}
