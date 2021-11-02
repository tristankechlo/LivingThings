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

public class MonkeyConfig {

	public final BooleanValue canAttack;
	public final DoubleValue health;
	public final DoubleValue speed;
	public final DoubleValue damage;
	public final IntValue maxSpawnedInChunk;

	public MonkeyConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Monkey").push("Monkey");

		canAttack = builder.define("CanAttack", true);
		health = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart().defineInRange("Health", 6.0D,
				LivingThingsConfig.MIN_HEALTH, LivingThingsConfig.MAX_HEALTH);

		speed = builder.comment(LivingThingsConfig.REQUIRES_RESTART + " | " + LivingThingsConfig.HIGH_IMPACT)
				.worldRestart()
				.defineInRange("MovementSpeed", 0.23D, LivingThingsConfig.MIN_SPEED, LivingThingsConfig.MAX_SPEED);

		damage = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart().defineInRange("AttackDamage", 3.0D,
				LivingThingsConfig.MIN_DAMAGE, LivingThingsConfig.MAX_DAMAGE);

		maxSpawnedInChunk = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart()
				.defineInRange("MaxSpawnedInChunk", 7, 1, 15);

		builder.pop();
	}

	@SuppressWarnings("unchecked")
	public static List<SpawnData> getDefaultSpawns() {
		List<SpawnData> spawns = new ArrayList<SpawnData>();
		spawns.add(new SpawnData(50, 4, 7, Biomes.JUNGLE, Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS,
				Biomes.MODIFIED_JUNGLE, Biomes.MODIFIED_JUNGLE_EDGE));
		spawns.add(new SpawnData(25, 4, 7, Biomes.BAMBOO_JUNGLE, Biomes.BAMBOO_JUNGLE_HILLS));
		return spawns;
	}

}
