package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.item.LexiconItem;
import com.tristankechlo.livingthings.item.OstrichEggItem;
import com.tristankechlo.livingthings.platform.IPlatformHelper;
import com.tristankechlo.livingthings.platform.RegistrationProvider;
import com.tristankechlo.livingthings.platform.RegistryObject;
import net.minecraft.core.Registry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluids;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class ModItems {

    public static void init() {}

    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registry.ITEM, LivingThings.MOD_ID);
    public static final List<RegistryObject<? extends Item>> ALL_ITEMS = new ArrayList<>();
    public static final List<RegistryObject<Item>> SPAWN_EGGS;

    public static final RegistryObject<Item> SHARK_TOOTH = registerItem("shark_tooth", () -> new Item(normalProps()));
    public static final RegistryObject<Item> OSTRICH_EGG = registerItem("ostrich_egg", () -> new OstrichEggItem(normalProps().stacksTo(16)));
    public static final RegistryObject<Item> CRAB = registerItem("crab", () -> new Item(normalProps().food(ModFoods.CRAB)));
    public static final RegistryObject<Item> COOKED_CRAB = registerItem("cooked_crab", () -> new Item(normalProps().food(ModFoods.COOKED_CRAB)));
    public static final RegistryObject<Item> CRAB_SHELL = registerItem("crab_shell", () -> new Item(normalProps()));
    public static final RegistryObject<Item> LEXICON = registerItem("lexicon", () -> new LexiconItem(normalProps().stacksTo(1)));
    public static final RegistryObject<Item> BANANA = registerItem("banana", () -> new Item(normalProps().food(ModFoods.BANANA)));
    public static final RegistryObject<Item> ANCIENT_HELMET = registerItem("ancient_helmet", () -> new ArmorItem(ModArmorMaterial.ANCIENT, EquipmentSlot.HEAD, normalProps().stacksTo(1)));
    public static final RegistryObject<Item> OSTRICH = registerItem("ostrich", () -> new Item(normalProps().food(ModFoods.OSTRICH)));
    public static final RegistryObject<Item> COOKED_OSTRICH = registerItem("cooked_ostrich", () -> new Item(normalProps().food(ModFoods.COOKED_OSTRICH)));
    public static final RegistryObject<Item> ELEPHANT = registerItem("elephant", () -> new Item(normalProps().food(ModFoods.ELEPHANT)));
    public static final RegistryObject<Item> COOKED_ELEPHANT = registerItem("cooked_elephant", () -> new Item(normalProps().food(ModFoods.COOKED_ELEPHANT)));
    public static final RegistryObject<Item> LION = registerItem("lion", () -> new Item(normalProps().food(ModFoods.LION)));
    public static final RegistryObject<Item> COOKED_LION = registerItem("cooked_lion", () -> new Item(normalProps().food(ModFoods.COOKED_LION)));
    public static final RegistryObject<Item> GIRAFFE = registerItem("giraffe", () -> new Item(normalProps().food(ModFoods.GIRAFFE)));
    public static final RegistryObject<Item> COOKED_GIRAFFE = registerItem("cooked_giraffe", () -> new Item(normalProps().food(ModFoods.COOKED_GIRAFFE)));
    public static final RegistryObject<Item> SEAHORSE_BUCKET = registerItem("seahorse_bucket", () -> IPlatformHelper.INSTANCE.createMobBucketItem(ModEntityTypes.SEAHORSE, Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, normalProps().stacksTo(1)));

    // register spawn eggs
    public static final RegistryObject<Item> ELEPHANT_SPAWN_EGG = ITEMS.register("elephant_spawn_egg", () -> IPlatformHelper.INSTANCE.createSpawnEgg(ModEntityTypes.ELEPHANT::get, 0x000000, 0x4e4e4e, normalProps()));
    public static final RegistryObject<Item> GIRAFFE_SPAWN_EGG = ITEMS.register("giraffe_spawn_egg", () -> IPlatformHelper.INSTANCE.createSpawnEgg( ModEntityTypes.GIRAFFE::get, 0xebb26c, 0x785f40, normalProps()));
    public static final RegistryObject<Item> LION_SPAWN_EGG = ITEMS.register("lion_spawn_egg", () -> IPlatformHelper.INSTANCE.createSpawnEgg( ModEntityTypes.LION::get, 0xebb26c, 0xFFFFFF, normalProps()));
    public static final RegistryObject<Item> SHARK_SPAWN_EGG = ITEMS.register("shark_spawn_egg", () -> IPlatformHelper.INSTANCE.createSpawnEgg( ModEntityTypes.SHARK::get, 0x707187, 0x595a6b, normalProps()));
    public static final RegistryObject<Item> PENGUIN_SPAWN_EGG = ITEMS.register("penguin_spawn_egg", () -> IPlatformHelper.INSTANCE.createSpawnEgg( ModEntityTypes.PENGUIN::get, 0x000000, 0xFFFFFF, normalProps()));
    public static final RegistryObject<Item> OSTRICH_SPAWN_EGG = ITEMS.register("ostrich_spawn_egg", () -> IPlatformHelper.INSTANCE.createSpawnEgg( ModEntityTypes.OSTRICH::get, 0x130d08, 0xa56f5b, normalProps()));
    public static final RegistryObject<Item> FLAMINGO_SPAWN_EGG = ITEMS.register("flamingo_spawn_egg", () -> IPlatformHelper.INSTANCE.createSpawnEgg( ModEntityTypes.FLAMINGO::get, 0xf38989, 0x2d0404, normalProps()));
    public static final RegistryObject<Item> CRAB_SPAWN_EGG = ITEMS.register("crab_spawn_egg", () -> IPlatformHelper.INSTANCE.createSpawnEgg( ModEntityTypes.CRAB::get, 0xeb4034, 0x73706f, normalProps()));
    public static final RegistryObject<Item> MANTARAY_SPAWN_EGG = ITEMS.register("mantaray_spawn_egg", () -> IPlatformHelper.INSTANCE.createSpawnEgg( ModEntityTypes.MANTARAY::get, 0x000896, 0x595a6b, normalProps()));
    public static final RegistryObject<Item> RACCOON_SPAWN_EGG = ITEMS.register("raccoon_spawn_egg", () -> IPlatformHelper.INSTANCE.createSpawnEgg( ModEntityTypes.RACCOON::get, 0x6e6e6e, 0x000000, normalProps()));
    public static final RegistryObject<Item> OWL_SPAWN_EGG = ITEMS.register("owl_spawn_egg", () -> IPlatformHelper.INSTANCE.createSpawnEgg( ModEntityTypes.OWL::get, 0xedd7d5, 0x6e3834, normalProps()));
    public static final RegistryObject<Item> ANCIENT_BLAZE_SPAWN_EGG = ITEMS.register("ancient_blaze_spawn_egg", () -> IPlatformHelper.INSTANCE.createSpawnEgg( ModEntityTypes.ANCIENT_BLAZE::get, 0xF6B200, 0xFFF87D, normalProps()));
    public static final RegistryObject<Item> KOALA_SPAWN_EGG = ITEMS.register("koala_spawn_egg", () -> IPlatformHelper.INSTANCE.createSpawnEgg( ModEntityTypes.KOALA::get, 0x565050, 0x8f8686, normalProps()));
    public static final RegistryObject<Item> SNAIL_SPAWN_EGG = ITEMS.register("snail_spawn_egg", () -> IPlatformHelper.INSTANCE.createSpawnEgg( ModEntityTypes.SNAIL::get, 0x2206464, 0x53588, normalProps()));
    public static final RegistryObject<Item> MONKEY_SPAWN_EGG = ITEMS.register("monkey_spawn_egg", () -> IPlatformHelper.INSTANCE.createSpawnEgg( ModEntityTypes.MONKEY::get, 10051392, 7555121, normalProps()));
    public static final RegistryObject<Item> NETHER_KNIGHT_SPAWN_EGG = ITEMS.register("nether_knight_spawn_egg", () -> IPlatformHelper.INSTANCE.createSpawnEgg( ModEntityTypes.NETHER_KNIGHT::get, 0x181a1c, 0xa32aa1, normalProps()));
    public static final RegistryObject<Item> SHROOMIE_SPAWN_EGG = ITEMS.register("shroomie_spawn_egg", () -> IPlatformHelper.INSTANCE.createSpawnEgg( ModEntityTypes.SHROOMIE::get, 0xb8968d, 0xdb380f, normalProps()));
    public static final RegistryObject<Item> SEAHORSE_SPAWN_EGG = ITEMS.register("seahorse_spawn_egg", () -> IPlatformHelper.INSTANCE.createSpawnEgg( ModEntityTypes.SEAHORSE::get, 0x22ff22, 0xdb380f, normalProps()));
    public static final RegistryObject<Item> BABY_ENDER_DRAGON_SPAWN_EGG = ITEMS.register("baby_ender_dragon_spawn_egg", () -> IPlatformHelper.INSTANCE.createSpawnEgg( ModEntityTypes.BABY_ENDER_DRAGON::get, 0x201e24, 0xff59cd, normalProps()));
    public static final RegistryObject<Item> PEACOCK_SPAWN_EGG = ITEMS.register("peacock_spawn_egg", () -> IPlatformHelper.INSTANCE.createSpawnEgg( ModEntityTypes.PEACOCK::get, 0x117fc7, 0x86d43b, normalProps()));

    public static Item.Properties normalProps() {
        return new Item.Properties().tab(IPlatformHelper.getCreativeTab());
    }

    private static RegistryObject<Item> registerItem(String name, Supplier<Item> item) {
        RegistryObject<Item> registeredItem = ITEMS.register(name, item);
        ALL_ITEMS.add(registeredItem);
        return registeredItem;
    }

    static {
        SPAWN_EGGS = List.of(ELEPHANT_SPAWN_EGG, GIRAFFE_SPAWN_EGG, LION_SPAWN_EGG, SHARK_SPAWN_EGG, PENGUIN_SPAWN_EGG, OSTRICH_SPAWN_EGG,
                FLAMINGO_SPAWN_EGG, CRAB_SPAWN_EGG, MANTARAY_SPAWN_EGG, RACCOON_SPAWN_EGG, OWL_SPAWN_EGG, ANCIENT_BLAZE_SPAWN_EGG,
                KOALA_SPAWN_EGG, SNAIL_SPAWN_EGG, MONKEY_SPAWN_EGG, NETHER_KNIGHT_SPAWN_EGG, SHROOMIE_SPAWN_EGG, SEAHORSE_SPAWN_EGG,
                BABY_ENDER_DRAGON_SPAWN_EGG, PEACOCK_SPAWN_EGG);
    }

}
