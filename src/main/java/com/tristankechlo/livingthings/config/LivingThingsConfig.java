package com.tristankechlo.livingthings.config;

import org.apache.logging.log4j.Level;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.entity.AncientBlazeConfig;
import com.tristankechlo.livingthings.config.entity.CrabConfig;
import com.tristankechlo.livingthings.config.entity.ElephantConfig;
import com.tristankechlo.livingthings.config.entity.FlamingoConfig;
import com.tristankechlo.livingthings.config.entity.GiraffeConfig;
import com.tristankechlo.livingthings.config.entity.KoalaConfig;
import com.tristankechlo.livingthings.config.entity.LionConfig;
import com.tristankechlo.livingthings.config.entity.MantarayConfig;
import com.tristankechlo.livingthings.config.entity.MonkeyConfig;
import com.tristankechlo.livingthings.config.entity.OstrichConfig;
import com.tristankechlo.livingthings.config.entity.OwlConfig;
import com.tristankechlo.livingthings.config.entity.PenguinConfig;
import com.tristankechlo.livingthings.config.entity.RaccoonConfig;
import com.tristankechlo.livingthings.config.entity.SharkConfig;
import com.tristankechlo.livingthings.config.entity.SnailConfig;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber
public class LivingThingsConfig {

	public static final String REQUIRES_RESTART = "requires Client and Server restart";
	public static final String DISABLE_SPAWNING = "to disable spawning, leave the array SpawnBoimes empty";
	public static final String SPAWNING_VANILLA = "can spawn on grass-blocks where lightlevel is higher than 8";
	public static final String SPAWNING_WATER = "can spawn in water";
	public static final String WEIGHTED_RANDOM = "the values are considered as weighted-random";
	public static final String HIGH_IMPACT = "be careful, even small changes can have a high impact";
	
	public static final double MIN_HEALTH = 1.0D;
	public static final double MAX_HEALTH = Short.MAX_VALUE;
	public static final double MIN_SPEED = 0.05D;
	public static final double MAX_SPEED = 10.0D;
	public static final double MIN_DAMAGE = 1.0D;
	public static final double MAX_DAMAGE = Short.MAX_VALUE;

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
	public static final RaccoonConfig RACCOON = new RaccoonConfig(BUILDER);
	public static final OwlConfig OWL = new OwlConfig(BUILDER);
	public static final AncientBlazeConfig ANCIENT_BLAZE = new AncientBlazeConfig(BUILDER);
	public static final KoalaConfig KOALA = new KoalaConfig(BUILDER);
	public static final SnailConfig SNAIL = new SnailConfig(BUILDER);
	public static final MonkeyConfig MONKEY = new MonkeyConfig(BUILDER);

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

	public static boolean checkBiome(String name, Object test) {
		if (ForgeRegistries.BIOMES.containsKey(new ResourceLocation(String.valueOf(test)))) {
			return true;
		}
		LivingThings.LOGGER.log(Level.INFO, "Removing unknown Biome[" + String.valueOf(test) + "] from " + name + "-SpawnBiomes");
		return false;
	}

}
