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

import com.sk89q.minecraft.util.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Name: MWCommands.java Created: 20 October 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class TeamCommands {


    public static class ParentTeamCommand {
        @Command(
                aliases = {"team"},
                desc = "Commands for working with teams",
                min = 1,
                max = -1
        )
        @NestedCommand({TeamCommands.class})
        public static void team() {
        }
    }


    @Command(
            aliases = {"force"},
            desc = "Force a player onto a team",
            usage = "<player>",
            min = 1,
            max = 1,
            help = "This is help for force",
            flags = "h"

    )
    @CommandPermissions("pgm.team.force")
    public static void force(CommandContext args, CommandSender sender) throws CommandException {
        if (args.hasFlag('h')) {
            sender.sendMessage(makeCaption("Force Help"));
        }

        Player target = Bukkit.getPlayer(args.getString(0)); //0 is the index

        if (target == null) throw new CommandException("Player " + args.getString(0) + " not found!");
        target.sendMessage("Hello!");
    }


    @Command(
            aliases = {"malias"},
            desc = "Rename a team",
            usage = "<great>",
            min = 1,
            max = -1,
            help = "This is the help command"
    )
    @CommandPermissions("pgm.team.malias")
    public static void malias(CommandContext args, CommandSender sender) throws CommandException {
        Player target = Bukkit.getPlayer(args.getString(0)); //0 is the index
        if (target == null) throw new CommandException("Player " + args.getString(0) + " not found!");
        target.sendMessage("Hello2!");
    }

    private final static int COLUMNS = 40;

    public static String makeCaption(String message) {
        int eqCount = COLUMNS - message.length();
        String out = "";
        if (eqCount >= 2) {
            for (int i = 0; i < (int) (eqCount / 2); i++)
                out += "=";
        }
        out += message.toUpperCase();
        int l = out.length();
        for (int i = 0; i < (COLUMNS - l); i++)
            out += "=";
        return "\n" + out + "\n";
    }


}
