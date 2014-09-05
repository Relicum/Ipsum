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

package com.relicum.ipsum.Locale;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Name: IMessage.java Created: 29 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public interface IMessage {


    /**
     * Send command raw message.
     *
     * @param sender  the @{@link org.bukkit.command.CommandSender} sender
     * @param message the {@link java.lang.String} message
     */
    static void sendRawMessage(CommandSender sender, String message) {

        sender.sendMessage(message);
    }

    default void sendRawMessage(CommandSender sender, String[] messages) {

        sender.sendMessage(messages);
    }

    default void sendMessage(CommandSender sender, String[] messages) {

        for (String s : messages) {
            sender.sendMessage(format(s));
        }

    }

    /**
     * Send command message.
     *
     * @param sender  the @{@link org.bukkit.command.CommandSender} sender
     * @param message the {@link java.lang.String} message
     */
    default void sendMessage(CommandSender sender, String message) {

        sender.sendMessage(format(message));
    }

    /**
     * Send command error message.
     *
     * @param sender  the @{@link org.bukkit.command.CommandSender} sender
     * @param message the {@link java.lang.String} message
     */
    default void sendErrorMessage(CommandSender sender, String message) {

        sender.sendMessage(errorFormat(message));
    }

    /**
     * Send command admin message.
     *
     * @param sender  the @{@link org.bukkit.command.CommandSender} sender
     * @param message the {@link java.lang.String} message
     */
    default void sendAdminMessage(CommandSender sender, String message) {

        sender.sendMessage(adminFormat(message));
    }

    /**
     * Format string.
     *
     * @param message the message
     * @return the string formatted with getPrefix(), message color and converts any other color codes in message
     */
    default String format(String message) {

        return addColor(getPrefix() + getInfoColor() + message);
    }

    /**
     * Format string.
     *
     * @param name    the name to use in prefix
     * @param message the message
     * @return the string formatted with prefix with custom name, message color and converts any other color codes in message
     */
    default String format(String name, String message) {

        return addColor("&5[&b" + name + "&5] " + message);
    }

    /**
     * Error format.
     *
     * @param message the message
     * @return the string
     */
    default String errorFormat(String message) {

        return addColor(getPrefix() + getErrorColor() + message);
    }

    /**
     * Admin format.
     *
     * @param message the message
     * @return the string
     */
    default String adminFormat(String message) {

        return addColor(getPrefix() + getAdminColor() + message);
    }

    /**
     * Log to console
     *
     * @param message the {@link java.lang.Object} message
     */
    default void log(Object message) {

        System.out.println(message);
    }

    /**
     * Remove color.
     *
     * @param message the message
     * @return the {@link java.lang.String} that has color removed
     */
    default String removeColor(String message) {

        return ChatColor.stripColor(message);
    }

    /**
     * Display a debug message in game. Will only log if debug is set to true in config.yml
     *
     * @param sender  the sender
     * @param message the message
     */
    default void debugGameMessage(CommandSender sender, String message) {


        sender.sendMessage(addColor(getDebugPrefix()) + addColor(message) + addColor("&r"));


    }

    /**
     * Convert color. Shade from Bukkit Api
     *
     * @param s {@link String} {@link String} the text to convert color chars to correct format
     * @return the {@link String} line of text which has color formatted
     */
    default String addColor(String s) {

        char[] b = s.toCharArray();
        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == getSAFE_CHAR() && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1) {
                b[i] = getCOLOR_CHAR();
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }

        }
        return new String(b);
    }

    /**
     * Convert color. Shade from Bukkit Api
     *
     * @param s {@link String} {@link String} the text to convert color chars to correct format
     * @return the {@link String} line of text which has color formatted
     */
    default String addAltColor(String s) {

        char[] b = s.toCharArray();
        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == getCOLOR_CHAR() && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1) {
                b[i] = getSAFE_CHAR();
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }

        }
        return new String(b);
    }

    default void clearChat(Player player) {

        for (int i = 0; i < 100; i++) {
            player.sendMessage("");
        }

    }

    default void clearChat(Player player, int lines) {

        for (int i = 0; i < lines; i++) {
            player.sendMessage("");
        }

    }

    /**
     * Gets prefix.
     *
     * @return Value of prefix.
     */
    String getPrefix();


    /**
     * Gets infoColor.
     *
     * @return Value of infoColor.
     */
    String getInfoColor();

    /**
     * Gets adminColor.
     *
     * @return Value of adminColor.
     */
    String getAdminColor();


    /**
     * Gets errorColor.
     *
     * @return Value of errorColor.
     */
    String getErrorColor();


    /**
     * Gets debugPrefix.
     *
     * @return Value of debugPrefix.
     */
    String getDebugPrefix();

    /**
     * Gets COLOR_CHAR.
     *
     * @return Value of COLOR_CHAR.
     */
    char getCOLOR_CHAR();

    /**
     * Gets SAFE_CHAR.
     *
     * @return Value of SAFE_CHAR.
     */
    char getSAFE_CHAR();
}
