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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import com.relicum.ipsum.io.JsonStringInv;

/**
 * GUIMenu a menu that contains clickable items, the menu can be attached to
 * Mobs, other items or called by command
 *
 * @author Relicum
 * @version 0.0.1
 */
public class GUIMenu extends AbstractMenu {

    private Map<Integer, GUIItem> items;
    @Getter
    @Setter
    protected UUID activatorUUID;

    @Getter
    @Setter
    private boolean isAttached = false;
    @Getter
    @Setter
    private MenuType menuType = MenuType.BUNGEE;

    private transient String jsonInventory;

    public GUIMenu() {
    }

    /**
     * Instantiates a new Simple menu.
     *
     * @param menuTitle the menu title
     * @param size the size
     * @param uniqueName the unique name
     */
    public GUIMenu(String menuTitle, int size, String uniqueName) {
        super(menuTitle, size, uniqueName);
        this.items = new HashMap<>();

    }

    /**
     * Instantiates a new Simple menu.
     *
     * @param menuTitle the menu title
     * @param size the size
     */
    public GUIMenu(String menuTitle, int size) {
        super(menuTitle, size);
        this.items = new HashMap<>();

    }

    /**
     * Add an item to the menu.
     *
     * @param item the {@link com.relicum.ipsum.Menus.GUIItem} to add
     */
    public void addItem(GUIItem item) {
        Validate.notNull(item);
        if (item.getSlot() > getSize())
            throw new IllegalArgumentException("The item slot given is larger the then menu size");
        item.setMenuUUID(getMenuUUID());
        this.items.put(item.getSlot(), item);
    }

    /**
     * Gets the {@link com.relicum.ipsum.Menus.GUIItem} at the specified slot.
     *
     * @param slot the slot position the item is in.
     * @return the {@link com.relicum.ipsum.Menus.GUIItem}
     */
    public GUIItem getItem(int slot) {

        return items.get(slot);
    }

    /**
     * Remove the {@link com.relicum.ipsum.Menus.GUIItem} from the menu at the
     * specified slot.
     *
     * @param slot the slot to remove the item from
     */
    public void removeItem(int slot) {

        if (checkSlot(slot))
            items.remove(slot);
    }

    /**
     * Checks to see if a slot has a valid item.
     *
     * @param slot the slot to check
     * @return true if the slot has a valid item of false if the slot is empty.
     */
    public boolean checkSlot(int slot) {

        return items.containsKey(slot);
    }

    /**
     * Gets all the {@link com.relicum.ipsum.Menus.GUIItem} in the menu in a
     * list.
     *
     * @return the list of menu items
     */
    public List<GUIItem> getItems() {
        return items.values().stream().collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Inventory getInventory() {

        Inventory inventory = Bukkit.createInventory(null, getSize(), getMenuTitle());

        for (GUIItem item : items.values()) {
            inventory.setItem(item.getSlot(), item.getItem());
        }

        return inventory;
    }

    /**
     * Gets next free slot in the menu.
     *
     * @return the int of the next free slot in the menu
     */
    public int nextSlot() {

        return items.size();
    }

    /**
     * Serialize the current Inventory to a json String.
     * <p>
     * This is stored and saved internally to help efficiency.
     */
    public void saveJsonInventory() {

        jsonInventory = JsonStringInv.convertToJson(getInventory());
    }

    /**
     * Deserialize the Inventory from a JSON string back to a
     * {@link org.bukkit.inventory.Inventory}
     *
     * @return the {@link org.bukkit.inventory.Inventory}
     */
    public Inventory loadJsonInventory() {

        return JsonStringInv.convertFromString(jsonInventory);
    }

    /**
     * Detach the uuid of the activator,setting it to null.
     * <p>
     * This will also set isAttached to false.
     */
    public void detachActivator() {
        this.activatorUUID = null;
        this.isAttached = false;
    }

    /**
     * Attach the uuid of the activator, this could be an item or an entity.
     * <p>
     * This will also set isAttached to true.
     *
     * @param activator the activator UUID
     */
    public void attachActivator(UUID activator) {
        Validate.notNull(activator);
        this.activatorUUID = activator;
        this.isAttached = true;

    }
}
