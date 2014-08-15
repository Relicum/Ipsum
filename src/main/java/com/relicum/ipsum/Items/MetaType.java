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

package com.relicum.ipsum.Items;

/**
 * Enum of the different ItemMeta Types.
 * <p>Used with the item build to automatically have the correct Meta class based
 * on the Material you choose.</p>
 *
 * @author Relicum
 * @version 0.0.1
 */
public enum MetaType {
    ITEM_META("ItemMeta", true),
    WRITTEN_BOOK("BookMeta", false),
    ENCHANTED_BOOK("EnchantmentStorageMeta", false),
    FIREWORK_EFFECT("FireworkEffectMeta", false),
    FIREWORK("FireworkMeta", false),
    COLORED_ARMOR("LeatherArmorMeta", true),
    MAP("MapMeta", false),
    POTION("PotionMeta", false),
    SKULL_ITEM("SkullMeta", true),
    REPAIRABLE("Repairable", false);


    /**
     * The Old name.
     */
    private final String OldName;
    /**
     * Is this Meta been added in yet.
     */
    private final boolean Complete;

    private MetaType(String on, boolean com) {

        this.OldName = on;
        this.Complete = com;
    }

    /**
     * Gets old name.
     *
     * @return the old name
     */
    public String getOldName() {
        return OldName;

    }

    /**
     * Is complete.
     *
     * @return the boolean true and the Meta is available to use
     */
    public boolean isComplete() {
        return Complete;
    }


}
