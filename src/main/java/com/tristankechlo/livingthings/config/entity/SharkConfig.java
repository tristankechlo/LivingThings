package com.tristankechlo.livingthings.config.entity;

import java.util.Arrays;
import java.util.List;

import com.tristankechlo.livingthings.config.LivingThingsConfig;

import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class SharkConfig {

	public final BooleanValue canAttack;
	public final DoubleValue health;
	public final DoubleValue speed;
	public final DoubleValue damage;

	public final ConfigValue<List<? extends String>> spawnBiomes;
	public final IntValue weight;
	public final IntValue minSpawns;
	public final IntValue maxSpawns;

	public SharkConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Shark").push("Shark");

		canAttack = builder.define("CanAttack", true);
		health = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart()
				.defineInRange("Health", 22.0D, LivingThingsConfig.MIN_HEALTH, LivingThingsConfig.MAX_HEALTH);

		speed = builder.comment(LivingThingsConfig.REQUIRES_RESTART + " | " + LivingThingsConfig.HIGH_IMPACT).worldRestart()
				.defineInRange("MovementSpeed", 1.05D, LivingThingsConfig.MIN_SPEED, LivingThingsConfig.MAX_SPEED);

		damage = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart()
				.defineInRange("AttackDamage", 6.0D, LivingThingsConfig.MIN_DAMAGE, LivingThingsConfig.MAX_DAMAGE);

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
				biome -> LivingThingsConfig.checkBiome("Shark", biome));
		weight = builder.worldRestart().defineInRange("SpawnWeight", 13, 1, Short.MAX_VALUE);
		minSpawns = builder.worldRestart().defineInRange("MinSpawns", 2, 1, Short.MAX_VALUE);
		maxSpawns = builder.worldRestart().defineInRange("MaxSpawns", 3, 1, Short.MAX_VALUE);
		builder.pop();

		builder.pop();

	}
}
