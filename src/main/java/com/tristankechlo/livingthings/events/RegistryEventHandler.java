package com.tristankechlo.livingthings.events;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.entities.AncientBlazeEntity;
import com.tristankechlo.livingthings.entities.CrabEntity;
import com.tristankechlo.livingthings.entities.ElephantEntity;
import com.tristankechlo.livingthings.entities.FlamingoEntity;
import com.tristankechlo.livingthings.entities.GiraffeEntity;
import com.tristankechlo.livingthings.entities.KoalaEntity;
import com.tristankechlo.livingthings.entities.LionEntity;
import com.tristankechlo.livingthings.entities.MantarayEntity;
import com.tristankechlo.livingthings.entities.OstrichEntity;
import com.tristankechlo.livingthings.entities.OwlEntity;
import com.tristankechlo.livingthings.entities.PenguinEntity;
import com.tristankechlo.livingthings.entities.RaccoonEntity;
import com.tristankechlo.livingthings.entities.SharkEntity;
import com.tristankechlo.livingthings.init.ModEntityTypes;

import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntitySpawnPlacementRegistry.PlacementType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LivingThings.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEventHandler {

	@SubscribeEvent
	public static void onEntityRegistry(RegistryEvent.Register<EntityType<?>> event) {
		registerEntityAttributes();
		registerEntitySpawnPlacements();
	}

	/**
	 * register Attributes like Health/Speed ...
	 */
	private static void registerEntityAttributes() {
		GlobalEntityTypeAttributes.put(ModEntityTypes.ELEPHANT_ENTITY.get(), ElephantEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(ModEntityTypes.GIRAFFE_ENTITY.get(), GiraffeEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(ModEntityTypes.LION_ENTITY.get(), LionEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(ModEntityTypes.SHARK_ENTITY.get(), SharkEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(ModEntityTypes.PENGUIN_ENTITY.get(), PenguinEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(ModEntityTypes.OSTRICH_ENTITY.get(), OstrichEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(ModEntityTypes.FLAMINGO_ENTITY.get(), FlamingoEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(ModEntityTypes.CRAB_ENTITY.get(), CrabEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(ModEntityTypes.MANTARAY_ENTITY.get(), MantarayEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(ModEntityTypes.RACCOON_ENTITY.get(), RaccoonEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(ModEntityTypes.OWL_ENTITY.get(), OwlEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(ModEntityTypes.ANCIENT_BLAZE_ENTITY.get(), AncientBlazeEntity.getAttributes().create());
		GlobalEntityTypeAttributes.put(ModEntityTypes.KOALA_ENTITY.get(), KoalaEntity.getAttributes().create());
	}

	/**
	 * EntitySpawnPlacementRegistry
	 */
	private static void registerEntitySpawnPlacements() {
		EntitySpawnPlacementRegistry.register(ModEntityTypes.ELEPHANT_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.GIRAFFE_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.LION_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.SHARK_ENTITY.get(), PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SharkEntity::canSharkSpawn);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.PENGUIN_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.OSTRICH_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.FLAMINGO_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.CRAB_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CrabEntity::canCrabSpawn);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.MANTARAY_ENTITY.get(), PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MantarayEntity::canMantaraySpawn);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.RACCOON_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.OWL_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, OwlEntity::canOwlSpawn);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.ANCIENT_BLAZE_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.KOALA_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
	}

}
