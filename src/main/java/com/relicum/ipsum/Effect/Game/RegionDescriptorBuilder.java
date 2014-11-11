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
 * Builder class for {@link com.relicum.ipsum.Effect.Game.RegionDescriptor} .
 * <p>All settings are optional, call the build method at the end to get a new instance of {@link com.relicum.ipsum.Effect.Game.RegionDescriptor}
 *
 * @author Relicum
 */
public class RegionDescriptorBuilder {

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

    private RegionDescriptorBuilder() {
    }

    /**
     * Get builder for {@link com.relicum.ipsum.Effect.Game.RegionDescriptor}
     *
     * @return instance of itself for chaining.
     */
    public static RegionDescriptorBuilder Builder() {
        return new RegionDescriptorBuilder();
    }

    /**
     * With parent.
     *
     * @param parent the parent
     * @return instance of itself for chaining.
     */
    public RegionDescriptorBuilder withParent(Boolean parent) {
        this.parent = parent;
        return this;
    }

    /**
     * With child.
     *
     * @param child the child
     * @return instance of itself for chaining.
     */
    public RegionDescriptorBuilder withChild(Boolean child) {
        this.child = child;
        return this;
    }

    /**
     * Sets stand alone.
     *
     * @param standAlone the stand alone
     * @return instance of itself for chaining.
     */
    public RegionDescriptorBuilder setStandAlone(Boolean standAlone) {
        this.standAlone = standAlone;
        return this;
    }

    /**
     * With join permission.
     *
     * @param joinPermission the join permission
     * @return instance of itself for chaining.
     */
    public RegionDescriptorBuilder withJoinPermission(Boolean joinPermission) {
        this.joinPermission = joinPermission;
        return this;
    }

    /**
     * With join by pass.
     *
     * @param joinByPass the join by pass
     * @return instance of itself for chaining.
     */
    public RegionDescriptorBuilder withJoinByPass(Boolean joinByPass) {
        this.joinByPass = joinByPass;
        return this;
    }

    /**
     * With spectators.
     *
     * @param spectatorsAllowed the spectators allowed
     * @return instance of itself for chaining.
     */
    public RegionDescriptorBuilder withSpectators(Boolean spectatorsAllowed) {
        this.spectatorsAllowed = spectatorsAllowed;
        return this;
    }

    /**
     * With join command.
     *
     * @param joinCommand the join command
     * @return instance of itself for chaining.
     */
    public RegionDescriptorBuilder withJoinCommand(Boolean joinCommand) {
        this.joinCommand = joinCommand;
        return this;
    }

    /**
     * With join sign.
     *
     * @param joinSign the join sign
     * @return instance of itself for chaining.
     */
    public RegionDescriptorBuilder withJoinSign(Boolean joinSign) {
        this.joinSign = joinSign;
        return this;
    }

    /**
     * Has clones.
     *
     * @param clones the clones
     * @return instance of itself for chaining.
     */
    public RegionDescriptorBuilder hasClones(Boolean clones) {
        this.clones = clones;
        return this;
    }

    /**
     * Has manager.
     *
     * @param manager the manager
     * @return instance of itself for chaining.
     */
    public RegionDescriptorBuilder hasManager(Boolean manager) {
        this.manager = manager;
        return this;
    }

    /**
     * Build and return new {@link com.relicum.ipsum.Effect.Game.RegionDescriptor}
     *
     * @return new instance of {@link com.relicum.ipsum.Effect.Game.RegionDescriptor}
     */
    public RegionDescriptor Build() {
        return new RegionDescriptor(parent, child, standAlone, joinPermission, joinByPass, spectatorsAllowed, joinCommand, joinSign, clones, manager);
    }
}
