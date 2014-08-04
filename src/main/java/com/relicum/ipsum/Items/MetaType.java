package com.relicum.ipsum.Items;

/**
 * Enum of the different ItemMeta Types.
 * <p>Used with the item build to automatically have the correct Meta class based
 * on the Material you choose.</p>
 *
 * @author Relicum
 * @version 0.0.1
 */
public enum MetaType {
    ITEM_META("ItemMeta", true),
    WRITTEN_BOOK("BookMeta", false),
    ENCHANTED_BOOK("EnchantmentStorageMeta", false),
    FIREWORK_EFFECT("FireworkEffectMeta", false),
    FIREWORK("FireworkMeta", false),
    COLORED_ARMOR("LeatherArmorMeta", true),
    MAP("MapMeta", false),
    POTION("PotionMeta", false),
    SKULL_ITEM("SkullMeta", true),
    REPAIRABLE("Repairable", false);


    /**
     * The Old name.
     */
    private final String OldName;
    /**
     * Is this Meta been added in yet.
     */
    private final boolean Complete;

    private MetaType(String on, boolean com) {

        this.OldName = on;
        this.Complete = com;
    }

    /**
     * Gets old name.
     *
     * @return the old name
     */
    public String getOldName() {
        return OldName;

    }

    /**
     * Is complete.
     *
     * @return the boolean true and the Meta is available to use
     */
    public boolean isComplete() {
        return Complete;
    }


}
