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

		if (ElephantSpawnBiomes.contains(currentBiome)) {
			event.getSpawns().getSpawner(EntityClassification.CREATURE)
					.add(new Spawners(ModEntityTypes.ELEPHANT_ENTITY,
							Math.max(1, LivingThingsConfig.ELEPHANT.weight.get()),
							Math.max(1, LivingThingsConfig.ELEPHANT.minSpawns.get()),
							Math.max(1, LivingThingsConfig.ELEPHANT.maxSpawns.get())));
		}

		if (GiraffeSpawnBiomes.contains(currentBiome)) {
			event.getSpawns().getSpawner(EntityClassification.CREATURE)
					.add(new Spawners(ModEntityTypes.GIRAFFE_ENTITY,
							Math.max(1, LivingThingsConfig.GIRAFFE.weight.get()),
							Math.max(1, LivingThingsConfig.GIRAFFE.minSpawns.get()),
							Math.max(1, LivingThingsConfig.GIRAFFE.maxSpawns.get())));
		}

		if (LionSpawnBiomes.contains(currentBiome)) {
			event.getSpawns().getSpawner(EntityClassification.CREATURE)
					.add(new Spawners(ModEntityTypes.LION_ENTITY, 
							Math.max(1,LivingThingsConfig.LION.weight.get()),
							Math.max(1, LivingThingsConfig.LION.minSpawns.get()),
							Math.max(1, LivingThingsConfig.LION.maxSpawns.get())));
		}

		if (SharkSpawnBiomes.contains(currentBiome)) {
			event.getSpawns().getSpawner(EntityClassification.WATER_CREATURE)
					.add(new Spawners(ModEntityTypes.SHARK_ENTIY, 
							Math.max(1,	LivingThingsConfig.SHARK.weight.get()),
							Math.max(1, LivingThingsConfig.SHARK.minSpawns.get()),
							Math.max(1, LivingThingsConfig.SHARK.maxSpawns.get())));
		}

		if (PenguinSpawnBiomes.contains(currentBiome)) {
			event.getSpawns().getSpawner(EntityClassification.CREATURE)
					.add(new Spawners(ModEntityTypes.PENGUIN_ENTITY,
							Math.max(1, LivingThingsConfig.PENGUIN.weight.get()),
							Math.max(1, LivingThingsConfig.PENGUIN.minSpawns.get()),
							Math.max(1, LivingThingsConfig.PENGUIN.maxSpawns.get())));
		}

		if (OstrichSpawnBiomes.contains(currentBiome)) {
			event.getSpawns().getSpawner(EntityClassification.CREATURE)
					.add(new Spawners(ModEntityTypes.OSTRICH_ENTIY, 
							Math.max(1, LivingThingsConfig.OSTRICH.weight.get()),
							Math.max(1, LivingThingsConfig.OSTRICH.minSpawns.get()), 
							Math.max(1, LivingThingsConfig.OSTRICH.maxSpawns.get())));
		}

		if (FlamingoSpawnBiomes.contains(currentBiome)) {
			event.getSpawns().getSpawner(EntityClassification.CREATURE)
					.add(new Spawners(ModEntityTypes.FLAMINGO_ENTIY, 
							Math.max(1, LivingThingsConfig.FLAMINGO.weight.get()),
							Math.max(1, LivingThingsConfig.FLAMINGO.minSpawns.get()), 
							Math.max(1, LivingThingsConfig.FLAMINGO.maxSpawns.get())));
		}

		if (CrabSpawnBiomes.contains(currentBiome)) {
			event.getSpawns().getSpawner(EntityClassification.CREATURE)
					.add(new Spawners(ModEntityTypes.CRAB_ENTITY, 
							Math.max(1, LivingThingsConfig.CRAB.weight.get()),
							Math.max(1, LivingThingsConfig.CRAB.minSpawns.get()), 
							Math.max(1, LivingThingsConfig.CRAB.maxSpawns.get())));
		}

		if (MantaraySpawnBiomes.contains(currentBiome)) {
			event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT)
					.add(new Spawners(ModEntityTypes.MANTARAY_ENTITY, 
							Math.max(1,	LivingThingsConfig.MANTARAY.weight.get()),
							Math.max(1, LivingThingsConfig.MANTARAY.minSpawns.get()),
							Math.max(1, LivingThingsConfig.MANTARAY.maxSpawns.get())));
		}

	}

	public static boolean checkBiome(String name, Object test) {
		if (ForgeRegistries.BIOMES.containsKey(new ResourceLocation(String.valueOf(test)))) {
			//LivingThings.LOGGER.log(Level.INFO, name + " " + String.valueOf(test));
			return true;
		}
		LivingThings.LOGGER.log(Level.INFO,
				"Removing unknown Biome[" + String.valueOf(test) + "] from " + name + "-SpawnBoimes");
		return false;
	}

}
