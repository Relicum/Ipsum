package com.relicum.ipsum.Items;

import org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * SkullMetaAdapter is used to add in the options in SkullMeta directly into the ItemBuilder.
 *
 * @author Relicum
 * @version 0.0.1
 */
public class SkullMetaAdapter extends ItemBuilder implements Builder {

    public SkullMeta itemMeta;

    /**
     * Instantiates a new SkullMetaAdapter builder.
     *
     * @param mat  the {@link org.bukkit.Material} the item is made from.
     * @param i    the amount of items in the final ItemStack
     * @param type
     */
    public SkullMetaAdapter(Material mat, int i, MetaType type) {
        super(mat, i, type);
        setItemMeta();

    }

    /**
     * Instantiates a new SkullMetaAdapter builder.
     *
     * @param mat  the {@link org.bukkit.Material} the item is made from.
     * @param type the {@link com.relicum.ipsum.Items.MetaType} type of meta data the item requires
     */
    public SkullMetaAdapter(Material mat, MetaType type) {
        super(mat, type);
        this.setItemMeta();
    }

    @Override
    public void setItemMeta() {
        this.itemMeta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
    }

    @Override
    public SkullMeta getItemMeta() {

        return this.itemMeta;
    }

    public String getOwner() {
        return itemMeta.getOwner();
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
     * Sets the owner of the skull.
     *
     * @param owner the owner
     * @return the instance of itself so methods can be chained
     * @throws Exception if there was a problem setting the skull owner
     */
    public ItemBuilder setOwner(String owner) throws Exception {
        Validate.notNull(owner, "Owner can not be null when setting the owner");
        if (!getItemMeta().setOwner(owner))
            throw new Exception("Error while trying to set Skull items owner");
        return this;
    }


}
