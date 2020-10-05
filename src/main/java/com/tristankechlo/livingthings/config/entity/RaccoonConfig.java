package com.tristankechlo.livingthings.config.entity;

import java.util.Arrays;
import java.util.List;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.init.RegisterEntitiesToBiomes;

import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class RaccoonConfig {

	public final BooleanValue canAttack;
	public final ConfigValue<Double> health;
	public final ConfigValue<Double> damage;

	public final ConfigValue<List<? extends String>> spawnBiomes;
	public final ConfigValue<Integer> weight;
	public final ConfigValue<Integer> minSpawns;
	public final ConfigValue<Integer> maxSpawns;

	public RaccoonConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Raccoon").push("Raccoon");
		
		canAttack = builder.define("CanAttack", true);
		health = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().define("Health", 14.0D);
		damage = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().define("AttackDamage", 3.0D);

		builder.comment(LivingThingsConfig.requiresRestart + " | " + LivingThingsConfig.disableSpawning + " | " + LivingThingsConfig.spawningVanilla).push("Spawns");
		spawnBiomes = builder.worldRestart().defineList("SpawnBoimes",
				Arrays.asList(Biomes.RIVER.getLocation().toString(),
						Biomes.BEACH.getLocation().toString(),
						Biomes.DESERT_LAKES.getLocation().toString(),
						Biomes.SWAMP_HILLS.getLocation().toString(),
						Biomes.SWAMP.getLocation().toString()),
				biome -> RegisterEntitiesToBiomes.checkBiome("Raccoon", biome));
		weight = builder.worldRestart().define("SpawnWeight", 50);
		minSpawns = builder.worldRestart().define("MinSpawns", 5);
		maxSpawns = builder.worldRestart().define("MaxSpawns", 8);
		builder.pop();
		
		builder.pop();
		
	}
}
