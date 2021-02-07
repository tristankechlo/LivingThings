package com.tristankechlo.livingthings.config.entity;

import java.util.ArrayList;
import java.util.List;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.config.misc.SpawnData;

import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class MantarayConfig {

	public final DoubleValue health;
	public final DoubleValue speed;
	public final IntValue maxSpawnedInChunk;

	public final ConfigValue<Integer> color1Weight;
	public final ConfigValue<Integer> color2Weight;

	public final ConfigValue<Integer> scaling1Weight;
	public final ConfigValue<Integer> scaling2Weight;
	public final ConfigValue<Integer> scaling3Weight;
	public final ConfigValue<Integer> scaling4Weight;

	public MantarayConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Mantaray").push("Mantaray");

		health = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart().defineInRange("Health", 10.0D,
				LivingThingsConfig.MIN_HEALTH, LivingThingsConfig.MAX_HEALTH);

		speed = builder.comment(LivingThingsConfig.REQUIRES_RESTART + " | " + LivingThingsConfig.HIGH_IMPACT)
				.worldRestart()
				.defineInRange("MovementSpeed", 1.0D, LivingThingsConfig.MIN_SPEED, LivingThingsConfig.MAX_SPEED);

		maxSpawnedInChunk = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart()
				.defineInRange("MaxSpawnedInChunk", 5, 1, 15);

		builder.comment(LivingThingsConfig.WEIGHTED_RANDOM).push("ColorVariantWeights");
		color1Weight = builder.define("Color1Weight", 50);
		color2Weight = builder.define("Color2Weight", 50);
		builder.pop();

		builder.comment(LivingThingsConfig.WEIGHTED_RANDOM).push("ScalingWeights");
		scaling1Weight = builder.define("Scaling1Weight", 25);
		scaling2Weight = builder.define("Scaling2Weight", 25);
		scaling3Weight = builder.define("Scaling3Weight", 25);
		scaling4Weight = builder.define("Scaling4Weight", 25);
		builder.pop();

		builder.pop();
	}

	@SuppressWarnings("unchecked")
	public static List<SpawnData> getDefaultSpawns() {
		List<SpawnData> spawns = new ArrayList<SpawnData>();
		spawns.add(new SpawnData(15, 2, 5, Biomes.OCEAN, Biomes.DEEP_OCEAN, Biomes.FROZEN_OCEAN,
				Biomes.DEEP_FROZEN_OCEAN, Biomes.COLD_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.WARM_OCEAN,
				Biomes.DEEP_WARM_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN));
		return spawns;
	}

}
