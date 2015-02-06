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

package com.relicum.ipsum.Events;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public abstract class BaseEvent {
    private static Map<String, List<Wrapper>> eventHandlers = new HashMap<>();

    public static void registerListener(Listener listener) {
        for (Method m : listener.getClass().getMethods()) {
            IpsumEvent e = m.getAnnotation(IpsumEvent.class);
            if (e != null) {
                Class[] params = m.getParameterTypes();
                if ((params.length == 1) && (BaseEvent.class.isAssignableFrom(params[0]))) {
                    String key = params[0].getSimpleName();
                    List<Wrapper> wrappers = (List) eventHandlers.get(key);
                    if (wrappers == null)
                        wrappers = new ArrayList<>();
                    wrappers.add(new Wrapper(listener, m, e.priority()));
                    Collections.sort(wrappers, new PrioritySorter());
                    eventHandlers.put(key, wrappers);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void callEvent(BaseEvent event) {
        List<Wrapper> wrappers = (List) eventHandlers.get(event.getClass().getSimpleName());
        if (wrappers != null) {
            for (Wrapper wrap : wrappers) {
                wrap.call(event);
            }
        }
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + getClass().getSimpleName().hashCode();
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!obj.getClass().isAssignableFrom(getClass()))
            return false;
        if (obj.getClass().getSimpleName().equals(getClass().getSimpleName()))
            return true;
        return false;
    }

    private static class PrioritySorter implements Comparator<BaseEvent.Wrapper> {
        public int compare(BaseEvent.Wrapper one, BaseEvent.Wrapper two) {
            int x = check(one.priority);
            int y = check(two.priority);
            if (x < y)
                return -1;
            if (x > y)
                return 1;
            return 0;
        }

        private int check(EventPriority p) {
            if (p == EventPriority.LOWEST)
                return 0;
            if (p == EventPriority.LOW)
                return 1;
            if (p == EventPriority.NORMAL)
                return 2;
            if (p == EventPriority.HIGH)
                return 3;
            if (p == EventPriority.HIGHEST)
                return 4;
            if (p == EventPriority.MONITOR)
                return 5;
            return -1;
        }
    }

    private static class Wrapper {
        public final EventPriority priority;
        private final Listener listener;
        private final Method m;

        public Wrapper(Listener listener, Method m, EventPriority p) {
            this.listener = listener;
            this.m = m;
            this.priority = p;
        }

        public void call(BaseEvent e) {
            try {
                if (!this.m.isAccessible())
                    this.m.setAccessible(true);
                this.m.invoke(this.listener, new Object[] { e });
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
                e1.printStackTrace();
            }
        }
    }
}
