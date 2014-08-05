package com.relicum.ipsum.Utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockState;
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
            BlockState blockState = world.getBlockAt(0, 31, 0).getState();
            if (blockState.getType().equals(Material.AIR)) {
                blockState.setType(Material.GOLD_BLOCK);
                blockState.update(true);
            }
        }
        return new Location(world, 0, 32, 0);
    }

}
