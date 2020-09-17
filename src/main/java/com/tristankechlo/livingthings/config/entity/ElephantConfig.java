package com.tristankechlo.livingthings.config.entity;

import java.util.Arrays;
import java.util.List;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.init.RegisterEntitiesToBiomes;

import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class ElephantConfig {

	public final ConfigValue<Double> health;
	public final ConfigValue<Double> damage;
	public final BooleanValue canAttack;

	public final ConfigValue<List<? extends String>> include;
	public final ConfigValue<Integer> weight;
	public final ConfigValue<Integer> minSpawns;
	public final ConfigValue<Integer> maxSpawns;

	public ElephantConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Elephant").push("Elephant");

		canAttack = builder.define("CanAttack", true);
		health = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().define("Health", 80.0D);
		damage = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().define("AttackDamage", 10.0D);

		builder.comment(LivingThingsConfig.requiresRestart + " | " + LivingThingsConfig.disableSpawning).push("Spawns");
		include = builder.worldRestart().defineList("SpawnBoimes",
				Arrays.asList(Biomes.SAVANNA.func_240901_a_().toString(),
						Biomes.SAVANNA_PLATEAU.func_240901_a_().toString(),
						Biomes.SHATTERED_SAVANNA.func_240901_a_().toString(),
						Biomes.SHATTERED_SAVANNA_PLATEAU.func_240901_a_().toString()),
				biome -> RegisterEntitiesToBiomes.checkBiome("Elephant", biome));
		weight = builder.worldRestart().define("SpawnWeight", 15);
		minSpawns = builder.worldRestart().define("MinSpawns", 3);
		maxSpawns = builder.worldRestart().define("MaxSpawns", 5);
		builder.pop();

		builder.pop();

	}
}
