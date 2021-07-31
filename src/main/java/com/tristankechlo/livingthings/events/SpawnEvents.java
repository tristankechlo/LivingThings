package com.tristankechlo.livingthings.events;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.init.ModEntityTypes;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.MobSpawnInfo.Spawners;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.world.StructureSpawnListGatherEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class SpawnEvents {

	@SubscribeEvent
	public void onStructureSpawnListGatherEvent(final StructureSpawnListGatherEvent event) {

		String structureName = event.getStructure().getRegistryName().toString();

		if (structureName.equals(Structure.NETHER_BRIDGE.getRegistryName().toString())) {

			// spawn NetherKnights in Fortresses
			event.addEntitySpawn(EntityClassification.MONSTER,
					new Spawners(ModEntityTypes.NETHER_KNIGHT_ENTITY.get(),
							LivingThingsConfig.NETHER_KNIGHT.spawnWeight.get(),
							LivingThingsConfig.NETHER_KNIGHT.minSpawnCount.get(),
							LivingThingsConfig.NETHER_KNIGHT.maxSpawnCount.get()));
		}
	}

}
