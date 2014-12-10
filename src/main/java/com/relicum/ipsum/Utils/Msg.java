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

package com.relicum.ipsum.Utils;

import com.relicum.ipsum.Locale.ConsoleColors;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Msg simple message sender, use {@link com.relicum.ipsum.Utils.MsgBuilder} to build all the different color and styles.
 * <p>You can then save the formatting results to a json file and loaded on each restart.
 * <p>Then you can use all the send methods in this class allowing everything to be standardized and consistent.
 * <p>New Line Added
 *
 * @author Relicum
 * @version 0.0.1
 */
public class Msg implements ConsoleColors {

    private final char COLOR_CHAR = '\u00A7';
    private final char SAFE_CHAR = '&';
    private Pattern PATTERN = Pattern.compile("(?i)" + String.valueOf(COLOR_CHAR) + "[0-9A-FK-OR]");


    private String prefix;
    private String infoColor;
    private String errorColor;
    private String adminColor;
    private String announceColor;

    private String logPrefix;
    private String logInfoColor;
    private String logSevereColor;
    private String logWarningColor;
    private String prefixNoColor;
    private String highLight;
    private String header;
    private String footer;


    public Msg(String prefix, String infoColor, String errorColor, String adminColor,
               String logPrefix, String logInfoColor, String logSevereColor, String logWarningColor,
               String prefixNoColor, String announceColor, String highLight, String header, String footer) {
        this.prefix = prefix;
        this.infoColor = infoColor;
        this.errorColor = errorColor;
        this.adminColor = adminColor;
        this.logPrefix = logPrefix;
        this.logInfoColor = logInfoColor;
        this.logSevereColor = logSevereColor;
        this.logWarningColor = logWarningColor;
        this.prefixNoColor = prefixNoColor;
        this.announceColor = announceColor;
        this.highLight = highLight;
        this.header = header;
        this.footer = footer;

    }

    /**
     * Send message.
     *
     * @param player the player
     * @param text   the text
     */
    public void sendMessage(CommandSender player, String text) {

        player.sendMessage(colorize(prefix + "&7&l\u2799 " + infoColor + text));

    }

    public String getMessage(String text) {

        return colorize(prefix + "&7&l\u2799 " + infoColor + text);
    }

    /**
     * Send error message.
     *
     * @param player the player
     * @param text   the text
     */
    public void sendErrorMessage(CommandSender player, String text) {
        player.sendMessage(colorize(prefix + "&7&l\u2799 " + errorColor + text));
    }

    /**
     * Send admin message.
     *
     * @param player the player
     * @param text   the text
     */
    public void sendAdminMessage(CommandSender player, String text) {

        player.sendMessage(colorize(prefix + "&7&l\u2799 " + adminColor + text));
    }

    /**
     * Send alt message.
     *
     * @param player the player
     * @param text   the text
     */
    public void sendAltMessage(CommandSender player, String text) {

        player.sendMessage(colorize(prefix + "&7&l\u2799 " + announceColor + text));
    }


    /**
     * Gets highlight color
     *
     * @return the high light
     */
    public String getHighLight() {
        return highLight;
    }

    /**
     * Broadcast a message to entire server
     *
     * @param text the to broadcast.
     */
    public void broadcast(String text) {

        Bukkit.broadcastMessage(prefix + colorize(text));
    }

    /**
     * Send message block header.
     *
     * @param player the player
     */
    public void sendHeader(CommandSender player) {
        player.sendMessage(colorize(header));
    }

    /**
     * Send message block footer.
     *
     * @param player the player
     */
    public void sendFooter(CommandSender player) {
        player.sendMessage(colorize(footer));
    }

    /**
     * Log void.
     *
     * @param level the level
     * @param text  the text
     */
    public void log(Level level, String text) {

        Logger.getGlobal().log(level, prefixNoColor + text + RESET);

    }

    /**
     * Log formatted.
     *
     * @param level the level
     * @param text  the text
     */
    public void logFormatted(Level level, String text) {

        if (level.equals(Level.INFO)) {

            sendToConsole(colorize(logPrefix + " " + logInfoColor + text));

        }
        if (level.equals(Level.WARNING)) {

            sendToConsole(colorize(logPrefix + " " + logWarningColor + text));
        }

        if (level.equals(Level.SEVERE)) {

            sendToConsole(colorize(logPrefix + " " + logSevereColor + text));
        }

    }

    private void sendToConsole(String text) {

        Bukkit.getConsoleSender().sendMessage(text);
    }

    /**
     * Colorize string.
     *
     * @param text the text
     * @return the string
     */
    public String colorize(String text) {

        return text.replaceAll("(?i)&([a-fklmnor0-9])", "\u00A7$1");

    }

    /**
     * Colorizer string.
     *
     * @param text the text
     * @return the string
     */
    public static String colorizer(String text) {

        return text.replaceAll("(?i)&([a-fklmnor0-9])", "\u00A7$1");

    }

    /**
     * Re colorize.
     *
     * @param text the text
     * @return the string
     */
    public String reColorize(String text) {

        return text.replaceAll("(?i)\u00A7([a-fklmnor0-9])", "&$1");
    }

    /**
     * Re colorizer.
     *
     * @param text the text
     * @return the string
     */
    public static String reColorizer(String text) {

        return text.replaceAll("(?i)\u00A7([a-fklmnor0-9])", "&$1");
    }

    /**
     * Strip color.
     *
     * @param text the text
     * @return the string
     */
    public String stripColor(String text) {
        return text.replaceAll("(?i)\u00A7([a-fklmnor0-9])", "").replaceAll("(?i)&([a-fklmnor0-9])", "");
    }

    /**
     * Clear chat.
     *
     * @param player the player
     */
    public void clearChat(Player player) {

        for (int i = 0; i < 100; i++) {
            player.sendMessage("");
        }

    }

    /**
     * Clear chat by number of lines.
     *
     * @param player the player
     * @param lines  the lines
     */
    public void clearChat(Player player, int lines) {

        for (int i = 0; i < lines; i++) {
            player.sendMessage("");
        }

    }


}
