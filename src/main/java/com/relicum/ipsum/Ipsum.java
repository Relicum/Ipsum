package com.relicum.ipsum;

import com.relicum.ipsum.Configuration.ConfigManager;
import com.relicum.ipsum.Items.SimpleItemFactory;
import com.relicum.ipsum.Utils.WorldGenerator;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * The type Ipsum.
 */
public class Ipsum extends JavaPlugin {

    private SimpleItemFactory simpleItemFactory;
    private ConfigManager configManager;

    private static Ipsum instance;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Ipsum getInstance() {

        return instance;

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
        if (configManager == null) {
            configManager = new ConfigManager(this);
        }

        return configManager;
    }

    /**
     * Gets simple item factory.
     *
     * @return the {@link com.relicum.ipsum.Items.SimpleItemFactory}
     */
    public SimpleItemFactory getSimpleItemFactory() {
        if (simpleItemFactory == null) {
            simpleItemFactory = new SimpleItemFactory(this);
        }

        return simpleItemFactory;

    }

    @Override
    public WorldGenerator getDefaultWorldGenerator(String worldName, String id) {

        return new WorldGenerator();
    }
}
