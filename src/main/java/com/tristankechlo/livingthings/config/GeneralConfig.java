package com.tristankechlo.livingthings.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;

public class GeneralConfig {

	public final BooleanValue ambientMode;
	public final BooleanValue doBananaDrops;
	public final DoubleValue bananaDropChance;

	public GeneralConfig(ForgeConfigSpec.Builder builder) {

		builder.comment("General Configuration").push("General");

		ambientMode = builder.comment("if set to true no modspecific mobs will attack, overrides the canAttack of every mob").define("AmbientMode", false);
		doBananaDrops = builder.comment("wether or not bananas should drop").define("DoBananaDrops", true);
		bananaDropChance = builder.comment("chance in % for each block to drop a banana").defineInRange("BananaDropChance", 45.0D, 0.0D, 100.0D);

		builder.pop();

	}
}
