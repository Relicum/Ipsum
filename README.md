Ipsum Gaming Commons
====

Providing All you need to create mini games and plugins
----

```
Currently working on Item generation.
```

Example of building a named Player Skull. Easy !!
---

```java
    ItemStack stack = SimpleItemFactory.getSkullBuilder(1, SkullType.PLAYER)
                    .setOwner("Relicum")
                    .build();
```

Example of building a blue leather chest plate. Easy !!
---
Includes custom display name, custom lore and adding an Enchantment.

```java
    ItemStack stack = SimpleItemFactory.getColorArmorBuilder(Material.LEATHER_CHESTPLATE,1)
                .setColor(Color.BLUE)
                .setDisplayName("&6Rels Blue Chest")
                .setLore("&aRelicums Chest Plate")
                .addUnsafeEnchantment(Enchant.builder().enchantment(Enchantment.PROTECTION_ENVIRONMENTAL).level(3).force(true).build())
                .build();
```

Designed and Built by Relicum
-----

`Build for Bukkit and Spigot 1.7.10-R0.1`

``This will be built using the Prototyping method, build them quick and build them often.
What this means is I will code parts release, play test, listen to feedback and carry on.``
