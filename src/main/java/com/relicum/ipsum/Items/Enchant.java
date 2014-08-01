package com.relicum.ipsum.Items;

import org.bukkit.enchantments.Enchantment;

/**
 * Very Simple class to build enchants to apply to items.
 *
 * @author Relicum
 * @version 0.0.1
 */

@lombok.experimental.Builder
public class Enchant {

    private final Enchantment enchantment;
    private final int level;
    private boolean force;

}
