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

/**
 * All commands need to extend this class as well as adding the annotation {@link com.relicum.ipsum.Commands.CmdInfo} to the class.
 * <p>The {@link com.relicum.ipsum.Commands.CmdInfo} annotation has the following values which you must supply values for
 * all of them.</p>
 * <p> Command Details
 * <ol>
 * <li><strong>name</strong>: The name of the command</li>
 * <li><strong>description</strong>: A description of the command (Used in Bukkit Help)</li>
 * <li><strong>usage</strong>: Command usage eg <tt>"/command [args]"</tt></li>
 * <li><strong>label</strong>: The label of the command</li>
 * <li><strong>permission</strong>: The permission to use the command</li>
 * <li><strong>minArgs</strong>: The minimum number of arguments</li>
 * <li><strong>maxArgs</strong>: The maximum number of arguments</li>
 * <li><strong>playerOnly</strong>: True if command is run by players only. False for console or player</li>
 * <li><strong>subCommand</strong>: True if this is a sub command (Still in development)</li>
 * </ol>
 *
 * @author Relicum
 * @version 0.0.1
 */
public abstract class SimpleCommand implements BaseCommand, TabExecutor, PluginIdentifiableCommand {
    public boolean debug = false;
    public Plugin plugin;
    private String parentPermissions = "ipsum.admin";
    public List<String> aliases = new ArrayList<>();

    /**
     * Instantiates a new Simple command.
     *
     * @param aliasess         the aliasess the command can use.
     * @param plugin           the {@link org.bukkit.plugin.Plugin} the command is to be registered under.
     * @param parentPermission the parent permission for this command
     */
    public SimpleCommand(List<String> aliasess, Plugin plugin, String parentPermission) {
        this.plugin = plugin;
        this.setParentPermissions(parentPermission);
        for (String s : aliasess) {

            this.aliases.add(s);
        }
        //  this.aliases.addAll(aliasess.stream().collect(Collectors.toList()));

        if (!getClass().isAnnotationPresent(CmdInfo.class)) {
            try {
                throw new CommandException("no annotations found");
            } catch (CommandException e) {
                e.printStackTrace();

            }

        }


    }

    /**
     * Instantiates a new Simple command with the option of turning debugging on.
     * <p>Default debugging is false, meaning it is set to off
     *
     * @param aliasess         the aliasess the command can use.
     * @param plugin           the {@link org.bukkit.plugin.Plugin} the command is to be registered under.
     * @param parentPermission the parent permission for this command
     * @param debug            set to true to turn on debugging, default it is set to false
     */
    public SimpleCommand(List<String> aliasess, Plugin plugin, String parentPermission, boolean debug) {
        this.plugin = plugin;
        this.debug = debug;
        this.setParentPermissions(parentPermission);
        this.plugin = plugin;
        for (String s : aliasess) {

            this.aliases.add(s);
        }

        //this.aliases.addAll(aliasess.stream().collect(Collectors.toList()));

        if (!getClass().isAnnotationPresent(CmdInfo.class)) {
            try {
                throw new CommandException("no annotations found");
            } catch (CommandException e) {
                e.printStackTrace();

            }

        }


    }

    /**
     * Executes the given command, returning its success.
     * <p>You do not need to alter this method. There is an abstract method you need to
     * impliment where you command logic goes.
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

    /**
     * Add your command logic to this method.
     * <p>No checks for permissions, number of arguments, if its a player only command is required in this method.
     * All of these checks will have already been done before this method is run.
     *
     * @param sender  the sender
     * @param command the command {@link org.bukkit.command.Command}
     * @param args    the string array of command arguments.
     * @return the {@link java.lang.Boolean} true on success, false if there are errors.
     */
    public abstract boolean onCommand(CommandSender sender, Command command, String[] args);

    /**
     * Gets parent permission just get this to return the parent permission for the command
     *
     * @return the parent permission
     */
    //public abstract String getParentPermission();

    /**
     * Gets parent permissions just get this to return the parent permission for the command
     *
     * @return the parent permission
     */
    public final String getParentPermission() {
        return parentPermissions;
    }

    public void setParentPermissions(String parentPermissions) {
        this.parentPermissions = parentPermissions;
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


    /**
     * Gets command info annotation {@link com.relicum.ipsum.Commands.CmdInfo}
     *
     * @return the {@link com.relicum.ipsum.Commands.CmdInfo} class
     */
    public CmdInfo getCommandInfo() {
        return getClass().getAnnotation(CmdInfo.class);
    }

    /**
     * Set debug.
     *
     * @param b the {@link java.lang.Boolean} true to enable debugging, default debugging is false.
     */
    public void setDebug(boolean b) {
        this.debug = b;
    }

    /**
     * Check if debugging is enabled.
     *
     * @return true if debugging is enabled, false and it is disabled
     */
    public boolean isDebug() {
        return this.debug;
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
            if (isDebug()) e.printStackTrace();
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

        } catch (SecurityException | IllegalArgumentException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            if (isDebug()) e.printStackTrace();
        }

        return command;
    }


    /**
     * Register the command.
     *
     * @return true if successful, false if there were errors
     */
    public boolean registerCommand() {


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
            if (isDebug()) System.out.println("Command: /" + this.getLabel() + " has successfully been registered");

            return true;
        }

        System.out.println("Error registering Command: /" + this.getLabel());
        return false;
    }

}
