package com.tristankechlo.livingthings.events;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.init.ModEntityTypes;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.event.world.StructureSpawnListGatherEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class SpawnEvents {

	@SubscribeEvent
	public void onStructureSpawnListGatherEvent(final StructureSpawnListGatherEvent event) {

		String structureName = event.getStructure().getRegistryName().toString();

		if (structureName.equals(StructureFeature.FORTRESS.getRegistryName().toString())) {

			// spawn NetherKnights in Fortresses
			event.addEntitySpawn(MobCategory.MONSTER,
					new SpawnerData(ModEntityTypes.NETHER_KNIGHT.get(),
							LivingThingsConfig.NETHER_KNIGHT.spawnWeight.get(),
							LivingThingsConfig.NETHER_KNIGHT.minSpawnCount.get(),
							LivingThingsConfig.NETHER_KNIGHT.maxSpawnCount.get()));
		}
	}

}
