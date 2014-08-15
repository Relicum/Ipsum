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
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Name: SimpleCommand.java Created: 05 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */

public abstract class SimpleCommand implements BaseCommand, TabExecutor, PluginIdentifiableCommand {

    private Plugin plugin;
    //private String name;
    private List<String> aliases = new ArrayList<>();

    public SimpleCommand(List<String> aliasess, Plugin plugin) {
        this.plugin = plugin;

        this.aliases.addAll(aliasess.stream().collect(Collectors.toList()));

        if (!getClass().isAnnotationPresent(CmdInfo.class)) {
            try {
                throw new CommandException("no annotations found");
            } catch (CommandException e) {
                e.printStackTrace();

            }

        }
        //registerCommand(getCmdName());

    }

    /**
     * Executes the given command, returning its success
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean success = false;

        if (!getPlugin().isEnabled()) {
            try {
                throw new CommandException("Unhandled exception while executing command " + command + "in plugin " + getPlugin().getDescription().getFullName() + ": plugin is not enabled!");
            } catch (Throwable ex) {
                ex.printStackTrace();
            }

        }
        if (!sender.hasPermission(getPermission()) && !sender.isOp()) {
            sender.sendMessage(getPermissionMessage());
            return true;
        }

        if ((isPlayerOnly()) && (!(sender instanceof Player))) {
            sender.sendMessage(ChatColor.RED + "This command can only be run in game");
            return true;
        }

        if (!(args.length >= getMinArgs()) || !(args.length <= getMaxArgs())) {
            sender.sendMessage(ChatColor.RED + "Invalid number of arguments min is: " + getMinArgs() + " max is: " + getMaxArgs());
            return true;
        }

        try {
            success = onCommand(sender, command, args);
        } catch (Throwable ex) {
            throw new CommandException("Unhandled exception executing command '" + command.getName() + "' in plugin " + getPlugin().getDescription().getFullName(), ex);
        }

        if ((!success) && (getUsage().length() > 0)) {
            for (String line : getUsage().replace("<command>", command.getName()).split("\n")) {
                sender.sendMessage(line);
            }
        }

        return success;


    }

    public abstract boolean onCommand(CommandSender sender, Command command, String[] args);

    /**
     * Gets parent permission just get this to return the parent permission for the command
     *
     * @return the parent permission
     */
    public abstract String getParentPermission();

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
    public abstract List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args);

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
        return getCommandInfo().usage();
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

    /**
     * Gets aliases.
     *
     * @return the command aliases
     */
    @Override
    public List<String> getAliases() {
        return this.aliases;
    }


    public CmdInfo getCommandInfo() {
        return getClass().getAnnotation(CmdInfo.class);
    }


    /**
     * Returns an instance of CommandMap which Can then be used to correctly
     * register the command and details with Bukkit
     *
     * @return CommandMap
     */
    public CommandMap getCommandMap() {

        CommandMap commandMap = null;

        try {

            Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);

            commandMap = (CommandMap) f.get(Bukkit.getServer());

        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return commandMap;
    }

    /**
     * Returns an instance of Command object setup For the command name you give
     * it.
     *
     * @return PluginCommand
     */
    public PluginCommand getCommand() {

        PluginCommand command = null;
        try {
            Constructor c = PluginCommand.class.getDeclaredConstructor(new Class[]{String.class, Plugin.class});
            c.setAccessible(true);

            command = (PluginCommand) c.newInstance(getCmdName().toLowerCase(), plugin);
            //command = (PluginCommand) c.newInstance(new Object[]{name , plugin});
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return command;
    }


    public boolean registerCommand() {

        //   Ipsum.getInstance().getPermissionManager().registerPermission(this.getPermission(), this.getDescription(), getParentPermission(), PermissionDefault.OP);

        CommandMap cmp = getCommandMap();
        PluginCommand cd = getCommand();
        cd.setPermissionMessage(this.getPermissionMessage());

        cd.setDescription(this.getDescription());
        cd.setUsage(this.getUsage());
        cd.setAliases(this.getAliases());
        cd.setExecutor(this);
        cd.setTabCompleter(this);
        cd.setLabel(this.getLabel());
        cd.setPermission(this.getPermission());
        if (cmp.register("", cd)) {
            System.out.println("Command: /" + this.getLabel() + " has successfully been registered");

            return true;
        }

        System.out.println("Error registering command");
        return false;
    }
}
