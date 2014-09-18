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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * PointsGroup is used to store a collection of points, each point must have its own unique id.
 *
 * @param <K> the type used as the key of the storage map
 * @param <V> the type or point the map will hold must extend {@link com.relicum.ipsum.Location.Locateable}
 */
public class PointsGroup<K, V extends Locateable> {

    private Map<K, V> spawnGroup = new HashMap<>();

    /**
     * Instantiates object to hold a collection of points.
     *
     * @param point the point this must extend {@link com.relicum.ipsum.Location.Locateable}
     * @param key   the key can be any type
     */
    public PointsGroup(K key, V point) {
        this.putIfAbsent(key, point);

    }

    /**
     * Instantiates a new Points group.
     */
    public PointsGroup() {


    }

    /**
     * Contains key.
     *
     * @param key the key
     * @return the boolean
     */
    public boolean containsKey(K key) {

        return this.spawnGroup.containsKey(key);
    }

    /**
     * Contains value.
     *
     * @param point the point
     * @return the boolean
     */
    public boolean containsValue(V point) {

        return this.spawnGroup.containsValue(point);
    }


    /**
     * Put if absent.
     *
     * @param point the point
     * @param key   the key
     * @return the boolean
     */
    public boolean putIfAbsent(K key, V point) {
        try {
            this.spawnGroup.putIfAbsent(key, point);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * Remove point from group
     *
     * @param key the key
     * @return the point that has been removed.
     */
    public V remove(K key) {

        return this.spawnGroup.remove(key);
    }

    /**
     * Get point by key.
     *
     * @param key the unique id of the point to get
     * @return the point
     */
    public V get(K key) {

        return this.spawnGroup.get(key);
    }

    /**
     * Get stream of the Groups values
     *
     * @return the stream of points in the PointsGroup
     */
    public Stream<V> getStream() {

        return this.spawnGroup.values().stream();
    }

    /**
     * Get Entry set of the Group
     *
     * @return the Entry Set
     */
    public java.util.Set<Map.Entry<K, V>> entrySet() {

        return this.spawnGroup.entrySet();
    }

    /**
     * Clears all points from the PointsGroup
     */
    public void clear() {

        this.spawnGroup.clear();
    }

    /**
     * Get points list.
     *
     * @return the points as a list
     */
    public List<V> getPointsList() {

        return spawnGroup.values().stream().collect(Collectors.toList());
    }

    /**
     * Prints the Map to the console only here for debugging will be removed later.
     */
    public void View() {

        for (Map.Entry<K, V> entry : entrySet()) {

            System.out.println("Key is " + entry.getKey() + " spawn point is " + entry.getValue().toString());
        }


    }

    /**
     * Get point group.
     *
     * @return the map
     */
    public Map<K, V> getPointGroup() {

        return this.spawnGroup;
    }

    /**
     * Get the total number of point currently stored in the group
     *
     * @return the int the number of points.
     */
    public int size() {

        return this.spawnGroup.size();
    }

}
