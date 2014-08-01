package com.relicum.ipsum.Items;

/**
 * Name: FallingCondion.java Created: 01 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public interface FallingCondion {

    public default boolean testFor(Object... list) {
        return false;
    }
}
