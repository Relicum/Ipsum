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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.relicum.ipsum.Utils.Msg;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.StringUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * CommandRegister used to register commands and also pre filter inbound commands.
 * <p>Create a new instance of {@link #CommandRegister(org.bukkit.plugin.Plugin)} in your main plugin class.
 * <p>Create it never the top of the onLoad. Then register all your cammands using the {@link #register(AbstractCommand)} .
 * <p>To create commands see {@link com.relicum.ipsum.Commands.AbstractCommand}
 *
 * @author Relicum
 * @version 0.0.1
 */
public class CommandRegister implements TabExecutor {

    @Getter
    private Plugin plugin;

    private Map<String, AbstractCommand> commands;

    private Msg msg;

    private List<String> rootCmd;

    private List<String> LEVEL1;

    private List<String> USE_TAB;

    /**
     * Instantiates a new Command register.
     *
     * @param plugin the plugin
     */
    public CommandRegister(Plugin plugin) {
        this.LEVEL1 = new ArrayList<>();
        this.plugin = plugin;
        rootCmd = new ArrayList<>(3);
        this.commands = new HashMap<>();
        this.USE_TAB = new ArrayList<>();
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String subCmd = "";

        if (!commands.containsKey(cmd.getName().toLowerCase()) && !isRootCmd(cmd.getName().toLowerCase())) { //Check me can find details of the command

            sender.sendMessage(ChatColor.RED + "Error command not found");
            return true;
        }

        if (rootCmd.contains(cmd.getName().toLowerCase()) && args.length == 0) { //Deal with main sub command

            sender.sendMessage(ChatColor.AQUA + "Main Cmd help section");
            return true;
        }
        AbstractCommand sub;
        if (args.length == 0) {
            sub = commands.get(cmd.getName().toLowerCase());
        } else {

            sub = commands.get(args[0].toLowerCase());
        }

        assert sub != null;
        int total = 0;
        if (sub.isSub())
            total = args.length - 1;
        else
            total = args.length;

        if ((sub.isSub() && args.length >= 1) || !sub.isSub()) {


            if (!(sender instanceof Player) && !sub.isAllowConsole()) { //Check if console can receive command

                sender.sendMessage(ChatColor.RED + "Error: You can only run this command from in game");
                return true;

            }
            if ((total) < sub.getMin()) { //Check the minimum number of arguments

                sender.sendMessage(ChatColor.RED + "Error: invalid number of arguments the minimum number is " + sub.getMin());

                return true;
            }

            if (sub.getMax() != -1 && (total > sub.getMax())) { //Check the maximum number of arguments

                sender.sendMessage(ChatColor.RED + "Error: invalid number of arguments the maximum number is " + sub.getMax());

                return true;

            }

            if (sub.getMax() != -1 && (sub.getMin() > sub.getMax())) { //Check I haven't made a mistake and got args round the wrong way

                sender.sendMessage(ChatColor.RED + "Error: invalid number of arguments the minimum number can't be greater than the max allowed");

                return true;

            }

            if ((!sender.isOp()) && (!sender.hasPermission(sub.getPermission()))) { //Check permissions

                sender.sendMessage(ChatColor.RED + "Error: You do not have permission to run this command");

                return true;

            }
            if (sub.isSub()) {
                subCmd = args[0].intern();
                args = Arrays.copyOfRange(args, 1, args.length);
            }
            try {
                sub.onCommand(sender, subCmd, args);  //Try and run the command
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                sub = null;
                subCmd = null;
                args = null;

            }

            return true;


        }


        plugin.getLogger().severe("Unhandled command Exception needs investigating");  //If we get to here we have a problem
        throw new CommandException("Unhandled command exception please report to developer");

    }


    /**
     * Register the new command class.
     *
     * @param <T> extends {@link com.relicum.ipsum.Commands.AbstractCommand}
     * @param cmd the instance of the command class.
     */
    public <T extends AbstractCommand> void register(T cmd) throws IllegalArgumentException {


        if (!this.commands.containsKey(cmd.getName().toLowerCase()))
            commands.putIfAbsent(cmd.getName(), cmd);

        registerCommand(cmd.getName(), cmd);


    }


    private boolean isRootCmd(String cmd) {

        return rootCmd.contains(cmd);
    }

    private boolean inLevel1(String cmd) {

        return LEVEL1.contains(cmd);
    }

    private boolean useTabComplete(String cmd) {

        return USE_TAB.contains(cmd);
    }

    protected boolean registerCommand(String name, AbstractCommand cmd) {

        if (!isRootCmd(cmd.getParent().toLowerCase())) {

            rootCmd.add(cmd.getParent().toLowerCase());
        }

        if (!inLevel1(name.toLowerCase())) {

            LEVEL1.add(name.toLowerCase());
        }

        if (cmd.useTab) {

            USE_TAB.add(name.toLowerCase());
        }

        String[] ps = cmd.getPermission().split("\\.");
        String ubPerm = ps[0] + "." + ps[1];

        Permission per = new Permission(cmd.getPermission());
        per.setDefault(PermissionDefault.OP);
        per.addParent(ubPerm, true);
        per.setDescription(cmd.getDescription());

        plugin.getServer().getPluginManager().addPermission(per);

        System.out.println("New Permission registered " + per.getName());

        CommandMap cmp = getCommandMap();
        PluginCommand cd = getCommand(name.toLowerCase());

        cd.setDescription(cmd.getDescription());
        if (cmd.getUsage() != null)
            cd.setUsage(cmd.getUsage());

        cd.setAliases(cmd.getAliases());
        String lab;
        if (cmd.isSub()) {
            lab = cmd.getParent().toLowerCase() + " " + name.toLowerCase();
            System.out.println("Sub is true");
        } else {
            lab = name.toLowerCase();
            System.out.println("sub is false");
        }
        cd.setExecutor(this);
        cd.setPermission(cmd.getPermission());
        cd.setTabCompleter(this);

        if (cmp.register(lab, "mc", cd)) {

            System.out.println("Command: /" + cd.getLabel() + " has successfully been registered");
            return true;
        }
        System.out.println("Command: /" + cd.getLabel() + " has FAILED to be registered");

        return false;

    }


    /**
     * Destroy and clear all objects call this in the plugins onDisable method.
     */
    public void destroy() {

        this.commands = null;
        this.plugin = null;
        rootCmd = null;

    }


    /**
     * End registration, you should call this once all commands are registered.
     * <p>It creates Immutable lists and Maps to help of sync and efficiency.
     */
    public void endRegistration() {

        commands = ImmutableMap.copyOf(commands);
        rootCmd = ImmutableList.copyOf(rootCmd);
        LEVEL1 = ImmutableList.copyOf(LEVEL1);
    }

    /**
     * Returns an instance of Command object setup For the command name you give
     * it.
     *
     * @param name String
     * @return PluginCommand
     */
    protected PluginCommand getCommand(String name) {

        PluginCommand command = null;
        try {
            Constructor c = PluginCommand.class.getDeclaredConstructor(new Class[]{String.class, Plugin.class});
            c.setAccessible(true);

            command = (PluginCommand) c.newInstance(name, plugin);

        } catch (SecurityException | IllegalArgumentException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return command;
    }

    /**
     * Returns an instance of CommandMap which Can then be used to correctly
     * register the command and details with Bukkit
     *
     * @return CommandMap
     */
    protected CommandMap getCommandMap() {

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


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            return StringUtil.copyPartialMatches(strings[0], LEVEL1, new ArrayList<>(LEVEL1.size()));
        }
        if (strings.length > 1 && strings.length < 6) {

            if (!useTabComplete(strings[0])) {

                return Collections.emptyList();


            } else {

                List<String> list = commands.get(strings[0]).tabComp(strings.length);
                if (list.size() == 0)
                    return Collections.emptyList();

                return StringUtil.copyPartialMatches(strings[strings.length - 1], list, new ArrayList<>(list.size()));

            }

        }

        return Collections.emptyList();
    }

}
