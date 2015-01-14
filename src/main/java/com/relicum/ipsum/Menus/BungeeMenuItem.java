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

import com.relicum.ipsum.Items.Inventory.Slot;
import com.relicum.ipsum.Items.MetaType;
import com.relicum.ipsum.Items.SimpleItemFactory;
import lombok.NonNull;
import lombok.ToString;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * BungeeMenuItem item used in a Bungee menu WIP.
 *
 * @author Relicum
 * @version 0.0.1
 */
@ToString(callSuper = true)
public class BungeeMenuItem extends AbstractMenuItem {

    private String serverName;

    private Slot slot;

    /**
     * Instantiates a new Bungee menu item.
     */
    public BungeeMenuItem() {
        super();
    }


    /**
     * Instantiates a new Bungee menu item.
     *
     * @param itemMaterial the item material
     * @param dataBit      the data bit
     * @param itemAmount   the item amount
     * @param metaType     the {@link com.relicum.ipsum.Items.MetaType}
     */
    public BungeeMenuItem(Material itemMaterial, byte dataBit, int itemAmount, MetaType metaType) {
        super(itemMaterial, dataBit, itemAmount, metaType);
    }

    /**
     * Instantiates a new Bungee menu item.
     *
     * @param itemMaterial the item material
     * @param itemAmount   the item amount
     * @param metaType     the {@link com.relicum.ipsum.Items.MetaType}
     */
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

    public void setSlot(Slot slot) {
        Validate.notNull(slot);
        this.slot = slot;
    }


    public Slot getItemSlot() {
        return slot;
    }

    /**
     * {@inheritDoc}
     */
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
