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
 * Name: SimpleCommand.java Created: 05 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */

public abstract class SimpleCommand implements BaseCommand, TabExecutor, PluginIdentifiableCommand {

    private Plugin plugin;
    private String name;
    private List<String> aliases = new ArrayList<>();

    public SimpleCommand(String name, List<String> aliasess, Plugin plugin) {
        this.plugin = plugin;
        this.name = name;
        for (String aliase : aliasess) {
            this.aliases.add(aliase);
        }

        if (!getClass().isAnnotationPresent(CmdInfo.class)) {
            try {
                throw new CommandException("no annotations found");
            } catch (CommandException e) {
                e.printStackTrace();

            }

        }
        registerCommand(name);

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

        if (!plugin.isEnabled()) {
            try {
                throw new CommandException("Unhandled exception while executing command " + command + "in plugin " + plugin.getDescription().getFullName() + ": plugin is not enabled!");
            } catch (Throwable ex) {
                ex.printStackTrace();
            }

        }
        if (!sender.hasPermission(getPermission())) {
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
            throw new CommandException("Unhandled exception executing command '" + command.getName() + "' in plugin " + plugin.getDescription().getFullName(), ex);
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
     * Gets plugin.
     *
     * @return the plugin
     */
    @Override
    public Plugin getPlugin() {
        return plugin;
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
        return this.name;
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
     * @param name String
     * @return PluginCommand
     */
    public PluginCommand getCommand(String name) {

        PluginCommand command = null;
        try {
            Constructor c = PluginCommand.class.getDeclaredConstructor(new Class[]{String.class, Plugin.class});
            c.setAccessible(true);

            command = (PluginCommand) c.newInstance(name, plugin);
            //command = (PluginCommand) c.newInstance(new Object[]{name , plugin});
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return command;
    }

    public boolean registerCommand(String name) {

        CommandMap cmp = getCommandMap();
        PluginCommand cd = getCommand(name.toLowerCase());
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
