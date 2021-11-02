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

public class RaccoonConfig {

	public final BooleanValue canAttack;
	public final DoubleValue health;
	public final DoubleValue speed;
	public final DoubleValue damage;
	public final IntValue maxSpawnedInChunk;

	public RaccoonConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Raccoon").push("Raccoon");

		canAttack = builder.define("CanAttack", true);
		health = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart().defineInRange("Health", 10.0D,
				LivingThingsConfig.MIN_HEALTH, LivingThingsConfig.MAX_HEALTH);

		speed = builder.comment(LivingThingsConfig.REQUIRES_RESTART + " | " + LivingThingsConfig.HIGH_IMPACT)
				.worldRestart()
				.defineInRange("MovementSpeed", 0.23D, LivingThingsConfig.MIN_SPEED, LivingThingsConfig.MAX_SPEED);

		damage = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart().defineInRange("AttackDamage", 2.0D,
				LivingThingsConfig.MIN_DAMAGE, LivingThingsConfig.MAX_DAMAGE);

		maxSpawnedInChunk = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart()
				.defineInRange("MaxSpawnedInChunk", 6, 1, 15);

		builder.pop();
	}

	@SuppressWarnings("unchecked")
	public static List<SpawnData> getDefaultSpawns() {
		List<SpawnData> spawns = new ArrayList<SpawnData>();
		spawns.add(new SpawnData(20, 2, 6, Biomes.PLAINS, Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS,
				Biomes.TALL_BIRCH_FOREST, Biomes.TALL_BIRCH_HILLS, Biomes.SUNFLOWER_PLAINS, Biomes.DARK_FOREST,
				Biomes.DARK_FOREST_HILLS, Biomes.FLOWER_FOREST));
		return spawns;
	}

}
