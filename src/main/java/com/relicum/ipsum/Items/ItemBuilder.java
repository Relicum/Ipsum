package com.relicum.ipsum.Items;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.List;

/**
 * Base ItemBuilder uses standard ItemMeta
 */
@AllArgsConstructor
public class ItemBuilder implements GenericItem {

    private Material material;

    private ItemStack stack;

    private int amount = 1;

    private short durability = -999;

    private ItemMeta meta;

    private String displayName;

    private List<String> lore = new ArrayList<>();

    private MaterialData materialData;

    private List<Enchant> enchantments = new ArrayList<>();

    private MetaType metaType;

    /**
     * Instantiates a new Item builder.
     */
    public ItemBuilder() {
    }

    /**
     * Instantiates a new Item builder.
     *
     * @param mat  the {@link org.bukkit.Material} the item is made from.
     * @param type the {@link com.relicum.ipsum.Items.MetaType} type of meta data the item requires
     */
    public ItemBuilder(Material mat, MetaType type) {
        Validate.notNull(mat, "Item material can not be null");
        this.material = mat;
        System.out.println(" Ampempting to infect MetaAdapter");
        this.metaType = type;

        if (type.equals(MetaType.ITEM_META))
            this.setItemMeta();


    }

    /**
     * Instantiates a new Item builder.
     *
     * @param mat the {@link org.bukkit.Material} the item is made from.
     * @param i   the amount of items in the final ItemStack
     */
    public ItemBuilder(Material mat, int i, MetaType type) {
        Validate.notNull(mat, "Item material can not be null");
        Validate.notNull(i, "Item amount can not be null");

        this.metaType = type;
        this.material = mat;
        this.amount = i;
        if (type.equals(MetaType.ITEM_META))
            this.setItemMeta();


    }

    protected void setItemMeta() {
        this.meta = Bukkit.getItemFactory().getItemMeta(material);
    }

    /**
     * Sets material.
     *
     * @param material the material
     * @return the instance of itself so methods can be chained
     */
    @Override
    public ItemBuilder setMaterial(Material material) {
        Validate.notNull(material, "Item material can not be null");
        this.material = material;


        return this;
    }

    /**
     * Sets amount.
     *
     * @param i the i
     * @return the instance of itself so methods can be chained
     */
    @Override
    public ItemBuilder setAmount(int i) {
        Validate.notNull(i, "Item amount can not be null");
        this.amount = i;
        return this;
    }

    /**
     * Sets durability.
     *
     * @param durability the durability
     * @return the instance of itself so methods can be chained
     */
    @Override
    public ItemBuilder setDurability(Short durability) {
        Validate.notNull(durability, "Item durability can not be null");
        this.durability = durability;
        return this;
    }

    /**
     * Sets display name.
     *
     * @return the instance of itself so methods can be chained
     */
    @Override
    public ItemBuilder setDisplayName(String name) {
        Validate.notNull(name, "The displayname can not be null");
        this.displayName = name;
        return this;
    }

    /**
     * Sets item lores.
     *
     * @param list the list
     * @return the instance of itself so methods can be chained
     */
    @Override
    public ItemBuilder setItemLores(List<String> list) {
        org.apache.commons.lang.Validate.notNull(list, "Item Lores list can not be null");

        this.lore.addAll(list);
        return this;
    }

    /**
     * Sets a single line to item lore.
     *
     * @param line the line
     * @return the instance of itself so methods can be chained
     */
    @Override
    public ItemBuilder setLore(String line) {
        Validate.notNull(line, "Item Lores line can not be null");
        this.lore.add(line);
        return this;
    }


    /**
     * Sets material data.
     *
     * @param materialData the material data
     * @return the instance of itself so methods can be chained
     */
    @Override
    public ItemBuilder setMaterialData(MaterialData materialData) {
        Validate.notNull(materialData, "MaterialData can not be null");
        this.materialData = materialData;
        return this;
    }


    /**
     * Add enchantment.
     *
     * @param ench  the ench
     * @param level the level
     * @return the instance of itself so methods can be chained
     */
    @Override
    public ItemBuilder addEnchantment(Enchantment ench, int level) {
        Validate.notNull(ench, "Enchant can not be null");
        Validate.notNull(level, "Enchant level can not be null");

        enchantments.add(new Enchant(ench, level, false));
        return this;
    }


    /**
     * Add unsafe enchantment.
     *
     * @param ench  the ench
     * @param level the level
     * @return the instance of itself so methods can be chained
     */
    @Override
    public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level) {

        enchantments.add(new Enchant(ench, level, true));
        return this;
    }

    public ItemMeta get() {
        return null;
    }

    /**
     * Build item stack.
     *
     * @return the item stack
     */
    @Override
    public ItemStack build() {
        System.out.println("Mat is " + this.material.name() + " and amount is " + this.amount);
        this.stack = new ItemStack(this.material, this.amount, (short) 3);


        if (displayName != null)
            getItemMeta().setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));

        if (lore != null)
            getItemMeta().setLore(lore);

        if (enchantments != null) {
            for (Enchant enchant : enchantments) {

                getItemMeta().addEnchant(enchant.getEnchantment(), enchant.getLevel(), true);

            }
        }

        this.stack.setItemMeta(getItemMeta());

        return this.stack;
    }

    public ItemMeta getItemMeta() {

        return this.meta;
    }

    public MetaType getMetaType() {
        return metaType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ItemBuilder{");
        sb.append("material=").append(material);
        sb.append(", stack=").append(stack);
        sb.append(", amount=").append(amount);
        sb.append(", durability=").append(durability);
        sb.append(", meta=").append(meta);
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", lore=").append(lore.toString());
        sb.append(", materialData=").append(materialData);
        sb.append(", enchantments=").append(enchantments);
        sb.append(", metaType=").append(metaType);
        sb.append('}');
        return sb.toString();
    }
}
