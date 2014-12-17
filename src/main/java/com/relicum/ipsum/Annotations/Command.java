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
 * Command used to define a commands settings.
 * <p>aliases, description and permission are the only required values to set the other are optional.
 *
 * @author Relicum
 * @version 0.0.1
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    /**
     * A list of aliases for the command. The first alias is the most
     * important -- it is the main name of the command.
     *
     * @return Aliases for a command
     */
    String[] aliases();

    /**
     * Set the permission for the command
     *
     * @return the permission string.
     */
    String perm();

    /**
     * Usage instruction.
     *
     * @return Usage instructions for a command
     */
    String usage() default "";

    /**
     * @return A short description for the command.
     */
    String desc();

    /**
     * The minimum number of arguments. This should be 0 or above.
     *
     * @return the minimum number of arguments
     */
    int min() default 0;

    /**
     * The maximum number of arguments. Use -1 for an unlimited number
     * of arguments.
     *
     * @return the maximum number of arguments
     */
    int max() default -1;

    /**
     * Is the command a sub command, default false
     *
     * @return the boolean
     */
    boolean isSub() default false;

    /**
     * Name of the parent command, used to prefix this command.
     *
     * @return the parent commands name
     */
    String parent() default "";

    /**
     * Set to true if Tab Complete is required
     *
     * @return set true to use tab complete, default is false not to use it.
     */
    boolean useTab() default false;
}
