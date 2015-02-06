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

import org.bukkit.ChatColor;

/**
 * Name: Messages.java Created: 21 January 2015
 *
 * @author Relicum
 * @version 0.0.1
 */
public class Messages {

    public static final String NAME = ChatColor.GRAY + "" + ChatColor.BOLD + "[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "MenuMobs" + ChatColor.GRAY + ""
            + ChatColor.BOLD + "]" + ChatColor.WHITE;
    public static final String PREFIX = NAME + ChatColor.GRAY + "" + ChatColor.BOLD + "➙ " + ChatColor.WHITE;
    public static final String ERROR_PREFIX = NAME + ChatColor.DARK_RED + "" + ChatColor.BOLD + " ✘ " + ChatColor.RED;
    public static final String HEADER = ChatColor.AQUA + "❙❙ " + ChatColor.GRAY + "================ " + NAME + ChatColor.GRAY + " ================="
            + ChatColor.AQUA + " ❙❙" + ChatColor.WHITE;
    public static final String FOOTER = ChatColor.AQUA + "❙❙ " + ChatColor.GRAY + "===================== MM =====================" + ChatColor.AQUA + " ❙❙"
            + ChatColor.WHITE;
}
