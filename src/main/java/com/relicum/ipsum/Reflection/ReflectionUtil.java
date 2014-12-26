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

import com.relicum.ipsum.Exception.Versioning;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Util for dealing with Java Reflection.
 *
 * @author dmulloy2
 */

public class ReflectionUtil {
    private ReflectionUtil() {
    }

    private static String PACKAGE;

    /**
     * Attempts to get the NMS (net.minecraft.server) class with this name.
     * While this does cross versions, it's important to note that this class
     * may have been changed or removed.
     *
     * @param name Class Name
     * @return NMS class, or null if none exists
     */
    public static final Class<?> getNMSClass(String name) {
        Validate.notNull(name, "name cannot be null!");

        if (PACKAGE == null) {
            // Lazy-load PACKAGE
            String serverPackage = Bukkit.getServer().getClass().getPackage().getName();
            PACKAGE = serverPackage.substring(serverPackage.lastIndexOf('.') + 1);
        }

        name = "net.minecraft.server." + PACKAGE + "." + name;

        try {
            return Class.forName(name);
        } catch (Throwable ex) {
        }
        return null;
    }

    /**
     * Attempts to get the OBC (org.bukkit.craftbukkit) class with this name.
     * While this does cross versions, it's important to note that this class
     * may have been changed or removed.
     *
     * @param name Class Name
     * @return OBC class, or null if none exists
     */
    public static final Class<?> getOBCClass(String name) {
        Validate.notNull(name, "name cannot be null!");

        if (PACKAGE == null) {
            // Lazy-load VERSION
            String serverPackage = Bukkit.getServer().getClass().getPackage().getName();
            PACKAGE = serverPackage.substring(serverPackage.lastIndexOf('.') + 1);
        }

        name = "org.bukkit.craftbukkit." + PACKAGE + "." + name;

        try {
            return Class.forName(name);
        } catch (Throwable ex) {
        }
        return null;
    }

    /**
     * Gets a {@link Field} in a given {@link Class} object.
     *
     * @param clazz Class object
     * @param name  Field nameame
     * @return The field, or null if none exists.
     */
    public static final Field getField(Class<?> clazz, String name) {
        Validate.notNull(clazz, "clazz cannot be null!");
        Validate.notNull(name, "name cannot be null!");

        try {
            Field field = clazz.getDeclaredField(name);
            if (field != null)
                return field;

            return clazz.getField(name);
        } catch (Throwable ex) {
        }
        return null;
    }

    /**
     * Whether or not a {@link Field} exists in a given {@link Class}.
     *
     * @param clazz Class object
     * @param name  Field name
     * @return True if the field exists, false if not
     */
    public static final boolean fieldExists(Class<?> clazz, String name) {
        return getField(clazz, name) != null;
    }

    /**
     * Gets a {@link Method} in a given {@link Class} object with the specified
     * arguments.
     *
     * @param clazz Class object
     * @param name  Method name
     * @param args  Arguments
     * @return The method, or null if none exists
     */
    public static final Method getMethod(Class<?> clazz, String name, Class<?>... args) {
        Validate.notNull(clazz, "clazz cannot be null!");
        Validate.notNull(name, "name cannot be null!");
        if (args == null) args = new Class<?>[0];

        for (Method method : clazz.getMethods()) {
            if (method.getName().equals(name) && Arrays.equals(args, method.getParameterTypes()))
                return method;
        }

        return null;
    }

    /**
     * Gets a {@link Method} in a given {@link Class} object.
     *
     * @param clazz Class object
     * @param name  Method name
     * @return The method, or null if none exists
     */
    public static final Method getMethod(Class<?> clazz, String name) {
        Validate.notNull(clazz, "clazz cannot be null!");
        Validate.notNull(name, "name cannot be null!");

        for (Method method : clazz.getMethods()) {
            if (method.getName().equals(name))
                return method;
        }

        return null;
    }

    /**
     * Gets the handle of a given object. This only works for classes that
     * declare the getHandle() method, like CraftPlayer.
     *
     * @param object Object to get the handle for
     * @return The handle, or null if none exists
     */
    public static final Object getHandle(Object object) {
        Validate.notNull(object, "object cannot be null!");

        Method getHandle = getMethod(object.getClass(), "getHandle");

        try {
            return getHandle.invoke(object, new Object[0]);
        } catch (Throwable ex) {
        }
        return null;
    }

    // ---- Versioning

    private static final Versioning.Version SUPPORTED = Versioning.Version.MC_17;

    /**
     * Whether or not a {@link Player} can reliably be sent packets. This works
     * by checking the player's client {@link com.relicum.ipsum.Exception.Versioning.Version} against the supported
     * version.
     *
     * @param player Player to check
     * @return True if they can reliably be sent packets, false if not
     */
    public static final boolean isReflectionSupported(Player player) {
        return SUPPORTED == getClientVersion(player);
    }

    /**
     * Returns the client {@link com.relicum.ipsum.Exception.Versioning.Version} a given {@link Player} is using.
     *
     * @param player Player to get version for
     * @return Their client version
     */
    public static final Versioning.Version getClientVersion(Player player) {
        Validate.notNull(player, "player cannot be null!");

        try {
            Object handle = getHandle(player);
            Field playerConnectionField = getField(handle.getClass(), "playerConnection");
            Object playerConnection = playerConnectionField.get(handle);

            Field networkManagerField = getField(playerConnection.getClass(), "networkManager");
            Object networkManager = networkManagerField.get(playerConnection);

            Method getVersion = getMethod(networkManager.getClass(), "getVersion", new Class<?>[0]);
            int version = (int) getVersion.invoke(networkManager);
            switch (version) {
                case 4:
                case 5:
                    return Versioning.Version.MC_17;
                case 47:
                    return Versioning.Version.MC_18;
                default:
                    return Versioning.Version.UNKNOWN;
            }
        } catch (Throwable ex) {
        }
        return Versioning.Version.UNKNOWN;
    }
}
