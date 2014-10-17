/*
 * Ipsum is a rapid development API for Minecraft, developer by Relicum
 * Copyright (C) 2014.  Chris Lutte
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.relicum.ipsum.io.Adapter;

import com.google.gson.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;

import java.lang.reflect.Type;
import java.util.*;

/**
 * ItemStackAdapter GSON serializer/deserializer for the Bukkit ItemStack.
 *
 * @author Relicum Massivecraft
 * @version 0.0.1
 */
@SuppressWarnings("deprecation")
public class ItemStackAdapter implements JsonDeserializer<ItemStack>, JsonSerializer<ItemStack> {

    //--------------------------------//
    // FIELD NAME CONSTANTS
    //-------------------------------//

    public static final String ID = "id";
    public static final String COUNT = "count";
    public static final String DAMAGE = "damage";

    public static final String NAME = "name";
    public static final String LORE = "lore";
    public static final String ENCHANTS = "enchants";
    public static final String REPAIRCOST = "repaircost";

    public static final String BOOK_TITLE = "title";
    public static final String BOOK_AUTHOR = "author";
    public static final String BOOK_PAGES = "pages";

    public static final String LEATHER_ARMOR_COLOR = "color";

    public static final String MAP_SCALING = "scaling";

    public static final String SKULL_OWNER = "skull";

    // We renamed "effects" to "potion-effects".
    public static final String POTION_EFFECTS_OLD = "effects";
    public static final String POTION_EFFECTS = "potion-effects";

    public static final String FIREWORK_EFFECT = "firework-effect";
    public static final String FIREWORK_EFFECTS = "firework-effects";
    public static final String FIREWORK_FLIGHT = "firework-flight";

    public static final String STORED_ENCHANTS = "stored-enchants";


    public static final int DEFAULT_ID;
    public static final int DEFAULT_COUNT;
    public static final int DEFAULT_DAMAGE;

    static {

        ItemStack stack = createStack();
        DEFAULT_ID = stack.getTypeId();
        DEFAULT_COUNT = stack.getAmount();
        DEFAULT_DAMAGE = stack.getDurability();
    }

    public static Map<String, Object> serialisedStack;


    @Override
    public JsonElement serialize(ItemStack src, Type typeOfSrc, JsonSerializationContext context) {
        return erialize(src);
    }

