package com.relicum.ipsum.Items;

import lombok.ToString;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


@ToString
public class ItemBuilder extends AbstractItemBuilder<ItemBuilder> {


    private ItemMeta meta;

    /**
     * Instantiates a new Item builder.
     *
     * @param material  the {@link org.bukkit.Material} the item is made from.
     * @param amount    the amount of items in the final ItemStack
     * @param type
     */
    public ItemBuilder(Material material, int amount, MetaType type) {
        super(material, amount, type);
        setItemMeta();
    }

    /**
     * Instantiates a new Item builder.
     *
     * @param material  the {@link org.bukkit.Material} the item is made from.
     * @param type the {@link com.relicum.ipsum.Items.MetaType} type of meta data the item requires
     */
    public ItemBuilder(Material material, MetaType type) {
        super(material, type);
        setItemMeta();
    }

    /**
     * Sets item meta.
     */
    @Override
    protected void setItemMeta() {
        meta = Bukkit.getItemFactory().getItemMeta(getMaterial());
    }

    /**
     * Build item stack.
     *
     * @return the item stack
     */
    @Override
    public ItemStack build() {
        System.out.println("Mat is " + getMaterial().name() + " and amount is " + getAmount());
        setStack(new ItemStack(getMaterial(), getAmount()));


        if (getDisplayName() != null)
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getDisplayName()));

        if (getLore() != null)
            meta.setLore(getLore());

        if (getEnchantments() != null) {
            for (Enchant enchant : getEnchantments()) {

                meta.addEnchant(enchant.enchantment(), enchant.level(), enchant.force());

            }
        }


        getStack().setItemMeta(meta);

        return getStack();
    }
}
