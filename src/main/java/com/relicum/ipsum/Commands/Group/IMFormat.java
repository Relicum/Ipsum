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

import java.util.Map;

/**
 * Interface for basic message formatting.
 * <p>All colors should be set using minecraft color codes prefixed with <strong>&amp;</strong>
 */
public interface IMFormat {


    /**
     * Sets message prefix.
     *
     * @param prefix the prefix used for plugin messages
     * @return the instance of itself for Chaining methods
     */
    public IMFormat setPrefix(String prefix);

    /**
     * Sets default color.
     *
     * @param defaultColor the default color
     * @return the instance of itself for Chaining methods
     */
    public IMFormat setDefaultColor(String defaultColor);

    /**
     * Sets error color.
     *
     * @param errorColor the error color
     * @return the instance of itself for Chaining methods
     */
    public IMFormat setErrorColor(String errorColor);

    /**
     * Sets admin color.
     *
     * @param adminColor the admin color
     * @return the instance of itself for Chaining methods
     */
    public IMFormat setAdminColor(String adminColor);

    /**
     * Sets broadcast message color.
     *
     * @param broadCastColor the broad cast color
     * @return the instance of itself for Chaining methods
     */
    public IMFormat setBroadCastColor(String broadCastColor);

    /**
     * Sets error message.
     * <p>Default error message if no over is provided.
     *
     * @param errorMessage the error message
     * @return the instance of itself for Chaining methods
     */
    public IMFormat setErrorMessage(String errorMessage);

    /**
     * Sets no perms message.
     * <p>Default message displayed when no the user does not have permission and no other message has been provided.
     *
     * @param noPermsMessage the no perms message
     * @return the instance of itself for Chaining methods
     */
    public IMFormat setNoPermsMessage(String noPermsMessage);

    /**
     * Sets debug color.
     *
     * @param debugColor the debug color
     * @return the instance of itself for Chaining methods
     */
    public IMFormat setDebugColor(String debugColor);

    /**
     * Sets debug prefix.
     *
     * @param debugPrefix the debug prefix
     * @return the instance of itself for Chaining methods
     */
    public IMFormat setDebugPrefix(String debugPrefix);

    /**
     * Returns a map of all the default formats.
     *
     * @return the map containing the keys and values
     */
    public Map<String, String> build();

}
