package com.tristankechlo.livingthings.config.entity;

import java.util.Arrays;
import java.util.List;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.init.RegisterEntitiesToBiomes;

import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class OstrichConfig {

	public final ConfigValue<Double> health;
	public final BooleanValue canBeRidden;

	public final ConfigValue<List<? extends String>> spawnBiomes;
	public final ConfigValue<Integer> weight;
	public final ConfigValue<Integer> minSpawns;
	public final ConfigValue<Integer> maxSpawns;

	public OstrichConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Ostrich").push("Ostrich");
		
		canBeRidden = builder.define("CanBeRidden", true);
		health = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().define("Health", 16.0D);

		builder.comment(LivingThingsConfig.requiresRestart + " | " + LivingThingsConfig.disableSpawning + " | " + LivingThingsConfig.spawningVanilla).push("Spawns");
		spawnBiomes = builder.worldRestart().defineList("SpawnBoimes",
				Arrays.asList(Biomes.SAVANNA.func_240901_a_().toString(),
						Biomes.SAVANNA_PLATEAU.func_240901_a_().toString(),
						Biomes.SHATTERED_SAVANNA.func_240901_a_().toString(),
						Biomes.SHATTERED_SAVANNA_PLATEAU.func_240901_a_().toString()),
				biome -> RegisterEntitiesToBiomes.checkBiome("Ostrich", biome));
		weight = builder.worldRestart().define("SpawnWeight", 15);
		minSpawns = builder.worldRestart().define("MinSpawns", 3);
		maxSpawns = builder.worldRestart().define("MaxSpawns", 5);
		builder.pop();
		
		builder.pop();
		
	}
}
