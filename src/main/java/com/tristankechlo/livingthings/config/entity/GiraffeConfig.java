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

public class GiraffeConfig {

	public final DoubleValue health;
	public final DoubleValue damage;
	public final BooleanValue canAttack;

	public final ConfigValue<Integer> color1Weight;
	public final ConfigValue<Integer> color2Weight;
	public final ConfigValue<Integer> colorAlbinoWeight;

	public final ConfigValue<List<? extends String>> spawnBiomes;
	public final IntValue weight;
	public final IntValue minSpawns;
	public final IntValue maxSpawns;

	public GiraffeConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Giraffe").push("Giraffe");
		
		canAttack = builder.define("CanAttack", true);
		health = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().defineInRange("Health", 30.0D, 1.0D, Short.MAX_VALUE);
		damage = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().defineInRange("AttackDamage", 4.0D, 1.0D, Short.MAX_VALUE);

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
				biome -> LivingThingsConfig.checkBiome("Giraffe", biome));
		weight = builder.worldRestart().defineInRange("SpawnWeight", 15, 1, Short.MAX_VALUE);
		minSpawns = builder.worldRestart().defineInRange("MinSpawns", 3, 1, Short.MAX_VALUE);
		maxSpawns = builder.worldRestart().defineInRange("MaxSpawns", 5, 1, Short.MAX_VALUE);
		builder.pop();
		
		builder.pop();
		
	}
}
