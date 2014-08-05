package com.relicum.ipsum;

import com.relicum.ipsum.Items.SimpleItemFactory;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * The type Ipsum.
 */
public class Ipsum extends JavaPlugin {

    private SimpleItemFactory simpleItemFactory;

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

    public SimpleItemFactory getSimpleItemFactory() {
        if (simpleItemFactory == null) {
            simpleItemFactory = new SimpleItemFactory(this);
        }

        return simpleItemFactory;

    }
}
