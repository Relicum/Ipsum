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

import net.minecraft.util.com.google.common.collect.Maps;
import org.apache.commons.lang.Validate;

import java.util.Map;

/**
 * Name: LocaleManager.java Created: 09 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class LocaleManager {

    private Map<String, String> resources = Maps.newHashMap();


    /**
     * Set a single MessageBundle resource.
     *
     * @param key the key
     * @param val the val
     */
    public void setResource(String key, String val) {
        Validate.notNull(key, "setResource can not have null passed as a argument");
        Validate.notNull(val, "setResource can not have null passed as a argument");
        this.resources.putIfAbsent(key, val);
    }

    /**
     * Gets a specified MessageBundle resource.
     *
     * @param key the key
     * @return the string
     */
    public String getResource(String key) {
        Validate.notNull(key, "setResource can not have null passed as a argument");
        try {
            return resources.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Sets new resources.
     *
     * @param resources New value of resources.
     */
    public void setResources(Map<String, String> resources) {
        this.resources = resources;
    }

    /**
     * Gets resources.
     *
     * @return Value of resources.
     */
    public Map<String, String> getResources() {
        return resources;
    }
}
