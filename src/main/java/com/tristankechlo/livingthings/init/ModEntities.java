package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.entities.ElephantEntity;

import java.util.List;

import com.google.common.collect.Lists;
import com.tristankechlo.livingthings.LivingThings;

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
			() -> EntityType.Builder.create(ElephantEntity::new, EntityClassification.AMBIENT)
					.size(1.85F, 2.7F).build((new ResourceLocation(LivingThings.MOD_ID, "elephant")).toString()));


	/*
	 * register Attributes like Health/Speed ... 
	 */
	public static void registerAttributes(){
		GlobalEntityTypeAttributes.put(ELEPHANT_ENTITY.get(), ElephantEntity.func_234200_m_().func_233813_a_());
	}

	/*
	 * where it is allowed to spawn the mobs
	 */
	public static void registerEntitySpawnPlacements() {
		EntitySpawnPlacementRegistry.register(ELEPHANT_ENTITY.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
	}

	/*
	 * add Entities to Biomes to spawn in
	 */
    public static void registerEntitySpawns() {
    	registerElephantSpawns();
    }
    
    private static void registerElephantSpawns() {
        List<Biome> biomes = Lists.newArrayList();
		biomes.add(Biomes.SAVANNA);
		biomes.add(Biomes.SAVANNA_PLATEAU);
        for (Biome biome : biomes) {
            biome.getSpawns(EntityClassification.AMBIENT).add(new Biome.SpawnListEntry(ELEPHANT_ENTITY.get(), 6, 2, 6));
        }
    }
}
