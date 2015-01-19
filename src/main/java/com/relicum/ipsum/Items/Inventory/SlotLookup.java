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

package com.relicum.ipsum.Items.Inventory;

/**
 * SlotLookup convert an int to the corresponding {@link com.relicum.ipsum.Items.Inventory.Slot}
 *
 * @author Relicum
 * @version 0.0.1
 */
public class SlotLookup {

    public static Slot lookup(int num) {

        switch (num) {
            case 0:
                return Slot.ZERO;
            case 1:
                return Slot.ONE;
            case 2:
                return Slot.TWO;
            case 3:
                return Slot.THREE;
            case 4:
                return Slot.FOUR;
            case 5:
                return Slot.FIVE;
            case 6:
                return Slot.SIX;
            case 7:
                return Slot.SEVEN;
            case 8:
                return Slot.EIGHT;
            case 9:
                return Slot.NINE;
            case 10:
                return Slot.TEN;
            case 11:
                return Slot.ELEVEN;
            case 12:
                return Slot.TWELVE;
            case 13:
                return Slot.THIRTEEN;
            case 14:
                return Slot.FOURTEEN;
            case 15:
                return Slot.FIFTEEN;
            case 16:
                return Slot.SIXTEEN;
            case 17:
                return Slot.SEVENTEEN;
            case 18:
                return Slot.EIGHTEEN;
            case 19:
                return Slot.NINETEEN;
            case 20:
                return Slot.TWENTY;
            case 21:
                return Slot.TWENTY_ONE;
            case 22:
                return Slot.TWENTY_TWO;
            case 23:
                return Slot.TWENTY_THREE;
            case 24:
                return Slot.TWENTY_FOUR;
            case 25:
                return Slot.TWENTY_FIVE;
            case 26:
                return Slot.TWENTY_SIX;
            default:
                return Slot.TWENTY_SEVEN;
        }

    }
}
