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

public class ShroomieConfig {

	public final DoubleValue health;
	public final DoubleValue speed;
	public final IntValue maxSpawnedInChunk;

	public final ConfigValue<Integer> brownWeight;
	public final ConfigValue<Integer> redWeight;

	public ShroomieConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Shroomie").push("Shroomie");

		health = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart().defineInRange("Health", 10.0D,
				LivingThingsConfig.MIN_HEALTH, LivingThingsConfig.MAX_HEALTH);

		speed = builder.comment(LivingThingsConfig.REQUIRES_RESTART + " | " + LivingThingsConfig.HIGH_IMPACT)
				.worldRestart()
				.defineInRange("MovementSpeed", 0.2D, LivingThingsConfig.MIN_SPEED, LivingThingsConfig.MAX_SPEED);

		maxSpawnedInChunk = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart()
				.defineInRange("MaxSpawnedInChunk", 5, 1, 15);

		builder.comment(LivingThingsConfig.WEIGHTED_RANDOM).push("ColorVariantWeights");
		brownWeight = builder.defineInRange("BrownWeight", 50, 0, Integer.MAX_VALUE);
		redWeight = builder.defineInRange("RedWeight", 50, 0, Integer.MAX_VALUE);
		builder.pop();

		builder.pop();
	}

	@SuppressWarnings("unchecked")
	public static List<SpawnData> getDefaultSpawns() {
		List<SpawnData> spawns = new ArrayList<SpawnData>();
		spawns.add(new SpawnData(15, 3, 6, Biomes.MUSHROOM_FIELDS, Biomes.MUSHROOM_FIELD_SHORE));
		return spawns;
	}

}
