package com.tristankechlo.livingthings.config.entity;

import java.util.Arrays;
import java.util.List;

import com.tristankechlo.livingthings.config.LivingThingsConfig;

import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class KoalaConfig {

	public final DoubleValue health;

	public final ConfigValue<List<? extends String>> spawnBiomes;
	public final IntValue weight;
	public final IntValue minSpawns;
	public final IntValue maxSpawns;

	public KoalaConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Koala").push("Koala");

		health = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().defineInRange("Health", 10.0D, 1.0D, Short.MAX_VALUE);

		builder.comment(LivingThingsConfig.requiresRestart + " | " + LivingThingsConfig.disableSpawning + " | " + LivingThingsConfig.spawningVanilla).push("Spawns");
		spawnBiomes = builder.worldRestart().defineList("SpawnBoimes",
				Arrays.asList(Biomes.JUNGLE.getLocation().toString(),
						Biomes.JUNGLE_EDGE.getLocation().toString(),
						Biomes.JUNGLE_HILLS.getLocation().toString(),
						Biomes.BAMBOO_JUNGLE.getLocation().toString(),
						Biomes.BAMBOO_JUNGLE_HILLS.getLocation().toString(),
						Biomes.MODIFIED_JUNGLE.getLocation().toString(),
						Biomes.MODIFIED_JUNGLE_EDGE.getLocation().toString(),
						Biomes.SAVANNA_PLATEAU.getLocation().toString()),
				biome -> LivingThingsConfig.checkBiome("Koala", biome));
		weight = builder.worldRestart().defineInRange("SpawnWeight", 50, 1, Short.MAX_VALUE);
		minSpawns = builder.worldRestart().defineInRange("MinSpawns", 3, 1, Short.MAX_VALUE);
		maxSpawns = builder.worldRestart().defineInRange("MaxSpawns", 5, 1, Short.MAX_VALUE);
		builder.pop();

		builder.pop();

	}
}
