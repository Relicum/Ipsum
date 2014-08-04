package com.relicum.ipsum;

import com.relicum.ipsum.Items.Enchant;
import com.relicum.ipsum.Items.MetaType;
import com.relicum.ipsum.Items.SimpleItemFactory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * The type Ipsum.
 */
public class Ipsum extends JavaPlugin implements Listener {


    private static Ipsum instance;
    private ItemStack item;
    private ItemStack item2;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Ipsum getInstance() {

        return instance;

    }

    /**
     * On enable.
     */
    @SuppressWarnings({"unchecked", "varargs"})
    @Override
    public void onEnable() {

        instance = this;
        getServer().getPluginManager().registerEvents(this, this);

        this.item = SimpleItemFactory.getItemBuilder(Material.BAKED_POTATO, 1, MetaType.ITEM_META)
                .setLore("&aWe have one line of lore")
                .setDisplayName("&6Chris Lutte")
                .addUnsafeEnchantment(Enchant.builder().enchantment(Enchantment.DAMAGE_ALL).level(20).force(true).build())
                .addUnsafeEnchantment(Enchant.builder().enchantment(Enchantment.KNOCKBACK).level(20).force(true).build())
                .build();

        System.out.println(item.toString());

        try {
            this.item2 = SimpleItemFactory.getSkullBuilder(1, SkullType.PLAYER)
                    .setOwner("Relicum")
                    .addEnchantment(Enchant.builder().enchantment(Enchantment.DURABILITY).level(3).force(false).build())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(item2.toString());
    }

    @EventHandler
    public void pj(PlayerJoinEvent e) {

        e.getPlayer().getInventory().setItem(0, this.item.clone());
        e.getPlayer().getInventory().setItem(1, this.item2.clone());


    }

    @EventHandler
    public void bp(BlockPlaceEvent e) {
        if (e.getBlockPlaced().getType().name().startsWith("SKULL")) {
            e.getPlayer().sendMessage(ChatColor.AQUA + "Block is a " + e.getBlockPlaced().getType().name());
            return;
        }
    }

    /**
     * On disable.
     */
    @Override
    public void onDisable() {


    }
}
