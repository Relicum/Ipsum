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

package com.relicum.ipsum.Events;

import org.bukkit.entity.Player;
import com.relicum.ipsum.Menus.ActionType;
import com.relicum.ipsum.Menus.GUIMenu;

/**
 * Name: OpenEditMenuEvent.java Created: 05 February 2015
 *
 * @author Relicum
 * @version 0.0.1
 */
public class OpenEditMenuEvent extends CancelablePlayerEvent {

    private final GUIMenu menu;
    private final ActionType result;

    public OpenEditMenuEvent(Player player, GUIMenu menu, ActionType result) {
        super(player);
        this.menu = menu;
        this.result = result;

    }

    /**
     * Gets result.
     *
     * @return Value of result.
     */
    public ActionType getResult() {
        return result;
    }

    /**
     * Gets menu.
     *
     * @return Value of menu.
     */
    public GUIMenu getMenu() {
        return menu;
    }
}
