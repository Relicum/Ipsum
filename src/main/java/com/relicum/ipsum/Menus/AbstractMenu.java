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

import java.util.UUID;
import org.apache.commons.lang.Validate;
import org.bukkit.inventory.InventoryHolder;
import com.relicum.ipsum.Utils.TextProcessor;

/**
 * AbstractMenu impliments the {@link com.relicum.ipsum.Menus.GenericMenu}
 * methods.
 * All Menus should extend directly off this class adding in any specific code
 * there.
 *
 * @author Relicum
 * @version 0.0.1
 */
public abstract class AbstractMenu implements InventoryHolder, GenericMenu {

    protected MenuState state;

    private UUID menuUUID;

    protected String menuTitle;

    protected int size;

    protected String menuName;

    protected boolean closeOnOutsideClick = true;

    /**
     * Instantiates a new Abstract menu.
     *
     * @param menuTitle the menu title
     * @param size the size
     */
    public AbstractMenu(String menuTitle, int size) {
        this.menuTitle = menuTitle;
        this.size = size;
        this.state = MenuState.EDIT;
        this.menuUUID = UUID.randomUUID();
    }

    /**
     * Instantiates a new Abstract menu.
     *
     * @param menuTitle the menu title
     * @param size the size
     * @param uniqueName the unique name
     */
    public AbstractMenu(String menuTitle, int size, String uniqueName) {
        this.menuTitle = menuTitle;
        this.size = size;
        setUniqueName(uniqueName);
        this.state = MenuState.EDIT;
        this.menuUUID = UUID.randomUUID();
    }

    public AbstractMenu() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        return this.size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMenuTitle() {
        return this.menuTitle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSize(int size) {
        Validate.notNull(size);
        Validate.isTrue(size % 9 == 0);
        if (size < 9)
            this.size = 9;
        else
            this.size = size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMenuTitle(String menuTitle) {
        Validate.notNull(menuTitle);
        if (menuTitle.length() > 31) {
            this.menuTitle = TextProcessor.colorize(menuTitle.substring(0, 31));
        } else
            this.menuTitle = TextProcessor.colorize(menuTitle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUniqueName() {

        return this.menuName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUniqueName(String name) {
        Validate.notNull(name);
        name = TextProcessor.stripColor(name);
        name = name.replaceAll(" ", "");
        if (name.length() > 15)
            name = name.substring(0, 15);

        this.menuName = name;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean willCloseOnOutsideClick() {
        return this.closeOnOutsideClick;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UUID getMenuUUID() {
        return this.menuUUID;
    }

    /**
     * Set if the menu is to be closed If the player clicks outside the menu.
     * <p>
     * Defaults to true meaning any outside clicks will auto close the menu
     *
     * @param click the click
     */
    public void setCloseOnOutsideClick(boolean click) {
        Validate.notNull(click);
        this.closeOnOutsideClick = click;
    }

    /**
     * Sets new menu state {@link com.relicum.ipsum.Menus.MenuState}.
     *
     * @param state the {@link MenuState} to set the menu to.
     */
    public void setState(MenuState state) {
        Validate.notNull(state);
        this.state = state;
    }

    /**
     * Gets the current {@link MenuState} of the menu.
     *
     * @return the state the menu is in.
     */
    public MenuState getState() {
        return state;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractMenu{");
        sb.append("closeOnOutsideClick=").append(closeOnOutsideClick);
        sb.append(", state=").append(state);
        sb.append(", menuUUID=").append(menuUUID);
        sb.append(", menuTitle='").append(menuTitle).append('\'');
        sb.append(", size=").append(size);
        sb.append(", menuName='").append(menuName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
