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

import net.minecraft.util.org.apache.commons.lang3.Validate;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

/**
 * ConfigSectionBuilder is WIP
 *
 * @author Relicum
 * @version 0.0.1
 */
public class ConfigSectionBuilder {

    private ConfigurationSection section;

    private ConfigSectionBuilder(ConfigurationSection cs) {
        Validate.notNull(cs);
        this.section = cs;

    }

    public static ConfigSectionBuilder newConfigSectionBuilder(ConfigurationSection cs) {
        return new ConfigSectionBuilder(cs);
    }


    /**
     * Gets ConfigurationSection
     *
     * @return the ConfigurationSection
     */
    public ConfigurationSection getSection() {

        return section;
    }


    public void setSection(String path, Object object) {

        section.set(path, object);
    }


    public List<String> getStringList(String path) {

        return section.getStringList(path);
    }

    public Boolean getBoolean(String path) {

        return section.getBoolean(path);
    }

    public Integer getInteger(String path) {

        return section.getInt(path);
    }

    public String getString(String path) {

        return section.getString(path);
    }
}
