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

import com.relicum.ipsum.Annotations.NonNls;
import com.relicum.ipsum.Annotations.PropertyKey;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Adds Multi language support using the I18N standard.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class I18N {

    private final static Locale locale = Locale.getDefault();
    @NonNls
    private static final ResourceBundle bundle = PropertyResourceBundle.getBundle("MessagesBundle", locale);
    //@NonNls
    //private static final ResourceBundle bundle = ResourceBundle.getBundle(dir + File.separator + "MessagesBundle", locale);

    private static final String prefix = bundle.getString("default.prefix");
    private static final String infoColor = bundle.getString("default.color.normal");
    private static final String altColor = bundle.getString("default.color.alt");
    private static final String adminColor = bundle.getString("default.color.admin");
    private static final String errorColor = bundle.getString("default.color.error");
    private static final String debugPrefix = bundle.getString("default.debug.prefix");
    private static final char COLOR_CHAR = '\u00A7';
    private static final char SAFE_CHAR = '&';


    public static String STRING
            (@PropertyKey(resourceBundle = "MessagesBundle") final
             String key, final Object... params) {

        String value = bundle.getString(key);
        if (params.length > 0) return MessageFormat.format(value, params);
        return value;
    }


    /**
     * Send command raw message.
     *
     * @param sender  the @{@link org.bukkit.command.CommandSender} sender
     * @param message the {@link java.lang.String} message
     */
    public static void sendRawMessage(CommandSender sender, String message) {

        sender.sendMessage(message);
    }

    public static void sendRawMessage(CommandSender sender, String[] messages) {

        sender.sendMessage(messages);
    }

    public static void sendMessage(CommandSender sender, String[] messages) {

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
    public static void sendMessage(CommandSender sender, String message) {

        sender.sendMessage(format(message));
    }

    /**
     * Send command error message.
     *
     * @param sender  the @{@link org.bukkit.command.CommandSender} sender
     * @param message the {@link java.lang.String} message
     */
    public static void sendErrorMessage(CommandSender sender, String message) {

        sender.sendMessage(errorFormat(message));
    }

    /**
     * Send command admin message.
     *
     * @param sender  the @{@link org.bukkit.command.CommandSender} sender
     * @param message the {@link java.lang.String} message
     */
    public static void sendAdminMessage(CommandSender sender, String message) {

        sender.sendMessage(adminFormat(message));
    }

    /**
     * Send alternative color command message.
     *
     * @param sender  the @{@link org.bukkit.command.CommandSender} sender
     * @param message the {@link java.lang.String} message
     */
    public static void sendAltMessage(CommandSender sender, String message) {

        sender.sendMessage(altFormat(message));
    }


    /**
     * Format string.
     *
     * @param message the message
     * @return the string formatted with getPrefix(), message color and converts any other color codes in message
     */
    public static String format(String message) {

        return addColor(prefix + infoColor + message);
    }

    public static String altFormat(String message) {

        return addColor(prefix + altColor + message);

    }

    /**
     * Format string.
     *
     * @param name    the name to use in prefix
     * @param message the message
     * @return the string formatted with prefix with custom name, message color and converts any other color codes in message
     */
    public static String format(String name, String message) {

        return addColor("&5[&b" + name + "&5] " + message);
    }

    /**
     * Error format.
     *
     * @param message the message
     * @return the string
     */
    public static String errorFormat(String message) {

        return addColor(prefix + errorColor + message);
    }

    /**
     * Admin format.
     *
     * @param message the message
     * @return the string
     */
    public static String adminFormat(String message) {

        return addColor(prefix + adminColor + message);
    }

    /**
     * Display a debug message in game. Will only log if debug is set to true in config.yml
     *
     * @param sender  the sender
     * @param message the message
     */
    public static void debugGameMessage(CommandSender sender, String message) {

        sender.sendMessage(addColor(debugPrefix) + addColor(message) + addColor("&r"));

    }

    /**
     * Log to console
     *
     * @param message the {@link java.lang.Object} message
     */
    public static void log(Object message) {

        System.out.println(message);
    }


    /**
     * Convert color. Shade from Bukkit Api
     *
     * @param s {@link String} {@link String} the text to convert color chars to correct format
     * @return the {@link String} line of text which has color formatted
     */
    public static String addColor(String s) {

        char[] b = s.toCharArray();
        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == SAFE_CHAR && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1) {
                b[i] = COLOR_CHAR;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }

        }
        return new String(b);
    }

    /**
     * Remove color.
     *
     * @param message the message
     * @return the {@link java.lang.String} that has color removed
     */
    public static String removeColor(String message) {

        return ChatColor.stripColor(message);
    }

    /**
     * Convert color. Shade from Bukkit Api
     *
     * @param s {@link String} {@link String} the text to convert color chars to correct format
     * @return the {@link String} line of text which has color formatted
     */
    public static String addAltColor(String s) {

        char[] b = s.toCharArray();
        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == COLOR_CHAR && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1) {
                b[i] = SAFE_CHAR;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }

        }
        return new String(b);
    }

    public static void clearChat(Player player) {

        for (int i = 0; i < 100; i++) {
            player.sendMessage("");
        }

    }

    public static void clearChat(Player player, int lines) {

        for (int i = 0; i < lines; i++) {
            player.sendMessage("");
        }

    }
}
