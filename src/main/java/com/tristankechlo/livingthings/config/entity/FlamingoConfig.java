package com.tristankechlo.livingthings.config.entity;

import java.util.Arrays;
import java.util.List;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.init.RegisterEntitiesToBiomes;

import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class FlamingoConfig {

	public final ConfigValue<Double> health;

	public final ConfigValue<List<? extends String>> include;
	public final ConfigValue<Integer> weight;
	public final ConfigValue<Integer> minSpawns;
	public final ConfigValue<Integer> maxSpawns;

	public FlamingoConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Flamingo").push("Flamingo");

		health = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().define("Health", 16.0D);

		builder.comment(LivingThingsConfig.requiresRestart + " | " + LivingThingsConfig.disableSpawning).push("Spawns");
		include = builder.worldRestart().defineList("SpawnBoimes",
						Arrays.asList(
							Biomes.SWAMP.func_240901_a_().toString(),
							Biomes.SWAMP_HILLS.func_240901_a_().toString(),
							Biomes.RIVER.func_240901_a_().toString()),
						biome -> RegisterEntitiesToBiomes.checkBiome("Flamingo", biome));
		weight = builder.worldRestart().define("SpawnWeight", 24);
		minSpawns = builder.worldRestart().define("MinSpawns", 3);
		maxSpawns = builder.worldRestart().define("MaxSpawns", 6);
		builder.pop();

		builder.pop();

	}
}
