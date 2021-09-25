package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.entities.AncientBlazeEntity;
import com.tristankechlo.livingthings.entities.CrabEntity;
import com.tristankechlo.livingthings.entities.ElephantEntity;
import com.tristankechlo.livingthings.entities.FlamingoEntity;
import com.tristankechlo.livingthings.entities.GiraffeEntity;
import com.tristankechlo.livingthings.entities.KoalaEntity;
import com.tristankechlo.livingthings.entities.LionEntity;
import com.tristankechlo.livingthings.entities.MantarayEntity;
import com.tristankechlo.livingthings.entities.MonkeyEntity;
import com.tristankechlo.livingthings.entities.NetherKnightEntity;
import com.tristankechlo.livingthings.entities.OstrichEntity;
import com.tristankechlo.livingthings.entities.OwlEntity;
import com.tristankechlo.livingthings.entities.PenguinEntity;
import com.tristankechlo.livingthings.entities.RaccoonEntity;
import com.tristankechlo.livingthings.entities.SeahorseEntity;
import com.tristankechlo.livingthings.entities.SharkEntity;
import com.tristankechlo.livingthings.entities.ShroomieEntity;
import com.tristankechlo.livingthings.entities.SnailEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, LivingThings.MOD_ID);
	private static final Properties spawn_egg_props = new Item.Properties().tab(ModItemGroups.GENERAL);

	// create entity types
	private static final EntityType<ElephantEntity> elephant = createStandardEntityType("elephant", ElephantEntity::new, EntityClassification.CREATURE, 1.85F, 2.7F);
	private static final EntityType<GiraffeEntity> giraffe = createStandardEntityType("giraffe", GiraffeEntity::new, EntityClassification.CREATURE, 1.5F, 3.2F);
	private static final EntityType<LionEntity> lion = createStandardEntityType("lion", LionEntity::new, EntityClassification.CREATURE, 1.25F, 1.5F);
	private static final EntityType<SharkEntity> shark = createStandardEntityType("shark", SharkEntity::new, EntityClassification.WATER_CREATURE, 1.4F, 1.1F);
	private static final EntityType<PenguinEntity> penguin = createStandardEntityType("penguin", PenguinEntity::new, EntityClassification.CREATURE, 0.8F, 1.45F);
	private static final EntityType<OstrichEntity> ostrich = createStandardEntityType("ostrich", OstrichEntity::new, EntityClassification.CREATURE, 0.8F, 1.8F);
	private static final EntityType<FlamingoEntity> flamingo = createStandardEntityType("flamingo", FlamingoEntity::new, EntityClassification.CREATURE, 0.6F, 1.25F);
	private static final EntityType<CrabEntity> crab = createStandardEntityType("crab", CrabEntity::new, EntityClassification.CREATURE, 0.4F, 0.4F);
	private static final EntityType<MantarayEntity> mantaray = createStandardEntityType("mantaray", MantarayEntity::new, EntityClassification.WATER_AMBIENT, 0.75F, 0.45F);
	private static final EntityType<RaccoonEntity> raccoon = createStandardEntityType("raccoon", RaccoonEntity::new, EntityClassification.CREATURE, 0.5F, 0.75F);
	private static final EntityType<OwlEntity> owl = createStandardEntityType("owl", OwlEntity::new, EntityClassification.CREATURE, 0.5F, 0.99F);
	private static final EntityType<AncientBlazeEntity> ancient_blaze = EntityType.Builder.of(AncientBlazeEntity::new, EntityClassification.MONSTER).sized(0.7F, 2.99F).fireImmune().build(LivingThings.MOD_ID + ":ancient_blaze");
	private static final EntityType<KoalaEntity> koala = createStandardEntityType("koala", KoalaEntity::new, EntityClassification.CREATURE, 0.6F, 0.75F);
	private static final EntityType<SnailEntity> snail = createStandardEntityType("snail", SnailEntity::new, EntityClassification.CREATURE, 0.6F, 0.7F);
	private static final EntityType<MonkeyEntity> monkey = createStandardEntityType("monkey", MonkeyEntity::new, EntityClassification.CREATURE, 0.6F, 0.7F);
	private static final EntityType<NetherKnightEntity> nether_knight = EntityType.Builder.of(NetherKnightEntity::new, EntityClassification.MONSTER).sized(0.7F, 2.3F).fireImmune().build(LivingThings.MOD_ID + ":nether_knight");
	private static final EntityType<ShroomieEntity> shroomie = createStandardEntityType("shroomie", ShroomieEntity::new, EntityClassification.CREATURE, 0.5F, 0.99F);
	public static final EntityType<SeahorseEntity> seahorse = createStandardEntityType("seahorse", SeahorseEntity::new, EntityClassification.WATER_AMBIENT, 0.2F, 0.7F);

	// register entity types
	public static final RegistryObject<EntityType<ElephantEntity>> ELEPHANT_ENTITY = ENTITY_TYPES.register("elephant", () -> elephant);
	public static final RegistryObject<EntityType<GiraffeEntity>> GIRAFFE_ENTITY = ENTITY_TYPES.register("giraffe", () -> giraffe);
	public static final RegistryObject<EntityType<LionEntity>> LION_ENTITY = ENTITY_TYPES.register("lion", () -> lion);
	public static final RegistryObject<EntityType<SharkEntity>> SHARK_ENTITY = ENTITY_TYPES.register("shark", () -> shark);
	public static final RegistryObject<EntityType<PenguinEntity>> PENGUIN_ENTITY = ENTITY_TYPES.register("penguin", () -> penguin);
	public static final RegistryObject<EntityType<OstrichEntity>> OSTRICH_ENTITY = ENTITY_TYPES.register("ostrich", () -> ostrich);
	public static final RegistryObject<EntityType<FlamingoEntity>> FLAMINGO_ENTITY = ENTITY_TYPES.register("flamingo", () -> flamingo);
	public static final RegistryObject<EntityType<CrabEntity>> CRAB_ENTITY = ENTITY_TYPES.register("crab", () -> crab);
	public static final RegistryObject<EntityType<MantarayEntity>> MANTARAY_ENTITY = ENTITY_TYPES.register("mantaray", () -> mantaray);
	public static final RegistryObject<EntityType<RaccoonEntity>> RACCOON_ENTITY = ENTITY_TYPES.register("raccoon", () -> raccoon);
	public static final RegistryObject<EntityType<OwlEntity>> OWL_ENTITY = ENTITY_TYPES.register("owl", () -> owl);
	public static final RegistryObject<EntityType<AncientBlazeEntity>> ANCIENT_BLAZE_ENTITY = ENTITY_TYPES.register("ancient_blaze", () -> ancient_blaze);
	public static final RegistryObject<EntityType<KoalaEntity>> KOALA_ENTITY = ENTITY_TYPES.register("koala", () -> koala);
	public static final RegistryObject<EntityType<SnailEntity>> SNAIL_ENTITY = ENTITY_TYPES.register("snail", () -> snail);
	public static final RegistryObject<EntityType<MonkeyEntity>> MONKEY_ENTITY = ENTITY_TYPES.register("monkey", () -> monkey);
	public static final RegistryObject<EntityType<NetherKnightEntity>> NETHER_KNIGHT_ENTITY = ENTITY_TYPES.register("nether_knight", () -> nether_knight);
	public static final RegistryObject<EntityType<ShroomieEntity>> SHROOMIE_ENTITY = ENTITY_TYPES.register("shroomie", () -> shroomie);
	public static final RegistryObject<EntityType<SeahorseEntity>> SEAHORSE_ENTITY = ENTITY_TYPES.register("seahorse", () -> seahorse);

	// register spawn eggs
	public static final RegistryObject<Item> ELEPHANT_SPAWN_EGG = ModItems.ITEMS.register("elephant_spawn_egg", () -> new SpawnEggItem(elephant, 0x000000, 0x4e4e4e, spawn_egg_props));
	public static final RegistryObject<Item> GIRAFFE_SPAWN_EGG = ModItems.ITEMS.register("giraffe_spawn_egg", () -> new SpawnEggItem(giraffe, 0xebb26c, 0x785f40, spawn_egg_props));
	public static final RegistryObject<Item> LION_SPAWN_EGG = ModItems.ITEMS.register("lion_spawn_egg", () -> new SpawnEggItem(lion, 0xebb26c, 0xFFFFFF, spawn_egg_props));
	public static final RegistryObject<Item> SHARK_SPAWN_EGG = ModItems.ITEMS.register("shark_spawn_egg", () -> new SpawnEggItem(shark, 0x707187, 0x595a6b, spawn_egg_props));
	public static final RegistryObject<Item> PENGUIN_SPAWN_EGG = ModItems.ITEMS.register("penguin_spawn_egg", () -> new SpawnEggItem(penguin, 0x000000, 0xFFFFFF, spawn_egg_props));
	public static final RegistryObject<Item> OSTRICH_SPAWN_EGG = ModItems.ITEMS.register("ostrich_spawn_egg", () -> new SpawnEggItem(ostrich, 0x130d08, 0xa56f5b, spawn_egg_props));
	public static final RegistryObject<Item> FLAMINGO_SPAWN_EGG = ModItems.ITEMS.register("flamingo_spawn_egg", () -> new SpawnEggItem(flamingo, 0xf38989, 0x2d0404, spawn_egg_props));
	public static final RegistryObject<Item> CRAB_SPAWN_EGG = ModItems.ITEMS.register("crab_spawn_egg", () -> new SpawnEggItem(crab, 0xeb4034, 0x73706f, spawn_egg_props));
	public static final RegistryObject<Item> MANTARAY_SPAWN_EGG = ModItems.ITEMS.register("mantaray_spawn_egg", () -> new SpawnEggItem(mantaray, 0x000896, 0x595a6b, spawn_egg_props));
	public static final RegistryObject<Item> RACCOON_SPAWN_EGG = ModItems.ITEMS.register("raccoon_spawn_egg", () -> new SpawnEggItem(raccoon, 0x6e6e6e, 0x000000, spawn_egg_props));
	public static final RegistryObject<Item> OWL_SPAWN_EGG = ModItems.ITEMS.register("owl_spawn_egg", () -> new SpawnEggItem(owl, 0xedd7d5, 0x6e3834, spawn_egg_props));
	public static final RegistryObject<Item> ANCIENT_BLAZE_SPAWN_EGG = ModItems.ITEMS.register("ancient_blaze_spawn_egg", () -> new SpawnEggItem(ancient_blaze, 0xF6B200, 0xFFF87D, spawn_egg_props));
	public static final RegistryObject<Item> KOALA_SPAWN_EGG = ModItems.ITEMS.register("koala_spawn_egg", () -> new SpawnEggItem(koala, 0x565050, 0x8f8686, spawn_egg_props));
	public static final RegistryObject<Item> SNAIL_SPAWN_EGG = ModItems.ITEMS.register("snail_spawn_egg", () -> new SpawnEggItem(snail, 0x2206464, 0x53588, spawn_egg_props));
	public static final RegistryObject<Item> MONKEY_SPAWN_EGG = ModItems.ITEMS.register("monkey_spawn_egg", () -> new SpawnEggItem(monkey, 10051392, 7555121, spawn_egg_props));
	public static final RegistryObject<Item> NETHER_KNIGHT_SPAWN_EGG = ModItems.ITEMS.register("nether_knight_spawn_egg", () -> new SpawnEggItem(nether_knight, 0x181a1c, 0xa32aa1, spawn_egg_props));
	public static final RegistryObject<Item> SHROOMIE_SPAWN_EGG = ModItems.ITEMS.register("shroomie_spawn_egg", () -> new SpawnEggItem(shroomie, 0xb8968d, 0xdb380f, spawn_egg_props));
	public static final RegistryObject<Item> SEAHORSE_SPAWN_EGG = ModItems.ITEMS.register("seahorse_spawn_egg", () -> new SpawnEggItem(seahorse, 0x22ff22, 0xdb380f, spawn_egg_props));

	// create standard entity type
	private static <T extends Entity> EntityType<T> createStandardEntityType(String entity_name, EntityType.IFactory<T> factory, EntityClassification classification, float width, float height) {
		return EntityType.Builder.of(factory, classification).sized(width, height).build(LivingThings.MOD_ID + ":" + entity_name);
	}

}
