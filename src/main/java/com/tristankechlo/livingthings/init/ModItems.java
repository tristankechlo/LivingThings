package com.tristankechlo.livingthings.init;

import java.util.function.Supplier;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.items.LexiconItem;
import com.tristankechlo.livingthings.items.ModArmorItem;
import com.tristankechlo.livingthings.items.OstrichEggItem;
import com.tristankechlo.livingthings.misc.Names;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModItems {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LivingThings.MOD_ID);
	private static final Properties SPAWN_EGG_PROPERTIES = new Item.Properties().tab(ModItemGroups.GENERAL);

	public static final RegistryObject<Item> SHARK_TOOTH = ITEMS.register("shark_tooth", () -> new Item(new Properties().tab(ModItemGroups.GENERAL)));
	public static final RegistryObject<Item> OSTRICH_EGG = ITEMS.register("ostrich_egg", () -> new OstrichEggItem(new Properties().tab(ModItemGroups.GENERAL).stacksTo(16)));
	public static final RegistryObject<Item> CRAB = ITEMS.register("crab", () -> new Item(new Properties().tab(ModItemGroups.GENERAL).food(ModFoods.CRAB)));
	public static final RegistryObject<Item> COOKED_CRAB = ITEMS.register("cooked_crab", () -> new Item(new Properties().tab(ModItemGroups.GENERAL).food(ModFoods.COOKED_CRAB)));
	public static final RegistryObject<Item> CRAB_SHELL = ITEMS.register("crab_shell", () -> new Item(new Properties().tab(ModItemGroups.GENERAL)));
	public static final RegistryObject<Item> LEXICON = ITEMS.register("lexicon", () -> new LexiconItem(new Properties().tab(ModItemGroups.GENERAL).stacksTo(1)));
	public static final RegistryObject<Item> ANCIENT_HELMET = ITEMS.register("ancient_helmet", () -> new ModArmorItem(ModArmorMaterial.ANCIENT, EquipmentSlot.HEAD, new Properties().tab(ModItemGroups.GENERAL).stacksTo(1)));
	public static final RegistryObject<Item> OSTRICH = ITEMS.register("ostrich", () -> new Item(new Properties().tab(ModItemGroups.GENERAL).food(ModFoods.OSTRICH)));
	public static final RegistryObject<Item> COOKED_OSTRICH = ITEMS.register("cooked_ostrich", () -> new Item(new Properties().tab(ModItemGroups.GENERAL).food(ModFoods.COOKED_OSTRICH)));
	public static final RegistryObject<Item> BANANA = ITEMS.register("banana", () -> new Item(new Properties().tab(ModItemGroups.GENERAL).food(ModFoods.BANANA)));
	public static final RegistryObject<Item> ELEPHANT = ITEMS.register("elephant", () -> new Item(new Properties().tab(ModItemGroups.GENERAL).food(ModFoods.ELEPHANT)));
	public static final RegistryObject<Item> COOKED_ELEPHANT = ITEMS.register("cooked_elephant", () -> new Item(new Properties().tab(ModItemGroups.GENERAL).food(ModFoods.COOKED_ELEPHANT)));
	public static final RegistryObject<Item> LION = ITEMS.register("lion", () -> new Item(new Properties().tab(ModItemGroups.GENERAL).food(ModFoods.LION)));
	public static final RegistryObject<Item> COOKED_LION = ITEMS.register("cooked_lion", () -> new Item(new Properties().tab(ModItemGroups.GENERAL).food(ModFoods.COOKED_LION)));
	public static final RegistryObject<Item> GIRAFFE = ITEMS.register("giraffe", () -> new Item(new Properties().tab(ModItemGroups.GENERAL).food(ModFoods.GIRAFFE)));
	public static final RegistryObject<Item> COOKED_GIRAFFE = ITEMS.register("cooked_giraffe", () -> new Item(new Properties().tab(ModItemGroups.GENERAL).food(ModFoods.COOKED_GIRAFFE)));
	public static final RegistryObject<Item> SEAHORSE_BUCKET = ITEMS.register("seahorse_bucket", () -> new MobBucketItem(ModEntityTypes.SEAHORSE, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, new Properties().tab(ModItemGroups.GENERAL).stacksTo(1)));


	// register spawn eggs
	public static final RegistryObject<Item> ELEPHANT_SPAWN_EGG = registerSpawnEgg(Names.ELEPHANT, ModEntityTypes.ELEPHANT, 0x000000, 0x4e4e4e);
	public static final RegistryObject<Item> GIRAFFE_SPAWN_EGG = registerSpawnEgg(Names.GIRAFFE, ModEntityTypes.GIRAFFE, 0xebb26c, 0x785f40);
	public static final RegistryObject<Item> LION_SPAWN_EGG = registerSpawnEgg(Names.LION, ModEntityTypes.LION, 0xebb26c, 0xFFFFFF);
	public static final RegistryObject<Item> SHARK_SPAWN_EGG = registerSpawnEgg(Names.SHARK, ModEntityTypes.SHARK, 0x707187, 0x595a6b);
	public static final RegistryObject<Item> PENGUIN_SPAWN_EGG = registerSpawnEgg(Names.PENGUIN, ModEntityTypes.PENGUIN, 0x000000, 0xFFFFFF);
	public static final RegistryObject<Item> OSTRICH_SPAWN_EGG = registerSpawnEgg(Names.OSTRICH, ModEntityTypes.OSTRICH, 0x130d08, 0xa56f5b);
	public static final RegistryObject<Item> FLAMINGO_SPAWN_EGG = registerSpawnEgg(Names.FLAMINGO, ModEntityTypes.FLAMINGO, 0xf38989, 0x2d0404);
	public static final RegistryObject<Item> CRAB_SPAWN_EGG = registerSpawnEgg(Names.CRAB, ModEntityTypes.CRAB, 0xeb4034, 0x73706f);
	public static final RegistryObject<Item> MANTARAY_SPAWN_EGG = registerSpawnEgg(Names.MANTARAY, ModEntityTypes.MANTARAY, 0x000896, 0x595a6b);
	public static final RegistryObject<Item> RACCOON_SPAWN_EGG = registerSpawnEgg(Names.RACCOON, ModEntityTypes.RACCOON, 0x6e6e6e, 0x000000);
	public static final RegistryObject<Item> OWL_SPAWN_EGG = registerSpawnEgg(Names.OWL, ModEntityTypes.OWL, 0xedd7d5, 0x6e3834);
	public static final RegistryObject<Item> ANCIENT_BLAZE_SPAWN_EGG = registerSpawnEgg(Names.ANCIENT_BLAZE, ModEntityTypes.ANCIENT_BLAZE, 0xF6B200, 0xFFF87D);
	public static final RegistryObject<Item> KOALA_SPAWN_EGG = registerSpawnEgg(Names.KOALA, ModEntityTypes.KOALA, 0x565050, 0x8f8686);
	public static final RegistryObject<Item> SNAIL_SPAWN_EGG = registerSpawnEgg(Names.SNAIL, ModEntityTypes.SNAIL, 0x2206464, 0x53588);
	public static final RegistryObject<Item> MONKEY_SPAWN_EGG = registerSpawnEgg(Names.MONKEY, ModEntityTypes.MONKEY, 10051392, 7555121);
	public static final RegistryObject<Item> NETHER_KNIGHT_SPAWN_EGG = registerSpawnEgg(Names.NETHER_KNIGHT, ModEntityTypes.NETHER_KNIGHT, 0x181a1c, 0xa32aa1);
	public static final RegistryObject<Item> SHROOMIE_SPAWN_EGG = registerSpawnEgg(Names.SHROOMIE, ModEntityTypes.SHROOMIE, 0xb8968d, 0xdb380f);
	public static final RegistryObject<Item> SEAHORSE_SPAWN_EGG = registerSpawnEgg(Names.SEAHORSE, ModEntityTypes.SEAHORSE, 0x22ff22, 0xdb380f);
	public static final RegistryObject<Item> BABY_ENDER_DRAGON_SPAWN_EGG = registerSpawnEgg(Names.BABY_ENDER_DRAGON, ModEntityTypes.BABY_ENDER_DRAGON, 0x201e24, 0xff59cd);

	private static RegistryObject<Item> registerSpawnEgg(String entityName, Supplier<? extends EntityType<? extends Mob>> type, int backgroundColor, int highlightColor) {
		return ITEMS.register(entityName + "_spawn_egg", () -> new ForgeSpawnEggItem(type, backgroundColor, highlightColor, SPAWN_EGG_PROPERTIES));
	}

}
