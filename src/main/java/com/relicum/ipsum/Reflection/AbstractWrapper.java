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
package com.relicum.ipsum.Reflection;

import com.relicum.ipsum.Exception.ReflectionException;
import lombok.Data;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author dmulloy2
 */

@Data
public abstract class AbstractWrapper {
    protected Object nmsHandle;
    protected Class<?> nmsClass;
    protected Constructor<?> constructor;

    protected AbstractWrapper() {
    }

    protected final Object invokeMethod(Method method, Object... args) throws ReflectionException {
        try {
            return method.invoke(nmsHandle, args);
        } catch (Throwable ex) {
            throw ReflectionException.fromThrowable("invokeMethod(" + method + ", " + args + ")", ex);
        }
    }

    protected final Object getField(String name) throws ReflectionException {
        try {
            Field field = ReflectionUtil.getField(nmsClass, name);
            field.setAccessible(true);
            return field.get(nmsHandle);
        } catch (Throwable ex) {
            throw ReflectionException.fromThrowable("getField(" + name + ")", ex);
        }
    }

    protected final void setField(Field field, Object value) throws ReflectionException {
        try {
            field.setAccessible(true);
            field.set(nmsHandle, value);
        } catch (Throwable ex) {
            throw ReflectionException.fromThrowable("setField(" + field + ", " + value + ")", ex);
        }
    }
}
