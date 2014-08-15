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

package com.relicum.ipsum.Utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

/**
 * WorldGenerator void worlds with a single gold block to spawn on
 *
 * @author Relicum
 * @version 0.0.1
 */
public class WorldGenerator extends ChunkGenerator {

    public byte[] generate(World world, Random random, int cx, int cz) {

        return new byte[65536];
    }

    @Override
    public Location getFixedSpawnLocation(World world, Random random) {

        if (!world.isChunkLoaded(0, 0)) {
            world.loadChunk(0, 0);
        }
        return new Location(world, 0, 32, 0);
    }

}
