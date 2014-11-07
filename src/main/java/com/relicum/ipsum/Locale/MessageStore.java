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

import net.minecraft.util.org.apache.commons.lang3.Validate;

import java.util.Properties;

/**
 * MessageStore used as a container to store messages as you build the plugin.
 * <p>This should be passed to you message creator object as a Map. Any time you want to add the
 * messages into the lang files just get the message creator or PluginMessages to save it.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class MessageStore implements IDMessage {

    private Properties properties = new Properties();

    /**
     * Returns the instance of {@link java.util.Properties} object that is used to store the messages in.
     *
     * @return the {@link java.util.Properties} object which is used to store the messages in.
     */
    @Override
    public Properties getProperties() {

        return properties;
    }

    /**
     * Add command description.
     *
     * @param command     the command name
     * @param description the description
     */
    public void addCommandDescription(String command, String description) {
        Validate.notNull(command);
        Validate.notNull(description);

        addMessage("command." + command.toLowerCase() + ".description", description);
    }

    /**
     * Add command success message.
     *
     * @param command the command name
     * @param success the message if the command completes succesfully
     */
    public void addCommandSuccess(String command, String success) {
        Validate.notNull(command);
        Validate.notNull(success);

        addMessage("command." + command.toLowerCase() + ".success", success);
    }

    /**
     * Add command usage this will be show by the bukkits default help system
     *
     * @param command the command name
     * @param usage   the usage of the command
     */
    public void addCommandUsage(String command, String usage) {
        Validate.notNull(command);
        Validate.notNull(usage);

        addMessage("command." + command.toLowerCase() + ".usage", usage);
    }

    /**
     * Add general purpose command message this message takes an added key.
     *
     * @param command the command name
     * @param key     the unique key for the message, short and a single word only
     * @param message the general purpose command message
     */
    public void addCommandMessage(String command, String key, String message) {
        Validate.notNull(command);
        Validate.notNull(key);
        Validate.notNull(message);

        addMessage("command." + command.toLowerCase() + ".message." + key, message);
    }

    /**
     * Add game broadcast message
     *
     * @param key     the unique key for the message
     * @param message the broadcast message
     */
    public void addGameBroadcast(String key, String message) {
        Validate.notNull(key);
        Validate.notNull(message);

        addMessage("game.broadcast." + key.toLowerCase(), message);
    }

    /**
     * Add game player message that is sent to a single player only.
     *
     * @param key     the unique key for the message.
     * @param message the message the player will receive.
     */
    public void addGamePlayerMessage(String key, String message) {
        Validate.notNull(key);
        Validate.notNull(message);

        addMessage("game.player.message." + key.toLowerCase(), message);
    }

    public void logGameMessage(String key, String message) {
        Validate.notNull(key);
        Validate.notNull(message);

        addMessage("game.message.log." + key.toLowerCase(), message);

    }
}
