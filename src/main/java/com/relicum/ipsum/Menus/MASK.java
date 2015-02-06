package com.relicum.ipsum.Menus;

/**
 * Name: MASK.java Created: 05 February 2015
 *
 * @author Relicum
 * @version 0.0.1
 */
public enum MASK {

    FILTERED("filtered"), MASKED("masked"), REPLACE("replace");

    private final String theMask;

    private MASK(String theMask) {

        this.theMask = theMask;
    }

    public String get() {

        return this.theMask;
    }

}
