package com.tristankechlo.livingthings.config.entity;

import java.util.Arrays;
import java.util.List;

import com.tristankechlo.livingthings.config.LivingThingsConfig;

import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class OwlConfig {

	public final DoubleValue health;
	public final DoubleValue speed;
	public final DoubleValue flyingSpeed;

	public final ConfigValue<Integer> colorBrownWeight;
	public final ConfigValue<Integer> colorWhiteWeight;
	public final ConfigValue<Integer> colorBlackWeight;

	public final ConfigValue<List<? extends String>> spawnBiomes;
	public final IntValue weight;
	public final IntValue minSpawns;
	public final IntValue maxSpawns;

	public OwlConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Owl").push("Owl");

		health = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart()
				.defineInRange("Health", 10.0D, LivingThingsConfig.MIN_HEALTH, LivingThingsConfig.MAX_HEALTH);

		speed = builder.comment(LivingThingsConfig.REQUIRES_RESTART + " | " + LivingThingsConfig.HIGH_IMPACT).worldRestart()
				.defineInRange("MovementSpeed", 0.25D, LivingThingsConfig.MIN_SPEED, LivingThingsConfig.MAX_SPEED);

		flyingSpeed = builder.comment(LivingThingsConfig.REQUIRES_RESTART + " | " + LivingThingsConfig.HIGH_IMPACT).worldRestart()
				.defineInRange("FlyingSpeed", 0.5D, LivingThingsConfig.MIN_SPEED, LivingThingsConfig.MAX_SPEED);

		builder.comment(LivingThingsConfig.WEIGHTED_RANDOM).push("ColorVariantWeights");
		colorBrownWeight = builder.define("colorBrownWeight", 33);
		colorWhiteWeight = builder.define("colorWhiteWeight", 33);
		colorBlackWeight = builder.define("colorBlackWeight", 33);
		builder.pop();

		builder.comment(LivingThingsConfig.REQUIRES_RESTART + " | " + LivingThingsConfig.DISABLE_SPAWNING + " | " + LivingThingsConfig.SPAWNING_VANILLA).push("Spawns");
		spawnBiomes = builder.worldRestart().defineList("SpawnBoimes",
				Arrays.asList(Biomes.FOREST.getLocation().toString(),
						Biomes.BIRCH_FOREST.getLocation().toString(),
						Biomes.BIRCH_FOREST_HILLS.getLocation().toString(),
						Biomes.TALL_BIRCH_FOREST.getLocation().toString(),
						Biomes.TALL_BIRCH_HILLS.getLocation().toString(),
						Biomes.DARK_FOREST.getLocation().toString(),
						Biomes.DARK_FOREST_HILLS.getLocation().toString(),
						Biomes.FLOWER_FOREST.getLocation().toString(),
						Biomes.WOODED_HILLS.getLocation().toString(),
						Biomes.WOODED_MOUNTAINS.getLocation().toString(),
						Biomes.TAIGA.getLocation().toString(),
						Biomes.TAIGA_HILLS.getLocation().toString(),
						Biomes.TAIGA_MOUNTAINS.getLocation().toString()),
				biome -> LivingThingsConfig.checkBiome("Owl", biome));
		weight = builder.worldRestart().defineInRange("SpawnWeight", 20, 1, Short.MAX_VALUE);
		minSpawns = builder.worldRestart().defineInRange("MinSpawns", 3, 1, Short.MAX_VALUE);
		maxSpawns = builder.worldRestart().defineInRange("MaxSpawns", 6, 1, Short.MAX_VALUE);
		builder.pop();

		builder.pop();

	}
}
