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

import java.util.Arrays;

/**
 * This class is part of the ParticleEffect library and follows the same usage conditions
 *
 * @author DarkBlade12
 */
public class NormalEffectData extends ParticleEffectData {
    private static final String FORMAT = "\\w+(@\\d+(\\.\\d+)?){3}@\\d+@\\d+(\\.\\d+)?";
    private final ParticleEffect effect;
    private final float speed;

    public NormalEffectData(ParticleEffect effect, float offsetX, float offsetY, float offsetZ, int amount, float speed) {
        super(offsetX, offsetY, offsetZ, amount);
        this.effect = effect;
        this.speed = speed;
    }

    public static NormalEffectData fromString(String s) throws IllegalArgumentException {
        if (!s.matches(FORMAT))
            throw new IllegalArgumentException("Invalid format");
        String[] p = s.split("@");
        ParticleEffect effect = ParticleEffect.fromName(p[0]);
        if (effect == null)
            throw new IllegalArgumentException("Contains an invalid particle effect name");
        return new NormalEffectData(effect, Float.parseFloat(p[1]), Float.parseFloat(p[2]), Float.parseFloat(p[3]), Integer.parseInt(p[4]), Float.parseFloat(p[5]));
    }

    @Override
    public void displayEffect(Location center, Player... players) {
        effect.display(offsetX, offsetY, offsetZ, speed, amount, center, Arrays.asList(players));
    }

    @Override
    public void displayEffect(Location center, double range) {
        effect.display(offsetX, offsetY, offsetZ, speed, amount, center, range);
    }

    public ParticleEffect getEffect() {
        return this.effect;
    }

    public float getSpeed() {
        return this.speed;
    }

    @Override
    public String toString() {
        return effect.getName() + "@" + super.toString() + "@" + speed;
    }
}
