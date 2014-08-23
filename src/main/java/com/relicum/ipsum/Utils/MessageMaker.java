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

import com.relicum.ipsum.Annotations.NonNls;
import org.bukkit.util.Vector;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This is used to add all your plugin messages to which can then be organised and stored for Multi Lang.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class MessageMaker {
    Vector min = new Vector(1, 5, 9);
    Vector max = new Vector(4, 76, 9);
    @NonNls
    private static final ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", Locale.getDefault());


    public static void test() {


    }

    public boolean isAABB(Vector vector) {
        return vector.isInAABB(min, max);
    }

}
