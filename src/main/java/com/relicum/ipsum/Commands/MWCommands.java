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
public class MWCommands {


/*    public static class SubCommand {

        @Command(aliases = {"mw"}, desc = "All Multi World Commands", min = 0, max = -1)
        @NestedCommand(MWCommands.class)
        public static void mwsub(final CommandContext args, CommandSender sender) throws CommandException {
        }
    }


    @CommandPermissions("mworlds.admin.create")
    @Command(aliases = {"create"}, desc = "Create a new world", usage = "[name] [-v] - Name of the world, optional v flag for void world", min = 1, max = 1,flags = "v")
    public static void create(final CommandContext args, CommandSender sender,Plugin plugin) throws CommandException {
        plugin.getLogger().info("Message from create command methodArgs");
        if (!((sender instanceof Player))) throw new CommandException("You must be a player to use this command");

        Player player = (Player) sender;

        if (args.hasFlag('v')) {

            player.sendMessage(ChatColor.DARK_AQUA + "You have ran create with the V flag");
            return;
        }

        player.sendMessage(ChatColor.GREEN + "Create ran with no flags");


    }*/

    public static class ParentCommand {
        @CommandPermissions(value = "mw.use")
        @Command(aliases = {"myplugin"}, usage = "/<command>", desc = "All MyPlugin commands", min = 0, max = -1, help = "Gives details about all main sub commands")
        @NestedCommand(MWCommands.class) //All commands will get passed on to Commands.class
        public static void myplugin(final CommandContext args, CommandSender sender) throws CommandException {
        }
    }

    @CommandPermissions(value = "mw.use.hello")
    @Command(aliases = {"hello", "hey"}, desc = "Says hello", usage = "[player] - The player to say hello to", min = 1, max = 1, help = "hello help")
    public static void hello(final CommandContext args, CommandSender sender) throws CommandException {
        Player target = Bukkit.getPlayer(args.getString(0)); //0 is the index
        if (target == null) throw new CommandException("Player " + args.getString(0) + " not found!");
        target.sendMessage("Hello!");
    }

    @CommandPermissions(value = "mw.use.hello2")
    @Command(aliases = {"hello2", "hey2"}, desc = "Says hello2", usage = "[player] - The player to say hello2 to", min = 1, max = 1, help = "hello2 help")
    public static void hello2(final CommandContext args, CommandSender sender) throws CommandException {
        Player target = Bukkit.getPlayer(args.getString(0)); //0 is the index
        if (target == null) throw new CommandException("Player " + args.getString(0) + " not found!");
        target.sendMessage("Hello2!");
    }
}
