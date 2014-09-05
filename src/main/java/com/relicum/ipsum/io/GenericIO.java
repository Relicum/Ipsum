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

import java.nio.file.Path;

/**
 * GenericIO is a generic Interface containing the minimum class other IO class in this package must impliment.
 *
 * @author Relicum
 * @version 0.0.1
 */
public interface GenericIO {

    /**
     * Read the file at the specified {@link java.nio.file.Path} location
     *
     * @param path the location on disk of the file to read from
     * @return the contents of the file.
     */
    Class<?> read(Path path);


    /**
     * Save the file to the specified {@link java.nio.file.Path location}
     *
     * @param path the location on disk the file is saved to.
     * @return the result of the attempted save. True if the file was successfully saved, false if there was a problem.
     */
    boolean save(Path path);

}
