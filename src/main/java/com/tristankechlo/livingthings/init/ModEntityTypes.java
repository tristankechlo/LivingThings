package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.entities.AncientBlazeEntity;
import com.tristankechlo.livingthings.entities.CrabEntity;
import com.tristankechlo.livingthings.entities.ElephantEntity;
import com.tristankechlo.livingthings.entities.FlamingoEntity;
import com.tristankechlo.livingthings.entities.GiraffeEntity;
import com.tristankechlo.livingthings.entities.LionEntity;
import com.tristankechlo.livingthings.entities.MantarayEntity;
import com.tristankechlo.livingthings.entities.OstrichEntity;
import com.tristankechlo.livingthings.entities.OwlEntity;
import com.tristankechlo.livingthings.entities.PenguinEntity;
import com.tristankechlo.livingthings.entities.RaccoonEntity;
import com.tristankechlo.livingthings.entities.SharkEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntitySpawnPlacementRegistry.PlacementType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
	
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, LivingThings.MOD_ID);
	private static final Properties spawn_egg_props = new Item.Properties().group(ModItemGroups.General);
	
	
	//create entity types
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
    private static final EntityType<AncientBlazeEntity> ancient_blaze = EntityType.Builder.create(AncientBlazeEntity::new, EntityClassification.MONSTER).size(0.7F, 2.99F).immuneToFire().build(LivingThings.MOD_ID + ":ancient_blaze");
	
	
	//register entity types
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
	
	
	//register spawn eggs
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
	
	

	/**
	 * register Attributes like Health/Speed ... 
	 */
	public static void registerEntityAttributes(){
		GlobalEntityTypeAttributes.put(ELEPHANT_ENTITY.get(), ElephantEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(GIRAFFE_ENTITY.get(), GiraffeEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(LION_ENTITY.get(), LionEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(SHARK_ENTITY.get(), SharkEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(PENGUIN_ENTITY.get(), PenguinEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(OSTRICH_ENTITY.get(), OstrichEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(FLAMINGO_ENTITY.get(), FlamingoEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(CRAB_ENTITY.get(), CrabEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(MANTARAY_ENTITY.get(), MantarayEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(RACCOON_ENTITY.get(), RaccoonEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(OWL_ENTITY.get(), OwlEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(ANCIENT_BLAZE_ENTITY.get(), AncientBlazeEntity.getAttributes().create());
	}

	/**
	 * EntitySpawnPlacementRegistry
	 */
	public static void registerEntitySpawnPlacements() {
		EntitySpawnPlacementRegistry.register(ELEPHANT_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(GIRAFFE_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(LION_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(SHARK_ENTITY.get(), PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SharkEntity::canSharkSpawn);
		EntitySpawnPlacementRegistry.register(PENGUIN_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(OSTRICH_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(FLAMINGO_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(CRAB_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CrabEntity::canCrabSpawn);
		EntitySpawnPlacementRegistry.register(MANTARAY_ENTITY.get(), PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MantarayEntity::canMantaraySpawn);
		EntitySpawnPlacementRegistry.register(RACCOON_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(OWL_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, OwlEntity::canOwlSpawn);
		EntitySpawnPlacementRegistry.register(ANCIENT_BLAZE_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
	}

	
	/**
	 * create standard entity type
	 * @param <T>
	 * @param entity_name
	 * @param factory
	 * @param classification
	 * @param width
	 * @param height
	 * @return
	 */
    private static <T extends Entity> EntityType<T> createStandardEntityType(String entity_name, EntityType.IFactory<T> factory, EntityClassification classification, float width, float height) {
        
        EntityType<T> entity_type = EntityType.Builder.create(factory, classification).size(width, height).build(LivingThings.MOD_ID + ":" + entity_name);
        return entity_type;
    }

}
