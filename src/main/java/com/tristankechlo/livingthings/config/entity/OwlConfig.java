package com.tristankechlo.livingthings.config.entity;

import java.util.Arrays;
import java.util.List;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.init.RegisterEntitiesToBiomes;

import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class OwlConfig {

	public final ConfigValue<Double> health;

	public final ConfigValue<Integer> colorBrownWeight;
	public final ConfigValue<Integer> colorWhiteWeight;
	public final ConfigValue<Integer> colorBlackWeight;

	public final ConfigValue<List<? extends String>> spawnBiomes;
	public final ConfigValue<Integer> weight;
	public final ConfigValue<Integer> minSpawns;
	public final ConfigValue<Integer> maxSpawns;

	public OwlConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Owl").push("Owl");
		
		health = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().define("Health", 10.0D);

		builder.comment(LivingThingsConfig.weightedRandom).push("ColorVariantWeights");
		colorBrownWeight = builder.define("colorBrownWeight", 33);
		colorWhiteWeight = builder.define("colorWhiteWeight", 33);
		colorBlackWeight = builder.define("colorBlackWeight", 33);
		builder.pop();

		builder.comment(LivingThingsConfig.requiresRestart + " | " + LivingThingsConfig.disableSpawning + " | " + LivingThingsConfig.spawningVanilla).push("Spawns");
		spawnBiomes = builder.worldRestart().defineList("SpawnBoimes",
				Arrays.asList(Biomes.FOREST.getLocation().toString(),
						Biomes.BIRCH_FOREST.getLocation().toString(),
						Biomes.BIRCH_FOREST_HILLS.getLocation().toString(),
						Biomes.TALL_BIRCH_FOREST.getLocation().toString(),
						Biomes.TALL_BIRCH_HILLS.getLocation().toString(),
						Biomes.DARK_FOREST.getLocation().toString(),
						Biomes.DARK_FOREST_HILLS.getLocation().toString(),
						Biomes.FLOWER_FOREST.getLocation().toString()),
				biome -> RegisterEntitiesToBiomes.checkBiome("Owl", biome));
		weight = builder.worldRestart().define("SpawnWeight", 20);
		minSpawns = builder.worldRestart().define("MinSpawns", 2);
		maxSpawns = builder.worldRestart().define("MaxSpawns", 4);
		builder.pop();
		
		builder.pop();
		
	}
}
