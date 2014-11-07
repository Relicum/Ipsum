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

package com.relicum.ipsum.Utils.Tool;

/**
 * Name: ToolAction.java Created: 29 October 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public enum ToolAction {

    LEFT_CLICK_INVENTORY,
    RIGHT_CLICK_INVENTORY,
    HOVER_OVER,
    DROP_TOOL,
    ADD_TO_INVENTORY,
    REMOVE_FROM_INVENTORY,
    HOLD_TOOL,
    RIGHT_CLICK_BLOCK,
    SNEAK_RIGHT_CLICK_AIR,
    SNEAK_LEFT_CLICK_BLOCK,
    SNEAK_LEFT_CLICK_AIR,
    SNEAK_RIGHT_CLICK_BLOCK,
    RIGHT_CLICK_AIR,
    LEFT_CLICK_BLOCK,
    LEFT_CLICK_AIR,
    PRESSURE,
    RIGHT_CLICK_LIVING,
    LEFT_CLICK_LIVING,
    ENCHANT_TOOL,
    MODIFY_META,
    INITIAL_ACTION,
    SEQUENCE_ACTION,

    CHARGEABLE_ACTION,
    RANDOM_ACTION,

    HEAL_OTHER,
    HEAL_SELF,

    RANKED_ACTION,

    DAMAGE_OTHER,
    DAMAGE_SELF,

}
