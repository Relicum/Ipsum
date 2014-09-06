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

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * ParticleEffect Library v1.4
 * <p>
 * This library was created by @DarkBlade12 based on content related to particles of @microgeek (names and packet values), it allows you to display all Minecraft particle effects
 * on a Bukkit server
 * <p>
 * You are welcome to use it, modify it and redistribute it under the following conditions: 1. Don't claim this class as your own 2. Don't remove this text
 * <p>
 * (Would be nice if you provide credit to me)
 *
 * @author DarkBlade12
 */
@SuppressWarnings({"JavadocReference", "SameParameterValue", "WeakerAccess", "CanBeFinal"})
public enum ParticleEffect {
    /**
     * Appearance Huge explosions,displayed by TNT and creepers.
     */
    HUGE_EXPLOSION("hugeexplosion"),
    /**
     * Appearance Smaller explosions displayed by TNT and creepers.
     */
    LARGE_EXPLODE("largeexplode"),
    /**
     * Appearance Little white sparkling stars displayed by Fireworks.
     */
    FIREWORKS_SPARK("fireworksSpark"),
    /**
     * Appearance Bubbles displayed in water.
     */
    BUBBLE("bubble"),
    /**
     * Appearance Unknown
     */
    SUSPEND("suspend"),
    /**
     * Appearance Little gray dots displayed in the Void and water.
     */
    DEPTH_SUSPEND("depthSuspend"),
    /**
     * Appearance Little gray dots displayed by Mycelium.
     */
    TOWN_AURA("townaura"),
    /**
     * Appearance Light brown crosses displayed by critical hits.
     */
    CRIT("crit"),
    /**
     * Appearance Cyan stars displayed by hits with an enchanted weapon.
     */
    MAGIC_CRIT("magicCrit"),
    /**
     * Appearance Little black/gray clouds displayed by torches, primed TNT and end portals.
     */
    SMOKE("smoke"),
    /**
     * Appearance Colored swirls displayed by potion effects.
     */
    MOB_SPELL("mobSpell"),
    /**
     * Appearance Transparent colored swirls displayed by beacon effect.
     */
    MOB_SPELL_AMBIENT("mobSpellAmbient"),
    /**
     * Appearance Colored swirls displayed by splash potions.
     */
    SPELL("spell"),
    /**
     * Appearance Colored crosses displayed by instant splash potions. (instant health/instant damage)
     */
    INSTANT_SPELL("instantSpell"),
    /**
     * Appearance Colored crosses displayed by witches.
     */
    WITCH_MAGIC("witchMagic"),
    /**
     * Appearance Colored notes displayed by note blocks.
     */
    NOTE("note"),
    /**
     * Appearance Little purple clouds displayed by nether portals. Endermen, ender pearls, eyes of ender and ender chests.
     */
    PORTAL("portal"),
    /**
     * Appearance: White letters isplayed by enchantment tables that are near bookshelves.
     */
    ENCHANTMENT_TABLE("enchantmenttable"),
    /**
     * Appearance White clouds
     */
    EXPLODE("explode"),
    /**
     * Appearance Little flames displayed by torches, furnaces, magma cubes and monster spawners.
     */
    FLAME("flame"),
    /**
     * Appearance Little orange blobs displayed by lava.
     */
    LAVA("lava"),
    /**
     * Appearance Gray transparent squares
     */
    FOOTSTEP("footstep"),
    /**
     * Appearance Blue drops displayed by water, rain and shaking wolves.
     */
    SPLASH("splash"),
    /**
     * Appearance Blue droplets displayed on water when fishing.
     */
    WAKE("wake"),
    /**
     * Appearance Black/Gray clouds displayed by fire, minecarts with furance and blazes.
     */
    LARGE_SMOKE("largesmoke"),
    /**
     * Appearance Large white clouds displayed on mob death.
     */
    CLOUD("cloud"),
    /**
     * Appearance Little colored clouds displayed by active redstone wires and redstone torches.
     */
    RED_DUST("reddust"),
    /**
     * Appearance Little white parts displayed by cracking snowballs and eggs.
     */
    SNOWBALL_POOF("snowballpoof"),
    /**
     * Appearance Blue drips displayed by blocks below a water source.
     */
    DRIP_WATER("dripWater"),
    /**
     * Appearance Orange drips displayed by blocks below a lava source.
     */
    DRIP_LAVA("dripLava"),
    /**
     * Appearance White clouds
     */
    SNOW_SHOVEL("snowshovel"),
    /**
     * Appearance Little green parts displayed by slimes.
     */
    SLIME("slime"),
    /**
     * Appearance Red hearts displayed when breeding.
     */
    HEART("heart"),

