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

package com.relicum.ipsum.io;

import org.apache.commons.lang.Validate;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PropertyIO is a simple iterface to read and write from and to {@link java.util.Properties} files.
 * <p>Its main use is for classes that need to read or write to properties files, to allow
 * a quick and light weight way of doing so without duplicating code.
 * <p>The method used to write to files is an instance of {@link java.io.FileWriter}
 * <p>The method used to read from files is an instance of {@link java.io.FileReader}
 * <p>When implementing this you are not required to override any of the methods.
 * <p>Neither reading of writing to the files is thread safe.
 *
 * @author Relicum
 * @version 0.0.1
 */
public interface PropertyIO {


    /**
     * Return a {@link java.util.Properties} read from the specified string file path.
     *
     * @param path the {@link String } name of the file to read the properties from.
     * @throws IOException the {@link java.io.IOException} if the file was not found or there were problems reading from it.
     */
    default Properties readFromFile(String path) throws IOException {

        Validate.notNull(path);

        File file = new File(path);

        if (!file.exists())
            throw new FileNotFoundException("File at " + path + " was not found");

        Reader reader = null;
        Properties properties = new Properties();

        try {
            reader = new FileReader(file);
            properties.load(reader);
        } catch (IOException e) {
            Logger.getLogger("minecraft").log(Level.SEVERE, e.getMessage(), e.getCause());
            throw e;
        } finally {
            if (reader != null)
                reader.close();
        }

        return properties;
    }

    /**
     * Return a {@link java.util.Properties} read from the specified string file path.
     *
     * @param file the {@link String } name of the file to read the properties from.
     * @throws IOException the {@link java.io.IOException} if the file was not found or there were problems reading from it.
     */
    default Properties readFromFile(File file) throws IOException {

        Validate.notNull(file);

        if (!file.exists())
            throw new FileNotFoundException("File at " + file.getPath() + " was not found");

        Reader reader = null;
        Properties properties = new Properties();

        try {
            reader = new FileReader(file);
            properties.load(reader);
        } catch (IOException e) {
            Logger.getLogger("minecraft").log(Level.SEVERE, e.getMessage(), e.getCause());
            throw e;
        } finally {
            if (reader != null)
                reader.close();
        }
        return properties;
    }


    /**
     * Write the properties object to the specified string file path.
     *
     * @param properties an instance of a {@link java.util.Properties} object.
     * @param path       the string file path that the properties will be written to.
     * @param message    the message that is included in the header of properties files.
     * @throws IOException an {@link java.io.IOException} if their was a problem writing to the file.
     */
    default void writeToFile(Properties properties, String path, String message) throws IOException {
        Validate.notNull(properties);
        Validate.notNull(path);
        Validate.notNull(message);
        System.out.println(path);
        Writer writer = null;

        try {
            writer = new FileWriter(checkFile(path));
            properties.store(writer, message);
        } catch (IOException e) {
            Logger.getLogger("minecraft").log(Level.SEVERE, e.getMessage(), e.getCause());
            throw e;
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }


    }

    /**
     * Write the properties object to the specified file.
     *
     * @param properties an instance of a {@link java.util.Properties} object.
     * @param file       the file that the properties will be written to.
     * @param message    the message that is included in the header of properties files.
     * @throws IOException an {@link java.io.IOException} if their was a problem writing to the file.
     */
    default void writeToFile(Properties properties, File file, String message) throws IOException {
        Validate.notNull(properties);
        Validate.notNull(file);
        Validate.notNull(message);

        Writer writer = null;

        boolean b;
        b = file.exists();
        if (!b) {

            try {
                b = file.createNewFile();
                if (!b)
                    throw new RuntimeException("Can not create file at " + file.getPath());
                writer = new FileWriter(file);
                properties.store(writer, message);
            } catch (IOException e) {
                Logger.getLogger("minecraft").log(Level.SEVERE, e.getMessage(), e.getCause());
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
     * Check if file exists at the give string path, if it does not, creates a new file and returns that.
     *
     * @param file the file
     * @return the file
     * @throws java.io.IOException the iO exception
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    default File checkFile(String file) throws IOException {
        Logger.getLogger("minecraft").info("The path tried is " + file);
        File tmp = new File(file);
        try {
            tmp.createNewFile();
        } catch (IOException e) {
            Logger.getLogger("minecraft").log(Level.SEVERE, e.getMessage(), e.getCause());
            throw e;
        }

        return tmp;
    }

}
