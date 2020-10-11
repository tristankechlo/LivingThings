package com.tristankechlo.livingthings.config.entity;

import java.util.Arrays;
import java.util.List;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.init.RegisterEntitiesToBiomes;

import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class LionConfig {
	
	public final BooleanValue canAttack;
	public final ConfigValue<Double> health;
	public final ConfigValue<Double> damage;

	public final ConfigValue<Integer> genderMaleWeight;
	public final ConfigValue<Integer> genderFemaleWeight;

	public final ConfigValue<Integer> color1Weight;
	public final ConfigValue<Integer> colorAlbinoWeight;

	public final ConfigValue<List<? extends String>> spawnBiomes;
	public final ConfigValue<Integer> weight;
	public final ConfigValue<Integer> minSpawns;
	public final ConfigValue<Integer> maxSpawns;

	public LionConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Lion").push("Lion");
		
		canAttack = builder.define("CanAttack", true);
		health = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().define("Health", 20.0D);
		damage = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().define("AttackDamage", 5.0D);

		builder.comment(LivingThingsConfig.weightedRandom).push("GenderWeights");
		genderMaleWeight = builder.define("GenderMaleWeight", 50);
		genderFemaleWeight = builder.define("GenderFemaleWeight", 50);
		builder.pop();

		builder.comment(LivingThingsConfig.weightedRandom).push("ColorVariantWeights");
		color1Weight = builder.define("Color1Weight", 99);
		colorAlbinoWeight = builder.define("AlbinoWeight", 1);
		builder.pop();

		builder.comment(LivingThingsConfig.requiresRestart + " | " + LivingThingsConfig.disableSpawning + " | " + LivingThingsConfig.spawningVanilla).push("Spawns");
		spawnBiomes = builder.worldRestart().defineList("SpawnBoimes",
				Arrays.asList(Biomes.SAVANNA.getLocation().toString(),
						Biomes.SAVANNA_PLATEAU.getLocation().toString(),
						Biomes.SHATTERED_SAVANNA.getLocation().toString(),
						Biomes.SHATTERED_SAVANNA_PLATEAU.getLocation().toString()),
				biome -> RegisterEntitiesToBiomes.checkBiome("Lion", biome));
		weight = builder.worldRestart().define("SpawnWeight", 15);
		minSpawns = builder.worldRestart().define("MinSpawns", 2);
		maxSpawns = builder.worldRestart().define("MaxSpawns", 4);
		builder.pop();
		
		builder.pop();
		
	}
}
