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

package com.relicum.ipsum.Minecraft.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * PlayerPostJoinEvent is fired 20 ticks after the PlayerJoinEvent is fired.
 * <p>This is very useful if you wish to give the player items or kits and need to be
 * sure the player has fully logged in as trying to alter the players inventory before they
 * logged in it cause many problems. This will also reduce lag a little as a lot of plugins use
 * the {@link org.bukkit.event.player.PlayerJoinEvent} and this allows a little cooldown.
 *
 * @author Relicum
 * @version 0.0.1
 */
@SuppressWarnings("CanBeFinal")
public class PlayerPostLoginEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;

    /**
     * The default constructor is defined for cleaner code. This constructor
     * assumes the event is synchronous.
     *
     * @param player the player
     */
    public PlayerPostLoginEvent(Player player) {
        this.player = player;
    }

    /**
     * This constructor is used to explicitly declare an event as synchronous
     * or asynchronous.
     *
     * @param isAsync true indicates the event will fire asynchronously, false
     *                by default from default constructor
     * @param player  the player
     */
    public PlayerPostLoginEvent(boolean isAsync, Player player) {
        super(isAsync);
        this.player = player;
    }


    /**
     * Gets handler list.
     *
     * @return the handler list
     */
    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * Gets handlers.
     *
     * @return the handlers
     */
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    /**
     * Gets the player that has just logged in.
     *
     * @return the {@link org.bukkit.entity.Player}
     */
    public Player getPlayer() {
        return player;
    }
}
