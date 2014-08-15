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

import org.bukkit.ChatColor;

/**
 * Name: MessageFormatBuilder.java Created: 09 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class MessageFormatBuilder implements IMessageFormat<MessageFormatBuilder> {

    private ChatColor messageColor;
    private ChatColor errorMessageColor;
    private ChatColor highlighterColor;
    private ChatColor adminColor;
    private Prefix prefix;

    /**
     * Sets standard message color.
     *
     * @param messageColor the standard message color
     * @return the instance of itself so methods can be chained
     */
    @Override
    public MessageFormatBuilder setStdMessageColor(ChatColor messageColor) {
        this.messageColor = messageColor;
        return this;
    }

    /**
     * Sets error message color.
     *
     * @param errorMessageColor the error message color
     * @return the instance of itself so methods can be chained
     */
    @Override
    public MessageFormatBuilder setErrorMessageColor(ChatColor errorMessageColor) {
        this.errorMessageColor = errorMessageColor;
        return this;
    }

    /**
     * Sets highlighter color, used to highlight parts of a message.
     *
     * @param highlighterColor the highlighter color
     * @return the instance of itself so methods can be chained
     */
    @Override
    public MessageFormatBuilder setHighlighterColor(ChatColor highlighterColor) {
        this.highlighterColor = highlighterColor;
        return this;
    }

    /**
     * Sets admin color, used to display message when using admin commands.
     *
     * @param adminColor the admin color
     * @return the instance of itself so methods can be chained
     */
    @Override
    public MessageFormatBuilder setAdminColor(ChatColor adminColor) {
        this.adminColor = adminColor;
        return this;
    }

    /**
     * Sets prefix that will be used at the start of all messages.
     *
     * @param prefix the prefix
     * @return the instance of itself so methods can be chained
     */
    @Override
    public MessageFormatBuilder setPrefix(Prefix prefix) {
        this.prefix = prefix;
        return this;
    }

    public DefaultMessage createMessage() {

        return new DefaultMessage(messageColor, errorMessageColor, highlighterColor, adminColor, prefix);
    }
}
