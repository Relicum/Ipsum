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

package com.relicum.ipsum.Commands;

import java.util.List;


/**
 * The interface Base command.
 *
 * @author Relicum
 * @version 0.0.1
 */
public interface BaseCommand {


    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription();

    /**
     * Gets usage.
     *
     * @return the usage
     */
    public String getUsage();

    /**
     * Gets permission.
     *
     * @return the permission
     */
    public String getPermission();

    /**
     * Is player only.
     *
     * @return the boolean
     */
    public boolean isPlayerOnly();

    /**
     * Gets min args.
     *
     * @return the min args
     */
    public int getMinArgs();

    /**
     * Gets max args.
     *
     * @return the max args
     */
    public int getMaxArgs();

    /**
     * Is sub.
     *
     * @return the boolean
     */
    public boolean isSub();

    /**
     * Gets plugin.
     *
     * @return the plugin
     */
    // public T getThePlugin();

    /**
     * Gets permission message.
     *
     * @return the permission message
     */
    public String getPermissionMessage();

    /**
     * Gets label.
     *
     * @return the label
     */
    public String getLabel();

    /**
     * Gets cmd name.
     *
     * @return the cmd name
     */
    public String getCmdName();

    /**
     * Gets aliases.
     *
     * @return the command aliases
     */
    public List<String> getAliases();

}
