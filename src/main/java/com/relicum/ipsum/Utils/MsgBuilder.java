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
 * Msg Format Builder
 */
public class MsgBuilder {
    /**
     * The Prefix.
     */
    private String prefix;
    /**
     * The Info color.
     */
    private String infoColor;
    /**
     * The Error color.
     */
    private String errorColor;
    /**
     * The Admin color.
     */
    private String adminColor;
    /**
     * The Log prefix.
     */
    private String logPrefix;
    /**
     * The Log info color.
     */
    private String logInfoColor;
    /**
     * The Log severe color.
     */
    private String logSevereColor;
    /**
     * The Log warning color.
     */
    private String logWarningColor;
    /**
     * The Prefix no color.
     */
    private String prefixNoColor;
    /**
     * The Announce color.
     */
    private String announceColor;

    /**
     * The High light.
     */
    private String highLight;
    /**
     * The Header.
     */
    private String header;
    /**
     * The Footer.
     */
    private String footer;


    public MsgBuilder(String prefix, String infoColor, String errorColor,
                      String adminColor, String logPrefix, String logInfoColor, String logSevereColor,
                      String logWarningColor, String prefixNoColor, String announceColor,
                      String highLight, String header, String footer) {
        this.prefix = prefix;
        this.infoColor = infoColor;
        this.errorColor = errorColor;
        this.adminColor = adminColor;
        this.logPrefix = logPrefix;
        this.logInfoColor = logInfoColor;
        this.logSevereColor = logSevereColor;
        this.logWarningColor = logWarningColor;
        this.prefixNoColor = prefixNoColor;
        this.announceColor = announceColor;
        this.highLight = highLight;
        this.header = header;
        this.footer = footer;
    }

    public MsgBuilder() {

    }

    /**
     * Sets prefix color
     *
     * @param prefix the prefix
     * @return the prefix
     */
    public MsgBuilder setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    /**
     * Sets info color.
     *
     * @param infoColor the info color
     * @return the info color
     */
    public MsgBuilder setInfoColor(String infoColor) {
        this.infoColor = infoColor;
        return this;
    }

    /**
     * Sets error color.
     *
     * @param errorColor the error color
     * @return the error color
     */
    public MsgBuilder setErrorColor(String errorColor) {
        this.errorColor = errorColor;
        return this;
    }

    /**
     * Sets admin color.
     *
     * @param adminColor the admin color
     * @return the admin color
     */
    public MsgBuilder setAdminColor(String adminColor) {
        this.adminColor = adminColor;
        return this;
    }

    /**
     * Sets log prefix.
     *
     * @param logPrefix the log prefix
     * @return the log prefix
     */
    public MsgBuilder setLogPrefix(String logPrefix) {
        this.logPrefix = logPrefix;
        return this;
    }

    /**
     * Sets log info color.
     *
     * @param logInfoColor the log info color
     * @return the log info color
     */
    public MsgBuilder setLogInfoColor(String logInfoColor) {
        this.logInfoColor = logInfoColor;
        return this;
    }

    /**
     * Sets log severe color.
     *
     * @param logSevereColor the log severe color
     * @return the log severe color
     */
    public MsgBuilder setLogSevereColor(String logSevereColor) {
        this.logSevereColor = logSevereColor;
        return this;
    }

    /**
     * Sets log warning color.
     *
     * @param logWarningColor the log warning color
     * @return the log warning color
     */
    public MsgBuilder setLogWarningColor(String logWarningColor) {
        this.logWarningColor = logWarningColor;
        return this;
    }

    /**
     * Sets prefix no color.
     *
     * @param prefixNoColor the prefix no color
     * @return the prefix no color
     */
    public MsgBuilder setPrefixNoColor(String prefixNoColor) {
        this.prefixNoColor = prefixNoColor;
        return this;
    }

    /**
     * Sets announce color.
     *
     * @param announceColor the announce color
     * @return the announce color
     */
    public MsgBuilder setAnnounceColor(String announceColor) {
        this.announceColor = announceColor;
        return this;
    }

    /**
     * Sets highlight color.
     *
     * @param highLight the high light
     * @return the high light
     */
    public MsgBuilder setHighLight(String highLight) {
        this.highLight = highLight;
        return this;
    }

    /**
     * Sets header.
     *
     * @param header the header
     * @return the header
     */
    public MsgBuilder setHeader(String header) {
        this.header = Msg.reColorizer(header);
        return this;
    }

    /**
     * Sets footer.
     *
     * @param footer the footer
     * @return the footer
     */
    public MsgBuilder setFooter(String footer) {
        this.footer = Msg.reColorizer(footer);
        return this;
    }

    /**
     * Create Msg Settings Object
     *
     * @return the msg
     */
    public Msg createMsg() {
        return new Msg(prefix, infoColor, errorColor, adminColor, logPrefix,
                logInfoColor, logSevereColor, logWarningColor, prefixNoColor,
                announceColor, highLight, header, footer);
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MsgBuilder{");
        sb.append("prefix='").append(prefix).append('\'');
        sb.append(", infoColor='").append(infoColor).append('\'');
        sb.append(", errorColor='").append(errorColor).append('\'');
        sb.append(", adminColor='").append(adminColor).append('\'');
        sb.append(", logPrefix='").append(logPrefix).append('\'');
        sb.append(", logInfoColor='").append(logInfoColor).append('\'');
        sb.append(", logSevereColor='").append(logSevereColor).append('\'');
        sb.append(", logWarningColor='").append(logWarningColor).append('\'');
        sb.append(", prefixNoColor='").append(prefixNoColor).append('\'');
        sb.append(", announceColor='").append(announceColor).append('\'');
        sb.append(", highLight='").append(highLight).append('\'');
        sb.append(", header='").append(header).append('\'');
        sb.append(", footer='").append(footer).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
