package com.tristankechlo.livingthings.config;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.entity.CrabConfig;
import com.tristankechlo.livingthings.config.entity.ElephantConfig;
import com.tristankechlo.livingthings.config.entity.FlamingoConfig;
import com.tristankechlo.livingthings.config.entity.GiraffeConfig;
import com.tristankechlo.livingthings.config.entity.LionConfig;
import com.tristankechlo.livingthings.config.entity.MantarayConfig;
import com.tristankechlo.livingthings.config.entity.OstrichConfig;
import com.tristankechlo.livingthings.config.entity.PenguinConfig;
import com.tristankechlo.livingthings.config.entity.SharkConfig;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber
public class LivingThingsConfig {
	
	public static final String requiresRestart = "requires Client and Server restart";
	public static final String disableSpawning = "to disable Spawning leave the array SpawnBoimes empty";
	public static final String spawningVanilla = "can spawn on grass-blocks where lightlevel is higher than 8";
	public static final String spawningWater = "can spawn in water where y-axis is between 35 and sea-level";
	public static final String weightedRandom = "the values are considered as weighted-random items";

	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	
	public static final GeneralConfig GENERAL = new GeneralConfig(BUILDER);
	public static final ElephantConfig ELEPHANT = new ElephantConfig(BUILDER);
	public static final GiraffeConfig GIRAFFE = new GiraffeConfig(BUILDER);
	public static final LionConfig LION = new LionConfig(BUILDER);
	public static final SharkConfig SHARK = new SharkConfig(BUILDER);
	public static final PenguinConfig PENGUIN = new PenguinConfig(BUILDER);
	public static final FlamingoConfig FLAMINGO = new FlamingoConfig(BUILDER);
	public static final OstrichConfig OSTRICH = new OstrichConfig(BUILDER);
	public static final CrabConfig CRAB = new CrabConfig(BUILDER);
	public static final MantarayConfig MANTARAY = new MantarayConfig(BUILDER);
	
	public static final ForgeConfigSpec spec = BUILDER.build();

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
