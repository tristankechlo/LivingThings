package com.tristankechlo.livingthings.init;

import java.util.List;

import org.apache.logging.log4j.Level;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;

import net.minecraft.entity.EntityClassification;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.MobSpawnInfo.Spawners;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = LivingThings.MOD_ID)
public class RegisterEntitiesToBiomes {

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onBiomeLoading(BiomeLoadingEvent event) {

		String currentBiome = event.getName().toString();

		List<? extends String> ElephantSpawnBiomes = LivingThingsConfig.ELEPHANT.spawnBiomes.get();
		List<? extends String> GiraffeSpawnBiomes = LivingThingsConfig.GIRAFFE.spawnBiomes.get();
		List<? extends String> LionSpawnBiomes = LivingThingsConfig.LION.spawnBiomes.get();
		List<? extends String> SharkSpawnBiomes = LivingThingsConfig.SHARK.spawnBiomes.get();
		List<? extends String> PenguinSpawnBiomes = LivingThingsConfig.PENGUIN.spawnBiomes.get();
		List<? extends String> OstrichSpawnBiomes = LivingThingsConfig.OSTRICH.spawnBiomes.get();
		List<? extends String> FlamingoSpawnBiomes = LivingThingsConfig.FLAMINGO.spawnBiomes.get();
		List<? extends String> CrabSpawnBiomes = LivingThingsConfig.CRAB.spawnBiomes.get();
		List<? extends String> MantaraySpawnBiomes = LivingThingsConfig.MANTARAY.spawnBiomes.get();
		List<? extends String> RaccoonSpawnBiomes = LivingThingsConfig.RACCOON.spawnBiomes.get();
		List<? extends String> OwlSpawnBiomes = LivingThingsConfig.OWL.spawnBiomes.get();

		if (ElephantSpawnBiomes.contains(currentBiome)) {
			event.getSpawns().getSpawner(EntityClassification.CREATURE)
					.add(new Spawners(ModEntityTypes.ELEPHANT_ENTITY.get(),
							LivingThingsConfig.ELEPHANT.weight.get(),
							LivingThingsConfig.ELEPHANT.minSpawns.get(),
							LivingThingsConfig.ELEPHANT.maxSpawns.get()));
		}

		if (GiraffeSpawnBiomes.contains(currentBiome)) {
			event.getSpawns().getSpawner(EntityClassification.CREATURE)
					.add(new Spawners(ModEntityTypes.GIRAFFE_ENTITY.get(),
							LivingThingsConfig.GIRAFFE.weight.get(),
							LivingThingsConfig.GIRAFFE.minSpawns.get(),
							LivingThingsConfig.GIRAFFE.maxSpawns.get()));
		}

		if (LionSpawnBiomes.contains(currentBiome)) {
			event.getSpawns().getSpawner(EntityClassification.CREATURE)
					.add(new Spawners(ModEntityTypes.LION_ENTITY.get(), 
							LivingThingsConfig.LION.weight.get(),
							LivingThingsConfig.LION.minSpawns.get(),
							LivingThingsConfig.LION.maxSpawns.get()));
		}

		if (SharkSpawnBiomes.contains(currentBiome)) {
			event.getSpawns().getSpawner(EntityClassification.WATER_CREATURE)
					.add(new Spawners(ModEntityTypes.SHARK_ENTITY.get(), 
							LivingThingsConfig.SHARK.weight.get(),
							LivingThingsConfig.SHARK.minSpawns.get(),
							LivingThingsConfig.SHARK.maxSpawns.get()));
		}

		if (PenguinSpawnBiomes.contains(currentBiome)) {
			event.getSpawns().getSpawner(EntityClassification.CREATURE)
					.add(new Spawners(ModEntityTypes.PENGUIN_ENTITY.get(),
							LivingThingsConfig.PENGUIN.weight.get(),
							LivingThingsConfig.PENGUIN.minSpawns.get(),
							LivingThingsConfig.PENGUIN.maxSpawns.get()));
		}

		if (OstrichSpawnBiomes.contains(currentBiome)) {
			event.getSpawns().getSpawner(EntityClassification.CREATURE)
					.add(new Spawners(ModEntityTypes.OSTRICH_ENTITY.get(), 
							LivingThingsConfig.OSTRICH.weight.get(),
							LivingThingsConfig.OSTRICH.minSpawns.get(), 
							LivingThingsConfig.OSTRICH.maxSpawns.get()));
		}

		if (FlamingoSpawnBiomes.contains(currentBiome)) {
			event.getSpawns().getSpawner(EntityClassification.CREATURE)
					.add(new Spawners(ModEntityTypes.FLAMINGO_ENTITY.get(), 
							LivingThingsConfig.FLAMINGO.weight.get(),
							LivingThingsConfig.FLAMINGO.minSpawns.get(), 
							LivingThingsConfig.FLAMINGO.maxSpawns.get()));
		}

		if (CrabSpawnBiomes.contains(currentBiome)) {
			event.getSpawns().getSpawner(EntityClassification.CREATURE)
					.add(new Spawners(ModEntityTypes.CRAB_ENTITY.get(), 
							LivingThingsConfig.CRAB.weight.get(),
							LivingThingsConfig.CRAB.minSpawns.get(), 
							LivingThingsConfig.CRAB.maxSpawns.get()));
		}

		if (MantaraySpawnBiomes.contains(currentBiome)) {
			event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT)
					.add(new Spawners(ModEntityTypes.MANTARAY_ENTITY.get(), 
							LivingThingsConfig.MANTARAY.weight.get(),
							LivingThingsConfig.MANTARAY.minSpawns.get(),
							LivingThingsConfig.MANTARAY.maxSpawns.get()));
		}

		if (RaccoonSpawnBiomes.contains(currentBiome)) {
			event.getSpawns().getSpawner(EntityClassification.CREATURE)
					.add(new Spawners(ModEntityTypes.RACCOON_ENTITY.get(), 
							LivingThingsConfig.RACCOON.weight.get(),
							LivingThingsConfig.RACCOON.minSpawns.get(), 
							LivingThingsConfig.RACCOON.maxSpawns.get()));
		}

		if (OwlSpawnBiomes.contains(currentBiome)) {
			event.getSpawns().getSpawner(EntityClassification.CREATURE)
					.add(new Spawners(ModEntityTypes.OWL_ENTITY.get(), 
							LivingThingsConfig.OWL.weight.get(),
							LivingThingsConfig.OWL.minSpawns.get(), 
							LivingThingsConfig.OWL.maxSpawns.get()));
		}

	}

	public static boolean checkBiome(String name, Object test) {
		if (ForgeRegistries.BIOMES.containsKey(new ResourceLocation(String.valueOf(test)))) {
			//LivingThings.LOGGER.log(Level.INFO, name + " " + String.valueOf(test));
			return true;
		}
		LivingThings.LOGGER.log(Level.INFO,
				"Removing unknown Biome[" + String.valueOf(test) + "] from " + name + "-SpawnBiomes");
		return false;
	}

}
