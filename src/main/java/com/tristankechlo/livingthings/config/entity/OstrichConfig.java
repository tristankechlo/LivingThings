package com.tristankechlo.livingthings.config.entity;

import com.tristankechlo.livingthings.config.LivingThingsConfig;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class OstrichConfig {

	public final ConfigValue<Double> health;
	public final BooleanValue canBeRidden;

	public OstrichConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Ostrich").push("Ostrich");
		
		canBeRidden = builder.define("CanBeRidden", true);
		health = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().define("Health", 16.0D);
		
		builder.pop();
		
	}
}
