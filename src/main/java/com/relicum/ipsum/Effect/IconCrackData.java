package com.relicum.ipsum.Effect;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Arrays;

/**
 * This class is part of the ParticleEffect library and follows the same usage conditions
 *
 * @author DarkBlade12
 */
public class IconCrackData extends ParticleEffectData {
    private static final String FORMAT = "\\d+@\\d+(@\\d+(\\.\\d+)?){3}@\\d+@\\d+(\\.\\d+)?";
    private final int id;
    private final byte data;
    private final float speed;

    public IconCrackData(int id, byte data, float offsetX, float offsetY, float offsetZ, int amount, float speed) {
        super(offsetX, offsetY, offsetZ, amount);
        this.id = id;
        this.data = data;
        this.speed = speed;
    }

    public static IconCrackData fromString(String s) throws IllegalArgumentException {
        if (!s.matches(FORMAT))
            throw new IllegalArgumentException("Invalid format");
        String[] p = s.split("@");
        return new IconCrackData(Integer.parseInt(p[0]), Byte.parseByte(p[1]), Float.parseFloat(p[2]), Float.parseFloat(p[3]), Float.parseFloat(p[4]), Integer.parseInt(p[5]), Float.parseFloat(p[6]));
    }

    @Override
    public void displayEffect(Location center, Player... players) {
        ParticleEffect.displayIconCrack(id, data, offsetX, offsetY, offsetZ, speed, amount, center, Arrays.asList(players));
    }

    @Override
    public void displayEffect(Location center, double range) {
        ParticleEffect.displayIconCrack(id, data, offsetX, offsetY, offsetZ, speed, amount, center, range);
    }

    public int getId() {
        return this.id;
    }

    public float getSpeed() {
        return this.speed;
    }

    @Override
    public String toString() {
        return id + "@" + super.toString() + "@" + speed;
    }
}
