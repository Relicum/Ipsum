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

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

/**
 * ColorArmorBuilder used to build colored leather armor.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class ColorArmorBuilder extends AbstractItemBuilder<ColorArmorBuilder> {

    private LeatherArmorMeta meta;

    /**
     * Instantiates new ColorArmorBuilder.
     *
     * @param material the {@link org.bukkit.Material} the item is made from.
     * @param type     the {@link com.relicum.ipsum.Items.MetaType} type of meta data the item requires
     */
    public ColorArmorBuilder(Material material, MetaType type) {
        super(material, type);
        setItemMeta();
    }

    /**
     * Instantiates a new Item Builder
     *
     * @param material the {@link org.bukkit.Material} of the Item
     * @param amount   the amount of items in the stack
     * @param type     the Meta type of the item {@link com.relicum.ipsum.Items.MetaType} provides the options.
     */
    public ColorArmorBuilder(Material material, int amount, MetaType type) {
        super(material, amount, type);
        setItemMeta();

    }


    /**
     * Set color of the leather armor item
     *
     * @param color the {@link org.bukkit.Color} used to dye the armour.
     * @return the instance of itself so methods can be chained.
     */
    public ColorArmorBuilder setColor(Color color) {

        this.meta.setColor(color);
        return this;
    }


    /**
     * Sets item meta that this item will use
     */
    @Override
    protected void setItemMeta() {

        meta = (LeatherArmorMeta) Bukkit.getItemFactory().getItemMeta(material);
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
