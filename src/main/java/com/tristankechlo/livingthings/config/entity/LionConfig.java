package com.tristankechlo.livingthings.config.entity;

import com.tristankechlo.livingthings.config.LivingThingsConfig;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class LionConfig {

	public final ConfigValue<Double> health;
	public final ConfigValue<Double> damage;	
	public final BooleanValue canAttack;
	public final IntValue albinoChance;

	public LionConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Lion").push("Lion");
		
		canAttack = builder.define("canAttack", true);	
		albinoChance = builder.defineInRange("AlbinoChance", 1, 0, 100);
		health = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().define("Health", 20.0D);
		damage = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().define("AttackDamage", 6.0D);
		
		builder.pop();
		
	}
}
