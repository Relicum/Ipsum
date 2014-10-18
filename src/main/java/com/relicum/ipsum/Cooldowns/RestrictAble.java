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

/**
 * RestrictAble is an interface that defines anything that has a runtime restriction associated with it connected to a player.
 * <p>While a permission is obviously a restriction this plugin will not take permissions into consideration as that is
 * the task of permission plugins.
 * <p>This could be anything from a cooldown or a warm up delay before the require task is run.
 * Part of the reason for this entire implementation is to reduce the need to keep track of all the various cooldown's
 * etc that your plugin may need. Its aim is to eliminated having a individual Runnable for everyone of these tasks which is
 * very common and highly inefficient.
 * <p>The user should be free to define their own logic and the system will keep track auto clean up while the user only needs to call
 * a single method {@link #isRestricted()} to check if the current action is currently under restriction.
 * <p>This plugin should be usable by multiple plugins at once using a simple API, without having to know how
 * its being implemented.
 * <p>This plugin itself will make extensive use of various concurrent async Objects without writing directly to the Bukkit API.
 * If that is required it will call the methods on the primary thread to keep everything in sync.The downside to this is a slight increase memory but will
 * give a drastic increase in overall performance if you require to track multiple warmups and cooldowns.
 * <p>This plugin will also make use of Java generics to enforce the type of Restriction possible while allowing new Restrictions to be added
 * by implementing certain class's/interfaces.
 * <p>What this plugin is NOT meant to be used for is a replacement for Bukkits built in scheduler or {@link org.bukkit.scheduler.BukkitRunnable} class.
 * This should still be used when appropriate, eg if all you require is a standard delayed task.
 * <p>Consideration is also being made that bukkit will not be around for ever and should be as easy as possible to adapt this plugin to other API's like sponge
 * that will take its place in the future.
 *
 * @author Relicum
 * @version 0.0.1
 */
public interface RestrictAble {

    /**
     * Is the current task being attempted marked as restricted in any way.
     *
     * @return true and the task currently has a restriction, false and it currently does not. The restriction is not forced so you are free to override it whenever you choose.
     */
    boolean isRestricted();

    /**
     * Gets a human readable message containing the relevant restriction that is currently active.
     *
     * @return the message
     */
    String getMessage();

    /**
     * Gets the remaining time in milliseconds that the cooldown has until it expires.
     *
     * @return the time remaining until the cooldown expires
     */
    long getDelay();
}
