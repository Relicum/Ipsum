/*
 * Ipsum is a rapid development API for Minecraft, developer by Relicum
 * Copyright (C) 2015.  Chris Lutte
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

package com.relicum.ipsum.Menus;

import com.relicum.ipsum.Items.MetaType;
import com.relicum.ipsum.Items.SimpleItemFactory;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Name: MenuItem.java Created: 14 January 2015
 *
 * @author Relicum
 * @version 0.0.1
 */
public class MenuItem extends AbstractMenuItem {


    public MenuItem() {
    }

    public MenuItem(Material itemMaterial, byte dataBit, int itemAmount, MetaType metaType) {
        super(itemMaterial, dataBit, itemAmount, metaType);
    }

    public MenuItem(Material itemMaterial, int itemAmount, MetaType metaType) {
        super(itemMaterial, itemAmount, metaType);
    }

    @Override
    public String getPermission() throws UnsupportedOperationException {
        if (permission != null)
            return permission;
        else throw new UnsupportedOperationException("Permissions have not ben enabled for this item");
    }

    @Override
    public float getCostPerUse() {
        return cost;
    }


    @Override
    public ItemStack getItem() {
        if (dataBit == -1)
            return new SimpleItemFactory().getItemBuilder(itemMaterial, itemAmount, metaType).setDisplayName(this.displayName).setItemLores(lore).build();

        else
            return new SimpleItemFactory().getItemBuilder(itemMaterial, itemAmount, dataBit, metaType).setDisplayName(this.displayName).setItemLores(lore).build();
    }

    @Override
    public void setCost(float cost) {
        Validate.notNull(cost);
        this.cost = cost;
    }

    @Override
    public void setPermission(String permission) {
        Validate.notNull(permission);
        this.permission = permission;
    }


}
