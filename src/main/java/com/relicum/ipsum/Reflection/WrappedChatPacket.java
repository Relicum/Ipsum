/**
 * (c) 2014 dmulloy2
 */
package com.relicum.ipsum.Reflection;

import com.relicum.ipsum.Exception.ReflectionException;

/**
 * @author dmulloy2
 */

// TODO: Keep up to date with MC versions. 1.7.10
public class WrappedChatPacket extends WrappedPacket {
    private static final String NMS_CLASS_NAME = "PacketPlayOutChat";
    private static final Class<?> chatComponentClass = ReflectionUtil.getNMSClass("IChatBaseComponent");

    public WrappedChatPacket(Object chatComponent) throws ReflectionException {

        try {
            this.nmsClass = ReflectionUtil.getNMSClass(NMS_CLASS_NAME);
            this.constructor = nmsClass.getConstructor(chatComponentClass);
            this.nmsHandle = constructor.newInstance(chatComponent);
        } catch (Throwable ex) {
            throw ReflectionException.fromThrowable("Constructing wrapped chat packet", ex);
        }
    }

}
