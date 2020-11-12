package com.tristankechlo.livingthings.config.entity;

import java.util.Arrays;
import java.util.List;

import com.tristankechlo.livingthings.config.LivingThingsConfig;

import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class FlamingoConfig {

	public final DoubleValue health;
	public final DoubleValue speed;

	public final ConfigValue<List<? extends String>> spawnBiomes;
	public final IntValue weight;
	public final IntValue minSpawns;
	public final IntValue maxSpawns;

	public FlamingoConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Flamingo").push("Flamingo");

		health = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart()
				.defineInRange("Health", 10.0D, LivingThingsConfig.MIN_HEALTH, LivingThingsConfig.MAX_HEALTH);

		speed = builder.comment(LivingThingsConfig.REQUIRES_RESTART + " | " + LivingThingsConfig.HIGH_IMPACT).worldRestart()
				.defineInRange("MovementSpeed", 0.25D, LivingThingsConfig.MIN_SPEED, LivingThingsConfig.MAX_SPEED);

		builder.comment(LivingThingsConfig.REQUIRES_RESTART + " | " + LivingThingsConfig.DISABLE_SPAWNING + " | " + LivingThingsConfig.SPAWNING_VANILLA).push("Spawns");
		spawnBiomes = builder.worldRestart().defineList("SpawnBoimes",
				Arrays.asList(Biomes.SWAMP.getLocation().toString(),
						Biomes.SWAMP_HILLS.getLocation().toString(),
						Biomes.RIVER.getLocation().toString()),
				biome -> LivingThingsConfig.checkBiome("Flamingo", biome));
		weight = builder.worldRestart().defineInRange("SpawnWeight", 24, 1, Short.MAX_VALUE);
		minSpawns = builder.worldRestart().defineInRange("MinSpawns", 3, 1, Short.MAX_VALUE);
		maxSpawns = builder.worldRestart().defineInRange("MaxSpawns", 6, 1, Short.MAX_VALUE);
		builder.pop();

		builder.pop();

	}
}
