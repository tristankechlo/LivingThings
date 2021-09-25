package com.tristankechlo.livingthings.config.entity;

import java.util.ArrayList;
import java.util.List;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.config.misc.SpawnData;

import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class SeahorseConfig {

	public final DoubleValue health;
	public final DoubleValue speed;
	public final IntValue maxSpawnedInChunk;

	public final IntValue blueWeight;
	public final IntValue greenWeight;
	public final IntValue purpleWeight;
	public final IntValue yellowWeight;
	public final IntValue redWeight;

	public SeahorseConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Seahorse").push("Seahorse");

		health = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart().defineInRange("Health", 4.0D,
				LivingThingsConfig.MIN_HEALTH, LivingThingsConfig.MAX_HEALTH);

		speed = builder.comment(LivingThingsConfig.REQUIRES_RESTART + " | " + LivingThingsConfig.HIGH_IMPACT)
				.worldRestart()
				.defineInRange("MovementSpeed", 0.2D, LivingThingsConfig.MIN_SPEED, LivingThingsConfig.MAX_SPEED);

		maxSpawnedInChunk = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart()
				.defineInRange("MaxSpawnedInChunk", 4, 1, 15);

		builder.comment(LivingThingsConfig.WEIGHTED_RANDOM).push("ColorVariantWeights");
		blueWeight = builder.defineInRange("ColorBlueWeight", 20, 0, Integer.MAX_VALUE);
		greenWeight = builder.defineInRange("ColorGreenWeight", 20, 0, Integer.MAX_VALUE);
		purpleWeight = builder.defineInRange("ColorPurpleWeight", 20, 0, Integer.MAX_VALUE);
		yellowWeight = builder.defineInRange("ColorYellowWeight", 20, 0, Integer.MAX_VALUE);
		redWeight = builder.defineInRange("ColorRedWeight", 20, 0, Integer.MAX_VALUE);
		builder.pop();

		builder.pop();
	}

	@SuppressWarnings("unchecked")
	public static List<SpawnData> getDefaultSpawns() {
		List<SpawnData> spawns = new ArrayList<SpawnData>();
		spawns.add(new SpawnData(15, 2, 6, Biomes.WARM_OCEAN, Biomes.DEEP_WARM_OCEAN, Biomes.LUKEWARM_OCEAN,
				Biomes.DEEP_LUKEWARM_OCEAN, Biomes.OCEAN, Biomes.DEEP_OCEAN));
		return spawns;
	}

}
