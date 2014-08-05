package com.relicum.ipsum.Commands;

import org.bukkit.plugin.Plugin;

import java.util.List;


/**
 * The interface Base command.
 *
 * @author Relicum
 * @version 0.0.1
 */
public interface BaseCommand {


    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription();

    /**
     * Gets usage.
     *
     * @return the usage
     */
    public String getUsage();

    /**
     * Gets permission.
     *
     * @return the permission
     */
    public String getPermission();

    /**
     * Is player only.
     *
     * @return the boolean
     */
    public boolean isPlayerOnly();

    /**
     * Gets min args.
     *
     * @return the min args
     */
    public int getMinArgs();

    /**
     * Gets max args.
     *
     * @return the max args
     */
    public int getMaxArgs();

    /**
     * Is sub.
     *
     * @return the boolean
     */
    public boolean isSub();

    /**
     * Gets plugin.
     *
     * @return the plugin
     */
    public Plugin getPlugin();

    /**
     * Gets permission message.
     *
     * @return the permission message
     */
    public String getPermissionMessage();

    /**
     * Gets label.
     *
     * @return the label
     */
    public String getLabel();

    /**
     * Gets cmd name.
     *
     * @return the cmd name
     */
    public String getCmdName();

    /**
     * Gets aliases.
     *
     * @return the command aliases
     */
    public List<String> getAliases();

}
