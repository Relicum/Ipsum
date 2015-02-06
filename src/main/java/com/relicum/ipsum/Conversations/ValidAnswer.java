/*
 * Ipsum is a rapid development API for Minecraft, developer by Relicum
 * Copyright (C) 2015.  Chris Lutte
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

package com.relicum.ipsum.Conversations;

/**
 * VaildAnswer simple interface to validate the reponse from a prompt in the
 * {@link org.bukkit.conversations.ConversationFactory}
 *
 * @author Relicum
 * @version 0.0.1
 */
public abstract interface ValidAnswer {

    /**
     * Represents a response answer from a player to a defined prompt this
     * method should determine if the answer given is valid
     *
     * @param paramString the response answer from the player that needs
     *        validating.
     * @return true if the answer is valid, false if not.
     */
    public abstract boolean onAnswer(String paramString);
}
