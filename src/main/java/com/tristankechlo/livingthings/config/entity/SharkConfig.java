package com.tristankechlo.livingthings.config.entity;

import java.util.ArrayList;
import java.util.List;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.config.misc.SpawnData;

import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class SharkConfig {

	public final BooleanValue canAttack;
	public final DoubleValue health;
	public final DoubleValue speed;
	public final DoubleValue damage;
	public final IntValue maxSpawnedInChunk;

	public SharkConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Shark").push("Shark");

		canAttack = builder.define("CanAttack", true);
		health = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart().defineInRange("Health", 22.0D,
				LivingThingsConfig.MIN_HEALTH, LivingThingsConfig.MAX_HEALTH);

		speed = builder.comment(LivingThingsConfig.REQUIRES_RESTART + " | " + LivingThingsConfig.HIGH_IMPACT)
				.worldRestart()
				.defineInRange("MovementSpeed", 1.05D, LivingThingsConfig.MIN_SPEED, LivingThingsConfig.MAX_SPEED);

		damage = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart().defineInRange("AttackDamage", 6.0D,
				LivingThingsConfig.MIN_DAMAGE, LivingThingsConfig.MAX_DAMAGE);

		maxSpawnedInChunk = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart()
				.defineInRange("MaxSpawnedInChunk", 3, 1, 15);

		builder.pop();
	}

	@SuppressWarnings("unchecked")
	public static List<SpawnData> getDefaultSpawns() {
		List<SpawnData> spawns = new ArrayList<SpawnData>();
		spawns.add(new SpawnData(13, 2, 3, Biomes.OCEAN, Biomes.DEEP_OCEAN, Biomes.FROZEN_OCEAN,
				Biomes.DEEP_FROZEN_OCEAN, Biomes.COLD_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.WARM_OCEAN,
				Biomes.DEEP_WARM_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN));
		return spawns;
	}

}
