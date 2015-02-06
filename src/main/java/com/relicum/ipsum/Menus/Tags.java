/*
 * Ipsum is a rapid development API for Minecraft, developer by Relicum
 * Copyright (C) 2015.  Chris Lutte
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

package com.relicum.ipsum.Menus;

/**
 * Name: Tags.java Created: 15 January 2015
 *
 * @author Relicum
 * @version 0.0.1
 */
public enum Tags {

    CUSTOMNAME("CustomName"), CUSTOMNAMEVISIBLE("CustomNameVisible"), INVULNERABLE("Invulnerable"), PERSISTENCEREQUIRED("PersistenceRequired"), SMALL("Small"), MARKER("Marker"), INVISIBLE("Invisible"), NOBASEPLATE("NoBasePlate"), NOGRAVITY("NoGravity"), DISABLEDSLOTS("DisabledSlots"), AGE("Age"), PROFESSION("Profession"), CAREER("Career"), CAREERLEVEL("CareerLevel"), RIDING("Riding"), ID("id"), PLAYERCREATED("PlayerCreated"), NOAI("NoAI"), SILENT("Silent"), CANPICKUPLOOT("CanPickUpLoot"), EQUIPMENT("Equipment"), COUNT("Count"), TAG("tag"), CANPLACEON("CanPlaceOn"), CANDESTROY("CanDestroy"), HIDEFLAGS("HideFlags"), MOTION("Motion"), ROTATION("Rotation"), LOCK("Lock"), SLOT("Slot"), SKELETONTYPE("SkeletonType");

    private final String tag;

    private Tags(String tag) {
        this.tag = tag;
    }

    public String getTag() {

        return tag;
    }

}
