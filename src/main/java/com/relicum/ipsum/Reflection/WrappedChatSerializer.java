/**
 * (c) 2014 dmulloy2
 */
package com.relicum.ipsum.Reflection;

import com.relicum.ipsum.Exception.ReflectionException;

import java.lang.reflect.Method;

/**
 * @author dmulloy2
 */

//TODO: Keep up to date with MC versions. 1.7.10
public class WrappedChatSerializer extends AbstractWrapper {
    private static final String NMS_CLASS_NAME = "ChatSerializer";

    private final Method a;

    public WrappedChatSerializer() throws ReflectionException {
        try {
            this.nmsClass = ReflectionUtil.getNMSClass(NMS_CLASS_NAME);
            this.constructor = null;
            this.nmsHandle = null;

            this.a = ReflectionUtil.getMethod(nmsClass, "a", String.class);
        } catch (Throwable ex) {
            throw ReflectionException.fromThrowable("Constructing chat serializer", ex);
        }
    }

    public final Object serialize(String json) throws ReflectionException {
        return invokeMethod(a, json);
    }
}
