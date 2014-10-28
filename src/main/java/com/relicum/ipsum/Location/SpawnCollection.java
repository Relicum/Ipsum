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

import lombok.ToString;
import lombok.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SpawnCollection stores a Map of {@link com.relicum.ipsum.Location.PointsGroup}
 *
 * @author Relicum
 * @version 0.0.1
 */
@SuppressWarnings("ALL")
public class SpawnCollection<K, R, T extends Locateable> {

    private HashMap<K, PointsGroup<R, T>> spawnCollection = new HashMap<>();
    private K key;
    private Map<R, T> map = new HashMap<>();

    /**
     * Instantiates a new Spawn collection.
     *
     * @param spawnCollection the spawn collection
     */
    public SpawnCollection(HashMap<K, PointsGroup<R, T>> spawnCollection) {
        this.spawnCollection = spawnCollection;
    }

    /**
     * Instantiates a new Points collection.
     */
    public SpawnCollection() {

    }


    /**
     * Add new PointsGroup {@link com.relicum.ipsum.Location.PointsGroup} to the map.
     *
     * @param key        the key
     * @param spawnGroup the {@link com.relicum.ipsum.Location.PointsGroup}
     */
    public void addPointsGroup(K key, PointsGroup<R, T> spawnGroup) {

        spawnCollection.putIfAbsent(key, spawnGroup);
    }


    /**
     * Get PointsGroup {@link com.relicum.ipsum.Location.PointsGroup} specified by key
     *
     * @param key the key
     * @return the group {@link com.relicum.ipsum.Location.PointsGroup}
     */
    public PointsGroup<R, T> getGroup(K key) {

        return spawnCollection.get(key);
    }


    /**
     * Gets a List of {@link com.relicum.ipsum.Location.SpawnPoint} .
     *
     * @param key the key
     * @return the list of {@link com.relicum.ipsum.Location.SpawnPoint} from the {@link com.relicum.ipsum.Location.PointsGroup} specified by the key.
     */
    public List<T> getGroupAsList(K key) {

        return spawnCollection.get(key).getPointsList();

    }

    @ToString(includeFieldNames = true)
    @Value(staticConstructor = "of")
    public static class Example<K, R, T extends Locateable> {

        private HashMap<K, PointsGroup<R, T>> spawnCollection = new HashMap<>();

    }


}
