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

package com.relicum.ipsum.Locale;

/**
 * consoleColors used to format messages in color to the console
 *
 * @author Relicum
 * @version 0.0.1
 */
public interface consoleColors {

    String RESET = "\033[0m";
    String BLACK = "\033[30m";      /* Black */
    String RED = "\033[31m";      /* Red */
    String GREEN = "\033[32m";      /* Green */
    String YELLOW = "\033[33m";      /* Yellow */
    String BLUE = "\033[34m";      /* Blue */
    String MAGENTA = "\033[35m";      /* Magenta */
    String CYAN = "\033[36m";      /* Cyan */
    String WHITE = "\033[37m";      /* White */
    String BOLDBLACK = "\033[1m\033[30m";      /* Bold Black */
    String BOLDRED = "\033[1m\033[31m";      /* Bold Red */
    String BOLDGREEN = "\033[1m\033[32m";     /* Bold Green */
    String BOLDYELLOW = "\033[1m\033[33m";      /* Bold Yellow */
    String BOLDBLUE = "\033[1m\033[34m";      /* Bold Blue */
    String BOLDMAGENTA = "\033[1m\033[35m";      /* Bold Magenta */
    String BOLDCYAN = "\033[1m\033[36m";      /* Bold Cyan */
    String BOLDWHITE = "\033[1m\033[37m";     /* Bold White */

}
