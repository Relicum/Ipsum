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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.cubespace.Yamler.Config.Comment;
import net.cubespace.Yamler.Config.Config;
import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import com.relicum.ipsum.Items.Inventory.MenuClickAction;
import com.relicum.ipsum.Items.Inventory.Slot;
import com.relicum.ipsum.Items.MetaType;
import com.relicum.ipsum.Utils.TextProcessor;

/**
 * Name: Item.java Created: 19 January 2015
 *
 * @author Relicum
 * @version 0.0.1
 */
public class Item extends Config {

    @Comment("Item Meta Type")
    public String metaType = "";

    @Comment("Edit option")
    public String option = "";

    @Comment("Is there a cost involved")
    public Float cost = 0.0F;

    @Comment("Is a permission needed")
    public Boolean permissionRequired = false;

    @Comment("Permission")
    public String permission = "";

    @Comment("Click action")
    public String menuClickAction = "";

    @Comment("Menu item slot in string format")
    public String slot = "";

    @Comment("Menu item Material")
    public String material = "";

    @Comment("Menu item amount")
    public Integer amount = 1;

    @Comment("Menu item data bit")
    public short data = (short) -1;

    @Comment("Item Display Name use & for color")
    public String displayName = "";

    @Comment("Item Lore use & for color")
    public List<String> lore = new ArrayList<>();

    @Comment("General purpose key value store")
    public Map<String, String> store = new HashMap<>(4);

    public Item() {
    }

    public void addLore(String line) {

        this.lore.add(line);
    }

    public void addLore(String line, int index) {

        this.lore.add(index, line);
    }

    public void addLore(List<String> lines) {

        for (int i = 0; i < lines.size(); i++) {
            this.lore.add(i, lines.get(i));
        }

    }

    public List<String> getLore() {

        List<String> tmp = new ArrayList<>(lore.size());

        for (int i = 0; i < lore.size(); i++) {
            tmp.add(i, TextProcessor.colorize(lore.get(i)));
        }

        return tmp;

    }

    public void addToStore(String key, String value) {
        this.store.put(key, value);
    }

    public String getFromStore(String key) {

        return store.get(key);
    }

    public void setMetaType(MetaType type) {
        this.metaType = type.name();
    }

    public MetaType getMetaType() {

        return MetaType.valueOf(metaType);
    }

    public void setOption(EditOptions options) {

        this.option = options.name();
    }

    public EditOptions getOption() {

        return EditOptions.valueOf(option);
    }

    public void setSlot(Slot slot) {

        this.slot = slot.name();
    }

    public Slot getSlot() {

        return Slot.valueOf(slot);
    }

    public void setMenuClickAction(MenuClickAction action) {

        this.menuClickAction = action.name();
    }

    public MenuClickAction getMenuClickAction() {

        return MenuClickAction.valueOf(menuClickAction);
    }

    /**
     * Gets amount.
     *
     * @return Value of amount.
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * Gets data.
     *
     * @return Value of data.
     */
    public short getData() {
        return data;
    }

    /**
     * Sets new data.
     *
     * @param data New value of data.
     */
    public void setData(short data) {
        this.data = data;
    }

    /**
     * Sets new displayName.
     *
     * @param displayName New value of displayName.
     */
    public void setDisplayName(String displayName) {
        Validate.notNull(displayName);
        if (displayName.length() > 15)
            this.displayName = TextProcessor.colorize(displayName.substring(0, 15));
        else
            this.displayName = TextProcessor.colorize(displayName);
    }

    /**
     * Sets new amount.
     *
     * @param amount New value of amount.
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * Sets new material.
     *
     * @param material New value of material.
     */
    public void setMaterial(Material material) {
        this.material = material.name();
    }

    /**
     * Gets displayName.
     *
     * @return Value of displayName.
     */
    public String getDisplayName() {
        return ChatColor.translateAlternateColorCodes('&', displayName);
    }

    /**
     * Gets material.
     *
     * @return Value of material.
     */
    public Material getMaterial() {
        return Material.valueOf(material);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("amount=").append(amount);
        sb.append(", metaType='").append(metaType).append('\'');
        sb.append(", option='").append(option).append('\'');
        sb.append(", cost=").append(cost);
        sb.append(", permissionRequired=").append(permissionRequired);
        sb.append(", permission='").append(permission).append('\'');
        sb.append(", menuClickAction='").append(menuClickAction).append('\'');
        sb.append(", slot='").append(slot).append('\'');
        sb.append(", material='").append(material).append('\'');
        sb.append(", data=").append(data);
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", lore=").append(lore);
        sb.append(", store=").append(store);
        sb.append('}');
        return sb.toString();
    }
}
