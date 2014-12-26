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
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author dmulloy2
 */

// TODO: Keep up to date with MC versions. 1.7.10
public abstract class WrappedPacket extends AbstractWrapper {
    public final void send(Player player) throws ReflectionException {
        try {
            Object nmsPlayer = ReflectionUtil.getHandle(player);
            Field playerConnectionField = ReflectionUtil.getField(nmsPlayer.getClass(), "playerConnection");
            Object playerConnection = playerConnectionField.get(nmsPlayer);
            Method sendPacket = ReflectionUtil.getMethod(playerConnection.getClass(), "sendPacket");
            sendPacket.invoke(playerConnection, nmsHandle);
        } catch (Throwable ex) {
            throw ReflectionException.fromThrowable("Sending packet to " + player.getName(), ex);
        }
    }

    public final void sendToServer(Player player) throws ReflectionException {
        try {
            Object nmsPlayer = ReflectionUtil.getHandle(player);
            Field playerConnectionField = ReflectionUtil.getField(nmsPlayer.getClass(), "playerConnection");
            Object playerConnection = playerConnectionField.get(nmsPlayer);
            Method a = ReflectionUtil.getMethod(playerConnection.getClass(), "a", nmsClass);
            a.invoke(playerConnection, nmsHandle);
        } catch (Throwable ex) {
            throw ReflectionException.fromThrowable("Sending packet from " + player.getName() + " to the server", ex);
        }
    }
}
