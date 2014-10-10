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

package com.relicum.ipsum.Locale;

import net.minecraft.util.org.apache.commons.lang3.Validate;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Message Creator, used to in combination with other classes to build multiple lang files in the I18N standard.
 * <p>This is not very configurable as its meant to be a quick go to method but for most plugins this will be ideal.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class MessageCreator extends PluginMessages {

    protected String extension = ".properties";
    protected ConfigurationSection section;
    protected Plugin plugin;
    protected String srcPath;
    protected String savePath;
    protected String bundle = "MessagesBundle";
    protected String bundleMessage = "Plugin Messages";
    protected String GBLocale = "en_GB";
    protected String USLocale = "en_US";
    protected final Map<String, String> defaultFormat;


    /**
     * Instantiates a new Message Creator, used to in combination with other classes to build multiple lang files in the I18N standard.
     * <p>This is not very configurable as its meant to be a quick go to method but for most plugins this will be ideal.
     *
     * @param plugin  the plugin
     * @param scrPath the scr path is the absolute path to the root of your plugin source files, or the resources folder if using Maven
     * @param format  the {@link com.relicum.ipsum.Commands.Group.DefaultFormat} allows you to set some of the defaults or you can just pass in the Object and it will use the default settings.
     * @throws RuntimeException the runtime exception
     * @throws IOException      the iO exception
     */
    public MessageCreator(Plugin plugin, String scrPath, Map<String, String> format) throws RuntimeException, IOException {
        Validate.notNull(plugin);
        Validate.notNull(scrPath);
        Validate.notNull(format);
        this.plugin = plugin;
        this.savePath = plugin.getDataFolder().toString() + File.separator;
        this.srcPath = scrPath;
        this.defaultFormat = format;


        if (!configCreated()) {
            section = plugin.getConfig().createSection("messages");
            createConfig();
            plugin.saveConfig();
            plugin.reloadConfig();
        }

        section = plugin.getConfig().getConfigurationSection("messages");

        if (plugin.getConfig().getString("messages.enable").equals("false")) {
            plugin.getLogger().info("MessageCreator setting enable is false. Do you need to remove it from the plugin");
            return;
        }


        if (section.getBoolean("devMode")) {

            addMessagesFromMap(defaultFormat);


            section.set("enable", false);
            section.set("devMode", false);

            plugin.saveConfig();
            plugin.reloadConfig();

            plugin.getLogger().info("PLEASE RECOMPILE THE PLUGIN SO THE NEW PLUGIN MESSAGES CAN BE USED");
            return;

        }


        plugin.getLogger().info("No messages have been loaded for editing");

    }


    /**
     * Save the resource bundle files back to the root directory of the plugin source.
     * <p>Should only be used during plugin creation not in a live environment.
     *
     * @throws IOException the {@link java.io.IOException} if any errors occured.
     */
    public void saveToSource() throws IOException {

        writeToFile(getProperties(), srcPath + bundle + extension, bundleMessage);
        writeToFile(getProperties(), srcPath + bundle + "_" + GBLocale + extension, bundleMessage);
        writeToFile(getProperties(), srcPath + bundle + "_" + USLocale + extension, bundleMessage);
    }


    /**
     * Save the resource bundle files to the plugins root directory.
     * <p>Should only be used during plugin creation not in a live environment.
     *
     * @throws IOException the {@link java.io.IOException} if any errors occured.
     */
    public void save() throws IOException {

        writeToFile(getProperties(), savePath + bundle + extension, bundleMessage);
        writeToFile(getProperties(), savePath + bundle + "_" + GBLocale + extension, bundleMessage);
        writeToFile(getProperties(), savePath + bundle + "_" + USLocale + extension, bundleMessage);
    }


    private void createConfig() {
        section.set("enable", true);
        section.set("devMode", true);
        section.set("locale", "en_GB");
        section.set("saveFiles", false);
    }

    private boolean configCreated() {

        return plugin.getConfig().contains("messages");

    }


    /**
     * Gets the location of the root directory of the plugins source.
     *
     * @return the root directory of of the plugin.
     */
    public String getSrcPath() {
        return srcPath;
    }

    /**
     * Set the save location to the root folder in the plugins source folder.
     * <p>Remember on windows you will need to escape any \ that the path contains. This should be the absolute path, not relative.
     * <p>This is used to make it easy to add me messages and have them written directly to the source.
     * The reason for this is that I18N reads the messages embedded in the Jar file itself.
     * <p>You should only have this set to save to the source path IF you have added a new message. You need to compile the plugin
     * and run it once and stop the plugin straight away. Now the new messages will appear in the source folder. Now disable it saving to
     * the source location. Then recompile the plugin and the new messages will be available to use.
     *
     * @param srcPath the path to the root directory of the plugins source.
     */
    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }

    /**
     * Set resource bundle name.
     * <p>You only need to set this if you want to use a different name default is MessagesBundle
     *
     * @param bundle resource bundle name
     */
    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    /**
     * Get resource bundle name.
     *
     * @return name of the resource bundle
     */
    public String getBundle() {
        return bundle;
    }

    /**
     * Set message that appears on the first line of the resource bundle files.
     * <p>Only set this if you wish to change it. Default is <strong>Plugin Messages</strong>
     *
     * @param bundleMessage resource bundle file message.
     */
    public void setBundleMessage(String bundleMessage) {
        this.bundleMessage = bundleMessage;
    }

    /**
     * Get the resource bundle file message.
     *
     * @return Value of bundleMessage.
     */
    public String getBundleMessage() {
        return bundleMessage;
    }
}
