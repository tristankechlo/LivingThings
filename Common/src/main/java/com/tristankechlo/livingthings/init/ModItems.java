package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.platform.IPlatformHelper;
import com.tristankechlo.livingthings.platform.RegistrationProvider;
import com.tristankechlo.livingthings.platform.RegistryObject;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public final class ModItems {

    public static void init() {}

    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registry.ITEM, LivingThings.MOD_ID);

    public static final RegistryObject<Item> SHARK_TOOTH = ITEMS.register("shark_tooth", () -> new Item(normalProps()));
    public static final RegistryObject<Item> OSTRICH_EGG = ITEMS.register("ostrich_egg", () -> new Item(normalProps().stacksTo(16)));
    public static final RegistryObject<Item> CRAB = ITEMS.register("crab", () -> new Item(normalProps().food(ModFoods.CRAB)));
    public static final RegistryObject<Item> COOKED_CRAB = ITEMS.register("cooked_crab", () -> new Item(normalProps().food(ModFoods.COOKED_CRAB)));
    public static final RegistryObject<Item> CRAB_SHELL = ITEMS.register("crab_shell", () -> new Item(normalProps()));
    //public static final RegistryObject<Item> LEXICON = ITEMS.register("lexicon", () -> new LexiconItem(new Properties().tab(ModItemGroups.GENERAL).stacksTo(1)));
    public static final RegistryObject<Item> BANANA = ITEMS.register("banana", () -> new Item(normalProps().food(ModFoods.BANANA)));
    //public static final RegistryObject<Item> ANCIENT_HELMET = ITEMS.register("ancient_helmet", () -> new ModArmorItem(ModArmorMaterial.ANCIENT, EquipmentSlot.HEAD, new Properties().tab(ModItemGroups.GENERAL).stacksTo(1)));
    public static final RegistryObject<Item> OSTRICH = ITEMS.register("ostrich", () -> new Item(normalProps().food(ModFoods.OSTRICH)));
    public static final RegistryObject<Item> COOKED_OSTRICH = ITEMS.register("cooked_ostrich", () -> new Item(normalProps().food(ModFoods.COOKED_OSTRICH)));
    public static final RegistryObject<Item> ELEPHANT = ITEMS.register("elephant", () -> new Item(normalProps().food(ModFoods.ELEPHANT)));
    public static final RegistryObject<Item> COOKED_ELEPHANT = ITEMS.register("cooked_elephant", () -> new Item(normalProps().food(ModFoods.COOKED_ELEPHANT)));
    public static final RegistryObject<Item> LION = ITEMS.register("lion", () -> new Item(normalProps().food(ModFoods.LION)));
    public static final RegistryObject<Item> COOKED_LION = ITEMS.register("cooked_lion", () -> new Item(normalProps().food(ModFoods.COOKED_LION)));
    public static final RegistryObject<Item> GIRAFFE = ITEMS.register("giraffe", () -> new Item(normalProps().food(ModFoods.GIRAFFE)));
    public static final RegistryObject<Item> COOKED_GIRAFFE = ITEMS.register("cooked_giraffe", () -> new Item(normalProps().food(ModFoods.COOKED_GIRAFFE)));
    //public static final RegistryObject<Item> SEAHORSE_BUCKET = ITEMS.register("seahorse_bucket", () -> new MobBucketItem(ModEntityTypes.SEAHORSE, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, new Properties().tab(ModItemGroups.GENERAL).stacksTo(1)));

    // register spawn eggs
    public static final RegistryObject<Item> ELEPHANT_SPAWN_EGG = ITEMS.register("elephant_spawn_egg", () -> new SpawnEggItem(ModEntityTypes.ELEPHANT.get(), 0x000000, 0x4e4e4e, normalProps()));
    public static final RegistryObject<Item> GIRAFFE_SPAWN_EGG = ITEMS.register("giraffe_spawn_egg", () -> new SpawnEggItem(ModEntityTypes.GIRAFFE.get(), 0xebb26c, 0x785f40, normalProps()));
    public static final RegistryObject<Item> LION_SPAWN_EGG = ITEMS.register("lion_spawn_egg", () -> new SpawnEggItem(ModEntityTypes.LION.get(), 0xebb26c, 0xFFFFFF, normalProps()));
    public static final RegistryObject<Item> PENGUIN_SPAWN_EGG = ITEMS.register("penguin_spawn_egg", () -> new SpawnEggItem(ModEntityTypes.PENGUIN.get(), 0x000000, 0xFFFFFF, normalProps()));

    public static Item.Properties normalProps() {
        return new Item.Properties().tab(IPlatformHelper.getCreativeTab());
    }

}
