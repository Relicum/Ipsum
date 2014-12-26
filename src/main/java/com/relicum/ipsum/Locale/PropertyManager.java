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

import lombok.extern.java.Log;
import org.apache.commons.lang.Validate;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

/**
 * Properties Manager used to create and manage properties files.
 * <p>This is mainly used during the creation of plugins to let you dynamically register plugin messages.
 * When the plugin is live you will need to use the {@link com.relicum.ipsum.Locale.LocaleManager} which handles single and multi
 * language messages for plugins.
 *
 * @author Relicum
 * @version 0.0.1
 */
@Log(topic = "Ipsum")
public class PropertyManager {

    private Properties properties;
    private Writer writer;
    private Reader reader;
    private String file;
    private boolean devMode = false;
    private String devPath;
    private String defaultBundle = "MessagesBundle";
    private String extension = ".properties";
    private String defaultCountyCode;

    /**
     * Instantiates a new Property Manager.
     * Creates an empty property list.
     */
    public PropertyManager() {

        properties = new Properties();
    }


    /**
     * Instantiates a new Property manager.
     * Creates a property list from the settings in the specified file.
     *
     * @param devMode the dev mode
     * @param path    the path
     * @param locale  the locale
     * @throws IOException the iO exception
     */
    public PropertyManager(boolean devMode, String path, Locale locale) throws IOException {
        Validate.notNull(path, "The path to the directory when in development mode can not be null");

        defaultCountyCode = "_" + locale.toLanguageTag().replace('-', '_');
        log.info(defaultCountyCode);
        this.devMode = devMode;
        this.devPath = path;
        try {
            Files.createDirectories(Paths.get(devPath));
        } catch (IOException e) {
            log.severe(e.getMessage());
            throw e;
        }

        properties = new Properties();
    }

    /**
     * Instantiates a new Property manager.
     * Creates a property list from the settings in the specified file.
     *
     * @param file the path to the properties file that will be loaded.
     * @throws IOException the if there was a problem loading the file.
     */
    public PropertyManager(String file) throws IOException {
        Validate.notNull(file, "File path can not null when initialising PropertyManager");
        this.file = file;


        properties = new Properties();

        readFromFile();
        log.info("New Props Manager created");

        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            System.out.println(entry.getKey().toString() + " = " + entry.getValue().toString());
        }

    }

    /**
     * Instantiates a new Property Manager with default properties.
     * Creates an empty property list with the specified defaults.
     *
     * @param props the instance of {@link java.util.Properties} which will be set as defaults
     */
    public PropertyManager(Properties props) {

        properties = props;

        log.info("New Props Manager created with default settings");


        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            System.out.println(entry.getKey().toString() + " = " + entry.getValue().toString());
        }
    }


    /**
     * Write properties to the file specified in the constructor.
     *
     * @throws IOException the {@link java.io.IOException} if their was a problem writing to the file.
     */
    public void writeToFile() throws IOException {
        this.writeToFile(file);
    }

    /**
     * Write properties to the specified string name of the file.
     * <p>If the file doesn't exist it will attempt to create it for you.
     *
     * @param file the {@link String } name of the file to store the properties under.
     * @throws IOException the {@link java.io.IOException} if their was a problem writing to the file.
     */
    public void writeToFile(String file) throws IOException {

        try {
            writer = new FileWriter(checkFile(file));
            properties.store(writer, "Plugin Messages");
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage(), e.getCause());
            throw e;
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }

    /**
     * Write properties to the specified file.
     *
     * @param file the {@link java.io.File} to write the properties to.
     * @throws IOException the {@link java.io.IOException} if their was a problem writing to the file.
     */
    public void writeToFile(File file) throws IOException {

        boolean b;
        b = file.exists();
        if (!b) {

            try {
                b = file.createNewFile();
                if (!b)
                    throw new RuntimeException("Can not create file at " + file.getPath());
                writer = new FileWriter(file);
                properties.store(writer, "Plugin Messages");
            } catch (IOException e) {
                log.log(Level.SEVERE, e.getMessage(), e.getCause());
                throw e;

            } finally {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            }
        }

    }


    /**
     * Read properties to the specified string name of the file.
     *
     * @throws IOException the {@link java.io.IOException} if their was a problem reading from the file.
     */
    public void readFromFile() throws IOException {
        try {
            this.readFromFile(file);
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage(), e.getCause());
            throw e;
        }
    }


    /**
     * Read properties to the specified string name of the file.
     *
     * @param file the {@link String } name of the file to read the properties from.
     * @throws IOException the {@link java.io.IOException} if the file was not found or there were problems reading from it.
     */
    public void readFromFile(String file) throws IOException {

        try {
            reader = new FileReader(checkFile(file));
            properties.load(reader);
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage(), e.getCause());
            throw e;
        } finally {
            if (reader != null)
                reader.close();
        }

    }


    /**
     * Check file the existents of a file and creates it if it is not found;
     *
     * @param file the file
     * @return the file
     * @throws IOException the iO exception
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private File checkFile(String file) throws IOException {
        log.info("The path tried is " + file);
        File tmp = new File(file);
        try {
            tmp.createNewFile();
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage(), e.getCause());
            throw e;
        }

        return tmp;
    }


    /**
     * Sets new properties.
     *
     * @param properties New value of properties.
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    /**
     * Gets the properties object properties.
     *
     * @return Properties the {@link java.util.Properties} that is used to store the properties in.
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * Returns if the Property Manager is in Developer mode.
     * <p>When in developer mode the files file be saved to an alternative location.
     * <p>The path, if using mave should be set to the src resources folder. Doing this allows
     * IDE like Intellij to interact with the files including auto complete of the message key.
     * <p>You will need to include the properties files to the root of the jar what compiling your plugin.
     *
     * @return boolean value of true and PropertyManager is in development. Default is false.
     */
    public boolean isDevMode() {
        return devMode;
    }

    /**
     * Toggle development on and off.
     *
     * @param devMode value of true to enable development mode. Default is false meaning its disabled.
     */
    public void setDevMode(boolean devMode) {
        this.devMode = devMode;
    }

    /**
     * Get the local file path for the properties files to be saved.
     *
     * @return devPath the string path for storing the files while in development mode.
     */
    public String getDevPath() {
        return devPath;
    }

    /**
     * Sets the path of the directory to write property files to when in development mode.
     * <p>The path should be the absolute path which includes a trailing path separator , Do not include the file name itself.
     *
     * @param devPath path of the directory to write property files.
     */
    public void setDevPath(String devPath) {
        this.devPath = devPath;
    }

    /**
     * Sets new extension.
     *
     * @param extension New value of extension.
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * Gets defaultBundle.
     *
     * @return Value of defaultBundle.
     */
    public String getDefaultBundle() {
        return defaultBundle;
    }

    /**
     * Gets extension.
     *
     * @return Value of extension.
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Sets new defaultBundle.
     *
     * @param defaultBundle New value of defaultBundle.
     */
    public void setDefaultBundle(String defaultBundle) {
        this.defaultBundle = defaultBundle;
    }

    /**
     * Sets new defaultCountyCode.
     *
     * @param defaultCountyCode New value of defaultCountyCode.
     */
    public void setDefaultCountyCode(String defaultCountyCode) {
        this.defaultCountyCode = defaultCountyCode;
    }

    /**
     * Gets defaultCountyCode.
     *
     * @return Value of defaultCountyCode.
     */
    public String getDefaultCountyCode() {
        return defaultCountyCode;
    }

    public String getDefaultBundelName() {

        return defaultBundle + extension;
    }

    public String getLanguageFile() {

        return defaultBundle + defaultCountyCode + extension;
    }
}
