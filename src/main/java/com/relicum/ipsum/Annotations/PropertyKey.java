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

package com.relicum.ipsum.Annotations;


import java.lang.annotation.*;

/**
 * Specifies that a method parameter accepts arguments which must be valid property
 * keys in a specific resource bundle. When a string literal which is not a property
 * key in the specified bundle is passed as a parameter, IntelliJ IDEA highlights
 * it as an error. The annotation is also used to provide completion in string literals
 * passed as parameters.
 *
 *
 * @author max
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.FIELD})
public @interface PropertyKey {

    /**
     * The full-qualified name of the resource bundle in which the property keys must
     * be present. Consists of a full-qualified name of the package corresponding to the
     * directory where the resource bundle is located and the base name of the resource
     * bundle (with no locale specifier or extension), separated with a dot.
     */
    String resourceBundle();
}
