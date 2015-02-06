package com.relicum.ipsum.Menus;

/**
 * Name: MODE.java Created: 05 February 2015
 *
 * @author Relicum
 * @version 0.0.1
 */
public enum MODE {

    FORCE("force"), MOVE("move"), NORMAL("normal");

    private final String theMode;

    MODE(String theMode) {
        this.theMode = theMode;
    }

    public String get() {

        return this.theMode;
    }
}
