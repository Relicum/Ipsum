package com.relicum.ipsum.Items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.List;

/**
 * The interface that has the generic methods for setting properties that most items use.
 */
public interface GenericItem extends Builder {


    /**
     * Sets material.
     *
     * @param material the material
     * @return the instance of itself so methods can be chained
     */
    public abstract GenericItem setMaterial(Material material);

    /**
     * Sets amount. This is the number of Items the {@link org.bukkit.inventory.ItemStack} contains.
     *
     * @param i the number of items
     * @return the instance of itself so methods can be chained
     */
    public abstract GenericItem setAmount(int i);

    /**
     * Sets durability and also data value for things like Wool.
     *
     * @param durability or data value.
     * @return the instance of itself so methods can be chained
     */
    public abstract GenericItem setDurability(Short durability);


    /**
     * Sets ItemMetas displayName, use the <strong>&amp;</strong> to add color as per minecrafts color formatting.
     *
     * @param name the name
     * @return the instance of itself so methods can be chained
     */
    public abstract GenericItem setDisplayName(String name);

    /**
     * Sets item lores as a list of string.
     *
     * @param list the list making up the lore.
     * @return the instance of itself so methods can be chained
     */
    public GenericItem setItemLores(List<String> list);

    /**
     * Sets a single line to item lore.
     *
     * @param line the line
     * @return the instance of itself so methods can be chained
     */
    public abstract GenericItem setLore(String line);

    /**
     * Sets material data.
     *
     * @param materialData the material data
     * @return the instance of itself so methods can be chained
     */
    public abstract GenericItem setMaterialData(MaterialData materialData);

    /**
     * Add enchantment.
     *
     * @param ench  the ench
     * @param level the level
     * @return the instance of itself so methods can be chained
     */
    public abstract GenericItem addEnchantment(Enchantment ench, int level);


    /**
     * Add unsafe enchantment.
     *
     * @param ench  the ench
     * @param level the level
     * @return the instance of itself so methods can be chained
     */
    public abstract GenericItem addUnsafeEnchantment(Enchantment ench, int level);


    /**
     * Build item stack.
     *
     * @return the item stack
     */
    public abstract ItemStack build();

}
