package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.entities.ElephantEntity;
import com.tristankechlo.livingthings.entities.GiraffeEntity;
import com.tristankechlo.livingthings.entities.LionEntity;
import com.tristankechlo.livingthings.entities.OstrichEntity;
import com.tristankechlo.livingthings.entities.PenguinEntity;
import com.tristankechlo.livingthings.entities.SharkEntity;

import java.util.List;

import com.google.common.collect.Lists;
import com.tristankechlo.livingthings.LivingThings;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.Heightmap;

public class ModEntityTypes {
		
	@SuppressWarnings("rawtypes")
	public static final List<EntityType> ENTITIES = Lists.newArrayList();
    public static final List<Item> SPAWN_EGGS = Lists.newArrayList();
    
    public static final EntityType<ElephantEntity> ELEPHANT_ENTITY = createEntity("elephant", ElephantEntity::new, EntityClassification.CREATURE, 1.85F, 2.7F, 0x000000, 0x4e4e4e);
    public static final EntityType<GiraffeEntity> GIRAFFE_ENTITY = createEntity("giraffe", GiraffeEntity::new, EntityClassification.CREATURE, 1.5F, 3.2F, 0xebb26c, 0x785f40);
    public static final EntityType<LionEntity> LION_ENTITY = createEntity("lion", LionEntity::new, EntityClassification.CREATURE, 1.25F, 1.5F, 0xebb26c, 0xFFFFFF);
    public static final EntityType<SharkEntity> SHARK_ENTIY = createEntity("shark", SharkEntity::new, EntityClassification.WATER_CREATURE, 1.4F, 1.1F, 0x000896, 0x595a6b);
    public static final EntityType<PenguinEntity> PENGUIN_ENTITY = createEntity("penguin", PenguinEntity::new, EntityClassification.CREATURE, 0.8F, 1.45F, 0x000000, 0xFFFFFF);
    public static final EntityType<OstrichEntity> OSTRICH_ENTIY = createEntity("ostrich", OstrichEntity::new, EntityClassification.CREATURE, 0.8F, 1.8F, 0x130d08, 0xa56f5b);


	/*
	 * register Attributes like Health/Speed ... 
	 */
	public static void registerAttributes(){
		GlobalEntityTypeAttributes.put(ELEPHANT_ENTITY, ElephantEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(GIRAFFE_ENTITY, GiraffeEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(LION_ENTITY, LionEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(SHARK_ENTIY, SharkEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(PENGUIN_ENTITY, PenguinEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(OSTRICH_ENTIY, OstrichEntity.getAttributes().create());
	}

	/*
	 * where it is allowed to spawn the mobs
	 */
	public static void registerEntitySpawnPlacements() {
		EntitySpawnPlacementRegistry.register(ELEPHANT_ENTITY, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(GIRAFFE_ENTITY, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(LION_ENTITY, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(SHARK_ENTIY, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.OCEAN_FLOOR, SharkEntity::canSharkSpawn);
		EntitySpawnPlacementRegistry.register(PENGUIN_ENTITY, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(OSTRICH_ENTIY, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
	}

    /**
     * register Entity without SpawnEgg
     */
    private static <T extends CreatureEntity> EntityType<T> createEntity(String entity_name, EntityType.IFactory<T> factory, EntityClassification classification, float width, float height) {
        
    	ResourceLocation location = new ResourceLocation(LivingThings.MOD_ID, entity_name);
        EntityType<T> entity_type = EntityType.Builder.create(factory, classification).size(width, height).build(location.toString());
        entity_type.setRegistryName(location);
        ENTITIES.add(entity_type);
        
        return entity_type;
    }

	/**
     * register Entity with SpawnEgg
     */
    private static <T extends CreatureEntity> EntityType<T> createEntity(String entity_name, EntityType.IFactory<T> factory, EntityClassification classification, float width, float height, int eggPrimaryColor, int eggSecondaryColor) {
        
    	EntityType<T> entity_type = createEntity(entity_name, factory, classification, width, height);        
        addSpawnEggToEntity(entity_name, entity_type, eggPrimaryColor, eggSecondaryColor);
        
        return entity_type;
    }
    
    /**
	 * create SpawnEgg
     */
    private static <T extends CreatureEntity> void addSpawnEggToEntity(String entity_name, EntityType<T> entity_type, int eggPrimaryColor, int eggSecondaryColor) {
    	
    	final Properties standard_properties = new Item.Properties().group(ModItemGroups.General);
    	
        Item spawnEggItem = new SpawnEggItem(entity_type, eggPrimaryColor, eggSecondaryColor, standard_properties);
        spawnEggItem.setRegistryName(new ResourceLocation(LivingThings.MOD_ID, entity_name + "_spawn_egg"));
        SPAWN_EGGS.add(spawnEggItem);
    }

}
