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

import com.relicum.ipsum.Configuration.Worlds;
import com.relicum.ipsum.Ipsum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Random;

/**
 * Name: WorldCreate.java Created: 05 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
@CmdInfo(name = "worldcreate", description = "Used to create new worlds", usage = "/<command> <name>", label = "WorldCreate", permission = "worldcreate.use", minArgs = 1, maxArgs = 1, playerOnly = true, subCommand = false)
public class WorldCreate extends SimpleCommand {

    private Plugin plugin;

    public WorldCreate(List<String> aliasess, Plugin plugin) {
        super(aliasess, plugin);
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String[] args) {

        if (Bukkit.getWorld(args[0]) != null) {
            sender.sendMessage(ChatColor.RED + "Error: world with that name already exists");
            return true;
        }
        Random random = new Random();

        long n = random.nextLong();

        Worlds worlds = new Worlds(plugin, args[0]);
        ((Ipsum) plugin).getConfigManager().initConfig(args[0], worlds);
        worlds.setName(args[0]);
        worlds.setSeed(n);
        worlds.setGenerator("LuckyCrates");
        WorldCreator worldCreator = new WorldCreator(args[0]);
        worldCreator.seed(n)
                .generateStructures(worlds.getGenerateStructures())
                .environment(worlds.getEnvironment())
                .type(worlds.getWorldType())
                .generator(worlds.getGenerator());

        try {
            worldCreator.createWorld();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Bukkit.getScheduler().scheduleSyncDelayedTask(getPlugin(), () -> {
            if (Bukkit.getWorld(args[0]) != null)

                sender.sendMessage(ChatColor.GREEN + "New World Created Successfully");
            else {
                sender.sendMessage(ChatColor.RED + "Error creating new world");
            }
        }, 60l);


        return true;
    }

    /**
     * Gets parent permission just get this to return the parent permission for the command
     *
     * @return the parent permission
     */
    @Override
    public String getParentPermission() {
        return "ipsum.admin";
    }

    /**
     * Requests a list of possible completions for a command argument.
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param alias   The alias used
     * @param args    The arguments passed to the command, including final
     *                partial argument to be completed and command label
     * @return A List of possible completions for the final argument, or null
     * to default to the command executor
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }

    /**
     * Gets the owner of this PluginIdentifiableCommand.
     *
     * @return Plugin that owns this PluginIdentifiableCommand.
     */
    @Override
    public Plugin getPlugin() {
        return plugin;
    }
}
