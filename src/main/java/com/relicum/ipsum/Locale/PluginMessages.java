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

import com.relicum.ipsum.io.PropertyIO;
import org.apache.commons.lang.Validate;

import java.io.IOException;
import java.util.Properties;

/**
 * Manages plugin messages in a Properties format.
 * <p>This is used when creating a new multi language plugin messages file as well as adding messages
 * to a pre existing file.
 * <P>The class has built in methods to read and write to file automatically.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class PluginMessages implements IDMessage, PropertyIO {

    /**
     * The Properties object that stores the messages
     */
    protected Properties mess = new Properties();

    /**
     * Instantiates a new Plugin messages setting some basic default messages that all plugins need to use this system.
     * <p>Any of the default messages can be over ridden.
     */
    public PluginMessages() {
        setAddDefaults();
    }

    /**
     * Instantiates a new Plugin messages with the option to set the default messages or not.
     * <p>If you set this to false all the default messages will have to be manually specified.
     *
     * @param useDefaults set to false to not have the default messages included.
     */
    public PluginMessages(boolean useDefaults) {

        if (useDefaults)
            setAddDefaults();


    }

    /**
     * Instantiates a new Plugin messages object loading messages from from the specified string path.
     * <p>This method should be used once a new file has been created if not you past messages will be overridden each time.
     *
     * @param path the path of the file to load. The file must be an instance of a {@link java.util.Properties} file.
     * @throws IOException the {@link java.io.IOException} if there was a error loading the file, including if the file location was not vaild.
     */
    public PluginMessages(String path) throws IOException {
        Validate.notNull(path);
        mess = readFromFile(path);
    }

    /**
     * Instantiates a new Plugin messages passing in a {@link java.util.Properties} object.
     *
     * @param properties the properties instance to load into the store.
     */
    public PluginMessages(Properties properties) {
        this.mess = properties;
    }

    /**
     * Set {@link java.util.Properties} object contain plugin messages
     *
     * @param prop the {@link java.util.Properties} object to set as messages
     */
    public void setMessages(Properties prop) {
        this.mess = prop;
    }


    /**
     * Sets default messages
     */
    protected void setAddDefaults() {

        addMessage("default.prefix", "&b[&5IPSUM&b]&r ");
        addMessage("default.color.normal", "&a");
        addMessage("default.color.error", "&c");
        addMessage("default.color.admin", "&2");
        addMessage("default.color.broadcast", "&6");
        addMessage("default.message.error", "An unknown error has occured please check the log for details");
        addMessage("default.message.noperms", "Sorry you do not have permission to do that");

        addMessage("default.debug.prefix", "&b[&4IPSUM-DEBUG&b]&r ");
        addMessage("default.debug.color", "&d");


    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Properties getProperties() {
        return this.mess;
    }
}
