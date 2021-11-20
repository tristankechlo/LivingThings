package com.tristankechlo.livingthings;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.config.misc.ConfigManager;
import com.tristankechlo.livingthings.events.BlockEventHandler;
import com.tristankechlo.livingthings.events.SpawnEvents;
import com.tristankechlo.livingthings.init.ModBlocks;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.init.ModItems;
import com.tristankechlo.livingthings.init.ModParticle;
import com.tristankechlo.livingthings.init.ModSounds;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(LivingThings.MOD_ID)
public class LivingThings {

	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "livingthings";
	public static boolean patchouliLoaded = false;

	public LivingThings() {
		setupConfigs();
		ModLoadingContext.get().registerConfig(Type.COMMON, LivingThingsConfig.spec, "livingthings/livingthings.toml");

		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		ModItems.ITEMS.register(modEventBus);
		ModBlocks.BLOCKS.register(modEventBus);
		ModSounds.SOUNDS.register(modEventBus);
		ModParticle.PARTICLES.register(modEventBus);
		ModEntityTypes.ENTITY_TYPES.register(modEventBus);

		modEventBus.addListener(this::commonSetup);

		MinecraftForge.EVENT_BUS.register(new BlockEventHandler());
		MinecraftForge.EVENT_BUS.register(new SpawnEvents());
		MinecraftForge.EVENT_BUS.register(this);
	}

	public void commonSetup(final FMLCommonSetupEvent event) {
		LivingThings.patchouliLoaded = ModList.get().isLoaded("patchouli");
		ConfigManager.setup();
	}

	private void setupConfigs() {
		File dir = FMLPaths.CONFIGDIR.get().resolve(LivingThings.MOD_ID).toFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

}