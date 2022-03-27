package com.tristankechlo.livingthings.events;

import java.util.List;
import java.util.Map;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.BiomeSpawnConfig;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LivingThings.MOD_ID)
public class BiomeLoadingEventHandler {

	private static Map<ResourceLocation, Map<MobCategory, List<SpawnerData>>> biomeSpawnMap;

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onBiomeLoading(BiomeLoadingEvent event) {

		if (biomeSpawnMap == null) {
			biomeSpawnMap = BiomeSpawnConfig.getBiomeSpawnMap();
		}

		if (biomeSpawnMap.containsKey(event.getName())) {
			Map<MobCategory, List<SpawnerData>> classificationMap = biomeSpawnMap.get(event.getName());
			for (Map.Entry<MobCategory, List<SpawnerData>> entry : classificationMap.entrySet()) {
				MobCategory classification = entry.getKey();
				for (SpawnerData spawner : entry.getValue()) {
					event.getSpawns().getSpawner(classification).add(spawner);
//					LivingThings.LOGGER.info(event.getName() + " | " + classification + " | " + spawner);
				}
			}
		}
	}

}
