Ipsum Gaming Commons 1.0.51-SNAPSHOT
===

**Built using the best Java IDE around**

[![Image of JetBrains](https://www.jetbrains.com/idea/docs/logo_intellij_idea.png)](https://www.jetbrains.com/idea/)


Providing All you need to create mini games and plugins
----

```
Must be used with the latest 1.8 Spigot
Compiled in Java 1.8 to make use of all the new features.
This means it will only work if you compile any plugins made using Ipsum with Java 1.8
```

```
Currently NOT in a suitable production state.
```

Example of creating new void world. Easy !!
---

```java
    WorldMaker worldMaker = new WorldMaker(this);
    WorldCreator worldCreator = worldMaker.getWorldCreator("Relicum");
    worldCreator.generator("LuckyCrates");
    World world = worldCreator.createWorld();
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

External Contributors
===
**This plugin includes the sk89q-command-framework,
all copyrights belong @Sk89q and is licensed under GPL3**
>The sk89q-command-framework source can be located at https://github.com/OvercastNetwork/sk89q-command-framework 

**MassiveCraft development**
Some of the massiveCore inventory serialisation is included
Many thank to them details on them can be found at
>www.MassiveCraft.com

**MD_5s Chat API is included all copyrights belong to him.
It has also been enhanced by dmulloy2 and are licensed under GPL3.**

--------


`Build for Bukkit and Spigot 1.8`

`This will be built using the Prototyping method, build them quick and build them often.
What this means is I will code parts release, play test, listen to feedback and carry on.`


