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

import net.minecraft.util.com.google.common.collect.ArrayListMultimap;
import net.minecraft.util.com.google.common.collect.ListMultimap;
import net.minecraft.util.com.google.common.collect.Multimap;
import net.minecraft.util.com.google.common.collect.Multimaps;

import java.util.*;

/**
 * LocationListMultimap Implementation of Multimap that uses an ArrayList to store the values for a given key. A HashMap associates each key with an ArrayList of values.
 * <p>When iterating through the collections supplied by this class, the ordering of values for a given key agrees with the order in which the values were added.
 * <p>
 * <p>This multimap allows duplicate key-value pairs. After adding a new key-value pair equal to an existing key-value pair, the ArrayListMultimap will contain entries for both the new value and the old value.
 * <p>
 * <p>Keys and values may be null. All optional multimap methods are supported, and all returned views are modifiable.
 * <p>
 * <p>See the Guava User Guide article on <a href="http://code.google.com/p/guava-libraries/wiki/NewCollectionTypesExplained#Multimap">Multimap</a>
 * <p>
 * <p>This class is specifically designed to allow the storing of a collection of {@link org.bukkit.Location} or {@link com.relicum.ipsum.Location.SpawnPoint} by a single key.
 * This becomes especially useful if when creating mini games that have many different types of {@link com.relicum.ipsum.Location.SpawnPoint} or {@link org.bukkit.Location} but are grouped
 * or stored under different human readable names eg, start spawn, lobby spawn, region, game spawns that have multiple spawns. You can now store all of these in a single object and free to fame each
 * group or object as you like.
 * <p>
 * The internal backing object is Synchronised but you will need to manually synchronise if you wish to iterate over one of the collections.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class LocationListMultimap {

    private ListMultimap<String, SpawnPoint> map = Multimaps.synchronizedListMultimap(ArrayListMultimap.create());

    /**
     * Instantiates a new LocationListMultimap.
     */
    public LocationListMultimap() {

    }


    /**
     * Instantiates a new LocationListMultimap.
     *
     * @param multimap see {@link net.minecraft.util.com.google.common.collect.Multimap}
     */
    public LocationListMultimap(Multimap<? extends String, ? extends SpawnPoint> multimap) {

        this.map.putAll(multimap);
    }

    /**
     * Instantiates a new LocationListMultimap.
     *
     * @param key   the key
     * @param point the point
     */
    public LocationListMultimap(String key, SpawnPoint point) {

        this.map.put(key, point);
    }

    /**
     * Instantiates a new LocationListMultimap..
     *
     * @param key    the key
     * @param points the points
     */
    public LocationListMultimap(String key, Iterable<? extends SpawnPoint> points) {

        this.map.putAll(key, points);
    }

    /**
     * Add a new {@link com.relicum.ipsum.Location.SpawnPoint} using an existing or new key.
     *
     * @param key   the key
     * @param point the {@link com.relicum.ipsum.Location.SpawnPoint} to add.
     * @return true on success
     */
    public boolean put(String key, SpawnPoint point) {
        return this.map.put(key, point);
    }

    /**
     * Add a new List of {@link com.relicum.ipsum.Location.SpawnPoint} using an existing or new key.
     *
     * @param key    the key
     * @param points the List of {@link com.relicum.ipsum.Location.SpawnPoint} to add.
     * @return true on success
     */
    public boolean putAll(String key, Iterable<? extends SpawnPoint> points) {

        return map.putAll(key, points);
    }

    /**
     * Get a {@link java.util.List} of {@link com.relicum.ipsum.Location.SpawnPoint} using an existing key.
     * <p>If the key is not found an empty list is returned.
     *
     * @param key the key of the List of {@link com.relicum.ipsum.Location.SpawnPoint} to return.
     * @return {@link java.util.List} of {@link com.relicum.ipsum.Location.SpawnPoint} or an empty list if key was not found.
     */
    public List<SpawnPoint> get(String key) {
        if (map.containsKey(key))
            return map.get(key);
        return Collections.emptyList();
    }

    /**
     * Contains key.
     *
     * @param key the key
     * @return true if key was found
     */
    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    /**
     * Clear all keys and values.
     */
    public void clear() {
        map.clear();
    }

    /**
     * Get iterator for a specified List of {@link com.relicum.ipsum.Location.SpawnPoint}
     *
     * @param key the key
     * @return the iterator
     */
    public Iterator<SpawnPoint> getIterator(String key) {

        return map.get(key).iterator();
    }


    /**
     * Entries collection.
     *
     * @return the collection
     */
    public Collection<Map.Entry<String, SpawnPoint>> entries() {

        return map.entries();
    }

    /**
     * View of the object as a Map
     *
     * @return the map
     */
    public Map<String, Collection<SpawnPoint>> asMap() {
        return map.asMap();
    }


}
