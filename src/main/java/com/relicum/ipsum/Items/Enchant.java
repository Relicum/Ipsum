package com.relicum.ipsum.Items;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Builder;
import org.bukkit.enchantments.Enchantment;

/**
 * Very Simple class to build enchants to apply to items.
 *
 * @author Relicum
 * @version 0.0.1
 */
@Accessors(fluent = true)
@Builder
public class Enchant {
    @Getter
    @Setter
    private Enchantment enchantment;
    @Getter
    @Setter
    private int level;
    @Getter
    @Setter
    private boolean force;

}
