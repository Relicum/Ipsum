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

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * RandomFireWorks generator
 *
 * @author Relicum
 * @version 0.0.1
 */
public class RandomFireWorks extends BukkitRunnable {


    private final int offSetY;
    private final int offSet;
    private final int total;
    private final Location loc;
    private Random random;
    private int c = 0;
    private List<Firework> list = new ArrayList<>();

    /**
     * Instantiates a new RandomFireworks object, that displays a specified number of fireworks, with offsets.
     * <p>This class also removes all the entities when finished.
     *
     * @param location  the location
     * @param offSetY   the off set y
     * @param offSet    the off set
     * @param total     the total number of fire works to launch
     * @param frequency the frequency the fireworks are launched in ticks
     * @param plugin    the plugin
     */
    public RandomFireWorks(Location location, int offSetY, int offSet, int total, long frequency, Plugin plugin) {

        this.loc = location;
        this.offSetY = offSetY;
        this.offSet = offSet;
        this.total = total;
        this.random = new Random();

        this.runTaskTimer(plugin, 0l, frequency);


    }

    @Override
    public void run() {

        if (c == total) {

            list.stream().filter(Entity::isValid).forEach(org.bukkit.entity.Firework::remove);

            list.clear();

            cancel();
            return;
        }

        FireworkEffect.Builder builder = FireworkEffect.builder();

        Location l = loc.clone().add(random.nextInt(offSet - (-offSet)) + (-offSet - -1), offSetY, random.nextInt(offSet - (-offSet)) + (-offSet - -1));

        if (random.nextBoolean())
            builder.withFlicker();
        else
            builder.withTrail();


        builder.with(org.bukkit.FireworkEffect.Type.values()[random.nextInt(org.bukkit.FireworkEffect.Type.values().length)]);

        int colorCount = 17;

        builder.withColor(Color.fromRGB(random.nextInt(255), random.nextInt(255), random.nextInt(255)));

        while (random.nextInt(colorCount) != 0) {
            builder.withColor(Color.fromRGB(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            colorCount--;
        }

        Firework firework = (Firework) loc.getWorld().spawn(l, Firework.class);
        FireworkMeta data = firework.getFireworkMeta();

        data.addEffects(builder.build());

        data.setPower(random.nextInt(2));

        firework.setFireworkMeta(data);

        list.add(firework);

        c++;
        l = null;
    }
}
