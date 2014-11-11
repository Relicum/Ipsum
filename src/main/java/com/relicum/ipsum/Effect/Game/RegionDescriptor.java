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

package com.relicum.ipsum.Effect.Game;

/**
 * RegionDescriptor holds all the information describing the arena and features it implements.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class RegionDescriptor {

    private Boolean parent = false;
    private Boolean child = false;
    private Boolean standAlone = false;
    private Boolean joinPermission = false;
    private Boolean joinByPass = false;
    private Boolean spectatorsAllowed = false;
    private Boolean joinCommand = false;
    private Boolean joinSign = false;
    private Boolean clones = false;
    private Boolean manager = false;


    public RegionDescriptor(Boolean parent, Boolean child, Boolean standAlone, Boolean joinPermission, Boolean joinByPass, Boolean spectatorsAllowed, Boolean joinCommand, Boolean joinSign, Boolean clones, Boolean manager) {
        this.parent = parent;
        this.child = child;
        this.standAlone = standAlone;
        this.joinPermission = joinPermission;
        this.joinByPass = joinByPass;
        this.spectatorsAllowed = spectatorsAllowed;
        this.joinCommand = joinCommand;
        this.joinSign = joinSign;
        this.clones = clones;
        this.manager = manager;
    }

    /**
     * Has Arena a parent arena
     *
     * @return the true if it has a parent arena
     */
    public boolean hasParent() {
        return parent;
    }

    /**
     * Has this arena a child arena
     *
     * @return true if it has a child arena
     */
    public boolean hasChild() {
        return child;
    }


    /**
     * Is this arena on a standalone server
     *
     * @return true if the arena uses the whole server
     */
    public boolean isStandalone() {
        return standAlone;
    }

    /**
     * Has the arena a join permission.
     *
     * @return true if a permmission is required to join.
     */
    public boolean hasJoinPermission() {
        return joinPermission;
    }

    /**
     * Is there a join if full by pass.
     *
     * @return true if certain players can join a full server.
     */
    public boolean hasJoinByPass() {
        return joinByPass;
    }

    /**
     * Are spectators allowed
     *
     * @return true if spectators are allowed.
     */
    public boolean allowsSpectators() {
        return spectatorsAllowed;
    }

    /**
     * Has join command.
     *
     * @return true if the player can join the arena by command.
     */
    public boolean hasJoinCommand() {
        return joinCommand;
    }

    /**
     * Has join sign.
     *
     * @return true if join signs are used.
     */
    public boolean hasJoinSign() {
        return joinSign;
    }

    /**
     * Has clones.
     *
     * @return true if there are identical instances of this arena.
     */
    public boolean hasClones() {
        return clones;
    }

    /**
     * Has the arena a manager that controls the arena itself.
     *
     * @return true if the arena has a manager.
     */
    public boolean hasManager() {
        return manager;
    }
}
