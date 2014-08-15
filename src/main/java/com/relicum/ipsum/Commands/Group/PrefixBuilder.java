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
 * <strong>PrefixBuilder</strong> make message prefixes fast and effective, allowing your plugin messages to be standardized.
 * <p>Instantiate a new instance of the PrefixBuilder and chaining the methods one after the other.
 * When happy cal the createPrefix method and this will automatically build a new instance of the {@link com.relicum.ipsum.Commands.Group.Prefix}
 * object. Just can be further customised and then registered with the message manager.
 * <p>There is no restrictions on the number of prefixes you have. All prefixes will automatically be stored to hard disk.
 * <p>So you never have to worry about losing work.
 *
 * @author Relicum
 */
public class PrefixBuilder implements IPrefix {

    private String leftChar = "";

    private ChatColor leftColor = ChatColor.WHITE;

    private ChatColor leftStyle = ChatColor.STRIKETHROUGH;

    private String middleChars;

    private String altMiddleChars;

    private ChatColor middleColor = ChatColor.WHITE;

    private ChatColor middleStyle = ChatColor.STRIKETHROUGH;

    private String rightChar = "";

    private ChatColor rightColor = ChatColor.WHITE;

    private ChatColor rightStyle = ChatColor.STRIKETHROUGH;
    StringBuilder sb;
    String prefix;

    /**
     * Sets the Character to use on the left side of the prefix.
     *
     * @param leftChar the left chars
     * @return the instance of itself so methods can be chained
     */
    @Override
    public PrefixBuilder setLeftChar(String leftChar) {
        this.leftChar = leftChar;
        return this;
    }

    /**
     * Sets the color for the left side of the prefix.
     *
     * @param leftColor the left color from {@link org.bukkit.ChatColor}
     * @return the instance of itself so methods can be chained
     */
    @Override
    public PrefixBuilder setLeftColor(ChatColor leftColor) {
        this.leftColor = leftColor;
        return this;
    }

    /**
     * Sets the style for the left side of the prefix.
     *
     * @param leftStyle the left style from {@link org.bukkit.ChatColor}
     * @return the instance of itself so methods can be chained
     */
    @Override
    public PrefixBuilder setLeftStyle(ChatColor leftStyle) {
        this.leftStyle = leftStyle;
        return this;
    }

    /**
     * Sets the Characters to use on the middle of the prefix.
     *
     * @param middleChars the middle chars
     * @return the instance of itself so methods can be chained
     */
    @Override
    public PrefixBuilder setMiddleChars(String middleChars) {
        this.middleChars = middleChars;
        return this;
    }

    /**
     * Sets the Characters to use on the middle of the alternative prefix.
     *
     * @param altMiddleChars the alt middle chars
     * @return the instance of itself so methods can be chained
     */
    @Override
    public IPrefix setAltMiddleChars(String altMiddleChars) {
        this.altMiddleChars = altMiddleChars;
        return this;
    }

    /**
     * Sets the color for the middle of the prefix.
     *
     * @param middleColor the middle color from {@link org.bukkit.ChatColor}
     * @return the instance of itself so methods can be chained
     */
    @Override
    public PrefixBuilder setMiddleColor(ChatColor middleColor) {
        this.middleColor = middleColor;
        return this;
    }

    /**
     * Sets the style for the middle of the prefix.
     *
     * @param middleStyle the middle style from {@link org.bukkit.ChatColor}
     * @return the instance of itself so methods can be chained
     */
    @Override
    public PrefixBuilder setMiddleStyle(ChatColor middleStyle) {
        this.middleStyle = middleStyle;
        return this;
    }


    /**
     * Sets the Character to use on the right side of the prefix.
     *
     * @param rightChar the right chars
     * @return the instance of itself so methods can be chained
     */
    @Override
    public PrefixBuilder setRightChar(String rightChar) {
        this.rightChar = rightChar;
        return this;
    }

    /**
     * Sets the color for the right side of the prefix.
     *
     * @param rightColor the right color from {@link org.bukkit.ChatColor}
     * @return the instance of itself so methods can be chained
     */
    @Override
    public PrefixBuilder setRightColor(ChatColor rightColor) {
        this.rightColor = rightColor;
        return this;
    }

    /**
     * Sets the style for the right side of the prefix.
     *
     * @param rightStyle the right style from {@link org.bukkit.ChatColor}
     * @return the instance of itself so methods can be chained
     */
    @Override
    public PrefixBuilder setRightStyle(ChatColor rightStyle) {
        this.rightStyle = rightStyle;
        return this;
    }


/*    public Prefix createPrefixa() throws NullPointerException{
        return new Prefix(leftChar, leftColor, leftStyle, middleChars,altMiddleChars, middleColor, middleStyle, rightChar, rightColor, rightStyle);
    }*/

    /**
     * Creates a new instance of {@link com.relicum.ipsum.Commands.Group.Prefix}.
     * <p>Call this method at the end of the chain.
     *
     * @return the {@link com.relicum.ipsum.Commands.Group.Prefix}
     * @throws java.lang.NullPointerException when the middle characters have not been set and you call the createPrefix
     */
    public Prefix createPrefix() throws NullPointerException {

        buildPrefix();

        if (altMiddleChars == null)
            return new Prefix(String.format(prefix, middleChars), String.format(prefix, middleChars));
        else
            return new Prefix(String.format(prefix, middleChars), String.format(prefix, altMiddleChars));
    }

    private void buildPrefix() throws NullPointerException {
        if (middleChars == null)
            throw new NullPointerException("Middle Prefix Characters can not be null");
        sb = new StringBuilder();

        sb.append(leftColor);

        if (leftStyle != ChatColor.STRIKETHROUGH)
            sb.append(leftStyle);

        sb.append(leftChar);

        sb.append(middleColor);

        if (middleStyle != ChatColor.STRIKETHROUGH)
            sb.append(middleStyle);

        sb.append("%s");

        sb.append(rightColor);

        if (rightStyle != ChatColor.STRIKETHROUGH)
            sb.append(rightStyle);

        sb.append(rightChar);

        sb.append(ChatColor.RESET);

        prefix = sb.toString();

    }


    /**
     * Gets middle style.
     *
     * @return the middle style
     */
    public ChatColor getMiddleStyle() {
        return middleStyle;
    }

    /**
     * Gets right style.
     *
     * @return the right style
     */
    public ChatColor getRightStyle() {
        return rightStyle;
    }

    /**
     * Gets middle chars.
     *
     * @return the middle chars
     */
    public String getMiddleChars() {
        return middleChars;
    }

    /**
     * Gets left chars.
     *
     * @return the left chars
     */
    public String getLeftChar() {
        return leftChar;
    }

    /**
     * Gets right chars.
     *
     * @return the right chars
     */
    public String getRightChar() {
        return rightChar;
    }

    /**
     * Gets right color.
     *
     * @return the right color
     */
    public ChatColor getRightColor() {
        return rightColor;
    }

    /**
     * Gets middle color.
     *
     * @return the middle color
     */
    public ChatColor getMiddleColor() {
        return middleColor;
    }

    /**
     * Gets left style.
     *
     * @return the left style
     */
    public ChatColor getLeftStyle() {
        return leftStyle;
    }

    /**
     * Gets left color.
     *
     * @return the left color
     */
    public ChatColor getLeftColor() {
        return leftColor;
    }
}
