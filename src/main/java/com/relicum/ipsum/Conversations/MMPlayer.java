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

package com.relicum.ipsum.Conversations;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.Validate;
import org.bukkit.entity.Player;
import com.relicum.ipsum.Menus.GUIMenu;

/**
 * Name: MMPlayer.java Created: 21 January 2015
 *
 * @author Relicum
 * @version 0.0.1
 */
public class MMPlayer {

    @Getter
    @Setter
    private static ArrayList<MMPlayer> playerList = new ArrayList<>();

    private static GUIMenu menu;

    @Getter
    @Setter
    private Player player;
    @Getter
    @Setter
    private int menuAccessing;
    @Getter
    @Setter
    private boolean editing;
    @Getter
    @Setter
    private boolean using;
    @Getter
    @Setter
    private boolean chat;

    public MMPlayer(Player player) {
        Validate.notNull(player);

        setPlayer(player);

        addPlayer(this);

    }

    public static void addPlayer(MMPlayer player) {
        Validate.notNull(player);

        MMPlayer newPlayer = player;

        getPlayerList().add(newPlayer);

    }

    public static void updatePlayer(MMPlayer player) {
        int index = getPlayerIndex(player.getPlayer().getName());

        if (index == -1) {
            return;
        }

        getPlayerList().set(index, player);
    }

    public static void addGUIMenu(GUIMenu guiMenu) {
        Validate.notNull(guiMenu);
        menu = guiMenu;
    }

    public static GUIMenu getMenu() {

        return menu;
    }

    public static MMPlayer getPlayerByName(String player) {

        Validate.notNull(player);

        int index = getPlayerIndex(player);

        if (index == -1) {
            return null;
        }

        return getPlayerList().get(index);
    }

    public static void clearPlayerList() {
        if (playerList == null) {
            playerList = new ArrayList<>();
        }

        for (MMPlayer player : playerList) {
            if ((player.isUsing()) || (player.isEditing())) {
                player.getPlayer().sendMessage(Messages.PREFIX + "Server reloaded. Menu closed");
                player.closeMenu();
            }

        }

        playerList.clear();
        playerList = null;
        playerList = new ArrayList<>();
    }

    public void closeMenu() {
        getPlayer().closeInventory();
    }

    public static int getPlayerIndex(String player) {
        for (int i = 0; i < getPlayerList().size(); i++) {
            if (player.equals(getPlayerList().get(i).getPlayer().getName())) {
                return i;
            }

        }

        return -1;
    }

    public void editMenu(String menuName) {

        Validate.notNull(menuName);

        getPlayer().sendMessage(Messages.PREFIX + "It should now open a inv WIP");

        setEditing(true);

        // getPlayer().openInventory(menu.getInventory());
    }

    /**
     * Perform command.
     *
     * @param cmd the cmd
     */
    public void performCommand(String cmd) {

        getPlayer().performCommand(cmd);
    }
}
