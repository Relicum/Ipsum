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

package com.relicum.ipsum.Runnables;

import com.relicum.ipsum.io.GsonIO;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;

/**
 * JsonSaver saves a {@link com.relicum.ipsum.Configuration.PersistentPlayer} object to disk async.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class JsonSaver implements Runnable {

    private final Path path;
    private final Object src;
    private final Type type;

    /**
     * Instantiates a new Json saver.
     *
     * @param path      the {@link java.nio.file.Path} to the file.
     * @param src       the {@link com.relicum.ipsum.Configuration.PersistentPlayer} source object to save.
     * @param typeOfSrc the {@link java.lang.reflect.Type} of class.
     */
    public JsonSaver(Path path, Object src, Type typeOfSrc) {
        this.path = path;
        this.src = src;
        this.type = typeOfSrc;

    }

    @Override
    public void run() {
        try {
            GsonIO.writeToFile(path, src, type);
            System.out.println("File successfully written to disk after the delay");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
