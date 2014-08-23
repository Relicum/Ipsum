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

package com.relicum.ipsum.Effect;

import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.lang.reflect.Method;
import java.util.Random;

/**
 * <tt>FireworkEffects</tt> all in one class to play a firework effect at a given location. <p>A pre defined {@link org.bukkit.FireworkEffect} is passed the power of the effect is
 * internal randomised between 0 and 2. <p>FireworkEffects provides a thread-safe and (reasonably) version independent way to instantly explode a FireworkEffect at a given
 * location. You are welcome to use, redistribute, modify and destroy your own copies of this source with the following conditions:
 * <p>
 * <p>
 * <ol> <li> No warranty is given or implied. </li> <li> All damage is your own responsibility. </li> <li> The source was based off Codename_B code and you must include him as
 * the original author.
 * </li><li>If you modify this version you must also included Relicum as a contributor to the class</li> </ol>
 * <p>
 * <p><tt>Thanks to Codename_B for allowing this code to be used and modified, modifications by Relicum</tt>
 *
 * @author Codename_B and Relicum
 */
@SuppressWarnings({"WeakerAccess", "CanBeFinal", "UnusedAssignment"})
public class FireworkEffects {

    private Method worldHandler = null;
    private Method nmsWorldBroadcastEntityEffect = null;
    private Method fireworkHandler = null;
    private Random random = new Random(6543647);

    /**
     * Internal method, used as shorthand to grab our method in a nice friendly manner
     *
     * @param clazz  the class you want the method from
     * @param method the method you want in string format
     * @return Method (or null)
     */
    private static Method getMethod(Class<?> clazz, String method) {
        for (Method m : clazz.getMethods()) {
            if (m.getName().equals(method)) {
                return m;
            }
        }
        return null;
    }

    /**
     * Play a firework effect at the specified location.
     *
     * @param world the bukkit world the effect will be displayed
     * @param loc   the location in the world the effect will be displayed
     * @param fe    the {@link org.bukkit.FireworkEffect} what will be used
     * @throws Exception the exception
     */
    public void playFirework(World world, Location loc, FireworkEffect fe) throws Exception {

        Firework fw = world.spawn(loc, Firework.class);

        Object nmsWorld = null;
        Object nmsFirework = null;

        if (worldHandler == null) {

            worldHandler = getMethod(world.getClass(), "getHandle");
            fireworkHandler = getMethod(fw.getClass(), "getHandle");
        }

        nmsWorld = worldHandler.invoke(world, (Object[]) null);
        nmsFirework = fireworkHandler.invoke(fw, (Object[]) null);

        if (nmsWorldBroadcastEntityEffect == null) {

            nmsWorldBroadcastEntityEffect = getMethod(nmsWorld.getClass(), "broadcastEntityEffect");
        }

        //Load firework metadata
        FireworkMeta data = fw.getFireworkMeta();

        //clear firework effects to allow reuse
        data.clearEffects();
        //Set power of firework to 1
        data.setPower((random.nextInt(200) + 101) / 100);
        //add the effect
        data.addEffect(fe);

        fw.setFireworkMeta(data);

        /*
         * Now broadcast the firework and kill the firework object
         *
         */

        nmsWorldBroadcastEntityEffect.invoke(nmsWorld, nmsFirework, (byte) 17);

        //Remove the object from game
        fw.remove();
    }

}

