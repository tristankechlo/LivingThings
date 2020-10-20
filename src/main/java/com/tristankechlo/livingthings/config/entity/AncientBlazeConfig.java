package com.tristankechlo.livingthings.config.entity;

import com.tristankechlo.livingthings.config.LivingThingsConfig;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class AncientBlazeConfig {

	public final DoubleValue health;
	public final DoubleValue damage;
	public final IntValue chargingTime;
	public final BooleanValue canAttack;
	public final BooleanValue peacefulDespawn;

	public AncientBlazeConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for AncientBlaze").push("AncientBlaze");

		canAttack = builder.define("CanAttack", true);
		peacefulDespawn = builder.comment("wether or not the entity shall despawn when peaceful is turned on").define("PeacefulDespawn", true);
		chargingTime = builder.comment("required time in ticks, how long it takes to fully charge the blaze").worldRestart().defineInRange("ChargingTime", 200, 10, Short.MAX_VALUE);
		health = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().defineInRange("Health", 150.0D, 1.0D, Short.MAX_VALUE);
		damage = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().defineInRange("AttackDamage", 6.0D, 1.0D, Short.MAX_VALUE);

		builder.pop();

	}
}
