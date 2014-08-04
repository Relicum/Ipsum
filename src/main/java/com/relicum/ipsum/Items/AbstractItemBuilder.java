package com.relicum.ipsum.Items;

import lombok.ToString;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: AbstractItemBuilder.java Created: 02 August 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
@ToString
public abstract class AbstractItemBuilder<T extends AbstractItemBuilder<T>> implements GenericItem {

    protected Material material;

    protected ItemStack stack;

    protected int amount = 1;

    protected short durability;

    protected String displayName;

    protected List<String> lore = new ArrayList<String>();

    protected MaterialData materialData;

    protected List<Enchant> enchantments = new ArrayList<Enchant>();

    protected MetaType metaType;


    /**
     * Instantiates a new Item builder.
     *
     * @param mat  the {@link org.bukkit.Material} the item is made from.
     * @param type the {@link com.relicum.ipsum.Items.MetaType} type of meta data the item requires
     */
    public AbstractItemBuilder(Material mat, MetaType type) {
        Validate.notNull(mat, "Item material can not be null");
        this.material = mat;
        this.metaType = type;
    }


    /**
     * Instantiates a new Item Builder
     *
     * @param mat the {@link org.bukkit.Material} of the Item
     * @param amount the amount of items in the stack
     * @param type the Meta type of the item {@link com.relicum.ipsum.Items.MetaType} provides the options.
     */
    public AbstractItemBuilder(Material mat, int amount, MetaType type) {
        Validate.notNull(mat, "Item material can not be null");
        Validate.notNull(amount, "Item amount can not be null");

        this.metaType = type;
        this.material = mat;
        this.amount = amount;

    }


    /**
     * Sets item meta that this item will use
     */
    protected abstract void setItemMeta();

    /**
     * Build item stack.
     *
     * @return the item stack
     */
    public abstract ItemStack build();


    /**
     * Sets material.
     *
     * @param material the material
     * @return the instance of itself so methods can be chained
     */
    @Override
    public T setMaterial(Material material) {
        this.material = material;
        return (T) this;
    }

    /**
     * Sets amount. This is the number of Items the {@link org.bukkit.inventory.ItemStack} contains.
     *
     * @param i the number of items
     * @return the instance of itself so methods can be chained
     */

    public T setAmount(int i) {
        this.amount = i;
        return (T) this;
    }


    /**
     * Sets durability.
     *
     * @param durability the durability
     * @return the instance of itself so methods can be chained
     */
    @Override
    public T setDurability(Short durability) {
        Validate.notNull(durability, "Item durability can not be null");
        this.durability = durability;
        return (T) this;
    }

    /**
     * Sets display name.
     *
     * @return the instance of itself so methods can be chained
     */
    @Override
    public T setDisplayName(String name) {
        Validate.notNull(name, "The displayname can not be null");
        this.displayName = name;
        return (T) this;
    }

    /**
     * Sets item lores.
     *
     * @param list the list
     * @return the instance of itself so methods can be chained
     */
    @Override
    public T setItemLores(List<String> list) {
        Validate.notNull(list, "Item Lores list can not be null");

        this.lore.addAll(list);
        return (T) this;
    }

    /**
     * Sets a single line to item lore.
     *
     * @param line the line
     * @return the instance of itself so methods can be chained
     */
    @Override
    public T setLore(String line) {
        Validate.notNull(line, "Item Lores line can not be null");
        this.lore.add(line);
        return (T) this;
    }


    /**
     * Sets material data.
     *
     * @param materialData the material data
     * @return the instance of itself so methods can be chained
     */
    @Override
    public T setMaterialData(MaterialData materialData) {
        Validate.notNull(materialData, "MaterialData can not be null");
        this.materialData = materialData;
        return (T) this;
    }


    /**
     * Add enchantment.
     *
     * @param enchant the enchantment
     * @return the instance of itself so methods can be chained
     */
    @Override
    public T addEnchantment(Enchant enchant) {
        Validate.notNull(enchant, "Enchant can not be null");

        enchantments.add(enchant);
        return (T) this;
    }


    /**
     * Add unsafe enchantment.
     *
     * @param enchant the enchantment
     * @return the instance of itself so methods can be chained
     */
    @Override
    public T addUnsafeEnchantment(Enchant enchant) {

        enchantments.add(enchant);
        return (T) this;
    }


    public MetaType getMetaType() {
        return metaType;
    }


    /**
     * Gets amount.
     *
     * @return Value of amount.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Gets stack.
     *
     * @return Value of stack.
     */
    public ItemStack getStack() {
        return stack;
    }

    /**
     * Gets lore.
     *
     * @return Value of lore.
     */
    public List<String> getLore() {
        return lore;
    }

    /**
     * Gets displayName.
     *
     * @return Value of displayName.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets enchantments.
     *
     * @return Value of enchantments.
     */
    public List<Enchant> getEnchantments() {
        return enchantments;
    }

    /**
     * Gets durability.
     *
     * @return Value of durability.
     */
    public short getDurability() {
        return durability;
    }

    /**
     * Gets materialData.
     *
     * @return Value of materialData.
     */
    public MaterialData getMaterialData() {
        return materialData;
    }

    /**
     * Gets material.
     *
     * @return Value of material.
     */
    public Material getMaterial() {
        return material;
    }


    /**
     * Sets new stack.
     *
     * @param stack New value of stack.
     */
    public void setStack(ItemStack stack) {
        this.stack = stack;
    }


    /**
     * Sets new metaType.
     *
     * @param metaType New value of metaType.
     */
    public void setMetaType(MetaType metaType) {
        this.metaType = metaType;
    }

}