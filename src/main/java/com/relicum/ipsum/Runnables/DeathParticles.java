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

package com.relicum.ipsum.Runnables;

import com.relicum.ipsum.Effect.ParticleEffect;
import org.bukkit.entity.LivingEntity;

/**
 * DeathParticles simple clouds and colored dust particles are released around the location of the specified {@link org.bukkit.entity.LivingEntity} .
 * <p>This is not meant to to be configurable it is a quick easy go to and run method.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class DeathParticles implements Runnable {

    private final LivingEntity entity;

    /**
     * Instantiates a new Death particles object.
     *
     * @param entity the {@link org.bukkit.entity.LivingEntity} where the particles will be displayed around.
     */
    public DeathParticles(LivingEntity entity) {
        this.entity = entity;

    }

    @Override
    public void run() {

        ParticleEffect.ANGRY_VILLAGER.display(entity.getEyeLocation(), 1.0f, 1.0f, 1.0f, 0.1f, 40);
        ParticleEffect.FLAME.display(entity.getEyeLocation(), 1.5f, 1.5f, 1.5f, 0.1f, 50);

    }
}
