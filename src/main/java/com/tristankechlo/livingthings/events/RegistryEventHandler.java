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
import com.tristankechlo.livingthings.entities.MonkeyEntity;
import com.tristankechlo.livingthings.entities.NetherKnightEntity;
import com.tristankechlo.livingthings.entities.OstrichEntity;
import com.tristankechlo.livingthings.entities.OwlEntity;
import com.tristankechlo.livingthings.entities.PenguinEntity;
import com.tristankechlo.livingthings.entities.RaccoonEntity;
import com.tristankechlo.livingthings.entities.SharkEntity;
import com.tristankechlo.livingthings.entities.ShroomieEntity;
import com.tristankechlo.livingthings.entities.SnailEntity;
import com.tristankechlo.livingthings.init.ModEntityTypes;

import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntitySpawnPlacementRegistry.PlacementType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LivingThings.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEventHandler {

	@SubscribeEvent
	public static void onEntityRegistry(final RegistryEvent.Register<EntityType<?>> event) {
		registerEntitySpawnPlacements();
	}

	// add attributes like health to entities
	@SubscribeEvent
	public static void onAttributeRegister(final EntityAttributeCreationEvent event) {
		event.put(ModEntityTypes.ELEPHANT_ENTITY.get(), ElephantEntity.createAttributes().build());
		event.put(ModEntityTypes.GIRAFFE_ENTITY.get(), GiraffeEntity.createAttributes().build());
		event.put(ModEntityTypes.LION_ENTITY.get(), LionEntity.createAttributes().build());
		event.put(ModEntityTypes.SHARK_ENTITY.get(), SharkEntity.createAttributes().build());
		event.put(ModEntityTypes.PENGUIN_ENTITY.get(), PenguinEntity.createAttributes().build());
		event.put(ModEntityTypes.OSTRICH_ENTITY.get(), OstrichEntity.createAttributes().build());
		event.put(ModEntityTypes.FLAMINGO_ENTITY.get(), FlamingoEntity.createAttributes().build());
		event.put(ModEntityTypes.CRAB_ENTITY.get(), CrabEntity.createAttributes().build());
		event.put(ModEntityTypes.MANTARAY_ENTITY.get(), MantarayEntity.createAttributes().build());
		event.put(ModEntityTypes.RACCOON_ENTITY.get(), RaccoonEntity.createAttributes().build());
		event.put(ModEntityTypes.OWL_ENTITY.get(), OwlEntity.createAttributes().build());
		event.put(ModEntityTypes.ANCIENT_BLAZE_ENTITY.get(), AncientBlazeEntity.createAttributes().build());
		event.put(ModEntityTypes.KOALA_ENTITY.get(), KoalaEntity.createAttributes().build());
		event.put(ModEntityTypes.SNAIL_ENTITY.get(), SnailEntity.createAttributes().build());
		event.put(ModEntityTypes.MONKEY_ENTITY.get(), MonkeyEntity.createAttributes().build());
		event.put(ModEntityTypes.NETHER_KNIGHT_ENTITY.get(), NetherKnightEntity.createAttributes().build());
		event.put(ModEntityTypes.SHROOMIE_ENTITY.get(), ShroomieEntity.createAttributes().build());
	}

	private static void registerEntitySpawnPlacements() {
		EntitySpawnPlacementRegistry.register(ModEntityTypes.ELEPHANT_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.GIRAFFE_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.LION_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.SHARK_ENTITY.get(), PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SharkEntity::canSharkSpawn);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.PENGUIN_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.OSTRICH_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.FLAMINGO_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.CRAB_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CrabEntity::canCrabSpawn);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.MANTARAY_ENTITY.get(), PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MantarayEntity::canMantaraySpawn);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.RACCOON_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.OWL_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, OwlEntity::canOwlSpawn);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.ANCIENT_BLAZE_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.KOALA_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, KoalaEntity::canKoalaSpawn);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.SNAIL_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.MONKEY_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, MonkeyEntity::canMonkeySpawn);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.NETHER_KNIGHT_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
		EntitySpawnPlacementRegistry.register(ModEntityTypes.SHROOMIE_ENTITY.get(), PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ShroomieEntity::canShroomieSpawn);
	}

}
