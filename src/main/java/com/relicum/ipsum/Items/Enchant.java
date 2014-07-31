package com.relicum.ipsum.Items;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.enchantments.Enchantment;

/**
 * Very Simple class to build enchants to apply to items.
 *
 * @author Relicum
 * @version 0.0.1
 */
@Data
@AllArgsConstructor
public class Enchant {

    private final Enchantment enchantment;
    private final int level;
    private boolean force;

}
