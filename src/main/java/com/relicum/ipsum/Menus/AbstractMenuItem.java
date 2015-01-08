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

import com.relicum.ipsum.Items.Inventory.MenuClickAction;
import com.relicum.ipsum.Items.Inventory.Slot;
import com.relicum.ipsum.Items.MetaType;
import com.relicum.ipsum.Utils.TextProcessor;
import lombok.NonNull;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: AbstractMenuItem.java Created: 08 January 2015
 *
 * @author Relicum
 * @version 0.0.1
 */
public abstract class AbstractMenuItem implements GenericMenuItem {


    protected MenuClickAction menuClickAction;

    protected String displayName;

    protected List<String> lore;

    protected Material itemMaterial;

    protected int itemAmount;

    protected byte dataBit = (byte) -1;

    protected String permission;

    protected float cost = 0.0f;

    protected Slot slot;

    protected MetaType metaType;

    public AbstractMenuItem() {
        this.lore = new ArrayList<>();
    }

    public AbstractMenuItem(Material itemMaterial, byte dataBit, int itemAmount, MetaType metaType) {
        this.itemMaterial = itemMaterial;
        this.dataBit = dataBit;
        this.itemAmount = itemAmount;
        this.metaType = metaType;
        this.lore = new ArrayList<>();

    }

    public AbstractMenuItem(Material itemMaterial, int itemAmount, MetaType metaType) {
        this.itemMaterial = itemMaterial;

        this.itemAmount = itemAmount;
        this.metaType = metaType;
        this.lore = new ArrayList<>();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract String getPermission() throws UnsupportedOperationException;

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract float getCostPerUse();


    /**
     * {@inheritDoc}
     */
    @Override
    public MenuClickAction getOnClickAction() {
        return this.menuClickAction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Material getItemMaterial() {
        return this.itemMaterial;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte getItemByte() {
        return this.dataBit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getItemDisplayName() {
        return this.displayName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getItemLore() {
        return this.lore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemAmount() {
        return this.itemAmount;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Slot getItemSlot() {
        return this.slot;
    }

    /**
     * Sets new itemMaterial.
     *
     * @param itemMaterial New value of itemMaterial.
     */
    public void setItemMaterial(@NonNull Material itemMaterial) {

        this.itemMaterial = itemMaterial;
    }

    /**
     * Sets new menuClickAction.
     *
     * @param menuClickAction New value of menuClickAction.
     */
    public void setMenuClickAction(@NonNull MenuClickAction menuClickAction) {
        this.menuClickAction = menuClickAction;
    }

    /**
     * Sets new slot.
     *
     * @param slot New value of slot.
     */
    public void setSlot(@NonNull Slot slot) {
        this.slot = slot;
    }

    /**
     * Sets new displayName.
     *
     * @param displayName New value of displayName.
     */
    public void setDisplayName(@NonNull String displayName) {

        if (displayName.length() > 15)
            this.displayName = TextProcessor.colorize(displayName.substring(0, 15));
        else
            this.displayName = TextProcessor.colorize(displayName);
    }

    /**
     * Sets new itemAmount.
     *
     * @param itemAmount New value of itemAmount.
     */
    public void setItemAmount(@NonNull int itemAmount) {
        this.itemAmount = itemAmount;
    }

    /**
     * Sets new lore.
     *
     * @param lore New value of lore.
     */
    public void setLore(@NonNull List<String> lore) {

        for (String s : lore) {
            this.lore.add(TextProcessor.colorize(s));
        }


    }

    /**
     * Sets new dataBit.
     *
     * @param dataBit New value of dataBit.
     */
    public void setDataBit(byte dataBit) {
        this.dataBit = dataBit;
    }

    /**
     * Sets new cost.
     *
     * @param cost New value of cost.
     */
    public abstract void setCost(float cost);

    /**
     * Sets new permission.
     *
     * @param permission New value of permission.
     */
    public abstract void setPermission(String permission);


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractMenuItem{");
        sb.append("cost=").append(cost);
        sb.append(", dataBit=").append(dataBit);
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", itemAmount=").append(itemAmount);
        sb.append(", itemMaterial=").append(itemMaterial);
        sb.append(", lore=").append(lore);
        sb.append(", menuClickAction=").append(menuClickAction);
        sb.append(", metaType=").append(metaType);
        sb.append(", permission='").append(permission).append('\'');
        sb.append(", slot=").append(slot);
        sb.append('}');
        return sb.toString();
    }
}
