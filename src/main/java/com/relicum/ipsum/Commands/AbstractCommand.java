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

import com.relicum.ipsum.Annotations.Command;
import com.relicum.ipsum.Annotations.Console;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * AbstractCommand needs to be extended and add the annotation {@link com.relicum.ipsum.Annotations.Command} .
 * <p>You will need to add the relevant values to the annotation, the only default ones are aliases, permission and description.
 * <p>Everything else is optional. You can make both single or sub commands from this single classes.
 * <p>To create subcommand which actually hows all the sub commands in bukkits standard help, you will need to do the following.
 * <p>The actually parent sub command itself eg say it was <strong>/sg</strong> you need to register the <Strong>sg</Strong> command allow with is permissions
 * in the plugin.yml file. You will also need to call the command in the onEnable BEFORE you start registering its subcommands.
 * <p>Registering all of your sub commands is easy just create a new instance of {@link com.relicum.ipsum.Commands.CommandRegister} and use the register function to
 * pass in a new instance of the command and everything else will be done for you. All permission checking and argument checks and done for you.
 * <p>If you don't add the {@link com.relicum.ipsum.Annotations.Console} annoation to the command then only players will be able to use the command. Again all the checking is done for you.
 * <p>All permissions are also registered correctly with bukkit as well as make a sub command have a child permission to its parent.
 *
 * @author Relicum
 * @version 0.0.1
 */
public abstract class AbstractCommand implements CmdExecutor, PluginIdentifiableCommand {

    @Getter
    protected String permission;
    @Getter
    protected String description;
    @Getter
    protected int min;
    @Getter
    protected int max;
    @Getter
    protected boolean sub;
    @Getter
    protected String parent;
    @Getter
    protected boolean useTab;
    @Setter
    @Getter
    protected String help;
    @Getter
    protected String usage;
    @Getter
    protected String name;
    @Getter
    protected List<String> aliases;
    @Getter
    protected boolean allowConsole = false;

    protected final Plugin plugin;

    /**
     * Instantiates a new AbstractCommand
     *
     * @param plugin the plugin
     */
    public AbstractCommand(Plugin plugin) {
        this.plugin = plugin;
        setup();
    }


    private void setup() {

        permission = getCommand().perm();
        description = getCommand().desc();
        min = getCommand().min();
        max = getCommand().max();
        sub = getCommand().isSub();
        parent = getCommand().parent();
        usage = getCommand().usage();
        useTab = getCommand().useTab();
        List<String> tmp = Arrays.asList(getCommand().aliases());
        if (tmp.size() == 1) {

            name = tmp.get(0);
            aliases = Collections.emptyList();
        } else {
            name = tmp.get(0);
            aliases = new ArrayList<>(tmp.size() - 1);
            for (int i = 1; i < tmp.size(); i++) {
                aliases.add(i, tmp.get(1));
            }

        }

        if (getClass().isAnnotationPresent(Console.class)) {
            allowConsole = true;
        }

        tmp = null;
    }

    /**
     * Get a handle to the {@link com.relicum.ipsum.Annotations.Command} there should not be any need to use it though.
     *
     * @return the handle to the {@link com.relicum.ipsum.Annotations.Console}
     */
    public Command getCommand() {

        return getClass().getAnnotation(Command.class);
    }

    /**
     * Get plugin.
     *
     * @return the plugin that is running the command.
     */
    @Override
    public Plugin getPlugin() {
        return plugin;
    }

    /**
     * Format helper for command help strings
     *
     * @param sub  the main sub command if it has one.
     * @param cm   the sub command.
     * @param mess the help message.
     * @return the formatted string
     */
    public String formatHelp(String sub, String cm, String mess) {

        return ChatColor.GOLD + "/" + sub + " " + cm + ChatColor.GREEN + " : " + mess;
    }

    /**
     * Log a message to the log.
     *
     * @param text the text to log.
     */
    public void log(String text) {

        plugin.getLogger().info(text);
    }

    /**
     * Log warning message to the log.
     *
     * @param text the warning text to log.
     */
    public void logWarning(String text) {

        plugin.getLogger().warning(text);
    }

    /**
     * Log serve message to the log.
     *
     * @param text the serve text to log.
     */
    public void logServe(String text) {

        plugin.getLogger().severe(text);
    }

    /**
     * Tab comp. Override this if you want to use tab Complete.
     * <p>The first argument you can complete is when length is set to 2 as 1 is the sub command which will auto complete it for you.
     *
     * @param length the current command argument position
     * @return the list of available options for Tab complete.
     */
    public List<String> tabComp(int length) {
        return Collections.emptyList();
    }




}
