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


import org.apache.commons.lang.Validate;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Simple Interface for any class that needs to add messages settings to the Global message file.
 * <p>This is mainly used during the creation of the plugin, release version of the plugin should not have the classes
 * implementing this interface, as the message the message store will of been created by then.
 * <p>To avoid having to use a static field to hold the {@link java.util.Properties} object in you need to impliment 1 method,
 * {@link #getProperties()} which should return the instance of {@link java.util.Properties} that will store the messages in.
 * <p>There is no need to override any of the other messages unless you are making a custom implementation and you know what you are doing.
 *
 * @author Relicum
 * @version 0.0.1
 */
public interface IDMessage {

    /**
     * Returns the instance of {@link java.util.Properties} object that is used to store the messages in.
     *
     * @return the {@link java.util.Properties} object which is used to store the messages in.
     */
    public Properties getProperties();

    /**
     * Add new plugin message to the plugin store.
     * <p>If you want to add color to the message standard minecraft color formatting using the prefix of <strong>&amp;</strong>
     * Please remember that the messages will already already be auto formatted depending on thew type of message this is just to allow you to override that.
     * <p>In the message needs to have place holders which will be replaced with the specific values at run time just add <strong>{0}</strong> where
     * you want the place holder to be. You can add as many place holders as you like just increment the placeholder number eg if
     * you wanted 2 place holders the 2nd would be <strong>{1}</strong>. When displaying the message you will need to pass in what each
     * place holder is to be replaced with.
     *
     * @param key     the key that the message will be stored under.
     * @param message the message itself
     */
    default void addMessage(String key, String message) {

        Validate.notNull(key, "The key for adding a new message can not be null");
        Validate.notNull(message, "New message can not be null");

        getProperties().setProperty(key, message);


    }


    /**
     * Remove a message from the message store
     *
     * @param key the key of the message that is to be removed.
     * @throws RuntimeException if the message is not found.
     */
    default void removeMessage(String key) throws RuntimeException {

        Validate.notNull(key, "Key to search for to remove message can not be null");

        if (!getProperties().containsKey(key))
            throw new RuntimeException("Unable to find the message for deletion using the key " + key);

        getProperties().remove(key);

    }


    /**
     * Returns all of the messages as a {@link java.util.Properties} object.
     * Identical to {@link #getProperties()} method.
     *
     * @return the messages in {@link java.util.Properties} format.
     */
    default Properties getMessages() {
        return getProperties();
    }

    /**
     * Returns all of the messages as a{@link java.util.Map}
     * The keys of the map will be the message keys, the values will be the messages themselves
     *
     * @return the messages as a {@link java.util.Map}
     */
    default Map<String, String> getMessagesAsMap() {

        Map<String, String> map = new HashMap<>();

        for (Map.Entry<Object, Object> mess : getProperties().entrySet()) {

            map.put((String) mess.getKey(), (String) mess.getValue());
        }

        return map;
    }
}
