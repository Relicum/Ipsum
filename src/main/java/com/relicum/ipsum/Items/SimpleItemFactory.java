package com.relicum.ipsum.Items;

import org.bukkit.Material;

/**
 * SimpleItemFactory Single entry point to build any item or collections of items.
 * <p>Its only function is to build the require item/items returning the item stacks.
 * Other modules will be integrated to provide further function abilities.</p>
 *
 * @author Relicum
 * @version 0.0.1
 */
public class SimpleItemFactory {


    public static ItemBuilder getItemBuilder(Material material, int amount, MetaType type) {

            return new ItemBuilder(material, amount, type);
    }

    public static SkullMetaAdapter getSkullBuilder(Material material, int amount, MetaType type) {


        return new SkullMetaAdapter(material, 1, type);

    }
}
