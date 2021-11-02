package com.tristankechlo.livingthings.config.entity;

import java.util.ArrayList;
import java.util.List;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.config.misc.SpawnData;

import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class KoalaConfig {

	public final DoubleValue health;
	public final DoubleValue speed;
	public final IntValue maxSpawnedInChunk;

	public KoalaConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Koala").push("Koala");

		health = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart().defineInRange("Health", 10.0D,
				LivingThingsConfig.MIN_HEALTH, LivingThingsConfig.MAX_HEALTH);

		speed = builder.comment(LivingThingsConfig.REQUIRES_RESTART + " | " + LivingThingsConfig.HIGH_IMPACT)
				.worldRestart()
				.defineInRange("MovementSpeed", 0.17D, LivingThingsConfig.MIN_SPEED, LivingThingsConfig.MAX_SPEED);

		maxSpawnedInChunk = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart()
				.defineInRange("MaxSpawnedInChunk", 5, 1, 15);

		builder.pop();
	}

	@SuppressWarnings("unchecked")
	public static List<SpawnData> getDefaultSpawns() {
		List<SpawnData> spawns = new ArrayList<SpawnData>();
		spawns.add(new SpawnData(50, 3, 5, Biomes.JUNGLE, Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS,
				Biomes.MODIFIED_JUNGLE, Biomes.MODIFIED_JUNGLE_EDGE));
		spawns.add(new SpawnData(25, 3, 5, Biomes.BAMBOO_JUNGLE, Biomes.BAMBOO_JUNGLE_HILLS));
		spawns.add(new SpawnData(10, 3, 5, Biomes.SAVANNA_PLATEAU));
		return spawns;
	}

}
