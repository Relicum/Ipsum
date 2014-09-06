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

import com.relicum.ipsum.Locale.I18N;
import com.relicum.ipsum.Minecraft.ISender;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

/**
 * EntityRemoval creates a repeating task that will mark a specified list of entities for removal.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class EntityRemoval extends BukkitRunnable implements ISender {

    private List<Entity> entities = new ArrayList<>();

    private final long delay;

    private final long period;

    private int nonRemovalCount = 0;

    private final int nonRemovalThreshold;

    private final JavaPlugin plugin;


    public EntityRemoval(JavaPlugin plugin, long delay, long period, int nonRemovalThreshold) {
        this.nonRemovalThreshold = nonRemovalThreshold;
        this.delay = delay;
        this.period = period;

        this.runTaskTimer(plugin, delay, period);

        this.plugin = plugin;

    }

    public EntityRemoval(JavaPlugin plugin, long delay, long period) {
        this.nonRemovalThreshold = -1;
        this.delay = delay;
        this.period = period;

        this.runTaskTimer(plugin, delay, period);

        this.plugin = plugin;

    }

    /**
     * Add entity you would like to be marked for removal.
     * <p>The frequency of the actual removal is not entity specific.
     * It is determined
     *
     * @param entity the entity
     */
    public void addEntity(Entity entity) {

        getEntities().add(entity);

    }


    /**
     * Get entities.
     *
     * @return the list of entities waiting for removal
     */
    private synchronized List<Entity> getEntities() {

        return entities;
    }

    /**
     * Clear entities list.
     */
    private void clearEntitiesList() {

        getEntities().clear();
    }

    private void markEntitiesForRemoval() {


    }

    @Override
    public void run() {

        if (!(nonRemovalThreshold == -1) || (nonRemovalCount >= nonRemovalThreshold)) {

            plugin.getLogger().info("Non entity removal threshold reached stopping task");


        }

        if (this.nonRemovalThreshold != -1 || (nonRemovalCount <= nonRemovalThreshold)) {

            if (!getEntities().isEmpty()) {

                clear();

                nonRemovalCount++;

                plugin.getServer().broadcastMessage(I18N.adminFormat(I18N.STRING("")));

                plugin.getLogger().info("Successfully removed the marked entities");
            }

        }
    }

    private void clear() {

        getEntities().stream().filter(Entity::isValid).forEach(Entity::remove);
        clearEntitiesList();

    }
}
