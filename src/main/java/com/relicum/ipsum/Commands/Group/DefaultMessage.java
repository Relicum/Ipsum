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

import net.minecraft.util.com.google.common.base.Objects;
import org.bukkit.ChatColor;

/**
 * Name: DefaultMessage.java Created: 09 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class DefaultMessage {

    private ChatColor messageColor;
    private ChatColor errorMessageColor;
    private ChatColor highlighterColor;
    private ChatColor adminColor;
    private Prefix prefix;

    private DefaultMessage() {

    }

    public DefaultMessage(ChatColor messageColor, ChatColor errorMessageColor, ChatColor highlighterColor, ChatColor adminColor, Prefix prefix) {
        this.messageColor = messageColor;
        this.errorMessageColor = errorMessageColor;
        this.highlighterColor = highlighterColor;
        this.adminColor = adminColor;
        this.prefix = prefix;
    }


    /**
     * Gets highlighterColor.
     *
     * @return Value of highlighterColor.
     */
    public ChatColor getHighlighterColor() {
        return highlighterColor;
    }

    /**
     * Gets prefix.
     *
     * @return Value of prefix.
     */
    public Prefix getPrefix() {
        return prefix;
    }

    /**
     * Gets messageColor.
     *
     * @return Value of messageColor.
     */
    public ChatColor getMessageColor() {
        return messageColor;
    }

    /**
     * Gets errorMessageColor.
     *
     * @return Value of errorMessageColor.
     */
    public ChatColor getErrorMessageColor() {
        return errorMessageColor;
    }

    /**
     * Gets adminColor.
     *
     * @return Value of adminColor.
     */
    public ChatColor getAdminColor() {
        return adminColor;
    }


    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("messageColor", messageColor)
                .add("errorMessageColor", errorMessageColor)
                .add("highlighterColor", highlighterColor)
                .add("adminColor", adminColor)
                .add("prefix", prefix)
                .toString();
    }
}
