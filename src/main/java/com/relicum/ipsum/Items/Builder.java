package com.relicum.ipsum.Items;

import org.bukkit.inventory.ItemStack;

/**
 * Builder interface. Single method takes no arguments and
 * returns an {@link org.bukkit.inventory.ItemStack}
 *
 * @author Relicum
 * @version 0.0.1
 */
public interface Builder {

    /**
     * Build item stack.
     *
     * @return the item stack
     */
    public ItemStack build();


}
