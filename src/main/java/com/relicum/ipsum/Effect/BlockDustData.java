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

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * This class is part of the ParticleEffect library and follows the same usage conditions
 *
 * @author DarkBlade12
 */
public class BlockDustData extends ParticleEffectData {
    private static final String FORMAT = "\\d+@\\d+(@\\d+(\\.\\d+)?){3}@\\d+@\\d+(\\.\\d+)?";
    private final int id;
    private final byte data;
    private final float speed;

    public BlockDustData(int id, byte data, float offsetX, float offsetY, float offsetZ, int amount, float speed) {
        super(offsetX, offsetY, offsetZ, amount);
        this.id = id;
        this.data = data;
        this.speed = speed;
    }

    public static BlockDustData fromString(String s) throws IllegalArgumentException {
        if (!s.matches(FORMAT))
            throw new IllegalArgumentException("Invalid format");
        String[] p = s.split("@");
        return new BlockDustData(Integer.parseInt(p[0]), Byte.parseByte(p[1]), Float.parseFloat(p[2]), Float.parseFloat(p[3]), Float.parseFloat(p[4]), Integer.parseInt(p[5]), Float.parseFloat(p[6]));
    }

    @Override
    public void displayEffect(Location center, Player... players) {
        //   ParticleEffect.displayBlockDust(id, data, offsetX, offsetY, offsetZ, speed, amount, center, Arrays.asList(players));
    }

    @Override
    public void displayEffect(Location center, double range) {
        //  ParticleEffect.displayBlockDust(id, data, offsetX, offsetY, offsetZ, speed, amount, center, range);
    }

    public int getId() {
        return this.id;
    }

    public byte getData() {
        return this.data;
    }

    public float getSpeed() {
        return this.speed;
    }

    @Override
    public String toString() {
        return id + "@" + data + "@" + super.toString() + "@" + speed;
    }
}
