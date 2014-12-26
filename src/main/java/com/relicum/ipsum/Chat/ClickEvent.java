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
package com.relicum.ipsum.Chat;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents a clickable action.
 *
 * @author md_5
 */

@Getter
@AllArgsConstructor
final public class ClickEvent {
    /**
     * The type of action to preform on click
     */
    private final Action action;

    /**
     * Depends on action
     *
     * @see Action
     */
    private final String value;

    public enum Action {
        /**
         * Open a url at the path given by {@link com.relicum.ipsum.Chat.ClickEvent}
         */
        OPEN_URL,

        /**
         * Open a file at the path given by {@link com.relicum.ipsum.Chat.ClickEvent}
         */
        OPEN_FILE,

        /**
         * Run the command given by {@link com.relicum.ipsum.Chat.ClickEvent}
         */
        RUN_COMMAND,

        /**
         * Inserts the string given by {@link com.relicum.ipsum.Chat.ClickEvent} into the
         * player's text box
         */
        SUGGEST_COMMAND
    }

    @Override
    public String toString() {
        return String.format("ClickEvent{action=%s, value=%s}", action, value);
    }
}
