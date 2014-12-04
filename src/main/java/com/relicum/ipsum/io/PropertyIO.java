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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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
     * @param path the {@link java.nio.file.Path } path of the file to read the properties from.
     * @return the {@link java.util.Properties} instance.
     * @throws IOException the {@link java.io.IOException} if the file was not found or there were problems reading from it.
     */
    default Properties readFromFile(Path path) throws IOException {

        Validate.notNull(path);

        return readFromFile(path.toFile());
    }

    /**
     * Return a {@link java.util.Properties} read from the specified string file path.
     *
     * @param file the {@link java.nio.file.Path } name of the file to read the properties from.
     * @return the {@link java.util.Properties} instance.
     * @throws IOException the {@link java.io.IOException} if the file was not found or there were problems reading from it.
     */
    default Properties readFromFile(File file) throws IOException {

        Validate.notNull(file);

        if (Files.exists(file.toPath()))
            throw new FileNotFoundException("File at " + file.getPath() + " was not found");

        Properties properties = new Properties();
        try {

            properties.load(Files.newBufferedReader(file.toPath(), Charset.defaultCharset()));
        } catch (IOException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e.getCause());
            throw e;
        }

        return properties;
    }


    /**
     * Write the properties object to the specified string destination.
     *
     * @param properties the properties
     * @param path       the path
     * @param message    the message
     * @throws IOException the iO exception
     */
    default void writeToFile(Properties properties, String path, String message) throws IOException {
        Validate.notNull(properties);
        Validate.notNull(path);
        Validate.notNull(message);
        System.out.println(path);
        writeToFile(properties, Paths.get(path), message);
    }

    /**
     * Write the properties object to the specified string file path.
     *
     * @param properties an instance of a {@link java.util.Properties} object.
     * @param path       the  {@link java.nio.file.Path} that the properties will be written to.
     * @param message    the message that is included in the header of properties files.
     * @throws IOException an {@link java.io.IOException} if their was a problem writing to the file.
     */
    default void writeToFile(Properties properties, Path path, String message) throws IOException {
        Validate.notNull(properties);
        Validate.notNull(path);
        Validate.notNull(message);
        System.out.println(path);

        Files.deleteIfExists(path);

        try {

            properties.store(Files.newBufferedWriter(path, Charset.defaultCharset()), message);
        } catch (IOException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e.getCause());
            throw e;
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

        writeToFile(properties, file.toPath(), message);

    }


    /**
     * Create a new directory including any sub directories that is required.
     *
     * @param file the {@link java.nio.file.Path} of the file or directory
     * @throws RuntimeException if an error occured when trying to create the directory
     */
    default void createDirectories(Path file) throws RuntimeException {
        Validate.notNull(file);

        Path parent = file.getParent();

        try {
            Files.createDirectories(parent);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e.getCause());
            throw e;
        }

        return tmp;
    }

    /**
     * Gets all files in jar.
     *
     * @param clazz the clazz
     * @return the list of files and paths in jar
     */
    default List<String> getAllFilesInJar(Class<?> clazz) {

        List<String> list = new ArrayList<>();
        CodeSource src = clazz.getProtectionDomain().getCodeSource();
        if (src != null) {
            try {
                URL jar = src.getLocation();
                ZipInputStream zip = new ZipInputStream(jar.openStream());
                while (true) {
                    ZipEntry e = zip.getNextEntry();
                    if (e == null)
                        break;
                    String name = e.getName();
                    if (name.contains(".properties")) {
                        list.add(name);
                        System.out.println(name);
                    }

                }
                zip.close();
                jar = null;
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        return list;
    }

}
