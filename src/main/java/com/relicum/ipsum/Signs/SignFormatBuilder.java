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

import lombok.ToString;
import net.minecraft.util.org.apache.commons.lang3.Validate;
import org.bukkit.ChatColor;

/**
 * Name: SignFormatBuilder.java Created: 06 September 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
@ToString
public class SignFormatBuilder {


    private String[] lines = new String[]{" ", " ", " ", " "};


    public static SignFormatBuilder Builder() {

        return new SignFormatBuilder();
    }

    SignFormatBuilder() {
    }

    /**
     * Sets line text at index 0
     *
     * @param line0 the text to be displayed at index 0
     * @return instance of itself for chaining methods
     */
    public SignFormatBuilder setLine0(String line0) {
        this.lines[0] = validateLine(line0);
        return this;
    }

    /**
     * Sets line 1.
     *
     * @param line1 the line 1
     * @return instance of itself for chaining methods
     */
    public SignFormatBuilder setLine1(String line1) {
        this.lines[1] = validateLine(line1);
        return this;
    }

    /**
     * Sets line 2.
     *
     * @param line2 the line 2
     * @return instance of itself for chaining methods
     */
    public SignFormatBuilder setLine2(String line2) {
        this.lines[2] = validateLine(line2);
        return this;
    }

    /**
     * Sets line 3.
     *
     * @param line3 the line 3
     * @return instance of itself for chaining methods
     */
    public SignFormatBuilder setLine3(String line3) {
        this.lines[3] = validateLine(line3);
        return this;
    }


    /**
     * Build the new {@link com.relicum.ipsum.Signs.SignFormat} object.
     *
     * @return the a new Instance of {@link com.relicum.ipsum.Signs.SignFormat}
     */
    public SignFormat build() {

        return new SignFormat(addEmpty(lines));

    }

    private String[] addEmpty(String[] lines) {

        for (int i = 0; i < lines.length; i++) {
            if (lines[i] != null)
                break;
            else {
                lines[1] = "";
            }
        }

        return lines;
    }

    /**
     * Validate line.
     *
     * @param line the line to validate
     * @return the string validated
     */
    private String validateLine(String line) {
        line = ChatColor.translateAlternateColorCodes('&', line);
        Validate.isTrue(line.length() < 16, "The length of a sign line must be below 16 characters");
        return line;

    }


}

