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

import org.apache.commons.lang.Validate;
import org.bukkit.configuration.ConfigurationSection;

/**
 * ConfigSectionBuilder is WIP
 *
 * @author Relicum
 * @version 0.0.1
 */
public class ConfigSectionBuilder {

    private ConfigurationSection section;

    public ConfigSectionBuilder(ConfigurationSection cs) {
        Validate.notNull(cs);
        this.section = cs;

    }


    /**
     * Gets ConfigurationSection
     *
     * @return the ConfigurationSection
     */
    public ConfigurationSection getSection() {
        return section;
    }
}
