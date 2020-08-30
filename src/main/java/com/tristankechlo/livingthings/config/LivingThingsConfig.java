package com.tristankechlo.livingthings.config;

import com.tristankechlo.livingthings.LivingThings;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber
public class LivingThingsConfig {

	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final Server SERVER = new Server(BUILDER);
	public static final ForgeConfigSpec spec = BUILDER.build();

	public static class Server {

		public final BooleanValue ambientMode;
		
		public final IntValue lionAlbinoChance;
		public final IntValue giraffeAlbinoChance;

		Server(ForgeConfigSpec.Builder builder) {
			builder.comment("General Configuration").push("General");
			ambientMode = builder.comment("if set to true, mobs can't attack").define("ambientMode", false);
			builder.pop();

			builder.comment("Mob-Config for Giraffe").push("Lion");			
			lionAlbinoChance = builder.defineInRange("AlbinoChance", 1, 0, 100);
			builder.pop();

			builder.comment("Mob-Config for Lion").push("Giraffe");
			giraffeAlbinoChance = builder.defineInRange("AlbinoChance", 1, 0, 100);
			builder.pop();
		}
	}

	@SubscribeEvent
	public static void onLoad(final ModConfig.Loading configEvent) {
		if (configEvent.getConfig().getModId() == LivingThings.MOD_ID) {
			LivingThings.LOGGER.debug("Loaded config file {}", configEvent.getConfig().getFileName());
		}
	}

	@SubscribeEvent
	public static void onFileChange(final ModConfig.Reloading configEvent) {
		if (configEvent.getConfig().getModId() == LivingThings.MOD_ID) {
			LivingThings.LOGGER.debug("Config just got changed on the file system!");
		}
	}

}
