package com.tristankechlo.livingthings.config.entity;

import com.tristankechlo.livingthings.config.LivingThingsConfig;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class PenguinConfig {

	public final ConfigValue<Double> health;

	public PenguinConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("Mob-Config for Penguin").push("Penguin");
		
		health = builder.comment(LivingThingsConfig.requiresRestart).worldRestart().define("Health", 16.0D);
		
		builder.pop();
		
	}
}
