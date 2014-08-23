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

import java.io.*;
import java.util.Map;
import java.util.Properties;

/**
 * Name: PropertyManager.java Created: 09 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class PropertyManager {

    private Properties properties;
    private Writer writer;
    private Reader reader;
    private String file;


    public PropertyManager(String file) {
        this.file = file;


        properties = new Properties();

        try {
            readFromFile(file);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        /*
        properties.setProperty("join.message.standard.1", "Hello player hope you are well");
        properties.setProperty("join.message.standard.2", "Hiya player hope you are good");
        properties.setProperty("join.message.standard.3", "Hello arse hope you are well");
        properties.setProperty("join.message.standard.4", "Hello gamer hope you are well");
        properties.setProperty("join.message.standard.5", "Hello player hope you are die happy");
         */

        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            System.out.println(entry.getKey().toString() + " = " + entry.getValue().toString());
        }


    }

    public void writeToFile() {
        this.writeToFile(file);
    }

    public void writeToFile(String file) {

        try {
            writer = new FileWriter(file);
            properties.store(writer, "My Comments");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void readFromFile() throws Throwable {
        try {
            this.readFromFile(file);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }


    /**
     * Read from all the properties from file.
     *
     * @param file the full path to the properties file you want to read from
     */
    public void readFromFile(String file) throws Throwable {

        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new Throwable(e.getMessage(), e.getCause());
        }

        try {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
