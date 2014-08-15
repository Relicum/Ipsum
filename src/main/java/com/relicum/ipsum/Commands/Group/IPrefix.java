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
 * Name: IPrefix.java Created: 06 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */

public interface IPrefix {


    public IPrefix setLeftChar(String leftChar);

    public IPrefix setLeftColor(ChatColor leftColor);

    public IPrefix setLeftStyle(ChatColor leftStyle);

    public IPrefix setMiddleChars(String middleChars);

    public IPrefix setAltMiddleChars(String altMiddleChars);

    public IPrefix setMiddleColor(ChatColor middleColor);

    public IPrefix setMiddleStyle(ChatColor middleStyle);

    public IPrefix setRightChar(String rightChar);

    public IPrefix setRightColor(ChatColor rightColor);

    public IPrefix setRightStyle(ChatColor rightStyle);

}
