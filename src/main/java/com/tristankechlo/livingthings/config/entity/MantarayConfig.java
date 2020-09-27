package com.tristankechlo.livingthings.config.entity;

import java.util.Arrays;
import java.util.List;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.init.RegisterEntitiesToBiomes;

import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class MantarayConfig {

	public final ConfigValue<Double> health;
	
	public final ConfigValue<Integer> color1Weight;
	public final ConfigValue<Integer> color2Weight;
	public final ConfigValue<Integer> color3Weight;

	public final ConfigValue<List<? extends String>> spawnBiomes;
	public final ConfigValue<Integer> weight;
	public final ConfigValue<Integer> minSpawns;
	public final ConfigValue<Integer> maxSpawns;

	public MantarayConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Mantaray").push("Mantaray");
		
		health = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().define("Health", 12.0D);
		
		builder.comment(LivingThingsConfig.weightedRandom).push("ColorVariantWeights");
		color1Weight = builder.define("Color1Weight", 33);
		color2Weight = builder.define("Color2Weight", 33);
		color3Weight = builder.define("Color3Weight", 33);
		builder.pop();

		builder.comment(LivingThingsConfig.requiresRestart + " | " + LivingThingsConfig.disableSpawning + " | " + LivingThingsConfig.spawningWater).push("Spawns");
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
					biome -> RegisterEntitiesToBiomes.checkBiome("Mantaray", biome));
		weight = builder.worldRestart().define("SpawnWeight", 12);
		minSpawns = builder.worldRestart().define("MinSpawns", 2);
		maxSpawns = builder.worldRestart().define("MaxSpawns", 3);
		builder.pop();
		
		builder.pop();
		
	}
}
