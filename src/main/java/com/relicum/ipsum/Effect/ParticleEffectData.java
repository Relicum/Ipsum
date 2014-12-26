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
public abstract class ParticleEffectData {
    protected final float offsetX, offsetY, offsetZ;
    protected final int amount;

    public ParticleEffectData(float offsetX, float offsetY, float offsetZ, int amount) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.amount = amount;
    }

    public abstract void displayEffect(Location center, Player... players);

    public abstract void displayEffect(Location center, double range);

    public float getOffsetX() {
        return this.offsetX;
    }

    public float getOffsetY() {
        return this.offsetY;
    }

    public float getOffsetZ() {
        return this.offsetZ;
    }

    public int getAmount() {
        return this.amount;
    }

    @Override
    public String toString() {
        return offsetX + "@" + offsetY + "@" + offsetZ + "@" + amount;
    }
}
