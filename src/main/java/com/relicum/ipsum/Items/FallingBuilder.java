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

package com.relicum.ipsum.Items;

import org.bukkit.Location;
import org.bukkit.Material;

/**
 * FallingBuilder Interface,to be used t create different types of settings stores for FallingBlocks.
 *
 * @author Relicum
 * @version 0.0.1
 */
public interface FallingBuilder {


    /**
     * Sets falling block material.
     *
     * @param material the material
     * @return the instance of itself so methods can be chained
     */
    public FallingBuilder setFallingMaterial(Material material);

    /**
     * Sets falling block spawn location.
     *
     * @param fallingLocation the falling location
     * @return the instance of itself so methods can be chained
     */
    public FallingBuilder setFallingLocation(Location fallingLocation);

    /**
     * Sets falling byte.
     *
     * @param fallingByte the falling byte
     * @return the instance of itself so methods can be chained
     */
    public FallingBuilder setFallingByte(byte fallingByte);

    /**
     * Gets falling block spawn location.
     *
     * @return the falling block location
     */
    public Location getFallingLocation();

    /**
     * Gets falling block material.
     *
     * @return the falling block material
     */
    public Material getFallingMaterial();

    /**
     * Gets data bit.
     *
     * @return the data bit
     */
    public byte getDataBit();

}
