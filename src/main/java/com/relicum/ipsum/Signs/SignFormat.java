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

import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;

/**
 * SignFormat is a simple container holding the set of lines to be displayed on a sign.
 * <p>This includes an identifier and the index of the line it can be found on.
 *
 * @author Relicum
 */
public class SignFormat {


    private String[] lines = new String[4];


    /**
     * Instantiates a new Sign format.
     *
     * @param lines the lines
     */
    SignFormat(String[] lines) {

        for (String line : lines) {
            validateLine(line);
        }

        this.lines = lines;
    }


    /**
     * Sets new lines for a sign to display
     *
     * @param lines the list of lines in String[] format
     */
    public void setLines(String[] lines) {

        for (String line : lines) {
            validateLine(line);
        }

        this.lines = lines;
    }

    /**
     * Gets the String[] list of lines that is displayed on the sign.
     *
     * @return Value of lines.
     */
    public String[] getLines() {
        return lines;
    }

    /**
     * Validate line.
     *
     * @param line the line to validate
     * @return the string validated
     */
    private boolean validateLine(String line) {
        line = ChatColor.translateAlternateColorCodes('&', line);
        Validate.isTrue(line.length() < 16, "The length of a sign line must be below 16 characters");
        return true;

    }

    @Override
    public String toString() {
        String tmp = "";
        for (int i = 0; i < lines.length; i++) {
            tmp += "Line = " + i + " " + lines[i] + " ";
        }

        return tmp;
    }
}
