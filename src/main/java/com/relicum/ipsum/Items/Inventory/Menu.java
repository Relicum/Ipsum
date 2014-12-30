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

import com.relicum.ipsum.Utils.TextProcessor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.Validate;

import java.util.EnumMap;

/**
 * Name: MenuBase.java Created: 17 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
@SuppressWarnings("WeakerAccess")
public abstract class Menu {

    @Getter
    @Setter
    protected boolean closeOutside;
    @Getter
    @Setter
    protected boolean guiLock = false;
    @Getter
    @Setter
    protected boolean fullLock = false;
    @Getter
    @Setter
    protected int size;
    @Getter
    @Setter
    protected String title;
    @Getter
    protected EnumMap<MenuIconType, MenuItem> iconMap = new EnumMap<>(MenuIconType.class);


    public Menu(int size, String title) {
        Validate.notNull(size);
        Validate.notNull(title);
        this.size = size;
        this.title = TextProcessor.colorize(title);
    }

    public Menu addIcon(MenuIconType iconType, MenuItem item) {
        Validate.notNull(iconType);
        Validate.notNull(item);

        this.iconMap.putIfAbsent(iconType, item);

        return this;
    }

    public abstract Menu addItem(MenuItem item);


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Menu{");
        sb.append("closeOutside=").append(closeOutside);
        sb.append(", guiLock=").append(guiLock);
        sb.append(", fullLock=").append(fullLock);
        sb.append(", size=").append(size);
        sb.append(", title='").append(title).append('\'');
        sb.append(", iconMap=").append(iconMap);
        sb.append('}');
        return sb.toString();
    }
}
