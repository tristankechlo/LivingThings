package com.tristankechlo.livingthings.config.entity;

import java.util.ArrayList;
import java.util.List;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.config.misc.SpawnData;

import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class BabyEnderDragonConfig {

	public final BooleanValue canAttack;
	public final DoubleValue health;
	public final DoubleValue walkingSpeed;
	public final DoubleValue flyingSpeed;
	public final IntValue maxSpawnedInChunk;

	public BabyEnderDragonConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Baby Enderdragon").push("Baby Enderdragon");

		canAttack = builder.define("CanAttack", true);
		health = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart().defineInRange("Health", 10.0D,
				LivingThingsConfig.MIN_HEALTH, LivingThingsConfig.MAX_HEALTH);

		walkingSpeed = builder.comment(LivingThingsConfig.REQUIRES_RESTART + " | " + LivingThingsConfig.HIGH_IMPACT)
				.worldRestart()
				.defineInRange("WalkingSpeed", 0.3D, LivingThingsConfig.MIN_SPEED, LivingThingsConfig.MAX_SPEED);

		flyingSpeed = builder.comment(LivingThingsConfig.REQUIRES_RESTART + " | " + LivingThingsConfig.HIGH_IMPACT)
				.worldRestart()
				.defineInRange("FlyingSpeed", 0.5D, LivingThingsConfig.MIN_SPEED, LivingThingsConfig.MAX_SPEED);

		maxSpawnedInChunk = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart()
				.defineInRange("MaxSpawnedInChunk", 5, 1, 15);

		builder.pop();
	}

	@SuppressWarnings("unchecked")
	public static List<SpawnData> getDefaultSpawns() {
		List<SpawnData> spawns = new ArrayList<SpawnData>();
		spawns.add(new SpawnData(15, 3, 5, Biomes.END_BARRENS, Biomes.END_HIGHLANDS, Biomes.END_MIDLANDS,
				Biomes.SMALL_END_ISLANDS, Biomes.THE_END));
		return spawns;
	}
}
