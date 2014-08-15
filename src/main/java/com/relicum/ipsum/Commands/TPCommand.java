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

package com.relicum.ipsum.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * Name: TPCommand.java Created: 07 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
@CmdInfo(name = "tp", description = "Suit of Teleporting commands", usage = "/<command> <arg1> [extras]", label = "tp", permission = "lucky.admin.tp.use", minArgs = 1, maxArgs = 4
        , playerOnly = false, subCommand = false)
public abstract class TPCommand extends SimpleCommand {


    public TPCommand(List<String> aliasess, Plugin plugin) {
        super(aliasess, plugin);

    }


    @Override
    public abstract boolean onCommand(CommandSender sender, Command command, String[] args);


    @Override
    public abstract String getParentPermission();

    @Override
    public abstract List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args);


    @Override
    public abstract Plugin getPlugin();

}
