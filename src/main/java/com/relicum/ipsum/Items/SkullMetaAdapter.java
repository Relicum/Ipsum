package com.relicum.ipsum.Items;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * SkullMetaAdapter is used to add in the options in SkullMeta directly into the AbstractItemBuilder.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class SkullMetaAdapter extends AbstractItemBuilder<SkullMetaAdapter> {

    private SkullMeta meta;
    private SkullType skullType;

    /**
     * Instantiates a new SkullMetaAdapter builder.
     *
     * @param material the {@link org.bukkit.Material} the item is made from.
     * @param amount   the amount of items in the final ItemStack
     * @param type     the type of skull {@link org.bukkit.SkullType}
     */
    public SkullMetaAdapter(Material material, int amount, SkullType type) {
        super(material, amount, MetaType.SKULL_ITEM);
        skullType = type;
        this.setItemMeta();


    }

    /**
     * Instantiates a new SkullMetaAdapter builder.
     *
     * @param material the {@link org.bukkit.Material} the item is made from.
     * @param type     the type of skull {@link org.bukkit.SkullType}
     */
    public SkullMetaAdapter(Material material, SkullType type) {
        super(material, MetaType.SKULL_ITEM);
        skullType = type;
        this.setItemMeta();
    }


    /**
     * Sets item meta to {@link org.bukkit.inventory.meta.SkullMeta}
     */
    @Override
    protected void setItemMeta() {
        meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
    }


    /**
     * Gets  skull item meta.
     *
     * @return the item meta as {@link org.bukkit.inventory.meta.SkullMeta}
     */
    public SkullMeta getItemMeta() {

        return this.meta;
    }


    /**
     * Gets skull owner.
     *
     * @return the owner of the skull
     */
    public String getOwner() {
        return meta.getOwner();
    }

    /**
     * Sets the owner of the skull.
     *
     * @param owner the owner
     * @return the instance of itself so methods can be chained
     * @throws Exception if there was a problem setting the skull owner
     */
    public SkullMetaAdapter setOwner(String owner) throws Exception {
        Validate.notNull(owner, "Owner can not be null when setting the owner");
        if (!meta.setOwner(owner))
            throw new Exception("Error while trying to set Skull items owner");
        return this;
    }

    /**
     * Checks to see if the skull has an owner.
     *
     * @return true if the skull has an owner
     */
    public boolean hasOwner() {

        return getItemMeta().hasOwner();
    }

    /**
     * Build Skull item stack.
     *
     * @return the {@link org.bukkit.inventory.ItemStack}
     */
    @Override
    public ItemStack build() {
        System.out.println("Mat is " + getMaterial().name() + " and amount is " + getAmount());

        setStack(new ItemStack(getMaterial(), getAmount(), (byte) skullType.ordinal()));


        //  if ((Short) getDurability() != null) {
        //
        //      if (getDurability() != 0) {
        //          getStack().setDurability(getDurability());
        //      }
        //  }

        if (getDisplayName() != null)
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getDisplayName()));

        if (getLore() != null)
            meta.setLore(getLore());

        if (getEnchantments() != null) {
            for (Enchant enchant : getEnchantments()) {

                meta.addEnchant(enchant.enchantment(), enchant.level(), enchant.force());

            }
        }


        getStack().setItemMeta(meta);

        return getStack();
    }


    /**
     * Gets skullType.
     *
     * @return Value of skullType.
     */
    public SkullType getSkullType() {
        return skullType;
    }
}
