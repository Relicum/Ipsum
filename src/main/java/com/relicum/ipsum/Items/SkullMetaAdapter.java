package com.relicum.ipsum.Items;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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

    /**
     * Instantiates a new SkullMetaAdapter builder.
     *
     * @param mat  the {@link org.bukkit.Material} the item is made from.
     * @param i    the amount of items in the final ItemStack
     * @param type
     */
    public SkullMetaAdapter(Material mat, int i, MetaType type) {
        this.material = mat;
        this.metaType = type;
        this.amount = i;

        this.setItemMeta();


    }

    /**
     * Instantiates a new SkullMetaAdapter builder.
     *
     * @param mat  the {@link org.bukkit.Material} the item is made from.
     * @param type the {@link com.relicum.ipsum.Items.MetaType} type of meta data the item requires
     */
    public SkullMetaAdapter(Material mat, MetaType type) {

        this.material = mat;
        this.metaType = type;

        this.setItemMeta();


    }


    /**
     * Sets item meta.
     */
    @Override
    public void setItemMeta() {
        meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
    }


    public SkullMeta getItemMeta() {

        return this.meta;
    }


    /**
     * Gets owner.
     *
     * @return the owner
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

    @Override
    public ItemStack build() {
        System.out.println("Mat is " + getMaterial().name() + " and amount is " + getAmount());
        setStack(new ItemStack(getMaterial(), 1, (byte) 3));

        // if((Short)getDurability() != null){
        //      getStack().setDurability(getDurability());
        // }

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


}
