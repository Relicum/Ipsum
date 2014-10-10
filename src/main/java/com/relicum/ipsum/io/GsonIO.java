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


import net.minecraft.util.com.google.gson.Gson;
import net.minecraft.util.com.google.gson.stream.JsonReader;
import net.minecraft.util.com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * GsonIO Contain static methods for read and writing to Json files.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class GsonIO {

    //private static final GsonBuilder builder = new GsonBuilder().setPrettyPrinting().serializeNulls();
    private static final Gson gson = GsonLoader.gson;

    /**
     * Return a new {@link net.minecraft.util.com.google.gson.stream.JsonReader} at the specified {@link java.nio.file.Path} location
     *
     * @param path the location on disk of the file to create the reader from
     * @return the {@link net.minecraft.util.com.google.gson.stream.JsonReader}
     * @throws java.io.IOException if there was an exception while trying to create the reader
     */
    public static JsonReader reader(Path path) throws IOException {

        return new JsonReader(Files.newBufferedReader(path, Charset.defaultCharset()));
    }


    /**
     * Return a new {@link com.google.gson.stream.JsonWriter} at the specified {@link java.nio.file.Path} location
     *
     * @param path the location on disk of the file to write to
     * @return the {@link com.google.gson.stream.JsonWriter}
     * @throws IOException if there was an exception while trying to create the writer
     */
    public static JsonWriter writer(Path path) throws IOException {

        return new JsonWriter(Files.newBufferedWriter(path, Charset.defaultCharset()));
    }

    public static void writeToFile(Path path, Object src, Type typeOfSrc) throws IOException {

        JsonWriter writer = writer(path);
        writer.setIndent("    ");
        gson.toJson(src, typeOfSrc, writer);
        writer.flush();
        writer.close();
    }

    /**
     * Read from file.
     *
     * @param <T>       the type parameter
     * @param path      the path
     * @param typeOfSrc the type of src
     * @param clazz     the clazz
     * @return the t
     * @throws IOException the iO exception
     */
    public static <T> T readFromFile(Path path, Type typeOfSrc, T clazz) throws IOException {

        JsonReader reader = reader(path);
        clazz = gson.fromJson(reader, typeOfSrc);
        reader.close();
        return clazz;
    }

}
