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

import com.relicum.ipsum.Utils.TextProcessor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.Validate;

/**
 * AbstractMenu impliments the {@link com.relicum.ipsum.Menus.GenericMenu} methods.
 * <p>All Menus should extend directly off this class adding in any specific code there.
 *
 * @author Relicum
 * @version 0.0.1
 */
public abstract class AbstractMenu implements GenericMenu {

    @Getter
    @Setter
    protected String mobUUID;

    protected String menuTitle;

    protected int size;

    public AbstractMenu(String menuTitle, int size) {
        this.menuTitle = menuTitle;
        this.size = size;
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
        if (menuTitle.length() > 15) {
            this.menuTitle = TextProcessor.colorize(menuTitle.substring(0, 15));
        } else
            this.menuTitle = TextProcessor.colorize(menuTitle);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractMenu{");
        sb.append("menuTitle='").append(menuTitle).append('\'');
        sb.append(", size=").append(size);
        sb.append('}');
        return sb.toString();
    }
}
