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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * PlayerCooldowns is an object that holds an unlimited number of cooldowns related to a player.
 * <p>The cooldown itself is non specific and can be used for any task. While you can use this on its onw it is designed
 * to be used with {@link com.relicum.ipsum.Cooldowns.PlayerCooldowns}
 *
 * @author Relicum
 * @version 0.0.1
 */
public class PlayerCooldowns {

    private final Map<Integer, Cooldown> coolMap;

    private PlayerCooldowns() {
        coolMap = Collections.synchronizedMap(new HashMap<>());

    }

    /**
     * Create new a new instance of {@link com.relicum.ipsum.Cooldowns.PlayerCooldowns} and returns it to you.
     *
     * @return the player cooldowns
     */
    public static PlayerCooldowns createNew() {
        return new PlayerCooldowns();
    }

    /**
     * Is the player restricted by a cooldown connected to the specified id.
     *
     * @param id the unique id of the cooldown
     * @return true and the player is still restricted by the cool down. False and they aren't restricted.
     */
    public boolean isRestricted(Integer id) {
        return coolMap.containsKey(id) && coolMap.get(id).isRestricted();

    }

    /**
     * Get human readable message related to the cooldown connected to the specified id.
     *
     * @param id the unique id of the cooldown
     * @return the message
     */
    public String getMessage(Integer id) {

        return coolMap.get(id).getMessage();
    }

    /**
     * Get the time in milliseconds including the delay added on to create the cooldown
     *
     * @param id the unique id of the cooldown
     * @return the long
     */
    public long getDelay(Integer id) {

        return coolMap.get(id).getDelay();
    }

    /**
     * Add cool down. The param end is badly worded, it is actually the length in milliseconds the cooldown should be.
     *
     * @param id  the unique id of the cooldown
     * @param end the length of the cooldown in milliseconds.
     */
    public void addCoolDown(Integer id, Long end) {

        coolMap.putIfAbsent(id, Cooldown.createCooldown(end));

    }

    public long getEnd(Integer id) {

        return coolMap.get(id).getEnd();
    }

    public void removeById(Integer id) {

        coolMap.remove(id);
    }

    public Cooldown getCooldown(Integer id) {

        return coolMap.get(id);
    }

    /**
     * Remove all expired cooldowns.
     */
    public void removeAllExpired() {
        System.out.println("Map size is " + coolMap.size());
        boolean res = coolMap.entrySet().removeIf(p -> (p.getValue().getEnd() < System.currentTimeMillis()));
        if (res)
            System.out.println("Some cooldowns have been removed");
    }

    public void viewMap() {
        System.out.println("Total Cool downs is currently" + coolMap.size());
        System.out.println(" ");
        for (Map.Entry<Integer, Cooldown> entry : coolMap.entrySet()) {

            System.out.println("ID = " + entry.getKey() + " Cooldown start was " + entry.getValue().getEnd() + " remaining is " + entry.getValue().getDelay());
        }
    }
}
