package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.item.LexiconItem;
import com.tristankechlo.livingthings.item.OstrichEggItem;
import com.tristankechlo.livingthings.platform.IPlatformHelper;
import com.tristankechlo.livingthings.platform.RegistrationProvider;
import com.tristankechlo.livingthings.platform.RegistryObject;
import com.tristankechlo.livingthings.util.LivingThingsTags;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.material.Fluids;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public final class ModItems {

    public static void init() {}

    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(BuiltInRegistries.ITEM, LivingThings.MOD_ID);
    public static final List<RegistryObject<? extends Item>> ALL_ITEMS = new ArrayList<>();
    public static final List<RegistryObject<Item>> SPAWN_EGGS = new ArrayList<>();

    public static final RegistryObject<Item> SHARK_TOOTH = registerItem("shark_tooth", Item::new);
    public static final RegistryObject<Item> OSTRICH_EGG = registerItem("ostrich_egg", (p) -> new OstrichEggItem(p.stacksTo(16)));
    public static final RegistryObject<Item> CRAB = registerFoodItem("crab", ModFoods.CRAB);
    public static final RegistryObject<Item> COOKED_CRAB = registerFoodItem("cooked_crab", ModFoods.COOKED_CRAB);
    public static final RegistryObject<Item> CRAB_SHELL = registerItem("crab_shell", Item::new);
    public static final RegistryObject<Item> LEXICON = registerItem("lexicon", (p) -> new LexiconItem(p.stacksTo(1)));
    public static final RegistryObject<Item> BANANA = registerFoodItem("banana", ModFoods.BANANA);
    public static final RegistryObject<Item> ANCIENT_HELMET = registerArmorItem("ancient_helmet");
    public static final RegistryObject<Item> OSTRICH = registerFoodItem("ostrich", ModFoods.OSTRICH);
    public static final RegistryObject<Item> COOKED_OSTRICH = registerFoodItem("cooked_ostrich", ModFoods.COOKED_OSTRICH);
    public static final RegistryObject<Item> ELEPHANT = registerFoodItem("elephant", ModFoods.ELEPHANT);
    public static final RegistryObject<Item> COOKED_ELEPHANT = registerFoodItem("cooked_elephant", ModFoods.COOKED_ELEPHANT);
    public static final RegistryObject<Item> LION = registerFoodItem("lion", ModFoods.LION);
    public static final RegistryObject<Item> COOKED_LION = registerFoodItem("cooked_lion", ModFoods.COOKED_LION);
    public static final RegistryObject<Item> GIRAFFE = registerFoodItem("giraffe", ModFoods.GIRAFFE);
    public static final RegistryObject<Item> COOKED_GIRAFFE = registerFoodItem("cooked_giraffe", ModFoods.COOKED_GIRAFFE);
    public static final RegistryObject<Item> SEAHORSE_BUCKET = registerItem("seahorse_bucket", (p) -> IPlatformHelper.INSTANCE.createMobBucketItem(ModEntityTypes.SEAHORSE, Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, p.stacksTo(1)));

    // register spawn eggs
    public static final RegistryObject<Item> ELEPHANT_SPAWN_EGG = registerSpawnEgg("elephant_spawn_egg", ModEntityTypes.ELEPHANT::get, 0x000000, 0x4e4e4e);
    public static final RegistryObject<Item> GIRAFFE_SPAWN_EGG = registerSpawnEgg("giraffe_spawn_egg", ModEntityTypes.GIRAFFE::get, 0xebb26c, 0x785f40);
    public static final RegistryObject<Item> LION_SPAWN_EGG = registerSpawnEgg("lion_spawn_egg", ModEntityTypes.LION::get, 0xebb26c, 0xFFFFFF);
    public static final RegistryObject<Item> SHARK_SPAWN_EGG = registerSpawnEgg("shark_spawn_egg", ModEntityTypes.SHARK::get, 0x707187, 0x595a6b);
    public static final RegistryObject<Item> PENGUIN_SPAWN_EGG = registerSpawnEgg("penguin_spawn_egg", ModEntityTypes.PENGUIN::get, 0x000000, 0xFFFFFF);
    public static final RegistryObject<Item> OSTRICH_SPAWN_EGG = registerSpawnEgg("ostrich_spawn_egg", ModEntityTypes.OSTRICH::get, 0x130d08, 0xa56f5b);
    public static final RegistryObject<Item> FLAMINGO_SPAWN_EGG = registerSpawnEgg("flamingo_spawn_egg", ModEntityTypes.FLAMINGO::get, 0xf38989, 0x2d0404);
    public static final RegistryObject<Item> CRAB_SPAWN_EGG = registerSpawnEgg("crab_spawn_egg", ModEntityTypes.CRAB::get, 0xeb4034, 0x73706f);
    public static final RegistryObject<Item> MANTARAY_SPAWN_EGG = registerSpawnEgg("mantaray_spawn_egg", ModEntityTypes.MANTARAY::get, 0x000896, 0x595a6b);
    public static final RegistryObject<Item> RACCOON_SPAWN_EGG = registerSpawnEgg("raccoon_spawn_egg", ModEntityTypes.RACCOON::get, 0x6e6e6e, 0x000000);
    public static final RegistryObject<Item> OWL_SPAWN_EGG = registerSpawnEgg("owl_spawn_egg", ModEntityTypes.OWL::get, 0xedd7d5, 0x6e3834);
    public static final RegistryObject<Item> ANCIENT_BLAZE_SPAWN_EGG = registerSpawnEgg("ancient_blaze_spawn_egg", ModEntityTypes.ANCIENT_BLAZE::get, 0xF6B200, 0xFFF87D);
    public static final RegistryObject<Item> KOALA_SPAWN_EGG = registerSpawnEgg("koala_spawn_egg", ModEntityTypes.KOALA::get, 0x565050, 0x8f8686);
    public static final RegistryObject<Item> SNAIL_SPAWN_EGG = registerSpawnEgg("snail_spawn_egg", ModEntityTypes.SNAIL::get, 0x2206464, 0x53588);
    public static final RegistryObject<Item> MONKEY_SPAWN_EGG = registerSpawnEgg("monkey_spawn_egg", ModEntityTypes.MONKEY::get, 10051392, 7555121);
    public static final RegistryObject<Item> NETHER_KNIGHT_SPAWN_EGG = registerSpawnEgg("nether_knight_spawn_egg", ModEntityTypes.NETHER_KNIGHT::get, 0x181a1c, 0xa32aa1);
    public static final RegistryObject<Item> SHROOMIE_SPAWN_EGG = registerSpawnEgg("shroomie_spawn_egg", ModEntityTypes.SHROOMIE::get, 0xb8968d, 0xdb380f);
    public static final RegistryObject<Item> SEAHORSE_SPAWN_EGG = registerSpawnEgg("seahorse_spawn_egg", ModEntityTypes.SEAHORSE::get, 0x22ff22, 0xdb380f);
    public static final RegistryObject<Item> BABY_ENDER_DRAGON_SPAWN_EGG = registerSpawnEgg("baby_ender_dragon_spawn_egg", ModEntityTypes.BABY_ENDER_DRAGON::get, 0x201e24, 0xff59cd);
    public static final RegistryObject<Item> PEACOCK_SPAWN_EGG = registerSpawnEgg("peacock_spawn_egg", ModEntityTypes.PEACOCK::get, 0x117fc7, 0x86d43b);

    private static RegistryObject<Item> registerItem(String name, Function<Item.Properties, Item> item) {
        Item.Properties p = new Item.Properties().setId(RegistryHelper.itemId(name)).useItemDescriptionPrefix();
        RegistryObject<Item> registeredItem = ITEMS.register(name, () -> item.apply(p));
        ALL_ITEMS.add(registeredItem);
        return registeredItem;
    }

    private static RegistryObject<Item> registerArmorItem(String name) {
        ResourceLocation modelId = ResourceLocation.fromNamespaceAndPath(LivingThings.MOD_ID, "ancient_armor_model");
        return registerItem(name, (p) -> {
            p.stacksTo(1).durability(20)
                    .repairable(LivingThingsTags.REPAIRS_ANCIENT_HELMET)
                    .equippable(EquipmentSlot.HEAD)
                    .component(DataComponents.EQUIPPABLE,
                            Equippable.builder(EquipmentSlot.HEAD)
                                    .setEquipSound(ModSounds.ANCIENT_ARMOR_EQUIP.asHolder())
                                    .setModel(modelId)
                                    .build()
                    )
                    .attributes(createAttributes(ArmorType.HELMET, 3, 2.0F));
            return new Item(p);
        });
    }

    private static ItemAttributeModifiers createAttributes(ArmorType armorType, int defense, float toughness) {
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
        EquipmentSlotGroup group = EquipmentSlotGroup.bySlot(armorType.getSlot());
        ResourceLocation rl = ResourceLocation.fromNamespaceAndPath(LivingThings.MOD_ID, "armor." + armorType.getName());
        builder.add(Attributes.ARMOR, new AttributeModifier(rl, defense, AttributeModifier.Operation.ADD_VALUE), group);
        builder.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(rl, toughness, AttributeModifier.Operation.ADD_VALUE), group);
        return builder.build();
    }

    private static RegistryObject<Item> registerFoodItem(String name, FoodProperties food) {
        return registerItem(name, (p) -> new Item(p.food(food)));
    }

    private static RegistryObject<Item> registerSpawnEgg(String name, Supplier<EntityType<? extends Mob>> type, int primaryColor, int secondaryColor) {
        Item.Properties p = new Item.Properties().setId(RegistryHelper.itemId(name)).useItemDescriptionPrefix();
        RegistryObject<Item> registeredItem = ITEMS.register(name, () -> IPlatformHelper.INSTANCE.createSpawnEgg(type, primaryColor, secondaryColor, p));
        SPAWN_EGGS.add(registeredItem);
        return registeredItem;
    }

}
