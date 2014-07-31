package com.relicum.ipsum;

import com.relicum.ipsum.Items.MetaType;
import com.relicum.ipsum.Items.SimpleItemFactory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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


    /**
     * On enable.
     */
    @SuppressWarnings({"unchecked", "varargs"})
    @Override
    public void onEnable() {

        instance = this;
        getServer().getPluginManager().registerEvents(this, this);

        this.item = new SimpleItemFactory().getItemBuilder(Material.BAKED_POTATO, 1, MetaType.ITEM_META)
                .setDisplayName("&6Chris Lutte")
                .setLore("&aWe have one line of lore")
                .addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 20)
                .addUnsafeEnchantment(Enchantment.KNOCKBACK, 20)
                .build();

        System.out.println(item.toString());


    }

    @EventHandler
    public void pj(PlayerJoinEvent e) {
        if (!e.getPlayer().getInventory().containsAtLeast(this.item, 1)) {
            e.getPlayer().getInventory().setItem(0, this.item.clone());
        }

        e.getPlayer().setCanPickupItems(false);
        e.getPlayer().saveData();

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

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Ipsum getInstance() {

        return instance;

    }
}
