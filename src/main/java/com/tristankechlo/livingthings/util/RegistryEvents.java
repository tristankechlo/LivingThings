package com.tristankechlo.livingthings.util;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.init.ModEntities;

import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LivingThings.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {

	@SubscribeEvent
	public static void onEntityRegistry(RegistryEvent.Register<EntityType<?>> event) {
		ModEntities.registerAttributes();
		ModEntities.registerEntitySpawns();
		ModEntities.registerEntitySpawnPlacements();
	}

}
