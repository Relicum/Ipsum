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
package com.relicum.ipsum.Exception;

import org.bukkit.Bukkit;

/**
 * @author dmulloy2
 */

public class Versioning {
    private Versioning() {
    }

    /**
     * Represents a supported Minecraft version
     */
    public static enum Version {
        UNKNOWN,
        MC_16,
        MC_17,
        MC_18,;
    }

    private static Version version;

    /**
     * Gets the {@link Version} that this server is currently running.
     *
     * @return The version
     */
    public static final Version getVersion() {
        if (version == null) {
            String versionString = Bukkit.getVersion();
            if (versionString.contains("1.8"))
                version = Version.MC_18;
            else if (versionString.contains("1.7"))
                version = Version.MC_17;
            else if (versionString.contains("1.6"))
                version = Version.MC_16;
            else
                version = Version.UNKNOWN;
        }

        return version;
    }

    /**
     * Gets the version string that this server is currently running.
     *
     * @return The version string
     */
    public static String getVersionString() {
        switch (getVersion()) {
            case MC_16:
                return "Minecraft 1.6.x";
            case MC_17:
                return "Minecraft 1.7.x";
            case MC_18:
                return "Minecraft 1.8.x";
            default:
                return "Unknown";
        }
    }

    /**
     * Whether or not the currently running version is supported.
     *
     * @return True if it is supported, false if not
     */
    public static final boolean isSupported() {
        return getVersion() != Version.UNKNOWN;
    }
}
