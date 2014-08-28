/*
 * Ipsum is a rapid development API for Minecraft, developer by Relicum
 * Copyright (C) 2014.  Chris Lutte
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
     * @param material the {@link org.bukkit.Material} the item is made from.
     * @param amount   the amount of items in the final ItemStack
     * @param type     the {@link com.relicum.ipsum.Items.MetaType} type of meta data the item requires
     */
    public ItemBuilder(Material material, int amount, MetaType type) {
        super(material, amount, type);
        setItemMeta();
    }

    /**
     * Instantiates a new Item builder.
     *
     * @param material the {@link org.bukkit.Material} the item is made from.
     * @param type     the {@link com.relicum.ipsum.Items.MetaType} type of meta data the item requires
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

        setStack(new ItemStack(getMaterial(), getAmount()));

        if ((Short) getDurability() != null) {
            getStack().setDurability(getDurability());
        }

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
