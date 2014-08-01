package com.relicum.ipsum.Items;

import org.bukkit.enchantments.Enchantment;

/**
 * Very Simple class to build enchants to apply to items.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class Enchant {

    private final Enchantment enchantment;
    private final int level;
    private boolean force;

    @java.beans.ConstructorProperties({"enchantment", "level", "force"})
    public Enchant(Enchantment enchantment, int level, boolean force) {
        this.enchantment = enchantment;
        this.level = level;
        this.force = force;
    }

    public Enchantment getEnchantment() {
        return this.enchantment;
    }

    public int getLevel() {
        return this.level;
    }

    public boolean isForce() {
        return this.force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Enchant)) return false;
        final Enchant other = (Enchant) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$enchantment = this.enchantment;
        final Object other$enchantment = other.enchantment;
        if (this$enchantment == null ? other$enchantment != null : !this$enchantment.equals(other$enchantment))
            return false;
        if (this.level != other.level) return false;
        if (this.force != other.force) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $enchantment = this.enchantment;
        result = result * PRIME + ($enchantment == null ? 0 : $enchantment.hashCode());
        result = result * PRIME + this.level;
        result = ((result * PRIME) + (this.force ? 79 : 97));
        return result;
    }

    public boolean canEqual(Object other) {
        return other instanceof Enchant;
    }

    public String toString() {
        return "com.relicum.ipsum.Items.Enchant(enchantment=" + this.enchantment + ", level=" + this.level + ", force=" + this.force + ")";
    }
}
