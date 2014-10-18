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

package com.relicum.ipsum.Commands.Group;

import net.minecraft.util.org.apache.commons.lang3.Validate;

import java.util.HashMap;
import java.util.Map;

/**
 * Default message format builder.
 * <p>All methods return themselves for chaining. Each setting already has a default value so you only need to over
 * ride the formats that you want when complete call {@link #build()} and a Map will be returned with all the completed
 * keys and values.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class DefaultFormat implements IMFormat {


    private Map<String, String> format = new HashMap<>();

    public DefaultFormat() {

        format.put("default.prefix", "&b[&5IPSUM&b]&r ");
        format.put("default.color.normal", "&a");
        format.put("default.color.error", "&c");
        format.put("default.color.admin", "&2");
        format.put("default.color.broadcast", "&6");
        format.put("default.color.alt", "&3");
        format.put("default.message.error", "An unknown error has occured please check the log for details");
        format.put("default.message.noperms", "Sorry you do not have permission to do that");
        format.put("default.debug.prefix", "&b[&4IPSUM-DEBUG&b]&r ");
        format.put("default.debug.color", "&d");
    }


    /**
     * Sets message prefix.
     *
     * @param prefix the prefix used for plugin messages
     * @return the instance of itself for Chaining methods
     */
    @Override
    public IMFormat setPrefix(String prefix) {
        Validate.notNull(prefix);
        format.put("default.prefix", prefix);
        return this;
    }

    /**
     * Sets default color.
     *
     * @param defaultColor the default color
     * @return the instance of itself for Chaining methods
     */
    @Override
    public IMFormat setDefaultColor(String defaultColor) {
        Validate.notNull(defaultColor);
        format.put("default.color.normal", defaultColor);
        return this;
    }

    /**
     * Sets alternative color.
     *
     * @param altColor the alternative color
     * @return the instance of itself for Chaining methods
     */
    @Override
    public IMFormat setAltColor(String altColor) {
        Validate.notNull(altColor);
        format.put("default.color.alt", altColor);
        return this;
    }

    /**
     * Sets error color.
     *
     * @param errorColor the error color
     * @return the instance of itself for Chaining methods
     */
    @Override
    public IMFormat setErrorColor(String errorColor) {
        Validate.notNull(errorColor);
        format.put("default.color.error", errorColor);
        return this;
    }

    /**
     * Sets admin color.
     *
     * @param adminColor the admin color
     * @return the instance of itself for Chaining methods
     */
    @Override
    public IMFormat setAdminColor(String adminColor) {
        Validate.notNull(adminColor);
        format.put("default.color.admin", adminColor);
        return this;
    }

    /**
     * Sets broadcast message color.
     *
     * @param broadCastColor the broad cast color
     * @return the instance of itself for Chaining methods
     */
    @Override
    public IMFormat setBroadCastColor(String broadCastColor) {
        Validate.notNull(broadCastColor);
        format.put("default.color.broadcast", broadCastColor);
        return this;
    }

    /**
     * Sets error message.
     * <p>Default error message if no over is provided.
     *
     * @param errorMessage the error message
     * @return the instance of itself for Chaining methods
     */
    @Override
    public IMFormat setErrorMessage(String errorMessage) {
        Validate.notNull(errorMessage);
        format.put("default.message.error", errorMessage);
        return this;
    }

    /**
     * Sets no perms message.
     * <p>Default message displayed when no the user does not have permission and no other message has been provided.
     *
     * @param noPermsMessage the no perms message
     * @return the instance of itself for Chaining methods
     */
    @Override
    public IMFormat setNoPermsMessage(String noPermsMessage) {
        Validate.notNull(noPermsMessage);
        format.put("default.message.noperms", noPermsMessage);
        return this;
    }

    /**
     * Sets debug color.
     *
     * @param debugColor the debug color
     * @return the instance of itself for Chaining methods
     */
    @Override
    public IMFormat setDebugColor(String debugColor) {
        Validate.notNull(debugColor);
        format.put("default.debug.color", debugColor);
        return this;
    }

    /**
     * Sets debug prefix.
     *
     * @param debugPrefix the debug prefix
     * @return the instance of itself for Chaining methods
     */
    @Override
    public IMFormat setDebugPrefix(String debugPrefix) {
        Validate.notNull(debugPrefix);
        format.put("default.debug.prefix", debugPrefix);
        return this;
    }

    /**
     * Returns a map of all the default formats.
     *
     * @return the map containing the keys and values
     */
    @Override
    public Map<String, String> build() {
        return format;
    }
}
