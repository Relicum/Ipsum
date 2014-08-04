package com.relicum.ipsum.Items;

import org.bukkit.Material;
import org.bukkit.SkullType;

/**
 * SimpleItemFactory Single entry point to build any item or collections of items.
 * <p>Its only function is to build the require item/items returning the item stacks.
 * Other modules will be integrated to provide further function abilities.</p>
 *
 * @author Relicum
 * @version 0.0.1
 */
public class SimpleItemFactory {

    private SimpleItemFactory() {

    }

    /**
     * Gets item builder.
     *
     * @param material the material that the item is made from
     * @param amount   the amount of items in the stack
     * @param type     the {@link com.relicum.ipsum.Items.MetaType} of the item
     * @return the {@link com.relicum.ipsum.Items.ItemBuilder} used to build standard items.
     */
    public static ItemBuilder getItemBuilder(Material material, int amount, MetaType type) {

            return new ItemBuilder(material, amount, type);
    }

    /**
     * Gets SkullBuilder.
     *
     * @param amount the amount of items in the stack.
     * @param type   the {@link org.bukkit.SkullType} used to determine the skull to build.
     * @return the {@link com.relicum.ipsum.Items.SkullBuilder} to build the skull.
     */
    public static SkullBuilder getSkullBuilder(int amount, SkullType type) {


        return new SkullBuilder(Material.SKULL_ITEM, amount, type);

    }

    /**
     * Gets ColorArmorBuilder.
     *
     * @param material must be a instance of a leather armor type
     * @param amount   the amount of items in the stack
     * @return the {@link com.relicum.ipsum.Items.ColorArmorBuilder} to build colored armor.
     */
    public static ColorArmorBuilder getColorArmorBuilder(Material material, int amount) {

        return new ColorArmorBuilder(material, amount, MetaType.COLORED_ARMOR);
    }
}
