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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.cubespace.Yamler.Config.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

/**
 * PlayerData Stores player data in a separate yml file, used to create a yml database of player data.
 * <p>This is very similar to {@link com.relicum.ipsum.Configuration.PersistentPlayer} except the storage is in yml using Yamler.
 *
 * @author Relicum
 * @version 0.0.1
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class PlayerData extends Config {

    private transient Player player;

    private String uniqueId = "";

    private String name = "";

    private String lastName = "";

    private String rank = "";

    private String rankColor = "6";

    private String displayName = "";

    private String playerListName = "";

    private long lastSeen = 0l;

    private long firstSeen = 0l;

    private ArrayList<String> knowAliases = new ArrayList<>();

    public PlayerData(Plugin plugin, UUID uuid, String player) {


        String folder = uuid.toString().substring(0, 2);

        CONFIG_FILE = new File(plugin.getDataFolder(), "players" + File.separator + folder + File.separator + uuid.toString() + ".yml");

        CONFIG_HEADER = new String[]{"Player Database for " + player};

        if (firstSeen < 1l) {
            uniqueId = uuid.toString();
            firstSeen = System.currentTimeMillis();
            lastSeen = System.currentTimeMillis();
            lastName = player;
            rank = GameRanks.MVP.toString();
            rankColor = String.valueOf(RankMap.getFormat(GameRanks.MVP));
            displayName = rankColor + player;
        } else {

            if (!lastName.equals(player)) {
                if (knownPlayer(player)) {
                    System.out.println("Hmm this name is already in know aliases");
                } else {

                    knowAliases.add(lastName);
                    System.out.println("Added player to know aliases list");
                }


                this.lastName = player;
                this.name = player;

            }
        }

        this.name = player;
        this.player = Bukkit.getPlayer(uuid);

    }


    /**
     * Check to see if this player was know by a previous name in the past
     *
     * @param player the player name to check
     * @return true if the player has a past know name, or false if not.
     */
    public boolean knownPlayer(String player) {

        return knowAliases.contains(player);
    }


}
