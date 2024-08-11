# Changelog

### Version 1.21 - 2.1.0

- port to 1.21
- fix ostrich nest always dropping itself
- fix incorrect recipe for arrows from shark teeth (only a problem on fabric and neoforge)
- fix neoforge biome modifier, mobs now spawn naturally
- fix neoforge banana tag, monkey now accept the banana again
- fix monkey teleporting to player, even though they were ordered to sit
- elephant inventory can now be opened while riding (pressing the `open-inventory`-key)
- add `cherry_grove` to the default config of snails, raccoons and owls
- improve interaction with elephants
    - no automatic riding when equipping a saddle or chest
    - only consume temptation items when the elephants can fall in love (before: could eat infinite items without any
      effect)
- improved AI of baby enderdragon
    - can not take damage from own attacks any more (immune to own area effect clouds that do damage)

### Version 1.20.6 - 2.0.3

- port to 1.20.6
- fix missing subtitle for sound `livingthings:baby_ender_dragon.flap`

### Version 1.20.4 - 2.0.2

- port to 1.20.4
- add neoforge support

### Version 1.20.1 - 2.0.2

- fix game crashing by mantarays

### Version 1.20.1 - 2.0.1

- fix ostrich and elephant not moving
- fix not rendering items in hand of nether_knight
- slightly improved elephant model and animations
    - should fix the elephant tusk moving strangely
- remove unnecessary empty lines in patchouli book
- add *zh_cn* translation

### Version 1.20.1 - 2.0.0

- port to 1.20.1

### Version 1.19.4 - 2.0.0

- port to 1.19.4
- new config format
    - now json based
    - allow to change temptation items for mobs
- new textures for entities
    - penguin, crab, mantaray, shark, lion, flamingo
- slightly improved textures for entities
    - owl, giraffe, ostrich, monkey
- new ostrich nest texture
- ostrich eggs are now throwable
    - does half a heart of damage
    - does **not** spawn a baby ostrich on impact
- polar bears now hunt penguins
- add loot_table for seahorses
- add new mob `peacock`
    - can be tamed with wheat
    - can be found in jungles and savannas
    - will destroy crops

### Version 1.18.2 - 1.5.0

- port to 1.18.2
- new improved elephant texture
- new mob: `baby_ender_dragon`
    - can be hatched by placing a dragon egg on top of a purpur pillar
    - can be tamed with chorus fruits
- meat items are now actually declared as meat so they can be fed to wolves
- reduced falldamage for monkeys and koalas
- dropchance for the netherknight weapons are now changable through the config

### Version 1.18.1 - 1.4.1

- port to 1.18.1
- entity spawn rules now use data-tags to determine on which blocks the entities can spawn on
    - land entities use blocktags
    - swimming animals use fluidtags in which they can spawn in
    - format: `<mobname>_spawnable_on.json`
- reduced ostrich hitbox
- changed spawnrates for raccoon, snail and seahorses

### Version 1.17.1 - 1.4.0

- port to 1.17.1

### Version 1.16.5 - 1.4.0

- compress images
- compress sound files
- add new mobs
    - shroomie
    - seahorse (can be picked up with buckets)
- added new items
    - bucket of seahorse
    - raw lion meat
    - cooked lion meat
    - raw giraffe meat
    - cooked giraffe meat

### Version 1.16.5 - 1.3.2

- add nether knight entity

### Version 1.16.5 - 1.3.1

- added loot for snail/monkey/mantaray/kaola
- added more items
    - raw/cooked elephant meat

### Version 1.16.4 - 1.3.0

- added snail entity
    - will spawn in different variations
    - can be dyed
- added monkey entity
    - can be tamed
    - will "party" near jukeboxes
- retextured some item textures
    - crab
    - cooked_crab
    - crab_shell
    - shark_tooth
    - ostrich_egg
- renamed item *livingthings:raw_crab* to *livingthings:crab* to match vanilla namings
    - might produce some errors on the first world load with this version
    - will definitly delete all raw_crab items in chests/inventories etc.
- added raw and cooked ostrich meat
- added banana item
- added a new config for entity spawn biomes, which allows more customisation
- removed custom particles completely
- translation to *ru_ru* by [Intaria](https://github.com/Intaria) for version 1.2.3

### Version 1.16.4 - 1.2.3

- port to 1.16.4
- added koala entity
- rightclick mobs with the lexicon to open the corresponding page
- movement speed now changeable for all mobs through config
- temporarily disabling custom particles

### Version 1.16.3 - 1.2.2

- fix some server-side errors
- improve ancient helmet model

### Version 1.16.3 - 1.2.1

- added AncientBlaze entity
    - will drop it's helmet on death
    - can be created by placing a Jack o'Lantern on top of two glowstone blocks
- added Ancient Helmet
    - adds 2 minutes of Fire Resistance as Effect when worn
- fix Shark-Damage not changeable through the config

### Version 1.16.3 - 1.2.0

- translation to *pt_br* by [Mikeliro](https://github.com/Mikeliro)
- improved *de_de* translation

### Version 1.16.3 - 1.1.9

- adjust mob attributes like health/damage
- update lexicon item

### Version 1.16.3 - 1.1.8

- added raccoon entity
    - they destroy the ostrich egg in a nest
    - they destroy turtle eggs
- added owl entity
    - tamed owls will follow you
    - flying animal like parrots
    - can spawn in 3 different colors

### Version 1.16.3 - 1.1.7

- added mantaray entity
    - can spawn in 2 different colors and 4 different sizes
- compressed the sound files
- crabs can spawn in different sizes now
- fix the patchouli book glitching sometimes

### Version 1.16.3 - 1.1.6

- added crab entity
- added items:
    - raw crab
    - cooked crab
    - crab shell
- color and gender variants are now weighted-random and changeable in the config

### Version 1.16.3 - 1.1.5

- fix shark only spawning in two biomes
- ostrich babys are no longer rideable
- fix recipe for patchouli book (producing errors on startup if mod not installed)
- added some config comments

### Version 1.16.3 - 1.1.4

- port to 1.16.3
- extended config
    - health and damage now changeable
    - spawnbiomes for mobs can be modified
    - individual option for each mob if it can attack
- elephant ridable
    - they need to be tamed before
    - they can have an inventory when rightclicked with a chest before

### Version 1.16.2 - 1.1.3

- small bug fixes and improvements
    - wrong eye height for some mobs
    - shark not swimming sometimes
    - Ostrich Eggs now hatch a little bit faster

### Version 1.16.2 - 1.1.2

- added Flamingos

### Version 1.16.2 - 1.1.1

- Ostrichs are now rideable
    - if they are being ridden they just run in the direction you look
    - no saddle needed
- fixed typos in the language files
- tweaked penguin spawns
- added Patchouli support

### Version 1.16.2 - 1.1.0

- port to 1.16.2
- improved Mob-Attributes
- added Penguins & Ostrichs
- added sounds for Penguin / Elephant / Lion
- added basic config file (still WIP)
- added multiple Mob-Variants for Lions and Giraffes
- added new Item: Shark Tooth (can be used for arrows/bone-meal)
