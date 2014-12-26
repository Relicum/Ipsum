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

/**
 * (c) 2014 dmulloy2
 */
package com.relicum.ipsum.Reflection;

import com.relicum.ipsum.Exception.ReflectionException;

/**
 * @author dmulloy2
 */

// TODO: Keep up to date with MC versions. 1.7.10
public class WrappedChatPacket extends WrappedPacket {
    private static final String NMS_CLASS_NAME = "PacketPlayOutChat";
    private static final Class<?> chatComponentClass = ReflectionUtil.getNMSClass("IChatBaseComponent");

    public WrappedChatPacket(Object chatComponent) throws ReflectionException {

        try {
            this.nmsClass = ReflectionUtil.getNMSClass(NMS_CLASS_NAME);
            this.constructor = nmsClass.getConstructor(chatComponentClass);
            this.nmsHandle = constructor.newInstance(chatComponent);
        } catch (Throwable ex) {
            throw ReflectionException.fromThrowable("Constructing wrapped chat packet", ex);
        }
    }

}
