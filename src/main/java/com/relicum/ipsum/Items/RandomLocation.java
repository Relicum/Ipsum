package com.relicum.ipsum.Items;

import org.bukkit.util.Vector;

/**
 * Name: RandomLocation.java Created: 01 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public class RandomLocation {

    //todo random needs to be defined by a set of rules
    //to start only have to inside a radius and not landing in water
    //find a block location 500 radius from spawn thats not water or lava
    ;
    Vector min;
    Vector max;

    public RandomLocation(int X, int Y, int Z, int X1, int Z1) {
        this.min = new Vector(X, Y, Z);
        this.max = new Vector(X1, Y, Z1);


    }


}
