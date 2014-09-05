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

package com.relicum.ipsum;

import com.relicum.ipsum.Configuration.ConfigManager;
import com.relicum.ipsum.Items.SimpleItemFactory;
import com.relicum.ipsum.Permission.PermissionManager;
import com.relicum.ipsum.Utils.WorldGenerator;
import com.relicum.ipsum.Utils.WorldManager;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * The type Ipsum.
 */
public class Ipsum extends JavaPlugin {

    private static Ipsum instance;


    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Ipsum getInstance() {

        return instance;

    }

    public Ipsum getIpsum() {
        return this;
    }

    /**
     * On enable.
     */

    @Override
    public void onEnable() {

        instance = this;


    }

    /**
     * On disable.
     */
    @Override
    public void onDisable() {


    }


    /**
     * Get config manager.
     *
     * @return the {@link com.relicum.ipsum.Configuration.ConfigManager}
     */
    public ConfigManager getConfigManager() {

        return new ConfigManager(this);

    }

    /**
     * Gets simple item factory.
     *
     * @return the {@link com.relicum.ipsum.Items.SimpleItemFactory}
     */
    public SimpleItemFactory getSimpleItemFactory() {

        return new SimpleItemFactory();
    }

/*    public AbstractCommandRegister getCommandRegister() {
        if (commandRegister == null) {
            commandRegister = new AbstractCommandRegister(this);
        }
        return commandRegister;
    }*/

    public PermissionManager getPermissionManager() {

        return new PermissionManager();
    }

    public WorldManager getWorldManager() {

        return new WorldManager(this, getConfigManager());
    }


    @Override
    public WorldGenerator getDefaultWorldGenerator(String worldName, String id) {

        return new WorldGenerator();
    }
}
