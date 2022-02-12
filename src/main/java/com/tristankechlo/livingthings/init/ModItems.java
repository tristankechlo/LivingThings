package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.items.LexiconItem;
import com.tristankechlo.livingthings.items.ModArmorItem;
import com.tristankechlo.livingthings.items.OstrichEggItem;
import com.tristankechlo.livingthings.misc.Names;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
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
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Item> SEAHORSE_BUCKET = ITEMS.register("seahorse_bucket", () -> new MobBucketItem(ModEntityTypes.seahorse, Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, new Properties().tab(ModItemGroups.GENERAL).stacksTo(1)));

	// register spawn eggs
	// TODO replace item names
	public static final RegistryObject<Item> ELEPHANT_SPAWN_EGG = ITEMS.register("elephant_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.ELEPHANT, 0x000000, 0x4e4e4e, SPAWN_EGG_PROPERTIES));
	public static final RegistryObject<Item> GIRAFFE_SPAWN_EGG = ITEMS.register("giraffe_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.GIRAFFE, 0xebb26c, 0x785f40, SPAWN_EGG_PROPERTIES));
	public static final RegistryObject<Item> LION_SPAWN_EGG = ITEMS.register("lion_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.LION, 0xebb26c, 0xFFFFFF, SPAWN_EGG_PROPERTIES));
	public static final RegistryObject<Item> SHARK_SPAWN_EGG = ITEMS.register("shark_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.SHARK, 0x707187, 0x595a6b, SPAWN_EGG_PROPERTIES));
	public static final RegistryObject<Item> PENGUIN_SPAWN_EGG = ITEMS.register("penguin_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.PENGUIN, 0x000000, 0xFFFFFF, SPAWN_EGG_PROPERTIES));
	public static final RegistryObject<Item> OSTRICH_SPAWN_EGG = ITEMS.register("ostrich_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.OSTRICH, 0x130d08, 0xa56f5b, SPAWN_EGG_PROPERTIES));
	public static final RegistryObject<Item> FLAMINGO_SPAWN_EGG = ITEMS.register("flamingo_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.FLAMINGO, 0xf38989, 0x2d0404, SPAWN_EGG_PROPERTIES));
	public static final RegistryObject<Item> CRAB_SPAWN_EGG = ITEMS.register("crab_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.CRAB, 0xeb4034, 0x73706f, SPAWN_EGG_PROPERTIES));
	public static final RegistryObject<Item> MANTARAY_SPAWN_EGG = ITEMS.register("mantaray_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.MANTARAY, 0x000896, 0x595a6b, SPAWN_EGG_PROPERTIES));
	public static final RegistryObject<Item> RACCOON_SPAWN_EGG = ITEMS.register("raccoon_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.RACCOON, 0x6e6e6e, 0x000000, SPAWN_EGG_PROPERTIES));
	public static final RegistryObject<Item> OWL_SPAWN_EGG = ITEMS.register("owl_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.OWL, 0xedd7d5, 0x6e3834, SPAWN_EGG_PROPERTIES));
	public static final RegistryObject<Item> ANCIENT_BLAZE_SPAWN_EGG = ITEMS.register("ancient_blaze_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.ANCIENT_BLAZE, 0xF6B200, 0xFFF87D, SPAWN_EGG_PROPERTIES));
	public static final RegistryObject<Item> KOALA_SPAWN_EGG = ITEMS.register("koala_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.KOALA, 0x565050, 0x8f8686, SPAWN_EGG_PROPERTIES));
	public static final RegistryObject<Item> SNAIL_SPAWN_EGG = ITEMS.register("snail_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.SNAIL, 0x2206464, 0x53588, SPAWN_EGG_PROPERTIES));
	public static final RegistryObject<Item> MONKEY_SPAWN_EGG = ITEMS.register("monkey_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.MONKEY, 10051392, 7555121, SPAWN_EGG_PROPERTIES));
	public static final RegistryObject<Item> NETHER_KNIGHT_SPAWN_EGG = ITEMS.register("nether_knight_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.NETHER_KNIGHT, 0x181a1c, 0xa32aa1, SPAWN_EGG_PROPERTIES));
	public static final RegistryObject<Item> SHROOMIE_SPAWN_EGG = ITEMS.register("shroomie_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.SHROOMIE, 0xb8968d, 0xdb380f, SPAWN_EGG_PROPERTIES));
	public static final RegistryObject<Item> SEAHORSE_SPAWN_EGG = ITEMS.register("seahorse_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.SEAHORSE, 0x22ff22, 0xdb380f, SPAWN_EGG_PROPERTIES));
	public static final RegistryObject<Item> BABY_ENDER_DRAGON_SPAWN_EGG = ITEMS.register(Names.BABY_ENDER_DRAGON + "_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.BABY_ENDER_DRAGON, 0x201e24, 0xff59cd, SPAWN_EGG_PROPERTIES));
	
}
