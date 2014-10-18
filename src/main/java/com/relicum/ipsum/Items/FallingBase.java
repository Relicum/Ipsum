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

import net.minecraft.util.org.apache.commons.lang3.Validate;
import org.bukkit.Location;
import org.bukkit.Material;

/**
 * Name: FallingBase.java Created: 01 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public abstract class FallingBase implements FallingBuilder {

    private Material fallingMaterial;

    private Location fallingLocation;

    private byte byteBit;


    public FallingBase() {

    }


    public abstract FallingBase build();

    /**
     * Sets falling block material.
     *
     * @param material the material
     * @return the instance of itself so methods can be chained
     */
    @Override
    public FallingBuilder setFallingMaterial(Material material) {
        Validate.notNull(material, "The material for the falling block can not be null");
        this.fallingMaterial = material;
        return this;
    }

    /**
     * Sets falling block spawn location.
     *
     * @param fallingLocation the falling location
     * @return the instance of itself so methods can be chained
     */
    @Override
    public FallingBuilder setFallingLocation(Location fallingLocation) {
        Validate.notNull(fallingLocation, "Location for falling block can not be null");
        this.fallingLocation = fallingLocation;
        return this;
    }

    /**
     * Sets falling byte.
     *
     * @param fallingByte the falling byte
     * @return the instance of itself so methods can be chained
     */
    @Override
    public FallingBuilder setFallingByte(byte fallingByte) {
        Validate.notNull(fallingByte, "Byte bit can not be null");

        this.byteBit = fallingByte;
        return this;
    }

    /**
     * Gets falling block spawn location.
     *
     * @return the falling block location
     */
    @Override
    public Location getFallingLocation() {
        return this.fallingLocation;
    }

    /**
     * Gets falling block material.
     *
     * @return the falling block material
     */
    @Override
    public Material getFallingMaterial() {
        return this.fallingMaterial;
    }

    /**
     * Gets data bit.
     *
     * @return the data bit
     */
    @Override
    public byte getDataBit() {
        return this.byteBit;
    }

}
