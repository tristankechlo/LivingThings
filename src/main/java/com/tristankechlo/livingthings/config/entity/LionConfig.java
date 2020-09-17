package com.tristankechlo.livingthings.config.entity;

import java.util.Arrays;
import java.util.List;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.init.RegisterEntitiesToBiomes;

import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class LionConfig {

	public final ConfigValue<Double> health;
	public final ConfigValue<Double> damage;	
	public final BooleanValue canAttack;
	public final IntValue albinoChance;

	public final ConfigValue<List<? extends String>> include;
	public final ConfigValue<Integer> weight;
	public final ConfigValue<Integer> minSpawns;
	public final ConfigValue<Integer> maxSpawns;

	public LionConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Lion").push("Lion");
		
		canAttack = builder.define("CanAttack", true);	
		albinoChance = builder.defineInRange("AlbinoChance", 1, 0, 100);
		health = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().define("Health", 20.0D);
		damage = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().define("AttackDamage", 6.0D);

		builder.comment(LivingThingsConfig.requiresRestart).comment(LivingThingsConfig.disableSpawning).push("Spawns");
		include = builder.worldRestart().defineList("SpawnBoimes",
				Arrays.asList(Biomes.SAVANNA.func_240901_a_().toString(),
						Biomes.SAVANNA_PLATEAU.func_240901_a_().toString(),
						Biomes.SHATTERED_SAVANNA.func_240901_a_().toString(),
						Biomes.SHATTERED_SAVANNA_PLATEAU.func_240901_a_().toString()),
				biome -> RegisterEntitiesToBiomes.checkBiome("Lion", biome));
		weight = builder.worldRestart().define("SpawnWeight", 15);
		minSpawns = builder.worldRestart().define("MinSpawns", 2);
		maxSpawns = builder.worldRestart().define("MaxSpawns", 4);
		builder.pop();
		
		builder.pop();
		
	}
}