    @Override
    public ItemStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return erialize(json);
    }


    public JsonObject erialize(ItemStack stack) {

        if (stack == null) return null;
        if (stack.getTypeId() == 0) return null;
        if (stack.getAmount() == 0) return null;

        JsonObject json = new JsonObject();

        transferAll(stack, json, true);

        return json;

    }

    public ItemStack erialize(JsonElement jsonElement) {

        if (jsonElement == null) return null;

        if (!jsonElement.isJsonObject()) return null;

        JsonObject json = jsonElement.getAsJsonObject();

        ItemStack stack = createStack();

        transferAll(stack, json, false);

        return stack;
    }


    public static ItemStack createStack() {
        return new ItemStack(0);

    }

    public static void transferAll(ItemStack stack, JsonObject json, boolean stack2json) {
        transferStandard(stack, json, stack2json);

        ItemMeta meta = stack.getItemMeta();
        transferMeta(meta, json, stack2json);

        if (!stack2json) {
            stack.setItemMeta(meta);
        }
    }


    public static void transferStandard(ItemStack stack, JsonObject json, boolean stack2json) {

        transferId(stack, json, stack2json);
        transferCount(stack, json, stack2json);
        transferDamage(stack, json, stack2json);


    }


    public static void transferId(ItemStack stack, JsonObject json, boolean stack2json) {

        if (stack2json) {

            int id = stack.getTypeId();
            if (id == DEFAULT_ID) return;
            json.addProperty(ID, id);

        } else {

            JsonElement element = json.get(ID);
            if (element == null) return;
            stack.setTypeId(element.getAsInt());
        }


    }

    public static void transferCount(ItemStack stack, JsonObject json, boolean stack2json) {

        if (stack2json) {
            int count = stack.getAmount();
            if (count == DEFAULT_COUNT) return;
            json.addProperty(COUNT, count);
        } else {
            JsonElement element = json.get(COUNT);
            if (element == null) return;
            stack.setAmount(element.getAsInt());
        }

    }

    public static void transferDamage(ItemStack stack, JsonObject json, boolean stack2json) {

        if (stack2json) {
            int damage = stack.getDurability();
            if (damage == DEFAULT_DAMAGE) return;
            json.addProperty(DAMAGE, damage);
        } else {

            JsonElement element = json.get(DAMAGE);
            if (element == null) return;
            stack.setDurability(element.getAsShort());
        }

    }


    //-------------------//
    //Item Meta
    //------------------//

    public static void transferMeta(ItemMeta meta, JsonObject json, boolean meta2json) {
        transferUnspecificMeta(meta, json, meta2json);
        transferSpecificMeta(meta, json, meta2json);
    }


    public static void transferUnspecificMeta(ItemMeta meta, JsonObject json, boolean meta2json) {
        transferName(meta, json, meta2json);
        transferLore(meta, json, meta2json);
        transferEnchants(meta, json, meta2json);
        transferRepaircost(meta, json, meta2json);
    }

    // -------------------------------------------- //
    // UNSPECIFIC META: NAME
    // -------------------------------------------- //

    public static void transferName(ItemMeta meta, JsonObject json, boolean meta2json) {
        if (meta2json) {
            if (!meta.hasDisplayName()) return;
            json.addProperty(NAME, meta.getDisplayName());
        } else {
            JsonElement element = json.get(NAME);
            if (element == null) return;
            meta.setDisplayName(element.getAsString());
        }
    }

    // -------------------------------------------- //
    // UNSPECIFIC META: LORE
    // -------------------------------------------- //

    public static void transferLore(ItemMeta meta, JsonObject json, boolean meta2json) {
        if (meta2json) {
            if (!meta.hasLore()) return;
            json.add(LORE, convertStringList(meta.getLore()));
        } else {
            JsonElement element = json.get(LORE);
            if (element == null) return;
            meta.setLore(convertStringList(element));
        }
    }

    // -------------------------------------------- //
    // UNSPECIFIC META: ENCHANTS
    // -------------------------------------------- //

    public static void transferEnchants(ItemMeta meta, JsonObject json, boolean meta2json) {
        if (meta2json) {
            if (!meta.hasEnchants()) return;
            json.add(ENCHANTS, convertEnchantLevelMap(meta.getEnchants()));
        } else {
            JsonElement element = json.get(ENCHANTS);
            if (element == null) return;
            for (Map.Entry<Enchantment, Integer> entry : convertEnchantLevelMap(element).entrySet()) {
                meta.addEnchant(entry.getKey(), entry.getValue(), true);
            }
        }
    }

    // -------------------------------------------- //
    // UNSPECIFIC META: REPAIRCOST
    // -------------------------------------------- //

    public static void transferRepaircost(ItemMeta meta, JsonObject json, boolean meta2json) {
        if (!(meta instanceof Repairable)) return;
        Repairable repairable = (Repairable) meta;

        if (meta2json) {
            if (!repairable.hasRepairCost()) return;
            json.addProperty(REPAIRCOST, repairable.getRepairCost());
        } else {
            JsonElement element = json.get(REPAIRCOST);
            if (element == null) return;

            repairable.setRepairCost(element.getAsInt());
        }
    }


    // -------------------------------------------- //
    // SPECIFIC META
    // -------------------------------------------- //
    private static void transferSpecificMeta(ItemMeta meta, JsonObject json, boolean meta2json) {

    }


    // -------------------------------------------- //
    // MINI UTILS
    // -------------------------------------------- //

    public static JsonArray convertStringList(Collection<String> strings) {
        JsonArray ret = new JsonArray();
        for (String string : strings) {
            ret.add(new JsonPrimitive(string));
        }
        return ret;
    }

    public static List<String> convertStringList(JsonElement jsonElement) {
        JsonArray array = jsonElement.getAsJsonArray();
        List<String> ret = new ArrayList<>();

        Iterator<JsonElement> iter = array.iterator();
        while (iter.hasNext()) {
            JsonElement element = iter.next();
            ret.add(element.getAsString());
        }

        return ret;
    }

    // EnchantLevelMap
    public static JsonObject convertEnchantLevelMap(Map<Enchantment, Integer> enchantLevelMap) {
        JsonObject ret = new JsonObject();
        for (Map.Entry<Enchantment, Integer> entry : enchantLevelMap.entrySet()) {
            ret.addProperty(String.valueOf(entry.getKey().getId()), entry.getValue());
        }
        return ret;
    }

    public static Map<Enchantment, Integer> convertEnchantLevelMap(JsonElement jsonElement) {
        JsonObject json = jsonElement.getAsJsonObject();
        Map<Enchantment, Integer> ret = new HashMap<Enchantment, Integer>();
        for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
            int id = Integer.valueOf(entry.getKey());
            Enchantment ench = Enchantment.getById(id);
            int lvl = entry.getValue().getAsInt();
            ret.put(ench, lvl);
        }
        return ret;
    }


    public static ItemStackAdapter i = new ItemStackAdapter();

    public static ItemStackAdapter get() {
        return i;
    }


}
