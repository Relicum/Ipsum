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

package com.relicum.ipsum.Locale;

import org.apache.commons.lang.Validate;
import org.bukkit.plugin.Plugin;

import java.util.Locale;

/**
 * Simple Locale manager sets the Locale that is specified in the config.yml
 *
 * @author Relicum
 * @version 0.0.1
 */
public class LocaleManager {

    public LocaleManager(Plugin p) {
        Validate.notNull(p);

        String[] l = p.getConfig().getString("locale", "en_GB").split("_");
        setLocale(l[0], l[1]);


        if (p.getConfig().getBoolean("messages.saveFiles") && !p.getConfig().getBoolean("messages.devMode")) {

            saveToDisk(p);
            p.getConfig().set("messages.saveFiles", false);
        }


        p.getLogger().info("Language locale has been set to " + Locale.getDefault().toLanguageTag());
    }


    /**
     * Get the current locale currently set.
     *
     * @return locale in tag format of the current locale set.
     */
    public String getLocale() {
        return Locale.getDefault().toLanguageTag();
    }

    /**
     * Set new locale
     *
     * @param langCode    the lang code
     * @param countryCode the country code
     */
    public void setLocale(String langCode, String countryCode) {

        Locale.setDefault(new Locale(langCode, countryCode));
    }

    /**
     * Save all the language files to plugin folder.
     * <p>Needs work on this as not very flexible at all
     *
     * @param plugin instance of {@link org.bukkit.plugin.Plugin}
     */
    public void saveToDisk(Plugin plugin) {


        plugin.saveResource("MessagesBundle.properties", true);
        plugin.saveResource("MessagesBundle_en_GB.properties", true);
        plugin.saveResource("MessagesBundle_en_US.properties", true);
        plugin.getLogger().info("Message files saved to plugin folder");

    }
}
