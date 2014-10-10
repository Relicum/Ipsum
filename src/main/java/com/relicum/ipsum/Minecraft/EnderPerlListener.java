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

package com.relicum.ipsum.Minecraft;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.bukkit.Material.*;

/**
 * Name: EnderPerlListener.java Created: 06 October 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class EnderPerlListener implements Listener {


    private final static Set<Material> interactables =
            new HashSet<>(Arrays.asList(
                    ANVIL, COMMAND, BED, BEACON, BED_BLOCK, BREWING_STAND, BURNING_FURNACE, CAKE_BLOCK, CHEST,
                    DIODE, DIODE_BLOCK_OFF, DIODE_BLOCK_ON, DISPENSER, DROPPER, ENCHANTMENT_TABLE, ENDER_CHEST, FENCE_GATE,
                    FENCE_GATE, FURNACE, HOPPER, IRON_DOOR, IRON_DOOR_BLOCK, ITEM_FRAME, LEVER, REDSTONE_COMPARATOR,
                    REDSTONE_COMPARATOR_OFF, REDSTONE_COMPARATOR_ON, STONE_BUTTON, TRAP_DOOR, TRAPPED_CHEST,
                    WOODEN_DOOR, WOOD_BUTTON, WOOD_DOOR, WORKBENCH
            ));

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPerl(PlayerInteractEvent e) {
        if (e.getItem() == null
                || !e.getMaterial().equals(ENDER_PEARL)
                || (!e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && !e.getAction().equals(Action.RIGHT_CLICK_AIR))) ;
        return;


    }


}
