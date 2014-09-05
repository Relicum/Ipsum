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
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * SetOfArmorBuilder builds a complete set of armor all items are from the same Material and have the same properties and enchantments etc.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class SetOfArmorBuilder extends AbstractItemBuilder<SetOfArmorBuilder> {
    private ArmourMaterials armourMaterials;
    private ItemMeta meta;

    /**
     * Instantiates a new Item builder.
     *
     * @param mat  the {@link org.bukkit.Material} the item is made from.
     * @param type the {@link com.relicum.ipsum.Items.MetaType} type of meta data the item requires
     */
    public SetOfArmorBuilder(ArmourMaterials mat, MetaType type) {
        super(Material.valueOf(mat.name() + "_BOOTS"), type);
        setItemMeta();
        armourMaterials = mat;
    }

    /**
     * Instantiates a new Item Builder
     *
     * @param mat    the {@link org.bukkit.Material} of the Item
     * @param amount the amount of items in the stack
     * @param type   the Meta type of the item {@link com.relicum.ipsum.Items.MetaType} provides the options.
     */
    public SetOfArmorBuilder(ArmourMaterials mat, int amount, MetaType type) {
        super(Material.valueOf(mat.name() + "_BOOTS"), amount, type);
        setItemMeta();
        armourMaterials = mat;
    }

    /**
     * Sets item meta that this item will use
     */
    @Override
    protected void setItemMeta() {

        meta = Bukkit.getItemFactory().getItemMeta(getMaterial());
    }

    /**
     * DO NOT CALL THIS BUILD METHOD CALL THE OTHER
     *
     * @return NULL
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

    /**
     * Build the set of Armor.
     *
     * @return the ItemStack[] containing the full set of items each with the same settings. Index 0 is boots.
     */
    public ItemStack[] buildArmor() {
        ItemStack[] itemStacks = new ItemStack[4];

        itemStacks[0] = build();
        this.setMaterial(Material.valueOf(armourMaterials + "_LEGGINGS"));
        itemStacks[1] = build();
        this.setMaterial(Material.valueOf(armourMaterials + "_CHESTPLATE"));
        itemStacks[2] = build();
        this.setMaterial(Material.valueOf(armourMaterials + "_HELMET"));
        itemStacks[3] = build();

        return itemStacks;
    }
}
