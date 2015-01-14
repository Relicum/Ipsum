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

import com.relicum.ipsum.io.JsonStringInv;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: SimpleMenu.java Created: 14 January 2015
 *
 * @author Relicum
 * @version 0.0.1
 */
public class SimpleMenu extends AbstractMenu {

    private List<MenuItem> items;

    private String jsonInventory;

    public SimpleMenu() {
    }

    public SimpleMenu(String menuTitle, int size) {
        super(menuTitle, size);
        this.items = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        Validate.notNull(item);

        this.items.add(item);
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    public List<MenuItem> getItems() {
        return items;
    }


    @Override
    public Inventory getInventory() {

        Inventory inventory = Bukkit.createInventory(null, getSize(), getMenuTitle());

        for (MenuItem item : items) {
            inventory.setItem(item.getSlot(), item.getItem());
        }

        return inventory;
    }

    /**
     * Serialize the current Inventory to a json String.
     * <p>This is stored and saved internally to help efficiency.
     */
    public void saveJsonInventory() {

        jsonInventory = JsonStringInv.convertToJson(getInventory());
    }

    /**
     * Deserialize the Inventory from a JSON string back to a {@link org.bukkit.inventory.Inventory}
     *
     * @return the {@link org.bukkit.inventory.Inventory}
     */
    public Inventory loadJsonInventory() {

        return JsonStringInv.convertFromString(jsonInventory);
    }
}
