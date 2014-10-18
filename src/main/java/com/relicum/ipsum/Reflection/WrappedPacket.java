/**
 * (c) 2014 dmulloy2
 */
package com.relicum.ipsum.Reflection;

import com.relicum.ipsum.Exception.ReflectionException;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author dmulloy2
 */

// TODO: Keep up to date with MC versions. 1.7.10
public abstract class WrappedPacket extends AbstractWrapper {
    public final void send(Player player) throws ReflectionException {
        try {
            Object nmsPlayer = ReflectionUtil.getHandle(player);
            Field playerConnectionField = ReflectionUtil.getField(nmsPlayer.getClass(), "playerConnection");
            Object playerConnection = playerConnectionField.get(nmsPlayer);
            Method sendPacket = ReflectionUtil.getMethod(playerConnection.getClass(), "sendPacket");
            sendPacket.invoke(playerConnection, nmsHandle);
        } catch (Throwable ex) {
            throw ReflectionException.fromThrowable("Sending packet to " + player.getName(), ex);
        }
    }

    public final void sendToServer(Player player) throws ReflectionException {
        try {
            Object nmsPlayer = ReflectionUtil.getHandle(player);
            Field playerConnectionField = ReflectionUtil.getField(nmsPlayer.getClass(), "playerConnection");
            Object playerConnection = playerConnectionField.get(nmsPlayer);
            Method a = ReflectionUtil.getMethod(playerConnection.getClass(), "a", nmsClass);
            a.invoke(playerConnection, nmsHandle);
        } catch (Throwable ex) {
            throw ReflectionException.fromThrowable("Sending packet from " + player.getName() + " to the server", ex);
        }
    }
}
