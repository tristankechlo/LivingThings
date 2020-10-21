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

public class CrabConfig {

	public final BooleanValue canAttack;
	public final DoubleValue health;
	public final DoubleValue damage;	
	
	public final ConfigValue<Integer> color1Weight;
	public final ConfigValue<Integer> color2Weight;
	public final ConfigValue<Integer> colorAlbinoWeight;
	
	public final ConfigValue<Integer> scaling1Weight;
	public final ConfigValue<Integer> scaling2Weight;
	public final ConfigValue<Integer> scaling3Weight;
	public final ConfigValue<Integer> scaling4Weight;

	public final ConfigValue<List<? extends String>> spawnBiomes;
	public final IntValue weight;
	public final IntValue minSpawns;
	public final IntValue maxSpawns;

	public CrabConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Crab").push("Crab");
		
		canAttack = builder.define("CanAttack", true);
		health = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().defineInRange("Health", 8.0D, 1.0D, Short.MAX_VALUE);
		damage = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().defineInRange("AttackDamage", 1.0D, 1.0D, Short.MAX_VALUE);
		
		builder.comment(LivingThingsConfig.weightedRandom).push("ColorVariantWeights");
		color1Weight = builder.define("Color1Weight", 50);
		color2Weight = builder.define("Color2Weight", 50);
		colorAlbinoWeight = builder.define("AlbinoWeight", 1);
		builder.pop();
		
		builder.comment(LivingThingsConfig.weightedRandom).push("ScalingWeights");
		scaling1Weight = builder.define("Scaling1Weight", 30);
		scaling2Weight = builder.define("Scaling2Weight", 30);
		scaling3Weight = builder.define("Scaling3Weight", 25);
		scaling4Weight = builder.define("Scaling4Weight", 15);
		builder.pop();

		builder.comment(LivingThingsConfig.requiresRestart + " | " + LivingThingsConfig.disableSpawning + " | can spawn on grass/dirt and sand blocks").push("Spawns");
		spawnBiomes = builder.worldRestart().defineList("SpawnBoimes",
				Arrays.asList(Biomes.RIVER.getLocation().toString(),
						Biomes.BEACH.getLocation().toString(),
						Biomes.DESERT_LAKES.getLocation().toString(),
						Biomes.SWAMP_HILLS.getLocation().toString(),
						Biomes.SWAMP.getLocation().toString()),
				biome -> LivingThingsConfig.checkBiome("Crab", biome));
		weight = builder.worldRestart().defineInRange("SpawnWeight", 50, 1, Short.MAX_VALUE);
		minSpawns = builder.worldRestart().defineInRange("MinSpawns", 5, 1, Short.MAX_VALUE);
		maxSpawns = builder.worldRestart().defineInRange("MaxSpawns", 8, 1, Short.MAX_VALUE);
		builder.pop();
		
		builder.pop();
		
	}
}
