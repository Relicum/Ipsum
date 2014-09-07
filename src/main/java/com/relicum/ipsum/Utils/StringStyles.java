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

package com.relicum.ipsum.Utils;

import net.minecraft.util.org.apache.commons.lang3.Validate;
import net.minecraft.util.org.apache.commons.lang3.text.StrBuilder;
import org.bukkit.ChatColor;

/**
 * StringStyles selection of methods to create string styles, used to surround messages or message headers with a centralised title.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class StringStyles {

    /**
     * Creates a line using a single character that alternates between 2 different colors.
     *
     * @param color     the first color
     * @param color2    the second color
     * @param style     the style
     * @param character the character
     * @return the string style
     */
    public static String fullLine(final ChatColor color, final ChatColor color2, final ChatColor style, char character) {

        StrBuilder sb = new StrBuilder();
        boolean t = true;
        for (int i = 0; i < 53; i++) {
            if (t) {
                sb.append(style).append(color).append(character);
                t = false;
            } else {
                sb.append(style).append(color2).append(character);
                t = true;
            }
        }

        return sb.toString();
    }

    /**
     * Centered heading.
     * <p>Centres the text to be exactly in the middle of the line with white space used for padding either side.
     * The max length of the heading string is 26 characters.
     *
     * @param color   the color
     * @param style   the style
     * @param heading the heading text
     * @return the centered heading string
     */
    public static String centeredHeading(ChatColor color, ChatColor style, String heading) {

        Validate.notNull(color);
        Validate.notNull(style);
        Validate.isTrue(heading.length() < 27, "The maximum length of the heading is 26 Characters");

        int left = 26 - heading.length();
        int offSet = 0;
        int outSet = 0;

        if (!((Integer) left < 1)) {

            if (left == 1) {
                offSet = 1;
            } else if (left == 2) {
                offSet = 1;
                outSet = 1;
            } else if (left > 0 && left % 2 == 1) {
                offSet = (left / 2) + 1;
                outSet = left / 2;
            } else {
                offSet = left / 2;
                outSet = offSet;
            }
        }
        StrBuilder sb = new StrBuilder(58);

        sb.append(" ").appendPadding(19 + offSet, ' ').append(style).append("").append(color).append(heading).appendPadding(8 + offSet, ' ');

        return sb.toString();
    }

    /**
     * Boxed announcement.
     *
     * @param color     the color
     * @param color2    the color 2
     * @param style     the style
     * @param character the character
     * @param hColor    the h color
     * @param hStyle    the h style
     * @param heading   the heading
     * @param messages  the messages
     * @return the string
     */
    public static String boxedAnnouncement(final ChatColor color, final ChatColor color2, final ChatColor style, char character, final ChatColor hColor, ChatColor hStyle, String heading, String... messages) {

        StrBuilder sb = new StrBuilder();
        sb.setNewLineText("\n");
        sb.append(" ").appendNewLine().append(" ").appendNewLine().append(" ").appendNewLine();
        sb.append(fullLine(color, color2, style, character)).appendNewLine();
        sb.append(" ").appendNewLine();
        sb.append(centeredHeading(hColor, hStyle, heading)).appendNewLine();
        sb.append(" ").appendNewLine();
        sb.append(ChatColor.translateAlternateColorCodes('&', messages[0])).appendNewLine();
        sb.append(" ").appendNewLine();
        sb.append(fullLine(color2, color, style, character)).appendNewLine();

        return sb.toString();


    }
}
