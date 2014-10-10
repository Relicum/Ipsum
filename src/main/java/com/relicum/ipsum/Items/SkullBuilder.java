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

import net.minecraft.util.org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * SkullBuilder is used to build all types of Skulls.
 * This should be called using the {@link com.relicum.ipsum.Items.SimpleItemFactory#getSkullBuilder}
 *
 * @author Relicum
 * @version 0.0.1
 */
public class SkullBuilder extends AbstractItemBuilder<SkullBuilder> {

    private SkullMeta meta;
    private SkullType skullType;

    /**
     * Instantiates a new SkullBuilder
     *
     * @param material the {@link org.bukkit.Material} the item is made from.
     * @param amount   the amount of items in the final ItemStack
     * @param type     the type of skull {@link org.bukkit.SkullType}
     */
    public SkullBuilder(Material material, int amount, SkullType type) {
        super(material, amount, MetaType.SKULL_ITEM);
        skullType = type;
        this.setItemMeta();


    }

    /**
     * Instantiates a new SkullBuilder
     *
     * @param material the {@link org.bukkit.Material} the item is made from.
     * @param type     the type of skull {@link org.bukkit.SkullType}
     */
    public SkullBuilder(Material material, SkullType type) {
        super(material, MetaType.SKULL_ITEM);
        skullType = type;
        this.setItemMeta();
    }


    /**
     * Sets item meta to {@link org.bukkit.inventory.meta.SkullMeta}
     */
    @Override
    protected void setItemMeta() {
        meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
    }


    /**
     * Gets  skull item meta.
     *
     * @return the item meta as {@link org.bukkit.inventory.meta.SkullMeta}
     */
    public SkullMeta getItemMeta() {

        return this.meta;
    }


    /**
     * Gets skull owner.
     *
     * @return the owner of the skull
     */
    public String getOwner() {
        return meta.getOwner();
    }

    /**
     * Sets the owner of the skull.
     *
     * @param owner the owner
     * @return the instance of itself so methods can be chained
     * @throws Exception if there was a problem setting the skull owner
     */
    public SkullBuilder setOwner(String owner) throws Exception {
        Validate.notNull(owner, "Owner can not be null when setting the owner");
        if (!meta.setOwner(owner))
            throw new Exception("Error while trying to set Skull items owner");
        return this;
    }

    /**
     * Checks to see if the skull has an owner.
     *
     * @return true if the skull has an owner
     */
    public boolean hasOwner() {

        return getItemMeta().hasOwner();
    }

    /**
     * Build Skull item stack.
     *
     * @return the {@link org.bukkit.inventory.ItemStack}
     */
    @Override
    public ItemStack build() {


        setStack(new ItemStack(getMaterial(), getAmount(), (byte) skullType.ordinal()));

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
     * Gets skullType.
     *
     * @return Value of skullType.
     */
    public SkullType getSkullType() {
        return skullType;
    }
}
