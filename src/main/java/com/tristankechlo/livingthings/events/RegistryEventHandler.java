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
import com.tristankechlo.livingthings.entities.SeahorseEntity;
import com.tristankechlo.livingthings.entities.SharkEntity;
import com.tristankechlo.livingthings.entities.ShroomieEntity;
import com.tristankechlo.livingthings.entities.SnailEntity;
import com.tristankechlo.livingthings.init.ModEntityTypes;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.SpawnPlacements.Type;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.level.levelgen.Heightmap;
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
		event.put(ModEntityTypes.ELEPHANT.get(), ElephantEntity.createAttributes().build());
		event.put(ModEntityTypes.GIRAFFE.get(), GiraffeEntity.createAttributes().build());
		event.put(ModEntityTypes.LION.get(), LionEntity.createAttributes().build());
		event.put(ModEntityTypes.SHARK.get(), SharkEntity.createAttributes().build());
		event.put(ModEntityTypes.PENGUIN.get(), PenguinEntity.createAttributes().build());
		event.put(ModEntityTypes.OSTRICH.get(), OstrichEntity.createAttributes().build());
		event.put(ModEntityTypes.FLAMINGO.get(), FlamingoEntity.createAttributes().build());
		event.put(ModEntityTypes.CRAB.get(), CrabEntity.createAttributes().build());
		event.put(ModEntityTypes.MANTARAY.get(), MantarayEntity.createAttributes().build());
		event.put(ModEntityTypes.RACCOON.get(), RaccoonEntity.createAttributes().build());
		event.put(ModEntityTypes.OWL.get(), OwlEntity.createAttributes().build());
		event.put(ModEntityTypes.ANCIENT_BLAZE.get(), AncientBlazeEntity.createAttributes().build());
		event.put(ModEntityTypes.KOALA.get(), KoalaEntity.createAttributes().build());
		event.put(ModEntityTypes.SNAIL.get(), SnailEntity.createAttributes().build());
		event.put(ModEntityTypes.MONKEY.get(), MonkeyEntity.createAttributes().build());
		event.put(ModEntityTypes.NETHER_KNIGHT.get(), NetherKnightEntity.createAttributes().build());
		event.put(ModEntityTypes.SHROOMIE.get(), ShroomieEntity.createAttributes().build());
		event.put(ModEntityTypes.SEAHORSE.get(), SeahorseEntity.createAttributes().build());
	}

	private static void registerEntitySpawnPlacements() {
		SpawnPlacements.register(ModEntityTypes.ELEPHANT.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
		SpawnPlacements.register(ModEntityTypes.GIRAFFE.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
		SpawnPlacements.register(ModEntityTypes.LION.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
		SpawnPlacements.register(ModEntityTypes.SHARK.get(), Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SharkEntity::canSharkSpawn);
		SpawnPlacements.register(ModEntityTypes.PENGUIN.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
		SpawnPlacements.register(ModEntityTypes.OSTRICH.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
		SpawnPlacements.register(ModEntityTypes.FLAMINGO.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
		SpawnPlacements.register(ModEntityTypes.CRAB.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CrabEntity::canCrabSpawn);
		SpawnPlacements.register(ModEntityTypes.MANTARAY.get(), Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MantarayEntity::canMantaraySpawn);
		SpawnPlacements.register(ModEntityTypes.RACCOON.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
		SpawnPlacements.register(ModEntityTypes.OWL.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, OwlEntity::canOwlSpawn);
		SpawnPlacements.register(ModEntityTypes.ANCIENT_BLAZE.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(ModEntityTypes.KOALA.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, KoalaEntity::canKoalaSpawn);
		SpawnPlacements.register(ModEntityTypes.SNAIL.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
		SpawnPlacements.register(ModEntityTypes.MONKEY.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, MonkeyEntity::canMonkeySpawn);
		SpawnPlacements.register(ModEntityTypes.NETHER_KNIGHT.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
		SpawnPlacements.register(ModEntityTypes.SHROOMIE.get(), Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ShroomieEntity::canShroomieSpawn);
		SpawnPlacements.register(ModEntityTypes.SEAHORSE.get(), Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AbstractFish::checkFishSpawnRules);
	}

}
