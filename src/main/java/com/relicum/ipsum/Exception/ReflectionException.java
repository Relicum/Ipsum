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

/**
 * (c) 2014 dmulloy2
 */
package com.relicum.ipsum.Exception;

/**
 * An {@link Exception} that occurs when dealing with reflection.
 *
 * @author dmulloy2
 */

public class ReflectionException extends Exception {
    private static final long serialVersionUID = -355857662220280587L;

    /**
     * Constructs an empty ReflectionException.
     */
    public ReflectionException() {
        super();
    }

    /**
     * Constructs a ReflectionException with a given message.
     *
     * @param message Exception message
     */
    public ReflectionException(String message) {
        super(message);
    }

    /**
     * Constructs a ReflectionException with a given message and cause.
     *
     * @param message Exception message
     * @param cause   {@link Throwable} cause
     */
    public ReflectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public static ReflectionException fromThrowable(String message, Throwable ex) {
        if (ex instanceof ReflectionException)
            return (ReflectionException) ex;

        return new ReflectionException(message, ex);
    }
}
