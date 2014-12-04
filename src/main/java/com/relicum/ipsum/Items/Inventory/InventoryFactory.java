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

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

/**
 * This class allows you to serialize an inventory to single string and vice-versa.
 *
 * @author DarkBlade12
 * @version 0.0.1
 */
public final class InventoryFactory {
    private InventoryFactory() {
    }

    public static String toString(Inventory i) {
        YamlConfiguration configuration = new YamlConfiguration();
        configuration.set("Title", i.getTitle());
        configuration.set("Size", i.getSize());
        for (int a = 0; a < i.getSize(); a++) {
            ItemStack s = i.getItem(a);
            if (s != null)
                configuration.set("Contents." + a, s);
        }
        return Base64Coder.encodeString(configuration.saveToString());
    }

    public static String[] playerInventoryToBase64(PlayerInventory playerInventory) {

        String content = toString(playerInventory);
        String armor = itemStackArrayToBase64(playerInventory.getArmorContents());

        return new String[]{content, armor};
    }

    public static String itemStackArrayToBase64(ItemStack[] items) throws IllegalStateException {
        YamlConfiguration configuration = new YamlConfiguration();
        for (int i = 0; i < items.length; i++) {
            configuration.set("armor." + String.valueOf(i), items[i]);
        }

        return Base64Coder.encodeString(configuration.saveToString());
    }


    public static Inventory fromString(String s) {
        YamlConfiguration configuration = new YamlConfiguration();
        try {
            configuration.loadFromString(Base64Coder.decodeString(s));
            Inventory i = Bukkit.createInventory(null, configuration.getInt("Size"), configuration.getString("Title"));

            ConfigurationSection contents = configuration.getConfigurationSection("Contents");
            for (String index : contents.getKeys(false))
                i.setItem(Integer.parseInt(index), contents.getItemStack(index));
            return i;
        } catch (InvalidConfigurationException e) {
            return null;
        }
    }

    public static ItemStack[] itemStackArrayFromBase64(String data) {
        YamlConfiguration configuration = new YamlConfiguration();
        try {
            configuration.loadFromString(Base64Coder.decodeString(data));
            ConfigurationSection contents = configuration.getConfigurationSection("armor");
            ItemStack[] items = new ItemStack[contents.getKeys(false).size()];
            for (String index : contents.getKeys(false))
                items[Integer.parseInt(index)] = contents.getItemStack(index);
            return items;
        } catch (InvalidConfigurationException e) {
            return null;
        }

    }
}
