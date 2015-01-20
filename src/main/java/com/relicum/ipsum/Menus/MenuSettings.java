/*
 * Ipsum is a rapid development API for Minecraft, developer by Relicum
 * Copyright (C) 2015.  Chris Lutte
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

package com.relicum.ipsum.Menus;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import net.cubespace.Yamler.Config.*;
import org.bukkit.plugin.Plugin;

/**
 * Name: MenuSettings.java Created: 19 January 2015
 *
 * @author Relicum
 * @version 0.0.1
 */
public class MenuSettings extends Config {

    @Path("menu")
    private ConfigSection menu = new ConfigSection();

    @Comment("Individual menu item settings")
    @Path("menu.items")
    private Map<String, Item> items = new HashMap<>();

    public MenuSettings(Plugin plugin, String name) {

        CONFIG_FILE = new File(plugin.getDataFolder(), File.separator + "menu" + File.separator + name + ".yml");
        CONFIG_HEADER = new String[] { "This stores the settings for menu gui: " + name };

    }

    public MenuSettings() {
    }

    public void doInit() {

        try {
            this.init();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }

    }

    public void saveConfig() {

        try {
            this.save();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void reloadConfig() {

        try {
            this.reload();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets items.
     *
     * @param key the key
     * @return the items
     */
    public Item getItems(String key) {
        return items.get(key);
    }

    /**
     * Add item.
     *
     * @param key the key
     * @param item the item
     */
    public void addItem(String key, Item item) {
        this.items.put(key, item);
    }
}
