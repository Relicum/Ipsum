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

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import com.relicum.ipsum.Conversations.MMPlayer;

/**
 * Name: RunCommand.java Created: 21 January 2015
 *
 * @author Relicum
 * @version 0.0.1
 */
public class RunCommand implements Perform {

    private List<String> commands;

    @Getter
    private boolean useConsole;
    @Getter
    private MMPlayer player;

    public RunCommand() {
        this.commands = new ArrayList<>();
    }

    @Override
    public void addCommand(String cmd) {
        commands.add(cmd);
    }

    @Override
    public List<String> getCommands() {

        return commands;
    }

    @Override
    public void setUseConsole(boolean use) {
        Validate.notNull(use);
        this.useConsole = use;
    }

    @Override
    public void addPlayer(MMPlayer player) {
        Validate.notNull(player);

        this.player = player;

    }

    @Override
    public void perform() {
        for (String cmd : commands) {
            if (useConsole)
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replaceAll("\\\\", ""));
            else
                this.player.performCommand(cmd.replaceAll("\\\\", "/"));
        }

    }
}
