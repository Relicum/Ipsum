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

package com.relicum.ipsum.Location;

import com.relicum.ipsum.Annotations.CollectionOf;

import java.util.ArrayList;
import java.util.Collection;

/**
 * PointCollection wrapped Collection of {@link com.relicum.ipsum.Location.SpawnPoint}
 *
 * @author Relicum
 * @version 0.0.1
 */
public class PointCollection {


    @CollectionOf(value = SpawnPoint.class, type = ArrayList.class)
    Collection<SpawnPoint> pointCollection;

    /**
     * Instantiates a new empty PointCollection.
     */
    @SuppressWarnings("unchecked")
    public PointCollection() {
        pointCollection = new ArrayList<>();

    }

    /**
     * Get wrapped Collection of {@link com.relicum.ipsum.Location.SpawnPoint}
     *
     * @return the wrapped collection
     */
    public Collection<SpawnPoint> getCollection() {
        return pointCollection;
    }
}
