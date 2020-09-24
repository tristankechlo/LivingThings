package com.tristankechlo.livingthings.config.entity;

import java.util.Arrays;
import java.util.List;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.init.RegisterEntitiesToBiomes;

import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class GiraffeConfig {

	public final ConfigValue<Double> health;
	public final ConfigValue<Double> damage;	
	public final BooleanValue canAttack;

	public final ConfigValue<Integer> color1Weight;
	public final ConfigValue<Integer> color2Weight;
	public final ConfigValue<Integer> colorAlbinoWeight;

	public final ConfigValue<List<? extends String>> spawnBiomes;
	public final ConfigValue<Integer> weight;
	public final ConfigValue<Integer> minSpawns;
	public final ConfigValue<Integer> maxSpawns;

	public GiraffeConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Giraffe").push("Giraffe");
		
		canAttack = builder.define("CanAttack", true);
		health = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().define("Health", 30.0D);
		damage = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().define("AttackDamage", 8.0D);

		builder.comment(LivingThingsConfig.weightedRandom).push("ColorVariantWeights");
		color1Weight = builder.define("Color1Weight", 50);
		color2Weight = builder.define("Color2Weight", 50);
		colorAlbinoWeight = builder.define("AlbinoWeight", 1);
		builder.pop();

		builder.comment(LivingThingsConfig.requiresRestart + " | " + LivingThingsConfig.disableSpawning + " | " + LivingThingsConfig.spawningVanilla).push("Spawns");
		spawnBiomes = builder.worldRestart().defineList("SpawnBoimes",
				Arrays.asList(Biomes.SAVANNA.getLocation().toString(),
						Biomes.SAVANNA_PLATEAU.getLocation().toString(),
						Biomes.SHATTERED_SAVANNA.getLocation().toString(),
						Biomes.SHATTERED_SAVANNA_PLATEAU.getLocation().toString()),
				biome -> RegisterEntitiesToBiomes.checkBiome("Giraffe", biome));
		weight = builder.worldRestart().define("SpawnWeight", 15);
		minSpawns = builder.worldRestart().define("MinSpawns", 3);
		maxSpawns = builder.worldRestart().define("MaxSpawns", 5);
		builder.pop();
		
		builder.pop();
		
	}
}
