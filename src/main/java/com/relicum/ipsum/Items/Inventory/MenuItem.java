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

import com.relicum.ipsum.Items.MetaType;
import com.relicum.ipsum.Items.SimpleItemFactory;
import com.relicum.ipsum.Utils.TextProcessor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedList;
import java.util.List;

/**
 * Name: MenuItem.java Created: 26 December 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public abstract class MenuItem {

    @Getter
    private MenuClickAction menuClickAction;

    @Getter
    private String displayName;

    @Getter
    private List<String> lore;

    @Getter
    private Material itemMaterial;

    @Getter
    private int itemAmount;

    @Getter
    private byte dataBit = (byte) -1;

    @Getter
    @Setter
    private Menu menu;

    @Getter
    private Slot slot;

    @Getter
    private ItemStack stack;

    public MenuItem(Material itemMaterial, int itemAmount) {
        this.itemMaterial = itemMaterial;
        this.itemAmount = itemAmount;
    }

    public ItemStack buildItem() {
        if (dataBit == (byte) -1)
            this.stack = new SimpleItemFactory()
                    .getItemBuilder(itemMaterial, itemAmount, MetaType.ITEM_META)
                    .setDisplayName(TextProcessor.colorize(ChatColor.GOLD + "" + displayName))
                    .setItemLores(lore)
                    .build();
        else
            this.stack = new SimpleItemFactory()
                    .getItemBuilder(itemMaterial, itemAmount, (byte) dataBit, MetaType.ITEM_META)
                    .setDisplayName(TextProcessor.colorize(ChatColor.GOLD + "" + displayName))
                    .setItemLores(lore)
                    .build();

        return stack;
    }

    public MenuItem setDataBit(Byte dataBit) {
        Validate.notNull(dataBit);
        this.dataBit = dataBit;
        return this;
    }

    public MenuItem setItemAmount(Integer itemAmount) {
        Validate.notNull(itemAmount);
        Validate.isTrue(itemAmount < 65, "Item Stack can not be bigger than 64");
        this.itemAmount = itemAmount;
        return this;
    }

    public MenuItem setItemMaterial(Material itemMaterial) {
        Validate.notNull(itemMaterial);
        this.itemMaterial = itemMaterial;
        return this;
    }

    public MenuItem setLores(List<String> lores) {
        Validate.notNull(lores);
        this.lore = new LinkedList<>();

        for (String s : lores) {
            s = TextProcessor.colorize(s);
            this.lore.add(s);
        }


        return this;
    }

    public MenuItem setSlot(Slot slot) {
        this.slot = slot;
        return this;
    }

    public MenuItem setLoreLine(String line) {
        Validate.notNull(line);
        line = TextProcessor.colorize(line);
        if (lore == null)
            this.lore = new LinkedList<>();
        if (!this.lore.contains(line))
            this.lore.add(line);
        return this;
    }

    public MenuItem setMenuClickAction(MenuClickAction menuClickAction) {
        Validate.notNull(menuClickAction);
        this.menuClickAction = menuClickAction;
        return this;
    }

    public MenuItem setDisplayName(String displayName) {
        Validate.notNull(displayName);
        displayName = TextProcessor.colorize(displayName);

        if (displayName.length() > 16)
            displayName = displayName.substring(0, 15);

        this.displayName = displayName;

        return this;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MenuItem{");
        sb.append("onClickAction=").append(menuClickAction);
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", lore=").append(lore);
        sb.append(", itemMaterial=").append(itemMaterial);
        sb.append(", itemAmount=").append(itemAmount);
        sb.append(", dataBit=").append(dataBit);
        sb.append(", slot=").append(slot);
        sb.append(", stack=").append(stack);
        sb.append('}');
        return sb.toString();
    }
}
