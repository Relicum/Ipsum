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

package com.relicum.ipsum.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Name: FileUtils.java Created: 05 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class FileUtils {

    /**
     * Create directory a new directory at the specified path
     *
     * @param path the path
     * @param name the name
     */
    public static void createDirectory(String path, String name) {
        boolean f = new File(path + "/" + name).exists();
        if (!f) {
            boolean fi = new File(path + "/" + name).mkdirs();

            if (!fi)
                Logger.getLogger("MineCraft").severe("Error: Failed to directory at " + path + "/" + name);
        }
    }

    /**
     * Clear and delete a directory
     *
     * @param file the directory path as a File
     */
    public static void clear(File file) {
        if (!file.exists()) return;
        if (file.isFile()) {
            file.delete();
        } else {
            for (File f : file.listFiles())
                clear(f);
            file.delete();
        }
    }

    /**
     * Gets all files in directory that match the extensions.
     * <p>The Extensions are passed pseudo glob eg {yml} or {yml,txt} for multiples
     *
     * @param dir the dir the directory to search as a string
     * @param ext the pseudo glob containing the extensions to match eg {yml,txt}
     * @return the all names files in directory that match the extensions in the Array of strings;
     * @throws java.io.IOException the iO exception
     */
    public static List<String> getAllFilesInDirectory(String dir, final String ext) throws IOException {


        List<String> result = new ArrayList<>();
        String[] tmp;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(stringToPath(dir), "*." + ext)) {
            for (Path entry : stream) {

                tmp = entry.toFile().getName().split("\\.");

                result.add(tmp[0]);

            }
        } catch (DirectoryIteratorException ex) {

            throw ex.getCause();
        }
        return result;

    }

    /**
     * String to Path.
     *
     * @param stringPath the string instance that ius to be converted to a Path object
     * @return the path The converted string as a Path
     */
    public static Path stringToPath(String stringPath) {

        return Paths.get(stringPath);
    }
}
