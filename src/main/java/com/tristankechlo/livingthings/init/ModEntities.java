package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.entities.ElephantEntity;
import com.tristankechlo.livingthings.entities.GiraffeEntity;
import com.tristankechlo.livingthings.entities.LionEntity;
import com.tristankechlo.livingthings.LivingThings;

import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {

	public static DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, LivingThings.MOD_ID);


	public static final RegistryObject<EntityType<ElephantEntity>> ELEPHANT_ENTITY = ENTITIES.register("elephant",
			() -> EntityType.Builder.create(ElephantEntity::new, EntityClassification.CREATURE)
					.size(1.85F, 2.7F).build((new ResourceLocation(LivingThings.MOD_ID, "elephant")).toString()));

	public static final RegistryObject<EntityType<GiraffeEntity>> GIRAFFE_ENTITY = ENTITIES.register("giraffe",
			() -> EntityType.Builder.create(GiraffeEntity::new, EntityClassification.CREATURE)
					.size(1.5F, 3.2F).build((new ResourceLocation(LivingThings.MOD_ID, "giraffe")).toString()));

	public static final RegistryObject<EntityType<LionEntity>> LION_ENTITY = ENTITIES.register("lion",
			() -> EntityType.Builder.create(LionEntity::new, EntityClassification.CREATURE)
					.size(1.25F, 1.5F).build((new ResourceLocation(LivingThings.MOD_ID, "lion")).toString()));


	/*
	 * register Attributes like Health/Speed ... 
	 */
	public static void registerAttributes(){
		GlobalEntityTypeAttributes.put(ELEPHANT_ENTITY.get(), ElephantEntity.func_234200_m_().func_233813_a_());
		GlobalEntityTypeAttributes.put(GIRAFFE_ENTITY.get(), GiraffeEntity.func_234200_m_().func_233813_a_());
		GlobalEntityTypeAttributes.put(LION_ENTITY.get(), GiraffeEntity.func_234200_m_().func_233813_a_());
	}

	/*
	 * where it is allowed to spawn the mobs
	 */
	public static void registerEntitySpawnPlacements() {
		EntitySpawnPlacementRegistry.register(ELEPHANT_ENTITY.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(GIRAFFE_ENTITY.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(LION_ENTITY.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
	}

	/*
	 * add Entities to Biomes to spawn in
	 */
    public static void registerEntitySpawns() {
    	registerElephantSpawns();
    	registerGiraffeSpawns();
    	registerLionSpawns();
    }
    
    private static void registerElephantSpawns() {
        final List<Biome> biomes = Arrays.asList(Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU);
        for (Biome biome : biomes) {
            biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(ELEPHANT_ENTITY.get(), 15, 2, 6));
        }
    }
    
    private static void registerGiraffeSpawns() {
        final List<Biome> biomes = Arrays.asList(Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU);
        for (Biome biome : biomes) {
            biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(GIRAFFE_ENTITY.get(), 15, 2, 4));
        }
    }
    
    private static void registerLionSpawns() {
        final List<Biome> biomes = Arrays.asList(Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU);
        for (Biome biome : biomes) {
            biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(LION_ENTITY.get(), 15, 3, 6));
        }
    }
}
