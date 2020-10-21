package com.tristankechlo.livingthings.config.entity;

import java.util.Arrays;
import java.util.List;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.init.RegisterEntitiesToBiomes;

import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class FlamingoConfig {

	public final DoubleValue health;

	public final ConfigValue<List<? extends String>> spawnBiomes;
	public final IntValue weight;
	public final IntValue minSpawns;
	public final IntValue maxSpawns;

	public FlamingoConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Flamingo").push("Flamingo");

		health = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().defineInRange("Health", 10.0D, 1.0D, Short.MAX_VALUE);

		builder.comment(LivingThingsConfig.requiresRestart + " | " + LivingThingsConfig.disableSpawning + " | " + LivingThingsConfig.spawningVanilla).push("Spawns");
		spawnBiomes = builder.worldRestart().defineList("SpawnBoimes",
						Arrays.asList(
							Biomes.SWAMP.getLocation().toString(),
							Biomes.SWAMP_HILLS.getLocation().toString(),
							Biomes.RIVER.getLocation().toString()),
						biome -> RegisterEntitiesToBiomes.checkBiome("Flamingo", biome));
		weight = builder.worldRestart().defineInRange("SpawnWeight", 24, 1, Short.MAX_VALUE);
		minSpawns = builder.worldRestart().defineInRange("MinSpawns", 3, 1, Short.MAX_VALUE);
		maxSpawns = builder.worldRestart().defineInRange("MaxSpawns", 6, 1, Short.MAX_VALUE);
		builder.pop();

		builder.pop();

	}
}
