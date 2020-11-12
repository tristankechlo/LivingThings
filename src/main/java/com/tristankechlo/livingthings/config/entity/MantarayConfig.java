package com.tristankechlo.livingthings.config.entity;

import java.util.Arrays;
import java.util.List;

import com.tristankechlo.livingthings.config.LivingThingsConfig;

import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class MantarayConfig {

	public final DoubleValue health;
	public final DoubleValue speed;

	public final ConfigValue<Integer> color1Weight;
	public final ConfigValue<Integer> color2Weight;

	public final ConfigValue<Integer> scaling1Weight;
	public final ConfigValue<Integer> scaling2Weight;
	public final ConfigValue<Integer> scaling3Weight;
	public final ConfigValue<Integer> scaling4Weight;

	public final ConfigValue<List<? extends String>> spawnBiomes;
	public final IntValue weight;
	public final IntValue minSpawns;
	public final IntValue maxSpawns;

	public MantarayConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Mantaray").push("Mantaray");

		health = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart()
				.defineInRange("Health", 10.0D, LivingThingsConfig.MIN_HEALTH, LivingThingsConfig.MAX_HEALTH);

		speed = builder.comment(LivingThingsConfig.REQUIRES_RESTART + " | " + LivingThingsConfig.HIGH_IMPACT).worldRestart()
				.defineInRange("MovementSpeed", 1.0D, LivingThingsConfig.MIN_SPEED, LivingThingsConfig.MAX_SPEED);

		builder.comment(LivingThingsConfig.WEIGHTED_RANDOM).push("ColorVariantWeights");
		color1Weight = builder.define("Color1Weight", 50);
		color2Weight = builder.define("Color2Weight", 50);
		builder.pop();

		builder.comment(LivingThingsConfig.WEIGHTED_RANDOM).push("ScalingWeights");
		scaling1Weight = builder.define("Scaling1Weight", 25);
		scaling2Weight = builder.define("Scaling2Weight", 25);
		scaling3Weight = builder.define("Scaling3Weight", 25);
		scaling4Weight = builder.define("Scaling4Weight", 25);
		builder.pop();

		builder.comment(LivingThingsConfig.REQUIRES_RESTART + " | " + LivingThingsConfig.DISABLE_SPAWNING + " | " + LivingThingsConfig.SPAWNING_WATER).push("Spawns");
		spawnBiomes = builder.worldRestart().defineList("SpawnBoimes",
				Arrays.asList(Biomes.OCEAN.getLocation().toString(),
						Biomes.DEEP_OCEAN.getLocation().toString(),
						Biomes.FROZEN_OCEAN.getLocation().toString(),
						Biomes.DEEP_FROZEN_OCEAN.getLocation().toString(),
						Biomes.COLD_OCEAN.getLocation().toString(),
						Biomes.DEEP_COLD_OCEAN.getLocation().toString(),
						Biomes.WARM_OCEAN.getLocation().toString(),
						Biomes.DEEP_WARM_OCEAN.getLocation().toString(),
						Biomes.LUKEWARM_OCEAN.getLocation().toString(),
						Biomes.DEEP_LUKEWARM_OCEAN.getLocation().toString()),
				biome -> LivingThingsConfig.checkBiome("Mantaray", biome));
		weight = builder.worldRestart().defineInRange("SpawnWeight", 15, 1, Short.MAX_VALUE);
		minSpawns = builder.worldRestart().defineInRange("MinSpawns", 4, 1, Short.MAX_VALUE);
		maxSpawns = builder.worldRestart().defineInRange("MaxSpawns", 7, 1, Short.MAX_VALUE);
		builder.pop();

		builder.pop();

	}
}
