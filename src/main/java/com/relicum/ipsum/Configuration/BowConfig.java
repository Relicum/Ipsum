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

package com.relicum.ipsum.Configuration;

import net.cubespace.Yamler.Config.Comment;
import net.cubespace.Yamler.Config.Config;
import net.cubespace.Yamler.Config.Path;

/**
 * Name: BowConfigs.java Created: 22 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */

public class BowConfig extends Config {

    @Comment("Human readable name of the bow")
    @Path("name")
    private String name = "default";
    @Comment("Shooter of the Bow is an instance of Player")
    @Path("shootIsPlayer")
    private Boolean shooterPlayer = true;
    @Comment("Set to true if you want to identify the bow by display name. If false applies to any bow")
    @Path("useDisplayName")
    private Boolean useDisplayName = false;
    @Comment("The text used to set the display name. Max chars is 16 or 14 if a color code is used")
    @Path("displayName")
    private String displayName = "";
    @Comment("Set if the bow was the quick drawn feature")
    @Path("useQuickDrawn")
    private Boolean useQuickDrawn = false;
    @Comment("Set what the bow force max force that quick draw will activate at. Between 0 and 1")
    @Path("quickDrawThreshold")
    private float quickDrawThreshold = 2.0F;
    @Comment("Set the speed of the bow if quick draw is true")
    @Path("quickDraw")
    private double quickDraw = 3.0D;
    @Comment("Set the speed of the arrow when its above the threshold")
    @Path("aboveThreshold")
    private double aboveQuickDrawThreshold = 4.0D;
    @Comment("Is there a random chance of a bonus happening")
    @Path("isRandom")
    private Boolean isRandom = false;
    @Comment("Random chance out of 100, setting 100 means a 100% chance")
    @Path("randomChance")
    private int randomChance = 50;

    public BowConfig() {

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getShooterPlayer() {
        return this.shooterPlayer;
    }

    public void setShooterPlayer(Boolean shooterPlayer) {
        this.shooterPlayer = shooterPlayer;
    }

    public Boolean getUseDisplayName() {
        return this.useDisplayName;
    }

    public void setUseDisplayName(Boolean useDisplayName) {
        this.useDisplayName = useDisplayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Boolean getUseQuickDrawn() {
        return this.useQuickDrawn;
    }

    public void setUseQuickDrawn(Boolean useQuickDrawn) {
        this.useQuickDrawn = useQuickDrawn;
    }

    public Float getQuickDrawThreshold() {
        return this.quickDrawThreshold;
    }

    public void setQuickDrawThreshold(float quickDrawThreshold) {
        this.quickDrawThreshold = quickDrawThreshold;
    }

    public double getQuickDraw() {
        return this.quickDraw;
    }

    public void setQuickDraw(double quickDraw) {
        this.quickDraw = quickDraw;
    }

    public double getAboveQuickDrawThreshold() {
        return aboveQuickDrawThreshold;
    }

    public void setAboveQuickDrawThreshold(double aboveQuickDrawThreshold) {
        this.aboveQuickDrawThreshold = aboveQuickDrawThreshold;
    }

    public Boolean getIsRandom() {
        return isRandom;
    }

    public void setIsRandom(Boolean isRandom) {
        this.isRandom = isRandom;
    }

    public int getRandomChance() {
        return randomChance;
    }

    public void setRandomChance(int randomChance) {
        this.randomChance = randomChance;
    }
}
