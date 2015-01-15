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

package com.relicum.ipsum.Menus;

import com.relicum.ipsum.Items.Inventory.MenuClickAction;
import com.relicum.ipsum.Items.Inventory.Slot;
import com.relicum.ipsum.io.JsonStringInv;
import lombok.ToString;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Server to Server Menu for switching players on a Bungee Network WIP.
 */
@ToString(callSuper = true)
public class BungeeBar extends AbstractMenu {

    /**
     * The Items.
     */
    private List<BungeeMenuItem> items;

    private EnumMap<Slot, BungeeMenuItem> eItems;

    /**
     * The Json inventory.
     */
    private transient String jsonInventory;

    /**
     * Instantiates a new Bungee bar.
     *
     * @param size  the size
     * @param title the title
     */
    public BungeeBar(int size, String title) {
        super(title, size);
        this.items = new ArrayList<>();
        this.eItems = new EnumMap<>(Slot.class);
    }

    /**
     * Instantiates a new Bungee bar.
     */
    public BungeeBar() {
        super();
        this.items = new ArrayList<>();
        this.eItems = new EnumMap<>(Slot.class);
    }


    public MenuClickAction rightClick(Player player, Slot slot) {

        BungeeMenuItem bi = eItems.get(slot);

        if (bi.permissionRequired) {
            if (!player.hasPermission(bi.getPermission()) || !player.isOp()) {
                player.sendMessage(ChatColor.RED + "Error: You do not have permission to do that");
                return MenuClickAction.UNKNOWN;
            }
        }

        player.sendMessage(ChatColor.GREEN + "This is a click inventory: " + bi.getOnClickAction().name());

        return bi.getOnClickAction();

    }

    /**
     * Add a new {@link com.relicum.ipsum.Menus.BungeeMenuItem} to the menu.
     *
     * @param item the item {@link com.relicum.ipsum.Menus.BungeeMenuItem}
     */
    public void addItem(BungeeMenuItem item) {
        Validate.notNull(item);
        this.items.add(item);
        eItems.put(item.getItemSlot(), item);
    }


    /**
     * Gets items.
     *
     * @return the items
     */
    public List<BungeeMenuItem> getItems() {
        return eItems.values().stream().collect(Collectors.toList());
    }

    /**
     * Gets item by slot.
     *
     * @param slot the slot
     * @return the {@link com.relicum.ipsum.Menus.BungeeMenuItem}
     */
    public BungeeMenuItem getItemBySlot(Slot slot) {
        return eItems.get(slot);
        //List<BungeeMenuItem> res = items.stream().filter(p -> p.getItemSlot().equals(slot)).limit(1).collect(Collectors.toList());
        //return res.get(0);
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


    /**
     * {@inheritDoc}
     */
    @Override
    public Inventory getInventory() {

        Inventory inventory = Bukkit.createInventory(null, getSize(), getMenuTitle());

        for (BungeeMenuItem item : eItems.values()) {
            inventory.setItem(item.getItemSlot().ordinal(), item.getItem());
        }

        return inventory;
    }
}
