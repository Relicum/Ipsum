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

import com.relicum.ipsum.Ipsum;
import com.relicum.ipsum.Utils.LocUtils;
import net.cubespace.Yamler.Config.InvalidConfigurationException;
import net.minecraft.util.com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Name: WorldModify.java Created: 06 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
@CmdInfo(name = "worldmodify", description = "Used to modify world settings", usage = "/<command> <world> <action> [extras]", label = "WorldModify", permission = "worldmodify.use",
        minArgs = 2, maxArgs = 6, playerOnly = true, subCommand = false)
public class WorldModify extends SimpleCommand {

    private Ipsum plugin;
    private List<String> WORLDS = Lists.newArrayList();

    private List<String> SET = Lists.newArrayList();

    private List<String> BOLL = Lists.newArrayList();

    public WorldModify(List<String> aliasess, Plugin plugin) {
        super(aliasess, plugin);
        this.plugin = (Ipsum) plugin;
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, this::updateList, 120l);
        addSET();
        addBOLL();
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String[] args) {

        Player player = (Player) sender;

        if (args[1].equalsIgnoreCase("setspawn")) {

            player.getWorld().setSpawnLocation(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
            player.sendMessage(ChatColor.GREEN + "Spawn has successfully been set for world " + player.getWorld().getName() + " at " +
                    ChatColor.GOLD + "X = " + player.getLocation().getBlockX() + " Y = " + player.getLocation().getBlockY() + " Z = " + player.getLocation().getBlockZ());

            plugin.getConfigManager().getWorld(player.getWorld().getName()).setSpawnLocation(LocUtils.locationToLoc(player.getLocation()));
            try {
                plugin.getConfigManager().getWorld(player.getWorld().getName()).save();
            } catch (InvalidConfigurationException e) {
                e.printStackTrace();
            }
            return true;
        }
        if (args[1].equalsIgnoreCase("unload")) {

            if (Bukkit.getWorld(args[0]) == null) {
                player.sendMessage(ChatColor.RED + "World not found nothing to unload");
                return true;
            }

            Bukkit.getWorld(args[0]).getPlayers().stream().forEach(p -> p.kickPlayer("Sorry this world is being unloaded"));

            plugin.getConfigManager().getWorld(args[0]).setEnable(false);

            if (Bukkit.unloadWorld(args[0], true)) {

                player.sendMessage(ChatColor.GREEN + "The world has been unloaded");
                try {
                    plugin.getConfigManager().getWorld(args[0]).save();
                } catch (InvalidConfigurationException e) {
                    e.printStackTrace();
                }

                return true;
            } else
                player.sendMessage(ChatColor.RED + "Error, unable to unload the world please check the logs");

            return true;


        }
        if (args[1].equalsIgnoreCase("load")) {

            if (Bukkit.getWorld(args[0]) != null) {
                player.sendMessage(ChatColor.RED + "That world is already loaded maybe you need to restart it");
                return true;
            }

            if (plugin.getConfigManager().getWorld(args[0]) == null) {

                plugin.getLogger().warning("Unable to find world config for world " + args[0] + " will search the directory");

                if (!plugin.getWorldManager().checkWorldHasConfig(args[0])) {
                    plugin.getLogger().severe("Unable to find world config for world " + args[0] + " so unable to load it");
                    player.sendMessage(ChatColor.RED + "Unable to find world config for world " + args[0] + " so unable to load it");
                    return true;
                }

                plugin.getWorldManager().loadWorld(args[0]);

                new BukkitRunnable() {

                    @Override
                    public void run() {
                        if (plugin.getConfigManager().getWorld(args[0]) != null) {

                            player.sendMessage(ChatColor.GREEN + "Successfully load world " + ChatColor.GOLD + args[0]);

                        } else
                            player.sendMessage(ChatColor.RED + "Unable to load world " + args[0]);
                    }
                }.runTaskLater(plugin, 80l);

                return true;

            }

        }

        if (args.length == 3 && (args[1].equalsIgnoreCase("monsters") || args[1].equalsIgnoreCase("animals") || args[1].equalsIgnoreCase("pvp"))) {

            if (!args[2].equalsIgnoreCase("true") && !args[2].equalsIgnoreCase("false")) {

                player.sendMessage(ChatColor.RED + "Error: you must use true or false as 3rd argument");
                return true;
            }

            if (args[1].equalsIgnoreCase("monsters"))
                plugin.getConfigManager().getWorld(player.getWorld().getName()).setMonsters(Boolean.valueOf(args[2].toLowerCase()));
            if (args[1].equalsIgnoreCase("animals"))
                plugin.getConfigManager().getWorld(player.getWorld().getName()).setAnimals(Boolean.valueOf(args[2].toLowerCase()));
            if (args[1].equalsIgnoreCase("pvp"))
                plugin.getConfigManager().getWorld(args[0]).setPvp(Boolean.valueOf(args[2].toLowerCase()));

            player.sendMessage(ChatColor.GREEN + args[1] + " has now been set to " + args[2] + " in world " + args[0]);

            plugin.getConfigManager().saveAll();

            return true;
        }

        return false;

    }


    public void updateList() {
        WORLDS.addAll(getPlugin().getServer().getWorlds().stream().map(World::getName).collect(toList()));
        getPlugin().getLogger().info("Worlds should now of loaded into world TP list");
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

    private void addSET() {

        SET.add("setspawn");
        SET.add("monsters");
        SET.add("animals");
        SET.add("unload");
        SET.add("pvp");
        SET.add("load");
        SET.add("time");
        SET.add("save");
        SET.add("reload");

    }

    private void addBOLL() {

        BOLL.add("true");
        BOLL.add("false");
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
        if (args.length == 2) {

            return StringUtil.copyPartialMatches(args[1], SET, new ArrayList<>(SET.size()));
        }

        if (args.length == 3 && (args[1].equalsIgnoreCase("monsters") || args[1].equalsIgnoreCase("animals")) || args[1].equalsIgnoreCase("pvp")) {

            return StringUtil.copyPartialMatches(args[2], BOLL, new ArrayList<>(BOLL.size()));

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
