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

/**
 * Name: TextProcessor.java Created: 12 November 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class TextProcessor {

    private TextProcessor() {
    }

    /**
     * Colorize the string replacing <strong>&amp;</strong> with <strong>&#xa7;</strong> ONLY if it is followed by a minecraft color code.
     * <p>If the <strong>&amp;</strong> is not followed by a minecraft color code it will not be replaced.
     * <p>This will replace all valid matches in the entire string passed to it.
     *
     * @param string the string to be colorize
     * @return the colorized string
     */
    public static String colorize(String string) {

        return colorize(string, "&", "\u00A7");
    }

    /**
     * Colorize the string using a defined character, replacing it with <strong>&#xa7;</strong>, ONLY if it is followed by a minecraft color code.
     * <p>If the defined character is not followed by a minecraft color code it will not be replaced.
     * <p>This will replace all valid matches in the entire string passed to it.
     *
     * @param string the string to be colorize.
     * @param find   the character to search for.
     * @return the colorized string
     */
    public static String colorize(String string, String find) {

        return colorize(string, find, "\u00A7");
    }

    /**
     * Colorize the string using a defined character, replacing it with a defined character, ONLY if it is followed by a minecraft color code.
     * <p>If the defined character is not followed by a minecraft color code it will not be replaced.
     * <p>This will replace all valid matches in the entire string passed to it.
     * <p>This method is useful if you wish to reverse the process, eg convert <strong>&#xa7;6</strong> to <strong>&amp;6</strong> again only valid matches will be replaced.
     *
     * @param string  the string to be colorize.
     * @param find    the character to search for.
     * @param replace the replacement character.
     * @return the colorized string
     */
    public static String colorize(String string, String find, String replace) {

        return string.replaceAll("(" + find + "([a-fklmnor0-9]))", replace + "$2");
    }

    /**
     * Simple method to reverse the standard colorizing, converting <strong>&#xa7;</strong> to <strong>&amp;</strong> ONLY if it is followed by a minecraft color code.
     * <p>This is only a short cut method as the same result can be achieved using {@link #colorize(String, String, String)} .
     * <p>This will replace all valid matches in the entire string passed to it.
     *
     * @param string the string to be re colorize
     * @return the re colorized string
     */
    public static String reColorize(String string) {

        return colorize(string, "&", "\u00A7");

    }

    /**
     * Strip color from the string when the character <strong>&#xa7;</strong> is found followed by a minecraft color code.
     * <p>This will remove both the <strong>&#xa7;</strong> and the minecraft color code.
     * <p>This will strip color from all valid matches in the entire string passed to it.
     *
     * @param string the string to remove color from
     * @return the string stripped of all color.
     */
    public static String stripColor(String string) {

        return stripColor(string, "\u00A7");
    }

    /**
     * Strip color from the string using a defined character when it is found followed by a minecraft color code.
     * <p>This will remove both the defined character and the minecraft color code.
     * <p>Useful if the string contains color defined using the <strong>&amp;</strong> character.
     * <p>This will strip color from all valid matches in the entire string passed to it.
     *
     * @param string the string to remove color from
     * @param find   the character to search for.
     * @return the string stripped of all color.
     */
    public static String stripColor(String string, String find) {

        return string.replaceAll("(" + find + "([a-fklmnor0-9]))", "");
    }

    /**
     * Converts a sting
     *
     * @param text the text
     * @return the string
     */
    public static String convert(String text) {
        if ((text == null) || (text.length() == 0)) {
            return "\"\"";
        }

        int len = text.length();
        StringBuilder sb = new StringBuilder(len + 4);

        sb.append('"');
        for (int i = 0; i < len; i++) {
            char c = text.charAt(i);
            switch (c) {
                case '"':
                case '\\':
                    sb.append('\\');
                    sb.append(c);
                    break;
                case '/':
                    sb.append('\\');
                    sb.append(c);
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                default:
                    if (c < ' ') {
                        String t = new StringBuilder().append("000").append(Integer.toHexString(c)).toString();
                        sb.append(new StringBuilder().append("\\u").append(t.substring(t.length() - 4)).toString());
                    } else {
                        sb.append(c);
                    }
                    break;
            }
        }
        sb.append('"');
        return sb.toString();
    }

    /**
     * Convert and colorize.
     *
     * @param text the text
     * @return the string
     */
    public static String convertAndColorize(String text) {

        return convert(colorize(text));
    }
}
