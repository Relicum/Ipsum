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

package com.relicum.ipsum.Configuration;

import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import static org.bukkit.ChatColor.*;

/**
 * Currently used to store player ranks with color formatting for player Tab.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class RankMap {


    protected static Map<GameRanks, ChatColor> rankMap;

    /**
     * Instantiates a new Rank map This is currently required even though all other methods are static.
     * Will be fixing this shortly.
     */
    public RankMap() {

        rankMap = Collections.synchronizedMap(new EnumMap<>(GameRanks.class));
        rankMap.put(GameRanks.NOOB, DARK_GRAY);
        rankMap.put(GameRanks.VIP, GRAY);
        rankMap.put(GameRanks.MVP, AQUA);
        rankMap.put(GameRanks.HERO, GOLD);
        rankMap.put(GameRanks.MOD, LIGHT_PURPLE);
        rankMap.put(GameRanks.ADMIN, DARK_PURPLE);
        rankMap.put(GameRanks.DEV, BLUE);
        rankMap.put(GameRanks.OWNER, RED);

    }

    public static void addRank(GameRanks rank, ChatColor format) {

        rankMap.put(rank, format);
    }

    /**
     * Gets formatted color formatted player names for tab list based on rank.
     *
     * @param ranks  the ranks
     * @param player the player
     * @return the formatted
     */
    public static String getFormatted(GameRanks ranks, String player) {
        Validate.isTrue(!(player.length() > 15), "Players name can not be longer than 14 characters");
        if (rankValid(ranks)) {
            System.out.println("The rank of " + ranks.toString() + " has been found");
            return rankMap.get(ranks) + player;
        } else {
            System.out.println("The rank of " + ranks.toString() + " has not been found");
            return rankMap.get(GameRanks.NOOB) + player;
        }
    }

    protected static boolean rankValid(GameRanks rank) {

        return rankMap.containsKey(rank);
    }
}
