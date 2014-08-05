package com.relicum.ipsum.Utils;

import com.relicum.ipsum.Ipsum;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

import java.util.Random;

/**
 * WorldMaker used to created new worlds.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class WorldMaker {

    private WorldType worldType = WorldType.FLAT;
    private World.Environment environment = World.Environment.NORMAL;
    private boolean generateStructures = false;


    private Ipsum plugin;

    public WorldMaker(Ipsum plugin) {

        this.plugin = plugin;

    }

    public WorldCreator getWorldCreator(String name) {

        WorldCreator worldCreator = new WorldCreator("Relicum");
        worldCreator.environment(environment);
        worldCreator.generateStructures(generateStructures);
        worldCreator.type(worldType);
        // worldCreator.seed(randomSeed());

        return worldCreator;
    }


    /**
     * Fet a new Random seed for the world
     *
     * @return Random Long as a world seed
     */
    public long randomSeed() {

        Random random = new Random();
        return random.nextLong();
    }
}
