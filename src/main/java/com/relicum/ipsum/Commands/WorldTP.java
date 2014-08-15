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

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * WorldTP is a simple command to TP from world to world.
 * <p><strong>tp</strong> can also be used as an aliases. You do not specify the location in the world it will
 * Automatically TP you to the worlds default spawn.
 * <p>At a later data I will add in options to specify where in the world to TP to.
 * Using the tab key will scroll through the avaiable worlds you can TP to.
 *
 * @author Relicum
 * @version 0.0.1
 */
@CmdInfo(name = "worldtp", description = "Teleport to different worlds",
        usage = "/<command> <world>", label = "worldtp", permission = "lucky.admin.worldtp.use", minArgs = 1, maxArgs = 1, playerOnly = true, subCommand = false)
public class WorldTP extends SimpleCommand {

    private List<String> WORLDS = new ArrayList<>();
    private Plugin plugin;

    public WorldTP(List<String> aliasess, Plugin plugin) {
        super(aliasess, plugin);
        this.plugin = plugin;
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, this::updateList, 120l);

    }

    public void updateList() {
        WORLDS.addAll(getPlugin().getServer().getWorlds().stream().map(World::getName).collect(toList()));
        getPlugin().getLogger().info("Worlds should now of loaded into world TP list");
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String[] args) {
        Player player = (Player) sender;

        String wname = args[0];
        World world1;

        world1 = Bukkit.getWorld(wname);

        if (world1 != null) {
            world1.getSpawnLocation().getChunk().load();
            player.sendMessage(ChatColor.GREEN + "Please wait you are being teleported to " + wname);
            Bukkit.getScheduler().scheduleSyncDelayedTask(getPlugin(), () ->
                    player.teleport(world1.getSpawnLocation()), 10l);

            return true;

        } else {
            player.sendMessage(ChatColor.RED + "That world does not exist can teleport you there");
            return true;
        }

    }

    /**
     * Gets parent permission just get this to return the parent permission for the command
     *
     * @return the parent permission
     */
    @Override
    public String getParentPermission() {
        return "lucky.admin.*";
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

        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], WORLDS, new ArrayList<>(WORLDS.size()));
        }

        return Collections.emptyList();
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
