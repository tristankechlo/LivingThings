package com.tristankechlo.livingthings.config.entity;

import java.util.ArrayList;
import java.util.List;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.config.misc.SpawnData;

import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class CrabConfig {

	public final BooleanValue canAttack;
	public final DoubleValue health;
	public final DoubleValue speed;
	public final DoubleValue damage;
	public final IntValue maxSpawnedInChunk;

	public final ConfigValue<Integer> color1Weight;
	public final ConfigValue<Integer> color2Weight;
	public final ConfigValue<Integer> colorAlbinoWeight;

	public final ConfigValue<Integer> scaling1Weight;
	public final ConfigValue<Integer> scaling2Weight;
	public final ConfigValue<Integer> scaling3Weight;
	public final ConfigValue<Integer> scaling4Weight;

	public CrabConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Crab").push("Crab");

		canAttack = builder.define("CanAttack", true);
		health = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart().defineInRange("Health", 8.0D,
				LivingThingsConfig.MIN_HEALTH, LivingThingsConfig.MAX_HEALTH);

		speed = builder.comment(LivingThingsConfig.REQUIRES_RESTART + " | " + LivingThingsConfig.HIGH_IMPACT)
				.worldRestart()
				.defineInRange("MovementSpeed", 0.2D, LivingThingsConfig.MIN_SPEED, LivingThingsConfig.MAX_SPEED);

		damage = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart().defineInRange("AttackDamage", 1.0D,
				LivingThingsConfig.MIN_DAMAGE, LivingThingsConfig.MAX_DAMAGE);

		maxSpawnedInChunk = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart()
				.defineInRange("MaxSpawnedInChunk", 8, 1, 15);

		builder.comment(LivingThingsConfig.WEIGHTED_RANDOM).push("ColorVariantWeights");
		color1Weight = builder.define("Color1Weight", 50);
		color2Weight = builder.define("Color2Weight", 50);
		colorAlbinoWeight = builder.define("AlbinoWeight", 1);
		builder.pop();

		builder.comment(LivingThingsConfig.WEIGHTED_RANDOM).push("ScalingWeights");
		scaling1Weight = builder.define("Scaling1Weight", 30);
		scaling2Weight = builder.define("Scaling2Weight", 30);
		scaling3Weight = builder.define("Scaling3Weight", 25);
		scaling4Weight = builder.define("Scaling4Weight", 15);
		builder.pop();

		builder.pop();
	}

	@SuppressWarnings("unchecked")
	public static List<SpawnData> getDefaultSpawns() {
		List<SpawnData> spawns = new ArrayList<SpawnData>();
		spawns.add(new SpawnData(50, 5, 8, Biomes.RIVER, Biomes.BEACH, Biomes.DESERT_LAKES, Biomes.SWAMP_HILLS,
				Biomes.SWAMP));
		return spawns;
	}

}
