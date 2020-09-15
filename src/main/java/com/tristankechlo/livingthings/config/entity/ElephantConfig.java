package com.tristankechlo.livingthings.config.entity;

import com.tristankechlo.livingthings.config.LivingThingsConfig;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class ElephantConfig {
	
	public final ConfigValue<Double> health;
	public final ConfigValue<Double> damage;	
	public final BooleanValue canAttack;

	public ElephantConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Elephant").push("Elephant");
		
		canAttack = builder.define("canAttack", true);
		health = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().define("Health", 80.0D);
		damage = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().define("AttackDamage", 10.0D);
		
		builder.pop();
		
	}
}
