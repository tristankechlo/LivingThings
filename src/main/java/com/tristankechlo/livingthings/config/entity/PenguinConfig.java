package com.tristankechlo.livingthings.config.entity;

import java.util.Arrays;
import java.util.List;

import com.tristankechlo.livingthings.config.LivingThingsConfig;

import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class PenguinConfig {

	public final DoubleValue health;

	public final ConfigValue<List<? extends String>> spawnBiomes;
	public final IntValue weight;
	public final IntValue minSpawns;
	public final IntValue maxSpawns;

	public PenguinConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Penguin").push("Penguin");
		
		health = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().defineInRange("Health", 10.0D, 1.0D, Short.MAX_VALUE);

		builder.comment(LivingThingsConfig.requiresRestart + " | " + LivingThingsConfig.disableSpawning + " | " + LivingThingsConfig.spawningVanilla).push("Spawns");
		spawnBiomes = builder.worldRestart().defineList("SpawnBoimes",
					Arrays.asList(Biomes.SNOWY_TUNDRA.getLocation().toString(),
						Biomes.SNOWY_MOUNTAINS.getLocation().toString(),
						Biomes.SNOWY_BEACH.getLocation().toString(),
						Biomes.SNOWY_TAIGA.getLocation().toString(),
						Biomes.SNOWY_TAIGA_HILLS.getLocation().toString(),
						Biomes.SNOWY_TAIGA_MOUNTAINS.getLocation().toString()),
					biome -> LivingThingsConfig.checkBiome("Penguin", biome));
		weight = builder.worldRestart().defineInRange("SpawnWeight", 12, 1, Short.MAX_VALUE);
		minSpawns = builder.worldRestart().defineInRange("MinSpawns", 3, 1, Short.MAX_VALUE);
		maxSpawns = builder.worldRestart().defineInRange("MaxSpawns", 6, 1, Short.MAX_VALUE);
		builder.pop();
		
		builder.pop();
		
	}
}
