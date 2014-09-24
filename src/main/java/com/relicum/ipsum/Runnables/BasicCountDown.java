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

package com.relicum.ipsum.Runnables;

import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Name: BasicCountDown.java Created: 19 September 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class BasicCountDown implements Runnable {

    private long total;


    private long period;
    private TimeUnit unit;


    private List<Player> players;
    private boolean useBroadCast = true;

    public BasicCountDown(long length, long period, @NonNull TimeUnit unit) {

        this.period = period;
        this.unit = unit;
        total = length;

    }

    public BasicCountDown(long length, long period, TimeUnit unit, @NonNull List<Player> players) {

        this.period = period;
        this.unit = unit;
        this.players = players;
        this.useBroadCast = false;
        this.total = length;
    }


    public void run() {

        if (total > 0) {
            if (useBroadCast)
                broadcastMessage();
            else
                sendPlayerMessage();
        }
        total -= period;
        if (total <= 0) {
            return;
        }

    }

    private void broadcastMessage() {

        Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', String.format("&5[&bMC-OITC&5]&r &c%s&e seconds till the game begins", total)));
    }

    private void sendPlayerMessage() {
        players.stream().forEach(p -> p.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("&5[&bMC-OITC&5]&r &c%s&e seconds till the game begins", total))));
    }
}
