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

public class RaccoonConfig {

	public final BooleanValue canAttack;
	public final DoubleValue health;
	public final DoubleValue speed;
	public final DoubleValue damage;

	public final ConfigValue<List<? extends String>> spawnBiomes;
	public final IntValue weight;
	public final IntValue minSpawns;
	public final IntValue maxSpawns;

	public RaccoonConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Raccoon").push("Raccoon");

		canAttack = builder.define("CanAttack", true);
		health = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart()
				.defineInRange("Health", 10.0D, LivingThingsConfig.MIN_HEALTH, LivingThingsConfig.MAX_HEALTH);

		speed = builder.comment(LivingThingsConfig.REQUIRES_RESTART + " | " + LivingThingsConfig.HIGH_IMPACT).worldRestart()
				.defineInRange("MovementSpeed", 0.23D, LivingThingsConfig.MIN_SPEED, LivingThingsConfig.MAX_SPEED);

		damage = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart()
				.defineInRange("AttackDamage", 2.0D, LivingThingsConfig.MIN_DAMAGE, LivingThingsConfig.MAX_DAMAGE);

		builder.comment(LivingThingsConfig.REQUIRES_RESTART + " | " + LivingThingsConfig.DISABLE_SPAWNING + " | " + LivingThingsConfig.SPAWNING_VANILLA).push("Spawns");
		spawnBiomes = builder.worldRestart().defineList("SpawnBoimes", Arrays.asList(
				Biomes.PLAINS.getLocation().toString(),
				Biomes.FOREST.getLocation().toString(),
				Biomes.BIRCH_FOREST.getLocation().toString(),
				Biomes.BIRCH_FOREST_HILLS.getLocation().toString(),
				Biomes.TALL_BIRCH_FOREST.getLocation().toString(),
				Biomes.TALL_BIRCH_HILLS.getLocation().toString(),
				Biomes.SUNFLOWER_PLAINS.getLocation().toString(),
				Biomes.DARK_FOREST.getLocation().toString(),
				Biomes.DARK_FOREST_HILLS.getLocation().toString(),
				Biomes.FLOWER_FOREST.getLocation().toString()),
				biome -> LivingThingsConfig.checkBiome("Raccoon", biome));
		weight = builder.worldRestart().defineInRange("SpawnWeight", 20, 1, Short.MAX_VALUE);
		minSpawns = builder.worldRestart().defineInRange("MinSpawns", 2, 1, Short.MAX_VALUE);
		maxSpawns = builder.worldRestart().defineInRange("MaxSpawns", 6, 1, Short.MAX_VALUE);
		builder.pop();

		builder.pop();

	}
}
