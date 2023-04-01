package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.platform.IPlatformHelper;
import com.tristankechlo.livingthings.platform.RegistrationProvider;
import com.tristankechlo.livingthings.platform.RegistryObject;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

import java.util.ArrayList;
import java.util.List;

public final class ModItems {

    public static void init() {}

    private static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registry.ITEM, LivingThings.MOD_ID);
    public static final List<RegistryObject<? extends Item>> ALL_ITEMS = new ArrayList<>();
    public static final List<RegistryObject<Item>> SPAWN_EGGS = new ArrayList<>();

    public static final RegistryObject<Item> SHARK_TOOTH = register("shark_tooth", new Item(normalProps()));
    public static final RegistryObject<Item> OSTRICH_EGG = register("ostrich_egg", new Item(normalProps().stacksTo(16)));
    public static final RegistryObject<Item> CRAB = register("crab", new Item(normalProps().food(ModFoods.CRAB)));
    public static final RegistryObject<Item> COOKED_CRAB = register("cooked_crab", new Item(normalProps().food(ModFoods.COOKED_CRAB)));
    public static final RegistryObject<Item> CRAB_SHELL = register("crab_shell", new Item(normalProps()));
    //public static final RegistryObject<Item> LEXICON = ITEMS.register("lexicon", () -> new LexiconItem(new Properties().tab(ModItemGroups.GENERAL).stacksTo(1)));
    public static final RegistryObject<Item> BANANA = register("banana", new Item(normalProps().food(ModFoods.BANANA)));
    //public static final RegistryObject<Item> ANCIENT_HELMET = ITEMS.register("ancient_helmet", () -> new ModArmorItem(ModArmorMaterial.ANCIENT, EquipmentSlot.HEAD, new Properties().tab(ModItemGroups.GENERAL).stacksTo(1)));
    public static final RegistryObject<Item> OSTRICH = register("ostrich", new Item(normalProps().food(ModFoods.OSTRICH)));
    public static final RegistryObject<Item> COOKED_OSTRICH = register("cooked_ostrich", new Item(normalProps().food(ModFoods.COOKED_OSTRICH)));
    public static final RegistryObject<Item> ELEPHANT = register("elephant", new Item(normalProps().food(ModFoods.ELEPHANT)));
    public static final RegistryObject<Item> COOKED_ELEPHANT = register("cooked_elephant", new Item(normalProps().food(ModFoods.COOKED_ELEPHANT)));
    public static final RegistryObject<Item> LION = register("lion", new Item(normalProps().food(ModFoods.LION)));
    public static final RegistryObject<Item> COOKED_LION = register("cooked_lion", new Item(normalProps().food(ModFoods.COOKED_LION)));
    public static final RegistryObject<Item> GIRAFFE = register("giraffe", new Item(normalProps().food(ModFoods.GIRAFFE)));
    public static final RegistryObject<Item> COOKED_GIRAFFE = register("cooked_giraffe", new Item(normalProps().food(ModFoods.COOKED_GIRAFFE)));
    //public static final RegistryObject<Item> SEAHORSE_BUCKET = ITEMS.register("seahorse_bucket", () -> new MobBucketItem(ModEntityTypes.SEAHORSE, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, new Properties().tab(ModItemGroups.GENERAL).stacksTo(1)));

    // register spawn eggs
    public static final RegistryObject<Item> ELEPHANT_SPAWN_EGG = registerSpawnEgg("elephant", ModEntityTypes.ELEPHANT, 0x000000, 0x4e4e4e);
    public static final RegistryObject<Item> GIRAFFE_SPAWN_EGG = registerSpawnEgg("giraffe", ModEntityTypes.GIRAFFE, 0xebb26c, 0x785f40);
    public static final RegistryObject<Item> LION_SPAWN_EGG = registerSpawnEgg("lion", ModEntityTypes.LION, 0xebb26c, 0xFFFFFF);
    public static final RegistryObject<Item> PENGUIN_SPAWN_EGG = registerSpawnEgg("penguin", ModEntityTypes.PENGUIN, 0x000000, 0xFFFFFF);

    public static Item.Properties normalProps() {
        return new Item.Properties().tab(IPlatformHelper.getCreativeTab());
    }

    public static <T extends Item> RegistryObject<T> register(String name, T item) {
        RegistryObject<T> registryObject = ITEMS.register(name, () -> item);
        ALL_ITEMS.add(registryObject);
        return registryObject;
    }

    private static <T extends Mob> RegistryObject<Item> registerSpawnEgg(String name, RegistryObject<EntityType<T>> entityType, int color1, int color2) {
        RegistryObject<Item> item = ITEMS.register(name + "_spawn_egg", () -> new SpawnEggItem(entityType.get(), color1, color2, new Item.Properties()));
        SPAWN_EGGS.add(item);
        return item;
    }

}
