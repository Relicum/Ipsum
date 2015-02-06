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
 * MenuState holds the different states a menu can be in.
 *
 * <p>
 * ENABLED and the menu is active and available for use.
 * <p>
 * DISABLED and the menu is not avaiable for use or modification.
 * <p>
 * EDIT and the menu is not avaiable for player usage but can be opened and
 * edited by players with the permissions to do so.
 *
 * @author Relicum
 * @version 0.0.1
 */
public enum MenuState {

    ENABLED, DISABLED, EDIT
}
