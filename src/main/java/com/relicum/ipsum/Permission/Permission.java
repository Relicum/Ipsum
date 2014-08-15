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

package com.relicum.ipsum.Permission;

/**
 * The type Permission.
 */
public class Permission {

    /**
     * The Permission.
     */
    protected String permission;

    /**
     * Instantiates a new Permission.
     *
     * @param permission the permission
     * @param isDefault  the is default
     */
    public Permission(String permission, final boolean isDefault) {
        this.permission = permission;
    }

    /**
     * Instantiates a new Permission.
     *
     * @param permission the permission
     */
    public Permission(String permission) {
        this(permission, false);
    }

    /**
     * Get raw permission.
     *
     * @return the string
     */
    public String getRawPermission() {
        return this.permission;
    }
}
