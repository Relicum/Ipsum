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
 * Specifies that an element of the program is not a user-visible string which needs to be localized,
 * or does not contain such strings. The annotation is intended to be used by localization tools for
 * detecting strings which should not be reported as requiring localization.
 * <ul>
 * <li>If a method parameter is annotated with <code>NonNls</code>, the strings passed
 * as values of this parameter are not reported as requiring localization.
 * Also, if the parameter of a property setter method is annotated with <code>NonNls</code>, values
 * of that property in UI Designer forms are never highlighted as hard-coded strings.</li>
 * <li>If a field is annotated with <code>NonNls</code>, all string literals found in the
 * initializer of the field are not reported as requiring localization.</li>
 * <li>If a method is called on a field, parameter or local variable annotated with <code>NonNls</code>,
 * string literals passed as parameters to the method are not reported as requiring localization.
 * <li>If a field, parameter or local variable annotated with <code>NonNls</code> is passed as a
 * parameter to the <code>equals()</code> method invoked on a string literal, the literal is not
 * reported as requiring localization.</li>
 * <li>If a field, parameter or local variable annotated with <code>NonNls</code> is found at
 * the left side of an assignment expression, all string literals in the right side
 * of the expression are not reported as requiring localization.</li>
 * <li>If a method is annotated with <code>NonNls</code>, string literals returned from the method
 * are not reported as requiring localization.</li>
 * <li>If a class is annotated with <code>NonNls</code>, all string literals in
 * the class and all its subclasses are not reported as requiring localization.</li>
 * <li>If a package is annotated with <code>NonNls</code>, all string literals in
 * the package and all its subpackages are not reported as requiring localization.</li>
 * </ul>
 *
 * @author max
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE, ElementType.PACKAGE})
public @interface NonNls {

}
