/*
 * Ipsum is a rapid development API for Minecraft, developer by Relicum
 * Copyright (C) 2015.  Chris Lutte
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

package com.relicum.ipsum.Utils;

/**
 * Name: Profiler.java Created: 09 January 2015
 *
 * @author Relicum
 * @version 0.0.1
 */

import org.apache.commons.lang.Validate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * This class helps profiling code
 */
public class Profiler {
    private static final Map<String, Long> startTimes = new HashMap<>();

    public static void startProfiling(String id) {
        final long nanos = System.nanoTime();
        synchronized (startTimes) {
            if (startTimes.containsKey(id)) {
                throw new IllegalStateException("This ID is already being profiled!");
            }
            startTimes.put(id, nanos);
        }
    }

    public static long getCurrentDelta(String id) {
        final long nanos = System.nanoTime();
        Validate.notNull(id, "ID should not be null");
        synchronized (startTimes) {
            if (!startTimes.containsKey(id)) {
                throw new IllegalStateException("This ID is not being profiled!");
            }
            return nanos - startTimes.get(id);
        }
    }

    public static long getCurrentDelta(String id, TimeUnit unit) {
        return unit.convert(getCurrentDelta(id), TimeUnit.NANOSECONDS);
    }

    public static long endProfiling(String id) {
        final long delta = System.nanoTime();
        Validate.notNull(id, "ID should not be null");
        synchronized (startTimes) {
            return delta - startTimes.remove(id);
        }
    }

    public static long endProfiling(String id, TimeUnit unit) {
        return unit.convert(endProfiling(id), TimeUnit.NANOSECONDS);
    }

    public static void clean() {
        synchronized (startTimes) {
            startTimes.clear();
        }
    }
}