    /**
     * Appearance Dark gray cracked hearts,Displayed when attacking a villager in a village.
     */
    ANGRY_VILLAGER("angryVillager"),
    /**
     * Appearance Green stars displayed by bone meal and when trading with a villager.
     */
    HAPPY_VILLAGER("happyVillager");

    private static final Map<String, com.relicum.ipsum.Effect.ParticleEffect> NAME_MAP = new HashMap<>();
    private static final double MAX_RANGE = 16;
    private static Constructor<?> packetPlayOutWorldParticles;
    private static Method getHandle;
    private static Field playerConnection;
    private static Method sendPacket;
    private final String name;

    static {
        for (com.relicum.ipsum.Effect.ParticleEffect p : values()) {
            NAME_MAP.put(p.name, p);
        }
        try {
            packetPlayOutWorldParticles =
                    ReflectionHandler
                            .getConstructor(ReflectionHandler.PacketType.PLAY_OUT_WORLD_PARTICLES.getPacket(),
                                    String.class,
                                    float.class,
                                    float.class,
                                    float.class,
                                    float.class,
                                    float.class,
                                    float.class,
                                    float.class,
                                    int.class);
            getHandle = ReflectionHandler.getMethod("CraftPlayer", ReflectionHandler.SubPackageType.ENTITY, "getHandle");
            playerConnection = ReflectionHandler.getField("EntityPlayer", ReflectionHandler.PackageType.MINECRAFT_SERVER, "playerConnection");
            sendPacket =
                    ReflectionHandler.getMethod(playerConnection.getType(),
                            "sendPacket",
                            ReflectionHandler.getClass("Packet", ReflectionHandler.PackageType.MINECRAFT_SERVER));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param name Name of this particle effect
     */
    private ParticleEffect(String name) {

        this.name = name;
    }

    /**
     * Gets a particle effect from name
     *
     * @param name Name of the particle effect
     * @return The particle effect
     */
    public static com.relicum.ipsum.Effect.ParticleEffect fromName(String name) {

        if (name != null) {
            for (Map.Entry<String, com.relicum.ipsum.Effect.ParticleEffect> e : NAME_MAP.entrySet()) {
                if (e.getKey().equalsIgnoreCase(name)) {
                    return e.getValue();
                }
            }
        }
        return null;
    }

    /**
     * Gets a list of players in a certain range
     *
     * @param center Center location
     * @param range  Range
     * @return The list of players in the specified range
     */
    private static List<Player> getPlayers(Location center, double range) {

        List<Player> players = new ArrayList<>();
        String name = center.getWorld().getName();
        double squared = range * range;
        //players.addAll(Bukkit.getOnlinePlayers().stream().filter(p -> p.getWorld().getName().equals(name) && p.getLocation().distanceSquared(center) <= squared).collect(Collectors.toList()));

        for (Player player : Bukkit.getOnlinePlayers()) {

            if (player.getWorld().getName().equals(name) && player.getLocation().distanceSquared(center) <= squared) {
                players.add(player);
            }
        }

        return players;
    }

    /**
     * Instantiates a new @PacketPlayOutWorldParticles object through reflection
     *
     * @param center  Center location of the effect
     * @param offsetX Maximum distance particles can fly away from the center on the x-axis
     * @param offsetY Maximum distance particles can fly away from the center on the y-axis
     * @param offsetZ Maximum distance particles can fly away from the center on the z-axis
     * @param speed   Display speed of the particles
     * @param amount  Amount of particles
     * @return The packet object
     * @throws PacketInstantiationException if the amount is lower than 1 or if the @PacketPlayOutWorldParticles has changed its name or constructor parameters
     */
    private static Object instantiatePacket(String name, Location center, float offsetX, float offsetY, float offsetZ, float speed, int amount) {

        if (amount < 1) {
            throw new PacketInstantiationException("Amount cannot be lower than 1");
        }
        try {
            return packetPlayOutWorldParticles.newInstance(name,
                    (float) center.getX(),
                    (float) center.getY(),
                    (float) center.getZ(),
                    offsetX,
                    offsetY,
                    offsetZ,
                    speed,
                    amount);
        } catch (Exception e) {
            throw new PacketInstantiationException("Packet instantiation failed", e);
        }
    }

    /**
     * Instantiates a new @PacketPlayOutWorldParticles object through reflection especially for the "iconcrack" effect
     *
     * @param id      Id of the icon
     * @param offsetX Maximum distance particles can fly away from the center on the x-axis
     * @param offsetY Maximum distance particles can fly away from the center on the y-axis
     * @param offsetZ Maximum distance particles can fly away from the center on the z-axis
     * @param speed   Display speed of the particles
     * @param amount  Amount of particles
     * @return The packet object
     * @throws PacketInstantiationException if the amount is lower than 1 or if the @PacketPlayOutWorldParticles has changed its name or constructor parameters
     * @see instantiatePacket
     */
    private static Object instantiateIconCrackPacket(int id, Location center, float offsetX, float offsetY, float offsetZ, float speed, int amount) {

        return instantiatePacket("iconcrack_" + id, center, offsetX, offsetY, offsetZ, speed, amount);
    }

    /**
     * Instantiates a new @PacketPlayOutWorldParticles object through reflection especially for the "blockcrack" effect
     *
     * @param id      Id of the block
     * @param data    Data value
     * @param offsetX Maximum distance particles can fly away from the center on the x-axis
     * @param offsetY Maximum distance particles can fly away from the center on the y-axis
     * @param offsetZ Maximum distance particles can fly away from the center on the z-axis
     * @param amount  Amount of particles
     * @return The packet object
     * @throws PacketInstantiationException if the amount is lower than 1 or if the @PacketPlayOutWorldParticles has changed its name or constructor parameters
     * @see instantiatePacket
     */
    private static Object instantiateBlockCrackPacket(int id, byte data, Location center, float offsetX, float offsetY, float offsetZ, int amount) {

        return instantiatePacket("blockcrack_" + id + "_" + data, center, offsetX, offsetY, offsetZ, 0, amount);
    }

    /**
     * Instantiates a new @PacketPlayOutWorldParticles object through reflection especially for the "blockdust" effect
     *
     * @param id      Id of the block
     * @param data    Data value
     * @param offsetX Maximum distance particles can fly away from the center on the x-axis
     * @param offsetY Maximum distance particles can fly away from the center on the y-axis
     * @param offsetZ Maximum distance particles can fly away from the center on the z-axis
     * @param speed   Display speed of the particles
     * @param amount  Amount of particles
     * @return The packet object
     * @throws PacketInstantiationException if the amount is lower than 1 or if the name or the constructor of @PacketPlayOutWorldParticles have changed
     * @see instantiatePacket
     */
    private static Object instantiateBlockDustPacket(int id, byte data, Location center, float offsetX, float offsetY, float offsetZ, float speed, int amount) {

        return instantiatePacket("blockdust_" + id + "_" + data, center, offsetX, offsetY, offsetZ, speed, amount);
    }

    /**
     * Sends a packet through reflection to a player
     *
     * @param p      Receiver of the packet
     * @param packet Packet that is sent
     * @throws PacketSendingException if the packet is null or some methods which are accessed through reflection have changed
     */
    private static void sendPacket(Player p, Object packet) {

        try {
            sendPacket.invoke(playerConnection.get(getHandle.invoke(p)), packet);
        } catch (Exception e) {
            throw new PacketSendingException("Failed to send a packet to player '" + p.getName() + "'", e);
        }
    }

    /**
     * Sends a packet through reflection to a collection of players
     *
     * @param players Receivers of the packet
     * @param packet  Packet that is sent
     * @throws PacketSendingException if the sending to a single player fails
     * @see sendPacket
     */
    private static void sendPacket(Collection<Player> players, Object packet) {

        for (Player p : players) {
            sendPacket(p, packet);
        }
    }

    /**
     * Displays an icon crack (item break) particle effect which is only visible for the specified players
     *
     * @param center  Center location of the effect
     * @param id      Id of the icon
     * @param offsetX Maximum distance particles can fly away from the center on the x-axis
     * @param offsetY Maximum distance particles can fly away from the center on the y-axis
     * @param offsetZ Maximum distance particles can fly away from the center on the z-axis
     * @param speed   Display speed of the particles
     * @param amount  Amount of particles
     * @param players Receivers of the effect
     * @see sendPacket
     * @see instantiateIconCrackPacket
     */
    public static void displayIconCrack(Location center, int id, float offsetX, float offsetY, float offsetZ, float speed, int amount, Player... players) {

        sendPacket(Arrays.asList(players), instantiateIconCrackPacket(id, center, offsetX, offsetY, offsetZ, speed, amount));
    }

    /**
     * Displays an icon crack (item break) particle effect which is only visible for all players within a certain range in the world of @param center
     *
     * @param center  Center location of the effect
     * @param range   Range of the visibility
     * @param id      Id of the icon
     * @param offsetX Maximum distance particles can fly away from the center on the x-axis
     * @param offsetY Maximum distance particles can fly away from the center on the y-axis
     * @param offsetZ Maximum distance particles can fly away from the center on the z-axis
     * @param speed   Display speed of the particles
     * @param amount  Amount of particles
     * @throws IllegalArgumentException if the range is higher than 20
     * @see sendPacket
     * @see instantiateIconCrackPacket
     */
    public static void displayIconCrack(Location center, double range, int id, float offsetX, float offsetY, float offsetZ, float speed, int amount) {

        if (range > MAX_RANGE) {
            throw new IllegalArgumentException("Range has to be lower/equal the maximum of 16");
        }
        sendPacket(getPlayers(center, range), instantiateIconCrackPacket(id, center, offsetX, offsetY, offsetZ, speed, amount));
    }

    /**
     * Displays an icon crack (item break) effect which is visible for all players whitin the maximum range of 20 blocks in the world of @param center
     *
     * @param center  Center location of the effect
     * @param id      Id of the icon
     * @param offsetX Maximum distance particles can fly away from the center on the x-axis
     * @param offsetY Maximum distance particles can fly away from the center on the y-axis
     * @param offsetZ Maximum distance particles can fly away from the center on the z-axis
     * @param speed   Display speed of the particles
     * @param amount  Amount of particles
     * @see displayIconCrack(org.bukkit.Location, double, int, float, float, float, float, int)
     */
    public static void displayIconCrack(Location center, int id, float offsetX, float offsetY, float offsetZ, float speed, int amount) {

        displayIconCrack(center, MAX_RANGE, id, offsetX, offsetY, offsetZ, speed, amount);
    }

    /**
     * Displays a block crack (block break) particle effect which is only visible for the specified players
     *
     * @param center  Center location of the effect
     * @param id      Id of the block
     * @param data    Data value
     * @param offsetX Maximum distance particles can fly away from the center on the x-axis
     * @param offsetY Maximum distance particles can fly away from the center on the y-axis
     * @param offsetZ Maximum distance particles can fly away from the center on the z-axis
     * @param amount  Amount of particles
     * @param players Receivers of the effect
     * @see sendPacket
     * @see instantiateBlockCrackPacket
     */
    public static void displayBlockCrack(Location center, int id, byte data, float offsetX, float offsetY, float offsetZ, int amount, Player... players) {

        sendPacket(Arrays.asList(players), instantiateBlockCrackPacket(id, data, center, offsetX, offsetY, offsetZ, amount));
    }

    /**
     * Displays a block crack (block break) particle effect which is only visible for all players within a certain range in the world of @param center
     *
     * @param center  Center location of the effect
     * @param range   Range of the visibility
     * @param id      Id of the block
     * @param data    Data value
     * @param offsetX Maximum distance particles can fly away from the center on the x-axis
     * @param offsetY Maximum distance particles can fly away from the center on the y-axis
     * @param offsetZ Maximum distance particles can fly away from the center on the z-axis
     * @param amount  Amount of particles
     * @throws IllegalArgumentException if the range is higher than 20
     * @see sendPacket
     * @see instantiateBlockCrackPacket
     */
    public static void displayBlockCrack(Location center, double range, int id, byte data, float offsetX, float offsetY, float offsetZ, int amount) {

        if (range > MAX_RANGE) {
            throw new IllegalArgumentException("Range has to be lower/equal the maximum of 16");
        }
        sendPacket(getPlayers(center, range), instantiateBlockCrackPacket(id, data, center, offsetX, offsetY, offsetZ, amount));
    }

    /**
     * Displays a block crack (block break) effect which is visible for all players whitin the maximum range of 20 blocks in the world of @param center
     *
     * @param center  Center location of the effect
     * @param id      Id of the block
     * @param data    Data value
     * @param offsetX Maximum distance particles can fly away from the center on the x-axis
     * @param offsetY Maximum distance particles can fly away from the center on the y-axis
     * @param offsetZ Maximum distance particles can fly away from the center on the z-axis
     * @param amount  Amount of particles
     * @see displayBlockCrack(org.bukkit.Location, double, int, byte, float, float, float, int)
     */
    public static void displayBlockCrack(Location center, int id, byte data, float offsetX, float offsetY, float offsetZ, int amount) {

        displayBlockCrack(center, MAX_RANGE, id, data, offsetX, offsetY, offsetZ, amount);
    }

    /**
     * Displays a block dust particle effect which is only visible for the specified players
     *
     * @param center  Center location of the effect
     * @param id      Id of the block
     * @param data    Data value
     * @param offsetX Maximum distance particles can fly away from the center on the x-axis
     * @param offsetY Maximum distance particles can fly away from the center on the y-axis
     * @param offsetZ Maximum distance particles can fly away from the center on the z-axis
     * @param speed   Display speed of the particles
     * @param amount  Amount of particles
     * @param players Receivers of the effect
     * @see sendPacket
     * @see instantiateBlockDustPacket
     */
    public static void displayBlockDust(Location center, int id, byte data, float offsetX, float offsetY, float offsetZ, float speed, int amount, Player... players) {

        sendPacket(Arrays.asList(players), instantiateBlockDustPacket(id, data, center, offsetX, offsetY, offsetZ, speed, amount));
    }

    /**
     * Displays a block dust particle effect which is only visible for all players within a certain range in the world of @param center
     *
     * @param center  Center location of the effect
     * @param range   Range of the visibility
     * @param id      Id of the block
     * @param data    Data value
     * @param offsetX Maximum distance particles can fly away from the center on the x-axis
     * @param offsetY Maximum distance particles can fly away from the center on the y-axis
     * @param offsetZ Maximum distance particles can fly away from the center on the z-axis
     * @param speed   Display speed of the particles
     * @param amount  Amount of particles
     * @throws IllegalArgumentException if the range is higher than 20
     * @see sendPacket
     * @see instantiateBlockDustPacket
     */
    public static void displayBlockDust(Location center, double range, int id, byte data, float offsetX, float offsetY, float offsetZ, float speed, int amount) {

        if (range > MAX_RANGE) {
            throw new IllegalArgumentException("Range has to be lower/equal the maximum of 16");
        }
        sendPacket(getPlayers(center, range), instantiateBlockDustPacket(id, data, center, offsetX, offsetY, offsetZ, speed, amount));
    }

    /**
     * Displays a block dust effect which is visible for all players whitin the maximum range of 20 blocks in the world of @param center
     *
     * @param center  Center location of the effect
     * @param id      Id of the block
     * @param data    Data value
     * @param offsetX Maximum distance particles can fly away from the center on the x-axis
     * @param offsetY Maximum distance particles can fly away from the center on the y-axis
     * @param offsetZ Maximum distance particles can fly away from the center on the z-axis
     * @param speed   Display speed of the particles
     * @param amount  Amount of particles
     * @see displayBlockDust(org.bukkit.Location, double, int, byte, float, float, float, float, int)
     */
    public static void displayBlockDust(Location center, int id, byte data, float offsetX, float offsetY, float offsetZ, float speed, int amount) {

        displayBlockDust(center, MAX_RANGE, id, data, offsetX, offsetY, offsetZ, speed, amount);
    }

    /**
     * @return The name of this particle effect
     */
    public String getName() {

        return this.name;
    }

    /**
     * Displays a particle effect which is only visible for the specified players
     *
     * @param center  Center location of the effect
     * @param offsetX Maximum distance particles can fly away from the center on the x-axis
     * @param offsetY Maximum distance particles can fly away from the center on the y-axis
     * @param offsetZ Maximum distance particles can fly away from the center on the z-axis
     * @param speed   Display speed of the particles
     * @param amount  Amount of particles
     * @param players Receivers of the effect
     * @see sendPacket
     * @see instantiatePacket
     */
    public void display(Location center, float offsetX, float offsetY, float offsetZ, float speed, int amount, Player... players) {

        sendPacket(Arrays.asList(players), instantiatePacket(name, center, offsetX, offsetY, offsetZ, speed, amount));
    }

    /**
     * Displays a particle effect which is only visible for all players within a certain range in the world of @param center
     *
     * @param center  Center location of the effect
     * @param range   Range of the visibility
     * @param offsetX Maximum distance particles can fly away from the center on the x-axis
     * @param offsetY Maximum distance particles can fly away from the center on the y-axis
     * @param offsetZ Maximum distance particles can fly away from the center on the z-axis
     * @param speed   Display speed of the particles
     * @param amount  Amount of particles
     * @throws IllegalArgumentException if the range is higher than 20
     * @see sendPacket
     * @see instantiatePacket
     */
    public void display(Location center, double range, float offsetX, float offsetY, float offsetZ, float speed, int amount) {

        if (range > MAX_RANGE) {
            throw new IllegalArgumentException("Range cannot exceed the maximum value of 16");
        }
        sendPacket(getPlayers(center, range), instantiatePacket(name, center, offsetX, offsetY, offsetZ, speed, amount));
    }

    /**
     * Displays a particle effect which is only visible for all players within a range of 20 in the world of @param center
     *
     * @param center  Center location of the effect
     * @param offsetX Maximum distance particles can fly away from the center on the x-axis
     * @param offsetY Maximum distance particles can fly away from the center on the y-axis
     * @param offsetZ Maximum distance particles can fly away from the center on the z-axis
     * @param speed   Display speed of the particles
     * @param amount  Amount of particles
     * @see display(org.bukkit.Location, double, float, float, float, float, int)
     */
    public void display(Location center, float offsetX, float offsetY, float offsetZ, float speed, int amount) {

        display(center, MAX_RANGE, offsetX, offsetY, offsetZ, speed, amount);
    }

    /**
     * Represents a runtime exception that can be thrown upon packet instantiation
     */
    @SuppressWarnings("SameParameterValue")
    private static final class PacketInstantiationException extends RuntimeException {

        private static final long serialVersionUID = 3203085387160737484L;

        /**
         * @param message Message that will be logged
         */
        public PacketInstantiationException(String message) {

            super(message);
        }

        /**
         * @param message Message that will be logged
         * @param cause   Cause of the exception
         */
        public PacketInstantiationException(String message, Throwable cause) {

            super(message, cause);
        }
    }

    /**
     * Represents a runtime exception that can be thrown upon packet sending
     */
    private static final class PacketSendingException extends RuntimeException {

        private static final long serialVersionUID = 3203085387160737484L;

        /**
         * @param message Message that will be logged
         * @param cause   Cause of the exception
         */
        public PacketSendingException(String message, Throwable cause) {

            super(message, cause);
        }
    }
}


