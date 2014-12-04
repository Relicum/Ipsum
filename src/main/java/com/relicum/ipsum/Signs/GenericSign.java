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

package com.relicum.ipsum.Signs;

import com.relicum.ipsum.Configuration.Loc;
import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;

/**
 * GenericSign interface to form the base of all other signs.
 *
 * @author Relicum
 * @version 0.0.1
 */
public interface GenericSign {

    String[] getLines();

    String getLine(int index);

    Loc getLocation();

    void setLocation(Loc location);

    String getIdentity();

    void setIdentity();

    default void setLine(int i, String line) {

        Validate.isTrue((i < 4 && i > -1), "Index for a sign line must be between 0 and 3");
        line = ChatColor.translateAlternateColorCodes('&', line);
        Validate.isTrue(line.length() < 15, "The length of a sign line must be below 16 characters");

        getLines()[i] = line;
    }


}
