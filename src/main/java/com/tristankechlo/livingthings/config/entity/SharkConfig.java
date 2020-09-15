package com.tristankechlo.livingthings.config.entity;

import com.tristankechlo.livingthings.config.LivingThingsConfig;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class SharkConfig {

	public final ConfigValue<Double> health;
	public final ConfigValue<Double> damage;
	
	public final BooleanValue canAttack;

	public SharkConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Shark").push("Shark");
		
		canAttack = builder.define("canAttack", true);
		health = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().define("Health", 22.0D);
		damage = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().define("AttackDamage", 6.5D);
		
		builder.pop();
		
	}
}
