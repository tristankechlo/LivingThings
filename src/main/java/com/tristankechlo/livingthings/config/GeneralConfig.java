package com.tristankechlo.livingthings.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class GeneralConfig {
	
	public final BooleanValue ambientMode;

	public GeneralConfig(ForgeConfigSpec.Builder builder) {
		
		builder.comment("General Configuration").push("General");
		
		ambientMode = builder.comment("if set to true no modspecific mobs will attack, overrides the canAttack of every mob").define("AmbientMode", false);
		
		builder.pop();
		
	}
}
