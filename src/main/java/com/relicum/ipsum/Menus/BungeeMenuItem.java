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
import lombok.NonNull;
import lombok.ToString;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Name: BungeeMenuItem.java Created: 08 January 2015
 *
 * @author Relicum
 * @version 0.0.1
 */
@ToString(callSuper = true)
public class BungeeMenuItem extends AbstractMenuItem {

    private String serverName;

    public BungeeMenuItem() {
        super();
    }

    public BungeeMenuItem(Material itemMaterial, byte dataBit, int itemAmount, MetaType metaType) {
        super(itemMaterial, dataBit, itemAmount, metaType);
    }

    public BungeeMenuItem(Material itemMaterial, int itemAmount, MetaType metaType) {
        super(itemMaterial, itemAmount, metaType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPermission() throws UnsupportedOperationException {
        if (this.permission != null)
            return this.permission;

        else throw new UnsupportedOperationException("Not currently supported");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getCostPerUse() {
        return this.cost;
    }

    @Override
    public ItemStack getItem() {
        if (dataBit == -1)
            return new SimpleItemFactory().getItemBuilder(itemMaterial, itemAmount, metaType).setDisplayName(this.displayName).setItemLores(lore).build();

        else
            return new SimpleItemFactory().getItemBuilder(itemMaterial, itemAmount, dataBit, metaType).setDisplayName(this.displayName).setItemLores(lore).build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCost(float cost) {
        this.cost = cost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPermission(@NonNull String permission) {
        this.permission = permission;
    }

    /**
     * Gets serverName.
     *
     * @return Value of serverName.
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * Sets new serverName.
     *
     * @param serverName New value of serverName.
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
