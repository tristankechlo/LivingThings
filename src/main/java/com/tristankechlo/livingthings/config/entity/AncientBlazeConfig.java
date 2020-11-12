package com.tristankechlo.livingthings.config.entity;

import com.tristankechlo.livingthings.config.LivingThingsConfig;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class AncientBlazeConfig {

	public final DoubleValue health;
	public final DoubleValue speed;
	public final DoubleValue damage;
	public final IntValue chargingTime;
	public final IntValue largeFireballAmount;
	public final IntValue largeFireballChance;
	public final IntValue blazeSpawnCount;
	public final BooleanValue canAttack;
	public final BooleanValue peacefulDespawn;

	public AncientBlazeConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for AncientBlaze").push("AncientBlaze");

		health = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart()
				.defineInRange("Health", 250.0D, LivingThingsConfig.MIN_HEALTH, LivingThingsConfig.MAX_HEALTH);

		speed = builder.comment(LivingThingsConfig.REQUIRES_RESTART + " | " + LivingThingsConfig.HIGH_IMPACT).worldRestart()
				.defineInRange("MovementSpeed", 0.25D, LivingThingsConfig.MIN_SPEED, LivingThingsConfig.MAX_SPEED);

		damage = builder.comment(LivingThingsConfig.REQUIRES_RESTART).worldRestart()
				.defineInRange("AttackDamage", 6.0D, LivingThingsConfig.MIN_DAMAGE, LivingThingsConfig.MAX_DAMAGE);

		chargingTime = builder.comment("time in ticks, until the entity can be damaged | " + LivingThingsConfig.REQUIRES_RESTART).worldRestart()
				.defineInRange("InvulnerableTime", 200, 10, Short.MAX_VALUE);

		blazeSpawnCount = builder.comment("amount of blazes spawned on death | " + LivingThingsConfig.REQUIRES_RESTART).worldRestart()
				.defineInRange("BlazeSpawnCount", 4, 0, 16);

		largeFireballAmount = builder.comment("max amount of large fireballs to shoot | " + LivingThingsConfig.REQUIRES_RESTART).worldRestart()
				.defineInRange("LargeFireballCount", 6, 0, 10);

		largeFireballChance = builder.comment("chance for the attack to be a large fireball | " + LivingThingsConfig.REQUIRES_RESTART).worldRestart()
				.defineInRange("LargeFireballChance", 20, 0, 100);

		canAttack = builder.define("CanAttack", true);

		peacefulDespawn = builder.comment("wether or not the entity shall despawn when peaceful is turned on").define("PeacefulDespawn", true);

		builder.pop();

	}
}
