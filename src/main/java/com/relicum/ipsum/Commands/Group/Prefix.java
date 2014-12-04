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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.bukkit.ChatColor;

/**
 * Prefix quick way to standardise your plugin messages without repeating yourself.
 * <p>Use the {@link com.relicum.ipsum.Commands.Group.PrefixBuilder} to create instance of Prefix.
 *
 * @author Relicum
 */
public class Prefix {

    private String leftChars;

    private ChatColor leftColor;

    private ChatColor leftStyle;

    private String middleChars;

    private String altMiddleChar;

    private ChatColor middleColor;

    private ChatColor middleStyle;

    private String rightChars;

    private ChatColor rightColor;

    private ChatColor rightStyle;

    private String prefix;

    private String altPrefix;

    private Prefix() {

    }

    /**
     * Instantiates a new Prefix.
     *
     * @param prefix    the prefix
     * @param altPrefix the alt prefix
     */
    protected Prefix(String prefix, String altPrefix) {
        this.prefix = prefix;
        this.altPrefix = altPrefix;
    }


    /**
     * Gets prefix.
     *
     * @return Value of prefix.
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Gets altPrefix.
     *
     * @return Value of altPrefix.
     */
    public String getAltPrefix() {
        return altPrefix;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("leftChars", leftChars)
                .append("leftColor", leftColor)
                .append("leftStyle", leftStyle)
                .append("middleChars", middleChars)
                .append("altMiddleChar", altMiddleChar)
                .append("middleColor", middleColor)
                .append("middleStyle", middleStyle)
                .append("rightChars", rightChars)
                .append("rightColor", rightColor)
                .append("rightStyle", rightStyle)
                .append("prefix", prefix)
                .append("altPrefix", altPrefix)
                .toString();
    }
}
