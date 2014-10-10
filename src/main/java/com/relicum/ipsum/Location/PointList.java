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

import org.apache.commons.lang.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * PointList purpose is to group together by a list, objects that extend {@link com.relicum.ipsum.Location.Locateable} .
 * <p>This is mainly part of a GSON IO system I am working on, but it can be used for any other purpose.
 * This is currently not thread safe.
 *
 * @author Relicum
 */
public class PointList<T extends Locateable> {

    private List<T> spawnList = new ArrayList<>();

    /**
     * Instantiates a new Point list.
     */
    public PointList() {

    }


    /**
     * Instantiates a new Point list.
     *
     * @param point the point that extends {@link com.relicum.ipsum.Location.Locateable} to add to the list.
     */
    public PointList(T point) {
        Validate.notNull(point);
        addPoint(point);

    }

    /**
     * Instantiates a new PointList
     *
     * @param point the list of of any object the extends {@link com.relicum.ipsum.Location.Locateable} to be added to the group
     */
    public PointList(List<T> point) {
        Validate.notNull(point);
        if (spawnList.addAll(point))
            System.out.println("All points added");
        else
            System.out.println("Failed to add all points");
    }

    /**
     * Add a element that extends {@link com.relicum.ipsum.Location.Locateable} to the list.
     *
     * @param point to add that extends {@link com.relicum.ipsum.Location.Locateable}
     */
    public void addPoint(T point) {
        Validate.notNull(point);
        this.spawnList.add(point);
    }

    /**
     * Add a element that extends {@link com.relicum.ipsum.Location.Locateable} to the list.
     *
     * @param index the index
     * @param point the point
     */
    public void addPoint(int index, T point) {
        Validate.notNull(index);
        Validate.notNull(point);
        this.spawnList.add(index, point);
    }

    /**
     * Remove a element from the list.
     *
     * @param point the point that extends {@link com.relicum.ipsum.Location.Locateable} to remove.
     */
    public void removePoint(T point) {
        Validate.notNull(point);
        this.spawnList.remove(point);
    }

    /**
     * Remove all elements from the list.
     */
    public void removeAll() {

        spawnList.clear();

        System.out.println("All points have been removed");

    }

    /**
     * Returns <tt>true</tt> if this list contains the specified element.
     *
     * @param o the element to search for
     * @return the boolean true if the element is found, false it is not found.
     */
    public boolean contains(T o) {

        return this.spawnList.contains(o);
    }

    /**
     * Retrieve a element from the list at the specified index.
     *
     * @param index of the element that must extend {@link com.relicum.ipsum.Location.Locateable}
     * @return the element at specified index.
     */
    public T getPoint(int index) {
        Validate.notNull(index);
        return this.spawnList.get(index);
    }

    /**
     * Returns the list of points
     *
     * @return the list of of points that must extend {@link com.relicum.ipsum.Location.Locateable}
     */
    public List<T> getSpawnList() {
        return this.spawnList;
    }


    /**
     * Returns a sequential Stream with this collection as its source.
     *
     * @return the stream
     */
    public Stream<T> stream() {

        return this.spawnList.stream();
    }

    /**
     * The number of elements in the list.
     *
     * @return the total number of elements in the list.
     */
    public int size() {

        return this.spawnList.size();
    }
}
