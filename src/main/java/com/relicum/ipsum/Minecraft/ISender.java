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

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Collection;

/**
 * ISender is a simple interface with basic default methods for sending messages.
 * <p>In its basic form just impliment this interface in any classes you want to send messages with
 * as its Java 8 default methods can be defined in the interface. But you can override them if you wish.
 *
 * @author Relicum
 */
public interface ISender {


    /**
     * Sends <strong>Standard</strong> message to a player.
     *
     * @param player  to receive the message
     * @param message the message itself
     * @param subs    if the message has placeholders '%s' then you pass the strings in this varargs
     */
    default void sendMessage(Player player, String message, String[] subs) {
        if (subs != null) {
            player.sendMessage(String.format(ChatColor.translateAlternateColorCodes('&', message), subs[0]));
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }

    /**
     * Send message.
     *
     * @param type    the type
     * @param player  the player
     * @param message the message
     * @param subs    the subs
     */
    default void sendMessage(MessageType type, Player player, String message, String[] subs) {

        if (type.equals(MessageType.STANDARD)) {
            sendMessage(player, message, new String[]{"subs"});
            return;
        }
        if (type.equals(MessageType.ERROR)) {
            sendErrorMessage(player, message, subs);
            return;
        }
        if (type.equals(MessageType.ADMIN)) {
            sendAdminMessage(player, message, subs);
        }

    }

    /**
     * Send message.
     *
     * @param type    the type
     * @param message the message
     * @param subs    the subs
     */
    default void sendMessage(MessageType type, String message, String... subs) {
        if (type.equals(MessageType.BROADCAST)) {
            sendBroadcast(message, subs);

        }
    }

    /**
     * Send admin message.
     *
     * @param player  the player
     * @param message the message
     * @param subs    the subs
     */
    default void sendAdminMessage(Player player, String message, String[] subs) {

    }

    /**
     * Send admin message.
     *
     * @param players the players
     * @param message the message
     * @param subs    the subs
     */
    default void sendAdminMessage(Collection<? extends Player> players, String message, String[] subs) {

    }

    /**
     * Send error message.
     *
     * @param player  the player
     * @param message the message
     * @param subs    the subs
     */
    default void sendErrorMessage(Player player, String message, String[] subs) {


    }

    /**
     * Send broadcast.
     *
     * @param message the message
     * @param subs    the subs
     */
    default void sendBroadcast(String message, String[] subs) {

    }
}
