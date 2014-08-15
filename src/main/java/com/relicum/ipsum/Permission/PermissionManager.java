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

import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.PluginManager;

/**
 * PermissionManager mainly used to register dynamic permissions.
 * <p>This is accessible from the main plugin class. You can also unregister permissions from this.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class PermissionManager {

    /**
     * The PluginManager
     */
    private PluginManager pm = Bukkit.getPluginManager();


    /**
     * Instantiates a new Permission manager.
     */
    public PermissionManager() {


    }

    /**
     * Register permission.
     *
     * @param name              the name
     * @param description       the description
     * @param parent            the parent
     * @param permissionDefault the permission default
     * @return the {@link java.lang.Boolean} true if successfully added, false if there is an error
     */
    public boolean registerPermission(String name, String description, String parent, PermissionDefault permissionDefault) {

        try {
            pm.addPermission(new Permissions(name, description, permissionDefault, parent));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Register permission.
     *
     * @param name              the name
     * @param description       the description
     * @param permissionDefault the permission default
     * @return the {@link java.lang.Boolean} true if successfully added, false if there is an error
     */
    public boolean registerPermission(String[] name, String description, PermissionDefault permissionDefault) {
        try {
            pm.addPermission(new Permissions(name, description, permissionDefault));

            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Unregister permission.
     *
     * @param name the name
     */
    public void unregisterPermission(String name) {

        pm.removePermission(name);
    }

    /**
     * Unregister permission.
     *
     * @param permission the permission
     */
    public void unregisterPermission(Permission permission) {

        pm.removePermission(permission);
    }

}
