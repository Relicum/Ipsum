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
 * CustomSound our provided in a server resource pack.
 * <p>Non of the default sounds are overwritten.
 * <p>To use this you will need to add the web address where the server resource pack is automatically downloaded
 * if the user accepts. If they choose not to there is no errors they just won't hear any of the sounds.
 *
 * @author Relicum
 * @version 0.0.1
 */
public enum CustomSound {
    DOUBLE_KILL("doublekill"),
    FIRST_BLOOD("firstblood"),
    HOLY_SHIT("holyshit"),
    KILLING_SPREE("lillingspree"),
    MULTI_KILL("muiltikill"),
    PREPARE_TO_FIGHT("prepare"),
    TRAIN1("train1"),
    TRIPLE_KILL("triplekill"),
    ULTRA_KILL("ultrakill"),
    UNSTOPABBLE("unstop"),
    WICKED_SLICK("wickedslick"),
    NO_PERM1("no_perm"),
    NO_PERM2("no_perm2"),
    NO_PERM3("no_perm3"),
    NO_PERM4("no_perm4"),
    NO_PERM5("no_perm5"),
    NO_PERM6("no_perm6");


    private final String sound;

    private CustomSound(String s) {
        this.sound = s;
    }

    /**
     * Get sound.
     *
     * @return the string
     */
    public String getSound() {
        return sound;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return sound;
    }

}
