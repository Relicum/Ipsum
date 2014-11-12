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

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * Name: SubCommand.java Created: 09 October 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
@Deprecated
@SubCmdInfo(name = "display", description = "Display a list of entities", usage = "/sub %command% [world] [TileEntities]", label = "sub display",
        permission = "display.use", minArgs = 3, maxArgs = 3, playerOnly = false, subCommand = true, subPrefix = "sub")
public class SubCommand implements BaseCommand, CommandExecutor, PluginIdentifiableCommand {


    private Plugin plugin;


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        return false;
    }


    /**
     * Gets description.
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return getCommandInfo().description();
    }

    /**
     * Gets usage.
     *
     * @return the usage
     */
    @Override
    public String getUsage() {
        return getCommandInfo().usage().replace("%command%", getCmdName());
    }

    /**
     * Gets permission.
     *
     * @return the permission
     */
    @Override
    public String getPermission() {
        return getCommandInfo().permission();
    }

    /**
     * Is player only.
     *
     * @return the boolean
     */
    @Override
    public boolean isPlayerOnly() {
        return getCommandInfo().playerOnly();
    }

    /**
     * Gets min args.
     *
     * @return the min args
     */
    @Override
    public int getMinArgs() {
        return getCommandInfo().minArgs();
    }

    /**
     * Gets max args.
     *
     * @return the max args
     */
    @Override
    public int getMaxArgs() {
        return getCommandInfo().maxArgs();
    }

    /**
     * Is sub.
     *
     * @return the boolean
     */
    @Override
    public boolean isSub() {
        return getCommandInfo().subCommand();
    }

    /**
     * Gets permission message.
     *
     * @return the permission message
     */
    @Override
    public String getPermissionMessage() {
        return ChatColor.RED + "You do not have permission to run this command";
    }

    /**
     * Gets label.
     *
     * @return the label
     */
    @Override
    public String getLabel() {
        return getCommandInfo().label();
    }

    /**
     * Gets cmd name.
     *
     * @return the cmd name
     */
    @Override
    public String getCmdName() {
        return getCommandInfo().name();
    }

    public String getPrefix() {
        return getCommandInfo().subPrefix();

    }


    /**
     * Gets aliases.
     *
     * @return the command aliases
     */
    @Override
    public List<String> getAliases() {
        return null;
    }

    /**
     * Gets command info annotation {@link com.relicum.ipsum.Commands.SubCmdInfo}
     *
     * @return the {@link com.relicum.ipsum.Commands.SubCmdInfo} class
     */
    public SubCmdInfo getCommandInfo() {
        return getClass().getAnnotation(SubCmdInfo.class);
    }

    @Override
    public Plugin getPlugin() {
        return plugin;
    }

    /**
     * Sets new plugin.
     *
     * @param plugin New value of plugin.
     */
    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }


}
