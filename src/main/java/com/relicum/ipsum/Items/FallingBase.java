package com.relicum.ipsum.Items;

import org.apache.commons.lang.Validate;
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
