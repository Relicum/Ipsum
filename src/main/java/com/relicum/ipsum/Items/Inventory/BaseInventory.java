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

package com.relicum.ipsum.Items.Inventory;

import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * Name: BaseInventory.java Created: 21 November 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class BaseInventory implements BaseArmor, BaseContents {

    private ItemStack[] armor;
    private ItemStack[] contents;

    public BaseInventory(ItemStack[] ar, ItemStack[] contents) {
        this.armor = new ItemStack[4];
        this.contents = contents;
        this.armor = ar;

    }

    public BaseInventory() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemStack[] getArmor() {
        return armor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setArmor(ItemStack[] armor) {

        this.armor = armor;

/*
        this.armor[0]=new ItemStack(Material.DIAMOND_BOOTS);
        this.armor[1]=new ItemStack(Material.DIAMOND_LEGGINGS);
        this.armor[2]=new ItemStack(Material.DIAMOND_CHESTPLATE);
        this.armor[3]=new ItemStack(Material.DIAMOND_HELMET);*/

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContents(ItemStack[] contents) {

        this.contents = contents;

/*        ItemStack i1 = new ItemStack(Material.DIAMOND_AXE,1);
        ItemMeta meta = i1.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD +"Super Axe");
        meta.addEnchant(Enchantment.DIG_SPEED,2,true);
        meta.setLore(Arrays.asList(ChatColor.GREEN + "The first",ChatColor.RED + "The second"));
        i1.setItemMeta(meta);
        this.contents[0]=new ItemStack(Material.GOLD_BOOTS,1);
        this.contents[1]=i1;
        this.contents[2]=new ItemStack(Material.GOLD_CHESTPLATE,1);
        this.contents[3]=new ItemStack(Material.GOLD_HELMET,1);*/
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemStack[] getContents() {
        return contents;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BaseInventory{");
        sb.append("armor=").append(armor == null ? "null" : Arrays.asList(armor).toString());
        sb.append(", contents=").append(contents == null ? "null" : Arrays.asList(contents).toString());
        sb.append('}');
        return sb.toString();
    }
}
