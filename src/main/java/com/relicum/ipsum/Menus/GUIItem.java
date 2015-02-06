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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import com.relicum.ipsum.Items.MetaType;
import com.relicum.ipsum.Items.SimpleItemFactory;

/**
 * GUIItem an item that is contained in a
 * {@link com.relicum.ipsum.Menus.GUIMenu}
 *
 * @author Relicum
 * @version 0.0.1
 */
public class GUIItem extends AbstractGUIItem {

    private Map<Integer, String> commands;

    public GUIItem() {
    }

    /**
     * Instantiates a new GUIItem.
     *
     * @param itemMaterial the item material
     * @param dataBit the data or damage bit
     * @param itemAmount the item amount
     * @param metaType the meta type
     */
    public GUIItem(Material itemMaterial, byte dataBit, int itemAmount, MetaType metaType) {
        super(itemMaterial, dataBit, itemAmount, metaType);
        this.commands = new HashMap<>(10);

    }

    /**
     * Instantiates a new GUIItem.
     *
     * @param itemMaterial the item material
     * @param itemAmount the item amount
     * @param metaType the meta type
     */
    public GUIItem(Material itemMaterial, int itemAmount, MetaType metaType) {
        super(itemMaterial, itemAmount, metaType);
        this.commands = new HashMap<>(10);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPermission() throws UnsupportedOperationException {
        if (permission != null)
            return permission;
        else
            throw new UnsupportedOperationException("Permissions have not ben enabled for this item");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getCostPerUse() {
        return cost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemStack getItem() {
        if (dataBit == -1)
            return new SimpleItemFactory().getItemBuilder(itemMaterial, itemAmount, metaType).setDisplayName(this.displayName).setItemLores(lore).build();

        else
            return new SimpleItemFactory().getItemBuilder(itemMaterial, itemAmount, dataBit, metaType).setDisplayName(this.displayName).setItemLores(lore)
                                          .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCost(float cost) {
        Validate.notNull(cost);
        this.cost = cost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPermission(String permission) {
        Validate.notNull(permission);
        this.permission = permission;
    }

    /**
     * Set command that is run when the menu item is clicked, max 10 commands.
     * <p>
     * The index is the order the commands will run starting at 1 and
     * incrementing.
     *
     * @param index the index
     * @param command the command
     */
    public void setCommand(int index, String command) {
        this.commands.put(index, command);
    }

    /**
     * Set command that is run when the menu item is clicked, max 10 commands.
     * <p>
     * Command is add to the next available index.
     *
     * @param command the command
     */
    public void setCommand(String command) {

        this.commands.put(commands.size(), command);
    }

    /**
     * Set list of commands that are run when the menu item is clicked, max 10
     * commands.
     * <p>
     * The order of the commands will be the same order they appear in the list.
     *
     * @param cmds the cmds
     */
    public void setCommands(List<String> cmds) {

        for (int i = 0; i < cmds.size(); i++) {
            this.commands.put(i, cmds.get(i));
        }
    }

    /**
     * Get command by index
     *
     * @param index the index
     * @return the string
     */
    public String getCommand(int index) {
        if (this.commands.containsKey(index))
            return this.commands.get(index);
        return "";
    }

    /**
     * Get all commands as a Collection
     *
     * @return the collection of commands.
     */
    public Collection<String> getCommands() {

        return this.commands.values();
    }

}
