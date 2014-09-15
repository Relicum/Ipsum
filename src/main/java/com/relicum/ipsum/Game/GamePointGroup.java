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

package com.relicum.ipsum.Game;

import org.apache.commons.lang.Validate;

import java.util.ArrayList;
import java.util.List;

/**
 * An object that sole purpose is to group together {@link com.relicum.ipsum.Game.GamePoint} .
 * <p>This is mainly part of a GSON IO system I am working on, but it can be used for any other purpose.
 * This is currently not thread safe.
 */
public class GamePointGroup {

    private List<GamePoint> points = new ArrayList<>();

    /**
     * Instantiates a new GamePointGroup
     */
    public GamePointGroup() {

    }

    /**
     * Instantiates a new GamePointGroup
     *
     * @param point the list of {@link com.relicum.ipsum.Game.GamePoint} to be added to the group
     */
    public GamePointGroup(List<GamePoint> point) {
        Validate.notNull(point);
        if (points.addAll(point))
            System.out.println("All points added");
        else
            System.out.println("Failed to add all points");
    }

    /**
     * Add a {@link com.relicum.ipsum.Game.GamePoint} to the group.
     *
     * @param gamePoint the test point
     */
    public void addPoint(GamePoint gamePoint) {
        Validate.notNull(gamePoint);
        this.points.add(gamePoint);
    }

    /**
     * Remove a {@link com.relicum.ipsum.Game.GamePoint} from the group.
     *
     * @param gamePoint the test point
     */
    public void removePoint(GamePoint gamePoint) {
        Validate.notNull(gamePoint);
        this.points.remove(gamePoint);
    }

    /**
     * Remove all {@link com.relicum.ipsum.Game.GamePoint} from the group.
     */
    public void removeAll() {

        points.clear();

        System.out.println("All points have been removed");

    }

    /**
     * Retrieve a {@link com.relicum.ipsum.Game.GamePoint} from the group at the specified index.
     *
     * @param index of the {@link com.relicum.ipsum.Game.GamePoint}
     * @return the {@link com.relicum.ipsum.Game.GamePoint} at that index.
     */
    public GamePoint getGamePoint(int index) {
        Validate.notNull(index);
        return this.points.get(index);
    }

    /**
     * Retrieve all {@link com.relicum.ipsum.Game.GamePoint} in the group as a List.
     *
     * @return the list of {@link com.relicum.ipsum.Game.GamePoint}
     */
    public List<GamePoint> getPoints() {
        return this.points;
    }
}
